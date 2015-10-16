package br.com.gv8.yeschamix.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import br.com.gv8.yeschamix.util.Preferences;

public class PrincipalAtividade extends MenuActivity{

	private Preferences myPrefs = null;
	public static final String TITULO = "titulo";
	public static final String MENSAGEM = "mensagem";
	public static final String BOTAO = "botao";

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		System.gc();
		Runtime.getRuntime().gc();
		setContentView( R.layout.main );

	}

	/**
	 * 
	 * Método responsável por verificar se Existe conexão com a internet
	 * 
	 * @return
	 * 
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 04/06/2013 16:29:32
	 * @version 1.0
	 */
	public Boolean temInternet() {

		// Pego a conectividade do contexto o qual o metodo foi chamado
		ConnectivityManager cm = (ConnectivityManager) this.getSystemService( Context.CONNECTIVITY_SERVICE );
		// Crio o objeto netInfo que recebe as informacoes da NEtwork
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if ( ( netInfo != null ) && ( netInfo.isConnectedOrConnecting() ) && ( netInfo.isAvailable() ) ) { // Se o objeto for nulo ou nao tem
			// conectividade retorna false
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public void CreateMenu( Menu menu ) {
		menu.setQwertyMode( true );
		MenuItem mnu1 = menu.add( 0, 0, 0, "" );
		{
			mnu1.setAlphabeticShortcut( 'a' );
			mnu1.setIcon( R.drawable.sincronizar );
		}
		MenuItem mnu2 = menu.add( 0, 1, 1, "" );
		{
			mnu2.setAlphabeticShortcut( 'b' );
			mnu2.setIcon( R.drawable.configuracao );
		}
	}

	@Override
	public boolean onKeyDown( int keyCode, KeyEvent event ) {
		if ( ( keyCode == KeyEvent.KEYCODE_BACK ) ) {
			Log.d( this.getClass().getName(), "back button pressed" );
		}
		return super.onKeyDown( keyCode, event );
	}

	@Override
	public void onBackPressed() {
		System.gc();
		Runtime.getRuntime().gc();
//		startActivity( new Intent( getApplication(), FamiliasActivity.class ) );
	}

	public boolean MenuChoice( MenuItem item ) {
		switch ( item.getItemId() ) {
			case 0:
				System.gc();
				Runtime.getRuntime().gc();
				Intent intent = new Intent( this, FamiliasActivity.class );
				intent.putExtra( "SINCRONIZAR", Boolean.TRUE );
				this.startActivity( intent );
				return true;
			case 1:
				System.gc();
				Runtime.getRuntime().gc();
				startActivity( new Intent( getApplication(), FileChooserActivity.class ) );
				return true;
		}
		return false;
	}

	@Override
	public void finish() {
		
		System.gc();
		Runtime.getRuntime().gc();
		super.finish();
	}

	public Preferences getMyPrefs() {
		if ( myPrefs == null )
			myPrefs = new Preferences( this );
		return myPrefs;
	}

	@Override
	protected void onResume() {
		System.gc();
		Runtime.getRuntime().gc();
		super.onResume();
	}

	@Override
	protected void onPause() {
		System.gc();
		Runtime.getRuntime().gc();
		super.onPause();
	}

	@Override
	public void setContentView( int layoutResID ) {
		ViewGroup mainView = (ViewGroup) LayoutInflater.from( this ).inflate( layoutResID, null );

		setContentView( mainView );
	}

	@Override
	public void setContentView( View view ) {
		super.setContentView( view );

		m_contentView = (ViewGroup) view;
	}

	@Override
	public void setContentView( View view, LayoutParams params ) {
		super.setContentView( view, params );

		m_contentView = (ViewGroup) view;
	}


	private ViewGroup m_contentView = null;
}
