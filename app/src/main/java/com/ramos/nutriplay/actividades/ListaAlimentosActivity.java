package com.ramos.nutriplay.actividades;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibm.watson.developer_cloud.android.library.camera.CameraHelper;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassResult;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierResult;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import com.ramos.nutriplay.R;
import com.ramos.nutriplay.adaptadores.AlimentosAdapter;
import com.ramos.nutriplay.modelos.Alimento;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ListaAlimentosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAlimento;
    private List<Alimento> alimentos;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,databaseReference2;
    private AlimentosAdapter adapter;
    private Map<String,Boolean> coleccion_alimentos;
    private Map<String,String> id_alimentos;
    private VisualRecognition service;
    private CameraHelper helper;
    private ProgressDialog progressDialog;
    private CoordinatorLayout coordinatorLayout;
    private Dialog popupDescubierto;
    private Button botonAceptar;
    //PRUEBA
    private String uid_usuario="2asd2dn4r2b";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alimentos);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        recyclerViewAlimento = (RecyclerView)findViewById(R.id.recyclerViewAlimentos);
        alimentos = new ArrayList<>();
        coleccion_alimentos = new HashMap<String,Boolean>();
        id_alimentos = new HashMap<String, String>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Alimento");
        databaseReference2 = firebaseDatabase.getReference("Coleccion");
        //Inicialización del PopUp
        popupDescubierto = new Dialog(this);
        popupDescubierto.setCanceledOnTouchOutside(false);
        //Inicialización de IBM
        service = new VisualRecognition(getString(R.string.version));
        service.setEndPoint("https://gateway.watsonplatform.net/visual-recognition/api");

        IamOptions options = new IamOptions.Builder().apiKey(getString(R.string.api_key)).build();
        service.setIamCredentials(options);

        // Inicialización de camera helper
        helper = new CameraHelper(this);

        adapter = new AlimentosAdapter(alimentos,this,coleccion_alimentos);
        recyclerViewAlimento.setAdapter(adapter);
        recyclerViewAlimento.setHasFixedSize(true);
        // Pasando el padding de cada card
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.cards_padding);
        recyclerViewAlimento.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerViewAlimento.setLayoutManager(new GridLayoutManager(this,3));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                alimentos.removeAll(alimentos);
                for (DataSnapshot snapshot:
                         dataSnapshot.getChildren()) {
                    Alimento alimento= snapshot.getValue(Alimento.class);
                    alimento.setId(snapshot.getKey());
                    Log.d("ListaAlimentosActivity","El valor: "+snapshot.getValue());
                    alimentos.add(alimento);
                    id_alimentos.put(alimento.getId(),alimento.getNombre());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference2.child(uid_usuario).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()){
                    boolean estado = snapshot.getValue(Boolean.class);
                    coleccion_alimentos.put(snapshot.getKey(),estado);
                }
                adapter.notifyDataSetChanged();
                Log.d("ListaAlimentos",coleccion_alimentos.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            return true;
        }
        return false;
    }

    public void back(View view) {
        finishMyActivity();
    }

    @Override
    public void onBackPressed() {
        finishMyActivity();
    }

    public void finishMyActivity() {
        finish();
        overridePendingTransition(R.anim.back_in, R.anim.back_out);
    }
    private void mostrarSnackbar(){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Hay algunos problemas, verifica la conexión a Internet.", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void tomarFoto(View view) {
        //showDialog();
        if(isOnline()){
            helper.dispatchTakePictureIntent();
        }else{
            mostrarSnackbar();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            progressDialog = ProgressDialog.show(this, "Cargando", "El registro demomará unos segundos...", true);
            //you usually don't want the user to stop the current process, and this will make sure of that

            if (requestCode == CameraHelper.REQUEST_IMAGE_CAPTURE) {
                final File photoFile = helper.getFile(resultCode);
                //Por que
                Log.e("MainActivity", photoFile + "");

                if (photoFile == null)
                    return;

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ClassifyOptions classifyOptions = null;
                            classifyOptions = new ClassifyOptions.Builder()
                                    .imagesFile(photoFile)
                                    .imagesFilename(photoFile.getName())
                                    .threshold((float) 0.6)
                                    .acceptLanguage("es")
                                    .owners(Arrays.asList("me"))
                                    .classifierIds(Arrays.asList("Alimento_396195486"))
                                    .build();


                            final ClassifiedImages result = service.classify(classifyOptions).execute();

                            Log.e("MainActivty", result.toString());

                            ClassifierResult classifier = result.getImages().get(0).getClassifiers().get(0);
                            final List<String> alimentos_detectados = new ArrayList<String>();

                            //final StringBuffer output = new StringBuffer();
                            for (ClassResult object : classifier.getClasses()) {
                                if (object.getScore() > 0.7f)
                                    alimentos_detectados.add(object.getClassName());
                            }

                            Log.e("MainActivty", alimentos_detectados.toString());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (alimentos_detectados.isEmpty()) {
                                        Log.e("ListaAlimentosAc","El elemento detectado no es un alimento");
                                        MostrarPopUpDescubierto();
                                        progressDialog.dismiss();
                                    } else {
                                        Log.e("ListaAlimentosAc","El alimento es: " + alimentos_detectados.get(0));
                                        if(id_alimentos.containsValue(alimentos_detectados.get(0))){
                                            //Existe en la bd
                                            Log.e("ListaAlimentosAc","Alimento: "+alimentos_detectados.get(0)+" existe en la bd");
                                            getKeyFromValue(coleccion_alimentos, alimentos_detectados.get(0));
                                            if (coleccion_alimentos.get(""+getKeyFromValue(id_alimentos,alimentos_detectados.get(0)))){
                                                Log.e("ListaAlimentosAc","El alimento ya ha sido descubierto");
                                            }else{
                                                Log.e("ListaAlimentosAc","El alimento no ha sido descubierto");
                                            }
                                        }else{
                                            //No existe en la bd
                                            Log.e("ListaAlimentosAc","Alimento: "+alimentos_detectados.get(0)+" no existe en la bd");
                                        }
                                        progressDialog.dismiss();
                                    }
                                }
                            });

                        } catch (Throwable t){
                            t.printStackTrace();
                            Log.e("MainActivty", t.getMessage(), t);
                        }finally {
                            progressDialog.dismiss();
                        }

                    }
                });
            }
        }catch (Throwable e){
            progressDialog.dismiss();
            mostrarSnackbar();
            Log.e("MainActivty", e.getMessage(), e);
        }
        /*finally {
            progressDialog.dismiss();
        }*/
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    public void MostrarPopUpDescubierto(){
        popupDescubierto.setContentView(R.layout.descubiertopopup);
        botonAceptar = (Button)popupDescubierto.findViewById(R.id.botonAceptar);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupDescubierto.dismiss();
            }
        });
        popupDescubierto.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupDescubierto.show();
    }
    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }


}
