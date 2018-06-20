package net.gshp.p3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by leo on 19/06/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Csku> mDataSku;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View layout;
        public TextView tvIdSku;
        public RadioGroup radioGroup;


        public ViewHolder(final View itemView) {
            super(itemView);
            layout = itemView;
            tvIdSku = itemView.findViewById(R.id.tvIdSku);
            radioGroup = itemView.findViewById(R.id.radio_sn);



            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    //Csku aux = new Csku();
                    int seleccionado = radioGroup.getCheckedRadioButtonId();
                    RadioButton rbSelect = itemView.findViewById(seleccionado);
                    FragmentLista.listaCsku.get(getAdapterPosition()).setValue(rbSelect.getText().toString());
                    Toast.makeText(radioGroup.getContext(), "ID SKU: " + FragmentLista.listaCsku.get(getAdapterPosition()).getIdCSKU() + " Actualizado a: " + rbSelect.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void add(int position, Csku item) {
        mDataSku.add(position, item);
        notifyItemInserted(position);
    }


    public RecyclerViewAdapter(List<Csku> mDataSku) {
        this.mDataSku = mDataSku;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.recyler_fila, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Csku dato = mDataSku.get(position);
        holder.tvIdSku.setText("C_SKU ID: " + dato.getIdCSKU());

    }

    @Override
    public int getItemCount() {
        return mDataSku.size();
    }

}
