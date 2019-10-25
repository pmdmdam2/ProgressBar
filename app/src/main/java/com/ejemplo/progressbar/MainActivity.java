package com.ejemplo.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinkedList<ProgressBar> progressBar;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=new LinkedList<ProgressBar>();

        final Thread thred = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
                for(int i=1;i<6;i++)
                    progressBar.get(i).setVisibility(View.INVISIBLE);
            }
        });

        Button btStart = findViewById(R.id.btStart);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<6;i++)
                    progressBar.get(i).setVisibility(View.VISIBLE);
                thred.start();
            }
        });

        progressBar.add((ProgressBar)findViewById(R.id.pB1));
        progressBar.add((ProgressBar)findViewById(R.id.pB2));
        progressBar.add((ProgressBar)findViewById(R.id.pB3));
        progressBar.add((ProgressBar)findViewById(R.id.pB4));
        progressBar.add((ProgressBar)findViewById(R.id.pB5));
        progressBar.add((ProgressBar)findViewById(R.id.pB6));

    }
    public void doWork() {
        for (progress=10;progress<110;progress+=10)
        {
            try {
                Thread.sleep(1000);
                for(ProgressBar pB:progressBar){
                    pB.setProgress(progress);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
