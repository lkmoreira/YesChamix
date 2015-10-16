package br.com.gv8.yeschamix.relacaoimagemproduto.controller;



public final class RelacaoImagemProdutoService{
	
//	private GrupoProdutoBOService service;

	/*SINGLETON*/
	private static RelacaoImagemProdutoService instance;
	
	private RelacaoImagemProdutoService(){
//		service = new GrupoProdutoBOServiceImpl();
	}
	
	public static RelacaoImagemProdutoService getInstance() {
		if( instance == null ){
			instance = new RelacaoImagemProdutoService();
		}
	    return instance;
    }
	/*SINGLETON*/
	
	
//	public ArrayList< GarconVO > consultarTodosGarcons(String urlWS) throws Exception {
//		return service.consultarTodosGarcons(urlWS);
//	}
}
