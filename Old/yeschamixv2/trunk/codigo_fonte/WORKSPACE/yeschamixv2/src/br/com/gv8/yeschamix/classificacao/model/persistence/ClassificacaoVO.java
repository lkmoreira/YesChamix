/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 17:53:16
 * @version 1.0 
 */
package br.com.gv8.yeschamix.classificacao.model.persistence;

import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;
import br.com.gv8.yeschamix.grupoproduto.model.persistence.GrupoProdutoVO;

/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 17:53:16
 * @since 21/05/2013 - 17:05
 */
public class ClassificacaoVO {
	
	private String id;
	private String descricao;
	private GrupoProdutoVO grupoProduto;
	private FamiliaVO familia;
	
	public ClassificacaoVO (){}
	
	public ClassificacaoVO(String id, String descricao, GrupoProdutoVO grupoProduto, FamiliaVO familia){
		setId( id );
		setDescricao( descricao );
		setFamilia( familia );
		setGrupoProduto( grupoProduto );
	}
	
	public ClassificacaoVO(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId( String id ) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	public GrupoProdutoVO getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto( GrupoProdutoVO grupoProduto ) {
		this.grupoProduto = grupoProduto;
	}

	public FamiliaVO getFamilia() {
		return familia;
	}

	public void setFamilia( FamiliaVO familia ) {
		this.familia = familia;
	}
	
	
	
	
}