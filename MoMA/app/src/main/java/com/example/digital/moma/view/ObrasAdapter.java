package com.example.digital.moma.view;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.digital.moma.R;
import com.example.digital.moma.model.Obra;

import java.util.List;

public class ObrasAdapter extends RecyclerView.Adapter<ObrasAdapter.ObrasHolder> {
    private List<Obra> obraList;

    public ObrasAdapter(List<Obra> obraList) {
        this.obraList = obraList;
    }

    public void setObraList(List<Obra> obraList) {
        this.obraList = obraList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ObrasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ObrasHolder obrasHolder = new ObrasHolder(view);

        return obrasHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ObrasHolder holder, int position) {
        Obra obra = obraList.get(position);
        holder.cargar(obra);

    }

    @Override
    public int getItemCount() {
        return obraList.size();
    }
    public class ObrasHolder extends RecyclerView.ViewHolder {
        // ATRIBUTOS
        private TextView textViewNombre;
        private ImageView imageView;


        public ObrasHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.obraNombre);
            imageView = itemView.findViewById(R.id.image);
        }

        public void cargar(Obra obra) {
            textViewNombre.setText(obra.getName());

            Glide.with(itemView.getContext()).load(obra.getImage()).into(imageView);
        }
    }

}
