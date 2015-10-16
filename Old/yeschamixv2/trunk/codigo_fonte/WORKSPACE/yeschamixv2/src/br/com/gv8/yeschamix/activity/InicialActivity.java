package br.com.gv8.yeschamix.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;
import br.com.gv8.yeschamix.facade.YesChamixFacade;
import br.com.gv8.yeschamix.imagem.model.persistence.ImagemFolderVO;
import br.com.gv8.yeschamix.relacaousuariopreco.model.persistence.RelacaoUsuarioPrecoVO;
import br.com.gv8.yeschamix.util.TouchImageView;
import br.com.gv8.yeschamix.util.Utilidades;

public class InicialActivity extends MenuActivity implements ViewFactory {
	// A ProgressDialog object
	private List< ImagemFolderVO > imagemFolderVOs = null;
	ImageSwitcher iSwitcher = null;
	private int contador;
	ArrayList< RelacaoUsuarioPrecoVO > listaPreco = new ArrayList< RelacaoUsuarioPrecoVO >();
	YesChamixFacade facade= null;
	Bitmap bitmap;

	/** Called when the activity is first created. */
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.folders );

		facade = new YesChamixFacade( getApplicationContext() );
		imagemFolderVOs = getListaImagemFolders();
		contador = 0;
		
		if ( !getMyPrefs().isPrecoSelecionado() ) {
			escolherPreco();
		}
		
		
	}
	
	@Override
	protected void onResume() {
		System.gc();
		Runtime.getRuntime().gc();
		if(bitmap != null){
			bitmap.recycle();
		}
		if ( !Utilidades.isNullOrEmpty( imagemFolderVOs ) ) {
			iSwitcher = (ImageSwitcher) findViewById( R.id.ImageSwitcher01 );
			iSwitcher.setFactory( this );
			iSwitcher.setInAnimation( AnimationUtils.loadAnimation( this , android.R.anim.fade_in ) );
			iSwitcher.setOutAnimation( AnimationUtils.loadAnimation( this , android.R.anim.fade_out ) );
			Drawable drawable;
			try {
				Bitmap bitmap = null;
				bitmap = Utilidades.getBitmap( getApplicationContext() , imagemFolderVOs.get( contador ).getArquivo() , true , 1 );
				drawable = new BitmapDrawable( bitmap );
				iSwitcher.setImageDrawable( drawable );
				bitmap = null;
			} catch( Exception e ) {
				System.gc();
				Runtime.getRuntime().gc();
				iSwitcher.setImageResource( R.drawable.indisponivel );
			}

			ImageButton btnAvancar = (ImageButton) findViewById( R.id.btnAvancar );
			btnAvancar.setOnClickListener( new OnClickListener() {
				public void onClick( View v ) {
					++contador;
					if ( contador < imagemFolderVOs.size() ) {
						bitmap = null;
						try {
							
							Drawable drawable = null;
							bitmap = Utilidades.getBitmap( getApplicationContext() , imagemFolderVOs.get( contador ).getArquivo() , true , 1 );
							drawable = new BitmapDrawable( bitmap );
							iSwitcher.setImageDrawable( drawable );
							drawable = null;
							System.gc();
							Runtime.getRuntime().gc();
						} catch( Exception e ) {
							System.gc();
							Runtime.getRuntime().gc();
							iSwitcher.setImageResource( R.drawable.indisponivel );
						}
						
					} else {
						
						startActivity( new Intent( getApplication() , FamiliasActivity.class ) );
						
					}
				}
			} );
			
			ImageButton btnVoltar = (ImageButton) findViewById( R.id.btnVoltar );
			btnVoltar.setOnClickListener( new OnClickListener() {
				public void onClick( View v ) {
					--contador;
					if ( contador < imagemFolderVOs.size() && contador>=0 ) {
						bitmap = null;
						try {
							
							Drawable drawable = null;
							bitmap = Utilidades.getBitmap( getApplicationContext() , imagemFolderVOs.get( contador ).getArquivo() , true , 1 );
							drawable = new BitmapDrawable( bitmap );
							iSwitcher.setImageDrawable( drawable );
							drawable = null;
							System.gc();
							Runtime.getRuntime().gc();
						} catch( Exception e ) {
							System.gc();
							Runtime.getRuntime().gc();
							iSwitcher.setImageResource( R.drawable.indisponivel );
						}
						
					} else {
						
						startActivity( new Intent( getApplication() , FamiliasActivity.class ) );
							
					}
				}
			} );
		} else {
			
			startActivity( new Intent( getApplication() , FamiliasActivity.class ) );
			//System.exit( 0 );
	
			
		}
	    super.onResume();
	}


	private List< ImagemFolderVO > getListaImagemFolders() {
		List< ImagemFolderVO > lista = new ArrayList< ImagemFolderVO >();
		YesChamixFacade facade = new YesChamixFacade( this.getApplicationContext() );
		lista = facade.consultarTudoImagemFolder();

		List< ImagemFolderVO > listaL = new ArrayList< ImagemFolderVO >();
		List< ImagemFolderVO > listaP = new ArrayList< ImagemFolderVO >();
		List< ImagemFolderVO > listaI = new ArrayList< ImagemFolderVO >();

		for( ImagemFolderVO vo : lista ) {
			if ( vo.getArquivo().toUpperCase().startsWith( "L" ) ) {
				listaL.add( vo );
			}
			if ( vo.getArquivo().toUpperCase().startsWith( "P" ) ) {
				listaP.add( vo );
			}
			if ( vo.getArquivo().toUpperCase().startsWith( "I" ) ) {
				listaI.add( vo );
			}
		}
		
		Collections.sort( listaL , new Comparator< ImagemFolderVO >() {

			/**
			 * Método reescrito da Interface Comparator com a finalidade
			 * de conter as regras de ordenação do objeto.
			 */
			@Override
			public int compare( ImagemFolderVO imagem , ImagemFolderVO imagem1 ) {
				return imagem.getArquivo().compareTo( imagem1.getArquivo() );
			}

		} );
		
		Collections.sort( listaP , new Comparator< ImagemFolderVO >() {

			/**
			 * Método reescrito da Interface Comparator com a finalidade
			 * de conter as regras de ordenação do objeto.
			 */
			@Override
			public int compare( ImagemFolderVO imagem , ImagemFolderVO imagem1 ) {
				return imagem.getArquivo().compareTo( imagem1.getArquivo() );
			}

		} );
		
		Collections.sort( listaI , new Comparator< ImagemFolderVO >() {

			/**
			 * Método reescrito da Interface Comparator com a finalidade
			 * de conter as regras de ordenação do objeto.
			 */
			@Override
			public int compare( ImagemFolderVO imagem , ImagemFolderVO imagem1 ) {
				return imagem.getArquivo().compareTo( imagem1.getArquivo() );
			}

		} );
		
		lista = new ArrayList< ImagemFolderVO >();
		
		lista.addAll( listaL );
		lista.addAll( listaP );
		lista.addAll( listaI );
		
		if(getMyPrefs().getValue("tipoFolder", "vazio").equals( "Lançamentos" )){
			return listaL;
		}else if(getMyPrefs().getValue("tipoFolder", "vazio").equals( "Promoções" )){
			return listaP;
		}else if(getMyPrefs().getValue("tipoFolder", "vazio").equals( "Institucionais" )){
			return listaI;
		}else{
			return lista;
		}
		
		
	}

	@Override
	public View makeView() {

		TouchImageView iView = new TouchImageView( this , 1f , 1f );
		iView.setScaleType(ScaleType.FIT_CENTER);
		iView.setLayoutParams( new ImageSwitcher.LayoutParams( LayoutParams.FILL_PARENT , LayoutParams.FILL_PARENT ) );
		iView.setBackgroundColor( 0xFF000000 );
		return iView;
	}
	
	@Override
	public void onBackPressed() {
		System.gc();
		Runtime.getRuntime().gc();
		startActivity( new Intent( getApplication() , FamiliasActivity.class ) );
		//System.exit( 0 );
		
	}
	
	@Override
	protected void onPause() {
		System.gc();
		Runtime.getRuntime().gc();
	    super.onPause();
	    this.onDestroy();
	}

}
