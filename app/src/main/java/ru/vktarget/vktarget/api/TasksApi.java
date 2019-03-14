package ru.vktarget.vktarget.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.vktarget.vktarget.models.TasksResponse;

public interface TasksApi {
    @GET("getTasks?api_key=key")
    Call<TasksResponse> getBalance(@Query("api_key") String key);

}
