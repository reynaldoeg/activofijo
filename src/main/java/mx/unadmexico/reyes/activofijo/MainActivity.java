package mx.unadmexico.reyes.activofijo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnListAssets, btnAddAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListAssets = (Button) findViewById(R.id.btnAssetsList);
        btnAddAsset = (Button) findViewById(R.id.btnNewAsset);
        btnListAssets.setOnClickListener(this);
        btnAddAsset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnAssetsList:
                intent = new Intent(MainActivity.this,ListadoActivos.class);
                startActivity(intent);
                break;
            case R.id.btnNewAsset:
                intent = new Intent(MainActivity.this,NuevoActivo.class);
                startActivity(intent);
                break;
        }
    }
}
