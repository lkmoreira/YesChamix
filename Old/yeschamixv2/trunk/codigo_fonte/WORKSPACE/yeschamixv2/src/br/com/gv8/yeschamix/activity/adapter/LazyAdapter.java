package br.com.gv8.yeschamix.activity.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.gv8.yeschamix.activity.Principal;
import br.com.gv8.yeschamix.activity.ProdutoActivity;
import br.com.gv8.yeschamix.activity.R;
import br.com.gv8.yeschamix.activity.adapter.ImageDownloader.Mode;
import br.com.gv8.yeschamix.imagem.model.persistence.ImagemProdutoVO;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;
import br.com.gv8.yeschamix.util.Utilidades;

public class LazyAdapter extends BaseAdapter {
	private final ImageDownloader imageDownloader = new ImageDownloader();
//	private static final int IMAGEVIEWINDEX = 1;
	private Activity activity;
	private List< ProdutoVO > produtos;
	private static LayoutInflater inflater = null;
//	private Handler mHandler;
//	private ImageLoader mImageLoader = null;
	private Context mContext;

	
    private Mode mode = Mode.CORRECT;
	public LazyAdapter( Context context , Activity activity , List< ProdutoVO > produtos ) {
		this.activity = activity;
		this.produtos = produtos;
		inflater = (LayoutInflater) activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
//		mImageLoader = new ImageLoader( this );
//		mImageLoader.start();
//		mHandler = new Handler();
		mContext = context;
		
		
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();

		// stop the thread we started
//		mImageLoader.stopThread();
	}

	public int getCount() {
		return produtos.size();
	}

	public ProdutoVO getItem( int position ) {
		return produtos.get( position );
	}

	public long getItemId( int position ) {
		return position;
	}

	public View getView( final int position , View convertView , ViewGroup parent ) {
		ViewHolder view;
		getImageDownloader().setMode(mode);
		if ( convertView == null ) {
			view = new ViewHolder();
			convertView = inflater.inflate( R.layout.item , null , false );

			view.txtViewTitle = (TextView) convertView.findViewById( R.id.text );
			view.txtViewDesc = (TextView) convertView.findViewById( R.id.textDescricao );
			view.imgViewFlag = (ImageView) convertView.findViewById( R.id.image );
			convertView.setTag( view );
		} else {
			view = (ViewHolder) convertView.getTag();
		}
		
		ProdutoVO produto = produtos.get( position );
		view.imgViewFlag.setTag( produto );
		view.imgViewFlag.setClickable( true );
		// finish the activity (dismiss the image dialog) if the user clicks
		// anywhere on the image
		view.imgViewFlag.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick( View v ) {
				abrirDialogo( position );
				Principal.position = position;
			}
		} );

		StringBuilder bd = new StringBuilder();
		bd.append( produto.getId() );
		view.txtViewTitle.setText( bd.toString() );
		bd = new StringBuilder();
		bd.append( produto.getDescricao().trim() );
		view.txtViewDesc.setText( bd.toString() );
		String arquivo = "";

		for( ImagemProdutoVO imagem : produto.getImagensProduto() ) {
			if ( !Utilidades.isNullOrBlank( imagem.getNomeArquivo()) ) {
				if ( imagem.getNomeArquivo().contains( "_A" ) ) {
					arquivo = imagem.getNomeArquivo();
				}
			}
		}
		if ( Utilidades.isNullOrBlank( arquivo ) ) {
			
			for(ImagemProdutoVO nomeArquivo : produto.getImagensProduto()){
				if ( !Utilidades.isNullOrBlank( nomeArquivo.getNomeArquivo()) ) {
					arquivo = nomeArquivo.getNomeArquivo();
					break;
				}
			}
			
//			if ( !Utilidades.isNullOrEmpty( produto.getImagensProduto() ) ) {
//				if ( !Utilidades.isNullOrBlank( produto.getImagensProduto().get( 0 ) ) ) {
//					if ( !Utilidades.isNullOrBlank( produto.getImagensProduto().get( 0 ).getNomeArquivo() ) ) {
//						arquivo = produto.getImagensProduto().get( 0 ).getNomeArquivo();
//					}
//				}
//			}
		}
		view.imgViewFlag.setImageResource( R.drawable.loading );
		try {
			if ( !Utilidades.isNullOrBlank( arquivo ) ) {
				/*Bitmap bitmap = Utilidades.getBitmap( mContext , arquivo , true , 0 );
				view.imgViewFlag.setImageBitmap( bitmap );
				bitmap = null;*/
				if (view.imgViewFlag != null) {
					//new ImageDownloaderTask(view.imgViewFlag).execute(arquivo);
					imageDownloader.download(arquivo, view.imgViewFlag);
				}
			} else {
				view.imgViewFlag.setImageResource( R.drawable.indisponivel );
			}
		} catch( Exception e ) {
			System.gc();
			Runtime.getRuntime().gc();
			view.imgViewFlag.setImageResource( R.drawable.indisponivel );
		}
		return convertView;
	}

	public void abrirDialogo( int position ) {
		ProdutoActivity.produto = getItem( position );
		Intent intent = new Intent( activity.getApplicationContext() , ProdutoActivity.class );
		ProdutoActivity.produtos = produtos;
		activity.startActivity( intent );
	}

//	public void handleImageLoaded( final ViewSwitcher aViewSwitcher , final ImageView aImageView , final Bitmap aBitmap ) {
//
//		// The enqueue the following in the UI thread
//		mHandler.post( new Runnable() {
//			public void run() {
//
//				// set the bitmap in the ImageView
//				aImageView.setImageBitmap( aBitmap );
//
//				// explicitly tell the view switcher to show the second view
//				aViewSwitcher.setDisplayedChild( IMAGEVIEWINDEX );
//			}
//		} );
//
//	}
	
	 public ImageDownloader getImageDownloader() {
	        return imageDownloader;
	    }
}

class ViewHolder {
	public ImageView imgViewFlag;
	public TextView txtViewTitle;
	public TextView txtViewDesc;
}