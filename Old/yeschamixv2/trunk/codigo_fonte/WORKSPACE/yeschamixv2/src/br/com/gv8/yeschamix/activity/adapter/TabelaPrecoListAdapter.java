package br.com.gv8.yeschamix.activity.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.gv8.yeschamix.activity.R;
import br.com.gv8.yeschamix.relacaousuariopreco.model.persistence.RelacaoUsuarioPrecoVO;
import br.com.gv8.yeschamix.util.Utilidades;

public class TabelaPrecoListAdapter extends BaseAdapter {

	private List<RelacaoUsuarioPrecoVO> lista;
	private LayoutInflater inflater;
	public Activity activity;

	public TabelaPrecoListAdapter(Context context, List<RelacaoUsuarioPrecoVO> listaPrecos, Activity activity) {		
		this.lista = new ArrayList<RelacaoUsuarioPrecoVO>();
		this.lista.addAll(listaPrecos);
		if(lista.isEmpty()){
			lista.add( new RelacaoUsuarioPrecoVO() );
		}
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.activity = activity;
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

	// Classe onde fica a refer�ncia dos objetos
	static class ViewHolder {
		public 	TextView txvTabela;
		public  TextView txvTitulo;
	}
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		try {
			RelacaoUsuarioPrecoVO tabelaPreco = lista.get(position);
			ViewHolder holder;
			// Quando o objeto convertView não for nulo nós não precisaremos
			// inflar
			// os objetos do XML, ele será nulo quando for a primeira vez que
			// for carregado
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.lista_tabela_preco_row, null);

				// Cria o Viewholder e guarda a instancia dos objetos
				holder = new ViewHolder();
				
				TextView txvTabela = (TextView) convertView.findViewById(R.id.txvTabPreco);
				holder.txvTabela = txvTabela;
				
				
				convertView.setTag(holder);
			} else {
				// pega o ViewHolder para ter um acesso rápido aos objetos do
				// XML
				// ele sempre passará por aqui quando,por exemplo, for efetuado
				// uma rolagem na tela
				holder = (ViewHolder) convertView.getTag();
			}
				
				if(!Utilidades.isNullOrBlank( tabelaPreco.getPreco() )){
					String segmento = tabelaPreco.getPreco().getId();
					
					for(int i=0;i<segmento.length();i++){   
					       char ch = segmento.charAt(i);    
					       if(Character.isLetter(ch)){  
					           segmento = ch+"";    
					       }   
					  
					   }    
					
					holder.txvTabela.setText( "SEGMENTO "+segmento);
				}else{
					holder.txvTabela.setText( "N�O H� SEGMENTOS");
				}
			return convertView;

		} catch (Exception e) {
		}
		return convertView;
	}

}
