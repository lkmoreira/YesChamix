package br.com.gv8.yeschamix.activity.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.gv8.yeschamix.activity.R;
import br.com.gv8.yeschamix.grupoproduto.model.persistence.GrupoProdutoVO;


public class GrupoProdutoAdapter extends BaseAdapter {

    private List<GrupoProdutoVO> lista;
    private LayoutInflater inflater;

    public GrupoProdutoAdapter(Context context, List<GrupoProdutoVO> listaGrupoProduto) {
	this.lista = listaGrupoProduto;
	inflater = (LayoutInflater) context
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
	return lista.size();
    }

    @Override
    public Object getItem(int position) {
	return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    // Criada esta classe estática para guardar a referência dos objetos
    // abaixo
    static class ViewHolder {
	public TextView tvCategoria;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	try {
	    GrupoProdutoVO grupoProduto = lista.get(position);
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
		TextView texto = (TextView) convertView
			.findViewById(R.id.txtGrupoProduto);
		// texto.setTextColor(Color)
		holder.tvCategoria = texto;
		convertView.setTag(holder);
	    } else {
		// pega o ViewHolder para ter um acesso rápido aos objetos do
		// XML
		// ele sempre passará por aqui quando,por exemplo, for efetuado
		// uma rolagem na tela
		holder = (ViewHolder) convertView.getTag();
	    }

	    holder.tvCategoria.setText(grupoProduto.getDescricao());
	    return convertView;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return convertView;
    }
}
