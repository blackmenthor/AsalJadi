package slapdevstudio.asaljadi;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends AppCompatActivity {
    static Active active;
    static Template template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if(active == null)
            active = new Active();
        if(template == null)
            template = new Template();

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        pager.setAdapter(new AdapterTab(getSupportFragmentManager()));

        tabs.setTextColorResource(R.color.white);
        tabs.setTextSize(20);

        tabs.setViewPager(pager);

        ImageView overflow = (ImageView) findViewById(R.id.overflow);
        overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is an android.support.v7.widget.PopupMenu;
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.setOnMenuItemClickListener(new ItemListener());
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.menu_main, popupMenu.getMenu());
                popupMenu.show();
            }
        });

    }

    class ItemListener implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch(item.getItemId()){
                case R.id.action_about:
                    return true;
                case R.id.action_exit:
                    finish();
                    return true;
                default:
                    return true;
            }
        }
    }

    class AdapterTab extends FragmentPagerAdapter {

        public AdapterTab(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return active;
                case 1: return template;
                default: return active;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "active";
                case 1: return "templates";
                default: return "active";
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}