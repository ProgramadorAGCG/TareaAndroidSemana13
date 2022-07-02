package com.example.mitabactivity.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mitabactivity.R;
import com.example.mitabactivity.entidad.Movimientos;

import java.util.ArrayList;

public class MovimientoAdaptador extends BaseAdapter {

    private ArrayList<Movimientos> lista;
    private Context contexto;

    public MovimientoAdaptador(ArrayList<Movimientos> lista, Context contexto) {
        this.lista = lista;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        TextView tvcodm,tvdesm,tvmonm,tvmovm;
        LayoutInflater inflater=(LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row=inflater.inflate(R.layout.item_movimientos,parent,false);

        tvcodm=row.findViewById(R.id.tvcod);
        tvdesm=row.findViewById(R.id.tvdes);
        tvmonm=row.findViewById(R.id.tvmon);
        tvmovm=row.findViewById(R.id.tvmov);
        tvcodm.setText(""+lista.get(position).getIdmovimiento());
        tvdesm.setText(lista.get(position).getDescripcion());
        tvmonm.setText(""+lista.get(position).getMonto());
        tvmovm.setText(""+lista.get(position).getMovimiento());
        return  row;
    }

}
