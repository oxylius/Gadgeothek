package ch.mge.miniprojekt.gadgeothek.helper;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.domain.Gadget;
import ch.mge.miniprojekt.gadgeothek.domain.Reservation;


public class RVAdapterGadgets extends RecyclerView.Adapter<RVAdapterGadgets.GadgetViewHolder>{
    List<Gadget> gadgets;

    public static class GadgetViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView manufacturer;
        TextView gadgetTitle;
        TextView price;
        TextView condition;
        TextView inventoryId;

        GadgetViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.gadget_item);
            inventoryId = (TextView)itemView.findViewById(R.id.gadget_item_id);
            gadgetTitle = (TextView)itemView.findViewById(R.id.gadget_item_gadget_title);
            manufacturer = (TextView)itemView.findViewById(R.id.gadget_item_manufacturer);
            price = (TextView)itemView.findViewById(R.id.gadget_item_price);
            condition = (TextView)itemView.findViewById(R.id.gadget_item_condition);
            //personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }


    }

    public RVAdapterGadgets(List<Gadget> gadgets){
        this.gadgets = gadgets;
    }

    @Override
    public int getItemCount() {
        return gadgets.size();
    }

    @Override
    public GadgetViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gadget_item, viewGroup, false);
        return new GadgetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GadgetViewHolder GadgetViewHolder, int i) {
        GadgetViewHolder.inventoryId.setText(gadgets.get(i).getInventoryNumber());
        GadgetViewHolder.gadgetTitle.setText(gadgets.get(i).getName());
        GadgetViewHolder.manufacturer.setText(gadgets.get(i).getManufacturer());
        GadgetViewHolder.condition.setText(gadgets.get(i).getCondition().toString());
        //GadgetViewHolder.price.setText((int) gadgets.get(i).getPrice());

        //GadgetViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
