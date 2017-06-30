package eni_ecole.fr.lokacarsite.ui.carbrand.list;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.CarBrand;
import eni_ecole.fr.lokacarsite.beans.Client;
import eni_ecole.fr.lokacarsite.dao.CarBrandDao;
import eni_ecole.fr.lokacarsite.dao.ClientDao;
import eni_ecole.fr.lokacarsite.ui.carbrand.details.CarBrandAddFragment;
import eni_ecole.fr.lokacarsite.ui.carbrand.details.CarBrandDetailFragment;
import eni_ecole.fr.lokacarsite.ui.carbrand.details.CarBrandModifyFragment;
import eni_ecole.fr.lokacarsite.ui.generic.list.GenericListFragment;

/**
 * Created by pbontempi2017 on 29/06/2017.
 */

public class CarBrandListFragment extends GenericListFragment<CarBrand> {


    private int lastFilterSelected ;

    @Override
    public int getIdObject(CarBrand object) {
        return object.id;
    }

    @Override
    public CarBrandDao getDao(Context context) {
        return new CarBrandDao(context);
    }

    @Override
    public void constructListItem(View view, CarBrand object) {
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
        return CarBrandAddFragment.class;
    }

    @Override
    protected Class getDetailFragment() {
        return CarBrandDetailFragment.class;
    }

    @Override
    protected Class getModifyFragment() {
        return CarBrandModifyFragment.class;
    }

    @Override
    protected int getListLayout() {
        return R.layout.carbrand_list_content;
    }

}
