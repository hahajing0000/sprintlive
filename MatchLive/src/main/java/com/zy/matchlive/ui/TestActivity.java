package com.zy.matchlive.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zy.matchlive.R;
import com.zy.matchlive.entity.LiveDetailEntity;
import com.zy.matchlive.entity.LiveIndexEntity;
import com.zy.matchlive.model.service.MatchLiveService;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.Collections;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private Button btnMatchliveTest;

    MatchLiveService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        service = new MatchLiveService();

        initView();
        initData();
        initEvent();
    }

    //mid   100000:55400942
    private void initEvent() {
        btnMatchliveTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//                String currentDate = format.format(System.currentTimeMillis());
//                LiveData<MatchsEntity> result = service.getMatchsByData(currentDate);
//                result.observe(TestActivity.this, new Observer<MatchsEntity>() {
//                    @Override
//                    public void onChanged(MatchsEntity matchsEntity) {
//                        if (null!=matchsEntity){
//                            LogUtils.getInstance().d("123",matchsEntity.toString());
//                        }
//                    }
//                });

                String mid = "100000:55400942";
//                LiveData<MatchBaseEntity> result = service.getMatchBaseInfo(mid);
//                result.observe(TestActivity.this, new Observer<MatchBaseEntity>() {
//                    @Override
//                    public void onChanged(MatchBaseEntity matchBaseEntity) {
//                        Log.d("123", "onChanged: "+matchBaseEntity.toString());
//                    }
//                });


//                long l = System.currentTimeMillis();
//                Date date = new Date();
//                date.setTime(l);
//
//                LiveData<MatchCalendarEntity> result = service.getMatchCalendar(-1, 2021, date.getMonth() - 1);
//                result.observe(TestActivity.this, new Observer<MatchCalendarEntity>() {
//                    @Override
//                    public void onChanged(MatchCalendarEntity matchCalendarEntity) {
//                        Log.d("123", "onChanged: "+matchCalendarEntity.toString());
//                    }
//                });

//                LiveData<MatchStatEntity> result = service.getMatchStat(mid, "1");
//                result.observe(TestActivity.this, new Observer<MatchStatEntity>() {
//                    @Override
//                    public void onChanged(MatchStatEntity matchStatEntity) {
//                        Log.d("123", "onChanged: "+matchStatEntity.toString());
//                    }
//                });
//                LiveData<MatchVideoEntity> result = service.getMatchVideo(mid);
//                result.observe(TestActivity.this, new Observer<MatchVideoEntity>() {
//                    @Override
//                    public void onChanged(MatchVideoEntity matchVideoEntity) {
//                        Log.d("123", "onChanged: "+matchVideoEntity.toString());
//                    }
//                });
                Flowable.create(new FlowableOnSubscribe<LiveIndexEntity>() {
                    @Override
                    public void subscribe(FlowableEmitter<LiveIndexEntity> emitter) throws Exception {
                        LiveData<LiveIndexEntity> result = service.getMatchLiveIndex(mid);
                        result.observe(TestActivity.this, new Observer<LiveIndexEntity>() {
                            @Override
                            public void onChanged(LiveIndexEntity liveIndexEntity) {
                                emitter.onNext(liveIndexEntity);
                            }
                        });
                    }
                }, BackpressureStrategy.LATEST)
                        .flatMap(new Function<LiveIndexEntity, Publisher<List<String>>>() {
                            @Override
                            public Publisher<List<String>> apply(LiveIndexEntity liveIndexEntity) throws Exception {
                                if (liveIndexEntity!=null&&liveIndexEntity.data.index!=null){

                                    return Flowable.just( liveIndexEntity.data.index.subList(0,2));
                                }
                                return null;
                            }
                        }).subscribe(new Consumer<List<String>>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            LiveData<LiveDetailEntity> result = service.getMatchLiveDetail(mid, String.join(",", strings));
                            result.observe(TestActivity.this, new Observer<LiveDetailEntity>() {
                                @Override
                                public void onChanged(LiveDetailEntity liveDetailEntity) {
                                    Log.d("123", "onChanged: "+liveDetailEntity.toString());
                                }
                            });
                        }
                    }
                });

            }
        });
    }

    private void initData() {

    }

    private void initView() {
        btnMatchliveTest = (Button) findViewById(R.id.btn_matchlive_test);
    }
}