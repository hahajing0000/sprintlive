package com.nbasprint.net.retrofit.convertfactory;

import com.google.gson.Gson;
import com.nbasprint.net.entity.BaseEntity;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @ProjectName: MVVMZG51905
 * @Package: com.zy.net.retrofit.convertfactory
 * @ClassName: CustomResponseBodyConverter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/11/16 11:26
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/11/16 11:26
 * @UpdateRemark:
 * @Version: 1.0
 */
public class CustomResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;
    private Gson gson;

    public CustomResponseBodyConverter(Type _type){
        type=_type;
        gson = new Gson();
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String respContent=value.string();


        BaseEntity baseRespEntity = gson.fromJson(respContent, BaseEntity.class);
        if (baseRespEntity.getCode()!=0){
            return (T) baseRespEntity;
        }else{
            return gson.fromJson(respContent,type);
        }
    }
}
