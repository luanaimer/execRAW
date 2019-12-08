package br.edu.ifsc.rawexec;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("teste", "0");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.i("teste", "1");

        SQLiteDatabase bd = openOrCreateDatabase("meubanco", MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE  IF NOT EXISTS  alunos ( id INTEGER PRIMARY KEY AUTOINCREMENT , nome VARCHAR ) ");
        bd.execSQL("INSERT INTO alunos (nome) VALUES ('Luana' )");

        Log.i("teste", "2");

        Cursor cursor = bd.rawQuery("SELECT id, nome  FROM  alunos ", null);
        cursor.moveToFirst();

        Log.i("teste", "3");

        ArrayList<String> itens = new ArrayList<>();
        do {
            String s = cursor.getString( cursor.getColumnIndex("nome"));
            Log.i(" Resultado Sql :",s );
            itens.add(s);
        }while (cursor.moveToNext()) ;

        Log.i("teste", "4");

        ListView list;
        list = findViewById(R.id.listView);
        //ArrayAdaptor
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );

        Log.i("teste", "5");

        list.setAdapter(adapter);





    }
}
