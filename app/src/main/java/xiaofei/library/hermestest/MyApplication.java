package xiaofei.library.hermestest;

import android.app.Application;

import xiaofei.library.hermeseventbus.HermesEventBus;

/**
 * Created by wang on 16/11/21.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HermesEventBus.getDefault().init(this);
    }
}
