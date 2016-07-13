package nz.co.liuming.cityfriends.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.basecomponents.BaseActivity;
import nz.co.liuming.cityfriends.common.basecomponents.BaseFragment;
import nz.co.liuming.cityfriends.home.fragments.CalendarFragment;
import nz.co.liuming.cityfriends.home.fragments.CreateFeedFragment;
import nz.co.liuming.cityfriends.home.fragments.FeedFragment;
import nz.co.liuming.cityfriends.home.fragments.MessageFragment;
import nz.co.liuming.cityfriends.users.LoginActivity;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment(CreateFeedFragment.newInstance());
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        setupViewPager();
        updateNavigationView();
    }

    private void setupViewPager() {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),
                HomeActivity.this);
        adapter.addFragment(FeedFragment.newInstance());
        adapter.addFragment(CalendarFragment.newInstance());
        adapter.addFragment(MessageFragment.newInstance());
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void updateNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
//
//        navigationView.getMenu().findItem(R.id.nav_camera).setEnabled(false);
//        navigationView.getMenu().findItem(R.id.nav_gallery).setVisible(false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_freind) {

        } else if (id == R.id.nav_message) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<BaseFragment> mFragments = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            if (position >= 0 && position < mFragments.size()) {
                return mFragments.get(position);
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position >= 0 && position < mFragments.size()) {
                return mFragments.get(position).getFragmentTitle();
            }
            return "";
        }

        void addFragment(BaseFragment aFragment) {
            mFragments.add(aFragment);
        }
    }
}
