/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 19:27:57
 * @version 1.0 
 */
package br.com.bplm.yesbrasil.model;

import java.util.List;

/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 19:27:57
 * * @since 21/05/2013 - 17:26
 */
public class Produto {
	
	private String id;
	private Classificacao classificacao;
	private GrupoProduto grupoProduto;
	private Familia familia;
	private String detalhesProduto;
	private String descricao;
	private String oportunidadeDestaque;
	private String oportunidadeVenda;
	private String dataUltimaAtualizacao;
	private Integer contadorAtualizacao;
	private Integer contadorFoto;
	private List<ImagemProduto> imagensProduto;
	private String existeEmEstoque;
	private String statusProduto;
	
	public Produto(){}
	
	public Produto(String id, Classificacao classificacao, GrupoProduto grupoProduto, Familia familia, String detalhesProduto,
				   String descricao, String oportunidadeDestaque, String oportunidadeVenda, String dataUltimaAtualizacao,
				   Integer contadorAtualizacao, Integer contadorFoto, List<ImagemProduto> imagensProduto, String existeEmEstoque,
				   String statusProduto){
		setId( id );
		setClassificacao( classificacao );
		setGrupoProduto( grupoProduto );
		setFamilia( familia );
		setDetalhesProduto( detalhesProduto );
		setDescricao( descricao );
		setOportunidadeDestaque( oportunidadeDestaque );
		setOportunidadeVenda( oportunidadeVenda );
		setDataUltimaAtualizacao( dataUltimaAtualizacao );
		setContadorAtualizacao( contadorAtualizacao );
		setContadorFoto( contadorFoto );
		setImagensProduto( imagensProduto );
		setExisteEmEstoque( existeEmEstoque );
		setStatusProduto( statusProduto );
		
	}
	
	public Produto(String id){
		setId( id );
	}
	
	public String getId() {
		return id;
	}
	public void setId( String id ) {
		this.id = id;
	}
	public Classificacao getClassificacao() {
		return classificacao;
	}
	public void setClassificacao( Classificacao classificacao ) {
		this.classificacao = classificacao;
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
	public String getDetalhesProduto() {
		return detalhesProduto;
	}
	public void setDetalhesProduto( String detalhesProduto ) {
		this.detalhesProduto = detalhesProduto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}
	public String getOportunidadeDestaque() {
		return oportunidadeDestaque;
	}
	public void setOportunidadeDestaque( String oportunidadeDestaque ) {
		this.oportunidadeDestaque = oportunidadeDestaque;
	}
	public String getOportunidadeVenda() {
		return oportunidadeVenda;
	}
	public void setOportunidadeVenda( String oportunidadeVenda ) {
		this.oportunidadeVenda = oportunidadeVenda;
	}
	public String getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}
	public void setDataUltimaAtualizacao( String dataUltimaAtualizacao ) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}
	public Integer getContadorAtualizacao() {
		return contadorAtualizacao;
	}
	public void setContadorAtualizacao( Integer contadorAtualizacao ) {
		this.contadorAtualizacao = contadorAtualizacao;
	}
	public Integer getContadorFoto() {
		return contadorFoto;
	}
	public void setContadorFoto( Integer contadorFoto ) {
		this.contadorFoto = contadorFoto;
	}
	public List<ImagemProduto> getImagensProduto() {
		return imagensProduto;
	}
	public void setImagensProduto( List<ImagemProduto> imagensProduto ) {
		this.imagensProduto = imagensProduto;
	}
	public String getExisteEmEstoque() {
		return existeEmEstoque;
	}
	public void setExisteEmEstoque( String existeEmEstoque ) {
		this.existeEmEstoque = existeEmEstoque;
	}
	public String getStatusProduto() {
		return statusProduto;
	}
	public void setStatusProduto( String statusProduto ) {
		this.statusProduto = statusProduto;
	}
	
	
	
	
}
