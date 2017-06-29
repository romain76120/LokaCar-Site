package eni_ecole.fr.lokacarsite.ui.car.list;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

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

public class CarListFragment extends GenericListFragment<Car>{


    @Override
    public int getIdObject(Car object) {
        return object.id;
    }

    @Override
    public ObjectDao<Car> getDao(Context context) {
        return new CarDao(context);
    }

    @Override
    public void constructListItem(View view, Car object) {

    }

    @Override
    protected void onOptionalOptionItemSelected(MenuItem item) {

    }

    @Override
    protected int[] getOptionalMenuItem() {
        return new int[0];
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
