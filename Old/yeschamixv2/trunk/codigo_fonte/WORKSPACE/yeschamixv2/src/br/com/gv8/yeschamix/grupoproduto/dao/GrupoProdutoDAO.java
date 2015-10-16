/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 22/05/2013 17:30:37
 * @version 1.0 
 */
package br.com.gv8.yeschamix.grupoproduto.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.com.gv8.yeschamix.abstracts.AbstractDAO;
import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;
import br.com.gv8.yeschamix.grupoproduto.model.persistence.GrupoProdutoVO;
import br.com.gv8.yeschamix.preco.dao.PrecoDAO;
import br.com.gv8.yeschamix.produto.dao.ProdutoDAO;
import br.com.gv8.yeschamix.relacaogrupoprodutofamilia.dto.RelacaoGrupoProdutoFamiliaDTO;
import br.com.gv8.yeschamix.relacaousuariopreco.dao.RelacaoUsuarioPrecoDAO;
import br.com.gv8.yeschamix.usuario.dao.UsuarioDAO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 * @since 22/05/2013 - 17:30:37
 */
public class GrupoProdutoDAO extends AbstractDAO {

	public static final String CONTEXTO_LOGICO = "GrupoProdutoDAO";

	// Nome da tabela
	public static final String NOME_TABELA = "grupo_produto";

	// Nomes das colunas da tabela.
	public static final String ID = "id";
	public static final String DESCRICAO = "descricao";
	public static final String COD_FAMILIA = "cod_familia";

	public GrupoProdutoDAO( Context context ) {
		super( context );

	}

	public long inserir( GrupoProdutoVO model ) {
		Log.i( CONTEXTO_LOGICO , "Inserindo..." );
		ContentValues values = new ContentValues();

		//if(!Utilidades.isNullOrBlank( model.getCodigo() )){
		values.put( ID , model.getId() );
		/*}else{
			values.putNull(ID);
		}*/

		if ( !Utilidades.isNullOrBlank( model.getDescricao() ) ) {
			values.put( DESCRICAO , model.getDescricao() );
		} else {
			values.putNull( DESCRICAO );
		}
		
		if ( !Utilidades.isNullOrBlank( model.getFamilia() ) ) {
			values.put( COD_FAMILIA , model.getFamilia().getId() );
		} else {
			values.putNull( COD_FAMILIA );
		}

		return db.insert( NOME_TABELA , null , values );
	}
	
	public long inserir( RelacaoGrupoProdutoFamiliaDTO model ) {
		Log.i( CONTEXTO_LOGICO , "Inserindo..." );
		ContentValues values = new ContentValues();

		//if(!Utilidades.isNullOrBlank( model.getCodigo() )){
		values.put( ID , model.getIdGrupoProduto() );
		/*}else{
			values.putNull(ID);
		}*/

		if ( !Utilidades.isNullOrBlank( model.getDescGrupoProduto() ) ) {
			values.put( DESCRICAO , model.getDescGrupoProduto() );
		} else {
			values.putNull( DESCRICAO );
		}
		
		if ( !Utilidades.isNullOrBlank( model.getIdFamilia() ) ) {
			values.put( COD_FAMILIA , model.getIdFamilia() );
		} else {
			values.putNull( COD_FAMILIA );
		}

		return db.insert( NOME_TABELA , null , values );
	}

	public long alterar( GrupoProdutoVO model ) {
		Log.i( CONTEXTO_LOGICO , "Alterando..." );
		ContentValues values = new ContentValues();

		if ( !Utilidades.isNullOrBlank( model.getDescricao() ) ) {
			values.put( DESCRICAO , model.getDescricao() );
		} else {
			values.putNull( DESCRICAO );
		}
		
		if ( !Utilidades.isNullOrBlank( model.getFamilia() ) ) {
			values.put( COD_FAMILIA , model.getFamilia().getId() );
		} else {
			values.putNull( COD_FAMILIA );
		}
		
		return db.update( NOME_TABELA , values , ID + "=?" , new String[]{ model.getId().toString() } );
	}
	
	public long alterar( RelacaoGrupoProdutoFamiliaDTO model ) {
		Log.i( CONTEXTO_LOGICO , "Alterando..." );
		ContentValues values = new ContentValues();

		if ( !Utilidades.isNullOrBlank( model.getDescGrupoProduto() ) ) {
			values.put( DESCRICAO , model.getDescGrupoProduto() );
		} else {
			values.putNull( DESCRICAO );
		}
		
		if ( !Utilidades.isNullOrBlank( model.getIdFamilia() ) ) {
			values.put( COD_FAMILIA , model.getIdFamilia() );
		} else {
			values.putNull( COD_FAMILIA );
		}
		
		return db.update( NOME_TABELA , values , ID + "=?" , new String[]{ model.getIdGrupoProduto().toString() } );
	}

	public ArrayList< GrupoProdutoVO > consultarTodos() {
		Cursor dados = null;
		try {
			dados = db.query( NOME_TABELA , new String[]{ ID , DESCRICAO , COD_FAMILIA } , null , null , null , null , null );

			ArrayList< GrupoProdutoVO > gruposProduto = new ArrayList< GrupoProdutoVO >();

			if ( dados.moveToFirst() ) {
				// Pegando os Indices das Colunas
				int indexCodigo = dados.getColumnIndex( ID );
				int indexDescricao = dados.getColumnIndex( DESCRICAO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );

				do {

					GrupoProdutoVO model = new GrupoProdutoVO( dados.getString( indexCodigo ) , dados.getString( indexDescricao ), new FamiliaVO( dados.getString( indexCodFamilia )) );

					model.setId( dados.getString( indexCodigo ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setFamilia( new FamiliaVO(dados.getString( indexCodFamilia )) );
					gruposProduto.add( model );
				} while( dados.moveToNext() );
			}

			return gruposProduto;
		} catch( SQLException e ) {
			e.printStackTrace();
		} finally {
			if ( dados != null ) {
				dados.close();
			}
		}
		return new ArrayList< GrupoProdutoVO >();
	}
	
	public ArrayList< GrupoProdutoVO > consultarTodosPorLogin(String login) {
		Cursor dados = null;
		try {
			dados = db.query(true, NOME_TABELA+" INNER JOIN "+ProdutoDAO.NOME_TABELA+
					 " INNER JOIN "+PrecoDAO.NOME_TABELA+
					 " INNER JOIN "+RelacaoUsuarioPrecoDAO.NOME_TABELA+
					 " INNER JOIN "+UsuarioDAO.NOME_TABELA, new String[]{ GrupoProdutoDAO.NOME_TABELA+"."+ID , GrupoProdutoDAO.NOME_TABELA+"."+DESCRICAO, GrupoProdutoDAO.NOME_TABELA+"."+COD_FAMILIA } ,
					 										GrupoProdutoDAO.NOME_TABELA+"."+GrupoProdutoDAO.ID+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.COD_GRUPO_PRODUTO+" AND "+
					 										PrecoDAO.NOME_TABELA+"."+PrecoDAO.COD_PRODUTO+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.ID+" AND "+
					 										RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_PRECO+" = "+PrecoDAO.NOME_TABELA+"."+PrecoDAO.ID+" AND "+
					 										UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.ID+" = "+RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_USUARIO+" AND "+
					 										UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.LOGIN+" = ? ", new String[]{login} , null , null , GrupoProdutoDAO.NOME_TABELA+"."+GrupoProdutoDAO.DESCRICAO, null );

			ArrayList< GrupoProdutoVO > gruposProduto = new ArrayList< GrupoProdutoVO >();

			if ( dados.moveToFirst() ) {
				// Pegando os Indices das Colunas
				int indexCodigo = dados.getColumnIndex( ID );
				int indexDescricao = dados.getColumnIndex( DESCRICAO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );

				do {

					GrupoProdutoVO model = new GrupoProdutoVO( dados.getString( indexCodigo ) , dados.getString( indexDescricao ), new FamiliaVO( dados.getString( indexCodFamilia )) );

					model.setId( dados.getString( indexCodigo ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setFamilia( new FamiliaVO(dados.getString( indexCodFamilia )) );
					gruposProduto.add( model );
				} while( dados.moveToNext() );
			}

			return gruposProduto;
		} catch( SQLException e ) {
			e.printStackTrace();
		} finally {
			if ( dados != null ) {
				dados.close();
			}
		}
		return new ArrayList< GrupoProdutoVO >();
	}


	public GrupoProdutoVO consultarPorCodigoGrupoProduto( String idGrupoProduto ) {
		Cursor dados = null;
		try {

			dados = db.query( NOME_TABELA , new String[]{ ID , DESCRICAO, COD_FAMILIA } , GrupoProdutoDAO.ID + " = ? " , new String[]{idGrupoProduto} , null , null , null );

			
			GrupoProdutoVO model = new GrupoProdutoVO();
			if ( dados.moveToFirst() ) {
				// Pegando os Indices das Colunas
				int indexCodigo = dados.getColumnIndex( ID );
				int indexDescricao = dados.getColumnIndex( DESCRICAO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );

				do {

					model = new GrupoProdutoVO( dados.getString( indexCodigo ) , dados.getString( indexDescricao ) , new FamiliaVO( dados.getString( indexCodFamilia ) ) );

					model.setId( dados.getString( indexCodigo ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setFamilia( new FamiliaVO( dados.getString( indexCodFamilia ) ) );

					
				} while( dados.moveToNext() );
			}

			return model;
		} catch( SQLException e ) {
			e.printStackTrace();
		} finally {
			if ( dados != null ) {
				dados.close();
			}
		}
		return new GrupoProdutoVO();
	}
	
	public ArrayList< GrupoProdutoVO > consultarGrupoProdutoFamilia( FamiliaVO familia ) {
		Cursor dados = null;
		try {

			dados = db.query( NOME_TABELA , new String[]{ ID , DESCRICAO, COD_FAMILIA } , GrupoProdutoDAO.COD_FAMILIA + " LIKE ? " , new String[]{familia.getId()} , null , null , GrupoProdutoDAO.DESCRICAO );

			ArrayList< GrupoProdutoVO > gruposProduto = new ArrayList< GrupoProdutoVO >();

			if ( dados.moveToFirst() ) {
				// Pegando os Indices das Colunas
				int indexCodigo = dados.getColumnIndex( ID );
				int indexDescricao = dados.getColumnIndex( DESCRICAO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );

				do {

					GrupoProdutoVO model = new GrupoProdutoVO( dados.getString( indexCodigo ) , dados.getString( indexDescricao ) , new FamiliaVO( dados.getString( indexCodFamilia ) ) );

					model.setId( dados.getString( indexCodigo ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setFamilia( new FamiliaVO( dados.getString( indexCodFamilia ) ) );

					gruposProduto.add( model );
				} while( dados.moveToNext() );
			}

			return gruposProduto;
		} catch( SQLException e ) {
			e.printStackTrace();
		} finally {
			if ( dados != null ) {
				dados.close();
			}
		}
		return new ArrayList< GrupoProdutoVO >();
	}
	
	public ArrayList< GrupoProdutoVO > consultarGrupoProdutoFamiliaLogin( FamiliaVO familia, String login ) {
		Cursor dados = null;
		try {

			dados = db.query(true, NOME_TABELA+" INNER JOIN "+ProdutoDAO.NOME_TABELA+
					 " INNER JOIN "+PrecoDAO.NOME_TABELA+
					 " INNER JOIN "+RelacaoUsuarioPrecoDAO.NOME_TABELA+
					 " INNER JOIN "+UsuarioDAO.NOME_TABELA, new String[]{ GrupoProdutoDAO.NOME_TABELA+"."+ID , GrupoProdutoDAO.NOME_TABELA+"."+DESCRICAO, GrupoProdutoDAO.NOME_TABELA+"."+COD_FAMILIA } ,
					 										GrupoProdutoDAO.NOME_TABELA+"."+GrupoProdutoDAO.ID+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.COD_GRUPO_PRODUTO+" AND "+
					 										PrecoDAO.NOME_TABELA+"."+PrecoDAO.COD_PRODUTO+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.ID+" AND "+
					 										RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_PRECO+" = "+PrecoDAO.NOME_TABELA+"."+PrecoDAO.ID+" AND "+
					 										UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.ID+" = "+RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_USUARIO+" AND "+
					 										GrupoProdutoDAO.NOME_TABELA+"."+GrupoProdutoDAO.COD_FAMILIA + " LIKE ? AND "+
					 										UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.LOGIN+" = ? ", new String[]{familia.getId(), login} , null , null , GrupoProdutoDAO.NOME_TABELA+"."+GrupoProdutoDAO.DESCRICAO, null );

			ArrayList< GrupoProdutoVO > gruposProduto = new ArrayList< GrupoProdutoVO >();

			if ( dados.moveToFirst() ) {
				// Pegando os Indices das Colunas
				int indexCodigo = dados.getColumnIndex( ID );
				int indexDescricao = dados.getColumnIndex( DESCRICAO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );

				do {

					GrupoProdutoVO model = new GrupoProdutoVO( dados.getString( indexCodigo ) , dados.getString( indexDescricao ) , new FamiliaVO( dados.getString( indexCodFamilia ) ) );

					model.setId( dados.getString( indexCodigo ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setFamilia( new FamiliaVO( dados.getString( indexCodFamilia ) ) );

					gruposProduto.add( model );
				} while( dados.moveToNext() );
			}

			return gruposProduto;
		} catch( SQLException e ) {
			e.printStackTrace();
		} finally {
			if ( dados != null ) {
				dados.close();
			}
		}
		return new ArrayList< GrupoProdutoVO >();
	}
	
	
	public ArrayList< GrupoProdutoVO > consultarTudoGrupoProdutoLogin(String login ) {
		Cursor dados = null;
		try {

			dados = db.query(true, NOME_TABELA+" INNER JOIN "+ProdutoDAO.NOME_TABELA+
					 " INNER JOIN "+PrecoDAO.NOME_TABELA+
					 " INNER JOIN "+RelacaoUsuarioPrecoDAO.NOME_TABELA+
					 " INNER JOIN "+UsuarioDAO.NOME_TABELA, new String[]{ GrupoProdutoDAO.NOME_TABELA+"."+ID , GrupoProdutoDAO.NOME_TABELA+"."+DESCRICAO, GrupoProdutoDAO.NOME_TABELA+"."+COD_FAMILIA } ,
					 										GrupoProdutoDAO.NOME_TABELA+"."+GrupoProdutoDAO.ID+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.COD_GRUPO_PRODUTO+" AND "+
					 										PrecoDAO.NOME_TABELA+"."+PrecoDAO.COD_PRODUTO+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.ID+" AND "+
					 										RelacaoUsuarioPrecoDAO.COD_PRECO+" = "+PrecoDAO.NOME_TABELA+"."+PrecoDAO.ID+" AND "+
					 										UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.ID+" = "+RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_USUARIO+" AND "+
					 										UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.LOGIN+" = ? ", new String[]{login} , null , null , GrupoProdutoDAO.NOME_TABELA+"."+GrupoProdutoDAO.DESCRICAO, null );

			ArrayList< GrupoProdutoVO > gruposProduto = new ArrayList< GrupoProdutoVO >();

			if ( dados.moveToFirst() ) {
				// Pegando os Indices das Colunas
				int indexCodigo = dados.getColumnIndex( ID );
				int indexDescricao = dados.getColumnIndex( DESCRICAO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );

				do {

					GrupoProdutoVO model = new GrupoProdutoVO( dados.getString( indexCodigo ) , dados.getString( indexDescricao ) , new FamiliaVO( dados.getString( indexCodFamilia ) ) );

					model.setId( dados.getString( indexCodigo ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setFamilia( new FamiliaVO( dados.getString( indexCodFamilia ) ) );

					gruposProduto.add( model );
				} while( dados.moveToNext() );
			}

			return gruposProduto;
		} catch( SQLException e ) {
			e.printStackTrace();
		} finally {
			if ( dados != null ) {
				dados.close();
			}
		}
		return new ArrayList< GrupoProdutoVO >();
	}
	
	
	
	public GrupoProdutoVO consultarGrupoProdutoPorIdGrupoProdutoIdFamilia(String idGrupo, String idFamilia ) {
		Cursor dados = null;
		try {

			dados = db.query( NOME_TABELA , new String[]{ ID , DESCRICAO, COD_FAMILIA } , GrupoProdutoDAO.ID + " LIKE ? AND "+GrupoProdutoDAO.COD_FAMILIA+" LIKE ? " , new String[]{idGrupo, idFamilia} , null , null , GrupoProdutoDAO.ID );

			GrupoProdutoVO model = new GrupoProdutoVO();

			if ( dados.moveToFirst() ) {
				// Pegando os Indices das Colunas
				int indexCodigo = dados.getColumnIndex( ID );
				int indexDescricao = dados.getColumnIndex( DESCRICAO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );

				do {

					model = new GrupoProdutoVO( dados.getString( indexCodigo ) , dados.getString( indexDescricao ) , new FamiliaVO( dados.getString( indexCodFamilia ) ) );

					model.setId( dados.getString( indexCodigo ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setFamilia( new FamiliaVO( dados.getString( indexCodFamilia ) ) );

				} while( dados.moveToNext() );
			}

			return model;
		} catch( SQLException e ) {
			e.printStackTrace();
		} finally {
			if ( dados != null ) {
				dados.close();
			}
		}
		return new GrupoProdutoVO();
	}

}
