package eni_ecole.fr.lokacarsite.ui.carmodel.list;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.CarModel;
import eni_ecole.fr.lokacarsite.dao.CarModelDao;
import eni_ecole.fr.lokacarsite.ui.carmodel.details.CarModelAddFragment;
import eni_ecole.fr.lokacarsite.ui.carmodel.details.CarModelDetailFragment;
import eni_ecole.fr.lokacarsite.ui.carmodel.details.CarModelModifyFragment;
import eni_ecole.fr.lokacarsite.ui.generic.list.GenericListFragment;

/**
 * Created by pbontempi2017 on 29/06/2017.
 */

public class CarModelListFragment extends GenericListFragment<CarModel> {


    private int lastFilterSelected ;

    @Override
    public int getIdObject(CarModel object) {
        return object.id;
    }

    @Override
    public CarModelDao getDao(Context context) {
        return new CarModelDao(context);
    }

    @Override
    public void constructListItem(View view, CarModel object) {
        TextView mIdView = (TextView) view.findViewById(R.id.id);
        TextView mContentView = (TextView) view.findViewById(R.id.content);
        TextView mDetailView = (TextView) view.findViewById(R.id.details);
        TextView mStatusView = (TextView) view.findViewById(R.id.contentStatus);

        mIdView.setText(object.name);

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
        return CarModelAddFragment.class;
    }

    @Override
    protected Class getDetailFragment() {
        return CarModelDetailFragment.class;
    }

    @Override
    protected Class getModifyFragment() {
        return CarModelModifyFragment.class;
    }

    @Override
    protected int getListLayout() {
        return R.layout.carmodel_list_content;
    }

}
