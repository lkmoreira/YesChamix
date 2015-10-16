package br.com.bplm.yesbrasil.util;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by pelogia on 16/10/15.
 */
@SharedPref(value = SharedPref.Scope.APPLICATION_DEFAULT)
public interface Preferences {

    @DefaultString("")
    String databaseURL();

    @DefaultString("")
    String userDatabase();

    @DefaultString("")
    String passDatabase();

    @DefaultString("")
    String driveDatabase();

    @DefaultString("")
    String caminhoFTP();

    @DefaultString("")
    String userFTP();

    @DefaultString("")
    String passFTP();

    @DefaultString("")
    String pastaPrincipalFTP();

    @DefaultString("")
    String pastaProduto();

    @DefaultString("")
    String pastaFamilia();

    @DefaultString("")
    String pastaFolders();

    @DefaultString("")
    String caminhoPasta();

    @DefaultString("")
    String login();

    @DefaultString("")
    String codPreco();

    @DefaultString("")
    String senha();

    @DefaultString("")
    String usuarioLogado();

    @DefaultBoolean(true)
    Boolean primeiraVez();

    @DefaultBoolean(false)
    Boolean precoSelecionado();

    @DefaultBoolean(false)
    Boolean isBancoConfigurado();

    @DefaultInt(0)
    Integer localErro();

}
