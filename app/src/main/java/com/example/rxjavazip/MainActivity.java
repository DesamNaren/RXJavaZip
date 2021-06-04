package com.example.rxjavazip;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView resTv = (TextView) findViewById(R.id.res);

        EmpViewModel viewModel = new ViewModelProvider(this).get(EmpViewModel.class);
        resTv.setText("Loading.....");
        viewModel.getData(new EmpRepository())
                .observe(this, new Observer<List<String>>() {
                    @Override
                    public void onChanged(List<String> strings) {
                        if (strings != null) {
                            resTv.setText(strings.get(0) + "\n" +
                                    strings.get(1) + "\n"
                                    + strings.get(2));
                        } else
                            resTv.setText("Not found");
                    }
                });
    }
}