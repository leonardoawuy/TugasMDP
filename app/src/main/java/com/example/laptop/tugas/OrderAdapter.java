package com.example.laptop.tugas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import static com.example.laptop.tugas.OrderAdapter.mylistener;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    ArrayList<Order> order;
    static RVClickListener mylistener;

    public OrderAdapter(ArrayList<Order> order,RVClickListener rvcl) {
        this.order = order;
        this.mylistener = rvcl;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.activity_rowitem_order,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder viewHolder, int i) {
        viewHolder.harga.setText(order.get(i).getTotal()+"");
        viewHolder.type.setText( order.get(i).getQty()+" "+order.get(i).getType()+" ");
        viewHolder.toping.setText("with :" +order.get(i).getToping()+"");
    }

    @Override
    public int getItemCount() {
        return (order!=null)?order.size():0;
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        TextView type,harga,toping;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type= itemView.findViewById(R.id.txtType);
            harga= itemView.findViewById(R.id.txtHarga);
            toping= itemView.findViewById(R.id.txtToping);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mylistener.recyclerViewListClicked(v,ViewHolder.this.getLayoutPosition());
                }
            });
        }
    }
}
