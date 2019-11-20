# Andorid-learning
[文档位置](https://developer.android.com/courses/fundamentals-training/toc-v2)
# 目录：
1-1
[添加toast](#添加toast)
[更改textview的值](#更改textview的值)

1-2
[多种布局](#多种布局)
[不同方向的layout](#不同方向的layout)



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

## 添加toast
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

## 更改textview的值
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
### 多种布局
### 不同方向的layout
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

# 1-3 ScrollView
[ScrollView on codelabs](https://codelabs.developers.google.com/codelabs/android-training-text-and-scrolling-views/index.html?index=..%2F..%2Fandroid-training#5)

scrolling view 是可以下滑看东西的空间。

ScrollView 最好搭配 LinearLayout 使用才不会有一些性能显示问题。

然而 ScrollView 把东西都放在内存里面（这样才能保证流畅），如果 ScrollView 太长会对你的app的性能产生影响。
如果要显示用户可以 添加/删除/编辑 的项目的长列表，考虑使用 RecyclerView （会在其他课程里讲到）

## 操作
### **task1**
1. 进入界面设计的 xml 更改 layout 为 ConstraintLayout
```
android.support.constraint.ConstraintLayout
    ⏬ change to ⏬
RelativeLayout
```

2. 删除这行和 ConstraintLayout 有关的
```
xmlns:app="http://schemas.android.com/apk/res-auto"
```

3. 根据[教程](https://codelabs.developers.google.com/codelabs/android-training-text-and-scrolling-views/index.html?index=..%2F..%2Fandroid-training#2)添加3个textview然后设置好属性。并且加上内容文字。（我在jandan上面弄得文字）
几个我觉得有用如下：

```
属性：
layout_width            "match_parent"          //让宽度满屏
layout_height           "wrap_content"          //让高度正好包住内容
android:layout_below    "@id/article_heading"   //让他们按顺序上下排列
android:autoLink        "web"                   //可以让link🔗变成可以点击
```

操作：
Extract the ... 

在layout 的 xml里面操作的时候，左边有灯泡。这个操作可以快速把东西放到相关的资源xml里面

### **task2**
> 添加ScrollView和活动的Web链接
1. 给textview加上这个 `android:autoLink="web"` 可以让他能够自动识别链接
2. 用 ScrollView 标签包住要滚动的东西，然后设置好 ScrollView 位置，就可以滚动了。

### **task3**
> 由于 ScrollView 只能加入一个 view 在里面。
> 
> 所以我们要给好几个view都放在一起scroll的时候就得考虑一下这个问题。
> 
> 我们可以把那几个 view 放在 LinearLayout 里面，最后相当于只有一个 大的view 被放在 ScrollView 里。
![参考理解例图](https://codelabs.developers.google.com/codelabs/android-training-text-and-scrolling-views/img/515d9464431393a7.png)

## Coding challenge
Coding challenge：给你的 scrollview 里面加入一个 button
## Homework
很简单都……不写了。

#  1.4
<https://codelabs.developers.google.com/codelabs/android-training-available-resources/index.html?index=..%2F..%2Fandroid-training#0>

- [ ] 在哪里可以找到开发人员的信息和资源。
- [ ] 如何将启动器图标添加到您的应用程序。
- [ ] 开发Android应用程序时如何寻求帮助。

## 添加图标 icon

1. 在 **res** 文件夹 右键 ，new image assets
2.  Icon Type 选择框, 选择 Launcher Icons (Adaptive & Legacy) 【默认】
3.  可以自己创建一个（用提供的material icons）或者传图片。

## 隐藏顶部栏 Hide the status bar
<https://developer.android.com/training/system-ui/status.html>

```
View decorView = getWindow().getDecorView();
// Hide the status bar.
int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
decorView.setSystemUiVisibility(uiOptions);
```