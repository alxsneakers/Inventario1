package com.example.inventario1.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventario1.R;
import com.example.inventario1.entidades.Productos;

import java.util.ArrayList;

//Adaptador para asignar los valores a la vista a lista_item_Producto y mostrarlos en el recycled
public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ProductoViewHolder> {

    ArrayList<Productos> listaProductos;

    public ListaProductosAdapter(ArrayList<Productos> listaProductos){
        this.listaProductos = listaProductos;
    }


    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Asignar cual es el diseño de la lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto, null, false);

        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        //Asignamos los elementos que coresponden para cada opcion
        //position = 0 hasta N registros tiene la tabla
        //Trames el getItem, de la posicion 0 (get(position))
        holder.viewItem.setText(listaProductos.get(position).getItem());
        holder.viewSKU.setText(listaProductos.get(position).getSKU());
        holder.viewTalla.setText(listaProductos.get(position).getTalla());
        holder.viewFecha.setText(listaProductos.get(position).getFecha());
        //holder.viewPrecio.setText(double(listaProductos.get(position).getPrecio()));
        holder.viewGenero.setText(listaProductos.get(position).getGenero());

    }

    @Override
    public int getItemCount() {
        //Retorna el tamaño de la lista
        return listaProductos.size();

    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        //Identificamos los elementos
        TextView viewItem, viewSKU, viewTalla, viewFecha, viewPrecio, viewGenero;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewItem = itemView.findViewById(R.id.viewItem);
            viewSKU = itemView.findViewById(R.id.viewSKU);
            viewTalla = itemView.findViewById(R.id.viewTalla);
            viewFecha = itemView.findViewById(R.id.viewFecha);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            viewGenero = itemView.findViewById(R.id.viewGenero);

        }
    }
}
