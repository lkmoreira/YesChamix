/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 17:53:16
 * @version 1.0 
 */
package br.com.bplm.yesbrasil.model;

/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 17:53:16
 * @since 21/05/2013 - 17:05
 */
public class Classificacao {
	
	private String id;
	private String descricao;
	private GrupoProduto grupoProduto;
	private Familia familia;
	
	public Classificacao(){}
	
	public Classificacao(String id, String descricao, GrupoProduto grupoProduto, Familia familia){
		setId( id );
		setDescricao( descricao );
		setFamilia( familia );
		setGrupoProduto( grupoProduto );
	}
	
	public Classificacao(String id){
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

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto( GrupoProduto grupoProduto ) {
		this.grupoProduto = grupoProduto;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia( Familia familia ) {
		this.familia = familia;
	}
	
	
	
	
}