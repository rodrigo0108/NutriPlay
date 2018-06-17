package com.ramos.nutriplay.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ramos.nutriplay.R;
import com.ramos.nutriplay.actividades.DetalleAlimentoActivity;
import com.ramos.nutriplay.modelos.Alimento;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlimentosAdapter extends RecyclerView.Adapter<AlimentosAdapter.AlimentoViewHolder> {

    private List<Alimento> alimentoList;
    private Context context;
    private Map<String,Boolean> coleccion_alimentos;

    public AlimentosAdapter(List<Alimento> alimentoList,Context context,Map<String,Boolean> coleccion_alimentos) {
        this.alimentoList = alimentoList;
        this.context = context;
        this.coleccion_alimentos = coleccion_alimentos;
    }

    @NonNull
    @Override
    public AlimentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fila_layout,parent,false);
        AlimentoViewHolder holder = new AlimentoViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlimentoViewHolder holder, int position) {
        try {
            final Alimento alimento = alimentoList.get(position);
            if (coleccion_alimentos != null) {
                if (coleccion_alimentos.get(alimento.getId())) {
                    holder.card_imagen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //accion
                            Intent intent = new Intent(context, DetalleAlimentoActivity.class);
                            intent.putExtra("DATOS_ALIMENTO", alimento);
                            context.startActivity(intent);

                        }
                    });
                    mostrar(alimento.getImagen(),holder.alimentoView);
                } else {
                    holder.card_imagen.setClickable(false);
                    mostrar(alimento.getImagen_oscura(),holder.alimentoView);
                }

            }
        }catch (Exception e){
            Log.d("TAG",e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return alimentoList.size();
    }

    public static class AlimentoViewHolder extends RecyclerView.ViewHolder{
        ImageView alimentoView;
        CardView card_imagen;
        public AlimentoViewHolder(View view){
            super(view);
            alimentoView = (ImageView)view.findViewById(R.id.img_alimento);
            card_imagen = (CardView)view.findViewById(R.id.card_alimento);
        }
    }
    public void mostrar(String url_imagen,ImageView imagen){
        Picasso.get().load(url_imagen).into(imagen);
    }
}
