package mx.unadmexico.reyes.activofijo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListadoActivos extends AppCompatActivity {

    DBHelper mydb = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_activos);
        addAsset();

        ArrayList descripciones = mydb.getAllAssets();
        ListView lstAssets = (ListView) findViewById(R.id.lstAssets);
        lstAssets.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, descripciones));

        lstAssets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int id_To_Search = position + 1;

            Bundle dataBundle = new Bundle();
            dataBundle.putInt("id", id_To_Search);

            Intent intent = new Intent(getApplicationContext(),DetalleActivo.class);

            intent.putExtras(dataBundle);
            startActivity(intent);
            }
        });
    }

    public void addAsset(){
        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.fabaddAsset);
        miFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(ListadoActivos.this, NuevoActivo.class);
            startActivity(intent);

            finish();
            }
        });
    }
}
