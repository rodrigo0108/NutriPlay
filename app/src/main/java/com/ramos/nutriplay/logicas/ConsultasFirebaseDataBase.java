package com.ramos.nutriplay.logicas;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ramos.nutriplay.adaptadores.AlimentosAdapter;
import com.ramos.nutriplay.modelos.Alimento;

import java.util.List;
import java.util.Map;

public class ConsultasFirebaseDataBase {

    public static List<Alimento> ObtenerAlimentos(DatabaseReference databaseReference, final List<Alimento> alimentos, final Map<String,String> id_alimentos, final AlimentosAdapter adapter){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                alimentos.removeAll(alimentos);
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    final Alimento alimento= snapshot.getValue(Alimento.class);
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

        return alimentos;
    }
}
