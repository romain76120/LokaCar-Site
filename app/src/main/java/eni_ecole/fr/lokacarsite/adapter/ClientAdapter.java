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
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.CarBrand;
import eni_ecole.fr.lokacarsite.beans.CarModel;
import eni_ecole.fr.lokacarsite.beans.Client;
import eni_ecole.fr.lokacarsite.dao.CarBrandDao;
import eni_ecole.fr.lokacarsite.dao.CarDao;
import eni_ecole.fr.lokacarsite.dao.CarModelDao;
import eni_ecole.fr.lokacarsite.dao.ClientDao;

/**
 * Created by phili on 26/06/2017.
 */

public class ClientAdapter extends ArrayAdapter<Client> {
    private final ArrayList<Client> clients;
    private final Context context;
    private final int ressourceId;
    private final Resources ressource;

    public ClientAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Client> clients) {
        super(context, resource, clients);
        this.clients = clients;
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


        TextView mIdView = (TextView) view.findViewById(R.id.id);
        TextView mContentView = (TextView) view.findViewById(R.id.content);
        TextView mDetailView = (TextView) view.findViewById(R.id.details);

        oneClient = clients.get(position);

        mIdView.setText(oneClient.firstname);

        mContentView.setText(oneClient.lastname);
        mDetailView.setText(oneClient.mail);



        return view;
    }
}
