package com.baweigame.sprintnba.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baweigame.sprintnba.R;
import com.baweigame.sprintnba.utils.LoggerUtils;

import androidx.appcompat.app.AppCompatActivity;

public class LoggerTestActivity extends AppCompatActivity {
    private static String TAG=LoggerTestActivity.class.getSimpleName();
    private Button btnLoggerTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_test);


        btnLoggerTest = (Button) findViewById(R.id.btn_logger_Test);



        btnLoggerTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoggerUtils.getInstance().getLogger().d(TAG,"debug...........");
                LoggerUtils.getInstance().getLogger().i(TAG,"info...........");
                LoggerUtils.getInstance().getLogger().w(TAG,"warnning...........");
                LoggerUtils.getInstance().getLogger().e(TAG,"error...........");

                Object object=null;
                Log.e(TAG, "onClick: "+object.toString());
            }
        });
    }
}