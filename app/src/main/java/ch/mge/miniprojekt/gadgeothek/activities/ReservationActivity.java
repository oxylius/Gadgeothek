package ch.mge.miniprojekt.gadgeothek.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.domain.Gadget;
import ch.mge.miniprojekt.gadgeothek.domain.Reservation;
import ch.mge.miniprojekt.gadgeothek.helper.RVAdapter;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

public class ReservationActivity extends GadgeothekMain {

    RecyclerView rv;
    RVAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityTitle("Reservation");
        setContentView(R.layout.activity_reservation);

        rv = (RecyclerView) findViewById(R.id.reservation_container);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_reservation);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, AddReservationActivity.class));
            }
        });

        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> reservations) {

                adapter = new RVAdapter(reservations);
                rv.setAdapter(adapter);

            }

            @Override
            public void onError(String message) {
                Toast.makeText(ReservationActivity.this, "Error getting Reservations", Toast.LENGTH_SHORT).show();
            }
        });
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
                final int position = viewHolder.getAdapterPosition();
                adapter.onItemDismiss(viewHolder.getAdapterPosition());

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);
    }

}
