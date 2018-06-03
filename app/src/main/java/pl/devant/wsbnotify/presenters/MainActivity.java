package pl.devant.wsbnotify.presenters;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import pl.devant.wsbnotify.R;
import pl.devant.wsbnotify.fragments.FragmentInteractionListener;
import pl.devant.wsbnotify.fragments.factory.FragmentFacotry;
import pl.devant.wsbnotify.models.data.preferences.ManageSharedPreferences;
import pl.devant.wsbnotify.models.data.weather.Globals;


public class MainActivity extends AppCompatActivity implements FragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    Globals g = Globals.getInstance();

    private FragmentFacotry fragmentFacotry;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggleButton;

    private ManageSharedPreferences manageSharedPreferences;
    private View dView;
    private Button rusBtn;
    private Button polBtn;
    private Button ukBtn;
    private SwitchCompat nSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manageSharedPreferences = new ManageSharedPreferences(getApplicationContext());

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        drawerLayout = findViewById(R.id.drawer_layout);
        toggleButton = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggleButton);
        toggleButton.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout =  findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        nSwitch = findViewById(R.id.notifi_switch);
        Menu menu = navigationView.getMenu();
        MenuItem item = menu.findItem(R.id.notifi_onoff);
        View actionToggleView = MenuItemCompat.getActionView(item);
        nSwitch = actionToggleView.findViewById(R.id.notifi_switch);
        nSwitch.setChecked(manageSharedPreferences.getShowNotification());


        nSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    manageSharedPreferences.setShowNotification(true);
                    Toast.makeText(MainActivity.this, "Powiadomienia włączone", Toast.LENGTH_SHORT).show();
                }
                else{
                    manageSharedPreferences.setShowNotification(false);
                    Toast.makeText(MainActivity.this, "Powiadomienia wyłączone", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fragmentFacotry = new FragmentFacotry();
        manageSharedPreferences = new ManageSharedPreferences(MainActivity.this);

    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        moveTaskToBack(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton ButtonView, boolean isChecked) {

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.language)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            dView = getLayoutInflater().inflate(R.layout.language_dialog, null);
            rusBtn = dView.findViewById(R.id.buttonFlagRussia);
            ukBtn = dView.findViewById(R.id.buttonFlagUk);
            polBtn = dView.findViewById(R.id.buttonFlagPolish);
            builder.setView(dView);
            final AlertDialog dialog = builder.create();
            dialog.show();
            rusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manageSharedPreferences.setLanguage("ru");
                    dialog.dismiss();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                    startActivity(intent);
                }
            });
            ukBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manageSharedPreferences.setLanguage("en");
                    dialog.dismiss();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                    startActivity(intent);
                }
            });
            polBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manageSharedPreferences.setLanguage("pl");
                    dialog.dismiss();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                    startActivity(intent);
                }
            });


        }
        else if (id == R.id.send_opinion)
        {
            Toast.makeText(this, "Oceniono", Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if (id == R.id.devant_contact)
        {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "kontakt@devant.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text");
            this.startActivity(Intent.createChooser(emailIntent, null));
        }
        return true;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return null;
    }

    public void onFragmentInteraction(Uri uri) {

    }

//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return fragmentFacotry.getFragment(position);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }

    public void selectFragment(int position){
        mViewPager.setCurrentItem(position, true);
    }

}
