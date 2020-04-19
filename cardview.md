# cardview

![cardview demo.gif](http://ww1.sinaimg.cn/large/0083vuQJly1gdy4oyv8rog309v07lwg7.gif)

这是一个我配置好的cardview,[简书这里](https://www.jianshu.com/p/31f163f5c9d9)抄的.

1. gradle 添加: ([gradle卡住咋办](https://blog.csdn.net/u011622280/article/details/88051981))

```
dependencies {
    implementation 'androidx.cardview:cardview:1.0.0'
}
```

2. 在drawable 添加一个 `lift_on_touch.xml` 动画. 设置card的动态变化高度.

```xml
<?xml version="1.0" encoding="utf-8"?>
    <!-- animate the translationZ property of a view when pressed -->
<selector xmlns:android="http://schemas.android.com/apk/res/android">
<item
    android:state_enabled="true"
    android:state_pressed="true">
    <set>
        <objectAnimator
            android:duration="@android:integer/config_shortAnimTime"
            android:propertyName="translationZ"
            android:valueTo="6dp"
            android:valueType="floatType"/>
    </set>
</item>
<item>
    <set>
        <objectAnimator
            android:duration="@android:integer/config_shortAnimTime"
            android:propertyName="translationZ"
            android:valueTo="2dp"
            android:valueType="floatType"/>
    </set>
</item>
</selector>
```

3. 最后添加一个 cardview 的 xml代码(引用了上面的文件,我这个cardview很简单,里面只有一张图)

```xml
 <androidx.cardview.widget.CardView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:clickable="true"
        android:foreground="?attr/selectableItemBackground"
        android:stateListAnimator="@drawable/lift_on_touch"
        app:cardCornerRadius="2dp"
        app:cardPreventCornerOverlap="false"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <ImageView
            android:id="@+id/imageView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:contentDescription="TODO"
            app:srcCompat="@drawable/login_btn" />
    </androidx.cardview.widget.CardView>
```