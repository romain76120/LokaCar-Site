package eni_ecole.fr.lokacarsite.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.CarBrand;

/**
 * Created by rroger2016 on 30/06/2017.
 */

public class CarAdapter extends ArrayAdapter<Car> {
    private final ArrayList<Car> car;
    private final Context context;
    private final int ressourceId;
    private final Resources ressource;

    public CarAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Car> car) {
        super(context, resource, car);
        this.car = car;
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
         ImageView picture;
         TextView id;
         TextView content;
         TextView details;
         TextView contentStatus;
         Car c = car.get(position);

        //picture = (ImageView) view.findViewById(R.id.list.picture);
        id = (TextView) view.findViewById(R.id.id);
        content = (TextView) view.findViewById(R.id.content);
        details = (TextView) view.findViewById(R.id.details);
        contentStatus = (TextView) view.findViewById(R.id.contentStatus);


        // oneCarBrand = carBrands.get(position);
       // picture.setImageURI(Uri.parse());
        content.setText(c.carModel.name);
        details.setText(c.fuel);
        contentStatus.setText(String.valueOf(c.leasings));
        return view;
    }
}
