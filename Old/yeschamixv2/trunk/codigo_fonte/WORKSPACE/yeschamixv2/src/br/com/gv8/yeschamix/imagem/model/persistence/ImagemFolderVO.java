package br.com.gv8.yeschamix.imagem.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

public class ImagemFolderVO {

	private Integer id;
	private String arquivo;

	public ImagemFolderVO() {
		super();
	}

	public ImagemFolderVO( String arquivo ) {
		super();
		this.setArquivo( arquivo );
	}
	
	public ImagemFolderVO(Integer id, String arquivo ) {
		this.setId( id );
		this.setArquivo( arquivo );
	}

	public ImagemFolderVO( Cursor cursor ) {
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
