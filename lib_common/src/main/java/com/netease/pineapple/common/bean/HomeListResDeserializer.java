package com.netease.pineapple.common.bean;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.netease.pineapple.common.utils.GsonUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeListResDeserializer implements JsonDeserializer<HomeListBean> {

    private Gson gson = new Gson();

    @Override
    public HomeListBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try{
            JsonObject obj = json.getAsJsonObject();
            JsonObject dataObj = obj;

            if(obj.has("data") && obj.get("data") != null) {
                dataObj = obj.get("data").getAsJsonObject();
            }

            HomeListBean result = new HomeListBean();

            if(obj.has("code") && obj.get("code") != null) {
                result.code = Integer.parseInt(obj.get("code").getAsString());
            }

            if(result.code != 200) {
                // 不是200的时候直接返回防止crash
                if(obj.has("message") && obj.get("message") != null) {
                    result.message = obj.get("message").getAsString();
                }
            }

            HomeListBean.HomeListDataBean dataBean = new HomeListBean.HomeListDataBean();
            result.setData(dataBean);

            NextpageParamBean nextpageParamBean = gson.fromJson(dataObj.get("nextPageParam"), NextpageParamBean.class);
            dataBean.setNextPageParam(nextpageParamBean);

            List<HomeListBean.HomeListDataListItemBean> dataList = new ArrayList<>();
            dataBean.setDataList(dataList);

            JsonArray asJsonArray = dataObj.get("dataList").getAsJsonArray();
            for (JsonElement jsonElement : asJsonArray) {
                JsonObject jsonOb = jsonElement.getAsJsonObject();
                int type = jsonOb.get("type").getAsInt();
                JsonElement child = jsonOb.get("content");
                if(child != null) {
                    HomeListBean.HomeListDataListItemBean tmp = null;
                    switch (type)  {
//                        case 1: {
//                            VideoItemBean object = GsonUtil.parse(child, VideoItemBean.class, gson);
//                            if(object != null) {
//                                tmp = new HomeListBean.HomeListDataListItemBean(type, object);
//                            }
//                        }
//                        break;
                        case HomeListBean.HOME_LIST_ITEM_BEAN_TYPE_VIDEO: {
                            VideoItemBean object = GsonUtil.parse(child, VideoItemBean.class, gson);
                            // 没有订阅就是推荐过来的项目
                            object.setShowSunBtn(!object.getVideoTopic().isSubscribed());
                            if(object != null) {
                                tmp = new HomeListBean.HomeListDataListItemBean(type, object);
                            }
                        }
                        break;
//                        case 3: {
//                            HomeListBean.RecommendItemBean object = GsonUtil.parse(child, HomeListBean.RecommendItemBean.class, gson);
//                            if(object != null) {
//                                tmp = new HomeListBean.HomeListDataListItemBean(type, object);
//                            }
//                        }
//                        break;
                        case HomeListBean.HOME_LIST_ITEM_BEAN_TYPE_RECOMMEND_V2: {
                            VideoDetailBean.RecommendItemBean object = GsonUtil.parse(child, VideoDetailBean.RecommendItemBean.class, gson);
                            if(object != null) {
                                tmp = new HomeListBean.HomeListDataListItemBean(type, object);
                            }
                        }
                        break;
                    }

                    if(tmp != null) {
                        dataList.add(tmp);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
