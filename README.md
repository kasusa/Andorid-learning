# Andorid-learning
[文档位置](https://developer.android.com/courses/fundamentals-training/toc-v2)

[第一篇1.1](https://codelabs.developers.google.com/codelabs/android-training-hello-world/index.html?index=..%2F..%2Fandroid-training#7)

## 使用`logcat`：
* **虚拟设备**：无需操作
* **真实设备**：打开 `性能优化` - `高级日志输出` 才能看到log
* log 查看位置：`AndroidStudio`底部 > `Logcat` > 选好设备 > `debug`

```
Log.d("MainActivity", "Hello World"); 
```

## Coding challenge
- [x] Create a new project in Android Studio.
- [x] Change the "Hello World" greeting to "Happy Birthday to " and the name of someone with a recent birthday.
- [x] (Optional) Take a screenshot of your finished app and email it to someone whose birthday you forgot.
- [x] A common use of the Log class is to log Java exceptions when they occur in your program. There are some useful methods, such as Log.e(), that you can use for this purpose. Explore methods you can use to include an exception with a Log message. Then, write code in your app to trigger and log an exception.

最后一题做法：
```
public  class  logJavaExceptions{
    public logJavaExceptions(){
        int[] array = {0,1,2,3};
        try{
            System.out.println(array[4]);
        }catch(Exception e){
            Log.e("arrayout","array is out of the stuff");
        }

    }
}
```
> 要点：`try`语句只能在函数里面写，不能直接写在`class`里面，会报错。
> 
> 输出：`Log.e("tag","message");`
> 
[安卓版本和api对应号码](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-1-build-your-first-app/1-0-c-introduction-to-android/1-0-c-introduction-to-android.html)（网页内搜索`Android versions`）





# 1-2 hello Toast A
[教程页面](https://codelabs.developers.google.com/codelabs/android-training-layout-editor-part-a/index.html?index=..%2F..%2Fandroid-training#9) | 
[ConstraintLayout](https://codelabs.developers.google.com/codelabs/constraint-layout/index.html#0) | 
[文件目录内容](https://developer.android.com/guide/topics/resources/providing-resources?hl=zh-CN)

```
layout_width
layout_height
```
* 使用string.xml存字符串
*  添加toast按钮（给按钮添加函数）：在按钮的xml里面添加如下句子：
```
android:onClick="showToast"
```

## 添加`toast`
1. `toast`需要你所在的`activity`,因为他需要显示在当前`activity`的前面
2. 要显示的 `string`
3. 要显示的时间长度 如： `Toast.LENGTH_SHORT` (2s) / `Toast.LENGTH_LONG` (3.5s)
4. 使用 `show()` 来显示 
> 注意没有 `new`
```
public void showToast(View view) {
   Toast toast = Toast.makeText(this, R.string.toast_message, 
                                          Toast.LENGTH_SHORT);
   toast.show();
}
```

## 更改textview的值：
1. 新建一个全局 `Textview` 用来存放 `reference`
2. 在 `onCreate` 函数添加 `findViewById` 函数
3. `textview.setText()` 传递数

```
public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        setContentView(R.layout.activity_main);
        mTextView =  (TextView) findViewById(R.id.your_textview_id);
    }
   

    public void countUp(View view) {}}
        ...
        if (mShowCount != null)
            mShowCount.setText(your_string);
    }
}
```

# 1-2 hello Toast B
## 多方向布局
手机旋转图标 > 创建landspacelayout
## 使用 LinearLayout
1. 把xml首行更改
```
<android.support.constraint.ConstraintLayout xmlns:android="http:...
<LinearLayout xmlns:android="http:...
```
2. 并且在 `LinearLayout` 中增加下面这句让他变成纵向
```
android:orientation="vertical"
```
3. 删除所有 `app:layout_constraint` 开头的行
4. 更改红线部分

原版 | 改为
------- | -------
android:layout_width="0dp" | android:layout_width="match_parent"
android:layout_height="0dp" | android:layout_height="wrap_content"

5. 调整部件位置：只需要像 `html` 一样调整代码片段顺序即可
6. 调整部件大小：添加 `weight` 属性
```
   android:gravity="center"
   android:layout_weight="1"
```
> The android:layout_weight attribute indicates how much of the extra space in the LinearLayout will be allocated to the View. If only one View has this attribute, it gets all the extra screen space. For multiple View elements, the space is prorated. For example, if the Button elements each have a weight of 1 and the TextView 2, totaling 4, the Button elements get ¼ of the space each, and the TextView half.


## 使用 RelativeLayout
1. 打开 `activity_main.xml `
2. 把 `<LinearLayout` 改为 `<RelativeLayout `
   > 注意底部标签也要跟着改动
3. 给 `button_count` 添加属性
```
   android:layout_below="@+id/show_count"
```
4. 给 `show_count TextView` 添属性
```
android:layout_below="@+id/button_toast"
android:layout_alignParentLeft="true"
android:layout_alignParentStart="true"
```
> The android:`layout_alignParentLeft` aligns the view to the left side of the `RelativeLayout` parent view group. While this attribute by itself is enough to align the view to the left side, you may want the view to align to the right side if the app is running on a device that is using a right-to-left language. Thus, the android:layout_alignParentStart attribute makes the "start" edge of this view match the start edge of the parent. The start is the left edge of the screen if the preference is left-to-right, or it is the right edge of the screen if the preference is right-to-left.

5. 删除 `android:layout_weight="1"` 属性，因为它和`relativelayout`不兼容

## 小作业
- [x] 将项目名称更改为HelloConstraint，并将项目重构为Hello Constraint。（有关如何复制和重构项目的说明，请参阅附录：实用程序。）
- [x] 修改activity_main.xml布局以使Toast和CountButton元素沿show_count TextView显示“ 0” 的左侧对齐。有关布局，请参见下图。
- [x] 包括第三Button称为零的出现之间的吐司和计数Button的元素。
- [x] Button在顶部和底部之间垂直分布元素show_count TextView。
- [x] 将“ 零”设置Button为最初具有灰色背景。
- [x] 确保在的横向包含零Button，在activity_main.xml (land)的平板电脑尺寸的屏幕也包含activity_main (xlarge)。
- [x] 将“ 零”Button更改show_count TextView为0。
- [x] 更新Count的点击处理程序Button，使其根据新计数是奇数还是偶数来更改其自身的背景颜色。

**学习到** 

设置按钮背景颜色，
除了这样：
```
private Button mbuttonZero;
mbuttonZero =  (Button) findViewById(R.id.button_zero);
mbuttonZero.setBackgroundColor(Color.parseColor("#008577"));
// #008577 是在color.xml找到的PrimaryColor
```

还可以直接：

```
view.setBackgroundColor()
```