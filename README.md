# BroadcastTest
来自第一行代码，在此基础上修改了一点，主要是判断链接的网络是wifi还是数据流量

# 注意
此项目需要注意的是注意添加权限

# 笔记
广播分为两类

 1. 标准广播

异步、无序、不可截断

2. 有序广播

同步、有序、可截断
代码

广播注册和取消注册
``` java
//第一个参数是广播接收器  
//第二个是 过滤器
//添加action，该action是网络变化发出的
intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
registerReceiver(networkChange,intentFilter);

unregisterReceiver(networkChange);
```

获取网络状态
 1.  需要注意添加权限

``` stylus
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```


2. 代码——还可以优化
``` stylus
 ConnectivityManager connectivityManager =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo!=null && networkInfo.isAvailable()) {

                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    Toast.makeText(context, "wifi已连接", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "数据已连接", Toast.LENGTH_SHORT).show();

                }

            }else {
                Toast.makeText(context,"网络未连接", Toast.LENGTH_SHORT).show();

            }
```
发送有序广播

``` stylus
//发送有序广播
 sendOrderedBroadcast(intent,null);
```
第一个是intent 第二个是和intent相关的权限

截断广播
- 在接收器的代码处截断广播

``` stylus
 abortBroadcast();
```

本地广播 

注册本地广播和获取实例

``` stylus
//获取本地广播管理器
localBroadcastManager = LocalBroadcastManager.getInstance(this);

localReceiver = new LocalReceiver();
IntentFilter intentFilter1 = new IntentFilter();
intentFilter1.addAction("com.localReceiver");

//注册本地广播
localBroadcastManager.registerReceiver(localReceiver,intentFilter1);

```

发送本地广播

``` stylus
Intent intent = new Intent("com.localReceiver");

localBroadcastManager.sendBroadcast(intent);
```
