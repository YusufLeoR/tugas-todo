package com.example.tugastodopam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoApi {
    @GET("todo.php")
    Call<List<Todo>> getTodos();
}
