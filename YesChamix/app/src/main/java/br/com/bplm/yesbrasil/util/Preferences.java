package br.com.bplm.yesbrasil.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {

    public static final String DECRYPT_KEY = "d142f19d065ab4aef3d302108652044e";
    private SharedPreferences sharedPrefs = null;
    private Editor editor = null;
    private String databaseURL = "";
    private String userDatabase = "";
    private String passDatabase = "";
    private String driveDatabase = "";
    private String caminhoFTP = "";
    private String userFTP = "";
    private String passFTP = "";
    private String pastaPrincipalFTP = "";
    private String pastaProduto = "";
    private String pastaFamilia = "";
    private String pastaFolders = "";
    private String caminhoPasta = "";
    private String login = "";
    private String codPreco = "";
	private String senha = "";
	private String usuarioLogado = "";
	private Boolean primeiraVez = true;
	private Boolean precoSelecionado = false;
	private Integer localErro = 0;
	private Boolean isBancoConfigurado = false;

    public Preferences(Context context) {
	this.sharedPrefs = context.getSharedPreferences("PREFS_PRIVATE",
		Context.MODE_PRIVATE);
	this.editor = this.sharedPrefs.edit();
    }

    public String getValue(String key, String defaultvalue) {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	return this.sharedPrefs.getString(key, defaultvalue);
    }

    public void setValue(String key, String value) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString(key, value);

    }

    public String getDatabaseURL() {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	this.databaseURL = this.sharedPrefs.getString("databaseURL",
		databaseURL);
	return this.databaseURL;
    }

    public void setDatabaseURL(String databaseURL) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString("databaseURL", databaseURL);
    }

    /**
     * @return the userDatabase
     */
    public String getUserDatabase() {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	this.userDatabase = this.sharedPrefs.getString("userDatabase",
		userDatabase);
	return userDatabase;
    }

    /**
     * @param userDatabase
     *            the userDatabase to set
     */
    public void setUserDatabase(String userDatabase) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString("userDatabase", userDatabase);
    }

    /**
     * @return the passDatabase
     */
    public String getPassDatabase() {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	this.passDatabase = this.sharedPrefs.getString("passDatabase",
		passDatabase);
	return passDatabase;
    }

    /**
     * @param passDatabase
     *            the passDatabase to set
     */
    public void setPassDatabase(String passDatabase) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString("passDatabase", passDatabase);
    }

    /**
     * @return the caminhoFTP
     */
    public String getCaminhoFTP() {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	this.caminhoFTP = this.sharedPrefs.getString("caminhoFTP", caminhoFTP);
	return caminhoFTP;
    }

    /**
     * @param caminhoFTP
     *            the caminhoFTP to set
     */
    public void setCaminhoFTP(String caminhoFTP) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString("caminhoFTP", caminhoFTP);
    }

    /**
     * @return the userFTP
     */
    public String getUserFTP() {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	this.userFTP = this.sharedPrefs.getString("userFTP", userFTP);
	return userFTP;
    }

    /**
     * @param userFTP
     *            the userFTP to set
     */
    public void setUserFTP(String userFTP) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString("userFTP", userFTP);
    }

    /**
     * @return the passFTP
     */
    public String getPassFTP() {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	this.passFTP = this.sharedPrefs.getString("passFTP", passFTP);
	return passFTP;
    }

    /**
     * @param passFTP
     *            the passFTP to set
     */
    public void setPassFTP(String passFTP) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString("passFTP", passFTP);
    }

    /**
     * @return the pastaPrincipalFTP
     */
    public String getPastaPrincipalFTP() {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	this.pastaPrincipalFTP = this.sharedPrefs.getString(
		"pastaPrincipalFTP", pastaPrincipalFTP);
	return pastaPrincipalFTP;
    }

    /**
     * @param pastaPrincipalFTP
     *            the pastaPrincipalFTP to set
     */
    public void setPastaPrincipalFTP(String pastaPrincipalFTP) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString("pastaPrincipalFTP", pastaPrincipalFTP);
    }

    /**
     * @return the pastaProduto
     */
    public String getPastaProduto() {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	this.pastaProduto = this.sharedPrefs.getString("pastaProduto",
		pastaProduto);
	return pastaProduto;
    }

    /**
     * @param pastaProduto
     *            the pastaProduto to set
     */
    public void setPastaProduto(String pastaProduto) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString("pastaProduto", pastaProduto);
    }

    /**
     * @return the pastaFamilia
     */
    public String getPastaFamilia() {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	this.pastaFamilia = this.sharedPrefs.getString("pastaFamilia",
		pastaFamilia);
	return pastaFamilia;
    }

    /**
     * @param pastaFamilia
     *            the pastaFamilia to set
     */
    public void setPastaFamilia(String pastaFamilia) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString("pastaFamilia", pastaFamilia);
    }

    /**
     * @return the pastaFolders
     */
    public String getPastaFolders() {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	this.pastaFolders = this.sharedPrefs.getString("pastaFolders",
		pastaFolders);
	return pastaFolders;
    }

    /**
     * @param pastaFolders
     *            the pastaFolders to set
     */
    public void setPastaFolders(String pastaFolders) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString("pastaFolders", pastaFolders);
    }

    /**
     * @return the driveDatabase
     */
    public String getDriveDatabase() {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	this.driveDatabase = this.sharedPrefs.getString("driveDatabase",
		driveDatabase);
	return driveDatabase;
    }

    /**
     * @param driveDatabase
     *            the driveDatabase to set
     */
    public void setDriveDatabase(String driveDatabase) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString("driveDatabase", driveDatabase);
    }

    /**
     * @return the driveDatabase
     */
    public String getCaminhoPasta() {
	if (this.sharedPrefs == null) {
	    return "Unknown";
	}
	this.caminhoPasta = this.sharedPrefs.getString("caminhoPasta",
		caminhoPasta);
	return caminhoPasta;
    }

    /**
     * @param driveDatabase
     *            the driveDatabase to set
     */
    public void setCaminhoPasta(String caminhoPasta) {
	if (this.editor == null) {
	    return;
	}
	this.editor.putString("caminhoPasta", caminhoPasta);
    }

    public void save() {
	if (this.editor == null) {
	    return;
	}
	this.editor.commit();
    }
    
    /**
	 * @return the login
	 */
	public String getLogin() {
		if (this.sharedPrefs == null) {
			return "Unknown";
		}
		this.login = this.sharedPrefs.getString("login", login);
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		if (this.editor == null) {
			return;
		}
		this.editor.putString("login", login);
	}

	public String getSenha() {
		if (this.sharedPrefs == null) {
			return "Unknown";
		}
		this.senha = this.sharedPrefs.getString("senha", senha);
		return this.senha;
	}

	public void setSenha(String senhaAtual) {
		if (this.editor == null) {
			return;
		}
		this.editor.putString("senha", senhaAtual);
	}
	
	public String getUsuarioLogado() {
		if (this.sharedPrefs == null) {
			return "Unknown";
		}
		this.usuarioLogado = this.sharedPrefs.getString("usuarioLogado", usuarioLogado);
		return this.usuarioLogado;
	}

	public void setUsuarioLogado(String usuarioLogado) {
		if (this.editor == null) {
			return;
		}
		this.editor.putString("usuarioLogado", usuarioLogado);
	}
	
	public Boolean getPrimeiraVez() {
		if (this.sharedPrefs == null) {
			return false;
		}
		this.primeiraVez = this.sharedPrefs.getBoolean("primeiraVez", primeiraVez);
		return this.primeiraVez;
	}

	public void setPrimeiraVez(Boolean primeiraVez) {
		if (this.editor == null) {
			return;
		}
		this.editor.putBoolean("primeiraVez", primeiraVez);
	}
	
	public Boolean isPrecoSelecionado() {
		if (this.sharedPrefs == null) {
			return false;
		}
		this.precoSelecionado = this.sharedPrefs.getBoolean("precoSelecionado", precoSelecionado);
		return this.precoSelecionado;
	}

	public void isPrecoSelecionado(Boolean precoSelecionado) {
		if (this.editor == null) {
			return;
		}
		this.editor.putBoolean("precoSelecionado", precoSelecionado);
	}
	
	public String getCodPreco() {
		if (this.sharedPrefs == null) {
			return "Unknown";
		}
		this.codPreco = this.sharedPrefs.getString("codPreco", codPreco);
		return this.codPreco;
	}

	public void setCodPreco(String codPreco) {
		if (this.editor == null) {
			return;
		}
		this.editor.putString("codPreco", codPreco);
	}
	
	public Integer getLocalErro() {
		if (this.sharedPrefs == null) {
			return 0;
		}
		this.localErro = this.sharedPrefs.getInt("localErro", localErro);
		return this.localErro;
	}

	public void setLocalErro(Integer localErro) {
		if (this.editor == null) {
			return;
		}
		this.editor.putInt("localErro", localErro);
	}
	
	public Boolean isBancoConfigurado() {
		if (this.sharedPrefs == null) {
			return false;
		}
		this.isBancoConfigurado = this.sharedPrefs.getBoolean("isBancoConfigurado", isBancoConfigurado);
		return this.isBancoConfigurado;
	}

	public void isBancoConfigurado(Boolean isBancoConfigurado) {
		if (this.editor == null) {
			return;
		}
		this.editor.putBoolean("isBancoConfigurado", isBancoConfigurado);
	}
	
	
}
