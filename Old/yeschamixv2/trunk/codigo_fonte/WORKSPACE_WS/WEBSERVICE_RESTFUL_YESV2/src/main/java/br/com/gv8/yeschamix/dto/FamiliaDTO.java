/**
 * 
 */
package br.com.gv8.yeschamix.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Bruno Pelogia
 *
 */

@SuppressWarnings( "restriction" )
@XmlRootElement
public class FamiliaDTO {
	
	private String id;
	private String descricao;
	private Integer ordem;
	private String nomeArquivo;
	
	
	public FamiliaDTO(){}
	
	public FamiliaDTO(String id, String descricao, Integer ordem, String nomeArquivo) {
		this.id = id;
		this.descricao = descricao;
		this.ordem = ordem;
		this.nomeArquivo = nomeArquivo;
	}
	
	public FamiliaDTO(String id){
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




	@Override
	public String toString() {
		
		return id + " " + descricao + " " + ordem + " " + nomeArquivo;
	}
}
