package ch.mge.miniprojekt.gadgeothek.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.List;
import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.helper.loansAdapter;
import ch.mge.miniprojekt.gadgeothek.helper.DividerItemDecoration;
import ch.mge.miniprojekt.gadgeothek.domain.Loan;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

public class LoanUser extends GadgeothekMain {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityTitle("My loaned Gadgets");
        setContentView(R.layout.activity_loan_user);
        try {
            LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
                @Override
                public void onCompletion(List<Loan> input) {
                    //https://guides.codepath.com/android/Using-the-RecyclerView
                    RecyclerView rvLoans = (RecyclerView) findViewById(R.id.rvLoans);
                    rvLoans.setHasFixedSize(true);
                    /*
                    RecyclerView.ItemDecoration itemDecoration =
                            new DividerItemDecoration(LoanUser.this, DividerItemDecoration.VERTICAL_LIST);
                    rvLoans.addItemDecoration(itemDecoration);
                    */
                    loansAdapter adapter = new loansAdapter(input);
                    rvLoans.setAdapter(adapter);
                    rvLoans.setLayoutManager(new LinearLayoutManager(LoanUser.this));
                    /*
                    LinearLayoutManager layoutManager = new LinearLayoutManager(LoanUser.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    layoutManager.scrollToPosition(0);
                    rvLoans.setLayoutManager(layoutManager);
                    */
                }

                @Override
                public void onError(String message) {
                    Toast.makeText(LoanUser.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IllegalStateException e) {
            Intent intent = new Intent(LoanUser.this, loginUser.class);
            startActivity(intent);
        }

    }
}
