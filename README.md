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





# 1-2 hello Toast
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
   

    public void countUp(View view) {
        ...
        if (mShowCount != null)
            mShowCount.setText(your_string);
    }
}
```