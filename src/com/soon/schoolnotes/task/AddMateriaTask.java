package com.soon.s.task;

import android.content.Context;
import android.os.AsyncTask;

import com.soon.schoolnotes.database.MateriaDAO;
import com.soon.schoolnotes.entity.Materia;

public class AddMateriaTask extends AsyncTask<Materia, Void, Boolean> {
	
	private Context context;
	private AddMateriaTaskListener listener;
	 
	public AddMateriaTask(Context context, AddMateriaTaskListener listner) {
		this.context = context;
		this.listener = listner;
	}

	@Override
	protected Boolean doInBackground(Materia... params) {
		
		Materia materia = params[0];
		
		MateriaDAO dao = new MateriaDAO(context);
		
		return dao.insert(materia);
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		
		if (listener != null) {
			listener.onResultAddMateria(result);
		}
	}
	
	public interface AddMateriaTaskListener {
		public void onResultAddMateria(Boolean result);
	}

}
