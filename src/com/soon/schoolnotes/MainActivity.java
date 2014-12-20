package com.soon.s;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.soon.schoolnotes.entity.Materia;
import com.soon.schoolnotes.task.GetMateriaTask;
import com.soon.schoolnotes.task.GetMateriaTask.GetMateriasTaskListener;

public class MainActivity extends Activity {
	
	private ImageView imageView;
	private ListView listView;
	private MateriasAdapter materiaAdapter;
	
	 public ArrayList<Float> listaDeFloats = new ArrayList<Float>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageView = (ImageView)findViewById(R.id.image_view);
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MainActivity.this, AddMateriaActivity.class);
				startActivity(intent);
			}
		});
		
		listView = (ListView)findViewById(R.id.list_view);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
				
				Materia materia = materiaAdapter.getItem(position);
				
				Bundle extras = new Bundle();
				extras.putSerializable("materia", materia);
				
				Intent intent = new Intent(MainActivity.this, DetalheMateriaActivity.class);
				intent.putExtras(extras);
				startActivity(intent);
				
				
			}
		});
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		new GetMateriaTask(this, listener).execute();
	}
	
	private GetMateriasTaskListener listener = new GetMateriasTaskListener() {
		
		@Override
		public void onResultMaterias(List<Materia> materias) {
			
			materiaAdapter = new MateriasAdapter(MainActivity.this, 
					R.layout.item_list_materia, materias);
			
			
			listView.setAdapter(materiaAdapter);

			
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
