package br.com.gv8.yeschamix.activity;

import br.com.gv8.yeschamix.util.Preferences;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends Activity implements Runnable{

	Preferences myPrefs;

	@Override
	public void onCreate( Bundle icicle ) {
		super.onCreate( icicle );
		setContentView( R.layout.splash );

		//linha responsável por possibilitar que apareça a tabela de preços
		getMyPrefs().isPrecoSelecionado( false );

		final ImageView animationImageView = (ImageView) findViewById( R.id.imagem_splash );
		animationImageView.setBackgroundResource( R.anim.animacao_inicial );

		final AnimationDrawable frameAnimation = (AnimationDrawable) animationImageView.getBackground();
		animationImageView.post( new Runnable(){
			@Override
			public void run() {
				frameAnimation.start();
			}
		} );

		Handler h = new Handler();
		h.postDelayed( this, 5000 );

	}

	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}

	public void run() {
		if ( getMyPrefs().isBancoConfigurado() ) {
			startActivity( new Intent( getApplication(), LoginActivity.class ) );

		} else {
			startActivity( new Intent( getApplication(), FileChooserActivity.class ) );
		}
	}

	public Preferences getMyPrefs() {
		if ( myPrefs == null )
			myPrefs = new Preferences( this );
		return myPrefs;
	}
}
