package br.com.bplm.yesbrasil.activity.base;

/**
 * Created by lucas on 19/09/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.List;

import br.com.bplm.yesbrasil.application.YesApplication;
import br.com.bplm.yesbrasil.util.Preferences;
import br.com.bplm.yesbrasil.util.Preferences_;

/**
 * This activity is the base activity for all activities using tracking infos
 */
@EActivity
public abstract class BaseTrackingActivity extends AppCompatActivity {

    /*@Bean
    @NonConfigurationInstance
    protected Tracking tracking;*/

    @Pref
    protected Preferences_ prefs;

    protected YesApplication application;

    /**
     * This adds the possibility of changing the API implementation. In case
     * there is need to pass a different implementation to further activities
     * use a wrapper. This is mostly used for mocking the expected responses
     * during tests.
     */
    @Extra
    protected String /*APIWrapper*/ wrapper;

   /* @Bean
    protected Rest rest;*/


    /**
     * Method for returning a proper name for tracking instances
     *
     * @return The name definided for this activity to be in the tracker
     */
    public abstract String activityNameForTracking();

    public String getParametersForTracking() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //application = (OmeApplication) getApplication();
        /*if (getSupportActionBar() != null)
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        uiHelper = new UiLifecycleHelper(this, null);
        uiHelper.onCreate(savedInstanceState);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent newIntent) {
        this.setIntent(newIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }




    @Override
    protected void onStart() {
        super.onStart();
        if (!isTestActivity()) {
            //tracking.startSession();
        }
    }


    // GETTERs & SETTERs
    /*public Rest getRest() {
        return rest;
    }

    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }

    public Preferences_ getPrefs() {
        return prefs;
    }*/

    public boolean isTestActivity() {
        Log.d("Teste", "" + (wrapper != null));
        return wrapper != null;
    }

   /* public void setPrefs(Preferences_ prefs) {
        this.prefs = prefs;
    }

    public OmeApplication getOmeApplication() {
        return application;
    }

    public void setApplication(OmeApplication application) {
        this.application = application;
    }*/


    public String replaceArgsPath(String path, List<String> params) {

        for (String param : params) {
            path = path.replaceFirst("<([^<]*)>", param);
        }

        return path;
    }


}