package br.com.bplm.yesbrasil.model;

import android.content.ContentValues;
import android.database.Cursor;

public class ImagemFolder {

	private Integer id;
	private String arquivo;

	public ImagemFolder() {
		super();
	}

	public ImagemFolder(String arquivo) {
		super();
		this.setArquivo( arquivo );
	}
	
	public ImagemFolder(Integer id, String arquivo) {
		this.setId( id );
		this.setArquivo( arquivo );
	}

	public ImagemFolder(Cursor cursor) {
		this.setId( cursor.getInt( 0 ) );
		this.setArquivo( cursor.getString( 1 ) );
	}

	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();
		values.put( "nome_arquivo" , this.getArquivo() );
		return values;
	}

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo( String arquivo ) {
		this.arquivo = arquivo;
	}
}
