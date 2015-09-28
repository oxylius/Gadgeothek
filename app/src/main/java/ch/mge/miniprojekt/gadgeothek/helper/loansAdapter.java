package ch.mge.miniprojekt.gadgeothek.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.domain.Loan;

public class loansAdapter extends
    RecyclerView.Adapter<loansAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    private List<Loan> mLoans;

    public loansAdapter(List<Loan> loans) {
        mLoans = loans;
    }

    @Override
    public loansAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_loans, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(loansAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Loan loans = mLoans.get(position);

        // Set item views based on the data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(loans.getGadget().getName());

    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return mLoans.size();
    }
}
