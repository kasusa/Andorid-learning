# Andorid-learning
[文档位置](https://developer.android.com/courses/fundamentals-training/toc-v2)

# 目录：
0-0
* [跳转不同activity并传值](#跳转不同activity并传值)
* [添加向上导航功能](#添加向上导航功能)
* [logcat](#logcat)

0-1
* [sqlite](#sqlite)

1-1
* [添加toast](#添加toast)
* [获取并更改textview的值](#获取并更改textview的值)

1-2
* [多种布局](#多种布局)
* [不同方向的layout](#不同方向的layout)
* [设置按钮背景颜色](#设置按钮背景颜色)
* [LinearLayout](#LinearLayout)
* [RelativeLayout](#RelativeLayout)

1-3
* [ScrollView](#ScrollView)

1-4
* [添加图标icon](#添加图标icon)
* [#隐藏顶部栏](#隐藏顶部栏)

# 0-0
## 跳转不同activity并传值

**不传值** 

```java
public void sendMessage(View view) {
    Intent intent = new Intent(this, ACTIVITY_JUMPING_TO.class);
    startActivity(intent);
}
```

**传值** 起点界面activity

```java
public static final String EXTRA_MESSAGE = "YOUR_PACKAGE_NAME.MESSAGE";

public void sendMessage(View view) {
    Intent intent = new Intent(this, ACTIVITY_JUMPING_TO.class);
    EditText editText = (EditText) findViewById(R.id.editText);
    String message = editText.getText().toString();
    intent.putExtra(EXTRA_MESSAGE, message);
    startActivity(intent);
}
```

**传值** 目标界面activity（在oncreate）

```java
// Get the Intent that started this activity and extract the string
Intent intent = getIntent();
String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

// Capture the layout's TextView and set the string as its text
TextView textView = findViewById(R.id.textView);
textView.setText(message);
```

## logcat
```java
Log.println(Log.INFO,"meow","获取内容了：");
```

## 添加向上导航功能
在 mainfest.mxl 里面

```xml
<activity android:name=".DisplayMessageActivity"
          android:parentActivityName=".MainActivity">
    <!-- The meta-data tag is required if you support API level 15 and lower -->
    <meta-data
        android:name="android.support.PARENT_ACTIVITY"
        android:value=".MainActivity" />
</activity>
```

[第一篇1.1](https://codelabs.developers.google.com/codelabs/android-training-hello-world/index.html?index=..%2F..%2Fandroid-training#7)


## 使用`logcat`：
* **虚拟设备**：无需操作
* **真实设备**：打开 `性能优化` - `高级日志输出` 才能看到log
* log 查看位置：`AndroidStudio`底部 > `Logcat` > 选好设备 > `debug`

```java
Log.d("MainActivity", "Hello World"); 
```

## Coding challenge
- [x] Create a new project in Android Studio.
- [x] Change the "Hello World" greeting to "Happy Birthday to " and the name of someone with a recent birthday.
- [x] (Optional) Take a screenshot of your finished app and email it to someone whose birthday you forgot.
- [x] A common use of the Log class is to log Java exceptions when they occur in your program. There are some useful methods, such as Log.e(), that you can use for this purpose. Explore methods you can use to include an exception with a Log message. Then, write code in your app to trigger and log an exception.

最后一题做法：
```java
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
```java
Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
toast.show();
//或者是
Toast.makeText(context, R.string.strErrorSql, Toast.LENGTH_SHORT).show();
```

## 获取并更改textview的值
1. 新建一个全局 `Textview` 用来存放 `reference`
2. 在 `onCreate` 函数添加 `findViewById` 函数
3. `textview.setText()` 传递数

```java
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
## LinearLayout
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


## RelativeLayout
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

**学习到** 

## 设置按钮背景颜色
除了这样：
```java
private Button mbuttonZero;
mbuttonZero =  (Button) findViewById(R.id.button_zero);
mbuttonZero.setBackgroundColor(Color.parseColor("#008577"));
// #008577 是在color.xml找到的PrimaryColor
```

还可以直接：

```java
view.setBackgroundColor()
```

# 1-3 

## ScrollView
[ScrollView on codelabs](https://codelabs.developers.google.com/codelabs/android-training-text-and-scrolling-views/index.html?index=..%2F..%2Fandroid-training#5)

scrolling view 是可以下滑看东西的空间,最好搭配 LinearLayout 使用才不会有一些显示问题。

然而 ScrollView 把东西都放在内存里面（这样才能保证流畅），如果 ScrollView 太长会对你的app的性能产生影响。
如果要显示用户可以 添加/删除/编辑 的项目的长列表，考虑使用 RecyclerView （会在其他课程里讲到）

## 操作
### **task1**
1. 进入界面设计的 xml 更改 layout 为 ConstraintLayout
```
android.support.constraint.ConstraintLayout
⏬ 
RelativeLayout
```

2. 删除这行和 ConstraintLayout 有关的
```
xmlns:app="http://schemas.android.com/apk/res-auto"
```

3. 根据[教程](https://codelabs.developers.google.com/codelabs/android-training-text-and-scrolling-views/index.html?index=..%2F..%2Fandroid-training#2)添加3个textview然后设置好**属性**。并且加上内容文字。（我在jandan上面弄得文字）
几个我觉得有用de属性如下：

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

### 添加ScrollView和活动的Web链接
1. 给textview加上这个 `android:autoLink="web"` 可以让他能够自动识别链接
2. 用 ScrollView 标签包住要滚动的东西，然后设置好 ScrollView 位置，就可以滚动了。

### ScrollView 放入 LinearLayout 里
> 由于 ScrollView 只能加入一个 view 在里面。
> 
> 所以我们要给好几个view都放在一起scroll的时候就得考虑一下这个问题。
> 
> 我们可以把那几个 view 放在 LinearLayout 里面，最后相当于只有一个 大的view 被放在 ScrollView 里。
![参考理解例图](https://codelabs.developers.google.com/codelabs/android-training-text-and-scrolling-views/img/515d9464431393a7.png)


#  1.4

## 添加图标icon

1. 在 **res** 文件夹 右键 ，new image assets
2.  Icon Type 选择框, 选择 Launcher Icons (Adaptive & Legacy) 【默认】
3.  可以自己创建一个（用提供的material icons）或者传图片。

## 隐藏顶部栏
>  Hide the status bar
<https://developer.android.com/training/system-ui/status.html>

```java
View decorView = getWindow().getDecorView();
// Hide the status bar.
int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
decorView.setSystemUiVisibility(uiOptions);
```

# 0-1
# sqlite
导航：
* 以下的东西都需要现有一个DBhelper类
* [插入并logcat显示结果](#插入并logcat显示结果)
* [读取并用logcat输出](#读取并用logcat输出)
* [删除数据](#删除数据)
* [更新数据库](#更新数据库)
* [资源控制](#资源控制)

链接：
* [ANDORID sqlite教程 ](https://developer.android.com/training/data-storage/sqlite#DbHelper)
* [sqlitebrowser ⏬](https://sqlitebrowser.org/) 
* [sqlite的类型](https://www.sqlite.org/datatype3.html)

google官方会推荐你用一个叫做room的东西。但是好像我开着飞机都连不上墙外的样子。所以算了吧。还是用dbhelper。youtube教程有坑。你们不要看那个印度佬的祖传视频了。

新建一个 `DbHelper` class

```java
package android.example.ADemos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DATABASE_NAME.db";
    String SQL_CREATE_ENTRIES = "";
    String SQL_DELETE_ENTRIES = "";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

```

在界面类里面加入初始化：

```java
DbHelper dbHelper;

oncreate(){
 dbHelper = new DbHelper(this);
}

```
## 插入并logcat显示结果

```java
    public void Insert_hao(View view) {
        //写模式打开db
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //创建vContentalues 并向里面放数据
        ContentValues values = new ContentValues();
        values.put("name","星马豪");
        values.put("gender","男");
        values.put("birthday","8月1日");
        values.put("Animation","四驱兄弟");
        //插入数据
        long newRowId = db.insert("AnimeCharacter", null, values);//参数2：null是在你没有给的values列上面插入一个sql可以接受的null值
        //获取它的返回值-1是失败，其他数字是插入所在的行数的PK
        if(newRowId == -1){
            Log.println(Log.INFO,"meow","插入失败~");
        }else {
            String MSG = "插入成功，插入在第 "+newRowId+" 行";
            Log.println(Log.INFO,"meow",MSG);
        }
    }
```

## 读取并用logcat输出
```java
// 我看有的人的代码是用try的。不过这里还是不用try更加清晰一点
public void Read(View view){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // projection数组用啦存储要查询的列名字
        String[] projection = {
                "ID",
                "name",
                "gender",
                "birthday",
                "Animation"
        };

        Cursor cursor = db.query(
                "AnimeCharacter",   // table name
                projection,         // 要查的列名字符串数组
                null,               // The columns for the WHERE clause
                null,               // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                null                // The sort order
        );
        // logcat打印
        Log.println(Log.INFO,"meow","获取内容了：");
        //这里开始是读取
        // 可以读取string或者龙类型这里同样logcat输出不过实际是你可以赋值给任何变量了
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow("ID"));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow("name")
            );
            Log.println(Log.INFO,"meow","获取id： "+itemId+"name: "+name);
        }
        cursor.close();
    }
}

```

## 删除数据
```java
// 相当于：DELETE FROM AnimeCharacter WHERE gender like '男'
public void Delete(View view){
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    String selection = "gender" + " LIKE ?";  //“？” 部分会被替换为下面的 selectionArgs
    String[] selectionArgs = { "男" }; //可以多选
    int deletedRows = db.delete("AnimeCharacter", selection, selectionArgs);//表名字和上分两个参数
}
```

## 更新数据库

```java
public void Update_maleToUnknow(View view){
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    
    ContentValues values = new ContentValues();
    values.put("gender", "unknow"); //values.put("列名", "新值");

    String selection = "gender" + " LIKE ?";//“？” 部分会被替换为下面的 selectionArgs
    String[] selectionArgs = { "男" };//可以多选
    int count = db.update(
            "AnimeCharacter",   //表名
            values,             //插入值类 ContentValues
            selection,          //选行参数部分1
            selectionArgs);     //选行参数部分2
}
```

## 资源控制
> 由于在数据库关闭时，调用 getWritableDatabase() 和 getReadableDatabase() 的成本比较高，因此只要您有可能需要访问数据库，就应保持数据库连接处于打开状态。通常情况下，最好在发出调用的 Activity 的 onDestroy() 中关闭数据库。

结论是在destory里面写上些：

```java
@Override
protected void onDestroy() {
    dbHelper.close();
    super.onDestroy();
}
```    
