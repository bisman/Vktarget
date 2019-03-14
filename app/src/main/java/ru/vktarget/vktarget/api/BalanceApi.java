package ru.vktarget.vktarget.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.vktarget.vktarget.models.Data;

public interface BalanceApi {
    @GET("getBalance?api_key=key")
    Call<Data> getBalance(@Query("api_key") String key);
}
