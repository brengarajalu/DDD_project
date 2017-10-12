package com.example.brengarajulu.distracteddriving;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.content.res.AssetManager;
import 	android.content.Intent;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import 	android.os.Handler;
import android.os.Message;
import 	android.view.View;

public class MainActivity extends AppCompatActivity {

    private static AssetManager mgr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            final Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                private long startTime = System.currentTimeMillis();
                String message = "";
                public void run() {
                    for (int i=0;i<10;i++) {
                        try {
                            message =  ProcessSensorData("HELLO",getFilesDir().getAbsolutePath()) + i;
                            Thread.sleep(3000);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable(){
                            public void run() {
                                ConstraintLayout layout = (ConstraintLayout)  findViewById(R.id.my_layout);
                                TextView tv = (TextView)findViewById(R.id.sample_text);
                                tv.setText(message);
                            }
                        });
                    }
                }
            };
            new Thread(runnable).start();

            Log.v("Activity Created","Created");
           /* moveTaskToBack(true);

            //startService(new Intent(this, DataProcessorService.class));

            Thread timer = new Thread() {
                public void run () {
                    for (int i=0;i<10;i++) {
                        String message = ProcessSensorData();
                        Message msg=new Message();
                        msg.obj = message;
                        uiCallback.sendMessage(msg);
                        try
                        {
                            Thread.sleep(3000);    // sleep for 3 seconds
                        }
                        catch (InterruptedException e) {
                            // Restore the interrupted status
                            Thread.currentThread().interrupt();
                        }

                    }
                }
            };
            timer.start(); */
            Log.v("Service Started","ServiceStarted");
        }
        catch (Exception ex)
        {
            Log.v("Service Exception",ex.getMessage());
        }

       /* mgr = getResources().getAssets();
        ProcessSensorData(mgr);
        setContentView(R.layout.activity_main); */

    // Example of a call to a native method
    //TextView tv = (TextView) findViewById(R.id.sample_text);
   // tv.setText(ProcessSensorData(mgr));
    }

    private Handler uiCallback = new Handler () {
        public void handleMessage (Message msg) {
            // do stuff with UI
            ConstraintLayout layout = (ConstraintLayout)  findViewById(R.id.my_layout);
            TextView tv = (TextView)layout.findViewById(R.id.sample_text);
            tv.setText("hellow");
           // moveTaskToBack(false);
            Log.v("Value returned","Returned Value"+msg.obj);
        }
    };

    @Override
    protected void onStop() {

        super.onStop();
        Log.v("Destroy Called","Destroy");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public static native void ProcessSensorData(AssetManager mgr);
    public native static String ProcessSensorData(String mgr,String path);

    // Used to load the 'native-lib' library on   startup.
    static {
        System.loadLibrary("native-lib");
    }
}
