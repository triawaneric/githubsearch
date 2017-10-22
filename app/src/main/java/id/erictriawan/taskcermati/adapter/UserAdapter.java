package id.erictriawan.taskcermati.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.erictriawan.taskcermati.R;
import id.erictriawan.taskcermati.model.UserModel;
import id.erictriawan.taskcermati.utils.OnLoadMoreListener;


/**
 * Created by erictriawan on 10/20/17.
 */


public class UserAdapter extends RecyclerView.Adapter {
    private List<UserModel.Items> data;
    private Context context;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private OnLoadMoreListener onLoadMoreListener;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private RecyclerView recyclerView;
    boolean canLoadmore = false;
    private boolean loading;




    public UserAdapter(List<UserModel.Items> data, Context context,RecyclerView recyclerView,boolean canLoadmore) {
        this.data = data;
        this.context = context;
        this.recyclerView =recyclerView;
        this.canLoadmore = canLoadmore;


        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();
            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && totalItemCount <= (lastVisibleItem + visibleThreshold) && UserAdapter.this.canLoadmore) {
                                // End has been reached
                                // Do something
                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.onLoadMore();
                                }
                                loading = true;
                            }
                        }
                    });
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false);
            vh = new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.layout_loading_item, parent, false);
            vh = new ProgressViewHolder(v);
        }
        return vh;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof ViewHolder) {
            UserModel.Items userModel = data.get(position);


            if (userModel != null){
                ((ViewHolder)holder).text.setText(userModel.getmLogin());

                Glide.with(context)
                        .load(userModel.getmAvatarUrl())
                        .placeholder(R.drawable.image_placeholder)
                        .into(((ViewHolder)holder).imgView);

            }

        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }



    }

    @Override
    public int getItemViewType(int position) {
        if (!data.isEmpty() && position >= data.size() && canLoadmore) {
            return VIEW_TYPE_LOADING;
        }
        return VIEW_TYPE_ITEM; //listAds.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }


    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        if (!data.isEmpty() && canLoadmore) {
            return data.size() + 1;
        }
        return data.size();
    }



    public void setCanLoadmore(boolean canLoadmore) {
        this.canLoadmore = canLoadmore;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ImageView imgView;

        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.tvName);
            imgView =(ImageView) v.findViewById(R.id.imgProfile);
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }


    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }



}
