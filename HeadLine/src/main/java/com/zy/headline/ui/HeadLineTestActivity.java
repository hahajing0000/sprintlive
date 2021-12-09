package com.zy.headline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baweigame.common.tools.LogUtils;
import com.nbasprint.net.RetrofitFactory;
import com.zy.headline.R;
import com.zy.headline.common.Constant;
import com.nbasprint.net.entity.BaseEntity;
import com.zy.headline.entity.NewsDetailEntity;
import com.zy.headline.entity.NewsIndexEntity;
import com.zy.headline.entity.NewsItemEntity;
import com.zy.headline.model.service.HeadLineService;

import org.reactivestreams.Publisher;

import java.util.List;

//{"code":0,"data":[{"type":"news","id":"20211130004899","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130003562","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130003119","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130002834","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130002778","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130002709","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130002678","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130002659","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130002573","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130002395","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130002578","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130002539","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130001989","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130003038","column":"banner","needUpdate":"0"},{"type":"news","id":"20211130001977","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129006501","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129003418","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129002892","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129002855","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129002558","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129000704","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129001063","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129001058","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129000869","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129000662","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129000624","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128003913","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128002495","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128002485","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128001647","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128001623","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128001597","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129001314","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128001697","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128001224","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128001407","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128001611","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128001604","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128001515","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128001358","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128001475","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128000661","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127005728","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127005887","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127004474","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127004430","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129003249","column":"banner","needUpdate":"0"},{"type":"news","id":"20211129003667","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127004242","column":"banner","needUpdate":"0"},{"type":"news","id":"20211128001513","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127003843","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127003515","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127003271","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127003237","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125005468","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125005347","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127003107","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127002949","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127002938","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127002761","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127002935","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127002632","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127002651","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127002635","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127001977","column":"banner","needUpdate":"0"},{"type":"news","id":"20211127000407","column":"banner","needUpdate":"0"},{"type":"news","id":"20211126007496","column":"banner","needUpdate":"0"},{"type":"news","id":"20211126002421","column":"banner","needUpdate":"0"},{"type":"news","id":"20211126002418","column":"banner","needUpdate":"0"},{"type":"news","id":"20211126002162","column":"banner","needUpdate":"0"},{"type":"news","id":"20211126001852","column":"banner","needUpdate":"0"},{"type":"news","id":"20211126001791","column":"banner","needUpdate":"0"},{"type":"news","id":"20211126001634","column":"banner","needUpdate":"0"},{"type":"news","id":"20211126001301","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125009002","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125005812","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125005681","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125005476","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125004364","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125004393","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125004232","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125004227","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125002857","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125003050","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125003419","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125003335","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125003333","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125003248","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125003245","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125003184","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125003142","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125003094","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125002787","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125003443","column":"banner","needUpdate":"0"},{"type":"news","id":"20211118002777","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125001951","column":"banner","needUpdate":"0"},{"type":"news","id":"20211125000016","column":"banner","needUpdate":"0"},{"type":"news","id":"20211124003279","column":"banner","needUpdate":"0"},{"type":"news","id":"20211124002947","column":"banner","needUpdate":"0"},{"type":"news","id":"20211124002659","column":"banner","needUpdate":"0"},{"type":"news","id":"20211124002572","column":"banner","needUpdate":"0"},{"type":"news","id":"20211124002123","column":"banner","needUpdate":"0"},{"type":"news","id":"20211124002463","column":"banner","needUpdate":"0"},{"type":"news","id":"20211124001944","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123006898","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123007259","column":"banner","needUpdate":"0"},{"type":"news","id":"20211124001352","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123008692","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123008602","column":"banner","needUpdate":"0"},{"type":"news","id":"20211124002136","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123006599","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123006230","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123006151","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123006121","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123005129","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123005725","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123005380","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123004955","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123004938","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123004898","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123004768","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123004609","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123004547","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123004546","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123003845","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123004821","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123003823","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123003768","column":"banner","needUpdate":"0"},{"type":"news","id":"20211123003719","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122004091","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122004017","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122003449","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122002949","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122001764","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122001667","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122001595","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122001039","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122000801","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122000570","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122000501","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122000487","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122000464","column":"banner","needUpdate":"0"},{"type":"news","id":"20211122001289","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121002004","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121001889","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121001838","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121001917","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121001903","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121001522","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121001514","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121001300","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121001355","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121001315","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121001220","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121000881","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121001443","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121000647","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121000613","column":"banner","needUpdate":"0"},{"type":"news","id":"20211121000580","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120006748","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120005231","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120005114","column":"banner","needUpdate":"0"},{"type":"news","id":"20211118006531","column":"banner","needUpdate":"0"},{"type":"news","id":"20211118006492","column":"banner","needUpdate":"0"},{"type":"news","id":"20211118006471","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120004712","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120004647","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120004096","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120004098","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120004451","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120003930","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120004156","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120004152","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120003975","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120003688","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120003365","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120003350","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120000079","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119009874","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119006852","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119006826","column":"banner","needUpdate":"0"},{"type":"news","id":"20211120004063","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119005925","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119005924","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119004142","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119005252","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119005217","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119004268","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119004260","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119004119","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119003010","column":"banner","needUpdate":"0"},{"type":"news","id":"20211118010379","column":"banner","needUpdate":"0"},{"type":"news","id":"20211118010256","column":"banner","needUpdate":"0"},{"type":"news","id":"20211118007957","column":"banner","needUpdate":"0"},{"type":"news","id":"20211118006979","column":"banner","needUpdate":"0"},{"type":"news","id":"20211118004673","column":"banner","needUpdate":"0"},{"type":"news","id":"20211119004936","column":"banner","needUpdate":"0"},{"type":"news","id":"20211118003981","column":"banner","needUpdate":"0"},{"type":"news","id":"20211118004074","column":"banner","needUpdate":"0"},{"type":"news","id":"20211118004629","column":"banner","needUpdate":"0"}],"version":"19b8683c1039c7f81f26911fac47dd64"}

public class HeadLineTestActivity extends AppCompatActivity {
    private Button btnHeadlineGetnewsindex;
    private Button btnHeadlineGetdetailinfo;



    /**
     * 主线程Handler
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/12/2 9:20
     */ 
    Handler handler=new Handler(Looper.getMainLooper());
    private HeadLineService headLineService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_line_test);

        initView();
        initData();
        initEvent();
    }

    private void initData() {
        headLineService = new HeadLineService();
    }

    private void initEvent() {
        /**
         * 获取资讯列表信息
         */
        btnHeadlineGetnewsindex.setOnClickListener(v -> {

            Flowable.create((FlowableOnSubscribe<NewsIndexEntity>) emitter -> {
                /**
                 * 获取资讯IDS
                 */
                LiveData<NewsIndexEntity> reslut = headLineService.getNewsIndex(Constant.NewsType.BANNER);

                handler.post(() -> reslut.observe(HeadLineTestActivity.this, entity -> {
                    if (null==entity){
                        Log.e("123", "onChanged: error");
                        emitter.onError(new IllegalStateException("livedata is null..."));
                    }
                    Log.d("123", "onChanged: "+entity.data.size());
                    emitter.onNext(entity);
                }));
            }, BackpressureStrategy.LATEST).flatMap((Function<NewsIndexEntity, Publisher<LiveData<NewsItemEntity>>>) listBaseEntity -> {
                StringBuilder sb=new StringBuilder();
                if (listBaseEntity!=null&&listBaseEntity.data!=null&&listBaseEntity.data.size()>0)
                {
                    int i=0;
                    for (NewsIndexEntity.NewsIndexInfo entity :
                            listBaseEntity.data) {
                        if (i>=2){
                            break;
                        }
                        i++;
                        sb.append(entity.getId()).append(",");
                    }
                }
                String ids=sb.substring(0,sb.toString().length()-1);
                /**
                 * 根据上面获取到的IDS获取对应资讯信息
                 */
                LiveData<NewsItemEntity> result = headLineService.getNewsItem(Constant.NewsType.BANNER, ids);

                return Flowable.just(result);
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(s -> {
                if (s==null){
                    return;
                }
                s.observe(HeadLineTestActivity.this, listBaseEntity -> {
                    if (listBaseEntity==null){
                        return;
                    }
                    LogUtils.getInstance().d("123",""+listBaseEntity.data.size());
                });
            });
        });


        String id="20211130004899";
        /**
         * 获取资讯详情信息
         */
        btnHeadlineGetdetailinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveData<NewsDetailEntity> result = headLineService.getNewsDetail(Constant.NewsType.BANNER, id);
                result.observe(HeadLineTestActivity.this, new Observer<NewsDetailEntity>() {
                    @Override
                    public void onChanged(NewsDetailEntity newsDetailEntity) {
                        LogUtils.getInstance().d("123",newsDetailEntity.toString());
                    }
                });
            }
        });
    }

    private void initView() {
        btnHeadlineGetnewsindex = (Button) findViewById(R.id.btn_headline_getnewsindex);
        btnHeadlineGetdetailinfo = (Button) findViewById(R.id.btn_headline_getdetailinfo);
    }
}