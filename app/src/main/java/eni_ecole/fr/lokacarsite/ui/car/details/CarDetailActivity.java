package eni_ecole.fr.lokacarsite.ui.car.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.constant.Constant;
import eni_ecole.fr.lokacarsite.dao.CarDao;
import eni_ecole.fr.lokacarsite.ui.car.modify.CarModifyActivity;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class CarDetailActivity extends AppCompatActivity {

    private Car mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        mItem = new CarDao().getFromId(getIntent().getIntExtra(CarDetailFragment.ARG_ITEM_ID,-1));
        FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.action_delete);
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CarDao().delete(mItem.id);
                Snackbar.make(view, R.string.action_delete_result, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                CarDetailActivity.this.finish();
            }
        });
        FloatingActionButton fabModify = (FloatingActionButton) findViewById(R.id.action_modify);
        fabModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(CarDetailActivity.this, CarModifyActivity.class);
                intent.putExtra(Constant.ID_CAR, mItem.id);
                startActivity(intent);
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Si on est en mode portrait on rebascule sur la liste classique
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            this.finish();
        }
        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(CarDetailFragment.ARG_ITEM_ID,
                    getIntent().getIntExtra(CarDetailFragment.ARG_ITEM_ID,-1));


            CarDetailFragment fragment = new CarDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.car_detail_container, fragment)
                    .commit();
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
