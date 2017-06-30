package eni_ecole.fr.lokacarsite.ui.leasing.list;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.CarModel;
import eni_ecole.fr.lokacarsite.beans.Leasing;
import eni_ecole.fr.lokacarsite.dao.CarModelDao;
import eni_ecole.fr.lokacarsite.dao.LeasingDao;
import eni_ecole.fr.lokacarsite.ui.generic.list.GenericListFragment;
import eni_ecole.fr.lokacarsite.ui.leasing.details.LeasingAddFragment;
import eni_ecole.fr.lokacarsite.ui.leasing.details.LeasingDetailFragment;
import eni_ecole.fr.lokacarsite.ui.leasing.details.LeasingModifyFragment;

/**
 * Created by pbontempi2017 on 29/06/2017.
 */

public class LeasingListFragment extends GenericListFragment<Leasing> {


    private int lastFilterSelected ;

    @Override
    public int getIdObject(Leasing object) {
        return object.id;
    }

    @Override
    public LeasingDao getDao(Context context) {
        return new LeasingDao(context);
    }

    @Override
    public void constructListItem(View view, Leasing object) {
        TextView mIdView = (TextView) view.findViewById(R.id.id);
        TextView mContentView = (TextView) view.findViewById(R.id.content);
        TextView mDetailView = (TextView) view.findViewById(R.id.details);
        TextView mStatusView = (TextView) view.findViewById(R.id.contentStatus);

        mIdView.setText(object.startDate);

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
        return LeasingAddFragment.class;
    }

    @Override
    protected Class getDetailFragment() {
        return LeasingDetailFragment.class;
    }

    @Override
    protected Class getModifyFragment() {
        return LeasingModifyFragment.class;
    }

    @Override
    protected int getListLayout() {
        return R.layout.leasing_list_content;
    }

}
