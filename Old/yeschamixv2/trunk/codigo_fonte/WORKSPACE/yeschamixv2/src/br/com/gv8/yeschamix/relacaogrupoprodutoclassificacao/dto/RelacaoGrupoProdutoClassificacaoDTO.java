package br.com.gv8.yeschamix.relacaogrupoprodutoclassificacao.dto;

public class RelacaoGrupoProdutoClassificacaoDTO {

    private String idFamilia;
    private String idGrupoProduto;
    private String idClassificacao;
    private String descClassificacao;

    /**
     * @param idFamilia
     * @param idGrupoProduto
     * @param idClassificacao
     */
    public RelacaoGrupoProdutoClassificacaoDTO(String idFamilia,
	    String idGrupoProduto, String idClassificacao, String descClassificacao) {
	super();
	setIdFamilia( idFamilia );
	setIdGrupoProduto( idGrupoProduto );
	setIdClassificacao( idClassificacao );
	setDescClassificacao( descClassificacao );
    }

    /**
     * @return the idFamilia
     */
    public String getIdFamilia() {
	return idFamilia;
    }

    /**
     * @param idFamilia
     *            the idFamilia to set
     */
    public void setIdFamilia(String idFamilia) {
	this.idFamilia = idFamilia;
    }

    /**
     * @return the idGrupoProduto
     */
    public String getIdGrupoProduto() {
	return idGrupoProduto;
    }

    /**
     * @param idGrupoProduto
     *            the idGrupoProduto to set
     */
    public void setIdGrupoProduto(String idGrupoProduto) {
	this.idGrupoProduto = idGrupoProduto;
    }

    /**
     * @return the idClassificacao
     */
    public String getIdClassificacao() {
	return idClassificacao;
    }

    /**
     * @param idClassificacao
     *            the idClassificacao to set
     */
    public void setIdClassificacao(String idClassificacao) {
	this.idClassificacao = idClassificacao;
    }

    /**
     * @return the descClassificacao
     */
    public String getDescClassificacao() {
        return descClassificacao;
    }

    /**
     * @param descClassificacao the descClassificacao to set
     */
    public void setDescClassificacao(String descClassificacao) {
        this.descClassificacao = descClassificacao;
    }

}
