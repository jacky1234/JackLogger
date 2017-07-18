package com.jack.jacklogger;

import android.os.Bundle;
import android.view.View;

import com.jack.jacklogger.core.Log;
import com.jack.jacklogger.core.LogFragment;

public class MainActivity extends BaseActivity {

    boolean bStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final LogFragment fragment = new LogFragment();
        transaction.replace(R.id.framelog, fragment);
        transaction.commit();


        findViewById(R.id.b_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bStart) {
                    bStart = true;
                    new Thread(new LogTask()).start();
                }
            }
        });

        findViewById(R.id.b_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bStart = false;
            }
        });
    }

    private int i = 'a';

    private class LogTask implements Runnable {
        @Override
        public void run() {
            while (bStart) {
                try {
                    Thread.sleep(300);
                    Log.d("MainActivity", (char) i + "");
                    i++;

                    if (i > 'z') {
                        i = 'a';
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}