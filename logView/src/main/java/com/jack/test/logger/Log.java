package com.jack.test.logger;

/**
 * 2017/7/18.
 * <p>
 * github:[https://github.com/jacky1234]
 * qq:[847564732]
 *
 * @author yangjianfei
 *         输出内容到 LogView 中
 */

public class Log {

    public interface LogNode {
        void println(int priority, String tag, String msg, Throwable tr);

        void clearLog();

        void up();

        void down();

        void enableAutoScroll();
    }

    public static final int DEBUG = android.util.Log.DEBUG;
    public static final int INFO = android.util.Log.INFO;
    public static final int WARN = android.util.Log.WARN;
    public static final int ERROR = android.util.Log.ERROR;

    private static LogNode mLogNode;

    public static LogNode getLogNode() {
        return mLogNode;
    }

    public static void setLogNode(LogNode node) {
        mLogNode = node;
    }

    public static void d(String tag, String msg, Throwable tr) {
        println(DEBUG, tag, msg, tr);
    }

    public static void d(String tag, String msg) {
        d(tag, msg, null);
    }

    public static void i(String tag, String msg, Throwable tr) {
        println(INFO, tag, msg, tr);
    }

    public static void i(String tag, String msg) {
        i(tag, msg, null);
    }

    public static void w(String tag, String msg, Throwable tr) {
        println(WARN, tag, msg, tr);
    }

    public static void w(String tag, String msg) {
        w(tag, msg, null);
    }

    public static void w(String tag, Throwable tr) {
        w(tag, null, tr);
    }

    public static void e(String tag, String msg, Throwable tr) {
        println(ERROR, tag, msg, tr);
    }

    public static void e(String tag, String msg) {
        e(tag, msg, null);
    }

    public static void println(int priority, String tag, String msg, Throwable tr) {
        if (mLogNode != null) {
            mLogNode.println(priority, tag, msg, tr);
        }
    }

    public static void println(int priority, String tag, String msg) {
        println(priority, tag, msg, null);
    }

    public static void up() {
        mLogNode.up();
    }

    public static void down() {
        mLogNode.down();
    }

    public static void clearLog() {
        mLogNode.clearLog();
    }

    public static void enableAutoScroll() {
        mLogNode.enableAutoScroll();
    }

}
