package com.idealoop.busseek.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idealoop.busseek.R;
import com.idealoop.busseek.model.Bus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Owner_buslist_adaptor extends RecyclerView.Adapter<Owner_buslist_adaptor.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<Bus> buslists = new ArrayList<>();

    public Owner_buslist_adaptor(Context context, ArrayList<Bus> buslist){
        this.layoutInflater = LayoutInflater.from(context);
        buslists = buslist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.buslist_cardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bus bus = buslists.get(position);

        Picasso.get().load(bus.getDownloadImgEx()).into(holder.external);
        Picasso.get().load(bus.getDownloadImgIn()).into(holder.internal);
        holder.busID.setText(bus.getBusID());
        holder.busNo.setText(bus.getVehicleno());
        holder.fromto.setText("Bus Route- From: "+bus.getFrom()+" - To: "+bus.getTo());
        holder.time.setText("Time Slot - From: "+bus.getTimeSlots().get(0)+" - To: "+bus.getTimeSlots().get(1));

        int booked = 0;
        ArrayList <String> TimeSlots = bus.getTimeSlots();
        for(int i = 0; i<bus.getTimeSlots().size(); i++){
            if(bus.getTimeSlots().get(i).equals(1)){
                booked++;
            }
        }
        holder.seatsbooked.setText("No of Booked Seats: "+booked);
    }

    @Override
    public int getItemCount() {
        return buslists.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView busNo,busID,fromto,time,seatsbooked;
        ImageView external,internal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            busNo = itemView.findViewById(R.id.busNo);
            busID = itemView.findViewById(R.id.busID);
            fromto = itemView.findViewById(R.id.fromto);
            time = itemView.findViewById(R.id.time);
            seatsbooked = itemView.findViewById(R.id.seatsbooked);
            external = itemView.findViewById(R.id.external);
            internal = itemView.findViewById(R.id.internal);

        }
    }
}
