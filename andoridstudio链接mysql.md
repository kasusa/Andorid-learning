
在` build.gradle`里面添加这个

```
// https://mvnrepository.com/artifact/mysql/mysql-connector-java
    implementation group: 'mysql', name: 'mysql-connector-java', version: '5.1.39'
```

然后点击sync , 这一步的作用是导入connector . 接下来看:
[菜鸟教程](https://www.runoob.com/java/java-mysql-connect.html)
就可以了.

---

期间遇到了一个问题,会蹦出来一堆的红字,看着不舒服.
```
MySQL – Establishing SSL connection without server’s identity verification is not recommended
```
解决方法是在你的链接字符串后面添加 : `?useSSL=false` 
(大致如下)
```
spring.datasource.url=jdbc:mysql://localhost/mkyong?useSSL=false
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

---

一个安卓的问题 :

Android应用访问网络时需要先设置权限，在AndroidManifest.xml文件里设置权限：

```
<uses-permission android:name="android.permission.INTERNET"/>
```

。设置完后应用才可以联网。

并且Android应用不允许在主线程里连接网络，连接网络需要单开线程连接。

[菜鸟 多线程](https://www.runoob.com/java/java-multithreading.html)

由于我这边是多线程传数据,还需要等待线程执行完出结果,我又写了个类.
总共:

---
## mysql链接获得数据代码:

```java
public class MySQLutil{

    //    ip :123.56.18.36
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://123.56.18.36:3306/andorid-2020-spring?useSSL=false&serverTimezone=Asia/Shanghai";
    static final String USER = "root";
    static final String PASS = "**********";
    

    /**
     * 判断citizenID是否存在的链接mysql函数
     * @param compare_citizenID 要判断的citizenID
     * @return 如果存在, 返回true
     */
    public static boolean IsCitizenidAlreadyExist(String compare_citizenID) {
        boolean ans = false;

        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = " SELECT citizenID FROM citizen where citizenID = '"+ compare_citizenID +"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next() != false){
                 ans = true;
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

        return ans;
    }
}
```

## 新线程类的代码

```java
public class Thread_IsCitizenAlreadyExist implements Runnable {
    private Thread t;
    private String id ;
    public boolean ans = false ;

    public Thread_IsCitizenAlreadyExist(String citizenid) {
        id = citizenid;
    }

    public void run() {
        Log.println(Log.INFO,"meow","thread: 开始查找数据库中有没有这个 citizenid");
        ans = MySQLutil.IsCitizenidAlreadyExist(id);
        Dataclass.IsCitizenAlreadyExist = ans;
        Dataclass.threadDone();
        Log.println(Log.INFO,"meow","thread: 完成任务,citizenid 存在 : " + ans);
    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}
```

## 中间数据传递类

```java
public class Dataclass {
    public static boolean IsCitizenAlreadyExist =false;
    public static  boolean threadDone = false;

    public static void reset(){
        IsCitizenAlreadyExist = false;
        threadDone = false;

    }
    public static void threadDone(){
        threadDone = true;
    }
}

```

## 主线程那边的代码

这么多就是为了获得下数据库中存在不存在citizenID `citizenidExist`

```java
// 这里开始处理 如果身份证符合规则,判断是否存在于mysql的citizen表中. (新建线程
Dataclass.reset();
Thread_IsCitizenAlreadyExist t = new Thread_IsCitizenAlreadyExist(id);
t.start();
Toast toast = Toast.makeText(this, "查询远程数据库中", Toast.LENGTH_SHORT);
toast.show();
// 线程未完成数据拿的时候等待.
while (!Dataclass.threadDone)
    Thread.sleep(500);
// (线程结束

boolean citizenidExist = Dataclass.IsCitizenAlreadyExist;
```