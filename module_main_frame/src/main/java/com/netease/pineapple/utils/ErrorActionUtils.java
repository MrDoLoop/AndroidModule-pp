package com.netease.pineapple.utils;

import android.support.annotation.NonNull;

import io.reactivex.functions.Consumer;

public class ErrorActionUtils {

    @NonNull
    public static Consumer<Throwable> error() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        };
    }

    public static void print(@NonNull Throwable throwable) {
        throwable.printStackTrace();
    }
}
