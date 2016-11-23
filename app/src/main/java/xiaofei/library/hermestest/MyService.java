package xiaofei.library.hermestest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import xiaofei.library.hermeseventbus.HermesEventBus;

/**
 * Created by wang on 16/11/18.
 */
public class MyService extends Service {
    public MyService() {}

    @Override
    public void onCreate() {
        super.onCreate();

        HermesEventBus.getDefault().connectApp(this, "xiaofei.library.hermestest");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Toast.makeText(this, "MyService started", Toast.LENGTH_SHORT).show();
        HermesEventBus.getDefault().post("Send a message during the creation of MyService.");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
