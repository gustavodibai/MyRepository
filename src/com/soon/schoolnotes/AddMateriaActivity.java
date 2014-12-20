package com.soon.s;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.soon.schoolnotes.entity.Materia;
import com.soon.schoolnotes.task.AddMateriaTask;
import com.soon.schoolnotes.task.AddMateriaTask.AddMateriaTaskListener;

public class AddMateriaActivity extends Activity {
	
	private EditText editNome;
	private EditText editDesc;
	private ImageView imageView;
	private static float m = 0;
	
	public ArrayList<Float> listaDeFloats = new ArrayList<Float>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_materia_activity);
		
		editNome = (EditText)findViewById(R.id.edit_nome);
		editDesc = (EditText)findViewById(R.id.edit_desc);
		
		imageView = (ImageView)findViewById(R.id.image_view);
		imageView.setOnClickListener(onClickSave);
	}
	
	private OnClickListener onClickSave = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Materia materia = new Materia();
			materia.setNome(editNome.getText().toString());
			materia.setTexto(editDesc.getText().toString());
			
			new AddMateriaTask(AddMateriaActivity.this, AddMateriaActivity.this.addMateriaTaskListener)
			.execute(materia);
			
			listaDeFloats.add(m);
			
		}
	};
	
   private AddMateriaTaskListener addMateriaTaskListener = new AddMateriaTaskListener() {
		
		@Override
		public void onResultAddMateria(Boolean result) {
			
			if(result) {
				Toast.makeText(AddMateriaActivity.this, "Matter saved successfully!", Toast.LENGTH_SHORT).show();
				finish();
			}
			else {
				Toast.makeText(AddMateriaActivity.this, "Error trying to save matter!", Toast.LENGTH_SHORT).show();
			}
			
		}
	};
	

}
