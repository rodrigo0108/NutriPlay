package com.ramos.nutriplay.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ramos.nutriplay.R;
import com.ramos.nutriplay.modelos.Alimento;

import java.util.HashMap;
import java.util.List;

public class AlimentosAdapter extends RecyclerView.Adapter<AlimentosAdapter.AlimentoViewHolder> {

    private List<Alimento> alimentoList;
    private Context context;
    private HashMap<String,Boolean> coleccion_alimentos;

    public AlimentosAdapter(List<Alimento> alimentoList,Context context,HashMap<String,Boolean> coleccion_alimentos) {
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
        Alimento alimento = alimentoList.get(position);
        if(coleccion_alimentos != null) {
            if (coleccion_alimentos.get(alimento.getId())) {
                holder.card_imagen.setClickable(true);
                Glide.with(context)
                        .load(alimento.getImagen())
                        .into(holder.alimentoView);
            } else {
                Glide.with(context)
                        .load(alimento.getImagen_oscura())
                        .into(holder.alimentoView);
            }
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
}
