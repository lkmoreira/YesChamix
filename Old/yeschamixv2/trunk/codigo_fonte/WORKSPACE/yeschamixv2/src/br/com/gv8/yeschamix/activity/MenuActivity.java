package br.com.gv8.yeschamix.activity;

import java.io.File;
import java.io.FileFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import br.com.gv8.yeschamix.activity.adapter.TabelaPrecoListAdapter;
import br.com.gv8.yeschamix.classificacao.model.persistence.ClassificacaoVO;
import br.com.gv8.yeschamix.facade.YesChamixFacade;
import br.com.gv8.yeschamix.familia.controller.FamiliaService;
import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;
import br.com.gv8.yeschamix.grupoproduto.model.persistence.GrupoProdutoVO;
import br.com.gv8.yeschamix.imagem.controller.ImagemService;
import br.com.gv8.yeschamix.imagem.model.persistence.ImagemFolderVO;
import br.com.gv8.yeschamix.imagem.model.persistence.ImagemProdutoVO;
import br.com.gv8.yeschamix.preco.controller.PrecoService;
import br.com.gv8.yeschamix.preco.model.persistence.PrecoVO;
import br.com.gv8.yeschamix.produto.controller.ProdutoService;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;
import br.com.gv8.yeschamix.relacaogrupoprodutoclassificacao.dto.RelacaoGrupoProdutoClassificacaoDTO;
import br.com.gv8.yeschamix.relacaogrupoprodutofamilia.dto.RelacaoGrupoProdutoFamiliaDTO;
import br.com.gv8.yeschamix.relacaousuariopreco.controller.RelacaoUsuarioPrecoService;
import br.com.gv8.yeschamix.relacaousuariopreco.model.persistence.RelacaoUsuarioPrecoVO;
import br.com.gv8.yeschamix.usuario.controller.UsuarioService;
import br.com.gv8.yeschamix.usuario.model.persistence.UsuarioVO;
import br.com.gv8.yeschamix.util.GerarTxtLog;
import br.com.gv8.yeschamix.util.Preferences;
import br.com.gv8.yeschamix.util.Utilidades;
import br.com.gv8.yeschamix.util.YesChamixUtils;

public class MenuActivity extends Activity {

	private Preferences myPrefs = null;
	public static final String TITULO = "titulo";
	public static final String MENSAGEM = "mensagem";
	public static final String BOTAO = "botao";

	public String caminhoPastaString = "";
	public String qtdImagensString = "";
	public String tamOcupadoString = "";
	public static String caminhoArquivoLog = YesChamixUtils.getPastaLog();
	public static String nomeArquivoLog = FamiliasActivity.class.getSimpleName() + "Log";
	private ProgressDialog myprogress = null;
	private Handler progresshandler = null;
	private AlertDialog.Builder adb = null;
	ArrayList< RelacaoUsuarioPrecoVO > listaPreco = new ArrayList< RelacaoUsuarioPrecoVO >();
	YesChamixFacade facade = null;
	private String nomeArquivoImagem;

	List< ProdutoVO > produtosImagemAux = new ArrayList< ProdutoVO >();

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		System.gc();
		Runtime.getRuntime().gc();
		setContentView( R.layout.main );
		facade = new YesChamixFacade( this );
		this.adb = new AlertDialog.Builder( this );

	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {

		super.onCreateOptionsMenu( menu );
		MenuInflater inflater = getMenuInflater();
		inflater.inflate( R.menu.menu , menu );
		return true;
	}

	@Override
	public boolean onMenuItemSelected( int id , MenuItem item ) {
		switch ( item.getItemId() ) {
			case R.id.sincronizar:
				System.gc();
				Runtime.getRuntime().gc();
				if ( temInternet() ) {
					dialogoSincronizar( "Deseja sincronizar os Dados?" );
				} else {
					AlertDialog ad = MenuActivity.this.adb.create();
					ad.setMessage( "Sem conexão com a internet!." );
					ad.show();
				}
				return true;
			case R.id.configuracao:
				System.gc();
				Runtime.getRuntime().gc();
				startActivity( new Intent( getApplication() , FileChooserActivity.class ) );
				return true;

			case R.id.estatistica:
				System.gc();
				Runtime.getRuntime().gc();
				Processo processo = new Processo( this );
				processo.execute( 0 );
				return true;
		}
		return false;
	}

	public void exibirEstatistica() {
		final Dialog dialog = new Dialog( MenuActivity.this );
		dialog.setTitle( "Estatistica: " );
		dialog.setContentView( R.layout.estatistica );

		Button voltar = (Button) dialog.findViewById( R.id.btVoltar );
		TextView caminhoPasta = (TextView) dialog.findViewById( R.id.txtValorCaminho );
		TextView qtdImagens = (TextView) dialog.findViewById( R.id.txtValorQtdImagem );
		TextView tamOcupado = (TextView) dialog.findViewById( R.id.txtValorTamanhoOcupado );

		voltar.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick( View v ) {
				dialog.dismiss();

			}
		} );

		caminhoPasta.setText( caminhoPastaString );
		qtdImagens.setText( qtdImagensString );
		tamOcupado.setText( tamOcupadoString );
		dialog.show();
	}

	public Boolean temInternet() {
		ConnectivityManager cm = (ConnectivityManager) this.getSystemService( Context.CONNECTIVITY_SERVICE );// Pego a
		// conectividade
		// do contexto o
		// qual o metodo
		// foi chamado
		NetworkInfo netInfo = cm.getActiveNetworkInfo();// Crio o objeto netInfo
		// que recebe as
		// informacoes da
		// NEtwork
		if ( ( netInfo != null ) && ( netInfo.isConnectedOrConnecting() ) && ( netInfo.isAvailable() ) ) { // Se o objeto for nulo ou nao tem
			// conectividade retorna false
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean onKeyDown( int keyCode , KeyEvent event ) {
		if ( ( keyCode == KeyEvent.KEYCODE_BACK ) ) {
			Log.d( this.getClass().getName() , "back button pressed" );
		}
		return super.onKeyDown( keyCode , event );
	}

	@Override
	public void onBackPressed() {
		System.gc();
		Runtime.getRuntime().gc();
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
		ViewGroup mainView = (ViewGroup) LayoutInflater.from( this ).inflate( layoutResID , null );

		setContentView( mainView );
	}

	@Override
	public void setContentView( View view ) {
		super.setContentView( view );

		m_contentView = (ViewGroup) view;
	}

	@Override
	public void setContentView( View view , LayoutParams params ) {
		super.setContentView( view , params );

		m_contentView = (ViewGroup) view;
	}

	private ViewGroup m_contentView = null;

	public class Processo extends AsyncTask< Integer , String , Integer > {

		private ProgressDialog progress;
		private Context context;

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
							caminhoPastaString = YesChamixUtils.getPastaDestinoFoto();
							qtdImagensString = YesChamixUtils.quantidadeImagensPasta();
							tamOcupadoString = "Aprox. " + YesChamixUtils.tamanhoImagensOcupada() + " MB";

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
			exibirEstatistica();
		}

		@Override
		protected void onProgressUpdate( String... values ) {
			//Atualiza mensagem
			progress.setMessage( values[0] );
		}
	}

	public void dialogoSincronizar( String mensagem ) {
		final Dialog dialog = new Dialog( this );
		dialog.setTitle( mensagem );
		dialog.setContentView( R.layout.menu_android );
		final Button ok = (Button) dialog.findViewById( R.id.botaoSim );
		ok.setOnClickListener( new View.OnClickListener() {
			public void onClick( View v ) {
				System.gc();
				Runtime.getRuntime().gc();
				getMyPrefs().setPrimeiraVez( false );
				getMyPrefs().save();
				getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
				dialog.dismiss();
				sincronizando();
			}
		} );
		final Button cancelar = (Button) dialog.findViewById( R.id.botaoNao );
		cancelar.setOnClickListener( new View.OnClickListener() {
			public void onClick( View v ) {
				dialog.dismiss();
				getMyPrefs().setPrimeiraVez( false );
				getMyPrefs().save();
				System.gc();
				Runtime.getRuntime().gc();
			}
		} );
		dialog.show();// mostra o dialog
	}

	public void sincronizando() {
		this.myprogress = ProgressDialog.show( this , "Sincronizando Dados." , "Favor Aguarde" , true , false );
		this.progresshandler = new Handler() {
			@Override
			public void handleMessage( Message msg ) {
				switch ( msg.what ) {
					case 0:
						MenuActivity.this.myprogress.setMessage( "" + (String) msg.obj );
						break;
					case 1:
						MenuActivity.this.myprogress.cancel();
						avisoReinicializacao( "Aviso" , "Aplicação será Reinicializada." );
						enviar( "Sincronização" , "OK" , "Sincronização completada com sucesso." );

						break;
					case 2:
						MenuActivity.this.myprogress.cancel();
						avisoReinicializacao( "Aviso" , "Aplicação precisa ser reinicializada,\n para reestabelecer os parâmetros de conexão." );
						//						enviarErro( "Sincronização", "OK", "Ocorreu um erro na sincronização, tente novamente." );

						break;
					case 3:
						MenuActivity.this.myprogress.cancel();
						sincronizarDadosNovamente( msg.getData().getString( "error" ) );
						break;

				}
			}
		};
		Thread workthread = new Thread( new ProgressEnvia() );
		workthread.start();
	}

	public void enviar( String titulo , String botao , String mensagem ) {
		AlertDialog alertDialog = new AlertDialog.Builder( this ).create();
		alertDialog.setTitle( titulo );
		alertDialog.setMessage( mensagem );
		alertDialog.setButton( botao , new DialogInterface.OnClickListener() {
			public void onClick( DialogInterface dialog , int which ) {
				getWindow().clearFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
				dialog.dismiss();
				getMyPrefs().setPrimeiraVez( false );
				getMyPrefs().save();
			}
		} );
		alertDialog.show();
		System.gc();
		Runtime.getRuntime().gc();
	}

	public void enviarErro( String titulo , String botao , String mensagem ) {
		AlertDialog alertDialog = new AlertDialog.Builder( this ).create();
		alertDialog.setTitle( titulo );
		alertDialog.setMessage( mensagem );
		alertDialog.setButton( botao , new DialogInterface.OnClickListener() {
			public void onClick( DialogInterface dialog , int which ) {
				getWindow().clearFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
				dialog.dismiss();
				if ( temInternet() ) {
					System.gc();
					Runtime.getRuntime().gc();
					dialogoSincronizar( "Deseja sincronizar os Dados?" );

				} else {
					AlertDialog ad = MenuActivity.this.adb.create();
					ad.setMessage( "Sem conexão com a internet!." );
					ad.show();
				}
			}
		} );
		alertDialog.show();
		System.gc();
		Runtime.getRuntime().gc();
	}

	public void avisoReinicializacao( String titulo , String mensagem ) {
		final Dialog dialog = new Dialog( this );
		dialog.setTitle( titulo );
		dialog.setContentView( R.layout.menu_android_sem_internet );
		final TextView msg = (TextView) dialog.findViewById( R.id.msg );
		msg.setText( mensagem );
		final Button ok = (Button) dialog.findViewById( R.id.botaoOkSemInternet );
		ok.setOnClickListener( new View.OnClickListener() {
			public void onClick( View v ) {
				dialog.dismiss();

				getMyPrefs().setLocalErro( -1 );
				getMyPrefs().save();
				finish();
				startActivity( new Intent( MenuActivity.this , SplashActivity.class ) );
				//System.exit( 0 );
			}
		} );
		dialog.show();// mostra o dialog
	}

	private void sincronizarDadosNovamente( String mensagem ) {
		final Dialog dialog = new Dialog( this );
		dialog.setTitle( mensagem + "Tentar novamente?" );
		dialog.setContentView( R.layout.menu_android );
		final Button ok = (Button) dialog.findViewById( R.id.botaoSim );
		ok.setOnClickListener( new View.OnClickListener() {
			public void onClick( View v ) {
				if ( temInternet() ) {
					dialog.dismiss();
					sincronizando();
					System.gc();
					Runtime.getRuntime().gc();

				} else {
					dialog.dismiss();
					enviar( "Sincronização" , "OK" , "Sem conexão com internet." );
				}
			}
		} );
		final Button cancelar = (Button) dialog.findViewById( R.id.botaoNao );
		cancelar.setOnClickListener( new View.OnClickListener() {
			public void onClick( View v ) {
				dialog.dismiss();
				System.gc();
				Runtime.getRuntime().gc();
			}
		} );
		dialog.show();// mostra o dialog
	}

	int numeroDeErros = 0;

	class ProgressEnvia implements Runnable {
		public void run() {
			HashMap< String , ProdutoVO > produtosSalvar = new HashMap< String , ProdutoVO >();
			HashMap< String , FamiliaVO > familias = new HashMap< String , FamiliaVO >();
			List< ProdutoVO > produtos = new ArrayList< ProdutoVO >();
			ArrayList< PrecoVO > precos = new ArrayList< PrecoVO >();
			HashMap< String , UsuarioVO > usuarios = new HashMap< String , UsuarioVO >();
			List< RelacaoUsuarioPrecoVO > relacaoUsuarioPrecos = new ArrayList< RelacaoUsuarioPrecoVO >();
			List< RelacaoGrupoProdutoFamiliaDTO > grupoProdutos = new ArrayList< RelacaoGrupoProdutoFamiliaDTO >();
			List< RelacaoGrupoProdutoClassificacaoDTO > classificacoes = new ArrayList< RelacaoGrupoProdutoClassificacaoDTO >();
			Message msg = new Message();
			msg.what = 0;

			Integer localErro = getMyPrefs().getLocalErro();
			String usuarioLogado = getMyPrefs().getUsuarioLogado();

			if ( localErro == -1 ) {
				facade.limparBase();
			}

			System.gc();
			Runtime.getRuntime().gc();

			try {

				msg.obj = ( "Sincronizando dados ..." );

				MenuActivity.this.progresshandler.sendMessage( msg );
				familias = FamiliaService.getInstance().consultarTodosFamilias( usuarioLogado );
				produtos = ProdutoService.getInstance().consultarTodosProdutos( usuarioLogado );
				produtosImagemAux = ProdutoService.getInstance().consultarTodosProdutos( usuarioLogado );
				precos = PrecoService.getInstance().consultarTodosPrecosPorUsuario( usuarioLogado );
				usuarios = UsuarioService.getInstance().consultarTodosUsuarios( usuarioLogado );
				relacaoUsuarioPrecos = RelacaoUsuarioPrecoService.getInstance().consultarTudoUsuarioPreco( usuarioLogado );

				Collections.sort( produtos , new Comparator< ProdutoVO >() {

					/**
					 * Método reescrito da Interface Comparator com a finalidade
					 * de conter as regras de ordenação do objeto.
					 */
					@Override
					public int compare( ProdutoVO produto , ProdutoVO produto1 ) {
						return produto.getId().compareTo( produto1.getId() );
					}

				} );

				//lista auxiliar que sera utilizada para pegar as cores
				List< ProdutoVO > listaAuxProdutos = new ArrayList< ProdutoVO >();
				listaAuxProdutos.addAll( produtos );
				//produtosImagemAux = new ArrayList<ProdutoVO>(produtos);

				for( ProdutoVO produtoVO : produtos ) {
					String[] teste = produtoVO.getId().split( " " );
					String idProduto = teste[0].trim();

					//Teste inicio
					List< ImagemProdutoVO > listaImagens = new ArrayList< ImagemProdutoVO >();

					for( ProdutoVO prodCor : listaAuxProdutos ) {

						String[] teste2 = prodCor.getId().split( " " );
						String idProduto2 = teste2[0].trim();

						if ( idProduto2.equals( idProduto ) ) {

							String corProduto = "";

							for( String cor : teste2 ) {
								if ( !idProduto2.equals( cor ) && !Utilidades.isNullOrBlank( cor ) ) {
									corProduto = cor.replace( "_" , "" );
									break;
								}
							}
							prodCor.setId( idProduto2 );

							if ( !"1".equals( prodCor.getStatusProduto() ) ) {
								listaImagens.add( new ImagemProdutoVO( null , null , corProduto , prodCor , prodCor.getExisteEmEstoque() , prodCor.getStatusProduto() ) );
							}
						} else {
							break;
						}

					}

					//removendo produto que já peguei a cor
					listaAuxProdutos.remove( produtoVO );

					produtoVO.setImagensProduto( listaImagens );

					//Teste Fim

					if ( !Utilidades.isNullOrBlank( idProduto ) ) {
						if ( !produtosSalvar.containsKey( idProduto ) && produtoVO.getImagensProduto().size() != 0 ) {
							produtoVO.getImagensProduto().clear();
							produtoVO.setId( idProduto );
							produtosSalvar.put( idProduto , produtoVO );
						}
					}

					RelacaoGrupoProdutoFamiliaDTO relacaoGrupoProdutoFamilia = new RelacaoGrupoProdutoFamiliaDTO( produtoVO.getGrupoProduto().getId() , produtoVO.getFamilia().getId() , produtoVO.getGrupoProduto().getDescricao() );
					RelacaoGrupoProdutoClassificacaoDTO relacaoGrupoProdutoClassificacao = new RelacaoGrupoProdutoClassificacaoDTO( produtoVO.getFamilia().getId() , produtoVO.getGrupoProduto().getId() , produtoVO.getClassificacao().getId() , produtoVO.getClassificacao().getDescricao() );

					grupoProdutos.add( relacaoGrupoProdutoFamilia );
					classificacoes.add( relacaoGrupoProdutoClassificacao );

				}

				if ( localErro <= 0 ) {
					msg = new Message();
					msg.what = 0;
					msg.obj = ( "Sincronizando dados - famílias ..." );
					MenuActivity.this.progresshandler.sendMessage( msg );
					salvarDadosFamilia( familias.values() );
					familias = null;
					System.gc();
					Runtime.getRuntime().gc();
				}
				if ( localErro <= 1 ) {
					msg = new Message();
					msg.what = 0;
					msg.obj = ( "Sincronizando dados - grupo produtos ..." );
					MenuActivity.this.progresshandler.sendMessage( msg );
					salvarDadosGrupoProduto( grupoProdutos );
					grupoProdutos = null;
					System.gc();
					Runtime.getRuntime().gc();
				}
				if ( localErro <= 2 ) {
					msg = new Message();
					msg.what = 0;
					msg.obj = ( "Sincronizando dados - classificações..." );
					MenuActivity.this.progresshandler.sendMessage( msg );
					salvarDadosClassificacao( classificacoes );
					classificacoes = null;
					System.gc();
					Runtime.getRuntime().gc();
				}
				if ( localErro <= 3 ) {
					msg = new Message();
					msg.what = 0;
					msg.obj = ( "Sincronizando dados - precos..." );
					MenuActivity.this.progresshandler.sendMessage( msg );
					salvarDadosPrecos( precos );
					precos = null;
					System.gc();
					Runtime.getRuntime().gc();
				}
				if ( localErro <= 4 ) {
					msg = new Message();
					msg.what = 0;
					msg.obj = ( "Sincronizando dados - Usuario/Preco..." );
					MenuActivity.this.progresshandler.sendMessage( msg );
					salvarDadosUsuariosPrecos( relacaoUsuarioPrecos );
					relacaoUsuarioPrecos = null;
					System.gc();
					Runtime.getRuntime().gc();
				}
				if ( localErro <= 5 ) {
					msg = new Message();
					msg.what = 0;
					msg.obj = ( "Sincronizando dados - usuario..." );
					MenuActivity.this.progresshandler.sendMessage( msg );
					salvarDadosUsuario( usuarios.values() );
					usuarios = null;
					System.gc();
					Runtime.getRuntime().gc();
				}

				if ( localErro <= 6 ) {
					msg = new Message();
					msg.what = 0;
					msg.obj = ( "Sincronizando dados - folders..." );
					MenuActivity.this.progresshandler.sendMessage( msg );
					salvarDadosImagemFolder();
				}
				if ( localErro <= 7 ) {
					msg = new Message();
					msg.what = 0;
					msg.obj = ( "Sincronizando dados - produtos..." );
					MenuActivity.this.progresshandler.sendMessage( msg );
					salvarDados( produtosSalvar );
					produtosSalvar = null;
					produtos = null;
				}

				msg = new Message();
				msg.what = 1;
				MenuActivity.this.progresshandler.sendMessage( msg );
			} catch( SocketTimeoutException e ) {

				try {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter( sw );
					e.printStackTrace( pw );
					pw.close();
					sw.close();
					GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
				} catch( Exception ex ) {
				}
				e.printStackTrace();

				e.printStackTrace();
				msg = new Message();
				msg.getData().putString( "error" , e.getMessage() );
				msg.what = 3;
				MenuActivity.this.progresshandler.sendMessage( msg );

				System.gc();
				Runtime.getRuntime().gc();
			} catch( ConnectException e ) {

				try {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter( sw );
					e.printStackTrace( pw );
					pw.close();
					sw.close();
					GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
				} catch( Exception ex ) {
				}
				e.printStackTrace();

				msg = new Message();
				msg.getData().putString( "error" , e.getMessage() );
				msg.what = 3;
				MenuActivity.this.progresshandler.sendMessage( msg );

				System.gc();
				Runtime.getRuntime().gc();
			} catch( OutOfMemoryError e ) {

				try {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter( sw );
					e.printStackTrace( pw );
					pw.close();
					sw.close();
					GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
				} catch( Exception ex ) {
				}
				e.printStackTrace();
				msg = new Message();
				msg.getData().putString( "error" , " Erro " );
				msg.what = 3;
				MenuActivity.this.progresshandler.sendMessage( msg );
				System.gc();
				Runtime.getRuntime().gc();

			} catch( Exception e ) {

				try {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter( sw );
					e.printStackTrace( pw );
					pw.close();
					sw.close();
					GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
				} catch( Exception ex ) {
				}
				e.printStackTrace();

				e.printStackTrace();
				if ( numeroDeErros > 3 ) {
					msg = new Message();
					msg.getData().putString( "error" , " Erro " );
					msg.what = 2;
					MenuActivity.this.progresshandler.sendMessage( msg );
				} else {
					msg = new Message();
					msg.getData().putString( "error" , e.getMessage() );
					msg.what = 3;
					MenuActivity.this.progresshandler.sendMessage( msg );
				}
				numeroDeErros++;
				System.gc();
				Runtime.getRuntime().gc();

			} finally {
				familias = null;
				produtos = null;
				precos = null;
				usuarios = null;
				relacaoUsuarioPrecos = null;
				System.gc();
			}

		}
	}

	private void salvarDadosFamilia( Collection< FamiliaVO > lista ) throws Exception {
		YesChamixFacade facade = new YesChamixFacade( getApplicationContext() );
		Integer localErro = 0;
		try {
			try {

				for( FamiliaVO familiaVO : lista ) {
					nomeArquivoImagem = familiaVO.getNomeArquivo().trim();
					File diretorioCartao = new File( YesChamixUtils.getPastaDestinoFoto() + File.separator );
					File[] files = diretorioCartao.listFiles( new FileFilter() {
						public boolean accept( File pathname ) {
							return pathname.getName().startsWith( nomeArquivoImagem );
						}
					} );

					if ( files.length <= 0 ) {
						ImagemService.getInstance().downloadImagemFamilia( familiaVO.getNomeArquivo().trim() );
					}
					facade.salvarFamilia( familiaVO );
				}

			} catch( SocketTimeoutException e ) {

				getMyPrefs().setLocalErro( localErro );
				getMyPrefs().save();

				System.gc();
				Runtime.getRuntime().gc();
				throw new SocketTimeoutException( "Problema em Download de imagens." );
			} catch( ConnectException e ) {

				getMyPrefs().setLocalErro( localErro );
				getMyPrefs().save();

				System.gc();
				Runtime.getRuntime().gc();
				throw new ConnectException( "Problema em Download de imagens." );
			}

		} catch( Exception e ) {

			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
			} catch( Exception ex ) {
			}
			e.printStackTrace();

			getMyPrefs().setLocalErro( localErro );
			getMyPrefs().save();

			System.gc();
			Runtime.getRuntime().gc();
			throw new Exception( "Problema: " );
		}
	}

	private void salvarDadosGrupoProduto( List< RelacaoGrupoProdutoFamiliaDTO > lista ) throws Exception {
		YesChamixFacade facade = new YesChamixFacade( getApplicationContext() );

		Integer localErro = 1;

		try {
			for( RelacaoGrupoProdutoFamiliaDTO vo : lista ) {
				GrupoProdutoVO carregado = facade.consultarGrupoProdutoPorIdGrupoProdutoIdFamilia( vo.getIdGrupoProduto() , vo.getIdFamilia() );
				if ( Utilidades.isNullOrBlank( carregado.getId() ) ) {
					facade.salvarGrupoProduto( vo );
				}
			}
		} catch( Exception e ) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
			} catch( Exception ex ) {
			}
			e.printStackTrace();

			getMyPrefs().setLocalErro( localErro );
			getMyPrefs().save();

			System.gc();
			Runtime.getRuntime().gc();
			throw new Exception( "Problema: " );
		}
	}

	private void salvarDadosClassificacao( List< RelacaoGrupoProdutoClassificacaoDTO > lista ) throws Exception {
		YesChamixFacade facade = new YesChamixFacade( getApplicationContext() );

		Integer localErro = 2;

		try {

			for( RelacaoGrupoProdutoClassificacaoDTO vo : lista ) {
				ClassificacaoVO classificacao = facade.consultarClassificacao( vo.getIdClassificacao() , vo.getIdGrupoProduto() , vo.getIdFamilia() );
				if ( Utilidades.isNullOrBlank( classificacao.getId() ) ) {
					facade.salvarClassificacao( vo );

				}
			}
		} catch( Exception e ) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
			} catch( Exception ex ) {
			}

			e.printStackTrace();

			getMyPrefs().setLocalErro( localErro );
			getMyPrefs().save();

			System.gc();
			Runtime.getRuntime().gc();
			throw new Exception( "Problema: " );
		}
	}

	private void salvarDadosPrecos( List< PrecoVO > lista ) throws Exception {
		YesChamixFacade facade = new YesChamixFacade( getApplicationContext() );
		Integer localErro = 3;
		try {

			for( PrecoVO preco : lista ) {
				PrecoVO carregado = facade.consultarPrecoPorIdPrecoIdProduto( preco.getId() , preco.getProduto().getId() );
				if ( Utilidades.isNullOrBlank( carregado.getCodigo() ) ) {
					facade.salvarPreco( preco );
				}
			}

		} catch( Exception e ) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
			} catch( Exception ex ) {
			}
			e.printStackTrace();

			e.printStackTrace();
			getMyPrefs().setLocalErro( localErro );
			getMyPrefs().save();

			System.gc();
			Runtime.getRuntime().gc();
			throw new Exception( "Problema: " );
		}
	}

	private void salvarDadosUsuariosPrecos( List< RelacaoUsuarioPrecoVO > lista ) throws Exception {
		YesChamixFacade facade = new YesChamixFacade( getApplicationContext() );
		Integer localErro = 4;
		try {
			facade.salvarRelacaoUsuarioPreco( lista );

		} catch( Exception e ) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
			} catch( Exception ex ) {
			}
			e.printStackTrace();

			e.printStackTrace();

			getMyPrefs().setLocalErro( localErro );
			getMyPrefs().save();

			System.gc();
			Runtime.getRuntime().gc();
			throw new Exception( "Problema: " );
		}
	}

	private void salvarDadosUsuario( Collection< UsuarioVO > lista ) throws Exception {
		YesChamixFacade facade = new YesChamixFacade( getApplicationContext() );
		Integer localErro = 5;
		try {

			for( UsuarioVO vo : lista ) {
				facade.salvarUsuario( vo );
			}

		} catch( Exception e ) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
			} catch( Exception ex ) {
			}
			e.printStackTrace();

			e.printStackTrace();

			getMyPrefs().setLocalErro( localErro );
			getMyPrefs().save();

			System.gc();
			Runtime.getRuntime().gc();
			throw new Exception( "Problema: " );
		}
	}

	private void salvarDadosImagemFolder() throws Exception {
		YesChamixFacade facade = new YesChamixFacade( getApplicationContext() );

		Integer localErro = 6;

		try {

			List< String > nomesDasImagens = ImagemService.getInstance().downloadImagemFolder();
			facade.detetarTudoImagensFolder();

			if ( nomesDasImagens.size() >= 1 ) {
				for( String nomeImg : nomesDasImagens ) {
					ImagemFolderVO imgFolder = new ImagemFolderVO( nomeImg );
					facade.salvarImagemFolder( imgFolder );
				}

			}
		} catch( Exception e ) {

			getMyPrefs().setLocalErro( localErro );
			getMyPrefs().save();

			System.gc();
			Runtime.getRuntime().gc();
			throw new Exception( "Problema: " );
		}
	}

	private void salvarDados( HashMap< String , ProdutoVO > lista ) throws Exception {
		Integer localErro = 7;

		YesChamixFacade facade = new YesChamixFacade( getApplicationContext() );
		try {
			HashMap< String , ProdutoVO > listaProdutos = facade.consultarTudoProduto();
			for( ProdutoVO p : listaProdutos.values() ) {

				if ( !lista.containsKey( p.getId() ) ) {
					facade.deletarProduto( p.getId() );
				}
			}
			for( ProdutoVO produtoVO : lista.values() ) {
				if ( Utilidades.isNullOrBlank( facade ) ) {
					facade = new YesChamixFacade( getApplicationContext() );
				}
				boolean produtoExiste = listaProdutos.containsKey( produtoVO.getId() );
				if ( !produtoExiste ) {
					try {
						if ( produtoVO.getId().startsWith( "SR3010" ) ) {
							System.out.println( "Achei" );
						}
						List< String > nomesImagem = ImagemService.getInstance().downloadImagemProduto( produtoVO.getId().concat( ".JPG" ) );
						List< ImagemProdutoVO > imagensProduto = new ArrayList< ImagemProdutoVO >();

					for( String nomeImg : nomesImagem ) {
							ImagemProdutoVO imagemProduto;
							String[] nomeImgSemExtensao = nomeImg.split( "\\." );

							String[] nomeImgQuebra = nomeImgSemExtensao[0].split( "\\_" );
							String idProduto = nomeImgQuebra[0].trim();

							String corProduto = "";
							String corProdutoAux = "";

							for( String cor : nomeImgQuebra ) {
								if ( !idProduto.equals( cor ) && !"_".equals( cor ) ) {
									corProduto = cor.trim();
									break;
								}
							}
							
							for( String cor : nomeImgQuebra ) {
								if ( !idProduto.equals( cor ) && !"_".equals( cor ) ) {
									corProdutoAux += cor.trim();
								}
							}

							List< ImagemProdutoVO > produtos = getImagensProduto( idProduto );
							if ( produtos.size() > 0 ) {
								for( ImagemProdutoVO produtoVerificar : produtos ) {
									if ( produtoVerificar.getCor().equals( corProduto ) ) {
										imagemProduto = new ImagemProdutoVO( null , nomeImg , corProduto , produtoVO , produtoVerificar.getExisteEmEstoque() , produtoVerificar.getStatusProduto() );
										imagensProduto.add( imagemProduto );
									}else if(produtoVerificar.getCor().equals( corProdutoAux ) ) {
											imagemProduto = new ImagemProdutoVO( null , nomeImg , corProdutoAux , produtoVO , produtoVerificar.getExisteEmEstoque() , produtoVerificar.getStatusProduto() );
											imagensProduto.add( imagemProduto );
									}else if(corProdutoAux.startsWith(produtoVerificar.getCor()) ) {
										imagemProduto = new ImagemProdutoVO( null , nomeImg , corProdutoAux , produtoVO , produtoVerificar.getExisteEmEstoque() , produtoVerificar.getStatusProduto() );
										imagensProduto.add( imagemProduto );
								}
									
									
								}
								
								if(imagensProduto.size() == 0){
									imagemProduto = new ImagemProdutoVO( null , nomeImg , corProduto , produtoVO , produtoVO.getExisteEmEstoque() , produtoVO.getStatusProduto() );
									imagensProduto.add( imagemProduto );
								}
							} else {
								imagemProduto = new ImagemProdutoVO( null , nomeImg , corProduto , produtoVO , produtoVO.getExisteEmEstoque() , produtoVO.getStatusProduto() );
								imagensProduto.add( imagemProduto );
							}

						}

						produtoVO.getImagensProduto().clear();
						produtoVO.getImagensProduto().addAll( imagensProduto );

						for( ImagemProdutoVO imgProdCor : produtoVO.getImagensProduto() ) {

							/*for ( ImagemProdutoVO nomeImgCor : imagensProduto ) {
								if ( imgProdCor.getCor().startsWith( nomeImgCor.getCor() ) ) {
									ImagemProdutoVO novaCor = nomeImgCor;
									imgProdCor.setNomeArquivo( nomeImgCor.getNomeArquivo() );
									break;
								}

							}*/

							facade.salvarImagemProduto( imgProdCor );

						}
						/*List<ImagemProdutoVO> listaImagens = new ArrayList<ImagemProdutoVO>();
						if(imagensProduto.size() > 1 && produtoVO.getImagensProduto().size() == 1){
							for( ImagemProdutoVO nomeImgCor : imagensProduto ) {
								for( ImagemProdutoVO imgProdCor : produtoVO.getImagensProduto() ) {
									ImagemProdutoVO  imagemProdutoAux = new ImagemProdutoVO( null, nomeImgCor.getNomeArquivo(), nomeImgCor.getCor(), produtoVO, imgProdCor.getExisteEmEstoque(), imgProdCor.getStatusProduto() );
									listaImagens.add( imagemProdutoAux );
									facade.salvarImagemProduto( imagemProdutoAux );
								}

							}
							produtoVO.getImagensProduto().clear();
							produtoVO.getImagensProduto().addAll( listaImagens );
						}*/

						facade.salvarProduto( produtoVO );
					} catch( SocketTimeoutException e ) {
						try {
							StringWriter sw = new StringWriter();
							PrintWriter pw = new PrintWriter( sw );
							e.printStackTrace( pw );
							pw.close();
							sw.close();
							GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
						} catch( Exception ex ) {
							ex.printStackTrace();
						}
						e.printStackTrace();

						facade.deletarProduto( produtoVO.getId() );

						getMyPrefs().setLocalErro( localErro );
						getMyPrefs().save();

						System.gc();
						Runtime.getRuntime().gc();
						throw new SocketTimeoutException( "Problema em Download de imagens." );
					} catch( ConnectException e ) {
						try {
							StringWriter sw = new StringWriter();
							PrintWriter pw = new PrintWriter( sw );
							e.printStackTrace( pw );
							pw.close();
							sw.close();
							GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
						} catch( Exception ex ) {
							ex.printStackTrace();
						}
						e.printStackTrace();

						facade.deletarProduto( produtoVO.getId() );

						getMyPrefs().setLocalErro( localErro );
						getMyPrefs().save();

						System.gc();
						Runtime.getRuntime().gc();
						throw new ConnectException( "Problema em Download de imagens." );
					}

				} else if ( Utilidades.parseDate( produtoVO.getDataUltimaAtualizacao() ).after( Utilidades.parseDate( listaProdutos.get( produtoVO.getId() ).getDataUltimaAtualizacao() ) ) ) {
					//					ProdutoVO alterado = facade.consultarProdutoPorId( listaProdutos.get( produtoVO.getId() ).getId() );
					//					for( ImagemProdutoVO ip : alterado.getImagensProduto() ) {
					//						File file = new File( YesChamixUtils.getPastaDestinoFoto() + File.separator + ip.getNomeArquivo() );
					//						file.delete();
					//					}
					facade.deletarProduto( listaProdutos.get( produtoVO.getId() ).getId() );

					try {
						List< String > nomesImagem = ImagemService.getInstance().downloadImagemProduto( produtoVO.getId().concat( ".JPG" ) );
						List< ImagemProdutoVO > imagensProduto = new ArrayList< ImagemProdutoVO >();

						for( String nomeImg : nomesImagem ) {
							ImagemProdutoVO imagemProduto;
							String[] nomeImgSemExtensao = nomeImg.split( "\\." );

							String[] nomeImgQuebra = nomeImgSemExtensao[0].split( "\\_" );
							String idProduto = nomeImgQuebra[0].trim();

							String corProduto = "";

							for( String cor : nomeImgQuebra ) {
								if ( !idProduto.equals( cor ) && !"_".equals( cor ) ) {
									corProduto = cor.trim();
									break;
								}
							}
							imagemProduto = new ImagemProdutoVO( null , nomeImg , corProduto , null , null , null );

							imagensProduto.add( imagemProduto );
						}

						for( ImagemProdutoVO imgProdCor : produtoVO.getImagensProduto() ) {

							for( ImagemProdutoVO nomeImgCor : imagensProduto ) {
								if ( imgProdCor.getCor().equals( nomeImgCor.getCor() ) ) {

									imgProdCor.setNomeArquivo( nomeImgCor.getNomeArquivo() );

									break;
								}

							}

							facade.salvarImagemProduto( imgProdCor );

						}

						facade.salvarProduto( produtoVO );
					} catch( SocketTimeoutException e ) {
						try {
							StringWriter sw = new StringWriter();
							PrintWriter pw = new PrintWriter( sw );
							e.printStackTrace( pw );
							pw.close();
							sw.close();
							GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
						} catch( Exception ex ) {
							ex.printStackTrace();
						}
						e.printStackTrace();

						facade.deletarProduto( produtoVO.getId() );

						getMyPrefs().setLocalErro( localErro );
						getMyPrefs().save();

						System.gc();
						Runtime.getRuntime().gc();
						throw new SocketTimeoutException( "Problema em Download de imagens." );
					} catch( ConnectException e ) {
						try {
							StringWriter sw = new StringWriter();
							PrintWriter pw = new PrintWriter( sw );
							e.printStackTrace( pw );
							pw.close();
							sw.close();
							GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
						} catch( Exception ex ) {
							ex.printStackTrace();
						}
						e.printStackTrace();

						facade.deletarProduto( produtoVO.getId() );

						getMyPrefs().setLocalErro( localErro );
						getMyPrefs().save();

						throw new ConnectException( "Problema em Download de imagens." );
					}

				} else if ( Utilidades.parseDate( produtoVO.getDataUltimaAtualizacao() ).compareTo( Utilidades.parseDate( listaProdutos.get( produtoVO.getId() ).getDataUltimaAtualizacao() ) ) == 0 && produtoVO.getContadorAtualizacao() > listaProdutos.get( produtoVO.getId() ).getContadorAtualizacao() ) {
					//					ProdutoVO alterado = facade.consultarProdutoPorId( produtoVO.getId() );
					//					for( ImagemProdutoVO ip : alterado.getImagensProduto() ) {
					//						File file = new File( YesChamixUtils.getPastaDestinoFoto() + File.separator + ip.getNomeArquivo() );
					//						file.delete();
					//					}
					facade.deletarProduto( listaProdutos.get( produtoVO.getId() ).getId() );

					try {
						List< String > nomesImagem = ImagemService.getInstance().downloadImagemProduto( produtoVO.getId().concat( ".JPG" ) );
						List< ImagemProdutoVO > imagensProduto = new ArrayList< ImagemProdutoVO >();

						for( String nomeImg : nomesImagem ) {
							ImagemProdutoVO imagemProduto;
							String[] nomeImgSemExtensao = nomeImg.split( "\\." );

							String[] nomeImgQuebra = nomeImgSemExtensao[0].split( "\\_" );
							String idProduto = nomeImgQuebra[0].trim();

							String corProduto = "";

							for( String cor : nomeImgQuebra ) {
								if ( !idProduto.equals( cor ) && !"_".equals( cor ) ) {
									corProduto = cor.trim();
									break;
								}
							}
							imagemProduto = new ImagemProdutoVO( null , nomeImg , corProduto , null , null , null );

							imagensProduto.add( imagemProduto );
						}

						for( ImagemProdutoVO imgProdCor : produtoVO.getImagensProduto() ) {

							for( ImagemProdutoVO nomeImgCor : imagensProduto ) {
								if ( imgProdCor.getCor().equals( nomeImgCor.getCor() ) ) {

									imgProdCor.setNomeArquivo( nomeImgCor.getNomeArquivo() );

									break;
								}

							}

							facade.salvarImagemProduto( imgProdCor );

						}

						facade.salvarProduto( produtoVO );
					} catch( SocketTimeoutException e ) {
						e.printStackTrace();
						facade.deletarProduto( produtoVO.getId() );

						getMyPrefs().setLocalErro( localErro );
						getMyPrefs().save();

						System.gc();
						Runtime.getRuntime().gc();
						throw new SocketTimeoutException( "Problema em Download de imagens." );

					} catch( ConnectException e ) {
						facade.deletarProduto( produtoVO.getId() );

						getMyPrefs().setLocalErro( localErro );
						getMyPrefs().save();
						e.printStackTrace();
						System.gc();
						Runtime.getRuntime().gc();
						throw new ConnectException( "Problema em Download de imagens." );
					}

				}
			}

			//ao acabar produto, setar 0, para a proxima sincronização.
			getMyPrefs().setLocalErro( 0 );
			getMyPrefs().save();

		} catch( SocketTimeoutException e ) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
			} catch( Exception ex ) {
			}
			e.printStackTrace();

			getMyPrefs().setLocalErro( localErro );
			getMyPrefs().save();

			System.gc();
			Runtime.getRuntime().gc();
			throw new SocketTimeoutException( "Problema em Download de imagens." );
		} catch( ConnectException e ) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
			} catch( Exception ex ) {
			}
			e.printStackTrace();

			getMyPrefs().setLocalErro( localErro );
			getMyPrefs().save();

			System.gc();
			Runtime.getRuntime().gc();
			throw new ConnectException( "Problema em Download de imagens." );
		} catch( Exception e ) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString() , caminhoArquivoLog , nomeArquivoLog );
			} catch( Exception ex ) {
			}
			e.printStackTrace();

			getMyPrefs().setLocalErro( localErro );
			getMyPrefs().save();

			System.gc();
			Runtime.getRuntime().gc();
			throw new Exception( "Problema: " );

		}
	}

	public void escolherPreco() {
		final Dialog dialog = new Dialog( this );
		getMyPrefs().isPrecoSelecionado( Boolean.FALSE );
		getMyPrefs().setCodPreco( "" );
		getMyPrefs().save();

		listaPreco = facade.consultarRelacaoUsuarioPrecoPorLogin( getMyPrefs().getUsuarioLogado() );

		//linha responsável por ocultar a barra de titulo do dialogo
		dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
		dialog.setContentView( R.layout.lista_precos );

		ListView listaPrecos = (ListView) dialog.findViewById( R.id.listViewPrecos );

		//personalizando titulo tabela
		TextView titulo = (TextView) dialog.findViewById( R.id.txvTitulo );
		titulo.setText( "SELECIONE UM SEGMENTO DE PRODUTO:" );

		TabelaPrecoListAdapter adapter = new TabelaPrecoListAdapter( this.getApplicationContext() , listaPreco , this );
		listaPrecos.setAdapter( adapter );

		listaPrecos.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick( AdapterView< ? > parent , View view , int position , long id ) {

				RelacaoUsuarioPrecoVO tabelaPreco = (RelacaoUsuarioPrecoVO) parent.getItemAtPosition( position );

				if ( !Utilidades.isNullOrBlank( tabelaPreco.getPreco() ) ) {
					getMyPrefs().isPrecoSelecionado( Boolean.TRUE );
					getMyPrefs().setCodPreco( tabelaPreco.getPreco().getId() );
					getMyPrefs().save();
					dialog.dismiss();

				}

			}

		} );
		dialog.show();

		dialog.setOnDismissListener( new DialogInterface.OnDismissListener() {

			@Override
			public void onDismiss( DialogInterface d ) {
				if ( !getMyPrefs().isPrecoSelecionado() && !listaPreco.isEmpty() ) {
					dialog.show();
				}

			}
		} );
	}

	public List< ImagemProdutoVO > getImagensProduto( String codigoProduto ) {
		Collections.sort( produtosImagemAux , new Comparator< ProdutoVO >() {

			/**
			 * Método reescrito da Interface Comparator com a finalidade
			 * de conter as regras de ordenação do objeto.
			 */
			@Override
			public int compare( ProdutoVO produto , ProdutoVO produto1 ) {
				return produto.getId().compareTo( produto1.getId() );
			}

		} );

		//lista auxiliar que sera utilizada para pegar as cores
		List< ProdutoVO > listaAuxProdutos = new ArrayList< ProdutoVO >();
		listaAuxProdutos.addAll( produtosImagemAux );
		List< ImagemProdutoVO > listaImagens = new ArrayList< ImagemProdutoVO >();

		for( ProdutoVO produtoVO : produtosImagemAux ) {
			String[] teste = produtoVO.getId().split( " " );
			String idProduto = teste[0].trim();

			if ( idProduto.equals( codigoProduto ) ) {
				//Teste inicio
				listaImagens = new ArrayList< ImagemProdutoVO >();

				for( ProdutoVO prodCor : listaAuxProdutos ) {

					String[] teste2 = prodCor.getId().split( " " );
					String idProduto2 = teste2[0].trim();

					if ( idProduto2.equals( idProduto ) ) {

						String corProduto = "";

						for( String cor : teste2 ) {
							if ( !idProduto2.equals( cor ) && !Utilidades.isNullOrBlank( cor ) ) {
								corProduto = cor.replace( "_" , "" );
								break;
							}
						}
						//prodCor.setId( idProduto2 );

						if ( !"1".equals( prodCor.getStatusProduto() ) ) {
							listaImagens.add( new ImagemProdutoVO( null , null , corProduto , prodCor , prodCor.getExisteEmEstoque() , prodCor.getStatusProduto() ) );
						}
					}

				}

			}
		}
		return listaImagens;
	}
}
