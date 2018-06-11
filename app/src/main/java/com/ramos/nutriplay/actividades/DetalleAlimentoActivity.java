package com.ramos.nutriplay.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ramos.nutriplay.R;
import com.ramos.nutriplay.modelos.Alimento;

public class DetalleAlimentoActivity extends AppCompatActivity {
    private TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_alimento);
        descripcion = (TextView)findViewById(R.id.cosa);

        Bundle data = getIntent().getExtras();
        String beneficio = data.getString("DATOS_ALIMENTO");
        descripcion.setText(beneficio);

    }
}
