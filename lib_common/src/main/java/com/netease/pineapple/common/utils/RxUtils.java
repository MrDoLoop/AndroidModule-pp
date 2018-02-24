package com.netease.pineapple.common.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhaonan on 2018/2/25.
 */

public class RxUtils {
    private final static ObservableTransformer schedulersTransformer = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable observable) {
             return observable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static  <T> ObservableTransformer<T, T> applySchedulers() {
        return (ObservableTransformer<T, T>) schedulersTransformer;
    }
}
