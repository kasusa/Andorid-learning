# 从Activity B 关闭 Activity A

假设我们先开启 A , 后开启 B 。

然后从 B 跳转到 C 的时候要同时关闭 A，B 

方案 ： 用 boardcast ，用 B 广播一个关闭信号, A 收到信号自己关闭自己.

---

A 中 :
```java
BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {
 
@Override
public void onReceive(Context arg0, Intent intent) {
String action = intent.getAction();
if (action.equals("finish")) {
//finishing the activity
finish();
}
}
};
registerReceiver(broadcast_reciever, new IntentFilter("finish"));****
```

B 中:
```java
Intent intent = new Intent("finish");
sendBroadcast(intent);
finish();
```