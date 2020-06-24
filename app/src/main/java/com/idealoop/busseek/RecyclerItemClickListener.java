package com.idealoop.busseek;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 *
 * @author florian
 * Class RecyclerItemClickListener, implements RecyclerView.OnItemTouchListener
 * to intercept a click on item child
 *
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    // listener
    private OnItemClickListener mListener;

    /**
     *
     * @author florian
     * Interface OnItemClickListener which provides the click on Item
     *
     */
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    /**
     * Gesture detector
     */
    GestureDetector mGestureDetector;

    /**
     * Constructor
     * @param context : the context
     * @param listener : the listener
     */
    public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
        mListener = listener;
        // create GestureDetector
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // detect a single tap up
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        // get View child clicked by position X Y
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        // if listener is not null, up event to the listener
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            // call onItemClick
            mListener.onItemClick(childView, view.getChildPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent)
    {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

}