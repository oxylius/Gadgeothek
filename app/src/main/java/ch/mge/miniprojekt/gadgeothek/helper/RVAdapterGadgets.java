package ch.mge.miniprojekt.gadgeothek.helper;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.domain.Gadget;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;


public class RVAdapterGadgets extends RecyclerView.Adapter<RVAdapterGadgets.GadgetViewHolder>{
    List<Gadget> gadgets;

    public static class GadgetViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        Gadget gadget = null;
        CardView cv;
        TextView gadgetTitle;
        CoordinatorLayout gadgetDetailContainer;
        TextView gadgetDetailTitle;
        TextView gadgetDetailId;
        TextView gadgetDetailManufacturer;
        TextView gadgetDetailCondition;
        TextView gadgetDetailPrice;
        FloatingActionButton fabAddReservation;


        GadgetViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            cv = (CardView)itemView.findViewById(R.id.gadget_item);
            gadgetTitle = (TextView)itemView.findViewById(R.id.gadget_item_gadget_title);
            gadgetDetailContainer = (CoordinatorLayout)itemView.findViewById(R.id.gadget_detail_container);
            gadgetDetailTitle = (TextView)itemView.findViewById(R.id.gadget_detail_title);
            gadgetDetailId = (TextView)itemView.findViewById(R.id.gadget_detail_id);
            gadgetDetailManufacturer = (TextView)itemView.findViewById(R.id.gadget_detail_manufacturer);
            gadgetDetailCondition = (TextView)itemView.findViewById(R.id.gadget_detail_condition);
            gadgetDetailPrice = (TextView)itemView.findViewById(R.id.gadget_detail_price);
            fabAddReservation = (FloatingActionButton)itemView.findViewById(R.id.fab_add_reservation);

            //personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(gadgetDetailContainer.getVisibility() == View.GONE){
                        gadgetTitle.setVisibility(View.GONE);
                        gadgetDetailContainer.setVisibility(View.VISIBLE);
                        fabAddReservation.setVisibility(View.VISIBLE);
                    } else {
                        gadgetDetailContainer.setVisibility(View.GONE);
                        fabAddReservation.setVisibility(View.GONE);
                        gadgetTitle.setVisibility(View.VISIBLE);

                    }
                }
            });
            fabAddReservation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LibraryService.reserveGadget(gadget, new Callback<Boolean>() {
                        @Override
                        public void onCompletion(Boolean input) {
                            Toast.makeText(context, "Gadget Reserved!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(String message) {
                            Toast.makeText(context, "Could not reserve Gadget!", Toast.LENGTH_SHORT).show();
                        }
                    });
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
        GadgetViewHolder.gadgetDetailTitle.setText(gadgets.get(i).getName());
        GadgetViewHolder.gadgetDetailId.setText(gadgets.get(i).getInventoryNumber());
        GadgetViewHolder.gadgetDetailManufacturer.setText(gadgets.get(i).getManufacturer());
        GadgetViewHolder.gadgetDetailCondition.setText(gadgets.get(i).getCondition().toString());
        //GadgetViewHolder.gadgetDetailPrice.setText(((int)gadgets.get(i).getPrice()));
        //GadgetViewHolder.price.setText((int) gadgets.get(i).getPrice());


        //GadgetViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
