/**
 * 
 */
package br.com.gv8.yeschamix.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe que representa
 *
 *
 * @author Bruno Pelogia <bruno.pelogia@gmail.com>
 * @since 07/08/2013 17:34:31
 * @version 1.0
 */

@XmlRootElement
public class ConfigDTO{
	
	private String login;
	private String driver;
	private String url;
	private String user;
	private String password;

	public ConfigDTO(){
		
	}

	public ConfigDTO( String login, String driver, String url, String user, String password ){
	    super();
	    setLogin( login );
	    setDriver( driver );
	    setUrl( url );
	    setUser( user );
	    setPassword( password );
    }

	
	public final String getLogin() {
    	return login;
    }

	public final void setLogin( String login ) {
    	this.login = login;
    }

	public final String getDriver() {
    	return driver;
    }

	public final void setDriver( String driver ) {
    	this.driver = driver;
    }

	public final String getUrl() {
    	return url;
    }

	public final void setUrl( String url ) {
    	this.url = url;
    }


	public final String getUser() {
    	return user;
    }

	public final void setUser( String user ) {
    	this.user = user;
    }

	public final String getPassword() {
    	return password;
    }

	public final void setPassword( String password ) {
    	this.password = password;
    }

	@Override
    public String toString() {
	    return "ConfigDTO [getLogin()=" + getLogin() + ", getDriver()=" + getDriver() + ", getUrl()=" + getUrl() + ", getUser()=" + getUser() + ", getPassword()=" + getPassword() + "]";
    }
	
	
	
}
