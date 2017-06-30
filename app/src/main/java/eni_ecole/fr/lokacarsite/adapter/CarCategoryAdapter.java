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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.CarBrand;
import eni_ecole.fr.lokacarsite.beans.Category;

/**
 * Created by rroger2016 on 30/06/2017.
 */

public class CarCategoryAdapter extends ArrayAdapter<Category> {
    private final ArrayList<Category> carCategory;
    private final Context context;
    private final int ressourceId;
    private final Resources ressource;

    public CarCategoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Category> carCategory) {
        super(context, resource, carCategory);
        this.carCategory = carCategory;
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

        Category c = carCategory.get(position);
        TextView marque =(TextView) view.findViewById(R.id.id);
        marque.setText(c.name);
        return view;
    }
}
