package eni_ecole.fr.lokacarsite.ui.car.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import de.greenrobot.event.EventBus;
import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.constant.Constant;
import eni_ecole.fr.lokacarsite.dao.CarDao;
import eni_ecole.fr.lokacarsite.tools.QueryEvent;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class CarDetailActivity extends AppCompatActivity {

    private Integer mItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        mItemId = getIntent().getIntExtra(Constant.ID_CAR,-1);

        FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.action_delete);
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new QueryEvent(Constant.DELETE_CAR, mItemId));
                CarDetailActivity.this.finish();

            }
        });
        FloatingActionButton fabModify = (FloatingActionButton) findViewById(R.id.action_modify);
        fabModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new QueryEvent(Constant.MODIFY_CAR, mItemId));
            }
        });

        // On cache les boutons de modification si on est PAS admin
        if (!Constant.user.admin)
        {
            fabModify.setVisibility(View.INVISIBLE);
            fabDelete.setVisibility(View.INVISIBLE);
        }

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(Constant.ID_CAR,
                    getIntent().getIntExtra(Constant.ID_CAR,-1));


            CarDetailFragment fragment = new CarDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.car_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Si on est en mode portrait on rebascule sur la liste classique
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            this.finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
