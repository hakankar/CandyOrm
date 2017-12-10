package hakan.karyagdi.candyorm.library;

import android.app.Application;
import android.content.Context;

/**
 * Created by hakan on 12/4/17.
 */

public class CandyApp extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        CandyApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return CandyApp.context;
    }
}
