package com.example.livedataapp;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.*;

public class MainActivity extends AppCompatActivity {

    private TextView nameTextView;
    private Button button;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setComponents();

        final MainActivityViewModel model = new ViewModelProvider(this).get(MainActivityViewModel.class);
        final MutableLiveData<String> myRandomNumber = model.getNumber();
        myRandomNumber.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                nameTextView.setText(s);
                Log.i(TAG,"Data Updated in UI");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.createNumber();
            }
        });


        Log.i(TAG, "Random Number Set");
    }

    private void setComponents() {
        button = findViewById(R.id.buttonRefresh);
        nameTextView = findViewById(R.id.nameTextView);
    }
}