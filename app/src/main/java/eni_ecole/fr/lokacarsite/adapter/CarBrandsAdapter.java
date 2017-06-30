package eni_ecole.fr.lokacarsite.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.CarBrand;
import eni_ecole.fr.lokacarsite.beans.CarModel;
import eni_ecole.fr.lokacarsite.dao.CarBrandDao;
import eni_ecole.fr.lokacarsite.dao.CarDao;
import eni_ecole.fr.lokacarsite.dao.CarModelDao;

/**
 * Created by phili on 26/06/2017.
 */

public class CarBrandsAdapter extends ArrayAdapter<CarBrand> {
    private final ArrayList<CarBrand> carBrands;
    private final Context context;
    private final int ressourceId;
    private final Resources ressource;

    public CarBrandsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<CarBrand> carBrands) {
        super(context, resource, carBrands);
        this.carBrands = carBrands;
        this.context = context;
        this.ressourceId = resource;
        this.ressource = context.getResources();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            view = inflater.inflate(ressourceId, parent, false);
        }
        else
        {
            view = convertView;
        }

        CarBrand cb = carBrands.get(position);
       // oneCarBrand = carBrands.get(position);
        TextView marque =(TextView) view.findViewById(R.id.id);
        marque.setText(cb.name);
        return view;
    }
}
