package com.example.mitabactivity.ui.main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mitabactivity.Dao.MovimientoDaoImpl;
import com.example.mitabactivity.R;
import com.example.mitabactivity.adaptador.MovimientoAdaptador;
import com.example.mitabactivity.data.SqlOpenHelper;
import com.example.mitabactivity.entidad.Movimientos;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovimientosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovimientosFragment extends Fragment {

    ListView lstmovimientos;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovimientosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment movimientos.
     */
    // TODO: Rename and change types and number of parameters
    public static MovimientosFragment newInstance(String param1, String param2) {
        MovimientosFragment fragment = new MovimientosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movimientos, container, false);
        lstmovimientos = v.findViewById(R.id.lstm);
        listaM();
        return v;
    }

    SqlOpenHelper admin;

    //método que retorne la lista de movimientos
    private void listaM() {
        admin = new SqlOpenHelper(getActivity());
        ArrayList<Movimientos> data = new ArrayList<Movimientos>();
        Movimientos bean = null;
        SQLiteDatabase base = admin.getReadableDatabase();

        Cursor cursor = base.rawQuery("select * from movimientos", null);
        while (cursor.moveToNext()) {
            bean = new Movimientos();
            bean.setIdmovimiento(cursor.getInt(0));
            bean.setDescripcion(cursor.getString(1));
            bean.setMonto(cursor.getDouble(2));
            bean.setMovimiento(cursor.getInt(3));
            data.add(bean);
        }

        MovimientoDaoImpl dao = new MovimientoDaoImpl(getActivity());

        MovimientoAdaptador adaptador = new MovimientoAdaptador(data, getActivity());

        lstmovimientos.setAdapter(adaptador);
    }
}