package net.gshp.p3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
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

        protected View layout;
        private TextView tvIdSku;
        private RadioButton rbSi;
        private RadioButton rbNo;


        public ViewHolder(final View itemView) {
            super(itemView);
            layout = itemView;
            tvIdSku = itemView.findViewById(R.id.tvIdSku);
            rbSi = itemView.findViewById(R.id.radio_si);
            rbNo = itemView.findViewById(R.id.radio_no);
        }

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

        holder.rbSi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String valor = "";
                if(isChecked){
                    valor = holder.rbSi.getText().toString();
                    holder.rbNo.setChecked(false);
                    mDataSku.get(position).setValue("" + valor);
                }
            }
        });

        holder.rbNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String valor = "";
                if(isChecked){
                    valor = holder.rbNo.getText().toString();
                    holder.rbSi.setChecked(false);
                    mDataSku.get(position).setValue(valor);
                }
            }
        });

        if (dato.getValue().equals("Si")) {
            holder.rbSi.setChecked(true);
        } else if (dato.getValue().equals("No")) {
            holder.rbNo.setChecked(true);
        }else{
            holder.rbSi.setChecked(false);
            holder.rbNo.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return mDataSku.size();
    }

}
