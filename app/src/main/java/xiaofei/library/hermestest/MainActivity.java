package xiaofei.library.hermestest;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import xiaofei.library.hermes.Hermes;
import xiaofei.library.hermes.HermesService;
import xiaofei.library.hermeseventbus.HermesEventBus;
import xiaofei.library.hermestest.otto.TestEvent;
import xiaofei.library.hermestest.test.C;
import xiaofei.library.hermestest.test.NewInstance;
import xiaofei.library.hermestest.test.TestingActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HermesEventBus.getDefault().register(this);
        Hermes.init(this);
        Hermes.register(NewInstance.class);
        Hermes.register(C.class);
        Hermes.register(UserManager.class);
        Hermes.register(LoadingTask.class);
        Hermes.register(FileUtils.class);

        findViewById(R.id.testing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestingActivity.class);
                startActivity(intent);
                HermesEventBus.getDefault().post(new TestEvent("hehehda"));


            }
        });

        findViewById(R.id.demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DemoActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.another_process).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(getApplicationContext(),
                // AnotherProcessActivity.class);
                // startActivity(intent);
                startService(new Intent(getApplicationContext(), MyService.class));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showToast(TestEvent event) {
        Toast.makeText(MainActivity.this, event.text, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showText(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}
