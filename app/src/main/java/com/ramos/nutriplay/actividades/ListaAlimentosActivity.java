package com.ramos.nutriplay.actividades;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ramos.nutriplay.R;
import com.ramos.nutriplay.adaptadores.AlimentosAdapter;
import com.ramos.nutriplay.modelos.Alimento;
import com.ramos.nutriplay.modelos.Estado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class ListaAlimentosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAlimento;
    private List<Alimento> alimentos;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,databaseReference2;
    private AlimentosAdapter adapter;
    private HashMap<String,Boolean> coleccion_alimentos;

    //PRUEBA
    private String uid_usuario="lj3jbd2123";
    //---------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alimentos);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        recyclerViewAlimento = (RecyclerView)findViewById(R.id.recyclerViewAlimentos);
        alimentos = new ArrayList<>();
        coleccion_alimentos = new HashMap<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Alimento");
        databaseReference2 = firebaseDatabase.getReference("Coleccion");

        adapter = new AlimentosAdapter(alimentos,this,coleccion_alimentos);
        recyclerViewAlimento.setLayoutManager(new GridLayoutManager(this,3));
        //No hace lo que deseo
        recyclerViewAlimento.addItemDecoration(new GridSpacingItemDecoration(3,15,false));
        recyclerViewAlimento.setAdapter(adapter);
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
                    Estado estado = snapshot.getValue(Estado.class);
                    if(estado != null)
                    coleccion_alimentos.put(snapshot.getKey(),estado.isEstado());
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
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


}
