/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 19:24:01
 * @version 1.0 
 */
package br.com.bplm.yesbrasil.model;


/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 19:24:01
 * * @since 21/05/2013 - 17:08
 */
public class GrupoProduto {
	
	private String id;
	private String descricao;
	private Familia familia;
	
	public GrupoProduto(){}
	
	public GrupoProduto(String id, String descricao, Familia familia){
		setId( id);
		setDescricao( descricao );
		setFamilia( familia );
	}
	
	public GrupoProduto(String id){
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

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia( Familia familia ) {
		this.familia = familia;
	}
	
	
	
	
	
}