package eni_ecole.fr.lokacarsite.ui.client.list;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.Client;
import eni_ecole.fr.lokacarsite.dao.CarDao;
import eni_ecole.fr.lokacarsite.dao.ClientDao;
import eni_ecole.fr.lokacarsite.ui.client.details.ClientAddFragment;
import eni_ecole.fr.lokacarsite.ui.client.details.ClientDetailFragment;
import eni_ecole.fr.lokacarsite.ui.client.details.ClientModifyFragment;
import eni_ecole.fr.lokacarsite.ui.generic.list.GenericListFragment;

/**
 * Created by pbontempi2017 on 29/06/2017.
 */

public class ClientListFragment extends GenericListFragment<Client> {


    private int lastFilterSelected ;

    @Override
    public int getIdObject(Client object) {
        return object.id;
    }

    @Override
    public ClientDao getDao(Context context) {
        return new ClientDao(context);
    }

    @Override
    public void constructListItem(View view, Client object) {
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
        return ClientAddFragment.class;
    }

    @Override
    protected Class getDetailFragment() {
        return ClientDetailFragment.class;
    }

    @Override
    protected Class getModifyFragment() {
        return ClientModifyFragment.class;
    }

}
