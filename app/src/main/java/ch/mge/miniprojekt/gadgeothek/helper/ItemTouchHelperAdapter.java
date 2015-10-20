package ch.mge.miniprojekt.gadgeothek.helper;

import android.support.v7.widget.RecyclerView;

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position, RecyclerView view);

}
