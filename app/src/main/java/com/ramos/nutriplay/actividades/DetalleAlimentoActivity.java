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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_alimento);
        int id = getResources().getIdentifier("yourpackagename:drawable/" + "material_flat.png", null, null);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        ImageView placePicutre = (ImageView) findViewById(R.id.imagen_alimento);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/nutri-846ac.appspot.com/o/Alimentos%2FManzana%2Fmanzana_flat.jpg?alt=media&token=aa247299-3163-48e0-9679-f4b68c3651b1").into(placePicutre);

        //placePicutre.setImageDrawable(getResources().getDrawable(R.drawable.material_flat));
        descripcion = (TextView)findViewById(R.id.cosa);

        Bundle data = getIntent().getExtras();
        String beneficio = data.getString("DATOS_ALIMENTO");
        descripcion.setText(beneficio);

    }
}
