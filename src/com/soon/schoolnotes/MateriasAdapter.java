package com.soon.s;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.soon.schoolnotes.entity.Materia;

public class MateriasAdapter extends ArrayAdapter<Materia> {

	private int resource;
	
	public MateriasAdapter(Context context, int resource, List<Materia> objects) {
		super(context, resource, objects);
		this.resource = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder = null;
		
		if (convertView == null) {
			
			convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		Materia materia = getItem(position);
		
		viewHolder.textNome.setText(materia.getNome());
		viewHolder.textDesc.setText(materia.getTexto());
		
		
		return convertView;
	}
	
	
	private class ViewHolder {
		private TextView textNome;
		private TextView textDesc;
		
		public ViewHolder(View view) {
			textNome = (TextView)view.findViewById(R.id.text_nome);
			textDesc = (TextView)view.findViewById(R.id.text_desc);
		}
	}

}
