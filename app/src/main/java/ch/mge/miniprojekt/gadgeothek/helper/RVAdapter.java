package ch.mge.miniprojekt.gadgeothek.helper;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.domain.Reservation;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ReservationViewHolder> implements ItemTouchHelperAdapter {
    List<Reservation> reservations;
    private final Context context;


    public RVAdapter(List<Reservation> reservations, Context context){
        this.reservations = reservations;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reservation_item, viewGroup, false);
        return new ReservationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder reservationViewHolder, int i) {
        reservationViewHolder.gadgetTitle.setText(reservations.get(i).getGadget().getName());
        if(reservationViewHolder.gadgetTitle.getText().toString().contains("IPhone")) {
            reservationViewHolder.gadgetPhoto.setImageResource(R.mipmap.ic_apple);
        } else if(reservationViewHolder.gadgetTitle.getText().toString().contains("Android")) {
            reservationViewHolder.gadgetPhoto.setImageResource(R.drawable.ic_android_black_24dp);
        } else {
            reservationViewHolder.gadgetPhoto.setImageResource(R.drawable.ic_help_outline_black_24dp);
        }
        //reservationViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
        reservationViewHolder.waitingPos.setText("Waiting Position: " + reservations.get(i).getWatingPosition());
        if(reservations.get(i).isReady()){
            reservationViewHolder.cv.setCardBackgroundColor(context.getResources().getColor(R.color.green));
            reservationViewHolder.waitingImage.setVisibility(View.GONE);
            reservationViewHolder.waitingPos.setText("Ready for Pickup!");
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

    }

    @Override
    public void onItemDismiss(int position, final RecyclerView rv) {
        final int pos = position;
        LibraryService.deleteReservation(reservations.get(position), new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                reservations.remove(pos);
                notifyItemRemoved(pos);
                Snackbar.make(rv, "Reservation deleted!", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onError(String message) {
                Snackbar.make(rv, "Error on Reservation delete!", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        private final Context context;
        TextView reservationId;
        TextView gadgetTitle;
        ImageView gadgetPhoto;
        TextView waitingPos;
        ImageView waitingImage;

        ReservationViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            cv = (CardView)itemView.findViewById(R.id.reservation_item);
            gadgetTitle = (TextView)itemView.findViewById(R.id.reservation_item_gadget_title);
            gadgetPhoto = (ImageView)itemView.findViewById(R.id.reservation_item_gadget_image);
            //personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            waitingPos = (TextView)itemView.findViewById(R.id.reservation_item_waiting_pos);
            waitingImage = (ImageView)itemView.findViewById(R.id.reservation_item_waiting_image);
        }

    }


}
