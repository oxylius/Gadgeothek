package ch.mge.miniprojekt.gadgeothek.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.domain.Gadget;
import ch.mge.miniprojekt.gadgeothek.helper.RVAdapterGadgets;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

public class AddReservationActivity extends GadgeothekMain {
    RecyclerView rv;
    RVAdapterGadgets adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);
        setActivityTitle("Gadget");

        rv = (RecyclerView)findViewById(R.id.gadget_container);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> gadgets) {
                adapter = new RVAdapterGadgets(gadgets);
                rv.setAdapter(adapter);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(AddReservationActivity.this, "Error getting Gadgets", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
