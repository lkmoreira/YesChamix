package br.com.webservice.exemplo.dto;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings( "restriction" )
@XmlRootElement
public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private String endereco;
    
    public Cliente(){}
    
	public Cliente(int id, String nome, String cpf, String endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
	}
	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nome
	 */
	public final String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public final void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the cpf
	 */
	public final String getCpf() {
		return cpf;
	}
	/**
	 * @param cpf the cpf to set
	 */
	public final void setCpf(String cpf) {
		this.cpf = cpf;
	}
	/**
	 * @return the endereco
	 */
	public final String getEndereco() {
		return endereco;
	}
	/**
	 * @param endereco the endereco to set
	 */
	public final void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id + " " + nome + " " + cpf + " " + endereco;
	}
	
}