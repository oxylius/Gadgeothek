package ch.mge.miniprojekt.gadgeothek.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.domain.Loan;
import ch.mge.miniprojekt.gadgeothek.helper.LoansAdapter;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

public class LoansActivity extends GadgeothekMain {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityTitle("Loaned Gadgets");
        setContentView(R.layout.activity_loan_user);
        try {
            LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
                @Override
                public void onCompletion(List<Loan> input) {
                    RecyclerView rvLoans = (RecyclerView) findViewById(R.id.rvLoans);
                    rvLoans.setHasFixedSize(true);
                    LoansAdapter adapter = new LoansAdapter(input, getApplicationContext());
                    rvLoans.setAdapter(adapter);
                    rvLoans.setLayoutManager(new LinearLayoutManager(LoansActivity.this));
                }

                @Override
                public void onError(String message) {
                    Toast.makeText(LoansActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IllegalStateException e) {
            Intent intent = new Intent(LoansActivity.this, LoginUser.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.d("Exception", "getLoansForCustomer");
        }
    }
}


