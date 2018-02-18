package debug;

import com.netease.pineapple.common.base.BaseApplication;
import com.netease.pineapple.common.http.RetrofitFactory;
import com.netease.pineapple.net.api.INetApis;



public class InitApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitFactory.init(INetApis.HOST);
    }
}
