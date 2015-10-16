/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 22/05/2013 16:32:34
 * @version 1.0 
 */
package br.com.gv8.yeschamix.classificacao.dao;

import java.util.ArrayList;
import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.com.gv8.yeschamix.abstracts.AbstractDAO;
import br.com.gv8.yeschamix.classificacao.model.persistence.ClassificacaoVO;
import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;
import br.com.gv8.yeschamix.grupoproduto.model.persistence.GrupoProdutoVO;
import br.com.gv8.yeschamix.preco.dao.PrecoDAO;
import br.com.gv8.yeschamix.produto.dao.ProdutoDAO;
import br.com.gv8.yeschamix.relacaogrupoprodutoclassificacao.dto.RelacaoGrupoProdutoClassificacaoDTO;
import br.com.gv8.yeschamix.relacaousuariopreco.dao.RelacaoUsuarioPrecoDAO;
import br.com.gv8.yeschamix.usuario.dao.UsuarioDAO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 * @since 22/05/2013 - 16:32:34
 */
public class ClassificacaoDAO extends AbstractDAO {
	public static final String CONTEXTO_LOGICO = "ClassificacaoDAO";

	// Nome da tabela
	public static final String NOME_TABELA = "classificacao";

	// Nomes das colunas da tabela.
	public static final String ID = "id";	
	public static final String DESCRICAO = "descricao";
	public static final String COD_GRUPO_PRODUTO = "cod_grupo_produto";
	public static final String COD_FAMILIA = "cod_familia";
	


	public ClassificacaoDAO(Context context) {
		super(context);	
	}

	public long inserir(ClassificacaoVO model){
		Log.i(CONTEXTO_LOGICO, "Inserindo...");
		ContentValues values = new ContentValues();


		//if(!Utilidades.isNullOrBlank( model.getCodigo() )){
		values.put(ID, model.getId());
		/*}else{
			values.putNull(ID);
		}*/

		if(!Utilidades.isNullOrBlank( model.getDescricao() )){
			values.put(DESCRICAO, model.getDescricao());
		}else{
			values.putNull(DESCRICAO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getGrupoProduto() )){
			values.put(COD_GRUPO_PRODUTO, model.getGrupoProduto().getId());
		}else{
			values.putNull(COD_GRUPO_PRODUTO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getFamilia() )){
			values.put(COD_FAMILIA, model.getFamilia().getId());
		}else{
			values.putNull(COD_FAMILIA);
		}
		


		return db.insert(NOME_TABELA, null, values);
	}
	
	public long inserir(RelacaoGrupoProdutoClassificacaoDTO model){
		Log.i(CONTEXTO_LOGICO, "Inserindo...");
		ContentValues values = new ContentValues();


		//if(!Utilidades.isNullOrBlank( model.getCodigo() )){
		values.put(ID, model.getIdClassificacao());
		/*}else{
			values.putNull(ID);
		}*/

		if(!Utilidades.isNullOrBlank( model.getDescClassificacao() )){
			values.put(DESCRICAO, model.getDescClassificacao());
		}else{
			values.putNull(DESCRICAO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getIdGrupoProduto() )){
			values.put(COD_GRUPO_PRODUTO, model.getIdGrupoProduto());
		}else{
			values.putNull(COD_GRUPO_PRODUTO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getIdFamilia() )){
			values.put(COD_FAMILIA, model.getIdFamilia());
		}else{
			values.putNull(COD_FAMILIA);
		}
		


		return db.insert(NOME_TABELA, null, values);
	}

	public long alterar(ClassificacaoVO model){
		Log.i(CONTEXTO_LOGICO, "Alterando...");
		ContentValues values = new ContentValues();
		
		if(!Utilidades.isNullOrBlank( model.getDescricao() )){
			values.put(DESCRICAO, model.getDescricao());
		}else{
			values.putNull(DESCRICAO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getGrupoProduto() )){
			values.put(COD_GRUPO_PRODUTO, model.getGrupoProduto().getId());
		}else{
			values.putNull(COD_GRUPO_PRODUTO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getFamilia() )){
			values.put(COD_FAMILIA, model.getFamilia().getId());
		}else{
			values.putNull(COD_FAMILIA);
		}

		return db.update(NOME_TABELA, values, ID+"=?", new String[]{model.getId().toString()});
	}
	
	public long alterar(RelacaoGrupoProdutoClassificacaoDTO model){
		Log.i(CONTEXTO_LOGICO, "Alterando...");
		ContentValues values = new ContentValues();
		
		if(!Utilidades.isNullOrBlank( model.getDescClassificacao() )){
			values.put(DESCRICAO, model.getDescClassificacao());
		}else{
			values.putNull(DESCRICAO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getIdGrupoProduto() )){
			values.put(COD_GRUPO_PRODUTO, model.getIdGrupoProduto());
		}else{
			values.putNull(COD_GRUPO_PRODUTO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getIdFamilia() )){
			values.put(COD_FAMILIA, model.getIdFamilia());
		}else{
			values.putNull(COD_FAMILIA);
		}

		return db.update(NOME_TABELA, values, ID+"=?", new String[]{model.getIdClassificacao().toString()});
	}

	public ArrayList<ClassificacaoVO> consultarTodos(){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{ID, DESCRICAO, COD_GRUPO_PRODUTO, COD_FAMILIA}, null, null, null, null, null);

			ArrayList<ClassificacaoVO> classificacoes = new ArrayList<ClassificacaoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexCodGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );
				
				do{		

					ClassificacaoVO model = new ClassificacaoVO(dados.getString(indexCodigo),dados.getString(indexDescricao), new GrupoProdutoVO( dados.getString( indexCodGrupoProduto ) ), new FamiliaVO( dados.getString( indexCodFamilia ) ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDescricao( dados.getString(indexDescricao) );
					model.setGrupoProduto( new GrupoProdutoVO(dados.getString( indexCodGrupoProduto ) ));
					model.setFamilia( new FamiliaVO(dados.getString( indexCodFamilia )) );
					

					classificacoes.add(model);
				}while(dados.moveToNext());
			}

			return classificacoes;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ClassificacaoVO>();
	}

	public ClassificacaoVO consultarPorCodigoClassificacao(String idClassificacao){
		Cursor dados = null;
		try{

			dados = db.query(NOME_TABELA, new String[]{ID, DESCRICAO, COD_FAMILIA, COD_GRUPO_PRODUTO},ClassificacaoDAO.ID+ " = ? ", new String[]{idClassificacao}, null, null, null);

			ClassificacaoVO model = new ClassificacaoVO();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexCodGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );
				

				do{		

					model = new ClassificacaoVO(dados.getString(indexCodigo),dados.getString(indexDescricao), new GrupoProdutoVO( dados.getString( indexCodGrupoProduto ) ), new FamiliaVO( dados.getString( indexCodFamilia ) ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDescricao( dados.getString(indexDescricao) );
					model.setGrupoProduto( new GrupoProdutoVO(dados.getString( indexCodGrupoProduto ) ));
					model.setFamilia( new FamiliaVO(dados.getString( indexCodFamilia )) );

					
				}while(dados.moveToNext());
			}

			return model;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ClassificacaoVO();
	}
	
	public ArrayList<ClassificacaoVO> consultarClassificacaoPorGrupoProdutoFamilia(GrupoProdutoVO grupoProduto,FamiliaVO familia){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{ID, DESCRICAO, COD_GRUPO_PRODUTO, COD_FAMILIA},ClassificacaoDAO.COD_GRUPO_PRODUTO+ " LIKE ? AND "+ClassificacaoDAO.COD_FAMILIA+" LIKE ? ", new String[]{grupoProduto.getId(), familia.getId()}, null, null, ClassificacaoDAO.DESCRICAO);

			ArrayList<ClassificacaoVO> classificacoes = new ArrayList<ClassificacaoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexDescricao =  dados.getColumnIndex(DESCRICAO); 
				int indexCodGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );

				do{		

					ClassificacaoVO model = new ClassificacaoVO(dados.getString(indexCodigo),dados.getString(indexDescricao), new GrupoProdutoVO( dados.getString( indexCodGrupoProduto ) ), new FamiliaVO( dados.getString( indexCodFamilia ) ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDescricao( dados.getString(indexDescricao) );
					model.setGrupoProduto( new GrupoProdutoVO(dados.getString( indexCodGrupoProduto ) ));
					model.setFamilia( new FamiliaVO(dados.getString( indexCodFamilia )) );
					

					classificacoes.add(model);
				}while(dados.moveToNext());
			}

			return classificacoes;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ClassificacaoVO>();
	}
	
	
	public ArrayList<ClassificacaoVO> consultarClassificacaoPorGrupoProdutoFamiliaLogin(GrupoProdutoVO grupoProduto,FamiliaVO familia, String login){
		Cursor dados = null;
		try{
			
			//Fazendo as junções com as tabelas necessárias
			StringBuilder tabelas = new StringBuilder();
			tabelas.append( ClassificacaoDAO.NOME_TABELA );
			tabelas.append( " INNER JOIN " );
			tabelas.append( ProdutoDAO.NOME_TABELA );
			tabelas.append( " INNER JOIN " );
			tabelas.append( PrecoDAO.NOME_TABELA );
			tabelas.append( " INNER JOIN " );
			tabelas.append( RelacaoUsuarioPrecoDAO.NOME_TABELA );
			tabelas.append( " INNER JOIN " );
			tabelas.append( UsuarioDAO.NOME_TABELA );
			
			//Projetando as colunas desejadas
			String[] colunas;
			Vector<String> vetor = new Vector<String>();
			vetor.add( ClassificacaoDAO.NOME_TABELA+"."+ID );
			vetor.add( ClassificacaoDAO.NOME_TABELA+"."+DESCRICAO );
			vetor.add( ClassificacaoDAO.NOME_TABELA+"."+COD_GRUPO_PRODUTO );
			vetor.add( ClassificacaoDAO.NOME_TABELA+"."+COD_FAMILIA );
			colunas = vetor.toArray(new String[vetor.size()]);
			
			//Preparando as condições (amarração logica)
			StringBuilder condicoes = new StringBuilder();
			condicoes.append( ClassificacaoDAO.NOME_TABELA+"."+ClassificacaoDAO.ID);
			condicoes.append( " = ");
			condicoes.append( ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.COD_CLASSIFICACAO);
			condicoes.append( " AND ");
			condicoes.append(PrecoDAO.NOME_TABELA+"."+PrecoDAO.COD_PRODUTO);
			condicoes.append(" = ");
			condicoes.append(ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.ID);
			condicoes.append(" AND ");
			condicoes.append(RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_PRECO);
			condicoes.append(" = ");
			condicoes.append(PrecoDAO.NOME_TABELA+"."+PrecoDAO.ID);
			condicoes.append(" AND ");
			condicoes.append(UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.ID);
			condicoes.append(" = ");
			condicoes.append(RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_USUARIO);
			condicoes.append(" AND ");
			condicoes.append(ClassificacaoDAO.NOME_TABELA+"."+ClassificacaoDAO.COD_GRUPO_PRODUTO);
			condicoes.append(" LIKE ? AND ");
			condicoes.append(ClassificacaoDAO.NOME_TABELA+"."+ClassificacaoDAO.COD_FAMILIA);
			condicoes.append(" LIKE ? AND ");
			condicoes.append(UsuarioDAO.LOGIN);
			condicoes.append(" = ? ");
			
			
			/**
			 * query( distinct , table , columns , selection , selectionArgs , groupBy , having , orderBy , limit )
			 */
			
			dados = db.query(true, tabelas.toString(), colunas,	condicoes.toString(), new String[]{grupoProduto.getId(), familia.getId(), login}, null, null, ClassificacaoDAO.NOME_TABELA+"."+ClassificacaoDAO.DESCRICAO, null);

			ArrayList<ClassificacaoVO> classificacoes = new ArrayList<ClassificacaoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexDescricao =  dados.getColumnIndex(DESCRICAO); 
				int indexCodGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );

				do{		

					ClassificacaoVO model = new ClassificacaoVO(dados.getString(indexCodigo),dados.getString(indexDescricao), new GrupoProdutoVO( dados.getString( indexCodGrupoProduto ) ), new FamiliaVO( dados.getString( indexCodFamilia ) ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDescricao( dados.getString(indexDescricao) );
					model.setGrupoProduto( new GrupoProdutoVO(dados.getString( indexCodGrupoProduto ) ));
					model.setFamilia( new FamiliaVO(dados.getString( indexCodFamilia )) );
					

					classificacoes.add(model);
				}while(dados.moveToNext());
			}

			return classificacoes;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ClassificacaoVO>();
	}
	
	public ClassificacaoVO consultarClassificacao(String idClassificacao, String idGrupoProduto,String idFamilia){
		Cursor dados = null;
		try{

			dados = db.query(NOME_TABELA, new String[]{ID, DESCRICAO, COD_FAMILIA, COD_GRUPO_PRODUTO},ClassificacaoDAO.ID+ " LIKE ? AND "+ClassificacaoDAO.COD_GRUPO_PRODUTO+" LIKE ? AND "+ClassificacaoDAO.COD_FAMILIA+" LIKE ? ", new String[]{idClassificacao, idGrupoProduto, idFamilia}, null, null, ClassificacaoDAO.DESCRICAO);

			ClassificacaoVO model= new ClassificacaoVO();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexCodGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );
				

				do{		

					model = new ClassificacaoVO(dados.getString(indexCodigo),dados.getString(indexDescricao), new GrupoProdutoVO( dados.getString( indexCodGrupoProduto ) ), new FamiliaVO( dados.getString( indexCodFamilia ) ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDescricao( dados.getString(indexDescricao) );
					model.setGrupoProduto( new GrupoProdutoVO(dados.getString( indexCodGrupoProduto ) ));
					model.setFamilia( new FamiliaVO(dados.getString( indexCodFamilia )) );

					
				}while(dados.moveToNext());
			}

			return model;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ClassificacaoVO();
	}
	
	public ClassificacaoVO consultarClassificacao(String idClassificacao, String idGrupoProduto,String idFamilia, String login){
		Cursor dados = null;
		try{
			
			dados = db.query(true, NOME_TABELA+" INNER JOIN "+ProdutoDAO.NOME_TABELA+
					 " INNER JOIN "+PrecoDAO.NOME_TABELA+
					 " INNER JOIN "+RelacaoUsuarioPrecoDAO.NOME_TABELA+
					 " INNER JOIN "+UsuarioDAO.NOME_TABELA, new String[]{ClassificacaoDAO.NOME_TABELA+"."+ID, ClassificacaoDAO.NOME_TABELA+"."+DESCRICAO, ClassificacaoDAO.NOME_TABELA+"."+COD_GRUPO_PRODUTO, ClassificacaoDAO.NOME_TABELA+"."+COD_FAMILIA},
					 								ClassificacaoDAO.NOME_TABELA+"."+ClassificacaoDAO.ID+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.COD_CLASSIFICACAO+" AND "+
					 								PrecoDAO.NOME_TABELA+"."+PrecoDAO.COD_PRODUTO+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.ID+" AND "+
					 								RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_PRECO+" = "+PrecoDAO.NOME_TABELA+"."+PrecoDAO.ID+" AND "+
					 								UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.ID+" = "+RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_USUARIO+" AND "+
					 								ClassificacaoDAO.ID+ " LIKE ? AND "+ClassificacaoDAO.COD_GRUPO_PRODUTO+" LIKE ? AND "+ClassificacaoDAO.COD_FAMILIA+" LIKE ? AND "+
					 								UsuarioDAO.LOGIN+" = ? ", new String[]{idClassificacao, idGrupoProduto, idFamilia, login}, null, null, ClassificacaoDAO.NOME_TABELA+"."+ClassificacaoDAO.DESCRICAO, null);
			
			
			
			ClassificacaoVO model= new ClassificacaoVO();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexCodGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodFamilia = dados.getColumnIndex( COD_FAMILIA );
				

				do{		

					model = new ClassificacaoVO(dados.getString(indexCodigo),dados.getString(indexDescricao), new GrupoProdutoVO( dados.getString( indexCodGrupoProduto ) ), new FamiliaVO( dados.getString( indexCodFamilia ) ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDescricao( dados.getString(indexDescricao) );
					model.setGrupoProduto( new GrupoProdutoVO(dados.getString( indexCodGrupoProduto ) ));
					model.setFamilia( new FamiliaVO(dados.getString( indexCodFamilia )) );

					
				}while(dados.moveToNext());
			}

			return model;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ClassificacaoVO();
	}
}
