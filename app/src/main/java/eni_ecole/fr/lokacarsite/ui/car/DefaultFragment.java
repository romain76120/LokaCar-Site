package eni_ecole.fr.lokacarsite.ui.car;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eni_ecole.fr.lokacarsite.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DefaultFragment extends Fragment {


    public DefaultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_defaut, container, false);
    }

}
