package com.example.gopi.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.lstvw);
    }
    private void getHeroes(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);

        Call<List<Hero>> call=api.getHeroes();
           call.enqueue(new Call<List<Hero>>(){
               public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response){
                   List<Hero> heroList=response.body();
                   String[] heroes=new String[heroList.size()];
                   for(int i = 0; i < heroList.size(); i++)
                       heroes[i]=heroList.get(i).getName();
               }

        }
    }
}
