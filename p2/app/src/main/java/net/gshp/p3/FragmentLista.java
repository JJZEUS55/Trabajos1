package net.gshp.p3;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FragmentLista extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private DBLista bd;
    public static List<Csku> listaCsku;
    private LinearLayout linearLayout;
    private Button btnActionGuardar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Vista Lista", "Entrando a la Lista");

        View view = inflater.inflate(R.layout.recycler_view, container, false);
        btnActionGuardar = view.findViewById(R.id.xbtnGuardar);

        bd = new DBLista(this.getContext());

        listaCsku = new ArrayList<>();
        listaCsku = bd.selectAllDatos(0);
        Log.d("Vista Lista", listaCsku.toString());

        linearLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(parametros);

        recyclerView = view.findViewById(R.id.rvCskurt);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapter(listaCsku);
        recyclerView.setAdapter(mAdapter);

        btnActionGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Csku dato : listaCsku) {
                    Toast.makeText(view.getContext(), "ID: " + dato.getIdCSKU() + " Nuevo Val: " + dato.getValue(), Toast.LENGTH_SHORT).show();
                    bd.actualizarDatosTabla2(dato);
                }
                Toast.makeText(view.getContext(), "Se han guardado los cambios", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
