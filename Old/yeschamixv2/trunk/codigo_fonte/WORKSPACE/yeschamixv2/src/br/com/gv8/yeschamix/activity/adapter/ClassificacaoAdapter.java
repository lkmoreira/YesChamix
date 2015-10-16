package br.com.gv8.yeschamix.activity.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.gv8.yeschamix.activity.R;
import br.com.gv8.yeschamix.classificacao.model.persistence.ClassificacaoVO;


public class ClassificacaoAdapter extends BaseAdapter {

    private List<ClassificacaoVO> lstClassificao;
    private LayoutInflater inflater;

    public ClassificacaoAdapter(Context context, List<ClassificacaoVO> listaClassificacao) {
	this.lstClassificao = listaClassificacao;
	inflater = (LayoutInflater) context
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
	return lstClassificao.size();
    }

    @Override
    public Object getItem(int position) {
	return lstClassificao.get(position);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    // Criada esta classe estática para guardar a referência dos objetos
    // abaixo
    static class ViewHolder {
	public TextView tvClassificacao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	try {
	    ClassificacaoVO classificacao = lstClassificao.get(position);
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
		holder.tvClassificacao = (TextView) convertView
			.findViewById(R.id.txtGrupoProduto);
		convertView.setTag(holder);
	    } else {
		// pega o ViewHolder para ter um acesso rápido aos objetos do
		// XML
		// ele sempre passará por aqui quando,por exemplo, for efetuado
		// uma rolagem na tela
		holder = (ViewHolder) convertView.getTag();
	    }

	    holder.tvClassificacao.setText(classificacao.getDescricao());
	    return convertView;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return convertView;
    }
}
