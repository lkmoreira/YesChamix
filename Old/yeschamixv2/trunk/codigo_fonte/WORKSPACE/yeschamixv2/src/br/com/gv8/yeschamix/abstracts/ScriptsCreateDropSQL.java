package br.com.gv8.yeschamix.abstracts;

import br.com.gv8.yeschamix.classificacao.dao.ClassificacaoDAO;
import br.com.gv8.yeschamix.familia.dao.FamiliaDAO;
import br.com.gv8.yeschamix.grupoproduto.dao.GrupoProdutoDAO;
import br.com.gv8.yeschamix.imagem.dao.ImagemFolderDAO;
import br.com.gv8.yeschamix.imagem.dao.ImagemProdutoDAO;
import br.com.gv8.yeschamix.preco.dao.PrecoDAO;
import br.com.gv8.yeschamix.produto.dao.ProdutoDAO;
import br.com.gv8.yeschamix.relacaoimagemproduto.dao.RelacaoImagemProdutoDAO;
import br.com.gv8.yeschamix.relacaousuariopreco.dao.RelacaoUsuarioPrecoDAO;
import br.com.gv8.yeschamix.usuario.dao.UsuarioDAO;



public abstract class ScriptsCreateDropSQL {
	public static String[] SQL_CREATES =
	{

		"CREATE TABLE "+ ClassificacaoDAO.NOME_TABELA + " " +
		"(" + ClassificacaoDAO.ID + 			" VARCHAR(4) primary key, " +
		ClassificacaoDAO.COD_FAMILIA + 			" VARCHAR(2), "+
		ClassificacaoDAO.COD_GRUPO_PRODUTO+		" VARCHAR(4), "+
		ClassificacaoDAO.DESCRICAO + 			" VARCHAR(40));"
		
		
		,/*Proxima tabela a ser Criada*/	

		"CREATE TABLE "+ FamiliaDAO.NOME_TABELA + " " +
		"(" + FamiliaDAO.ID + 	 	" VARCHAR(2) primary key, " +
		FamiliaDAO.ORDEM + 		 	" INTEGER, " +
		FamiliaDAO.NOME_ARQUIVO + 	" VARCHAR(20), " +
		FamiliaDAO.DESCRICAO + 	 	" VARCHAR(40));"

		,/*Proxima tabela a ser Criada*/


		"CREATE TABLE "+ GrupoProdutoDAO.NOME_TABELA + " " +
		"(" + GrupoProdutoDAO.ID + 				" VARCHAR(4) primary key, " +
		GrupoProdutoDAO.COD_FAMILIA+			" VARCHAR(2), "+
		GrupoProdutoDAO.DESCRICAO + 			" VARCHAR(40));"

		,/*Proxima tabela a ser Criada*/

		"CREATE TABLE "+ ImagemProdutoDAO.NOME_TABELA + " " +
		"(" + ImagemProdutoDAO.ID + 			" INTEGER primary key AUTOINCREMENT, " +
		ImagemProdutoDAO.NOME_ARQUIVO + 		" VARCHAR(20), "+
		ImagemProdutoDAO.COR + 					" VARCHAR(20), "+
		ImagemProdutoDAO.COD_PRODUTO+ 			" VARCHAR(15), " +
		ImagemProdutoDAO.EXISTE_EM_ESTOQUE +	" VARCHAR(1), " +
		ImagemProdutoDAO.STATUS_PRODUTO + 		" VARCHAR(1)); "
		
		,/*Proxima tabela a ser Criada*/
		
		"CREATE TABLE "+ ImagemFolderDAO.NOME_TABELA + " " +
		"(" + ImagemFolderDAO.ID + 			" INTEGER primary key AUTOINCREMENT, " +
		ImagemFolderDAO.NOME_ARQUIVO + 		" VARCHAR(20));"
		

		,/*Proxima tabela a ser Criada*/


		"CREATE TABLE " + PrecoDAO.NOME_TABELA + " " +
		"("+PrecoDAO.CODIGO+ 		" INTEGER primary key AUTOINCREMENT, "+ 
		PrecoDAO.ID + 				" VARCHAR(3), "+
		PrecoDAO.PRECO + 			" DOUBLE, "+
		PrecoDAO.COD_PRODUTO + 		" VARCHAR(15));"

	,

	"CREATE TABLE "+ ProdutoDAO.NOME_TABELA + " " +
		"(" + ProdutoDAO.ID + 						" VARCHAR(15) primary key, " +
		ProdutoDAO.DESCRICAO + 						" VARCHAR(50), " +
		ProdutoDAO.DETALHES_PRODUTO+				" TEXT, " +
		ProdutoDAO.COD_FAMILIA + 					" VARCHAR(2), " +
		ProdutoDAO.COD_GRUPO_PRODUTO + 				" VARCHAR(4), " +
		ProdutoDAO.OPORTUNIDADE_DESTAQUE + 			" VARCHAR(1), " +
		ProdutoDAO.OPORTUNIDADE_VENDA + 			" VARCHAR(1), " +
		ProdutoDAO.DATA_ULTIMA_ATUALIZACAO + 		" DATE(10), " +
		ProdutoDAO.COD_CLASSIFICACAO + 				" VARCHAR(4), " +
		ProdutoDAO.CONTADOR_ATUALIZACAO + 			" INTEGER, " +
		ProdutoDAO.CONTADOR_FOTO +					" INTEGER, " +
		ProdutoDAO.EXISTE_EM_ESTOQUE +			    " VARCHAR(1), " +
		ProdutoDAO.STATUS_PRODUTO + 	  	  		" VARCHAR(1)); "
		

		,/*Proxima tabela a ser Criada*/


		"CREATE TABLE "+ RelacaoUsuarioPrecoDAO.NOME_TABELA + " " +
		"(" + RelacaoUsuarioPrecoDAO.COD_PRECO + 	" VARCHAR(3), " +
		RelacaoUsuarioPrecoDAO.COD_USUARIO + 		" INTEGER );"

		,/*Proxima tabela a ser Criada*/

		"CREATE TABLE "+ UsuarioDAO.NOME_TABELA + " " +
		"(" + UsuarioDAO.ID + 		" INTEGER primary key, " +
		UsuarioDAO.NOME + 			" VARCHAR(20), " +
		UsuarioDAO.LOGIN + 			" VARCHAR(10), " +
		UsuarioDAO.SENHA + 			" VARCHAR(10), " +
		UsuarioDAO.BLOQUEADO +		" INTEGER );"

		
		,/*Proxima tabela a ser Criada*/
		
		"CREATE TABLE "+ RelacaoImagemProdutoDAO.NOME_TABELA + " " +
		"(" + RelacaoImagemProdutoDAO.COD_IMAGEM + 	" INTEGER, " +
		RelacaoImagemProdutoDAO.COD_PRODUTO + 		" VARCHAR(15));"
		
	};
	public static String[] SQL_DROPS =
	{
		"DROP TABLE IF EXISTS "  + ClassificacaoDAO.NOME_TABELA,
		"DROP TABLE IF EXISTS "  + FamiliaDAO.NOME_TABELA,
		"DROP TABLE IF EXISTS "  + GrupoProdutoDAO.NOME_TABELA,
		"DROP TABLE IF EXISTS "  + ImagemProdutoDAO.NOME_TABELA,
		"DROP TABLE IF EXISTS "  + ImagemFolderDAO.NOME_TABELA,
		"DROP TABLE IF EXISTS "  + PrecoDAO.NOME_TABELA,
		"DROP TABLE IF EXISTS "  + ProdutoDAO.NOME_TABELA,
		"DROP TABLE IF EXISTS "  + RelacaoImagemProdutoDAO.NOME_TABELA,
		"DROP TABLE IF EXISTS "  + RelacaoUsuarioPrecoDAO.NOME_TABELA,
		"DROP TABLE IF EXISTS "  + UsuarioDAO.NOME_TABELA

};
}
