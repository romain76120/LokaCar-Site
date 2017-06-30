package eni_ecole.fr.lokacarsite.ui.category.list;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.CarModel;
import eni_ecole.fr.lokacarsite.beans.Category;
import eni_ecole.fr.lokacarsite.dao.CarModelDao;
import eni_ecole.fr.lokacarsite.dao.CategoryDao;
import eni_ecole.fr.lokacarsite.ui.category.details.CategoryAddFragment;
import eni_ecole.fr.lokacarsite.ui.category.details.CategoryDetailFragment;
import eni_ecole.fr.lokacarsite.ui.category.details.CategoryModifyFragment;
import eni_ecole.fr.lokacarsite.ui.generic.list.GenericListFragment;

/**
 * Created by pbontempi2017 on 29/06/2017.
 */

public class CategoryListFragment extends GenericListFragment<Category> {


    private int lastFilterSelected ;

    @Override
    public int getIdObject(Category object) {
        return object.id;
    }

    @Override
    public CategoryDao getDao(Context context) {
        return new CategoryDao(context);
    }

    @Override
    public void constructListItem(View view, Category object) {
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
        return CategoryAddFragment.class;
    }

    @Override
    protected Class getDetailFragment() {
        return CategoryDetailFragment.class;
    }

    @Override
    protected Class getModifyFragment() {
        return CategoryModifyFragment.class;
    }

    @Override
    protected int getListLayout() {
        return R.layout.category_list_content;
    }

}
