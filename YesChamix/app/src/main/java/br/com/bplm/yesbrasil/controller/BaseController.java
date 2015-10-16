package br.com.bplm.yesbrasil.controller;


import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;


/**
 * Controller of the BaseActivity
 * Created by Lucas Moreira on 19/09/15.
 */
@EBean
public class BaseController {

    @RootContext
    protected BaseActivity baseActivity;

    @UiThread
    protected void showDialog() {
        baseActivity.showDialog();
    }

    @UiThread
    protected void hideDialog() {
        baseActivity.hideDialog();
    }

}