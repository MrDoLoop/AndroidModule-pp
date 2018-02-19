package com.netease.pineapple.common.cache;

import com.netease.pineapple.common.bean.SimpleNetEaseCode;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaonan on 18/1/24.
 */

/**
 * 处理本地存储的 订阅+喜欢
 */
public class LocalSavedInfoCacheManager {
    private ConcurrentHashMap<String, Boolean> mSubscribedDataCache;
    private ConcurrentHashMap<String, Boolean> mLikedDataCache;

    private LocalSavedInfoCacheManager(){
        mSubscribedDataCache = new ConcurrentHashMap<>();
        mLikedDataCache = new ConcurrentHashMap<>();
    }

    private static LocalSavedInfoCacheManager sInstance;

    public static LocalSavedInfoCacheManager getInstance(){
        if(sInstance == null){
            sInstance = new LocalSavedInfoCacheManager();
        }
        return sInstance;
    }

    // 订阅 start
    public void putSubscribedInfo(String tid, boolean value){
        if(mSubscribedDataCache != null){
            mSubscribedDataCache.put(tid,value);
            //DBUtils.getSingleton().insertSubscribeInfo(tid, value);
        }
    }

    public boolean isSubscribed(String tid){
        if(mSubscribedDataCache != null && mSubscribedDataCache.containsKey(tid)){
            return mSubscribedDataCache.get(tid);
        } else {
            boolean dbVal = true; //DBUtils.getSingleton().isSubscribed(tid);
            mSubscribedDataCache.put(tid, dbVal);
            return dbVal;
        }
    }
    // 订阅 end

    // 收藏相关 start
    public void putLikedInfo(String vid, boolean value){
        if(mLikedDataCache != null){
            mLikedDataCache.put(vid,value);
            //DBUtils.getSingleton().insertLikeInfo(vid, value);
        }
    }

    public boolean isLiked(String vid){
        if(mLikedDataCache != null && mLikedDataCache.containsKey(vid)){
            return mLikedDataCache.get(vid);
        } else {
            boolean dbVal = true;//DBUtils.getSingleton().isLiked(vid);
            mSubscribedDataCache.put(vid, dbVal);
            return dbVal;
        }
    }
    // 收藏相关 end

    /**
     *
     * @param clearDbAsy 异步清除数据库
     */
    public void clearSubscribedInfo(boolean clearDbAsy){
        if(mSubscribedDataCache != null){
            mSubscribedDataCache.clear();
        }
        if(clearDbAsy) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //DBUtils.getSingleton().clearAllSubscribedInfo();
                }
            }).start();
        }
    }

    /**
     *
     * @param clearDdAsy 异步清除数据库
     */
    public void clearLikedInfo(boolean clearDdAsy){
        if(mLikedDataCache != null){
            mLikedDataCache.clear();
        }
        if(clearDdAsy) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //DBUtils.getSingleton().clearAllLikedInfo();
                }
            }).start();
        }
    }


    public static void release(){
        if(sInstance != null){
            sInstance.clearSubscribedInfo(false);
            sInstance.clearLikedInfo(false);
            sInstance = null;
        }
    }

    public void init() {
        loadSubscribedInfoDataFromDb();
        loadLikedInfoDataFromDb();
    }

    /**
     * 该方法为异步方法
     */
    public void loadSubscribedInfoDataFromDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mSubscribedDataCache) {
                    mSubscribedDataCache.clear();
//                    List<SubscribeInfoDbBean> list =  DBUtils.getSingleton().getAllSubscribedInfo();
//                    if(list != null) {
//                        for(SubscribeInfoDbBean bean : list) {
//                            mSubscribedDataCache.put(bean.getTid(), bean.isSubscribed());
//                        }
//                    }
                }
            }
        }).start();

    }

    /**
     * 该方法为异步方法
     */
    public void loadLikedInfoDataFromDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mLikedDataCache) {
                    mLikedDataCache.clear();
//                    List<LikeInfoDbBean> list =  DBUtils.getSingleton().getAllLikedInfo();
//                    if(list != null) {
//                        for(LikeInfoDbBean bean : list) {
//                            mLikedDataCache.put(bean.getVid(), bean.isLike());
//                        }
//                    }
                }
            }
        }).start();

    }

//    public void initLikedDataFromList(final List<SimpleVideo> list, final boolean saveInDb) {
//        if(list == null) return;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (mLikedDataCache) {
//                    if(list != null) {
//                        mLikedDataCache.clear();
//                        DBUtils.getSingleton().clearAllLikedInfo();
//                        for(SimpleVideo bean : list) {
//                            mLikedDataCache.put(bean.vid, true);
//                            if(saveInDb) {
//                                DBUtils.getSingleton().insertSubscribeInfo(bean.vid, true);
//                            }
//                        }
//                    }
//                }
//            }
//        }).start();
//
//    }


    public void initSubscribedDataFromList(final List<SimpleNetEaseCode> list, final boolean saveInDb) {
        if(list == null) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mSubscribedDataCache) {
                    if(list != null) {
                        mSubscribedDataCache.clear();
//                        DBUtils.getSingleton().clearAllSubscribedInfo();
//                        for(SimpleNetEaseCode bean : list) {
//                            mSubscribedDataCache.put(bean.getTid(), true);
//                            if(saveInDb) {
//                                DBUtils.getSingleton().insertSubscribeInfo(bean.getTid(), true);
//                            }
//                        }
                    }
                }
            }
        }).start();

    }

}
