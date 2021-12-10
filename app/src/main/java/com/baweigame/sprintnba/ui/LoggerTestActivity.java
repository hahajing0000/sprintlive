package com.baweigame.sprintnba.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baweigame.sprintnba.R;
import com.zg6.log.Logger;
import com.zg6.log.common.LoggerLevel;
import com.zg6.log.common.LoggerType;

public class LoggerTestActivity extends AppCompatActivity {
    private static String TAG=LoggerTestActivity.class.getSimpleName();
    private Button btnLoggerTest;
    Logger logger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_test);


        btnLoggerTest = (Button) findViewById(R.id.btn_logger_Test);

        logger=new Logger.Builder()
                .setTag("456")
                .setDebug(true)
                .setLoggerType(LoggerType.LOGCAT)
                .setLevel(LoggerLevel.Debug)
                .build();

        btnLoggerTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.d(TAG,"debug...........");
                logger.i(TAG,"info...........");
                logger.w(TAG,"warnning...........");
                logger.e(TAG,"error...........");
            }
        });
    }
}