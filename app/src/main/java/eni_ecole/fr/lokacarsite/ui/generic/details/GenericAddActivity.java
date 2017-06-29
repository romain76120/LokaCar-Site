package eni_ecole.fr.lokacarsite.ui.generic.details;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.InvocationTargetException;

import de.greenrobot.event.EventBus;
import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.constant.Constant;
import eni_ecole.fr.lokacarsite.tools.FragmentManager;
import eni_ecole.fr.lokacarsite.tools.QueryEvent;
import eni_ecole.fr.lokacarsite.ui.car.details.CarAddFragment;

public class GenericAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fabSave = (FloatingActionButton) findViewById(R.id.action_save);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new QueryEvent(Constant.ON_SAVE));
                GenericAddActivity.this.finish();

            }
        });
        FloatingActionButton fabCancel = (FloatingActionButton) findViewById(R.id.action_cancel);
        fabCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenericAddActivity.this.finish();
            }
        });
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            Fragment fragment = null;
            try {
                fragment = FragmentManager.getFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.car_detail_container, fragment)
                        .commit();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }

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
