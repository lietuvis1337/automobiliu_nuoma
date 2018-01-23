package eunuoma.freecluster.rol.nuoma;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.List;

public class AdapterAutomobiliai extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Automobiliai> data= Collections.emptyList();
    Automobiliai current;
    int currentPos=0;

    // create constructor to initialize context and data sent from MainActivity
    public AdapterAutomobiliai(Context context, List<Automobiliai> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_automobiliai, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        Automobiliai current=data.get(position);
        myHolder.textVardasPavarde.setText(current.getVardas() + " " + current.getPavarde());
        myHolder.textAsmensKodas.setText(current.getAsmenskodas());
        myHolder.textAutomobilis.setText(current.getAutomobilis());
        myHolder.textTipas.setText(current.getTipas());
        myHolder.textPaslaugos.setText("Papildomai: " + current.getPaslaugos());
        myHolder.textData.setText(current.getData());
        myHolder.textID.setText("ID: " + current.getId());
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textVardasPavarde;
        TextView textAsmensKodas;
        TextView textAutomobilis;
        TextView textTipas;
        TextView textPaslaugos;
        TextView textData;
        TextView textID;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textVardasPavarde = (TextView) itemView.findViewById(R.id.textVardasPavarde);
            textAsmensKodas = (TextView) itemView.findViewById(R.id.textAsmensKodas);
            textAutomobilis = (TextView) itemView.findViewById(R.id.textAutomobilis);
            textTipas = (TextView) itemView.findViewById(R.id.textTipas);
            textPaslaugos = (TextView) itemView.findViewById(R.id.textPapildomai);
            textData = (TextView) itemView.findViewById(R.id.textData);
            textID = (TextView) itemView.findViewById(R.id.textID);
            itemView.setOnClickListener(this);
        }

        // Click event for all items
        @Override
        public void onClick(View v) {

            Toast.makeText(context, "You clicked an item", Toast.LENGTH_SHORT).show();

        }

    }

}
