package com.example.mitabactivity.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mitabactivity.Dao.MovimientoDaoImpl;
import com.example.mitabactivity.MainActivity;
import com.example.mitabactivity.R;
import com.example.mitabactivity.data.SqlOpenHelper;
import com.example.mitabactivity.entidad.Movimientos;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IngresoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngresoFragment extends Fragment implements View.OnClickListener {

    Switch mswMovimiento;
    Button btnregistrar;
    TextInputEditText mtetDescripcion, mtetMonto;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IngresoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ingreso.
     */
    // TODO: Rename and change types and number of parameters
    public static IngresoFragment newInstance(String param1, String param2) {
        IngresoFragment fragment = new IngresoFragment();
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
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //necesito view buscar widget
        mtetDescripcion = view.findViewById((R.id.tetDescripcion));
        mtetMonto = view.findViewById((R.id.tetMonto));
        mswMovimiento = view.findViewById(R.id.swMovimiento);
        btnregistrar = view.findViewById(R.id.btnRegistrar);

        mswMovimiento.setOnClickListener(this);
        btnregistrar.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingreso, container, false);
    }

    SqlOpenHelper admin;
    int valormovimiento = -1;

    @Override
    public void onClick(View v) {
        if (v == mswMovimiento) {

            if (mswMovimiento.isChecked()) {
                mswMovimiento.setText("Ingreso");
                valormovimiento = 1;
            } else {
                mswMovimiento.setText("Egreso");
                valormovimiento = -1;
            }
        }
        if (v == btnregistrar) {
            String descripcion;
            double monto;
            descripcion = mtetDescripcion.getText().toString().trim();
            monto = Double.parseDouble(mtetMonto.getText().toString());
            admin = new SqlOpenHelper(getActivity());

            Movimientos bean = new Movimientos();
            bean.setDescripcion(descripcion);
            bean.setMonto(monto);
            //   bean.setMovimiento(Integer.parseInt(mswMovimiento.getText().toString()));
            bean.setMovimiento(valormovimiento);
            MovimientoDaoImpl daoMovimiento = new MovimientoDaoImpl(getActivity());
            daoMovimiento.registrarMovimiento(bean);
            Toast.makeText(getActivity(), "Nuevo movimiento ", Toast.LENGTH_SHORT).show();
            mtetDescripcion.setText("");
            mtetMonto.setText("");
            mtetDescripcion.requestFocus();
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);

        }
    }
}