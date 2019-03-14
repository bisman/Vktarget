package ru.vktarget.vktarget.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vktarget.vktarget.R;
import ru.vktarget.vktarget.api.BalanceApi;
import ru.vktarget.vktarget.models.Data;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "AppLog";
    private SharedPreferences preferences;

    private EditText mApiKey;
    private View mProgressView;
    private View mLoginFormView;
    private Button mSignInButton;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://vktarget.ru/api/method/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    BalanceApi balanceApi = retrofit.create(BalanceApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mApiKey = findViewById(R.id.api_key);
        preferences = getPreferences(Context.MODE_PRIVATE);

        mApiKey.setText(preferences.getString("key", ""));

        mSignInButton = findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.api_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void attemptLogin() {
        mApiKey.setError(null);

        String apiKey = mApiKey.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(apiKey)) {
            mApiKey.setError(getString(R.string.error_field_required));
            focusView = mApiKey;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            Log.d(TAG, "attemptLogin: " + apiKey);
            autorizaton(apiKey);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });
        mSignInButton.setVisibility(show ? View.GONE : View.VISIBLE);
        mSignInButton.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mSignInButton.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    /**
     * Background thread
     */
    private void autorizaton(final String key) {
        Call<Data> balance = balanceApi.getBalance(key);
        balance.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("key", key);
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("key", key);
                        intent.putExtra("balance", response.body().getResponse().getBalance());
                        startActivity(intent);
                    } else {
                        mApiKey.setError(response.body().getResponse().getError());
                    }
                }
                showProgress(false);
            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {
                Log.e("Error", "LoadError: ", t);
                showProgress(false);
            }
        });
    }
}

