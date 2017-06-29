package eni_ecole.fr.lokacarsite.ui.car.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.dao.CarDao;
import eni_ecole.fr.lokacarsite.dao.ObjectDao;
import eni_ecole.fr.lokacarsite.ui.car.details.CarAddFragment;
import eni_ecole.fr.lokacarsite.ui.car.details.CarDetailFragment;
import eni_ecole.fr.lokacarsite.ui.car.details.CarModifyFragment;
import eni_ecole.fr.lokacarsite.ui.generic.list.GenericListFragment;

/**
 * Created by pbontempi2017 on 29/06/2017.
 */

public class CarListFragment extends GenericListFragment<Car> {


    private int lastFilterSelected ;

    @Override
    public int getIdObject(Car object) {
        return object.id;
    }

    @Override
    public CarDao getDao(Context context) {
        return new CarDao(context);
    }

    @Override
    public void constructListItem(View view, Car object) {
        TextView mIdView = (TextView) view.findViewById(R.id.id);
        TextView mContentView = (TextView) view.findViewById(R.id.content);
        TextView mDetailView = (TextView) view.findViewById(R.id.details);
        TextView mStatusView = (TextView) view.findViewById(R.id.contentStatus);

        mIdView.setText(object.category.name);

        mContentView.setText(object.carModel.carBrand.name + " " + object.carModel.name);
        mDetailView.setText(object.fuel);

        if (object.isLeasing) {
            mStatusView.setText(R.string.string_list_car_status);
        }

    }

    @Override
    protected void onOptionalOptionItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_all:
                lastFilterSelected = item.getItemId();
                break;
            case R.id.action_rented:
                lastFilterSelected = item.getItemId();
                break;
            case R.id.action_no_rented:
                lastFilterSelected = item.getItemId();
                break;

        }
        reloadList();
    }

    @Override
    protected ArrayList<Car> constructList() {
        ArrayList<Car> cars = new ArrayList<>();
        switch (lastFilterSelected) {
            case R.id.action_all:
                cars = getDao(getContext()).get();
                break;
            case R.id.action_rented:
                cars = getDao(getContext()).getRented(true);
                break;
            case R.id.action_no_rented:
                cars = getDao(getContext()).getRented(false);
                break;
            default:
                cars = getDao(getContext()).get();
                break;
        }
        return cars;
    }

    @Override
    protected int[] getOptionalMenuItem() {
        return new int[]{R.menu.car_list};
    }

    @Override
    protected Class getAddFragment() {
        return CarAddFragment.class;
    }

    @Override
    protected Class getDetailFragment() {
        return CarDetailFragment.class;
    }

    @Override
    protected Class getModifyFragment() {
        return CarModifyFragment.class;
    }

}
