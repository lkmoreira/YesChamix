package br.com.bplm.yesbrasil.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;


/**
 * Created by lucas on 19/09/15.
 */
public final class PreferenceUtils {

    private PreferenceUtils() {}

    public static String getAppVersion(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("AppVersion", "Exception: " + e.getMessage());
        }

        return "";
    }
}
