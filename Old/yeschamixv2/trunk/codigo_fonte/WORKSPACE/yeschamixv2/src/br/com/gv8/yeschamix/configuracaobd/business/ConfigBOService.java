package br.com.gv8.yeschamix.configuracaobd.business;




public interface ConfigBOService{
	
	public String configurarBD(String login,String driver,String url,String user,String password) throws Exception;
	
}
