package net.gshp.p3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentLista extends Fragment {

    private RadioGroup rgGrupo;
    private Button btnPrueba;
    private DBLista bd;
    private List<Csku> listaCsku;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = new DBLista(getContext());
        listaCsku = new ArrayList<>();
        listaCsku = bd.cargarDatos();


        LinearLayout linearLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(parametros);
        btnPrueba = new Button(getActivity());
        parametros = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btnPrueba.setLayoutParams(parametros);
        btnPrueba.setText("Boton ajio");
        linearLayout.addView(btnPrueba);
        container.addView(linearLayout);

        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                for (Csku dato : listaCsku) {
//                    Toast.makeText(getContext(), "Soy el dato num: " + dato.getIdCSKU(), Toast.LENGTH_SHORT).show();
//                }
                Toast.makeText(getContext(), "Soy el dato num: " + listaCsku.get(1).getIdCSKU(), Toast.LENGTH_SHORT).show();

            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
