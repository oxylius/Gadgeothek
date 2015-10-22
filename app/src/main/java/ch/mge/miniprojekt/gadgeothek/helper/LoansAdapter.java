package ch.mge.miniprojekt.gadgeothek.helper;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.domain.Loan;

public class LoansAdapter extends
    RecyclerView.Adapter<LoansAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView itemImage;
        TextView nameTextView;
        TextView pickupDateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            nameTextView = (TextView) itemView.findViewById(R.id.item_name);
            pickupDateTextView = (TextView) itemView.findViewById(R.id.pickupDate);
            itemImage = (ImageView) itemView.findViewById(R.id.item_photo);
        }
    }

    private List<Loan> mLoans;

    public LoansAdapter(List<Loan> loans) {
        this.mLoans = loans;
    }

    @Override
    public LoansAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View loanView = inflater.inflate(R.layout.item_loans, parent, false);

        // Return a new holder instance
        return new ViewHolder(loanView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(LoansAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Loan loans = mLoans.get(position);

        // Set item views based on the data model
        ImageView photoIV = viewHolder.itemImage;
        if(loans.getGadget().getName().contains("IPhone")) {
            photoIV.setImageResource(R.mipmap.ic_apple);
        } else if (loans.getGadget().getName().contains("Android")) {
            photoIV.setImageResource(R.drawable.ic_android_black_24dp);
        } else {
            photoIV.setImageResource(R.drawable.ic_help_outline_black_24dp);

        }
        TextView nameTV = viewHolder.nameTextView;
        nameTV.setText(loans.getGadget().getName());

            Calendar cal = Calendar.getInstance();
            cal.setTime(loans.getPickupDate());
            cal.add(Calendar.DATE, 7);
            Date result = cal.getTime();


            TextView pickupDateTV = viewHolder.pickupDateTextView;
            pickupDateTV.setText(result.toString());

    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return mLoans.size();
    }
}
