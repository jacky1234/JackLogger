package com.jack.test.logger;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 2017/7/18.
 * <p>
 * github:[https://github.com/jacky1234]
 * qq:[847564732]
 *
 * @author yangjianfei
 */

public class LogFragment extends Fragment {

    private LogView mLogView;

    public LogFragment() {
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLogView = new LogView(getActivity());
        return mLogView;
    }

    public LogView getLogView() {
        return mLogView;
    }
}
