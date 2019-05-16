package com.example.thelast.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thelast.adapter.AdapterAlbumsHome;
import com.example.thelast.adapter.AdapterAllSongListHome;
import com.example.thelast.adapter.AdapterArtist;
import com.example.thelast.adapter.AdapterCategory;
import com.example.thelast.apis.Constant;
import com.example.thelast.asycn.RequestHome;
import com.example.thelast.asycn.RequestMurottal;
import com.example.thelast.interfaces.ClickListenerPlayList;
import com.example.thelast.interfaces.HomeRequest;
import com.example.thelast.interfaces.InterAdListener;
import com.example.thelast.ownmodal.Album;
import com.example.thelast.ownmodal.Artist;
import com.example.thelast.ownmodal.Category;
import com.example.thelast.ownmodal.ItemSong;
import com.example.thelast.ownmodal.MisharyRashid;
import com.example.thelast.ownmodal.MuhammadThaha;
import com.example.thelast.tampilan.PlayerService;
import com.example.thelast.utils.GlobalBus;
import com.example.thelast.utils.MethodJava;
import com.example.thelast.utils.Methods;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import supriyanto.tampilan.R;


public class FragmentHome extends Fragment {

    View view;
    LinearLayout ll_popular, ll_album, ll_category, ll_artist, ll_all;
    TextView tv_popular, tv_album, tv_category, tv_artist, tv_all;
    RecyclerView rv_popular, rv_album, rv_category, rv_artist, rv_all;

    //All ArrayList
    ArrayList<MisharyRashid> misharyRashids;
    ArrayList<ItemSong> songArrayList;
    ArrayList<Album> albumArrayList;
    ArrayList<Artist> artistArrayList;
    ArrayList<Category> categoryArrayList;

    //Component
    Methods methodJava;
    GridLayoutManager manager, category, album, song;

    //All Adapter
    AdapterArtist adapterArtist;
    AdapterCategory adapterCategory;
    AdapterAlbumsHome adapterAlbumsHome;
    AdapterAllSongListHome adapterAllSongList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        methodJava = new Methods(getActivity());

        //LinearLayout
        ll_popular = view.findViewById(R.id.ll_popular);
        ll_album = view.findViewById(R.id.ll_album);
        ll_category = view.findViewById(R.id.ll_category);
        ll_artist = view.findViewById(R.id.ll_artist);
        ll_all = view.findViewById(R.id.ll_all);

        //TextView
        tv_popular = view.findViewById(R.id.tv_popular);
        tv_album = view.findViewById(R.id.tv_album);
        tv_category = view.findViewById(R.id.tv_category);
        tv_artist = view.findViewById(R.id.tv_artist);
        tv_all = view.findViewById(R.id.tv_all);

        //RecyclerView
        rv_popular = view.findViewById(R.id.rv_popular);
        rv_album = view.findViewById(R.id.rv_album);
        rv_category = view.findViewById(R.id.rv_category);
        rv_artist = view.findViewById(R.id.rv_artist);
        rv_all = view.findViewById(R.id.rv_all);

        //Layout
        manager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        category = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        album = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.HORIZONTAL, false);
        song = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.HORIZONTAL, false);

        //RecyclerView
        rv_artist.setLayoutManager(manager);
        rv_category.setLayoutManager(category);
        rv_album.setLayoutManager(album);
        rv_all.setLayoutManager(song);

        methodJava = new Methods(getActivity(), new InterAdListener() {
            @Override
            public void onClick(int position, String type) {
                Constant.isOnline = true;
                Constant.arrayList_play.clear();
                Constant.arrayList_play.addAll(songArrayList);
                Constant.playPos = position;
                Log.d("datasiap", "");
                Intent intent = new Intent(getActivity(), PlayerService.class);
                intent.setAction(PlayerService.ACTION_PLAY);
                getActivity().startService(intent);
            }
        });

        //DeclareArrayList
        misharyRashids = new ArrayList<>();
        songArrayList= new ArrayList<>();
        artistArrayList = new ArrayList<>();
        categoryArrayList = new ArrayList<>();
        albumArrayList = new ArrayList<>();

        getAllData();

        return view;

    }

    private void getAllData() {

        if (methodJava.isNetworkAvailable()){

            RequestHome requestHome = new RequestHome(new HomeRequest() {
                @Override
                public void onStart() {
                    ll_category.setVisibility(View.GONE);
                    ll_popular.setVisibility(View.GONE);
                    ll_artist.setVisibility(View.GONE);
                    ll_all.setVisibility(View.GONE);
                    ll_album.setVisibility(View.GONE);

                }

                @Override
                public void onEnd(String success, ArrayList<Category> categories, ArrayList<Album> albums, ArrayList<Artist> artists, ArrayList<ItemSong> itemSongs) {

                    if (getActivity() != null){

                        if (success.equals("1")){

                            albumArrayList.addAll(albums);
                            categoryArrayList.addAll(categories);
                            artistArrayList.addAll(artists);
                            songArrayList.addAll(itemSongs);

                            adapterCategory = new AdapterCategory(getActivity(), categoryArrayList);
                            rv_category.setAdapter(adapterCategory);

                            adapterArtist = new AdapterArtist(artistArrayList, getActivity());
                            rv_artist.setAdapter(adapterArtist);

                            adapterAlbumsHome = new AdapterAlbumsHome(getActivity(), albumArrayList);
                            rv_album.setAdapter(adapterAlbumsHome);

                            adapterAllSongList = new AdapterAllSongListHome(songArrayList, getActivity(), new ClickListenerPlayList() {
                                @Override
                                public void onClick(int position) {
                                    methodJava.showInterAd(position, "");
                                }

                                @Override
                                public void onItemZero() {

                                }
                            }, "online");
                            rv_all.setAdapter(adapterAllSongList);

                            setEmpty();

                        }
                    }
                }
            });
            requestHome.execute(Constant.URL_HOME);
        }
    }

    private void setEmpty(){
        if (artistArrayList.size() == 0){
            ll_artist.setVisibility(View.GONE);
        }else{
            ll_artist.setVisibility(View.VISIBLE);
        }

        if (categoryArrayList.size() == 0){
            ll_category.setVisibility(View.GONE);
        }else{
            ll_category.setVisibility(View.VISIBLE);
        }

        if (albumArrayList.size() == 0){
            ll_album.setVisibility(View.GONE);
        }else{
            ll_album.setVisibility(View.VISIBLE);
        }

        if (songArrayList.size() == 0){
            ll_all.setVisibility(View.GONE);
        }else{
            ll_all.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEquilizerChange(Album itemAlbums) {
        adapterAllSongList.notifyDataSetChanged();
        GlobalBus.getBus().removeStickyEvent(itemAlbums);
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onStop() {
        GlobalBus.getBus().unregister(this);
        super.onStop();
    }
}