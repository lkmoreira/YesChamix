/**
 * 
 */
package br.com.gv8.yeschamix.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Bruno Pelogia
 *
 */
@XmlRootElement
public class ClassificacaoDTO {
	
	private String id;
	private String descricao;
	
	public ClassificacaoDTO(){}
	
	public ClassificacaoDTO(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public ClassificacaoDTO(String id){
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
	
	@Override
	public String toString() {
		
		return  id + " " + descricao ;
	}
}
