package com.netease.pineapple.common.base.api;

import com.netease.pineapple.common.utils.AESUtils;
import com.netease.pineapple.common.utils.AppInfoUitls;
import com.netease.pineapple.common.utils.DeviceUtils;
import com.netease.pineapple.common.utils.NetworkUtils;

import java.util.HashMap;


/**
 * Created by zhaonan on 2018/2/16.
 */

public class NetApiParamsBuilder {

    /**
     * 列表请求的默认长度
     */
    public static int DEFAULT_REQ_REC_DATA_SIZE = 10;

    public static int DEFAULT_REQ_DATA_SIZE = 20;

    /**
     * 首页推荐列表参数
     */
    public static HashMap<String, String> getHomeRecommendListParams(int fn, int offset, String ename){
        RequestParams params = new RequestParams();
        params.put("subtab", ename);
        params.put("size", DEFAULT_REQ_REC_DATA_SIZE);
        params.put("offset", offset);
        params.put("fn", fn);
        params.put("passport", "");//暂时空缺
        String devId = DeviceUtils.getDeviceId();
        String ts = String.valueOf(System.currentTimeMillis());
        params.put("devId", AESUtils.encryptWithTuiJianKey(devId));
        params.put("version", AppInfoUitls.getAppVerName());
        params.put("net", NetworkUtils.getNetworkTypeString());
        params.put("ts", ts);
        params.put("sign", AESUtils.signWithTuijianKey(devId, ts));
        params.put("encryption", "1");
        params.put("canal", AppInfoUitls.getChannel());//暂时空缺
        params.put("mac", AESUtils.encryptWithTuiJianKey(DeviceUtils.getMacAddress()));
        params.put("lat", "");
        params.put("lon", "");
        params.put("from", "Boluo");
        return params.urlParams;
    }

    /**
     * 观看历史参数
     */
    public static HashMap<String, String> getViewHistroyParams(int start, int size, String lastId){
        RequestParams params = new RequestParams();
        params.put("start", start);
        params.put("size", size);
        params.put("lastId", lastId);
        return params.urlParams;
    }

}
