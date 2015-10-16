/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 19:27:57
 * @version 1.0 
 */
package br.com.gv8.yeschamix.produto.model.persistence;

import java.util.List;

import br.com.gv8.yeschamix.classificacao.model.persistence.ClassificacaoVO;
import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;
import br.com.gv8.yeschamix.grupoproduto.model.persistence.GrupoProdutoVO;
import br.com.gv8.yeschamix.imagem.model.persistence.ImagemProdutoVO;

/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 19:27:57
 * * @since 21/05/2013 - 17:26
 */
public class ProdutoVO {
	
	private String id;
	private ClassificacaoVO classificacao;
	private GrupoProdutoVO grupoProduto;
	private FamiliaVO familia;
	private String detalhesProduto;
	private String descricao;
	private String oportunidadeDestaque;
	private String oportunidadeVenda;
	private String dataUltimaAtualizacao;
	private Integer contadorAtualizacao;
	private Integer contadorFoto;
	private List<ImagemProdutoVO> imagensProduto;
	private String existeEmEstoque;
	private String statusProduto;
	
	public ProdutoVO(){}
	
	public ProdutoVO(String id, ClassificacaoVO classificacao, GrupoProdutoVO grupoProduto, FamiliaVO familia, String detalhesProduto,
						String descricao, String oportunidadeDestaque, String oportunidadeVenda, String dataUltimaAtualizacao,
						Integer contadorAtualizacao, Integer contadorFoto, List<ImagemProdutoVO> imagensProduto, String existeEmEstoque,
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
	
	public ProdutoVO(String id){
		setId( id );
	}
	
	public String getId() {
		return id;
	}
	public void setId( String id ) {
		this.id = id;
	}
	public ClassificacaoVO getClassificacao() {
		return classificacao;
	}
	public void setClassificacao( ClassificacaoVO classificacao ) {
		this.classificacao = classificacao;
	}
	public GrupoProdutoVO getGrupoProduto() {
		return grupoProduto;
	}
	public void setGrupoProduto( GrupoProdutoVO grupoProduto ) {
		this.grupoProduto = grupoProduto;
	}
	public FamiliaVO getFamilia() {
		return familia;
	}
	public void setFamilia( FamiliaVO familia ) {
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
	public List< ImagemProdutoVO > getImagensProduto() {
		return imagensProduto;
	}
	public void setImagensProduto( List< ImagemProdutoVO > imagensProduto ) {
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
