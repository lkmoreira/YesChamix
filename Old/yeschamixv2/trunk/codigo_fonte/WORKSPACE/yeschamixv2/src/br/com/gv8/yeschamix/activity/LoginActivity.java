package br.com.gv8.yeschamix.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import br.com.gv8.yeschamix.configuracaobd.controller.ConfigService;
import br.com.gv8.yeschamix.facade.YesChamixFacade;
import br.com.gv8.yeschamix.usuario.controller.UsuarioService;
import br.com.gv8.yeschamix.usuario.model.persistence.UsuarioVO;
import br.com.gv8.yeschamix.util.Utilidades;

import com.google.gson.Gson;

public class LoginActivity extends PrincipalAtividade {

	public Boolean primeiraVez;
	private ProgressDialog myprogress;
	public static Handler progresshandler;
	private ProgressDialog progressDialog;
	private AlertDialog.Builder adb;
	private EditText edtUsuario;
	private EditText edtSenha;
	private CheckBox chkLembrarSenha;
	private Button btnEntrar;
	private Button btnSair;
	private Button btnLimpar;
	private String usuario = "";
	private String senha = "";
	private UsuarioVO usuarioVO;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		setContentView( R.layout.login );
		primeiraVez = true;
		
		//Essa linha é responsável possibilitar que apareça todos os folders
		getMyPrefs().setValue( "tipoFolder", " " );
		getMyPrefs().isPrecoSelecionado( false );
		getMyPrefs().save();

		new YesChamixFacade( getApplicationContext() );

		this.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN );
		this.adb = new AlertDialog.Builder( this );

		edtUsuario = (EditText) findViewById( R.id.txtLogin );
		edtSenha = (EditText) findViewById( R.id.txtSenha );
		chkLembrarSenha = (CheckBox) findViewById( R.id.chkLembrarSenha );

		if ( !Utilidades.isNullOrBlank( getMyPrefs().getLogin() ) ) {
			edtUsuario.setText( getMyPrefs().getLogin() );
			usuario = getMyPrefs().getLogin();
		}

		if ( !Utilidades.isNullOrBlank( getMyPrefs().getSenha() ) ) {
			edtSenha.setText( getMyPrefs().getSenha() );
			chkLembrarSenha.setChecked( true );
			senha = getMyPrefs().getSenha();
		}

		edtUsuario.addTextChangedListener( new TextWatcher() {

			@Override
			public void onTextChanged( CharSequence s , int start , int before , int count ) {
			}

			@Override
			public void beforeTextChanged( CharSequence s , int start , int count , int after ) {
			}

			@Override
			public void afterTextChanged( Editable s ) {
				usuario = ( edtUsuario.getText().toString() );
			}
		} );
		
		

		edtSenha.addTextChangedListener( new TextWatcher() {

			@Override
			public void onTextChanged( CharSequence s , int start , int before , int count ) {
			}

			@Override
			public void beforeTextChanged( CharSequence s , int start , int count , int after ) {
			}

			@Override
			public void afterTextChanged( Editable s ) {
				senha = ( edtSenha.getText().toString() );
			}
		} );

		edtSenha.setOnKeyListener( new OnKeyListener() {
			public boolean onKey( View v , int keyCode , KeyEvent event ) {
				if ( event.getAction() == KeyEvent.ACTION_DOWN ) {
					switch ( keyCode ) {
						case KeyEvent.KEYCODE_DPAD_CENTER:
						case KeyEvent.KEYCODE_ENTER:

							if ( usuario.equals( "" ) ) {
								final Dialog dialog = new Dialog( LoginActivity.this );
								dialog.setTitle( "Digite o usuário!" );
								dialog.setContentView( R.layout.menu_android_sem_internet );
								final Button ok = (Button) dialog.findViewById( R.id.botaoOkSemInternet );
								ok.setOnClickListener( new View.OnClickListener() {
									public void onClick( View v ) {

										dialog.dismiss();

									}
								} );

								dialog.show();
							} else if ( senha.equals( "" ) ) {
								final Dialog dialog = new Dialog( LoginActivity.this );
								dialog.setTitle( "Digite sua senha!" );
								dialog.setContentView( R.layout.menu_android_sem_internet );
								final Button ok = (Button) dialog.findViewById( R.id.botaoOkSemInternet );
								ok.setOnClickListener( new View.OnClickListener() {
									public void onClick( View v ) {
										dialog.dismiss();
									}
								} );

								dialog.show();
							}

							if ( !senha.equals( "" ) && !usuario.equals( "" ) ) {
								progressDialog = ProgressDialog.show( LoginActivity.this , "Sincronizando" , "Aguarde..." );

								try {
									sincronizandoLogin();
									progressDialog.dismiss();
								} catch( NumberFormatException e ) {
									e.printStackTrace();
								}

								progressDialog.setOnDismissListener( new OnDismissListener() {

									@Override
									public void onDismiss( DialogInterface paramDialogInterface ) {
										if ( !Utilidades.isNullOrBlank( usuario ) ) {
											Intent intent = new Intent( getApplicationContext() , InicialActivity.class );
											intent.setClassName( "br.com.gv8.yeschamix.activity" , "br.com.gv8.yeschamix.activity.InicialActivity" );
											UsuarioVO user = new UsuarioVO();
											user.setLogin( usuario );
											user.setSenha( senha );
											intent.putExtra( "usuario" , usuario );

											startActivity( intent );
										} else {
											final Dialog dialog = new Dialog( LoginActivity.this );
											dialog.setTitle( "Erro ao encontrar usuário!\nTente novamente" );
											dialog.setContentView( R.layout.menu_android_sem_internet );
											final Button ok = (Button) dialog.findViewById( R.id.botaoOkSemInternet );
											ok.setOnClickListener( new View.OnClickListener() {
												public void onClick( View v ) {

													dialog.dismiss();

												}
											} );

											dialog.show();

										}
									}
								} );

							}
							return true;
						default:
							break;
					}
				}
				return false;
			}
		} );

		btnEntrar = (Button) findViewById( R.id.btnLogar );
		btnEntrar.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick( View v ) {

				if ( usuario.equals( "" ) ) {
					final Dialog dialog = new Dialog( LoginActivity.this );
					dialog.setTitle( "Digite o usuário!" );
					dialog.setContentView( R.layout.menu_android_sem_internet );
					final Button ok = (Button) dialog.findViewById( R.id.botaoOkSemInternet );
					ok.setOnClickListener( new View.OnClickListener() {
						public void onClick( View v ) {

							dialog.dismiss();

						}
					} );

					dialog.show();
				} else if ( senha.equals( "" ) ) {
					final Dialog dialog = new Dialog( LoginActivity.this );
					dialog.setTitle( "Digite sua senha!" );
					dialog.setContentView( R.layout.menu_android_sem_internet );
					final Button ok = (Button) dialog.findViewById( R.id.botaoOkSemInternet );
					ok.setOnClickListener( new View.OnClickListener() {
						public void onClick( View v ) {
							dialog.dismiss();
						}
					} );

					dialog.show();
				}

				if ( !senha.equals( "" ) && !usuario.equals( "" ) ) {
					progressDialog = ProgressDialog.show( LoginActivity.this , "Sincronizando" , "Aguarde..." );

					try {
						sincronizandoLogin();
						progressDialog.dismiss();
					} catch( NumberFormatException e ) {
						e.printStackTrace();
					}

					progressDialog.setOnDismissListener( new OnDismissListener() {

						@Override
						public void onDismiss( DialogInterface paramDialogInterface ) {
							if ( !Utilidades.isNullOrBlank( usuario ) ) {
								Intent intent = new Intent( getApplicationContext() , InicialActivity.class );
								intent.setClassName( "br.com.gv8.yeschamix.activity" , "br.com.gv8.yeschamix.activity.InicialActivity" );
								UsuarioVO user = new UsuarioVO();
								user.setLogin( usuario );
								user.setSenha( senha );
								intent.putExtra( "usuario" , usuario );

								startActivity( intent );
							} else {
								final Dialog dialog = new Dialog( LoginActivity.this );
								dialog.setTitle( "Erro ao encontrar usuário!\nTente novamente" );
								dialog.setContentView( R.layout.menu_android_sem_internet );
								final Button ok = (Button) dialog.findViewById( R.id.botaoOkSemInternet );
								ok.setOnClickListener( new View.OnClickListener() {
									public void onClick( View v ) {

										dialog.dismiss();

									}
								} );

								dialog.show();

							}
						}
					} );

				}
			}
		} );

		btnSair = (Button) findViewById( R.id.btnSairAplicacao );
		btnSair.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick( View v ) {
				Intent intent = new Intent( Intent.ACTION_MAIN );
				intent.addCategory( Intent.CATEGORY_HOME );
				intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
				startActivity( intent );

			}
		} );

		btnLimpar = (Button) findViewById( R.id.btnLimparDados );
		btnLimpar.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick( View v ) {
				edtUsuario.setText( "" );
				edtSenha.setText( "" );
			}
		} );

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent( Intent.ACTION_MAIN );
		intent.addCategory( Intent.CATEGORY_HOME );
		intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		startActivity( intent );
	}

	public void sincronizarDados( String mensagem , final Activity activity ) {
		final Dialog dialog = new Dialog( this );
		dialog.setTitle( "Sincronizar dados?" );
		dialog.setContentView( R.layout.menu_android );
		final Button ok = (Button) dialog.findViewById( R.id.botaoSim );
		ok.setOnClickListener( new View.OnClickListener() {
			public void onClick( View v ) {

				dialog.dismiss();
				sincronizandoLogin();

			}
		} );
		final Button cancelar = (Button) dialog.findViewById( R.id.botaoNao );
		cancelar.setOnClickListener( new View.OnClickListener() {
			public void onClick( View v ) {
			}
		} );
		dialog.show();// mostra o dialog
	}

	private void sincronizandoLogin() {
		this.myprogress = ProgressDialog.show( this , "Validando usuário" , "Favor Aguarde" , true , false );
		progresshandler = new Handler() {
			@Override
			public void handleMessage( Message msg ) {
				switch ( msg.what ) {
					case 0:
						myprogress.setMessage( "" + (String) msg.obj );
						break;
					case 1:
						myprogress.cancel();
						enviar( "Validação completada com sucesso." );
						break;
					case 2:
						myprogress.cancel();
						enviar( "Ocorreu um erro na validação, tente novamente." );
						break;

					case 3:
						myprogress.cancel();
						enviar( "Não há conexão com a Internet!" );
						break;

					case 4:
						myprogress.cancel();
						enviar( "Usuario encontra-se BLOQUEADO!" );
						break;

				}
			}
		};
		Thread workthread = new Thread( new ProgressEnvia() );
		workthread.start();
	}

	private void enviar( String mensagem ) {

		final String mensagemAux = mensagem;
		AlertDialog ad = this.adb.create();
		ad.setMessage( mensagem );
		ad.setOnDismissListener( new OnDismissListener() {

			@Override
			public void onDismiss( DialogInterface paramDialogInterface ) {
				if ( mensagemAux.equals( "Sincronização completada com sucesso." ) ) {
					Intent intent = new Intent( getApplicationContext() , InicialActivity.class );
					intent.setClassName( "br.com.gv8.yeschamix.activity" , "br.com.gv8.yeschamix.activity.InicialActivity" );
					intent.putExtra( "usuario" , usuario );

					startActivity( intent );

				} else {
					Intent intent = new Intent( getApplicationContext() , LoginActivity.class );
					intent.setClassName( "br.com.gv8.yeschamix.activity" , "br.com.gv8.yeschamix.activity.LoginActivity" );
					intent.putExtra( "usuario" , usuario );

					startActivity( intent );
				}
			}
		} );
		ad.show();
		return;
	}

	public class ProgressEnvia implements Runnable {
		public void run() {
			Message msg = new Message();
			msg.what = 0;
			try {
				msg.obj = ( "Verificando..." );
				LoginActivity.progresshandler.sendMessage( msg );

				realizarLogin();

			} catch( Exception e ) {
				msg = new Message();
				msg.what = 2;
				LoginActivity.progresshandler.sendMessage( msg );
			}
		}
	}

	public void realizarLogin() throws Exception {

		usuarioVO = new UsuarioVO();
		usuarioVO.setLogin( usuario.trim());
		usuarioVO.setSenha( senha.trim() );
		String usuarioDigitado =  edtUsuario.getText().toString().toUpperCase().trim();
		String senhaDigitada =  edtSenha.getText().toString().toUpperCase().trim();
		
		YesChamixFacade facade = new YesChamixFacade( this );
		UsuarioVO usuarioBD = facade.consultarUsuarioPorLogin( usuario );
		
		if ( temInternet() ) {
			Gson gson = new Gson();
			String dadosLogin = gson.toJson( usuarioVO );

			String configuracao = ConfigService.getInstance().configurarBD(usuario, 
																			getMyPrefs().getDriveDatabase(), 
																			getMyPrefs().getDatabaseURL(),
																			getMyPrefs().getUserDatabase(), 
																			getMyPrefs().getPassDatabase());
//			String configuracao = ConfigService.getInstance().configurarBD(usuario, 
//					"com.mysql.jdbc.Driver", 
//					"jdbc:mysql://mysql.yesturbo.kinghost.net/yesturbo",
//					"yesturbo", 
//					"y35turbo");
			
			String status = UsuarioService.getInstance().autenticar( dadosLogin );
			
			if ( "OK".equals( status )&& "OK".equals(configuracao) ) {

				Message msg = new Message();
				msg.what = 1;
				LoginActivity.progresshandler.sendMessage( msg );

				if ( chkLembrarSenha.isChecked() ) {
					getMyPrefs().setSenha( senha.trim() );
				} else {
					getMyPrefs().setSenha( "" );
				}
				getMyPrefs().setLogin( usuario );
				Intent intent = new Intent( getApplicationContext() , InicialActivity.class );
				intent.setClassName( "br.com.gv8.yeschamix.activity" , "br.com.gv8.yeschamix.activity.InicialActivity" );
				getMyPrefs().setUsuarioLogado( usuario );
				getMyPrefs().setPrimeiraVez( primeiraVez );
				getMyPrefs().save();
				progressDialog.dismiss();
				startActivity( intent );
				//System.exit( 0 );

			} else if ( "BLOQUEADO".equals( status ) ) {
				Message msg = new Message();
				msg.what = 4;
				LoginActivity.progresshandler.sendMessage( msg );
				getMyPrefs().setSenha( "" );
				getMyPrefs().setLogin( "" );
				getMyPrefs().save();

			} else {
				final Dialog dialog = new Dialog( LoginActivity.this );
				dialog.setTitle( "Erro ao encontrar usuário!\nTente novamente" );
				dialog.setContentView( R.layout.menu_android_sem_internet );
				final Button ok = (Button) dialog.findViewById( R.id.botaoOkSemInternet );
				ok.setOnClickListener( new View.OnClickListener() {
					public void onClick( View v ) {

						dialog.dismiss();

					}
				} );

				dialog.show();

			}
			
		} else if ( !Utilidades.isNullOrBlank( usuarioBD.getLogin().toUpperCase().trim() ) && usuarioBD.getLogin().toUpperCase().trim().equals(usuarioDigitado ) && usuarioBD.getSenha().toUpperCase().trim().equals( senhaDigitada )) {

			if ( chkLembrarSenha.isChecked() ) {
				getMyPrefs().setSenha( senha.trim() );
			} else {
				getMyPrefs().setSenha( "" );
			}

			Intent intent = new Intent( getApplicationContext() , InicialActivity.class );
			intent.setClassName( "br.com.gv8.yeschamix.activity" , "br.com.gv8.yeschamix.activity.InicialActivity" );
			getMyPrefs().setUsuarioLogado( usuario );
			getMyPrefs().setPrimeiraVez( primeiraVez );
			getMyPrefs().save();
			progressDialog.dismiss();
			startActivity( intent );
			//System.exit( 0 );

			
		}else{
			
			progressDialog.dismiss();
			Message msg = new Message();
			msg.what = 2;
			LoginActivity.progresshandler.sendMessage( msg );
			getMyPrefs().setSenha( "" );
			getMyPrefs().save();
		}
	}

}
