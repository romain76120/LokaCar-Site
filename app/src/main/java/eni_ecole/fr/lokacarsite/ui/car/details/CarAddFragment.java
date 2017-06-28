package eni_ecole.fr.lokacarsite.ui.car.details;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.constant.Constant;
import eni_ecole.fr.lokacarsite.dao.CarDao;
import eni_ecole.fr.lokacarsite.dao.CategoryDao;
import eni_ecole.fr.lokacarsite.tools.QueryEvent;

public class CarAddFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    Car mItem;
    /**
     * The dummy content this fragment is presenting.
     */

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CarAddFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        super.onDestroy();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(QueryEvent event) {
        if (event.getAction() == Constant.ON_SAVE_CAR){

            // TODO
            EventBus.getDefault().post(new QueryEvent(Constant.DETAIL_CAR, -1, mItem));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_car_detail, container, false);
        // Show the dummy content as text in a TextView.
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {

            appBarLayout.setTitle("Création du véhicule");
        }

        return rootView;
    }
}
