package br.com.bplm.yesbrasil.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import br.com.bplm.yesbrasil.R;
import br.com.bplm.yesbrasil.activity.base.BaseActivity;
import br.com.bplm.yesbrasil.util.Preferences;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    @ViewById(R.id.imagem_splash)
    protected ImageView animation;

    @Override
    protected void afterViews() {
        //linha responsável por possibilitar que apareça a tabela de preços
        //getMyPrefs().isPrecoSelecionado( false );
        Log.i("TESTTTT", "passou");
        doAnimation();
    }

    @UiThread
    protected void doAnimation() {
        animation.setBackgroundResource(R.drawable.start_animation);
        final AnimationDrawable frameAnimation = (AnimationDrawable) animation.getBackground();
        animation.post(new Runnable() {
            @Override
            public void run() {
                frameAnimation.start();
            }
        });

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkDatabase();
            }

        }, 5000);
    }

    @Override
    public String activityNameForTracking() {
        return null;
    }

    private void checkDatabase() {
        if (prefs.isDatabaseConfigured().get())
            LoginActivity_.intent(this).start();
        else
            FileChooserActivity_.intent(this).start();
    }
}
