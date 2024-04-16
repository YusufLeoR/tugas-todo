package com.example.tugastodopam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodayToDoActivity extends AppCompatActivity {
    private RecyclerView rvTodo;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_to_do);

        rvTodo = findViewById(R.id.rvList);
        rvTodo.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mgm.ub.ac.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TodoApi todoApi = retrofit.create(TodoApi.class);
        Call<List<Todo>> call = todoApi.getTodos();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<Todo> todos = response.body();
                adapter = new TodoAdapter(todos);
                rvTodo.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Toast.makeText(TodayToDoActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
