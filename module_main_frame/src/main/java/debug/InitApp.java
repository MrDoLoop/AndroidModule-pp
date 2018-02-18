package debug;

import android.support.v7.app.AppCompatDelegate;

import com.meiji.toutiao.api.INewsApi;
import com.netease.pineapple.common.base.BaseApplication;
import com.meiji.toutiao.util.SettingUtil;
import com.netease.pineapple.common.http.RetrofitFactory;

import java.util.Calendar;

/**
 * Created by Meiji on 2016/12/12.
 */

public class InitApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitFactory.init(INewsApi.HOST);
    }
}
