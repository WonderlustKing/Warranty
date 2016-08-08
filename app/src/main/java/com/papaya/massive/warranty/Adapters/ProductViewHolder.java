package com.papaya.massive.warranty.Adapters;

import android.animation.ValueAnimator;
import android.media.Image;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.papaya.massive.warranty.R;
import com.papaya.massive.warranty.models.ProductInfo;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by christos on 30/6/2016.
 */
public class ProductViewHolder extends RecyclerView.ViewHolder {
    @BindView (R.id.card_view) CardView cardView;
    @BindView(R.id.info_text) TextView name_text;
    @BindView(R.id.days_left_textv) TextView days_left_text;
    @BindView(R.id.expires_tv) TextView expires;
    @BindView(R.id.bought_at_tv) TextView bought;
    @BindView(R.id.three_dots_card) ImageView three_dots_img;
    @BindView(R.id.favore_card) ImageView favorite_img;
    @BindView(R.id.notification_card) ImageView notification_img;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.more_btn) Button more;

    int minHeight = 120;
    int height = 600;

    public ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        cardView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                cardView.getViewTreeObserver().removeOnPreDrawListener(this);
                height = cardView.getHeight();
                ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
                layoutParams.height = minHeight;
                cardView.setLayoutParams(layoutParams);

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) days_left_text.getLayoutParams();
                params.setMargins(0, 0, 0, 0);
                days_left_text.setLayoutParams(params);

                return true;
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCardViewHeight(150);
            }

            private void toggleCardViewHeight(int i) {
                if(cardView.getHeight() == minHeight){
                    expandView(height);
                }else{
                    collapseView();
                }
            }
        });

    }

    public void expandView(int height){
        //progressBar.setVisibility(View.INVISIBLE);
        //days_left_text.setVisibility(View.INVISIBLE);
        ValueAnimator anim = ValueAnimator.ofInt(cardView.getMeasuredHeightAndState(),
                height);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
                layoutParams.height = val;
                cardView.setLayoutParams(layoutParams);
            }
        });
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) days_left_text.getLayoutParams();
        params.setMargins(0, 0, 0, 20);
        days_left_text.setLayoutParams(params);
        anim.start();
        more.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                expires.setVisibility(View.VISIBLE);
                bought.setVisibility(View.VISIBLE);
                favorite_img.setVisibility(View.VISIBLE);
                notification_img.setVisibility(View.VISIBLE);
                //progressBar.setVisibility(View.VISIBLE);
               // days_left_text.setVisibility(View.VISIBLE);
            }
        }, 150);

    }

    public void collapseView(){
       // progressBar.setVisibility(View.INVISIBLE);
       // days_left_text.setVisibility(View.INVISIBLE);
        ValueAnimator anim = ValueAnimator.ofInt(cardView.getMeasuredHeightAndState(),
                minHeight);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
                layoutParams.height = val;
                cardView.setLayoutParams(layoutParams);

            }
        });
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) days_left_text.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        days_left_text.setLayoutParams(params);
        anim.start();
        more.setVisibility(View.INVISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                expires.setVisibility(View.INVISIBLE);
                bought.setVisibility(View.INVISIBLE);
                favorite_img.setVisibility(View.INVISIBLE);
                notification_img.setVisibility(View.INVISIBLE);
               // progressBar.setVisibility(View.VISIBLE);
                //days_left_text.setVisibility(View.VISIBLE);

            }
        }, 150);

    }




}
