package eni_ecole.fr.lokacarsite.ui.generic.details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import java.lang.reflect.InvocationTargetException;

import de.greenrobot.event.EventBus;
import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.constant.Constant;
import eni_ecole.fr.lokacarsite.tools.FragmentManager;
import eni_ecole.fr.lokacarsite.tools.QueryEvent;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class GenericDetailActivity extends AppCompatActivity {

    private Integer mItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        mItemId = getIntent().getIntExtra(Constant.ID,-1);

        FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.action_delete);
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new QueryEvent(Constant.DELETE, mItemId));
                GenericDetailActivity.this.finish();

            }
        });
        FloatingActionButton fabModify = (FloatingActionButton) findViewById(R.id.action_modify);
        fabModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new QueryEvent(Constant.MODIFY, mItemId));
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
            arguments.putInt(Constant.ID,
                    getIntent().getIntExtra(Constant.ID,-1));


            Fragment fragment = null;
            try {
                fragment = FragmentManager.getFragment();
                fragment.setArguments(arguments);
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
