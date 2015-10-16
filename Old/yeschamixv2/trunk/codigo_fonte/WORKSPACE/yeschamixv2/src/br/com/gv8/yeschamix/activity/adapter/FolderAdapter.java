package br.com.gv8.yeschamix.activity.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.gv8.yeschamix.activity.R;


public class FolderAdapter extends BaseAdapter {

    private List<String> lstFolders;
    private LayoutInflater inflater;

    public FolderAdapter(Context context, List<String> tiposFolders) {
	this.lstFolders = tiposFolders;
	inflater = (LayoutInflater) context
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
	return lstFolders.size();
    }

    @Override
    public Object getItem(int position) {
	return lstFolders.get(position);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    // Criada esta classe estática para guardar a referência dos objetos
    // abaixo
    static class ViewHolder {
	public TextView txtTipoFolder;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	try {
	    String tipoFolder = lstFolders.get(position);
	    ViewHolder holder;
	    // Quando o objeto convertView não for nulo nós não precisaremos
	    // inflar
	    // os objetos do XML, ele será nulo quando for a primeira vez que
	    // for carregado
	    if (convertView == null) {
		convertView = inflater
			.inflate(R.layout.grupo_produto_row, null);

		// Cria o Viewholder e guarda a instância dos objetos
		holder = new ViewHolder();
		holder.txtTipoFolder = (TextView) convertView
			.findViewById(R.id.txtGrupoProduto);
		convertView.setTag(holder);
	    } else {
		// pega o ViewHolder para ter um acesso rápido aos objetos do
		// XML
		// ele sempre passará por aqui quando,por exemplo, for efetuado
		// uma rolagem na tela
		holder = (ViewHolder) convertView.getTag();
	    }

	    holder.txtTipoFolder.setText(tipoFolder);
	    return convertView;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return convertView;
    }
}
