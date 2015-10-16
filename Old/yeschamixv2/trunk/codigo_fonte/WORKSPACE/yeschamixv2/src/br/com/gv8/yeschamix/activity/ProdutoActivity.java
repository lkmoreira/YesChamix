package br.com.gv8.yeschamix.activity;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.gv8.yeschamix.activity.adapter.DetalheImageDialog;
import br.com.gv8.yeschamix.activity.adapter.GalleryImagemProdutoAdapter;
import br.com.gv8.yeschamix.facade.YesChamixFacade;
import br.com.gv8.yeschamix.imagem.model.persistence.ImagemProdutoVO;
import br.com.gv8.yeschamix.preco.model.persistence.PrecoVO;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;
import br.com.gv8.yeschamix.util.TouchImageView;
import br.com.gv8.yeschamix.util.Utilidades;

public class ProdutoActivity extends PrincipalAtividade {
	public static ProdutoVO produto=null;
	private AlertDialog.Builder adb=null;
	private ImagemProdutoVO imagemProduto=null;
	public static List< ProdutoVO > produtos=null;
	private Gallery gallery=null;
	private ImageView imageView=null;
	private YesChamixFacade facade=null;
	private TextView tvCodigo=null;
	private TextView tvNome=null;
	private TextView tvPreco=null;
	private TextView tvDescricao=null;
	private TextView tvEstoque=null;
	private Integer posicao=0;
	

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.gallery );
		this.adb = new AlertDialog.Builder( this );

		tvCodigo = (TextView) findViewById( R.id.codigo );
		tvNome = (TextView) findViewById( R.id.nome );
		tvPreco = (TextView) findViewById( R.id.preco );
		tvDescricao = (TextView) findViewById( R.id.descricaoProdutoGaleria );
		gallery = (Gallery) findViewById( R.id.gallery1 );
		tvEstoque = (TextView) findViewById( R.id.txtEstoque );
		

	}

	public void detalhes(Integer position) {
		final Dialog dialog = new Dialog( this );
		
		//linha responsável por ocultar a barra de titulo do dialogo
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView( R.layout.detalhes_gallery );
		
		TextView tvCodigo = (TextView) dialog.findViewById( R.id.codigo );
		TextView tvNome = (TextView) dialog.findViewById( R.id.nome );
		TextView tvPreco = (TextView) dialog.findViewById( R.id.preco );
		TextView tvDescricao = (TextView) dialog.findViewById( R.id.descricaoProdutoGaleria );
		TextView tvEstoque = (TextView) dialog.findViewById( R.id.txtEstoque );
		ImageButton btVoltar = (ImageButton) dialog.findViewById( R.id.backwardImage );
		
		
		/*if("0".equals(produto.getImagensProduto().get( position ).getExisteEmEstoque())){
			tvEstoque.setVisibility( View.VISIBLE );
			tvEstoque.setText(R.string.produto_indisponivel );
		}else{
			tvEstoque.setVisibility( View.INVISIBLE );
			tvEstoque.setText("" );
		}*/
		
		if(produto.getImagensProduto().size() > 0){
			if("0".equals(produto.getImagensProduto().get( 0 ).getExisteEmEstoque())){
				tvEstoque.setVisibility( View.VISIBLE );
				tvEstoque.setText(R.string.produto_indisponivel);
			}else{
				tvEstoque.setVisibility( View.INVISIBLE );
				tvEstoque.setText("" );
			}
		}else{
			if("0".equals(produto.getExisteEmEstoque())){
				tvEstoque.setVisibility( View.VISIBLE );
				tvEstoque.setText(R.string.produto_indisponivel);
			}else{
				tvEstoque.setVisibility( View.INVISIBLE );
				tvEstoque.setText("" );
			}
		}
		
		String preco = retornarPreco( produto );
		tvCodigo.setText( produto.getId());
		tvNome.setText( produto.getDescricao() );
		tvDescricao.setText( produto.getDetalhesProduto() );
		tvPreco.setText( preco );
		
		btVoltar.setOnClickListener( new OnClickListener(){
			
			@Override
			public void onClick( View v ) {
				dialog.dismiss();
				
			}
		});
		
		dialog.show();
	}
	
	@Override
	protected void onResume() {
		facade = new YesChamixFacade( this );
		if(produto.getImagensProduto().size() > 0){
			if("0".equals(produto.getImagensProduto().get( 0 ).getExisteEmEstoque())){
				tvEstoque.setVisibility( View.VISIBLE );
				tvEstoque.setText(R.string.produto_indisponivel);
			}else{
				tvEstoque.setVisibility( View.INVISIBLE );
				tvEstoque.setText("" );
			}
		}else{
			if("0".equals(produto.getExisteEmEstoque())){
				tvEstoque.setVisibility( View.VISIBLE );
				tvEstoque.setText(R.string.produto_indisponivel);
			}else{
				tvEstoque.setVisibility( View.INVISIBLE );
				tvEstoque.setText("" );
			}
		}
		
		String preco = retornarPreco( produto );
		tvCodigo.setText( produto.getId());
		tvNome.setText( produto.getDescricao() );
		tvDescricao.setText( produto.getDetalhesProduto() );
		tvPreco.setText( preco );
		try {
			
			gallery.setAdapter( new GalleryImagemProdutoAdapter( getApplicationContext() , produto.getImagensProduto() , obtainStyledAttributes( R.styleable.Gallery1 ) ) );
			imageView = (ImageView) findViewById( R.id.imageDialog );
			imagemProduto = null;
			for( ImagemProdutoVO imgProduto : produto.getImagensProduto() ) {
				if(!Utilidades.isNullOrBlank( imgProduto.getNomeArquivo() )){
					if ( imgProduto.getNomeArquivo().contains( "_A" ) ) {
						imagemProduto = imgProduto;
					}
				}
			}
			try {
				if ( Utilidades.isNullOrBlank( imagemProduto ) ) {
					imagemProduto = produto.getImagensProduto().get( 0 );
				}
				Bitmap bitmap = Utilidades.getBitmap( this.getApplicationContext() , imagemProduto.getNomeArquivo() , false , 0 );
				imageView.setImageBitmap( bitmap );
			} catch( Exception e ) {
				System.gc();
				Runtime.getRuntime().gc();
				imageView.setImageResource( R.drawable.indisponivel );
			} catch( OutOfMemoryError ex ) {
				System.gc();
				Runtime.getRuntime().gc();
				imageView.setImageResource( R.drawable.indisponivel );
			}
			imageView.setOnClickListener( new View.OnClickListener() {
				public void onClick( View v ) {
					abrirDialogo( v.getContext() , imagemProduto );
				}
			} );
			gallery.setOnItemClickListener( new OnItemClickListener() {
				

				@SuppressWarnings( "rawtypes" )
				public void onItemClick( AdapterView parent , View v , final int position , long id ) {
					//animação
					gal_onItemClick( parent, v, position, id );
					
					ProdutoActivity.this.posicao = position;
					ImageView imageView = (ImageView) findViewById( R.id.imageDialog );
					try {
						Bitmap bitmap = Utilidades.getBitmap( v.getContext() , produto.getImagensProduto().get( position ).getNomeArquivo().trim() , false , 0 );
						imageView.setImageBitmap( bitmap );
						bitmap = null;
						
						
						if("0".equals(produto.getImagensProduto().get( position ).getExisteEmEstoque())){
							tvEstoque.setVisibility( View.VISIBLE );
							tvEstoque.setText(R.string.produto_indisponivel);
						}else{
							tvEstoque.setVisibility( View.INVISIBLE );
							tvEstoque.setText("" );
						}
						
					} catch( Exception e ) {
						System.gc();
						Runtime.getRuntime().gc();
						imageView.setImageResource( R.drawable.indisponivel );
						
						if("0".equals(produto.getImagensProduto().get( position ).getExisteEmEstoque())){
							tvEstoque.setVisibility( View.VISIBLE );
							tvEstoque.setText(R.string.produto_indisponivel );
						}else{
							tvEstoque.setVisibility( View.INVISIBLE );
							tvEstoque.setText("" );
						}
						
					}
					imageView.setOnClickListener( new View.OnClickListener() {
						public void onClick( View v ) {
							abrirDialogo( v.getContext() , produto.getImagensProduto().get( position ) );
						}
					} );
				}
			} );
			
			
			
			ImageButton imageButton = (ImageButton) findViewById( R.id.backwardImage );
			imageButton.setOnClickListener( new OnClickListener() {
				public void onClick( View v ) {
					Principal.familia = produto.getFamilia();
					Principal.grupoProduto = produto.getGrupoProduto();
					Principal.classificacao = produto.getClassificacao();
					Principal.produtosRecarregados = produtos;
					Principal.continuar = 1;
					Intent intent = new Intent( v.getContext() , Principal.class );
					startActivity( intent );
					
				}
			} );
			Button btMaisDetalhes = (Button) findViewById( R.id.btMaisDetalhes );
			btMaisDetalhes.setOnClickListener( new OnClickListener(){
				
				@Override
				public void onClick( View v ) {
					detalhes(posicao);
					
				}
			});
			
			final EditText editText = (EditText) findViewById( R.id.editTextProdutoBusca );
			editText.setOnKeyListener( new OnKeyListener() {
				public boolean onKey( View v , int keyCode , KeyEvent event ) {
					if ( event.getAction() == KeyEvent.ACTION_DOWN ) {
						switch ( keyCode ) {
							case KeyEvent.KEYCODE_DPAD_CENTER:
								buscarProdutos( editText.getText().toString() );
								return true;
							case KeyEvent.KEYCODE_ENTER:
								buscarProdutos( editText.getText().toString() );
								return true;
							default:
								break;
						}
					}
					return false;
				}
			} );
			Button button = (Button) findViewById( R.id.buttonBuscarProduto );
			button.setOnClickListener( new OnClickListener() {
				public void onClick( View v ) {
					buscarProdutos( editText.getText().toString() );
				}
			} );

			ImageView retornar = (ImageView) findViewById( R.id.imageLogo );
			retornar.setOnClickListener( new OnClickListener() {
				public void onClick( View v ) {
					Intent intent = new Intent( v.getContext() , FamiliasActivity.class );
					startActivity( intent );
					
				}
			} );

			Button button2 = (Button) findViewById( R.id.imageBotaoFamilia );
			button2.setOnClickListener( new OnClickListener() {
				public void onClick( View v ) {
					Intent intent = new Intent( v.getContext() , FamiliasActivity.class );
					startActivity( intent );
				}
			} );

			Button button3 = (Button) findViewById( R.id.imageBotaoProduto );
			button3.setOnClickListener( new OnClickListener() {
				public void onClick( View v ) {
					Intent intent = new Intent( v.getContext() , Principal.class );
					startActivity( intent );
					
				}
			} );
		} catch( Exception e ) {
			Intent mIntent = new Intent( this , FamiliasActivity.class );
			startActivity( mIntent );
		}

		super.onResume();
	}
	
	
	protected void gal_onItemClick(AdapterView<?> parent, View v,
            int position, long id) {        

        // animate selected image
        Animation growAnimation = AnimationUtils.loadAnimation(this,R.anim.grow_shrink_image);
        v.startAnimation(growAnimation);
}

	public String retornarPreco( ProdutoVO produto ) {
		String preco = "";
		if ( !Utilidades.isNullOrBlank( produto ) ) {
			PrecoVO precoVO = facade.consultarPrecoPorIdPrecoIdProduto( getMyPrefs().getCodPreco() , produto.getId() );

			if ( Utilidades.isNullOrBlank( precoVO.getPreco() ) || new Double( 0 ).equals( precoVO.getPreco() ) ) {
				preco = preco + "Não Disponível";
			} else {
				preco = preco + precoVO.getPreco().toString().replace( "." , "," );
				String digito = null;
				
				digito = preco.substring( preco.indexOf( "," ) + 1 );

				if ( new Integer( 1 ).equals( digito.length() ) ) {
					preco = preco.concat( "0" );
				}
			}
		}

		return preco;
	}

	public void abrirDialogo( Context context , ImagemProdutoVO imagemProduto ) {
		final DetalheImageDialog dialog = new DetalheImageDialog( context );
		dialog.setTitle( "Clique aqui para fechar." );
		dialog.setCancelable( false );

		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom( dialog.getWindow().getAttributes() );
		lp.width = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.FILL_PARENT;
		TouchImageView img = new TouchImageView( context , 280f , 1f );
		try {
			Bitmap bitmap = Utilidades.getBitmap( context , imagemProduto.getNomeArquivo().trim() , false , 0 );
			img.setImageBitmap( bitmap );
		} catch( Exception e ) {
			System.gc();
			Runtime.getRuntime().gc();
			img.setImageResource( R.drawable.indisponivel );
		}
		img.setLayoutParams( new WindowManager.LayoutParams( lp.width , lp.height ) );

		img.setPadding( 8 , 8 , 8 , 8 );
		dialog.setContentView( img );
		dialog.show();
		dialog.getWindow().setAttributes( lp );
	}

	private void buscarProdutos( String produtoBusca ) {
		if ( Utilidades.isNullOrBlank( produtoBusca ) ) {
			AlertDialog ad = ProdutoActivity.this.adb.create();
			ad.setMessage( "Digite código ou nome do produto." );
			ad.show();
			return;
		}
		Principal.familia = null;
		Principal.produtoBusca = produtoBusca;
		Principal.continuar = 0;
		
		Principal.familia = null;
		getMyPrefs().setValue( "produtoBusca", produtoBusca );
		getMyPrefs().save();
		
		startActivity( new Intent( getApplication() , Principal.class ) );
	}

	@Override
	public void onBackPressed() {
		Principal.familia = produto.getFamilia();
		Principal.grupoProduto = produto.getGrupoProduto();
		Principal.classificacao = produto.getClassificacao();
		Principal.produtosRecarregados = produtos;
		Principal.continuar = 1;
		Intent intent = new Intent( getApplicationContext() , Principal.class );
		startActivity( intent );
		
		
	}

	@Override
	protected void onPause() {
		Utilidades.nullViewDrawablesRecursive( gallery );
		Utilidades.nullViewDrawablesRecursive( imageView );
		gallery = null;
		imageView = null;
		System.gc();
		Runtime.getRuntime().gc();
		super.onPause();
	}

	public class Processo extends AsyncTask< Integer , String , Integer > {

		private ProgressDialog progress;
		private Context context;
		Intent intent;

		public Processo( Context context ) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			//Cria novo um ProgressDialogo e exibe
			progress = new ProgressDialog( context );
			progress.setMessage( "Aguarde..." );
			progress.show();
		}

		@Override
		protected Integer doInBackground( Integer... paramss ) {
			for( int i = 0; i < paramss.length; i++ ) {
				try {
					//Simula processo...
					switch ( paramss[i] ) {
						case 0:

							break;

					}

					//Atualiza a interface através do onProgressUpdate
					publishProgress( "..." );
				} catch( Exception e ) {
					e.printStackTrace();
				}
			}
			return 1;
		}

		@Override
		protected void onPostExecute( Integer result ) {
			//Cancela progressDialogo
			progress.dismiss();

		}

		@Override
		protected void onProgressUpdate( String... values ) {
			//Atualiza mensagem
			progress.setMessage( values[0] );
		}
	}

}
