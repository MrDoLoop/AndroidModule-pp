package debug;

import com.netease.pineapple.common.base.BaseApplication;
import com.guiying.module.common.http.HttpClient;
import com.netease.pineapple.common.http.OnResultListener;
import com.orhanobut.logger.Logger;

public class ShareApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    /**
     * 在这里模拟登陆，然后拿到sessionId或者Token
     * 这样就能够在组件请求接口了
     */
    private void login() {
        HttpClient client = new HttpClient.Builder()
                .baseUrl("http://gank.io/api/data/")
                .url("福利/10/1")
                .build();
        client.get(new OnResultListener<String>() {

            @Override
            public void onSuccess(String result) {
                Logger.e(result);
            }

            @Override
            public void onError(int code, String message) {
                Logger.e(message);
            }

            @Override
            public void onFailure(String message) {
                Logger.e(message);
            }
        });
    }
}