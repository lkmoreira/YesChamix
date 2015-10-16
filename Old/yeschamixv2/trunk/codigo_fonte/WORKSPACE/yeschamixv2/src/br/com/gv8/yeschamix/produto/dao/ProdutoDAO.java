/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 22/05/2013 20:59:23
 * @version 1.0 
 */
package br.com.gv8.yeschamix.produto.dao;

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
import br.com.gv8.yeschamix.imagem.dao.ImagemProdutoDAO;
import br.com.gv8.yeschamix.preco.dao.PrecoDAO;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;
import br.com.gv8.yeschamix.relacaousuariopreco.dao.RelacaoUsuarioPrecoDAO;
import br.com.gv8.yeschamix.usuario.dao.UsuarioDAO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 * @since 22/05/2013 - 20:59:23
 */
public class ProdutoDAO extends AbstractDAO{
	
	
	public static final String CONTEXTO_LOGICO = "ProdutoDAO";

	// Nome da tabela
	public static final String NOME_TABELA = "produto";

	// Nomes das colunas da tabela.
	public static final String ID = "id";
	public static final String DETALHES_PRODUTO = "detalhes_produto";
	public static final String DESCRICAO = "descricao";
	public static final String OPORTUNIDADE_DESTAQUE = "oportunidade_destaque";
	public static final String OPORTUNIDADE_VENDA = "oportunidade_venda";
	public static final String DATA_ULTIMA_ATUALIZACAO = "data_ultima_atualizacao";
	public static final String CONTADOR_ATUALIZACAO = "contador_atualizacao";
	public static final String CONTADOR_FOTO = "contador_foto";
	public static final String EXISTE_EM_ESTOQUE = "existe_em_estoque";
	public static final String STATUS_PRODUTO = "status_produto";
	public static final String COD_FAMILIA = "cod_familia";
	public static final String COD_GRUPO_PRODUTO = "cod_grupo_produto";
	public static final String COD_CLASSIFICACAO = "cod_classificacao";
	
	
	public ProdutoDAO( Context context ) {
		super( context );
		// TODO Auto-generated constructor stub
	}
	
	public long inserir(ProdutoVO model){
		Log.i(CONTEXTO_LOGICO, "Inserindo...");
		ContentValues values = new ContentValues();


		//if(!Utilidades.isNullOrBlank( model.getCodigo() )){
		values.put(ID, model.getId());
		/*}else{
			values.putNull(ID);
		}*/
		
		if(!Utilidades.isNullOrBlank( model.getDetalhesProduto() )){
			values.put(DETALHES_PRODUTO, model.getDetalhesProduto());
		}else{
			values.putNull(DETALHES_PRODUTO);
		}

		if(!Utilidades.isNullOrBlank( model.getDescricao() )){
			values.put(DESCRICAO, model.getDescricao());
		}else{
			values.putNull(DESCRICAO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getOportunidadeDestaque() )){
			values.put(OPORTUNIDADE_DESTAQUE, model.getOportunidadeDestaque());
		}else{
			values.putNull(OPORTUNIDADE_DESTAQUE);
		}
		
		if(!Utilidades.isNullOrBlank( model.getOportunidadeVenda() )){
			values.put(OPORTUNIDADE_VENDA, model.getOportunidadeVenda());
		}else{
			values.putNull(OPORTUNIDADE_VENDA);
		}
		
		if(!Utilidades.isNullOrBlank( model.getDataUltimaAtualizacao() )){
			values.put(DATA_ULTIMA_ATUALIZACAO,  model.getDataUltimaAtualizacao());
		}else{
			values.putNull(DATA_ULTIMA_ATUALIZACAO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getContadorAtualizacao() )){
			values.put(CONTADOR_ATUALIZACAO, model.getContadorAtualizacao());
		}else{
			values.putNull(CONTADOR_ATUALIZACAO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getContadorFoto() )){
			values.put(CONTADOR_FOTO, model.getContadorFoto());
		}else{
			values.putNull(CONTADOR_FOTO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getExisteEmEstoque() )){
			values.put(EXISTE_EM_ESTOQUE, model.getExisteEmEstoque());
		}else{
			values.putNull(EXISTE_EM_ESTOQUE);
		}
		
		if(!Utilidades.isNullOrBlank( model.getStatusProduto() )){
			values.put(STATUS_PRODUTO, model.getStatusProduto());
		}else{
			values.putNull(STATUS_PRODUTO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getFamilia() )){
			values.put(COD_FAMILIA, model.getFamilia().getId());
		}else{
			values.putNull(COD_FAMILIA);
		}
		
		if(!Utilidades.isNullOrBlank( model.getGrupoProduto() )){
			values.put(COD_GRUPO_PRODUTO, model.getGrupoProduto().getId());
		}else{
			values.putNull(COD_GRUPO_PRODUTO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getClassificacao() )){
			values.put(COD_CLASSIFICACAO, model.getClassificacao().getId());
		}else{
			values.putNull(COD_CLASSIFICACAO);
		}
		
		return db.insert(NOME_TABELA, null, values);
	}
	
	public long alterar(ProdutoVO model){
		Log.i(CONTEXTO_LOGICO, "Alterando...");
		ContentValues values = new ContentValues();
		
		if(!Utilidades.isNullOrBlank( model.getDetalhesProduto() )){
			values.put(DETALHES_PRODUTO, model.getDetalhesProduto());
		}else{
			values.putNull(DETALHES_PRODUTO);
		}

		if(!Utilidades.isNullOrBlank( model.getDescricao() )){
			values.put(DESCRICAO, model.getDescricao());
		}else{
			values.putNull(DESCRICAO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getOportunidadeDestaque() )){
			values.put(OPORTUNIDADE_DESTAQUE, model.getOportunidadeDestaque());
		}else{
			values.putNull(OPORTUNIDADE_DESTAQUE);
		}
		
		if(!Utilidades.isNullOrBlank( model.getOportunidadeVenda() )){
			values.put(OPORTUNIDADE_VENDA, model.getOportunidadeVenda());
		}else{
			values.putNull(OPORTUNIDADE_VENDA);
		}
		
		if(!Utilidades.isNullOrBlank( model.getDataUltimaAtualizacao() )){
			values.put(DATA_ULTIMA_ATUALIZACAO,  model.getDataUltimaAtualizacao());
		}else{
			values.putNull(DATA_ULTIMA_ATUALIZACAO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getContadorAtualizacao() )){
			values.put(CONTADOR_ATUALIZACAO, model.getContadorAtualizacao());
		}else{
			values.putNull(CONTADOR_ATUALIZACAO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getContadorFoto() )){
			values.put(CONTADOR_FOTO, model.getContadorFoto());
		}else{
			values.putNull(CONTADOR_FOTO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getExisteEmEstoque() )){
			values.put(EXISTE_EM_ESTOQUE, model.getExisteEmEstoque());
		}else{
			values.putNull(EXISTE_EM_ESTOQUE);
		}
		
		if(!Utilidades.isNullOrBlank( model.getStatusProduto() )){
			values.put(STATUS_PRODUTO, model.getStatusProduto());
		}else{
			values.putNull(STATUS_PRODUTO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getFamilia() )){
			values.put(COD_FAMILIA, model.getFamilia().getId());
		}else{
			values.putNull(COD_FAMILIA);
		}
		
		if(!Utilidades.isNullOrBlank( model.getGrupoProduto() )){
			values.put(COD_GRUPO_PRODUTO, model.getGrupoProduto().getId());
		}else{
			values.putNull(COD_GRUPO_PRODUTO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getClassificacao() )){
			values.put(COD_CLASSIFICACAO, model.getClassificacao().getId());
		}else{
			values.putNull(COD_CLASSIFICACAO);
		}

		return db.update(NOME_TABELA, values, ID+"=?", new String[]{model.getId().toString()});
	}
	
	public boolean deletarProduto(String id) {
		try {
		    db.delete( NOME_TABELA , ProdutoDAO.ID+" LIKE ? " , new String[]{id} );
		   
		    db.delete(ImagemProdutoDAO.NOME_TABELA, ImagemProdutoDAO.COD_PRODUTO+" LIKE ? ",new String[]{id});
		} catch (Exception e) {
		    return false;
		}
		return true;
	    }
	
	public ArrayList<ProdutoVO> consultarTodos(){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{ID,DETALHES_PRODUTO, DESCRICAO, OPORTUNIDADE_DESTAQUE, OPORTUNIDADE_VENDA,
														DATA_ULTIMA_ATUALIZACAO, CONTADOR_ATUALIZACAO, CONTADOR_FOTO, EXISTE_EM_ESTOQUE,
														STATUS_PRODUTO, COD_CLASSIFICACAO, COD_GRUPO_PRODUTO, COD_FAMILIA}, null, null, null, null, null);

			ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);
				int indexDetalhesProduto = dados.getColumnIndex( DETALHES_PRODUTO );
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexOportunidadeDestaque = dados.getColumnIndex( OPORTUNIDADE_DESTAQUE );
				int indexOportunidadeVenda = dados.getColumnIndex( OPORTUNIDADE_VENDA );
				int indexDataUltimaAtualizacao = dados.getColumnIndex( DATA_ULTIMA_ATUALIZACAO );
				int indexContadorAtualizacao = dados.getColumnIndex( CONTADOR_ATUALIZACAO );
				int indexContadorFoto = dados.getColumnIndex( CONTADOR_FOTO );
				
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				int indexCodigoClassificacao = dados.getColumnIndex( COD_CLASSIFICACAO );
				int indexCodigoGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodigoFamilia = dados.getColumnIndex( COD_FAMILIA );
				
				do{		

					ProdutoVO model = new ProdutoVO(dados.getString( indexCodigo ),new ClassificacaoVO( dados.getString(indexCodigoClassificacao) ), 
										new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )), new FamiliaVO( dados.getString( indexCodigoFamilia ) ),
										dados.getString( indexDetalhesProduto ),dados.getString( indexDescricao ), dados.getString( indexOportunidadeDestaque ), 
										dados.getString( indexOportunidadeVenda ),  dados.getString( indexDataUltimaAtualizacao ),
										dados.getInt( indexContadorAtualizacao ),dados.getInt( indexContadorFoto ),null,dados.getString( indexExisteEmEstoque ),
										dados.getString( indexStatusProduto ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDetalhesProduto( dados.getString( indexDetalhesProduto ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setOportunidadeDestaque( dados.getString( indexOportunidadeDestaque ) );
					model.setOportunidadeVenda( dados.getString( indexOportunidadeVenda ) );
					model.setDataUltimaAtualizacao( dados.getString( indexDataUltimaAtualizacao ));
					model.setContadorAtualizacao( dados.getInt( indexContadorAtualizacao ) );
					model.setContadorFoto( dados.getInt( indexContadorFoto ) );
					model.setImagensProduto( null );
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
					model.setClassificacao( new ClassificacaoVO( dados.getString( indexCodigoClassificacao ) ));
					model.setGrupoProduto(new GrupoProdutoVO(dados.getString( indexCodigoGrupoProduto )) );
					model.setFamilia(new FamiliaVO(dados.getString( indexCodigoFamilia )) );
					

					produtos.add(model);
				}while(dados.moveToNext());
			}

			return produtos;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ProdutoVO>();
	}
	
	public ArrayList<ProdutoVO> consultarTodosPorLogin(String login){
		Cursor dados = null;
		try{
			dados = db.query(true,NOME_TABELA+" INNER JOIN "+PrecoDAO.NOME_TABELA+
					 " INNER JOIN "+RelacaoUsuarioPrecoDAO.NOME_TABELA+
					 " INNER JOIN "+UsuarioDAO.NOME_TABELA, new String[]{ProdutoDAO.NOME_TABELA+"."+ID,DETALHES_PRODUTO, ProdutoDAO.NOME_TABELA+"."+DESCRICAO, OPORTUNIDADE_DESTAQUE, OPORTUNIDADE_VENDA,
												DATA_ULTIMA_ATUALIZACAO, CONTADOR_ATUALIZACAO, CONTADOR_FOTO, EXISTE_EM_ESTOQUE,
												STATUS_PRODUTO, ProdutoDAO.NOME_TABELA+"."+COD_CLASSIFICACAO, ProdutoDAO.NOME_TABELA+"."+COD_GRUPO_PRODUTO, ProdutoDAO.NOME_TABELA+"."+COD_FAMILIA},
																										           PrecoDAO.NOME_TABELA+"."+PrecoDAO.COD_PRODUTO+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.ID+" AND "+
																										           RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_PRECO+" = "+PrecoDAO.NOME_TABELA+"."+PrecoDAO.ID+" AND "+
																												   UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.ID+" = "+RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_USUARIO+" AND "+
																												   UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.LOGIN+" = ? ",new String[]{ login}, null, null, null, null);

			ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);
				int indexDetalhesProduto = dados.getColumnIndex( DETALHES_PRODUTO );
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexOportunidadeDestaque = dados.getColumnIndex( OPORTUNIDADE_DESTAQUE );
				int indexOportunidadeVenda = dados.getColumnIndex( OPORTUNIDADE_VENDA );
				int indexDataUltimaAtualizacao = dados.getColumnIndex( DATA_ULTIMA_ATUALIZACAO );
				int indexContadorAtualizacao = dados.getColumnIndex( CONTADOR_ATUALIZACAO );
				int indexContadorFoto = dados.getColumnIndex( CONTADOR_FOTO );
				
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				int indexCodigoClassificacao = dados.getColumnIndex( COD_CLASSIFICACAO );
				int indexCodigoGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodigoFamilia = dados.getColumnIndex( COD_FAMILIA );
				
				do{		

					ProdutoVO model = new ProdutoVO(dados.getString( indexCodigo ),new ClassificacaoVO( dados.getString(indexCodigoClassificacao) ), 
										new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )), new FamiliaVO( dados.getString( indexCodigoFamilia ) ),
										dados.getString( indexDetalhesProduto ),dados.getString( indexDescricao ), dados.getString( indexOportunidadeDestaque ), 
										dados.getString( indexOportunidadeVenda ),  dados.getString( indexDataUltimaAtualizacao ),
										dados.getInt( indexContadorAtualizacao ),dados.getInt( indexContadorFoto ),null,dados.getString( indexExisteEmEstoque ),
										dados.getString( indexStatusProduto ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDetalhesProduto( dados.getString( indexDetalhesProduto ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setOportunidadeDestaque( dados.getString( indexOportunidadeDestaque ) );
					model.setOportunidadeVenda( dados.getString( indexOportunidadeVenda ) );
					model.setDataUltimaAtualizacao( dados.getString( indexDataUltimaAtualizacao ));
					model.setContadorAtualizacao( dados.getInt( indexContadorAtualizacao ) );
					model.setContadorFoto( dados.getInt( indexContadorFoto ) );
					model.setImagensProduto( null );
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
					model.setClassificacao( new ClassificacaoVO( dados.getString( indexCodigoClassificacao ) ));
					model.setGrupoProduto(new GrupoProdutoVO(dados.getString( indexCodigoGrupoProduto )) );
					model.setFamilia(new FamiliaVO(dados.getString( indexCodigoFamilia )) );
					

					produtos.add(model);
				}while(dados.moveToNext());
			}

			return produtos;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ProdutoVO>();
	}
	
	
	public ProdutoVO consultarPorCodigoProduto(String idProduto){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{ID,DETALHES_PRODUTO, DESCRICAO, OPORTUNIDADE_DESTAQUE, OPORTUNIDADE_VENDA,
														DATA_ULTIMA_ATUALIZACAO, CONTADOR_ATUALIZACAO, CONTADOR_FOTO, EXISTE_EM_ESTOQUE,
														STATUS_PRODUTO, COD_CLASSIFICACAO, COD_GRUPO_PRODUTO, COD_FAMILIA},ProdutoDAO.ID+ " = ? ", new String[]{idProduto}, null, null, null);

			ProdutoVO model = new ProdutoVO();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);
				int indexDetalhesProduto = dados.getColumnIndex( DETALHES_PRODUTO );
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexOportunidadeDestaque = dados.getColumnIndex( OPORTUNIDADE_DESTAQUE );
				int indexOportunidadeVenda = dados.getColumnIndex( OPORTUNIDADE_VENDA );
				int indexDataUltimaAtualizacao = dados.getColumnIndex( DATA_ULTIMA_ATUALIZACAO );
				int indexContadorAtualizacao = dados.getColumnIndex( CONTADOR_ATUALIZACAO );
				int indexContadorFoto = dados.getColumnIndex( CONTADOR_FOTO );
				
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				int indexCodigoClassificacao = dados.getColumnIndex( COD_CLASSIFICACAO );
				int indexCodigoGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodigoFamilia = dados.getColumnIndex( COD_FAMILIA );
				
				do{		
					
					//mandando a lista de imagens nula
					 model = new ProdutoVO(dados.getString( indexCodigo ),new ClassificacaoVO( dados.getString(indexCodigoClassificacao) ), 
										new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )), new FamiliaVO( dados.getString( indexCodigoFamilia ) ),
										dados.getString( indexDetalhesProduto ),dados.getString( indexDescricao ), dados.getString( indexOportunidadeDestaque ), 
										dados.getString( indexOportunidadeVenda ),  dados.getString( indexDataUltimaAtualizacao ) ,
										dados.getInt( indexContadorAtualizacao ),dados.getInt( indexContadorFoto ),null,dados.getString( indexExisteEmEstoque ),
										dados.getString( indexStatusProduto ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDetalhesProduto( dados.getString( indexDetalhesProduto ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setOportunidadeDestaque( dados.getString( indexOportunidadeDestaque ) );
					model.setOportunidadeVenda( dados.getString( indexOportunidadeVenda ) );
					model.setDataUltimaAtualizacao( dados.getString( indexDataUltimaAtualizacao ));
					model.setContadorAtualizacao( dados.getInt( indexContadorAtualizacao ) );
					model.setContadorFoto( dados.getInt( indexContadorFoto ) );
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
					model.setClassificacao(new ClassificacaoVO(dados.getString( indexCodigoClassificacao)) );
					model.setGrupoProduto(new GrupoProdutoVO(dados.getString( indexCodigoGrupoProduto ) ));
					model.setFamilia(new FamiliaVO(dados.getString( indexCodigoFamilia )) );
					

					
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
		return new ProdutoVO();
	}
	
	
	public ArrayList<ProdutoVO> consultarProdutoPorFamilia(FamiliaVO familia){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{ID,DETALHES_PRODUTO, DESCRICAO, OPORTUNIDADE_DESTAQUE, OPORTUNIDADE_VENDA,
														DATA_ULTIMA_ATUALIZACAO, CONTADOR_ATUALIZACAO, CONTADOR_FOTO, EXISTE_EM_ESTOQUE,
														STATUS_PRODUTO, COD_CLASSIFICACAO, COD_GRUPO_PRODUTO, COD_FAMILIA},ProdutoDAO.COD_FAMILIA+ " LIKE ? ",new String[]{ familia.getId()}, null, null, " RANDOM() LIMIT 100");

			ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);
				int indexDetalhesProduto = dados.getColumnIndex( DETALHES_PRODUTO );
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexOportunidadeDestaque = dados.getColumnIndex( OPORTUNIDADE_DESTAQUE );
				int indexOportunidadeVenda = dados.getColumnIndex( OPORTUNIDADE_VENDA );
				int indexDataUltimaAtualizacao = dados.getColumnIndex( DATA_ULTIMA_ATUALIZACAO );
				int indexContadorAtualizacao = dados.getColumnIndex( CONTADOR_ATUALIZACAO );
				int indexContadorFoto = dados.getColumnIndex( CONTADOR_FOTO );
				
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				int indexCodigoClassificacao = dados.getColumnIndex( COD_CLASSIFICACAO );
				int indexCodigoGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodigoFamilia = dados.getColumnIndex( COD_FAMILIA );
				
				do{		
					
					//mandando a lista de imagens nula
					ProdutoVO model = new ProdutoVO(dados.getString( indexCodigo ),new ClassificacaoVO( dados.getString(indexCodigoClassificacao) ), 
										new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )), new FamiliaVO( dados.getString( indexCodigoFamilia ) ),
										dados.getString( indexDetalhesProduto ),dados.getString( indexDescricao ), dados.getString( indexOportunidadeDestaque ), 
										dados.getString( indexOportunidadeVenda ),  dados.getString( indexDataUltimaAtualizacao ) ,
										dados.getInt( indexContadorAtualizacao ),dados.getInt( indexContadorFoto ),null,dados.getString( indexExisteEmEstoque ),
										dados.getString( indexStatusProduto ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDetalhesProduto( dados.getString( indexDetalhesProduto ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setOportunidadeDestaque( dados.getString( indexOportunidadeDestaque ) );
					model.setOportunidadeVenda( dados.getString( indexOportunidadeVenda ) );
					model.setDataUltimaAtualizacao( dados.getString( indexDataUltimaAtualizacao ));
					model.setContadorAtualizacao( dados.getInt( indexContadorAtualizacao ) );
					model.setContadorFoto( dados.getInt( indexContadorFoto ) );
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
					model.setClassificacao(new ClassificacaoVO( dados.getString (indexCodigoClassificacao) ));
					model.setGrupoProduto(new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )) );
					model.setFamilia(new FamiliaVO(dados.getString( indexCodigoFamilia )) );
					

					produtos.add(model);
				}while(dados.moveToNext());
			}

			return produtos;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ProdutoVO>();
	}
	
	public ArrayList<ProdutoVO> consultarProdutoPorFamiliaLogin(FamiliaVO familia, String login){
		Cursor dados = null;
	
		try{
			dados = db.query(true ,NOME_TABELA+" INNER JOIN "+PrecoDAO.NOME_TABELA+
							 " INNER JOIN "+RelacaoUsuarioPrecoDAO.NOME_TABELA+
							 " INNER JOIN "+UsuarioDAO.NOME_TABELA, new String[]{ProdutoDAO.NOME_TABELA+"."+ID,DETALHES_PRODUTO, ProdutoDAO.NOME_TABELA+"."+DESCRICAO, OPORTUNIDADE_DESTAQUE, OPORTUNIDADE_VENDA,
														DATA_ULTIMA_ATUALIZACAO, CONTADOR_ATUALIZACAO, CONTADOR_FOTO, EXISTE_EM_ESTOQUE,
														STATUS_PRODUTO, ProdutoDAO.NOME_TABELA+"."+COD_CLASSIFICACAO, ProdutoDAO.NOME_TABELA+"."+COD_GRUPO_PRODUTO, ProdutoDAO.NOME_TABELA+"."+COD_FAMILIA},
																												           PrecoDAO.NOME_TABELA+"."+PrecoDAO.COD_PRODUTO+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.ID+" AND "+
																												           RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_PRECO+" = "+PrecoDAO.NOME_TABELA+"."+PrecoDAO.ID+" AND "+
																														   UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.ID+" = "+RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_USUARIO+" AND "+
																														   ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.COD_FAMILIA+ " LIKE ? AND "+
																														   UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.LOGIN+" = ? ",new String[]{ familia.getId(), login}, null, null, " RANDOM() LIMIT 100", null);

			ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);
				int indexDetalhesProduto = dados.getColumnIndex( DETALHES_PRODUTO );
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexOportunidadeDestaque = dados.getColumnIndex( OPORTUNIDADE_DESTAQUE );
				int indexOportunidadeVenda = dados.getColumnIndex( OPORTUNIDADE_VENDA );
				int indexDataUltimaAtualizacao = dados.getColumnIndex( DATA_ULTIMA_ATUALIZACAO );
				int indexContadorAtualizacao = dados.getColumnIndex( CONTADOR_ATUALIZACAO );
				int indexContadorFoto = dados.getColumnIndex( CONTADOR_FOTO );
				
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				int indexCodigoClassificacao = dados.getColumnIndex( COD_CLASSIFICACAO );
				int indexCodigoGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodigoFamilia = dados.getColumnIndex( COD_FAMILIA );
				
				do{		
					
					//mandando a lista de imagens nula
					ProdutoVO model = new ProdutoVO(dados.getString( indexCodigo ),new ClassificacaoVO( dados.getString(indexCodigoClassificacao) ), 
										new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )), new FamiliaVO( dados.getString( indexCodigoFamilia ) ),
										dados.getString( indexDetalhesProduto ),dados.getString( indexDescricao ), dados.getString( indexOportunidadeDestaque ), 
										dados.getString( indexOportunidadeVenda ),  dados.getString( indexDataUltimaAtualizacao ) ,
										dados.getInt( indexContadorAtualizacao ),dados.getInt( indexContadorFoto ),null,dados.getString( indexExisteEmEstoque ),
										dados.getString( indexStatusProduto ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDetalhesProduto( dados.getString( indexDetalhesProduto ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setOportunidadeDestaque( dados.getString( indexOportunidadeDestaque ) );
					model.setOportunidadeVenda( dados.getString( indexOportunidadeVenda ) );
					model.setDataUltimaAtualizacao( dados.getString( indexDataUltimaAtualizacao ));
					model.setContadorAtualizacao( dados.getInt( indexContadorAtualizacao ) );
					model.setContadorFoto( dados.getInt( indexContadorFoto ) );
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
					model.setClassificacao(new ClassificacaoVO( dados.getString (indexCodigoClassificacao) ));
					model.setGrupoProduto(new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )) );
					model.setFamilia(new FamiliaVO(dados.getString( indexCodigoFamilia )) );
					

					produtos.add(model);
				}while(dados.moveToNext());
			}

			return produtos;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ProdutoVO>();
	}
	
	public ArrayList<ProdutoVO> consultarProdutoPorNomeOuCodigo(String nomeProduto){
		Cursor dados = null;
		try{
			
			
			
			dados = db.query(NOME_TABELA, new String[]{ID,DETALHES_PRODUTO, DESCRICAO, OPORTUNIDADE_DESTAQUE, OPORTUNIDADE_VENDA,
														DATA_ULTIMA_ATUALIZACAO, CONTADOR_ATUALIZACAO, CONTADOR_FOTO, EXISTE_EM_ESTOQUE,
														STATUS_PRODUTO, COD_CLASSIFICACAO, COD_GRUPO_PRODUTO, COD_FAMILIA},ProdutoDAO.DESCRICAO+ " LIKE ? OR "+ProdutoDAO.ID+" LIKE ? ",new String[]{ "%"+nomeProduto+"%","%"+nomeProduto+"%"}, null, null, ProdutoDAO.DESCRICAO);

			ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);
				int indexDetalhesProduto = dados.getColumnIndex( DETALHES_PRODUTO );
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexOportunidadeDestaque = dados.getColumnIndex( OPORTUNIDADE_DESTAQUE );
				int indexOportunidadeVenda = dados.getColumnIndex( OPORTUNIDADE_VENDA );
				int indexDataUltimaAtualizacao = dados.getColumnIndex( DATA_ULTIMA_ATUALIZACAO );
				int indexContadorAtualizacao = dados.getColumnIndex( CONTADOR_ATUALIZACAO );
				int indexContadorFoto = dados.getColumnIndex( CONTADOR_FOTO );
				
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				int indexCodigoClassificacao = dados.getColumnIndex( COD_CLASSIFICACAO );
				int indexCodigoGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodigoFamilia = dados.getColumnIndex( COD_FAMILIA );
				
				do{		
					
					//mandando a lista de imagens nula
					ProdutoVO model = new ProdutoVO(dados.getString( indexCodigo ),new ClassificacaoVO( dados.getString(indexCodigoClassificacao) ), 
										new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )), new FamiliaVO( dados.getString( indexCodigoFamilia ) ),
										dados.getString( indexDetalhesProduto ),dados.getString( indexDescricao ), dados.getString( indexOportunidadeDestaque ), 
										dados.getString( indexOportunidadeVenda ),  dados.getString( indexDataUltimaAtualizacao ) ,
										dados.getInt( indexContadorAtualizacao ),dados.getInt( indexContadorFoto ),null,dados.getString( indexExisteEmEstoque ),
										dados.getString( indexStatusProduto ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDetalhesProduto( dados.getString( indexDetalhesProduto ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setOportunidadeDestaque( dados.getString( indexOportunidadeDestaque ) );
					model.setOportunidadeVenda( dados.getString( indexOportunidadeVenda ) );
					model.setDataUltimaAtualizacao( dados.getString( indexDataUltimaAtualizacao ));
					model.setContadorAtualizacao( dados.getInt( indexContadorAtualizacao ) );
					model.setContadorFoto( dados.getInt( indexContadorFoto ) );
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
					model.setClassificacao(new ClassificacaoVO( dados.getString (indexCodigoClassificacao) ));
					model.setGrupoProduto(new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto ) ));
					model.setFamilia(new FamiliaVO( dados.getString( indexCodigoFamilia )) );
					

					produtos.add(model);
				}while(dados.moveToNext());
			}

			return produtos;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ProdutoVO>();
	}
	
	
	public ArrayList<ProdutoVO> consultarProdutoPorNomeOuCodigoLogin(String nomeProduto, String login){
		Cursor dados = null;
		try{
			
			
			
			dados = db.query(true,NOME_TABELA+" INNER JOIN "+PrecoDAO.NOME_TABELA+
					 " INNER JOIN "+RelacaoUsuarioPrecoDAO.NOME_TABELA+
					 " INNER JOIN "+UsuarioDAO.NOME_TABELA, new String[]{ProdutoDAO.NOME_TABELA+"."+ID, DETALHES_PRODUTO, ProdutoDAO.NOME_TABELA+"."+DESCRICAO, OPORTUNIDADE_DESTAQUE, OPORTUNIDADE_VENDA,
														DATA_ULTIMA_ATUALIZACAO, CONTADOR_ATUALIZACAO, CONTADOR_FOTO, EXISTE_EM_ESTOQUE,
														STATUS_PRODUTO, ProdutoDAO.NOME_TABELA+"."+COD_CLASSIFICACAO, ProdutoDAO.NOME_TABELA+"."+COD_GRUPO_PRODUTO, ProdutoDAO.NOME_TABELA+"."+COD_FAMILIA},
																														   PrecoDAO.NOME_TABELA+"."+PrecoDAO.COD_PRODUTO+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.ID+" AND "+
																														   RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_PRECO+" = "+PrecoDAO.NOME_TABELA+"."+PrecoDAO.ID+" AND "+
																														   UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.ID+" = "+RelacaoUsuarioPrecoDAO.COD_USUARIO+" AND "+
																														   ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.ID+" LIKE ? AND "+
																														   UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.LOGIN+" = ? ",new String[]{"%"+nomeProduto+"%", login}, null, null, ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.DESCRICAO, null);
			
			ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);
				int indexDetalhesProduto = dados.getColumnIndex( DETALHES_PRODUTO );
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexOportunidadeDestaque = dados.getColumnIndex( OPORTUNIDADE_DESTAQUE );
				int indexOportunidadeVenda = dados.getColumnIndex( OPORTUNIDADE_VENDA );
				int indexDataUltimaAtualizacao = dados.getColumnIndex( DATA_ULTIMA_ATUALIZACAO );
				int indexContadorAtualizacao = dados.getColumnIndex( CONTADOR_ATUALIZACAO );
				int indexContadorFoto = dados.getColumnIndex( CONTADOR_FOTO );
				
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				int indexCodigoClassificacao = dados.getColumnIndex( COD_CLASSIFICACAO );
				int indexCodigoGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodigoFamilia = dados.getColumnIndex( COD_FAMILIA );
				
				do{		
					
					//mandando a lista de imagens nula
					ProdutoVO model = new ProdutoVO(dados.getString( indexCodigo ),new ClassificacaoVO( dados.getString(indexCodigoClassificacao) ), 
										new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )), new FamiliaVO( dados.getString( indexCodigoFamilia ) ),
										dados.getString( indexDetalhesProduto ),dados.getString( indexDescricao ), dados.getString( indexOportunidadeDestaque ), 
										dados.getString( indexOportunidadeVenda ),  dados.getString( indexDataUltimaAtualizacao ) ,
										dados.getInt( indexContadorAtualizacao ),dados.getInt( indexContadorFoto ),null,dados.getString( indexExisteEmEstoque ),
										dados.getString( indexStatusProduto ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDetalhesProduto( dados.getString( indexDetalhesProduto ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setOportunidadeDestaque( dados.getString( indexOportunidadeDestaque ) );
					model.setOportunidadeVenda( dados.getString( indexOportunidadeVenda ) );
					model.setDataUltimaAtualizacao( dados.getString( indexDataUltimaAtualizacao ));
					model.setContadorAtualizacao( dados.getInt( indexContadorAtualizacao ) );
					model.setContadorFoto( dados.getInt( indexContadorFoto ) );
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
					model.setClassificacao(new ClassificacaoVO( dados.getString (indexCodigoClassificacao) ));
					model.setGrupoProduto(new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto ) ));
					model.setFamilia(new FamiliaVO( dados.getString( indexCodigoFamilia )) );
					

					produtos.add(model);
				}while(dados.moveToNext());
			}

			return produtos;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ProdutoVO>();
	}
	
	
	
	public ArrayList<ProdutoVO> consultarProdutosRandom(){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{ID,DETALHES_PRODUTO, DESCRICAO, OPORTUNIDADE_DESTAQUE, OPORTUNIDADE_VENDA,
														DATA_ULTIMA_ATUALIZACAO, CONTADOR_ATUALIZACAO, CONTADOR_FOTO, EXISTE_EM_ESTOQUE,
														STATUS_PRODUTO, COD_CLASSIFICACAO, COD_GRUPO_PRODUTO, COD_FAMILIA}, null, null, null, null, " RANDOM() LIMIT 100 ");

			ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);
				int indexDetalhesProduto = dados.getColumnIndex( DETALHES_PRODUTO );
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexOportunidadeDestaque = dados.getColumnIndex( OPORTUNIDADE_DESTAQUE );
				int indexOportunidadeVenda = dados.getColumnIndex( OPORTUNIDADE_VENDA );
				int indexDataUltimaAtualizacao = dados.getColumnIndex( DATA_ULTIMA_ATUALIZACAO );
				int indexContadorAtualizacao = dados.getColumnIndex( CONTADOR_ATUALIZACAO );
				int indexContadorFoto = dados.getColumnIndex( CONTADOR_FOTO );
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				int indexCodigoClassificacao = dados.getColumnIndex( COD_CLASSIFICACAO );
				int indexCodigoGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodigoFamilia = dados.getColumnIndex( COD_FAMILIA );
				
				do{		

					ProdutoVO model = new ProdutoVO(dados.getString( indexCodigo ),new ClassificacaoVO( dados.getString(indexCodigoClassificacao) ), 
										new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )), new FamiliaVO( dados.getString( indexCodigoFamilia ) ),
										dados.getString( indexDetalhesProduto ),dados.getString( indexDescricao ), dados.getString( indexOportunidadeDestaque ), 
										dados.getString( indexOportunidadeVenda ),  dados.getString( indexDataUltimaAtualizacao ),
										dados.getInt( indexContadorAtualizacao ),dados.getInt( indexContadorFoto ),null,dados.getString( indexExisteEmEstoque ),
										dados.getString( indexStatusProduto ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDetalhesProduto( dados.getString( indexDetalhesProduto ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setOportunidadeDestaque( dados.getString( indexOportunidadeDestaque ) );
					model.setOportunidadeVenda( dados.getString( indexOportunidadeVenda ) );
					model.setDataUltimaAtualizacao( dados.getString( indexDataUltimaAtualizacao ));
					model.setContadorAtualizacao( dados.getInt( indexContadorAtualizacao ) );
					model.setContadorFoto( dados.getInt( indexContadorFoto ) );
					model.setImagensProduto( null );
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
					model.setClassificacao(new ClassificacaoVO( dados.getString (indexCodigoClassificacao)) );
					model.setGrupoProduto(new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto ) ));
					model.setFamilia(new FamiliaVO( dados.getString( indexCodigoFamilia )) );
					

					produtos.add(model);
				}while(dados.moveToNext());
			}

			return produtos;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ProdutoVO>();
	}
	
	public ArrayList<ProdutoVO> consultarProdutosRandomPorLogin(String login){
		Cursor dados = null;
		try{
			dados = db.query(true, NOME_TABELA+" INNER JOIN "+PrecoDAO.NOME_TABELA+
					 " INNER JOIN "+RelacaoUsuarioPrecoDAO.NOME_TABELA+
					 " INNER JOIN "+UsuarioDAO.NOME_TABELA, new String[]{ProdutoDAO.NOME_TABELA+"."+ID, DETALHES_PRODUTO, ProdutoDAO.NOME_TABELA+"."+DESCRICAO, OPORTUNIDADE_DESTAQUE, OPORTUNIDADE_VENDA,
														DATA_ULTIMA_ATUALIZACAO, CONTADOR_ATUALIZACAO, CONTADOR_FOTO, EXISTE_EM_ESTOQUE,
														STATUS_PRODUTO, ProdutoDAO.NOME_TABELA+"."+COD_CLASSIFICACAO, ProdutoDAO.NOME_TABELA+"."+COD_GRUPO_PRODUTO, ProdutoDAO.NOME_TABELA+"."+COD_FAMILIA},
																														   PrecoDAO.NOME_TABELA+"."+PrecoDAO.COD_PRODUTO+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.ID+" AND "+
																														   RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_PRECO+" = "+PrecoDAO.NOME_TABELA+"."+PrecoDAO.ID+" AND "+
																														   UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.ID+" = "+RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_USUARIO+" AND "+
																														   UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.LOGIN+" = ? ",new String[]{login}, null, null, " RANDOM() LIMIT 100 ",null);

			ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);
				int indexDetalhesProduto = dados.getColumnIndex( DETALHES_PRODUTO );
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexOportunidadeDestaque = dados.getColumnIndex( OPORTUNIDADE_DESTAQUE );
				int indexOportunidadeVenda = dados.getColumnIndex( OPORTUNIDADE_VENDA );
				int indexDataUltimaAtualizacao = dados.getColumnIndex( DATA_ULTIMA_ATUALIZACAO );
				int indexContadorAtualizacao = dados.getColumnIndex( CONTADOR_ATUALIZACAO );
				int indexContadorFoto = dados.getColumnIndex( CONTADOR_FOTO );
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				int indexCodigoClassificacao = dados.getColumnIndex( COD_CLASSIFICACAO );
				int indexCodigoGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodigoFamilia = dados.getColumnIndex( COD_FAMILIA );
				
				do{		

					ProdutoVO model = new ProdutoVO(dados.getString( indexCodigo ),new ClassificacaoVO( dados.getString(indexCodigoClassificacao) ), 
										new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )), new FamiliaVO( dados.getString( indexCodigoFamilia ) ),
										dados.getString( indexDetalhesProduto ),dados.getString( indexDescricao ), dados.getString( indexOportunidadeDestaque ), 
										dados.getString( indexOportunidadeVenda ),  dados.getString( indexDataUltimaAtualizacao ),
										dados.getInt( indexContadorAtualizacao ),dados.getInt( indexContadorFoto ),null,dados.getString( indexExisteEmEstoque ),
										dados.getString( indexStatusProduto ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDetalhesProduto( dados.getString( indexDetalhesProduto ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setOportunidadeDestaque( dados.getString( indexOportunidadeDestaque ) );
					model.setOportunidadeVenda( dados.getString( indexOportunidadeVenda ) );
					model.setDataUltimaAtualizacao( dados.getString( indexDataUltimaAtualizacao ));
					model.setContadorAtualizacao( dados.getInt( indexContadorAtualizacao ) );
					model.setContadorFoto( dados.getInt( indexContadorFoto ) );
					model.setImagensProduto( null );
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
					model.setClassificacao(new ClassificacaoVO( dados.getString (indexCodigoClassificacao)) );
					model.setGrupoProduto(new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto ) ));
					model.setFamilia(new FamiliaVO( dados.getString( indexCodigoFamilia )) );
					

					produtos.add(model);
				}while(dados.moveToNext());
			}

			return produtos;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ProdutoVO>();
	}
	
	public ArrayList<ProdutoVO> consultarProduto(String idFamilia, String idGrupoProduto,
		    String idClassificacao, String lancamento, String oportunidade){
		Cursor dados = null;
		try{
			boolean and = false;
			StringBuilder condicao = new StringBuilder();
			String[] parametros;
			Vector<String> vetor = new Vector<String>();

			
			if (lancamento.equals("1")) {
				condicao.append(ProdutoDAO.OPORTUNIDADE_DESTAQUE+" LIKE ? ");
			    and = true;
			    vetor.add( lancamento );
			   
			}
			if (oportunidade.equals("1")) {
			    if (and) {
				condicao.append(" AND ");
			    }
			    condicao.append(ProdutoDAO.OPORTUNIDADE_VENDA+" LIKE ? ");
			    and = true;
			    vetor.add( oportunidade );
			}
			if (!Utilidades.isNullOrBlank(idFamilia)) {
			    if (and) {
			    	condicao.append(" AND ");
			    }
			    condicao.append(ProdutoDAO.COD_FAMILIA+" LIKE ? ");
			    and = true;
			    vetor.add( idFamilia );
			}
			if (!Utilidades.isNullOrBlank(idGrupoProduto)) {
			    if (and) {
			    	condicao.append(" AND ");
			    }
			    condicao.append(ProdutoDAO.COD_GRUPO_PRODUTO+" LIKE ? ");
			    and = true;
			    vetor.add( idGrupoProduto );
			}
			if (!Utilidades.isNullOrBlank(idClassificacao)) {
			    if (and) {
			    	condicao.append(" AND ");
			    }
			    condicao.append(ProdutoDAO.COD_CLASSIFICACAO+" LIKE ? ");
			    and = true;
			   
			    vetor.add( idClassificacao );
			}
			
			parametros = vetor.toArray(new String[vetor.size()]);
			
			
			
			dados = db.query(true,NOME_TABELA, new String[]{ID, DETALHES_PRODUTO, DESCRICAO, OPORTUNIDADE_DESTAQUE, OPORTUNIDADE_VENDA,
					DATA_ULTIMA_ATUALIZACAO, CONTADOR_ATUALIZACAO, CONTADOR_FOTO, EXISTE_EM_ESTOQUE,
					STATUS_PRODUTO, COD_CLASSIFICACAO,COD_GRUPO_PRODUTO, COD_FAMILIA}, condicao.toString() ,parametros, null, null, ProdutoDAO.ID, null);
			
			ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);
				int indexDetalhesProduto = dados.getColumnIndex( DETALHES_PRODUTO );
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexOportunidadeDestaque = dados.getColumnIndex( OPORTUNIDADE_DESTAQUE );
				int indexOportunidadeVenda = dados.getColumnIndex( OPORTUNIDADE_VENDA );
				int indexDataUltimaAtualizacao = dados.getColumnIndex( DATA_ULTIMA_ATUALIZACAO );
				int indexContadorAtualizacao = dados.getColumnIndex( CONTADOR_ATUALIZACAO );
				int indexContadorFoto = dados.getColumnIndex( CONTADOR_FOTO );
				
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				int indexCodigoClassificacao = dados.getColumnIndex( COD_CLASSIFICACAO );
				int indexCodigoGrupoProduto = dados.getColumnIndex( COD_GRUPO_PRODUTO );
				int indexCodigoFamilia = dados.getColumnIndex( COD_FAMILIA );
				
				do{		
					
					//mandando a lista de imagens nula
					ProdutoVO model = new ProdutoVO(dados.getString( indexCodigo ),new ClassificacaoVO( dados.getString(indexCodigoClassificacao) ), 
										new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )), new FamiliaVO( dados.getString( indexCodigoFamilia ) ),
										dados.getString( indexDetalhesProduto ),dados.getString( indexDescricao ), dados.getString( indexOportunidadeDestaque ), 
										dados.getString( indexOportunidadeVenda ),  dados.getString( indexDataUltimaAtualizacao ) ,
										dados.getInt( indexContadorAtualizacao ),dados.getInt( indexContadorFoto ),null,dados.getString( indexExisteEmEstoque ),
										dados.getString( indexStatusProduto ));
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDetalhesProduto( dados.getString( indexDetalhesProduto ) );
					model.setDescricao( dados.getString( indexDescricao ) );
					model.setOportunidadeDestaque( dados.getString( indexOportunidadeDestaque ) );
					model.setOportunidadeVenda( dados.getString( indexOportunidadeVenda ) );
					model.setDataUltimaAtualizacao( dados.getString( indexDataUltimaAtualizacao ));
					model.setContadorAtualizacao( dados.getInt( indexContadorAtualizacao ) );
					model.setContadorFoto( dados.getInt( indexContadorFoto ) );
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
					model.setClassificacao(new ClassificacaoVO( dados.getString (indexCodigoClassificacao)) );
					model.setGrupoProduto(new GrupoProdutoVO( dados.getString( indexCodigoGrupoProduto )) );
					model.setFamilia(new FamiliaVO( dados.getString( indexCodigoFamilia )) );
					

					produtos.add(model);
				}while(dados.moveToNext());
			}

			return produtos;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ProdutoVO>();
	}
	
}
