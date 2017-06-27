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

import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.Client;
import eni_ecole.fr.lokacarsite.beans.User;
import eni_ecole.fr.lokacarsite.dao.ClientDao;

/**
 * Created by phili on 26/06/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {
    private final ArrayList<User> users;
    private final Context context;
    private final int ressourceId;
    private final Resources ressource;

    public UserAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull ArrayList<User> users) {
        super(context, resource, textViewResourceId, users);
        this.users = users;
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

        Client oneClient;

        ClientDao clientDao;

        TextView listClientLastname;
        TextView listClientFirstname;

        listClientLastname = (TextView) view.findViewById(R.id.list_client_lastname);
        listClientFirstname = (TextView) view.findViewById(R.id.list_client_firstname);

        clientDao = new ClientDao();

        oneClient = clientDao.getFromId(position);

        listClientLastname.setText(oneClient.lastname);
        listClientFirstname.setText(oneClient.firstname);


        return view;
    }
}
