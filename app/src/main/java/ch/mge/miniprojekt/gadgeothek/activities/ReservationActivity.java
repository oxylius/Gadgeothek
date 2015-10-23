package ch.mge.miniprojekt.gadgeothek.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.domain.Reservation;
import ch.mge.miniprojekt.gadgeothek.helper.RVAdapter;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

public class ReservationActivity extends GadgeothekMain {

    RecyclerView rv;
    TextView emptyReservations;
    ImageView emptyReservationsImage;
    RVAdapter adapter;
    FloatingActionButton fab;
    ItemTouchHelper.SimpleCallback simpleItemTouchCallback;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityTitle("Reservation");
        setContentView(R.layout.activity_reservation);

        rv = (RecyclerView) findViewById(R.id.reservation_container);
        rv.setHasFixedSize(true);

        emptyReservations = (TextView)findViewById(R.id.empty_reservations);
        emptyReservationsImage = (ImageView)findViewById(R.id.empty_reservations_image);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        fab = (FloatingActionButton) findViewById(R.id.fab_add_reservation);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, AddReservationActivity.class));
            }
        });

        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> reservations) {

                if(reservations.size() != 0) {
                    emptyReservations.setVisibility(View.GONE);
                    emptyReservationsImage.setVisibility(View.GONE);
                    adapter = new RVAdapter(reservations, getApplicationContext());
                    rv.setAdapter(adapter);
                } else {
                    emptyReservations.setVisibility(View.VISIBLE);
                    emptyReservationsImage.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onError(String message) {
                Snackbar.make(rv, "Error getting Reservations", Snackbar.LENGTH_LONG).show();
            }
        });
        simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
                final int position = viewHolder.getAdapterPosition();
                adapter.onItemDismiss(viewHolder.getAdapterPosition(), rv);

            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.accent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
                    @Override
                    public void onCompletion(List<Reservation> reservations) {

                        if(reservations.size() != 0) {
                            emptyReservations.setVisibility(View.GONE);
                            emptyReservationsImage.setVisibility(View.GONE);
                            adapter = new RVAdapter(reservations, getApplicationContext());
                            rv.setAdapter(adapter);
                            rv.getAdapter().notifyDataSetChanged();
                        } else {
                            emptyReservations.setVisibility(View.VISIBLE);
                            emptyReservationsImage.setVisibility(View.VISIBLE);
                        }
                        swipeRefreshLayout.setRefreshing(false);

                    }

                    @Override
                    public void onError(String message) {
                        Snackbar.make(rv, "Error getting Reservations", Snackbar.LENGTH_LONG).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> reservations) {

                if (reservations.size() != 0) {
                    emptyReservations.setVisibility(View.GONE);
                    emptyReservationsImage.setVisibility(View.GONE);
                    adapter = new RVAdapter(reservations, getApplicationContext());
                    rv.setAdapter(adapter);
                } else {
                    emptyReservations.setVisibility(View.VISIBLE);
                    emptyReservationsImage.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onError(String message) {
                Snackbar.make(rv, "Error getting Reservations", Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
