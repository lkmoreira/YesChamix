package br.com.gv8.yeschamix.relacaogrupoprodutofamilia.dto;

public class RelacaoGrupoProdutoFamiliaDTO {

    private String idGrupoProduto;
    private String idFamilia;
    private String descGrupoProduto;
    /**
     * @param idGrupoProduto
     * @param familia
     */
    public RelacaoGrupoProdutoFamiliaDTO(String idGrupoProduto, String familia, String descGrupoProduto) {
	super();
	setIdGrupoProduto( idGrupoProduto );
	setIdFamilia( familia );
	setDescGrupoProduto( descGrupoProduto );
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
     * @return the familia
     */
    public String getIdFamilia() {
	return idFamilia;
    }

    /**
     * @param familia
     *            the familia to set
     */
    public void setIdFamilia(String familia) {
	this.idFamilia = familia;
    }

    /**
     * @return the descGrupoProduto
     */
    public String getDescGrupoProduto() {
        return descGrupoProduto;
    }

    /**
     * @param descGrupoProduto the descGrupoProduto to set
     */
    public void setDescGrupoProduto(String descGrupoProduto) {
        this.descGrupoProduto = descGrupoProduto;
    }

}
