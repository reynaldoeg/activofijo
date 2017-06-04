package mx.unadmexico.reyes.activofijo;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NuevoActivo extends AppCompatActivity {

    DBHelper mydb;

    private EditText txtNumAct;
    private EditText txtCateg;
    private EditText txtDesCorta;
    private EditText txtDesLarga;
    private EditText txtMarca;

    private int Value;
    private boolean isUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_activo);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            Value = extras.getInt("id");
            isUpdate = true;

            Cursor rs = mydb.getData(Value);
            rs.moveToFirst();

            String numAct = rs.getString(rs.getColumnIndex(DBHelper.ASSETS_COLUMN_NUMBER));
            String categ = rs.getString(rs.getColumnIndex(DBHelper.ASSETS_COLUMN_CATEGORY));
            String sdesc = rs.getString(rs.getColumnIndex(DBHelper.ASSETS_COLUMN_SHORTDESC));
            String ldesc = rs.getString(rs.getColumnIndex(DBHelper.ASSETS_COLUMN_LONGDESC));
            String brand = rs.getString(rs.getColumnIndex(DBHelper.ASSETS_COLUMN_BRAND));

            txtNumAct   = (EditText) findViewById(R.id.txtNumAct);
            txtCateg    = (EditText) findViewById(R.id.txtCateg);
            txtDesCorta = (EditText) findViewById(R.id.txtDesCorta);
            txtDesLarga = (EditText) findViewById(R.id.txtDesLarga);
            txtMarca    = (EditText) findViewById(R.id.txtMarca);

            txtNumAct.setText(numAct);
            txtCateg.setText(categ);
            txtDesCorta.setText(sdesc);
            txtDesLarga.setText(ldesc);
            txtMarca.setText(brand);

        }else{
            isUpdate = false;
        }

        saveAsset();
    }

    public void saveAsset(){
        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.save);
        miFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtNumAct   = (EditText) findViewById(R.id.txtNumAct);
                txtCateg    = (EditText) findViewById(R.id.txtCateg);
                txtDesCorta = (EditText) findViewById(R.id.txtDesCorta);
                txtDesLarga = (EditText) findViewById(R.id.txtDesLarga);
                txtMarca    = (EditText) findViewById(R.id.txtMarca);

                String num   = txtNumAct.getText().toString();
                String cat   = txtCateg.getText().toString();
                String sdesc = txtDesCorta.getText().toString();
                String ldesc = txtDesLarga.getText().toString();
                String bran  = txtMarca.getText().toString();

                Boolean success;

                if(isUpdate){

                    success = mydb.updateAsset(Value, num, cat, sdesc, ldesc, bran);

                }else{

                    success = mydb.insertAsset(num, cat, sdesc, ldesc, bran);

                }

                if(success){
                    //Toast.makeText(getBaseContext(), getResources().getString(R.string.texto_titulo), Toast.LENGTH_SHORT).show();
                    Snackbar.make(v, getResources().getString(R.string.saved), Snackbar.LENGTH_LONG).show();

                    txtNumAct.getText().clear();
                    txtCateg.getText().clear();
                    txtDesCorta.getText().clear();
                    txtDesLarga.getText().clear();
                    txtMarca.getText().clear();

                }else{
                    Snackbar.make(v, getResources().getString(R.string.err_saved), Snackbar.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(NuevoActivo.this, ListadoActivos.class);
            startActivity(intent);

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
