package br.com.bplm.yesbrasil.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import br.com.bplm.yesbrasil.R;
import br.com.bplm.yesbrasil.util.Preferences;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity implements Runnable {

  /*@Pref
  protected Preferences prefs;*/

    @ViewById(R.id.imagem_splash)
    protected ImageView animation;

    @AfterViews
    public void afterViews(){
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
        h.postDelayed(this, 5000);
    }

    @Override
    public void run() {
        Toast.makeText(SplashActivity.this, "Proxima Classe", Toast.LENGTH_LONG).show();
                /*if ( getMyPrefs().isBancoConfigurado() ) {
                    startActivity( new Intent( getApplication(), LoginActivity.class ) );

                } else {
                    startActivity( new Intent( getApplication(), FileChooserActivity.class ) );
                }*/
    }

    /*public Preferences getMyPrefs() {
        if ( prefs == null )
            //prefs = new Preferencess( this );
        return prefs;
    }*/
}
