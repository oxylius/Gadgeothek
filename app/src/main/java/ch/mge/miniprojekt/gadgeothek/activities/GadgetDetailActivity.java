package ch.mge.miniprojekt.gadgeothek.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.domain.Gadget;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

public class GadgetDetailActivity extends GadgeothekMain {
    public final static String GADGET = "GADGET";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityTitle("Gadget Detail");
        setContentView(R.layout.activity_gadget_detail);

        final Gadget gadget = (Gadget)getIntent().getSerializableExtra(GADGET);

        TextView id = (TextView)findViewById(R.id.gadget_detail_id);
        id.setText(gadget.getInventoryNumber());

        TextView title = (TextView)findViewById(R.id.gadget_detail_title);
        title.setText(gadget.getName());

        TextView manufacturer = (TextView)findViewById(R.id.gadget_detail_manufacturer);
        manufacturer.setText(gadget.getManufacturer());

        TextView price = (TextView)findViewById(R.id.gadget_detail_price);
        price.setText(""+gadget.getPrice());

        TextView condition = (TextView)findViewById(R.id.gadget_detail_condition);
        condition.setText(gadget.getCondition().toString());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_reservation);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibraryService.reserveGadget(gadget, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        Toast.makeText(GadgetDetailActivity.this, "Gadget Reserved!", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(GadgetDetailActivity.this, "Could not reserve Gadget!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }



}
