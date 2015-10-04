package ch.mge.miniprojekt.gadgeothek.helper;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.activities.ReservationActivity;
import ch.mge.miniprojekt.gadgeothek.domain.Reservation;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ReservationViewHolder> implements ItemTouchHelperAdapter {
    List<Reservation> reservations;


    public RVAdapter(List<Reservation> reservations){
        this.reservations = reservations;
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
        reservationViewHolder.reservationId.setText(reservations.get(i).getReservationId());
        reservationViewHolder.gadgetTitle.setText(reservations.get(i).getGadget().getName());
        if(reservationViewHolder.gadgetTitle.getText().toString().contains("IPhone")) {
            reservationViewHolder.gadgetPhoto.setImageResource(R.mipmap.ic_apple);
        } else if(reservationViewHolder.gadgetTitle.getText().toString().contains("Android")) {
            reservationViewHolder.gadgetPhoto.setImageResource(R.drawable.ic_android_black_24dp);
        } else {
            reservationViewHolder.gadgetPhoto.setImageResource(R.drawable.ic_help_outline_black_24dp);
        }
        //reservationViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

    }

    @Override
    public void onItemDismiss(int position) {
        final int pos = position;
        LibraryService.deleteReservation(reservations.get(position).getReservationId(), new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                reservations.remove(pos);
                notifyItemRemoved(pos);
            }

            @Override
            public void onError(String message) {
            }
        });
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView reservationId;
        TextView gadgetTitle;
        ImageView gadgetPhoto;

        ReservationViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.reservation_item);
            reservationId = (TextView)itemView.findViewById(R.id.reservation_item_id);
            gadgetTitle = (TextView)itemView.findViewById(R.id.reservation_item_gadget_title);
            gadgetPhoto = (ImageView)itemView.findViewById(R.id.reservation_item_gadget_image);
            //personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }

    }


}
