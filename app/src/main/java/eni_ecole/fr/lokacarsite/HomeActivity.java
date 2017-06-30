package eni_ecole.fr.lokacarsite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import de.greenrobot.event.EventBus;
import eni_ecole.fr.lokacarsite.constant.Constant;
import eni_ecole.fr.lokacarsite.tools.QueryEvent;
import eni_ecole.fr.lokacarsite.ui.car.list.CarListFragment;
import eni_ecole.fr.lokacarsite.ui.client.list.ClientListFragment;
import eni_ecole.fr.lokacarsite.ui.generic.list.GenericListFragment;
import eni_ecole.fr.lokacarsite.ui.user.list.UserListFragment;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new QueryEvent(Constant.ADD, -1));
            }
        });

        // On cache les boutons de modification si on est PAS admin
        if (!Constant.user.admin)
        {
            fab.setVisibility(View.INVISIBLE);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        setNavigation(R.id.nav_park);

        if (Constant.user.admin == false)
        {
            hideItemAdmin();
        }

    }

    private void hideItemAdmin()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_agency).setVisible(false);
        nav_Menu.findItem(R.id.nav_carbrand).setVisible(false);
        nav_Menu.findItem(R.id.nav_carmodel).setVisible(false);
        nav_Menu.findItem(R.id.nav_category).setVisible(false);
        nav_Menu.findItem(R.id.nav_user).setVisible(false);
        nav_Menu.findItem(R.id.nav_sales).setVisible(false);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.linearLayoutFragment, fragment)
                .commit();

    }

    private void setNavigation(Integer idMenu) {
        switch (idMenu) {
            case R.id.nav_park:
                switchFragment(new CarListFragment());
                break;
            case R.id.nav_client:
                switchFragment(new ClientListFragment());
                break;
            case R.id.nav_leasing:
                // TODO
                break;
            case R.id.nav_carmodel:
                // TODO
                break;
            case R.id.nav_carbrand:
                // TODO
                break;
            case R.id.nav_category:
                // TODO
                break;
            case R.id.nav_user:
                switchFragment(new UserListFragment());
                break;
            case R.id.nav_sales:
                switchFragment(new CarListFragment());
                break;
            case R.id.nav_agency:
                // TODO
                break;
            case R.id.nav_disconnection:
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }

//
//        android:id="@+id/nav_park"
//        android:title="@string/nav_park" />
//        <item
//        android:id="@+id/nav_client"
//        android:title="@string/nav_client" />
//        <item
//        android:id="@+id/nav_leasing"
//        android:title="@string/nav_leasing" />
//        <item
//        android:id="@+id/nav_carmodel"
//        android:title="@string/nav_carmodel" />
//        <item
//        android:id="@+id/nav_carbrand"
//        android:title="@string/nav_carbrand" />
//        <item
//        android:id="@+id/nav_category"
//        android:title="@string/nav_category" />
//        <item
//        android:id="@+id/nav_sales"
//        android:title="@string/nav_sales" />
//        <item
//        android:id="@+id/nav_user"
//        android:title="@string/nav_user" />
//        <item
//        android:id="@+id/nav_agency"
//        android:title="@string/nav_agency" />

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        setNavigation(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
