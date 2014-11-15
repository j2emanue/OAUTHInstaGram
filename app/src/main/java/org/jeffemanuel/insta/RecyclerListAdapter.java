package org.jeffemanuel.insta;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import gson.POJO.Datum;


public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private final View.OnClickListener mClickListener;
    private List<Datum> mDataset;
    private int mDatumPosition;
    // Provide a reference to the views for each data item
    public class ViewHolder extends RecyclerView.ViewHolder  {
        // each data item is just a string in this case
        //define our 6 image views per row
        public NetworkImageView nivA,nivB,nivC,nivD,nivE,nivF;

        public ViewHolder(View root) {
            super(root);
            nivA = (NetworkImageView) root.findViewById(R.id.niv_image_a_big);
            nivB = (NetworkImageView) root.findViewById(R.id.niv_image_b_small);
            nivC = (NetworkImageView) root.findViewById(R.id.niv_image_c_small);
            nivD = (NetworkImageView) root.findViewById(R.id.niv_image_d_large);
            nivE = (NetworkImageView) root.findViewById(R.id.niv_image_e_small);
            nivF = (NetworkImageView) root.findViewById(R.id.niv_image_f_small);

        }

        /**
         *
         * @return all the network images at  position
         */
    public NetworkImageView[] getRowOfImages(){
        NetworkImageView[] array = {nivA,nivB,nivC,nivD,nivE,nivF};
        return array;
    }

    }

    public RecyclerListAdapter(List<Datum> myDataset,View.OnClickListener clickListener) {
        mClickListener = clickListener;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Log.d(TAG, "pos:" + position );

        // - get element from the dataset at this position
        // - replace the contents of the view with that element
        //imageloader uses lruCache and lazy Loading

        ImageLoader imageLoader = MainApplication.getInstance().getImageLoader();

/*there are 6 image views per row, we loop through each image in the row.
note that mDatumPosition keeps track of what index we are at since each call
to bindviewholder really goes by 4 not 1 since there are 4/row.
 */
      for(NetworkImageView niv:holder.getRowOfImages()) {

          if(mDatumPosition>=mDataset.size())
              break;
          niv.setImageUrl(mDataset.get(mDatumPosition++).getImages().getStandardResolution().getUrl(), imageLoader);
          niv.setDefaultImageResId(R.drawable.default_image);
          niv.setErrorImageResId(R.drawable.default_error);
          niv.setOnClickListener(mClickListener);
      }
      }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //return a qtr of the size since we are doing 4 items per row
        return mDataset.size()/4;
    }
}