package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionaryapp.ApiHandlers.OnFetchDataListener;
import com.example.dictionaryapp.ApiHandlers.RequestManager;
import com.example.dictionaryapp.adapters.MeaningsAdapter;
import com.example.dictionaryapp.adapters.PhoneticAdapter;
import com.example.dictionaryapp.models.ApiResponse;

public class MainActivity extends AppCompatActivity {
    TextView tv_word;
    RecyclerView rv_phonetics, rv_meanings;
    SearchView searchView;
    ProgressDialog progressDialog;
    CardView cv_word, cv_phonetic, cv_meaning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_phonetics = findViewById(R.id.rv_phonetic);
        rv_meanings = findViewById(R.id.rv_meaning);
        tv_word = findViewById(R.id.tv_word);
        searchView = findViewById(R.id.search_view_main);
        cv_word = findViewById(R.id.cv_word);
        cv_phonetic = findViewById(R.id.cv_phonetic);
        cv_meaning = findViewById(R.id.cv_meaning);
        progressDialog = new ProgressDialog(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getWordMeaning(listener, s);
                searchView.clearFocus();
                progressDialog.setTitle("Searching Result for "+s);
                progressDialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }
    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetch(ApiResponse apiResponse, String message) {
            progressDialog.dismiss();
            if(apiResponse!=null){
                showData(apiResponse);
            }else{
                Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(ApiResponse apiResponse) {
        cv_word.setVisibility(View.VISIBLE);
        cv_meaning.setVisibility(View.VISIBLE);
        cv_phonetic.setVisibility(View.VISIBLE);
        tv_word.setText(apiResponse.getWord());
        PhoneticAdapter pAdapter = new PhoneticAdapter(MainActivity.this, apiResponse.getPhonetics());
        MeaningsAdapter mAdapter = new MeaningsAdapter(MainActivity.this, apiResponse.getMeanings());
        rv_phonetics.setAdapter(pAdapter);
        rv_meanings.setAdapter(mAdapter);
        rv_phonetics.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv_meanings.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}