package com.oktaysadoglu.memofication.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.events.WordCardViewEvent;
import com.oktaysadoglu.memofication.fragments.WordListFragment;
import com.oktaysadoglu.memofication.interfaces.WordCardActivityInterface;
import com.oktaysadoglu.memofication.jobs.WriteWordCardJob;
import com.oktaysadoglu.memofication.model.WordCard;
import com.oktaysadoglu.memofication.pagerAdapters.OneOfLevelPagerAdapter;
import com.oktaysadoglu.memofication.recyclerViewAdapters.LevelListRecyclerViewAdapter;
import com.oktaysadoglu.memofication.tools.DialogTools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LevelActivity extends BaseActivity{

/*    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.activity_level_navigation_view)
    NavigationView mNavigationView;
    @Bind(R.id.activity_level_navigation_view_notification_switch)
    Switch mNotificationOnOffSwitch;
    @Bind(R.id.activity_level_navigation_view_notification_number_button)
    Button mNotificationNumberButton;
    @Bind(R.id.app_bar_level_sliding_tabs)
    TabLayout mTabLayout;
    @Bind(R.id.app_bar_level_view_pager)
    ViewPager mViewPager;

    FragmentPagerAdapter mFragmentPagerAdapter;*/

    private int mLevelOfActivity;

    private ArrayList<WordCard> mWordCardsInLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        setArguments();

    }

    /*private void startWordCardsViewThreads(){

        mWordCardsInLevel = new ArrayList<>();

        int startId = ((mLevelOfActivity *50)-50) + 1;

        for(int i = startId ; i < startId + 50 ; i++){

            Memofication.getJobManager().addJobInBackground(new WriteWordCardJob(i));

        }

    }*/

    @Override
    protected void onStart() {

        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

        /*EventBus.getDefault().register(this);*/
    }

    @Override
    protected void onPause() {
        super.onPause();

        /*EventBus.getDefault().unregister(this);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){

            setIntentLevelValue();

            finish();

        }

        return true;
    }
*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            setIntentLevelValue();

            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_play) {

            Intent intent = new Intent();

            intent.putExtra("level", mLevelOfActivity);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

            setResult(LevelListRecyclerViewAdapter.START_LEVEL_REQUEST_CODE, intent);

            finish();

        } else if (id == R.id.nav_achievement) {

            Intent intent = new Intent(this,MainActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

            intent.putExtra("level", mLevelOfActivity);

            startActivity(intent);

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_level;
    }

    /*@Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
*/
    private void setArguments(){

        Bundle bundle = getIntent().getExtras();

        mLevelOfActivity = bundle.getInt("level");

        /*startWordCardsViewThreads();*/

    }

    /*private void registerListeners(){

        mNotificationNumberButton.setOnClickListener(this);

        mNotificationOnOffSwitch.setOnCheckedChangeListener(this);

        mNavigationView.setNavigationItemSelectedListener(this);

    }
*/
    private void setViewPager(){

        /*mFragmentPagerAdapter = new OneOfLevelPagerAdapter(getSupportFragmentManager(), mLevelOfActivity);

        mViewPager.setAdapter(mFragmentPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);*/

    }

   /* private void setToolbar(){

        setSupportActionBar(mToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
*/
    private void setIntentLevelValue(){

        Intent intent = new Intent();
        intent.putExtra("level", mLevelOfActivity);

        Log.e("my", mLevelOfActivity + " setIntentLEvel");

        setResult(LevelListRecyclerViewAdapter.START_LEVEL_REQUEST_CODE, intent);

    }

    /*@Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(WordCardViewEvent event) {

        mWordCardsInLevel.add(event.getWordCard());

        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        if(fragments.size() > 0){

            for (Fragment fragment :fragments){

                WordListFragment wordListFragment = (WordListFragment) fragment;

                wordListFragment.changedWordCardList(mWordCardsInLevel);

            }

        }

    }
*/
   /* @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(LevelActivity.this, "changedCheck", Toast.LENGTH_LONG).show();
    }
*/
    /*@Override
    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AppDialogTheme);

        View view = LayoutInflater.from(this).inflate(R.layout.navigation_change_number_of_notification_dialog,null);

        NumberPicker picker = (NumberPicker) view.findViewById(R.id.navigation_change_number_of_notification_dialog_number_picker);

        picker.setValue(0);
        picker.setMaxValue(10);
        picker.setMinValue(0);

        DialogTools dialogTools = new DialogTools();

        dialogTools.setNumberPickerTextColor(picker, ContextCompat.getColor(this, R.color.grey_900));
        dialogTools.setDividerColor(picker, ContextCompat.getColor(this, R.color.grey_700));

        final AlertDialog alertDialog = builder
                .setView(view)
                .setTitle("Number Of Notifications")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LevelActivity.this, "okay", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();


        alertDialog.show();

    }*/

    /*@Override
    public ArrayList<WordCard> getWordCard() {
        return mWordCardsInLevel;
    }

    @Override
    public void controlOfAnswer(int position, int section, boolean truth,long wordId) {

        WordListFragment wordListFragment = (WordListFragment) (getSupportFragmentManager().getFragments().get(section));

        wordListFragment.evaluateAnswer(position, truth,wordId);

    }
*/

}
