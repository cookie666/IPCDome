package com.cookie.ipcdome.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import com.cookie.ipcdome.IMyAidlInterface;
import com.cookie.ipcdome.R;

/**
 * Created by Chen Lin Jiang on 2017/2/22.
 */

public class RemoteService extends Service {
    private MyBinder binder;
    private MyServiceConnection serviceConnection;
    private PendingIntent pendingIntent;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(binder==null){
            binder = new MyBinder();
        }
        serviceConnection = new MyServiceConnection();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        RemoteService.this.bindService(new Intent(this,LocalService.class),serviceConnection,BIND_IMPORTANT);

        Notification notification = new Notification(R.mipmap.ic_launcher,"cookie安全服务中",System.currentTimeMillis());
        pendingIntent = PendingIntent.getService(this,0,intent,0);
        notification.setLatestEventInfo(this,"cookie安全服务","fanghuoqiag",pendingIntent);
        startForeground(startId,notification);
        return START_STICKY;
    }

    class MyBinder extends IMyAidlInterface.Stub{
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }

    class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //Toast.makeText(RemoteService.this,"remoteService服务被杀",Toast.LENGTH_LONG).show();

            RemoteService.this.startService(new Intent(RemoteService.this,LocalService.class));

            RemoteService.this.bindService(new Intent(RemoteService.this,LocalService.class),serviceConnection,Context.BIND_IMPORTANT);

        }
    }
}
