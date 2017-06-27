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
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.CarBrand;
import eni_ecole.fr.lokacarsite.beans.CarModel;
import eni_ecole.fr.lokacarsite.dao.CarBrandDao;
import eni_ecole.fr.lokacarsite.dao.CarDao;
import eni_ecole.fr.lokacarsite.dao.CarModelDao;

/**
 * Created by phili on 26/06/2017.
 */

public class CarsAdapter extends ArrayAdapter<Car> {
    private final ArrayList<Car> cars;
    private final Context context;
    private final int ressourceId;
    private final Resources ressource;

    public CarsAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull ArrayList<Car> cars) {
        super(context, resource, textViewResourceId, cars);
        this.cars = cars;
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

        Car oneCar;
        CarBrand oneCarBrand;
        CarModel oneCarModel;

        CarBrandDao carBrandDao;
        CarModelDao carModelDao;

        TextView listCarTitle;
        TextView listCarTextLine1;
        TextView listCarTextLine2;
        TextView listCarStatus;

        listCarTitle = (TextView) view.findViewById(R.id.list_car_title);
        listCarTextLine1 = (TextView) view.findViewById(R.id.list_car_text_line1);
        listCarTextLine2 = (TextView) view.findViewById(R.id.list_car_text_line2);
        listCarStatus = (TextView) view.findViewById(R.id.list_car_status);

        carBrandDao = new CarBrandDao();
        carModelDao = new CarModelDao();

        oneCar = cars.get(position);
        oneCarModel = carModelDao.getFromId(oneCar.idCarModel);
        oneCarBrand = carBrandDao.getFromId(oneCarModel.idCarBrand);

        listCarTitle.setText(oneCar.type);
        listCarTextLine1.setText(oneCarBrand.name + " " + oneCarModel.name);
        listCarTextLine2.setText(oneCar.fuel);

        if (oneCar.isLeasing)
        {
            listCarStatus.setVisibility(View.VISIBLE);
            listCarStatus.setText(R.string.string_list_car_status);
        }
        else
        {
            listCarStatus.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
