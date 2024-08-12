package com.tsdl.practices.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.tsdl.practices.R;
import com.tsdl.practices.entity.Dish;
import com.tsdl.practices.config.WebConfig;
import com.tsdl.practices.util.ToastUtils;
import com.tsdl.practices.util.TypeUtils;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {

    private Context mContext;
    private List<Dish> mDishList;

    public DishAdapter(Context context, List<Dish> dishList) {
        mContext = context;
        mDishList = dishList;
    }

    public void setData(List<Dish> data) {
        mDishList = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dish, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dish dish = mDishList.get(position);
        if (!TypeUtils.isEmpty(dish.getImagePath())) {
            RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(mContext).load(WebConfig.getRequestUrl(WebConfig.URL_PROFILE) + dish.getImagePath())
                    .apply(options)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target,
                                                    boolean isFirstResource) {
                            holder.dishImage.setImageResource(R.mipmap.web_config);
                            try {
                                Thread.sleep(1000);
                            } catch (Exception exception) {

                            }
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                       DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(holder.dishImage);
        }
        holder.dishName.setText(dish.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(dish.getName());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDishList == null ? 0 : mDishList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView dishImage;
        TextView dishName;

        public ViewHolder(View view) {
            super(view);
            dishImage = (ImageView) view.findViewById(R.id.iv_dish);
            dishName = (TextView) view.findViewById(R.id.tv_name);
        }
    }
}
