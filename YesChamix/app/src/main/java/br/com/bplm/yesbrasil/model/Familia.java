/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 17:55:07
 * @version 1.0 
 */
package br.com.bplm.yesbrasil.model;

/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 17:55:07
 * * @since 21/05/2013 - 17:06
 */
public class Familia {
	
	private String id;
	private String descricao;
	private Integer ordem;
	private String nomeArquivo;
	
	public Familia(){}
	
	public Familia(String id, String descricao, Integer ordem, String nomeArquivo){
		setId( id );
		setDescricao(descricao);
		setOrdem( ordem );
		setNomeArquivo( nomeArquivo );
	}
	
	public Familia(String id){
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
	public Integer getOrdem() {
		return ordem;
	}
	public void setOrdem( Integer ordem ) {
		this.ordem = ordem;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo( String nomeArquivo ) {
		this.nomeArquivo = nomeArquivo;
	}
	
	
	
	
	
	
}
