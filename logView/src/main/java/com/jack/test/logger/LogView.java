package com.jack.test.logger;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jack.test.logger.Log.LogNode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2017/7/18.
 * <p>
 * github:[https://github.com/jacky1234]
 * qq:[847564732]
 *
 * @author yangjianfei
 */

public class LogView extends FrameLayout implements LogNode, TextWatcher {

    private LogNode mNext;
    private ScrollView mScrollView;
    private TextView mTextView;

    private boolean enableAutoScroll = true;

    public LogView(Context context) {
        this(context, null);
    }

    public LogView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mScrollView = new ScrollView(context);

        mScrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        mTextView = new TextView(context);
        mTextView.setClickable(true);

        final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent e) {
                mTextView.setText("");
                return true;
            }
        });
        mTextView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
        mTextView.addTextChangedListener(this);

        mScrollView.addView(mTextView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addView(mScrollView);
    }

    public LogNode getNext() {
        return mNext;
    }

    public void setNext(LogNode node) {
        mNext = node;
    }

    @Override
    public void println(int priority, String tag, String msg, Throwable tr) {

        String priorityStr = null;

        switch (priority) {
            case Log.DEBUG:
                priorityStr = "D";
                break;
            case Log.INFO:
                priorityStr = "I";
                break;
            case Log.WARN:
                priorityStr = "W";
                break;
            case Log.ERROR:
                priorityStr = "E";
                break;
            default:
                break;
        }

        String exceptionStr = null;
        if (tr != null) {
            exceptionStr = android.util.Log.getStackTraceString(tr);
        }

        final StringBuilder outputBuilder = new StringBuilder();

        //String delimiter = "|";
        //appendIfNotNull(outputBuilder, priorityStr, delimiter);
        //appendIfNotNull(outputBuilder, tag, delimiter);
        //appendIfNotNull(outputBuilder, msg, delimiter);
        //appendIfNotNull(outputBuilder, exceptionStr, "");
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        outputBuilder.append(priorityStr + " ");
        outputBuilder.append(str);
        outputBuilder.append(" ");
        outputBuilder.append(msg);
        outputBuilder.append("\r\n");

        ((Activity) getContext()).runOnUiThread((new Thread(new Runnable() {
            @Override
            public void run() {
                appendToLog(outputBuilder.toString());
            }
        })));

        if (mNext != null) {
            mNext.println(priority, tag, msg, tr);
        }
    }

    @Override
    public void clearLog() {
        mTextView.setText("");
    }

    @Override
    public void up() {
        enableAutoScroll = false;
        mScrollView.fullScroll(ScrollView.SCROLL_INDICATOR_TOP);
    }

    @Override
    public void down() {
        enableAutoScroll = true;
        scrollDown();
    }

    @Override
    public void enableAutoScroll() {
        enableAutoScroll = true;
    }

    public void appendToLog(String s) {
        mTextView.append("\n" + s);
    }

    private StringBuilder appendIfNotNull(StringBuilder source, String addStr, String delimiter) {
        if (addStr != null) {
            if (addStr.length() == 0) {
                delimiter = "";
            }

            return source.append(addStr).append(delimiter);
        }
        return source;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (enableAutoScroll) {
            scrollDown();
        }
    }

    private void scrollDown() {
        mScrollView.post(new Runnable() {

            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }
}
