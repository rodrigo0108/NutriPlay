package com.ramos.nutriplay.actividades;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ramos.nutriplay.R;
import com.ramos.nutriplay.modelos.Alimento;
import com.ramos.nutriplay.modelos.CategoriaAlimento;
import com.ramos.nutriplay.modelos.Minerales;
import com.ramos.nutriplay.modelos.Origen;
import com.ramos.nutriplay.modelos.Pais;
import com.ramos.nutriplay.modelos.Vitaminas;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetalleAlimentoActivity extends AppCompatActivity {
    private TextView descripcion,txtPais,txtOrigen,txtTipo,beneficios,tips,calorias_cantidad,fibra_cantidad,proteinas_cantidad;
    private Alimento alimento_escogido;
    private Bundle datos_recolectados;
    private ImageView imagen_flat,pais,origen,tipo;
    private CollapsingToolbarLayout collapsingToolbar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbReferenciaCategoriaAlimento,dbReferenciaContinente,dbReferenciaPais;
    private CategoriaAlimento categoriaAlimento;
    private Origen orig;
    private Pais paiss;
    private PieChart pieChart;

    private static final String TAG = "DetalleAlimento";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_alimento);

        imagen_flat = (ImageView) findViewById(R.id.imagen_alimento);
        pais = (ImageView) findViewById(R.id.pais);
        origen = (ImageView) findViewById(R.id.origen);
        tipo = (ImageView) findViewById(R.id.tipo);
        descripcion = (TextView)findViewById(R.id.nombre);
        txtPais = (TextView)findViewById(R.id.txtPais);
        txtOrigen = (TextView)findViewById(R.id.txtOrigen);
        txtTipo = (TextView)findViewById(R.id.txtTipo);
        beneficios = (TextView)findViewById(R.id.beneficios);
        tips = (TextView)findViewById(R.id.tips);
        calorias_cantidad = (TextView)findViewById(R.id.calorias_cantidad);
        fibra_cantidad = (TextView)findViewById(R.id.fibra_cantidad);
        proteinas_cantidad = (TextView)findViewById(R.id.proteinas_cantidad);
        // Pasar el Collapsing Toolbar layout a la pantalla
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        //Inicialización de FirebaseDatabase
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbReferenciaCategoriaAlimento = firebaseDatabase.getReference("CategoriaAlimento");
        dbReferenciaContinente = firebaseDatabase.getReference("Continente");
        dbReferenciaPais = firebaseDatabase.getReference("Pais");

        //Inicialización de la librería de fuentes de texto
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/dosis-book.ttf").setFontAttrId(R.attr.fontPath).build());
        //Recepción de alimentos de la clase Parcelable Alimento
        datos_recolectados = getIntent().getExtras();
        alimento_escogido = datos_recolectados.getParcelable("DATOS_ALIMENTO");

        Log.d(TAG,alimento_escogido.getValorNutricional().getMinerales().toString());
        //Establecimiento de propiedades para el toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(" ");

        //Consulta Categoria Alimento
        dbReferenciaCategoriaAlimento.child(alimento_escogido.getCategoria_alimento()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    categoriaAlimento = dataSnapshot.getValue(CategoriaAlimento.class);
                }
                Log.d(TAG,categoriaAlimento.getImagen());
                Picasso.get().load(categoriaAlimento.getImagen()).into(tipo);
                txtTipo.setText(categoriaAlimento.getNombre());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Consulta Origen
        dbReferenciaContinente.child(alimento_escogido.getOrigen()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    orig = dataSnapshot.getValue(Origen.class);
                }
                Log.d(TAG,orig.getImagen());
                Picasso.get().load(orig.getImagen()).into(origen);
                txtOrigen.setText(orig.getNombre());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Consulta Pais
        dbReferenciaPais.child(alimento_escogido.getPais_productor()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    paiss = dataSnapshot.getValue(Pais.class);
                }
                Log.d(TAG,paiss.getBandera());
                Picasso.get().load(paiss.getBandera()).into(pais);
                txtPais.setText(paiss.getNombre());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Establecimiento de datos que se mostrarán el la actividad Detalle
        Picasso.get().load(alimento_escogido.getImagen_flat()).into(imagen_flat);
        //Log.d(TAG,categoriaAlimento.getImagen());
        descripcion.setText(alimento_escogido.getNombre().toUpperCase());
        String beneficioss = alimento_escogido.getBeneficios();
        beneficioss = beneficioss.replace("\\n", System.getProperty("line.separator"));
        beneficios.setText(beneficioss);
        String tipss = alimento_escogido.getTips();
        tipss = tipss.replace("\\n", System.getProperty("line.separator"));
        tips.setText(tipss);
        calorias_cantidad.setText(alimento_escogido.getValorNutricional().getCalorias());
        fibra_cantidad.setText(alimento_escogido.getValorNutricional().getFibra());
        proteinas_cantidad.setText(alimento_escogido.getValorNutricional().getProteinas());

        //Modificacion de datos

        //VITAMINAS
        /*Vitaminas vitaminas = alimento_escogido.getValorNutricional().getVitaminas();
        String vit_A = vitaminas.getVit_a();
        vit_A = vit_A.substring(0,vit_A.length() - 3);
        String vit_C = vitaminas.getVit_c();
        vit_C = vit_C.substring(0,vit_C.length() - 3);
        String vit_D = vitaminas.getVit_d();
        vit_D = vit_D.substring(0,vit_D.length() - 3);
        Log.e(TAG,vitaminas.toString());*/

        //MINERALES
        Minerales minerales = alimento_escogido.getValorNutricional().getMinerales();
        String calcio = minerales.getCalcio();
        calcio = calcio.substring(0,calcio.length() - 3);
        String fosforo = minerales.getFosforo();
        fosforo = fosforo.substring(0,fosforo.length() - 3);
        String hierro = minerales.getHierro();
        hierro = hierro.substring(0,hierro.length() - 3);
        String magnesio = minerales.getMagnesio();
        magnesio = magnesio.substring(0,magnesio.length() - 3);
        String potasio = minerales.getPotasio();
        potasio = potasio.substring(0,potasio.length() - 3);
        String sodio = minerales.getSodio();
        sodio = sodio.substring(0,sodio.length() - 3);
        Log.e(TAG,minerales.toString());
        //Codigo de PieChart
        pieChart = (PieChart)findViewById(R.id.chart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        //Cambio de vista
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        //pieChart.getLegend().setEnabled(false);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(Float.parseFloat(calcio), "Calcio"));
        yValues.add(new PieEntry(Float.parseFloat(fosforo), "Fósforo"));
        yValues.add(new PieEntry(Float.parseFloat(hierro), "Hierro"));
        yValues.add(new PieEntry(Float.parseFloat(magnesio), "Magnesio"));
        yValues.add(new PieEntry(Float.parseFloat(potasio), "Potasio"));
        yValues.add(new PieEntry(Float.parseFloat(sodio), "Sodio"));


        final int[] MY_COLORS = {Color.rgb(172, 138, 97), Color.rgb(189, 189, 104), Color.rgb(158, 48, 73  ),
                Color.rgb(204, 126, 82), Color.rgb(250, 215, 160), Color.rgb(26, 188, 156)};
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for(int c: MY_COLORS) colors.add(c);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setTextSize(12);
        l.setDrawInside(false);
        l.setXEntrySpace(10f);
        l.setYEntrySpace(1f);

        //Estableciendo los valores dentro del gráfico
        PieDataSet dataSet = new PieDataSet(yValues ,"");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(1f);
        dataSet.setColors(colors);
        pieChart.setDrawEntryLabels(false);
        PieData datos = new PieData((dataSet));
        datos.setValueTextSize(15f);
        datos.setValueTextColor(Color.WHITE);

        pieChart.setData(datos);

    }

    //Importante - Añadir este método es escencial para establecer el contexto base de la librería
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
