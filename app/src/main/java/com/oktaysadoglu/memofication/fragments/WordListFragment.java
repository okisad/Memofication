package com.oktaysadoglu.memofication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.events.WordCardViewOnClickEvent;
import com.oktaysadoglu.memofication.interfaces.WordCardActivityInterface;
import com.oktaysadoglu.memofication.jobs.EvaluationWordCardJob;
import com.oktaysadoglu.memofication.model.WordCard;
import com.oktaysadoglu.memofication.recyclerViewAdapters.WordListRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordListFragment extends Fragment {

    @Bind(R.id.fragment_word_list_recycler_view)
    RecyclerView mRecyclerView;

    private int level,tabSection;

    private long wordStartIdInTab;

    private ArrayList<WordCard> mWordCardsInLevel;

    public static WordListFragment newInstance(int level,int tabSection){

       WordListFragment wordListFragment = new WordListFragment();

       Bundle bundle = new Bundle();
       bundle.putInt("level", level);
       bundle.putInt("tabSection", tabSection);

       wordListFragment.setArguments(bundle);

       return wordListFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mWordCardsInLevel = ((WordCardActivityInterface) getActivity()).getWordCard();

        level = getArguments().getInt("level");

        tabSection = getArguments().getInt("tabSection");

        setWordStartIdInTab(calculateStartWordId());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_word_list, container, false);

        ButterKnife.bind(this, view);

        setAdapterRecyclerView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        EventBus.getDefault().register(this);

    }

    @Override
    public void onPause() {
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(WordCardViewOnClickEvent event) {

        if (event.getTabSection() == tabSection){

            WordCardActivityInterface wordCardActivityInterface = (WordCardActivityInterface) getActivity();

            wordCardActivityInterface.controlOfAnswer(event.getPositionInTab(), event.getTabSection(),event.isTruth(),event.getWordId());

        }

    }

    public long getWordStartIdInTab() {
        return wordStartIdInTab;
    }

    public void setWordStartIdInTab(long wordStartIdInTab) {
        this.wordStartIdInTab = wordStartIdInTab;
    }

    private void setAdapterRecyclerView(){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(layoutManager);

        WordListRecyclerViewAdapter mWordListRecyclerViewAdapter = new WordListRecyclerViewAdapter(mWordCardsInLevel,tabSection);

        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(mWordListRecyclerViewAdapter));

    }

    private int calculateStartWordId(){

        int startLevelId = ((level*50)-50) + 1;

        if (tabSection == 0){

            return startLevelId;

        }else if (tabSection == 1){

            return startLevelId + 10;

        }else if (tabSection == 2){

            return startLevelId + 20;

        }else if(tabSection == 3){

            return startLevelId + 30;

        }else if(tabSection == 4){

            return startLevelId + 40;

        }

        return 1;
    }

    public void changedWordCardList(ArrayList<WordCard> wordCards){

        mWordCardsInLevel = wordCards;

        mRecyclerView.getAdapter().notifyDataSetChanged();

    }

    public void evaluateAnswer(int position,boolean truth,long wordId){

        Log.e("my","fi");

        View view = mRecyclerView.findViewHolderForAdapterPosition(position).itemView;

        if (truth){

            Log.e("my","fi");

            Toast.makeText(getActivity(), "doğru" ,Toast.LENGTH_SHORT).show();

            Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob(wordId,truth));

        }else {

            YoYo.with(Techniques.Shake).duration(500).playOn(view);

            Toast.makeText(getActivity(), "yanlış", Toast.LENGTH_SHORT).show();

            Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob(wordId, truth));

        }

        Log.e("my","fi");

        mWordCardsInLevel.remove(position);

        mRecyclerView.getAdapter().notifyDataSetChanged();

        Log.e("my", "fi "+mWordCardsInLevel.size());

    }



}
