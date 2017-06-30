package eni_ecole.fr.lokacarsite.ui.car.details;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.adapter.CarBrandsAdapter;
import eni_ecole.fr.lokacarsite.adapter.CarModelsAdapter;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.CarBrand;
import eni_ecole.fr.lokacarsite.constant.Constant;
import eni_ecole.fr.lokacarsite.dao.CarBrandDao;
import eni_ecole.fr.lokacarsite.dao.CarDao;
import eni_ecole.fr.lokacarsite.tools.QueryEvent;

public class CarModifyFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */

    /**
     * The dummy content this fragment is presenting.
     */
    private Car mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CarModifyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(Constant.ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = new CarDao(getContext()).get(getArguments().getInt(Constant.ID));


            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle("Modification du véhicule");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_car_modify, container, false);
        ArrayAdapter adapter = new CarBrandsAdapter(getActivity(),R.layout.carbrand_list_content, new CarBrandDao(getContext()).get());
        ImageView idphotomodify;
        Spinner marquespinnermodify;
        Spinner intitulespinnermodify;
        Spinner typespinnermodify;
        Spinner carburantspinnermodify;
        EditText immatspinnermodify;
        EditText prixspinnermodify;


        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            idphotomodify = (ImageView) rootView.findViewById(R.id.idphotomodify);
            marquespinnermodify = (Spinner) rootView.findViewById(R.id.marquespinnermodify);
            intitulespinnermodify = (Spinner) rootView.findViewById(R.id.intitulespinnermodify);
            typespinnermodify = (Spinner) rootView.findViewById(R.id.typespinnermodify);
            carburantspinnermodify = (Spinner) rootView.findViewById(R.id.carburantspinnermodify);
            immatspinnermodify = (EditText) rootView.findViewById(R.id.immatspinnermodify);
            prixspinnermodify = (EditText) rootView.findViewById(R.id.prixspinnermodify);

            ArrayList<String> carburants = new ArrayList<String>();
            carburants.add("Diesel");
            carburants.add("Essence");
            carburantspinnermodify.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,carburants));
            immatspinnermodify.setText(mItem.registration);
            prixspinnermodify.setText(String.valueOf(mItem.price) + " €");

        }

        return rootView;
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
        if (event.getAction() == Constant.ON_SAVE){
            // TODO
            EventBus.getDefault().post(new QueryEvent(Constant.DETAIL, mItem.id, mItem));
        }
    }
}
