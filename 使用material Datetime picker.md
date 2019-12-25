# 教你如何在最新的安卓开发环境使用MaterialDateTimePicker
> 2019-12-25 开发套件都是andoridx之类的。
> 如果你看不了图片，右键打开应该可以看。因为我用的新浪微博图片上传

> 注意：我参考的教程虽然有一定的参考意义，但是如果你完全按照它做会失败。而我个人对于fragment理解也很薄弱，如有代码不优雅的地方请见谅。

[官方网站](https://github.com/wdullaer/MaterialDateTimePicker)
[我参考的教程](https://www.freakyjolly.com/android-material-datepicker-and-timepicker-by-wdullaer-tutorial-by-example/)

![datepicker](https://wx1.sinaimg.cn/mw690/006rgJELgy1ga8z8oqghej30f10pmmzm.jpg)
![time picker](https://wx4.sinaimg.cn/mw690/006rgJELgy1ga8z8opb1rj30c80oqjt4.jpg)

# 添加gradle内容
> 默认你创建一个empty activity 的空项目

在 build.gradle (app) 内部添加：

```
dependencies {
    implementation 'com.wdullaer:materialdatetimepicker:4.2.3'
    ...
}
```

```
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    ...
}
```
> 第一个是添加这个远程项目的库什么的，第二个会防止跳出一个什么 andorid N 的错误

# 编写activity.xml
布局内容很简单。就是俩button，俩textview一个点一些跳出【选日期】另一个点一下跳出【选时间】

# 更改activity类
## 让你的activity类继承下这两个界面：

```java

public class MainActivity extends AppCompatActivity implements 
DatePickerDialog.OnDateSetListener, 
TimePickerDialog.OnTimeSetListener 
{
...
```
你会发现划红线了。你需要import一下这两个类

```java
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

```
还是有红线。你将会需要继承两个接受返回值的方法我们稍后会说。
## mainActivity加入变量
```java
    FragmentManager fragmentManager;

    DatePickerDialog datePickerDialog ;
    TimePickerDialog timePickerDialog ;
    int Year, Month, Day, Hour, Minute;
    Calendar calendar ;
```

## Oncreate中加入初始化
```java
fragmentManager = getSupportFragmentManager();
```
> andoridx

## 弹出弹出的函数
Date picker
```java
    public void Datepicker(View v) {
        datePickerDialog = DatePickerDialog.newInstance(MainActivity.this, Year, Month, Day);
        datePickerDialog.setThemeDark(false);
        datePickerDialog.showYearPickerFirst(false);
        datePickerDialog.setTitle("Date Picker");
        fragmentManager =getSupportFragmentManager();
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialogInterface) {

                Toast.makeText(MainActivity.this, "Datepicker Canceled", Toast.LENGTH_SHORT).show();
            }
        });
        datePickerDialog.show(fragmentManager, "DatePickerDialog");
    }
```
TimePicker
```java
    public void Timepicker(View v) {
        timePickerDialog = TimePickerDialog.newInstance(MainActivity.this, Hour, Minute,false );
        timePickerDialog.setThemeDark(false);
        //timePickerDialog.showYearPickerFirst(false);
        timePickerDialog.setTitle("Time Picker");

        timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialogInterface) {

                Toast.makeText(MainActivity.this, "Timepicker Canceled", Toast.LENGTH_SHORT).show();
            }
        });

        timePickerDialog.show(fragmentManager, "TimePickerDialog");
    }
```

编写这两个函数去activity页面点击按钮，编辑onclick事件，选择对应的函数：
![编辑onclick](https://wx1.sinaimg.cn/mw690/006rgJELgy1ga8zzjtusrj30e604umx3.jpg)

## 接受返回数据的函数
这里点击你的mainactivity这里有的大红线，我们来implement方法。
![继承方法@override](https://wx1.sinaimg.cn/mw690/006rgJELgy1ga904avnmij30os07v74m.jpg)

创建了两个重写的方法，会在选好日期/时间的时候由picker调用。

这里我就把值用toast显示一下，你当然也可以赋值给真正有意义的东西。


Date picker 返回的年份和日期是正常的，月份却是0~11.

```java
@Override
public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
    String date = "Date: "+year+"-"+monthOfYear+1+"-"+dayOfMonth;
    Toast.makeText(MainActivity.this, date, Toast.LENGTH_LONG).show();
}

```

Time Picker

```java
@Override
public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
    String time = "Time: "+hourOfDay+"h"+minute+"m"+second;
    Toast.makeText(MainActivity.this, time, Toast.LENGTH_LONG).show();
}
```

## 设置最小日期

你可以在打开日期的函数里加上来把最小的日期设为今天。
这丫打开的时候就不会直接跳转到1900年了。
```java
// Setting Min Date to today date
Calendar min_date_c = Calendar.getInstance();
datePickerDialog.setMinDate(min_date_c);
```
> 实际上它还提供了设置最大日期的方法，但是我每次做都闪退我就不写了。
> 
> 有什么不会的都可以直接 【ctrl按住】点击方法，会跳转到官方的代码，一般都是有javaDoc写好的，可惜都是英文的。

谢谢你的关注~