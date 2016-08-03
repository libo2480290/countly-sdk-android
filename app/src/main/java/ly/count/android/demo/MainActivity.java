package ly.count.android.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ly.count.android.sdk.Countly;
import ly.count.android.sdk.DeviceId;


public class MainActivity extends Activity {
    private Activity activity;
    private int count;
    private Button btnEnterSecond;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindEvent();

        Countly.sharedInstance().setLoggingEnabled(true);

        Countly.onCreate(this);

        /** You should use cloud.count.ly instead of YOUR_SERVER for the line below if you are using Countly Cloud service */
        Countly.sharedInstance()
                .init(this, "http://sdk.bdpdev.bdp.cn", "431badd022d248ae9840a66cb2258e4f96caa4b2", null, DeviceId.Type.OPEN_UDID);
//                .setLocation(LATITUDE, LONGITUDE);
//                .setLoggingEnabled(true);
//        setUserData(); // If UserData plugin is enabled on your server
       // Countly.sharedInstance().enableCrashReporting();
        Countly.sharedInstance().setViewTracking(true);

        Countly.sharedInstance().addCrashLog("crashlog--libo");
        HashMap<String, String> segmentation = new HashMap<String, String>();
        segmentation.put("location", "Activity");
        segmentation.put("app_version", "1.0");
        segmentation.put("name", "libo");
        Countly.sharedInstance().setCustomCrashSegments(segmentation);
//        Countly.sharedInstance().recordEvent("test", 1);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Countly.sharedInstance().recordEvent("test2", 1, 2);
//            }
//        }, 5000);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Countly.sharedInstance().recordEvent("test3");
//            }
//        }, 10000);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Countly.sharedInstance().setLocation(44.5888300, 33.5224000);
//            }
//        }, 11000);
//
//        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                Countly.sharedInstance().recordEvent("timer_task!"+ (++count), count);
//                Log.e(Countly.TAG, "第" + count + "次");
//            }
//        }, 0, 20 * 1000, TimeUnit.MILLISECONDS);

        Button button1 = (Button) findViewById(R.id.runtime);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Countly.sharedInstance().addCrashLog("Button 1 pressed");
                Countly.sharedInstance().crashTest(4);
            }
        });

        Button button2 = (Button) findViewById(R.id.nullpointer);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Countly.sharedInstance().addCrashLog("Button 2 pressed");
                Countly.sharedInstance().crashTest(5);
            }
        });

        Button button3 = (Button) findViewById(R.id.division0);
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Countly.sharedInstance().addCrashLog("Button 3 pressed");
                Countly.sharedInstance().crashTest(2);
            }
        });


        Button button5 = (Button) findViewById(R.id.stackoverflow);
        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Countly.sharedInstance().addCrashLog("Button 4 pressed");
                Countly.sharedInstance().crashTest(1);
            }
        });

        Button button6 = (Button) findViewById(R.id.handled);
        button6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Countly.sharedInstance().addCrashLog("Button 5 pressed");
                try {
                    Countly.sharedInstance().crashTest(5);
                }
                catch(Exception e){
                    Countly.sharedInstance().logException(e);
                }
            }
        });

    }

    public void setUserData(){
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name", "Firstname Lastname");
        data.put("username", "nickname");
        data.put("email", "test@test.com");
        data.put("organization", "Tester");
        data.put("phone", "+123456789");
        data.put("gender", "M");
        //provide url to picture
        //data.put("picture", "http://example.com/pictures/profile_pic.png");
        //or locally from device
        //data.put("picturePath", "/mnt/sdcard/portrait.jpg");
        data.put("byear", "1987");

        //providing any custom key values to store with user
        HashMap<String, String> custom = new HashMap<String, String>();
        custom.put("country", "Turkey");
        custom.put("city", "Istanbul");
        custom.put("address", "My house 11");

        //set multiple custom properties
        Countly.userData.setUserData(data, custom);

        //set custom properties by one
        Countly.userData.setProperty("test", "test");

        //increment used value by 1
        Countly.userData.incrementBy("used", 1);

        //insert value to array of unique values
        Countly.userData.pushUniqueValue("type", "morning");

        //insert multiple values to same property
        Countly.userData.pushUniqueValue("skill", "fire");
        Countly.userData.pushUniqueValue("skill", "earth");

        Countly.userData.save();
    }

    public void enableCrashTracking(){
        //add some custom segments, like dependency library versions
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("Facebook", "3.5");
        data.put("Admob", "6.5");
        Countly.sharedInstance().setCustomCrashSegments(data);
        Countly.sharedInstance().enableCrashReporting();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Countly.sharedInstance().onStart(this);
    }

    @Override
    public void onStop()
    {
        Countly.sharedInstance().onStop();
        super.onStop();
    }

    private void bindEvent(){
        btnEnterSecond = (Button) findViewById(R.id.btn_enter_second);
        btnEnterSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
