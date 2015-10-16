/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 19:55:33
 * @version 1.0 
 */
package br.com.bplm.yesbrasil.model;

/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 19:55:33
 */
public class ImagemProduto {
	
	private Integer id;
	private String nomeArquivo;
	private String cor;
	private Produto produto;
	private String existeEmEstoque;
	private String statusProduto;
	
	public ImagemProduto(String nomeArquivo){
		setNomeArquivo( nomeArquivo );
	}
	
	public ImagemProduto(){}
	
	public ImagemProduto(Integer id, String nomeArquivo, String cor, Produto produto, String existeEmEstoque,
						 String statusProduto){
		setId( id );
		setNomeArquivo( nomeArquivo );
		setCor( cor );
		setProduto( produto );
		setExisteEmEstoque( existeEmEstoque );
		setStatusProduto( statusProduto );
		
	}
	
	public final String getCor() {
		return cor;
	}

	public final void setCor( String cor ) {
		this.cor = cor;
	}

	public ImagemProduto(Integer id){
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId( Integer id ) {
		this.id = id;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo( String nomeArquivo ) {
		this.nomeArquivo = nomeArquivo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto( Produto produto ) {
		this.produto = produto;
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
