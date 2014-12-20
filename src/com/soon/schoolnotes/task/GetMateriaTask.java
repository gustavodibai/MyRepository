package com.soon.s.task;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

import com.soon.schoolnotes.database.MateriaDAO;
import com.soon.schoolnotes.entity.Materia;

public class GetMateriaTask extends AsyncTask<Void, Void, List<Materia>> {
	private Context context;
	private GetMateriasTaskListener listener;
	
	public GetMateriaTask(Context context, GetMateriasTaskListener listener) {
		this.context = context;
		this.listener = listener;
	}
	
	
	@Override
	protected List<Materia> doInBackground(Void... params) {
		
		MateriaDAO dao = new MateriaDAO(context);
		
		return dao.getMateria();
	}
	
	@Override
	protected void onPostExecute(List<Materia> result) {
		super.onPostExecute(result);
		if (listener != null) {
			listener.onResultMaterias(result);
		}
	}
	
	public interface GetMateriasTaskListener {
		public void onResultMaterias(List<Materia> materias);
	}

}
