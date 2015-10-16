package br.com.gv8.yeschamix.activity.adapter;

import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import br.com.gv8.yeschamix.activity.R;
import br.com.gv8.yeschamix.imagem.model.persistence.ImagemProdutoVO;
import br.com.gv8.yeschamix.util.Utilidades;


public class GalleryImagemProdutoAdapter extends BaseAdapter {
	private Context context;
//	private int itemBackground;
	private List< ImagemProdutoVO > imagemProdutos;
	private int mGalleryItemBackground;

	public GalleryImagemProdutoAdapter( Context c , List< ImagemProdutoVO > produtoImagems , TypedArray typedArray ) {
		context = c;
		imagemProdutos = produtoImagems;
		// ---setting the style---
		TypedArray a = typedArray;
//		itemBackground = a.getResourceId( R.styleable.Gallery1_android_galleryItemBackground , 0 );
		mGalleryItemBackground = R.drawable.background2;
		a.recycle();
		
	}

	// ---returns the number of images---
	public int getCount() {
		return imagemProdutos.size();
	}

	// ---returns the ID of an item---
	public Object getItem( int position ) {
		return position;
	}

	public long getItemId( int position ) {
		return position;
	}

	// ---returns an ImageView view---
	public View getView( final int position , View convertView , ViewGroup parent ) {
		ImageView imageView = new ImageView( context );
		if ( !Utilidades.isNullOrBlank( imagemProdutos.get( position ) ) ) {
			try {
				Bitmap bitmap = Utilidades.getBitmap( context , imagemProdutos.get( position ).getNomeArquivo().trim() , true , 4 );
				imageView.setImageBitmap( bitmap );
				if("0".equals( imagemProdutos.get( position ).getExisteEmEstoque())){
					mGalleryItemBackground = R.drawable.background;
										
				}else{
					mGalleryItemBackground = R.drawable.background2;
				}
				bitmap = null;
			} catch( Exception e ) {
				System.gc();
				Runtime.getRuntime().gc();
				imageView.setImageResource( R.drawable.indisponivel );
				if("0".equals( imagemProdutos.get( position ).getExisteEmEstoque())){
					mGalleryItemBackground = R.drawable.background;
										
				}else{
					mGalleryItemBackground = R.drawable.background2;
				}
				
				
			}
		} else {
			imageView.setImageResource( R.drawable.indisponivel );
			if("0".equals( imagemProdutos.get( position ).getExisteEmEstoque())){
				mGalleryItemBackground = R.drawable.background;
									
			}else{
				mGalleryItemBackground = R.drawable.background2;
			}
		}
		imageView.setScaleType( ImageView.ScaleType.FIT_XY );
		imageView.setLayoutParams( new Gallery.LayoutParams( 150 , 120 ) );
		
		// The preferred Gallery item background
        imageView.setBackgroundResource(mGalleryItemBackground);
        imageView.setPadding(7, 7, 7, 7);
		return imageView;
	}
}
