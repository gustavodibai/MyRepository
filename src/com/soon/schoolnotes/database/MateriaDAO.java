package com.soon.s.database;

import java.util.ArrayList;
import java.util.List;

import com.soon.schoolnotes.entity.Materia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MateriaDAO {
	
	private DBManager dbManager;
	private static final String ID = "id";
	private static final String NOME = "nome";
	private static final String TEXTO = "texto";
	private static final String TABLE = "contato";
	
	public MateriaDAO(Context context){
		
		dbManager =  new DBManager(context);
	}
	public boolean insert(Materia materia){
		SQLiteDatabase database = dbManager.getDatabase();//recupera banco
		
		ContentValues contentValues = new ContentValues();//valores 
		contentValues.put(NOME, materia.getNome());
		contentValues.put(TEXTO, materia.getTexto());
		
		long value = database.insert(TABLE, null, contentValues);//colunas que aceitam o valor null
		
		if(value == -1){//se o valor for igual a -1 é porque deu erro nos dados do banco
			return false;
		}
		return true;
	}
	
	public List<Materia> getMateria(){
		
		SQLiteDatabase database = dbManager.getDatabase();//cria uma nova Database utilizando os metodos da class DBManager
		List<Materia> materias =  new ArrayList<Materia>();//cria a lista de contatos
		
		Cursor cursor = database.query(TABLE, null, null, null, null, null, null);//cria um cursor
		                                                                         //cursor serve para
		                                                                         //navegar na list
		if(cursor.moveToFirst()){//pega o cursor e coloca no primeiro item
			do { //executa a primeira vez
				
				Materia materia = new Materia();//cria um novo contato
				//get e o set
				materia.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
				materia.setTexto(cursor.getString(cursor.getColumnIndex(TEXTO)));
				
				materias.add(materia);
				
				
			}while(cursor.moveToNext()); //move para o proximo //executa em loop 
			
		}
		return materias;//retorna contatos
	}
	public void close(){//fecha a lista 
		dbManager.close();
	}

}
