package com.example.tuionf.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NetworkChange networkChange ;
    private IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkChange = new NetworkChange();
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //注册广播
        registerReceiver(networkChange,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChange);
    }

    class NetworkChange extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

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
        }
    }
}
