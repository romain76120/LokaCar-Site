package eni_ecole.fr.lokacarsite.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import eni_ecole.fr.lokacarsite.constent.Constant;
import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.CarModel;
import eni_ecole.fr.lokacarsite.dao.CarModelDao;

/**
 * Created by phili on 26/06/2017.
 */

public class CarModelsAdapter extends ArrayAdapter<CarModel> {
    private final ArrayList<CarModel> carModels;
    private final Context context;
    private final int ressourceId;
    private final Resources ressource;

    public CarModelsAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull ArrayList<CarModel> carModels) {
        super(context, resource, textViewResourceId, carModels);
        this.carModels = carModels;
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

        CarModel oneCarModel;

        TextView listCarBrand;

        listCarBrand = (TextView) view.findViewById(R.id.list_car_model);

        oneCarModel = carModels.get(position);

        return view;
    }
}
