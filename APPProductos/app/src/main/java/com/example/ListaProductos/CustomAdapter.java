package com.example.ListaProductos;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ListaProductos.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Producto> {
    private List<Producto> myData;
    public CustomAdapter(@NonNull Context context, @NonNull List<Producto> data) {
        super(context, R.layout.card_layout_list,data);

        this.myData=data;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=LayoutInflater.from(getContext()).inflate(R.layout.card_layout_list,parent,false);

        ImageView productoImg=convertView.findViewById(R.id.productoImg);
        TextView productoNombre=convertView.findViewById(R.id.productoNombre);
        TextView productoDescripcion=convertView.findViewById(R.id.productoDescripcion);
        TextView productoPrecioCosto=convertView.findViewById(R.id.productoPrecioCosto);
        TextView productoPrecioVenta=convertView.findViewById(R.id.productoPrecioVenta);


        Picasso.Builder builder= new Picasso.Builder(getContext());
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        });

        builder.build().load("http://backendnetcore.westus2.cloudapp.azure.com/"+myData.get(position).getImagen()).into(productoImg);
        productoNombre.setText(myData.get(position).getProducto());
        productoDescripcion.setText(myData.get(position).getDescripcion());
        productoPrecioCosto.setText("Precio costo: Q "+myData.get(position).getPrecio_costo());
        productoPrecioVenta.setText("Precio venta: Q "+myData.get(position).getPrecio_venta());

        return convertView;
    }
}
