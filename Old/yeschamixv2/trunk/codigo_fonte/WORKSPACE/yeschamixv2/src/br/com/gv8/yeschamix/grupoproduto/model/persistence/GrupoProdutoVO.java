/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 19:24:01
 * @version 1.0 
 */
package br.com.gv8.yeschamix.grupoproduto.model.persistence;

import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;


/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 19:24:01
 * * @since 21/05/2013 - 17:08
 */
public class GrupoProdutoVO {
	
	private String id;
	private String descricao;
	private FamiliaVO familia;
	
	public GrupoProdutoVO (){}
	
	public GrupoProdutoVO(String id, String descricao, FamiliaVO familia){
		setId( id);
		setDescricao( descricao );
		setFamilia( familia );
	}
	
	public GrupoProdutoVO(String id){
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

	public FamiliaVO getFamilia() {
		return familia;
	}

	public void setFamilia( FamiliaVO familia ) {
		this.familia = familia;
	}
	
	
	
	
	
}