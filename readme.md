## Android Log工具 
Android标准的Api中Log.i打印输出的控制台。本示例基于装饰模式,扩展了Log的功能，能将打印信息到LogView中。


## 本示例库已经支持jcenter的引入
`compile 'com.jackyang.main.test:logView:1.4.0'`

## 功能
1. 双击清除log
2. 滑动最上，最下
3. 自动滑动



## 使用
1. in the layout add FrameLayout with id framelog.
```xml
<FrameLayout
android:id="@id/framelog"
android:layout_width="match_parent"
android:layout_height="match_parent"></FrameLayout>
```

2. inject the Fragment in java.
```java
final android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final LogFragment fragment = new LogFragment();
        transaction.replace(R.id.framelog, fragment);
```

3. When get focus inject LogView to LogWrapper
```java
LogFragment fragment = (LogFragment) getSupportFragmentManager().findFragmentById(R.id.framelog);
LogCatWrapper logcat = new LogCatWrapper();
logcat.setNext(fragment.getLogView());      //wait unit the fragment has getFocus
Log.setLogNode(logcat);
```

4. use log
```java
com.jack.test.logger.Logger.d(...)
```