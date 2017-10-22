package id.erictriawan.taskcermati.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.erictriawan.taskcermati.R;
import id.erictriawan.taskcermati.adapter.UserAdapter;
import id.erictriawan.taskcermati.model.UserModel;
import id.erictriawan.taskcermati.network.NetworkUtil;
import id.erictriawan.taskcermati.utils.OnLoadMoreListener;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private Toolbar toolbar;
    private CompositeSubscription mSubscriptions;
    private Context context;
    private UserAdapter adapter;
    private List<UserModel.Items> userList;
    private TextView tvNotFound;
    RecyclerView.LayoutManager layoutManager;
    int limit = 5;
    String queryData;
    ProgressBar progessBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSubscriptions = new CompositeSubscription();
        context = this;

        renderView();

    }



    private void renderView() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        tvNotFound = (TextView) findViewById(R.id.tvBlankPage);
        progessBar = (ProgressBar) findViewById(R.id.loading);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchView = (SearchView) toolbar.findViewById(R.id.searchview);

        //recyclerview
        userList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        adapter = new UserAdapter(userList,context,recyclerView,true);

        recyclerView.setAdapter(adapter);



        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Code for the UiThread
                userList.clear();
                recyclerView.setAdapter(adapter);
                layoutManager.removeAllViews();
                adapter.notifyDataSetChanged();
            }
        });


        //searchview
        searchView.setOnQueryTextListener(this);
        searchView.setQuery("", false);
        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {

            @Override
            public boolean onClose() {
                // Put your code here to clear and display the results
                Log.e("Print","close :");
                return true;
            }

        });






    }

    @Override
    public boolean onQueryTextSubmit(final String query) {

        if(TextUtils.isEmpty(query)){

            tvNotFound.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

            userList.clear();
            recyclerView.setAdapter(adapter);
            layoutManager.removeAllViews();

            adapter.notifyDataSetChanged();


        }else {
            //loadData(query);
            Log.e("Print","close :");
            queryData = query;

            adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    Log.d("onLoadMore", "onLoadMore");


                    //Load more data for reyclerview
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            limit += 5;
                            loadData(query, limit);
                        }
                    }, 1000);


                }

            });

            loadData(query, limit);
        }

        return false;
    }


    private void loadData(final String q,final int limit) {





        mSubscriptions.add(NetworkUtil.getRetrofit().apiSearch(q,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserModel>() {
                    @Override
                    public void onCompleted() {
                        //
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("Print","error :"+e.getMessage());
                        tvNotFound.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);

                    }

                    @Override
                    public void onNext(UserModel userModel) {


                        if (userModel != null){

                            if (userModel.getmItems() != null){

                                tvNotFound.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);




                                if (limit == 5) {
                                    //hideDialog();
                                    // dialog.hide();
                                }

                                if (userList != null) {
                                    if (userModel.getmItems().size() < limit) {
                                        adapter.setCanLoadmore(false);
                                    }
                                    if (limit == 5) {
                                        for (UserModel.Items item : userModel.getmItems()){
                                            userList.add(item);
                                        }
                                    } else {

                                        List<UserModel.Items> ltemp = userModel.getmItems().subList(userList.size(), userModel.getmItems().size());
                                        userList.addAll(ltemp);
                                    }
                                    adapter.notifyDataSetChanged();
                                } else {
                                    tvNotFound.setVisibility(View.VISIBLE);
                                    //Util.SnackBar(layout, getResources().getString(R.string.nodata));
                                }
                                //setStatusCampiagn(campaignListModel.getData().getStatusCampaign());


                                adapter.setLoaded();






                            }else {
                                Toast.makeText(context,"Empty Data",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            progessBar.setVisibility(View.VISIBLE);
                        }



                    }
                }));
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;


        if(TextUtils.isEmpty(newText)) {

            tvNotFound.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            userList.clear();
            recyclerView.setAdapter(adapter);
            layoutManager.removeAllViews();
            adapter.notifyDataSetChanged();


        }

        return false;
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        tvNotFound.setVisibility(View.GONE);
    }
}
