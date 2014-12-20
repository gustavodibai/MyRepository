package com.soon.schoolnotes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetalheMateriaActivity extends Activity {
	
	private static EditText edit;
	private static Button plus;
	private static TextView textView;
	private static float materia = 0;
	public SharedPreferences save;
	public SharedPreferences.Editor editor;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe_materia);
		
		matter();
	}
	
	public void matter(){
		
		edit = (EditText)findViewById(R.id.edit_text);
		plus = (Button)findViewById(R.id.add_button);
		textView = (TextView)findViewById(R.id.text_view);
		
		save = getSharedPreferences("save",
                Context.MODE_PRIVATE);
		
		materia = save.getFloat("materia", 0);
		
		textView.setText(Float.toString(materia));
		
		
	 plus.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			String counter = edit.getText().toString();
			
			if (counter.equals("")) {
                Toast.makeText(getApplicationContext(),
                        "Write a note to add!", Toast.LENGTH_SHORT)
                        .show();
                 //se você não colocar nada no checkbox
                //vai dizer para digitar um valor númerico
			} else {
				float counterAsFloat = Float.parseFloat(counter);
				
			    	materia = materia + counterAsFloat;
			    	textView.setText(Float.toString(materia));
			}
			
		}
	});
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
		editor = save.edit();
		editor.putFloat("materia", materia);
		editor.commit();
	}
	
}

