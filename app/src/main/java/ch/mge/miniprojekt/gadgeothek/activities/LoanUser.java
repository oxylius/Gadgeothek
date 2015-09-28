package ch.mge.miniprojekt.gadgeothek.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.helper.loansAdapter;
import ch.mge.miniprojekt.gadgeothek.domain.Loan;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

public class LoanUser extends GadgeothekMain {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityTitle("My loaned Gadgets");
        setContentView(R.layout.activity_loan_user);

        LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {
                //https://guides.codepath.com/android/Using-the-RecyclerView
                RecyclerView rvLoans = (RecyclerView) findViewById(R.id.rvLoans);
                loansAdapter adapter = new loansAdapter(input);
                rvLoans.setAdapter(adapter);
                rvLoans.setLayoutManager(new LinearLayoutManager(LoanUser.this));
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
