package br.com.bplm.yesbrasil.application;

/**
 * Created by lucas on 19/09/15.
 */

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

import br.com.bplm.yesbrasil.util.PreferenceUtils;
import br.com.bplm.yesbrasil.util.Preferences;

/**
 * Main Application class. Here is the common place for configuring
 * frameworks.
 */
public class YesApplication extends Application implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String TAG = YesApplication.class.getSimpleName();

    private static Context mAppContext;

    private long launchTime;

    private Preferences prefs;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Application", "Init");


        prefs = new Preferences(getApplicationContext());
        mAppContext = getApplicationContext();

        initPreferences();

    }


    // Clear params that are sent through push
    private void initPreferences() {

    }

    public void handleMainActivityResume(Activity activity) {
        // Only run the following for the launch of the app
        if (launchTime == 0)
            return;

        try {
            JSONObject jsonObject = new JSONObject();
            // Stringify screen size float value to avoidJSON value 4.3000001928
            // instead of 4.3
            jsonObject.put("display_size", "" + getScreenSizeInches(activity));
            try {
                String appVersion = PreferenceUtils.getAppVersion(activity);
                if (null != appVersion) {
                    jsonObject.put("app_version", appVersion);
                }
            } catch (Exception e) {
                Log.d(TAG,
                        "Unexpected exception when accessing package version: ",
                        e);
            }
            jsonObject.put("duration",
                    (System.currentTimeMillis() - launchTime));
        } catch (Exception e) {
            Log.d(TAG,
                    "Unexpected exception when adding information to JSON object: "
                            + e);
        }
    }

    public float getScreenSizeInches(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        return (float) Math.round(screenInches * 10) / 10;
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}