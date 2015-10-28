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
    String pathFTP();

    @DefaultString("")
    String userFTP();

    @DefaultString("")
    String passwordFTP();

    @DefaultString("")
    String mainFolderFTP();

    @DefaultString("")
    String productFolder();

    @DefaultString("")
    String familyFolder();

    @DefaultString("")
    String folderersFolder();

    @DefaultString("")
    String pathFolder();

    @DefaultString("")
    String login();

    @DefaultString("")
    String proceCod();

    @DefaultString("")
    String password();

    @DefaultString("")
    String loggedUser();

    @DefaultBoolean(true)
    boolean isFirstTime();

    @DefaultBoolean(false)
    boolean selectedPrice();

    @DefaultBoolean(false)
    boolean isDatabaseConfigured();

    @DefaultInt(0)
    int localError();

}
