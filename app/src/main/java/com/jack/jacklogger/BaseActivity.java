package com.jack.jacklogger;

import android.support.v7.app.AppCompatActivity;

import com.jack.test.logger.Log;
import com.jack.test.logger.LogCatWrapper;
import com.jack.test.logger.LogFragment;


/**
 * 2017/7/18.
 * <p>
 * github:[https://github.com/jacky1234]
 * qq:[847564732]
 *
 * @author yangjianfei
 * @description:
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        initializeLogging();
    }


    private void initializeLogging() {
        LogFragment logFragment = (LogFragment) getSupportFragmentManager().findFragmentById(R.id.framelog);

        LogCatWrapper logcat = new LogCatWrapper();
        logcat.setNext(logFragment.getLogView());

        Log.setLogNode(logcat);
    }
}
