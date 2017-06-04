package mx.unadmexico.reyes.activofijo;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetalleActivo extends AppCompatActivity {

    private DBHelper mydb;

    private TextView tvNumAct;
    private TextView tvCateg;
    private TextView tvDesCorta;
    private TextView tvDesLarga;
    private TextView tvMarca;

    private int Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_activo);

        deleteAsset();
        updateAsset();

        tvNumAct   = (TextView) findViewById(R.id.tvNumAct);
        tvCateg    = (TextView) findViewById(R.id.tvCateg);
        tvDesCorta = (TextView) findViewById(R.id.tvDesCorta);
        tvDesLarga = (TextView) findViewById(R.id.tvDesLarga);
        tvMarca    = (TextView) findViewById(R.id.tvMarca);

        //txtNumAct.setText(getResources().getString(R.string.app_name));

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            Value = extras.getInt("id");

            Cursor rs = mydb.getData(Value);
            rs.moveToFirst();

            String numAct = rs.getString(rs.getColumnIndex(DBHelper.ASSETS_COLUMN_NUMBER));
            String categ = rs.getString(rs.getColumnIndex(DBHelper.ASSETS_COLUMN_CATEGORY));
            String sdesc = rs.getString(rs.getColumnIndex(DBHelper.ASSETS_COLUMN_SHORTDESC));
            String ldesc = rs.getString(rs.getColumnIndex(DBHelper.ASSETS_COLUMN_LONGDESC));
            String brand = rs.getString(rs.getColumnIndex(DBHelper.ASSETS_COLUMN_BRAND));

            tvNumAct.setText(numAct);
            tvCateg.setText(categ);
            tvDesCorta.setText(sdesc);
            tvDesLarga.setText(ldesc);
            tvMarca.setText(brand);

        }
    }

    public void deleteAsset(){
        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.fabDelete);
        miFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydb.deleteAsset(Value);

                Intent intent = new Intent(DetalleActivo.this, ListadoActivos.class);
                startActivity(intent);

                finish();
            }
        });
    }

    public void updateAsset(){
        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.fabUpdate);
        miFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", Value);

                Intent intent = new Intent(DetalleActivo.this, NuevoActivo.class);
                intent.putExtras(dataBundle);
                startActivity(intent);

                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(DetalleActivo.this, ListadoActivos.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
