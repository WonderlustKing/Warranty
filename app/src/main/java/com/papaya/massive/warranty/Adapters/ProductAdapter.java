package com.papaya.massive.warranty.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papaya.massive.warranty.R;
import com.papaya.massive.warranty.models.ProductInfo;

import java.util.List;

/**
 * Created by christos on 30/6/2016.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private List<ProductInfo> productList;

    public ProductAdapter(List<ProductInfo> productList){
        this.productList=productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_pattern, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ProductInfo pi = productList.get(position);
        holder.cardView.setCardBackgroundColor(pi.getColor());
        holder.name_text.setText(pi.getName());
        if(pi.getDays_left() == 0){
            holder.days_left_text.setText("EXPIRED");
            holder.progressBar.setVisibility(View.INVISIBLE);
        }else {
            holder.days_left_text.setText(Integer.toString(pi.getDays_left()) + " days left");
            int progress = (int) (100 - pi.getDays_left() * 0.1);
            holder.progressBar.setProgress(progress);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
