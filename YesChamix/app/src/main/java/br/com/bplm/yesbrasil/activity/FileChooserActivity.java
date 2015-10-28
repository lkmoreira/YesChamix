package br.com.bplm.yesbrasil.activity;

import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.EActivity;


@EActivity
public class FileChooserActivity extends ListActivity{

	/*private File currentDir;
	private FileArrayAdapter adapter;
	private File file;
	private Preferences preferences;
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		dialogo( "Configura��o do Xml", "Por favor, selecione o arquivo de configura��o." );
		file = new File( "" );
		preferences = new Preferences( this );
		currentDir = new File( Environment.getExternalStorageDirectory().getPath() );
		fill( currentDir );
	}

	private void fill( File f ) {
		File[ ] dirs = f.listFiles();
		this.setTitle( "Current Dir: " + f.getName() );
		List< Option > dir = new ArrayList< Option >();
		List< Option > fls = new ArrayList< Option >();
		try {
			for ( File ff : dirs ) {
				if ( ff.isDirectory() )
					dir.add( new Option( ff.getName(), "Pasta", ff.getAbsolutePath() ) );
				else {
					fls.add( new Option( ff.getName(), "Tamanho: " + ff.length(), ff.getAbsolutePath() ) );
				}
			}
		} catch ( Exception e ) {

		}
		Collections.sort( dir );
		Collections.sort( fls );
		dir.addAll( fls );
		if ( !f.getName().equalsIgnoreCase( "sdcard" ) )
			dir.add( 0, new Option( "..", "Parent Directory", f.getParent() ) );
		adapter = new FileArrayAdapter( FileChooserActivity.this, R.layout.file_view, dir );
		this.setListAdapter( adapter );
	}

	@Override
	protected void onListItemClick( ListView l, View v, int position, long id ) {
		super.onListItemClick( l, v, position, id );
		Option o = adapter.getItem( position );
		if ( o.getData().equalsIgnoreCase( "pasta" ) || o.getData().equalsIgnoreCase( "parent directory" ) ) {
			currentDir = new File( o.getPath() );
			fill( currentDir );
		} else {
			onFileClick( o );
		}
	}

	private void onFileClick( Option o ) {
		try {
			file = new File( o.getPath() );
			if ( !file.getName().endsWith( ".xml" ) ) {
				Toast.makeText( this, "xml", Toast.LENGTH_SHORT ).show();
				throw new Exception( "Arquivo de configura��o inv�lido." );
			}
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( new FileInputStream( file ) ) );

			String linha;
			StringBuilder buffer = new StringBuilder();
			while ( ( linha = bufferedReader.readLine() ) != null )
				buffer.append( linha );
			bufferedReader.close();
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilderFactory.setCoalescing( true );
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse( new ByteArrayInputStream( buffer.toString().trim().getBytes( "UTF8" ) ) );

			// normalize text representation
			doc.getDocumentElement().normalize();
			NodeList items = doc.getElementsByTagName( "configuracao" );
			if ( Utilidades.isNullOrBlank( items ) ) {
				Toast.makeText( this, "configuracao", Toast.LENGTH_SHORT ).show();
				throw new Exception( "Arquivo de configura��o inv�lido." );
			}
			// -----
			SecretKeySpec skeySpec = new SecretKeySpec( Utilidades.toHex( Preferences.DECRYPT_KEY ), "AES" );
			Cipher cipher = Cipher.getInstance( "AES" );
			cipher.init( Cipher.DECRYPT_MODE, skeySpec );
			// ---
			
			for ( int i = 0; i < items.getLength(); i++ ) {
				Node firstNode = items.item( i );
				if ( firstNode.getNodeType() == Node.ELEMENT_NODE ) {
					Element firstPersonElement = (Element) firstNode;
					// -------
					NodeList dbNode = firstPersonElement.getElementsByTagName( "databaseURL" );
					if ( Utilidades.isNullOrBlank( dbNode ) ) {
						Toast.makeText( this, "databaseURL", Toast.LENGTH_SHORT ).show();
						throw new Exception( "Arquivo de configura��o inv�lido." );
					}
					Element dbElement = (Element) dbNode.item( 0 );
					NodeList dbList = dbElement.getChildNodes();
					String dbDecrypt = ( (Node) dbList.item( 0 ) ).getNodeValue().trim();
					byte[ ] original = cipher.doFinal( Utilidades.toHex( dbDecrypt ) );
					preferences.setDatabaseURL( new String( original ) );

					NodeList userDBNode = firstPersonElement.getElementsByTagName( "userDatabase" );
					if ( Utilidades.isNullOrBlank( userDBNode ) ) {
						Toast.makeText( this, "userDatabase", Toast.LENGTH_SHORT ).show();
						throw new Exception( "Arquivo de configura��o inv�lido." );
					}
					Element userDBElement = (Element) userDBNode.item( 0 );
					NodeList userDBList = userDBElement.getChildNodes();
					String userDBDecrypt = ( (Node) userDBList.item( 0 ) ).getNodeValue().trim();
					original = cipher.doFinal( Utilidades.toHex( userDBDecrypt ) );
					preferences.setUserDatabase( new String( original ) );

					NodeList passDBNode = firstPersonElement.getElementsByTagName( "passDatabase" );
					if ( Utilidades.isNullOrBlank( passDBNode ) ) {
						Toast.makeText( this, "passDatabase", Toast.LENGTH_SHORT ).show();
						throw new Exception( "Arquivo de configura��o inv�lido." );
					}
					Element passDBElement = (Element) passDBNode.item( 0 );
					NodeList passDBList = passDBElement.getChildNodes();
					String passDBDecrypt = ( (Node) passDBList.item( 0 ) ).getNodeValue().trim();
					original = cipher.doFinal( Utilidades.toHex( passDBDecrypt ) );
					preferences.setPassDatabase( new String( original ) );

					NodeList driveDBNode = firstPersonElement.getElementsByTagName( "driveDatabase" );
					if ( Utilidades.isNullOrBlank( driveDBNode ) ) {
						Toast.makeText( this, "driveDatabase", Toast.LENGTH_SHORT ).show();
						throw new Exception( "Arquivo de configura��o inv�lido." );
					}
					Element driveDBElement = (Element) driveDBNode.item( 0 );
					NodeList driveDBList = driveDBElement.getChildNodes();
					String driveDBDecrypt = ( (Node) driveDBList.item( 0 ) ).getNodeValue().trim();
					original = cipher.doFinal( Utilidades.toHex( driveDBDecrypt ) );
					preferences.setDriveDatabase( new String( original ) );

//					NodeList caminhoFTPNode = firstPersonElement.getElementsByTagName( "caminhoFTP" );
//					if ( Utilidades.isNullOrBlank( caminhoFTPNode ) ) {
//						Toast.makeText( this, "caminhoFTP", Toast.LENGTH_SHORT ).show();
//						throw new Exception( "Arquivo de configura��o inv�lido." );
//					}
//					Element caminhoFTPElement = (Element) caminhoFTPNode.item( 0 );
//					NodeList caminhoFTPList = caminhoFTPElement.getChildNodes();
//					String caminhoFTPDecrypt = ( (Node) caminhoFTPList.item( 0 ) ).getNodeValue().trim();
//					original = cipher.doFinal( Utilidades.toHex( caminhoFTPDecrypt ) );
//					preferences.setCaminhoFTP( new String( original ) );
//
//					NodeList userFTPNode = firstPersonElement.getElementsByTagName( "userFTP" );
//					if ( Utilidades.isNullOrBlank( userFTPNode ) ) {
//						Toast.makeText( this, "userFTP", Toast.LENGTH_SHORT ).show();
//						throw new Exception( "Arquivo de configura��o inv�lido." );
//					}
//					Element userFTPElement = (Element) userFTPNode.item( 0 );
//					NodeList userFTPList = userFTPElement.getChildNodes();
//					String userFTPDecrypt = ( (Node) userFTPList.item( 0 ) ).getNodeValue().trim();
//					original = cipher.doFinal( Utilidades.toHex( userFTPDecrypt ) );
//					preferences.setUserFTP( new String( original ) );
//
//					NodeList passFTPNode = firstPersonElement.getElementsByTagName( "passFTP" );
//					if ( Utilidades.isNullOrBlank( passFTPNode ) ) {
//						Toast.makeText( this, "passFTP", Toast.LENGTH_SHORT ).show();
//						throw new Exception( "Arquivo de configura��o inv�lido." );
//					}
//					Element passFTPElement = (Element) passFTPNode.item( 0 );
//					NodeList passFTPList = passFTPElement.getChildNodes();
//					String passFTPDecrypt = ( (Node) passFTPList.item( 0 ) ).getNodeValue().trim();
//					original = cipher.doFinal( Utilidades.toHex( passFTPDecrypt ) );
//					preferences.setPassFTP( new String( original ) );
//
//					NodeList pastaPrincipalFTPNode = firstPersonElement.getElementsByTagName( "pastaPrincipalFTP" );
//					if ( Utilidades.isNullOrBlank( pastaPrincipalFTPNode ) ) {
//						Toast.makeText( this, "pastaPrincipalFTP", Toast.LENGTH_SHORT ).show();
//						throw new Exception( "Arquivo de configura��o inv�lido." );
//					}
//					Element pastaPrincipalFTPElement = (Element) pastaPrincipalFTPNode.item( 0 );
//					NodeList pastaPrincipalFTPList = pastaPrincipalFTPElement.getChildNodes();
//					String pastaPrincipalFTPDecrypt = ( (Node) pastaPrincipalFTPList.item( 0 ) ).getNodeValue().trim();
//					original = cipher.doFinal( Utilidades.toHex( pastaPrincipalFTPDecrypt ) );
//					preferences.setPastaPrincipalFTP( new String( original ) );
//
//					NodeList pastaProdutoNode = firstPersonElement.getElementsByTagName( "pastaProdutoFTP" );
//					if ( Utilidades.isNullOrBlank( pastaProdutoNode ) ) {
//						Toast.makeText( this, "pastaProdutoFTP", Toast.LENGTH_SHORT ).show();
//						throw new Exception( "Arquivo de configura��o inv�lido." );
//					}
//					Element pastaProdutoElement = (Element) pastaProdutoNode.item( 0 );
//					NodeList pastaProdutoList = pastaProdutoElement.getChildNodes();
//					String pastaProdutoFTPDecrypt = ( (Node) pastaProdutoList.item( 0 ) ).getNodeValue().trim();
//					original = cipher.doFinal( Utilidades.toHex( pastaProdutoFTPDecrypt ) );
//					preferences.setPastaProduto( new String( original ) );
//
//					NodeList pastaFamiliaNode = firstPersonElement.getElementsByTagName( "pastaFamiliaFTP" );
//					if ( Utilidades.isNullOrBlank( pastaFamiliaNode ) ) {
//						Toast.makeText( this, "pastaFamiliaFTP", Toast.LENGTH_SHORT ).show();
//						throw new Exception( "Arquivo de configura��o inv�lido." );
//					}
//					Element pastaFamiliaElement = (Element) pastaFamiliaNode.item( 0 );
//					NodeList pastaFamiliaList = pastaFamiliaElement.getChildNodes();
//					String pastaFamiliaFTPDecrypt = ( (Node) pastaFamiliaList.item( 0 ) ).getNodeValue().trim();
//					original = cipher.doFinal( Utilidades.toHex( pastaFamiliaFTPDecrypt ) );
//					preferences.setPastaFamilia( new String( original ) );
//
//					NodeList pastaFoldersNode = firstPersonElement.getElementsByTagName( "pastaFoldersFTP" );
//					if ( Utilidades.isNullOrBlank( pastaFoldersNode ) ) {
//						Toast.makeText( this, "pastaFoldersFTP", Toast.LENGTH_SHORT ).show();
//						throw new Exception( "Arquivo de configura��o inv�lido." );
//					}
//					Element pastaFoldersElement = (Element) pastaFoldersNode.item( 0 );
//					NodeList pastaFoldersList = pastaFoldersElement.getChildNodes();
//					String pastaFoldesFTPDecrypt = ( (Node) pastaFoldersList.item( 0 ) ).getNodeValue().trim();
//					original = cipher.doFinal( Utilidades.toHex( pastaFoldesFTPDecrypt ) );
//					preferences.setPastaFolders( new String( original ) );
//
//					NodeList caminhoPastaNode = firstPersonElement.getElementsByTagName( "urlCaminho" );
//					if ( Utilidades.isNullOrBlank( caminhoPastaNode ) ) {
//						Toast.makeText( this, "urlCaminho", Toast.LENGTH_SHORT ).show();
//						throw new Exception( "Arquivo de configura��o inv�lido." );
//					}
//					Element caminhoPastaElement = (Element) caminhoPastaNode.item( 0 );
//					NodeList caminhoPastaList = caminhoPastaElement.getChildNodes();
//					String caminhoPastaDecrypt = ( (Node) caminhoPastaList.item( 0 ) ).getNodeValue().trim();
//					original = cipher.doFinal( Utilidades.toHex( caminhoPastaDecrypt ) );
//					preferences.setCaminhoPasta( new String( original ) );

				}
			}
			preferences.isBancoConfigurado( true );	
			preferences.save();		
			Toast.makeText( this, "Yes Turbo configurado com sucesso. Fa�a a sincroniza��o.", Toast.LENGTH_SHORT ).show();
		} catch ( Exception e ) {
			Toast.makeText( this, "Problema no arquivo - " + e.getMessage(), Toast.LENGTH_SHORT ).show();
		}
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

	
	public void dialogo(String titulo, String mensagem ) {
		final Dialog dialog = new Dialog( this );
		dialog.setTitle( titulo );
		dialog.setContentView( R.layout.menu_android_sem_internet );
		final TextView msg = (TextView) dialog.findViewById( R.id.msg );
		msg.setText( mensagem );
		final Button ok = (Button) dialog.findViewById( R.id.botaoOkSemInternet );
		ok.setOnClickListener( new View.OnClickListener(){
			public void onClick( View v ) {
				dialog.dismiss();
			}
		} );
		dialog.show();// mostra o dialog
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
		if ( !file.getName().endsWith( ".xml" )||!preferences.isBancoConfigurado()) {
			Toast.makeText( this, "Selecione um arquivo xml", Toast.LENGTH_LONG).show();
		}else{
			startActivity( new Intent( getApplication(), LoginActivity.class ) );
		}
	}*/

	public boolean MenuChoice( MenuItem item ) {
		switch ( item.getItemId() ) {
			/*case 0:
				Intent intent = new Intent(this, FamiliasActivity.class);
				this.startActivity(intent);
				return true;
			case 1:
				startActivity( new Intent( getApplication(), FileChooserActivity.class ) );
				return true;*/
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		super.onCreateOptionsMenu(menu);
		//CreateMenu( menu );
		return true;
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		return MenuChoice( item );
	}
	
	@Override
	public void finish() {
		finish();
	}
}