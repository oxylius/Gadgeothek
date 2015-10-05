package ch.mge.miniprojekt.gadgeothek.helper;

import android.content.Context;
import android.content.Intent;
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
import ch.mge.miniprojekt.gadgeothek.activities.AddReservationActivity;
import ch.mge.miniprojekt.gadgeothek.activities.GadgetDetailActivity;
import ch.mge.miniprojekt.gadgeothek.domain.Gadget;
import ch.mge.miniprojekt.gadgeothek.domain.Reservation;


public class RVAdapterGadgets extends RecyclerView.Adapter<RVAdapterGadgets.GadgetViewHolder>{
    List<Gadget> gadgets;

    public static class GadgetViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        Gadget gadget = null;
        CardView cv;
        TextView gadgetTitle;

        GadgetViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            cv = (CardView)itemView.findViewById(R.id.gadget_item);
            gadgetTitle = (TextView)itemView.findViewById(R.id.gadget_item_gadget_title);

            //personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(context, GadgetDetailActivity.class);
                    intent.putExtra(GadgetDetailActivity.GADGET, gadget);

                    context.startActivity(intent);
                }
            });
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
        GadgetViewHolder.gadget = gadgets.get(i);
        GadgetViewHolder.gadgetTitle.setText(gadgets.get(i).getName());
        //GadgetViewHolder.price.setText((int) gadgets.get(i).getPrice());


        //GadgetViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
