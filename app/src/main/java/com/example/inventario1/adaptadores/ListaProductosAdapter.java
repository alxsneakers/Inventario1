package com.example.inventario1.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventario1.R;
import com.example.inventario1.VerProductoActivity;
import com.example.inventario1.entidades.Productos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Adaptador para asignar los valores a la vista a lista_item_Producto y mostrarlos en el recycled
public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ProductoViewHolder> {

    ArrayList<Productos> listaProductos;
    ArrayList<Productos> listaOriginal;


    public ListaProductosAdapter(ArrayList<Productos> listaProductos){
        this.listaProductos = listaProductos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaProductos);
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

    //Buscador
    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();

        if(longitud == 0){
            listaProductos.clear();
            listaProductos.addAll(listaOriginal);
        }else {
            //Buscar por item para dos versiones de android
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Productos> collecion = listaProductos.stream()
                        .filter(i -> i.getItem().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collecion);
            }else{
                for(Productos c: listaProductos){
                    if(c.getItem().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaProductos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        //Retorna el tamaño de la lista
        return listaProductos.size();

    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        //Identificamos los elementos
        //Asignamos low view de nuestra lista a la plantilla individual
        TextView viewItem, viewSKU, viewTalla, viewFecha, viewPrecio, viewGenero;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewItem = itemView.findViewById(R.id.viewItem);
            viewSKU = itemView.findViewById(R.id.viewSKU);
            viewTalla = itemView.findViewById(R.id.viewTalla);
            viewFecha = itemView.findViewById(R.id.viewFecha);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            viewGenero = itemView.findViewById(R.id.viewGenero);

            //on click para abrir el producto y llevarnos a verActivity para ver los detalles
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerProductoActivity.class);
                    intent.putExtra("ID", listaProductos.get(getAdapterPosition()).getID());
                    context.startActivity(intent);
                }
            });

        }
    }
}
