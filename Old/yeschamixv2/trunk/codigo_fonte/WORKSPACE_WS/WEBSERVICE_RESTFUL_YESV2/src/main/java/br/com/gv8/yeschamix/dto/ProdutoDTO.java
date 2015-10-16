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
public class ProdutoDTO {

	private String id;
	private String descricao;
	private String detalhesProduto;
	private FamiliaDTO familia;
	private GrupoProdutoDTO grupoProduto;
	private String oportunidadeDestaque;
	private String oportunidadeVenda;
	private String dataUltimaAtualizacao;
	private ClassificacaoDTO classificacao;
	private Integer contadorAtualizacao;
	private Integer contadorFoto;
	private String existeEmEstoque;
	private String statusProduto;

	public ProdutoDTO( String id , ClassificacaoDTO classificacao , GrupoProdutoDTO grupoProduto , FamiliaDTO familia , String detalhesProduto ,
			String descricao , String oportunidadeDestaque , String oportunidadeVenda , String dataUltimaAtualizacao , Integer contadorAtualizacao ,
			Integer contadorFoto , String existeEmEstoque , String statusProduto ) {
		this.id = id;
		this.classificacao = classificacao;
		this.grupoProduto = grupoProduto;
		this.familia = familia;
		this.detalhesProduto = detalhesProduto;
		this.descricao = descricao;
		this.oportunidadeDestaque = oportunidadeDestaque;
		this.oportunidadeVenda = oportunidadeVenda;
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
		this.contadorAtualizacao = contadorAtualizacao;
		this.contadorFoto = contadorFoto;
		this.existeEmEstoque = existeEmEstoque;
		this.statusProduto = statusProduto;

	}
	
	public ProdutoDTO() {
		
	}
	
	public ProdutoDTO(String id){
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


	public String getDetalhesProduto() {
		return detalhesProduto;
	}


	public void setDetalhesProduto( String detalhesProduto ) {
		this.detalhesProduto = detalhesProduto;
	}


	public FamiliaDTO getFamilia() {
		return familia;
	}


	public void setFamilia( FamiliaDTO familia ) {
		this.familia = familia;
	}


	public GrupoProdutoDTO getGrupoProduto() {
		return grupoProduto;
	}


	public void setGrupoProduto( GrupoProdutoDTO grupoProduto ) {
		this.grupoProduto = grupoProduto;
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


	public void setDataUltimaAtualizacao(String dataUltimaAtualizacao ) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}


	public ClassificacaoDTO getClassificacao() {
		return classificacao;
	}


	public void setClassificacao( ClassificacaoDTO classificacao ) {
		this.classificacao = classificacao;
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


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id+ " " +classificacao+ " " +grupoProduto+ " " +familia+ " " +detalhesProduto+ " " +descricao+ " " +oportunidadeDestaque+ " " +
		oportunidadeVenda+ " " +dataUltimaAtualizacao+ " " +contadorAtualizacao+ " " +contadorFoto+ " " +existeEmEstoque+ " " +statusProduto;
	}
}
