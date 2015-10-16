package br.com.gv8.yeschamix.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class MensagemAndroid extends MenuActivity {

    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	Bundle extras = getIntent().getExtras();
	String mensagem = extras.getString(MENSAGEM);
	String titulo = extras.getString(TITULO);
	String botao = extras.getString(BOTAO);
	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	alertDialog.setTitle(titulo);
	alertDialog.setMessage(mensagem);
	alertDialog.setButton(botao, new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int which) {
		startActivity(new Intent(getApplication(), InicialActivity.class));
		return;
	    }
	});
	alertDialog.show();
    }
}