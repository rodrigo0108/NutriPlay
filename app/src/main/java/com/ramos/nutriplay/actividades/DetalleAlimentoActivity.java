package com.ramos.nutriplay.actividades;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramos.nutriplay.R;
import com.ramos.nutriplay.modelos.Alimento;
import com.squareup.picasso.Picasso;

public class DetalleAlimentoActivity extends AppCompatActivity {
    private TextView descripcion;
    private Alimento alimento_escogido;
    private  Bundle datos_recolectados;
    private ImageView imagen_flat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_alimento);

        imagen_flat = (ImageView) findViewById(R.id.imagen_alimento);
        descripcion = (TextView)findViewById(R.id.cosa);
        // Pasar el Collapsing Toolbar layout a la pantalla
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        //Recepción de alimentos de la clase Parcelable Alimento
        datos_recolectados = getIntent().getExtras();
        alimento_escogido = datos_recolectados.getParcelable("DATOS_ALIMENTO");

        //Establecimiento de propiedades
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(" ");

        //Establecimiento de datos que se mostrarán el la actividad Detalle
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/nutri-846ac.appspot.com/o/Alimentos%2FManzana%2Fmanzana_flat.jpg?alt=media&token=aa247299-3163-48e0-9679-f4b68c3651b1").into(imagen_flat);
        descripcion.setText(alimento_escogido.getNombre());

    }
}
