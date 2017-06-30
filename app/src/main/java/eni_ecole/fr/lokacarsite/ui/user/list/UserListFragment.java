package eni_ecole.fr.lokacarsite.ui.user.list;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.Client;
import eni_ecole.fr.lokacarsite.beans.User;
import eni_ecole.fr.lokacarsite.dao.ClientDao;
import eni_ecole.fr.lokacarsite.dao.UserDao;
import eni_ecole.fr.lokacarsite.ui.generic.list.GenericListFragment;
import eni_ecole.fr.lokacarsite.ui.user.details.UserAddFragment;
import eni_ecole.fr.lokacarsite.ui.user.details.UserDetailFragment;
import eni_ecole.fr.lokacarsite.ui.user.details.UserModifyFragment;

/**
 * Created by pbontempi2017 on 29/06/2017.
 */

public class UserListFragment extends GenericListFragment<User> {


    private int lastFilterSelected ;

    @Override
    public int getIdObject(User object) {
        return object.id;
    }

    @Override
    public UserDao getDao(Context context) {
        return new UserDao(context);
    }

    @Override
    public void constructListItem(View view, User object) {
        TextView mIdView = (TextView) view.findViewById(R.id.id);
        TextView mContentView = (TextView) view.findViewById(R.id.content);
        TextView mDetailView = (TextView) view.findViewById(R.id.details);
        TextView mStatusView = (TextView) view.findViewById(R.id.contentStatus);

        mIdView.setText(object.firstname);

        mContentView.setText(object.lastname);
        mDetailView.setText(object.mail);



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
        return UserAddFragment.class;
    }

    @Override
    protected Class getDetailFragment() {
        return UserDetailFragment.class;
    }

    @Override
    protected Class getModifyFragment() {
        return UserModifyFragment.class;
    }

}
