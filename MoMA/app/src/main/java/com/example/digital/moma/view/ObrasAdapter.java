package com.example.digital.moma.view;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.digital.moma.R;
import com.example.digital.moma.model.Obra;
import com.example.digital.moma.model.User;
import com.facebook.Profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ObrasAdapter extends RecyclerView.Adapter<ObrasAdapter.ObrasHolder> {

    //ATRIBUTOS
    private List<Obra> obraList;
    private AdapterListener listener;
    private FirebaseStorage firebaseStorage;

    //CONSTRUCTOR


    public ObrasAdapter(List<Obra> obraList, AdapterListener listener) {
        this.obraList = obraList;
        this.listener = listener;
    }

    //SETTER
    public void setObraList(List<Obra> obraList) {
        this.obraList = obraList;
        notifyDataSetChanged();
    }

    //METODOS
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Obra obra =obraList.get(getAdapterPosition());
                    listener.irDetalle(obra);
                }
            });
        }


        public void cargar(Obra obra) {
            textViewNombre.setText(obra.getName());

            firebaseStorage = FirebaseStorage.getInstance();
            StorageReference raiz = firebaseStorage.getReference();

            StorageReference imagenes = raiz.child("artistpaints");
            firebaseStorage = FirebaseStorage.getInstance();

            //VIA URI
            imagenes.child(obra.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(itemView.getContext()).load(uri).into(imageView);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override            public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(itemView.getContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    public interface AdapterListener{
        void irDetalle(Obra obra);
    }

}
