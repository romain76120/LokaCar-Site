package eni_ecole.fr.lokacarsite.ui.client.details;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.greenrobot.event.EventBus;
import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.Client;
import eni_ecole.fr.lokacarsite.constant.Constant;
import eni_ecole.fr.lokacarsite.tools.QueryEvent;

public class ClientAddFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    Client mItem;
    /**
     * The dummy content this fragment is presenting.
     */

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ClientAddFragment() {
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
        if (event.getAction() == Constant.ON_SAVE){

            // TODO
            EventBus.getDefault().post(new QueryEvent(Constant.DETAIL, -1, mItem));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_client_add, container, false);
        // Show the dummy content as text in a TextView.
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {

            appBarLayout.setTitle("Création du client");
        }

        return rootView;
    }
}
