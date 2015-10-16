package br.com.gv8.yeschamix.dto;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings( "restriction" )
@XmlRootElement
public class GrupoProdutoDTO {
	
	private String id;
	private String descricao;
	private String nomeArquivo;
	
	public GrupoProdutoDTO(){}
	
	public GrupoProdutoDTO(String id, String descricao, String nomeArquivo){
		this.id = id;
		this.descricao = descricao;
		this.nomeArquivo = nomeArquivo;
		
	}
	
	public GrupoProdutoDTO(String id){
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




	public String getNomeArquivo() {
		return nomeArquivo;
	}




	public void setNomeArquivo( String nomeArquivo ) {
		this.nomeArquivo = nomeArquivo;
	}




	@Override
	public String toString() {
		
		return id + " " + descricao + nomeArquivo;
	}
}
