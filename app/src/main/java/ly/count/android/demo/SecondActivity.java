package ly.count.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ly.count.android.sdk.Countly;

/**
 * Created by libo on 16/8/3.
 */
public class SecondActivity extends Activity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_view);
        init();
    }

    private void init(){
        button = (Button) findViewById(R.id.btn_second_crash);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    int a = 10 / 0;
//                } catch (Exception e){
//                    Countly.sharedInstance().logException(e);
//                }

                //Countly.sharedInstance().recordEvent("nihao");
                Countly.sharedInstance().recordView("liboView");
            }
        });
    }



}
