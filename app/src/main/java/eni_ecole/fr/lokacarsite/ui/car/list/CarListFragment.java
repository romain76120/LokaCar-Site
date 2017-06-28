package eni_ecole.fr.lokacarsite.ui.car.list;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import de.greenrobot.event.EventBus;
import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.CarBrand;
import eni_ecole.fr.lokacarsite.beans.CarModel;
import eni_ecole.fr.lokacarsite.beans.Category;
import eni_ecole.fr.lokacarsite.constant.Constant;
import eni_ecole.fr.lokacarsite.dao.CarBrandDao;
import eni_ecole.fr.lokacarsite.dao.CarDao;
import eni_ecole.fr.lokacarsite.dao.CarModelDao;
import eni_ecole.fr.lokacarsite.dao.CategoryDao;
import eni_ecole.fr.lokacarsite.tools.QueryEvent;
import eni_ecole.fr.lokacarsite.ui.car.details.CarAddActivity;
import eni_ecole.fr.lokacarsite.ui.car.details.CarAddFragment;
import eni_ecole.fr.lokacarsite.ui.car.details.CarDetailActivity;
import eni_ecole.fr.lokacarsite.ui.car.details.CarDetailFragment;
import eni_ecole.fr.lokacarsite.ui.car.details.CarModifyActivity;
import eni_ecole.fr.lokacarsite.ui.car.details.CarModifyFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarListFragment extends Fragment {

    private static final int REQUEST_CODE = 4542;
    private View recyclerView;
    private FrameLayout frameLayoutContainer;
    private boolean mTwoPane;
    private View lastViewSelected = null;
    private CarItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private int currentFragment;

    // Pour gérer le réaffichage de la liste lorsqu'on supprime l'élément
    public Car lastItemSelected = null;
    private int lastFilterSelected;
    private String query;

    public CarListFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        lastFilterSelected = R.id.action_all;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.car_list, container, false);
        EventBus.getDefault().register(this);
        setHasOptionsMenu(true);
        lastFilterSelected = R.id.action_all;
        recyclerView = view.findViewById(R.id.car_list);
        frameLayoutContainer = (FrameLayout) view.findViewById(R.id.car_detail_container);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // Si on est en paysage et qu'on a la liste + le détail
        if (frameLayoutContainer != null) {
            mTwoPane = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(QueryEvent event) {
        currentFragment = event.getAction();
        switch (event.getAction()) {
            case Constant.ADD_CAR:

                if (mTwoPane) {
                    getActivity().invalidateOptionsMenu();
                    lastViewSelected.setSelected(false);
                    CarAddFragment fragment = new CarAddFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.car_detail_container, fragment)
                            .commit();
                } else {
                    Intent intent = new Intent(getActivity(), CarAddActivity.class);
                    startActivity(intent);
                }
                break;
            case Constant.DETAIL_CAR:
                if (mTwoPane) {
                    getActivity().invalidateOptionsMenu();
                    int elementId = event.getElementId();
                    // si l'élément n'a pas été donné, alors on sélectionne le dernier connu
                    if (elementId == -1) {
                        elementId = lastItemSelected.id;
                        lastViewSelected.setSelected(true);
                    }
                    Bundle arguments = new Bundle();
                    arguments.putInt(Constant.ID_CAR, event.getElementId());

                    CarDetailFragment fragment = new CarDetailFragment();
                    fragment.setArguments(arguments);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.car_detail_container, fragment)
                            .commit();
                } else {
                    Intent intent = new Intent(getActivity(), CarDetailActivity.class);
                    intent.putExtra(Constant.ID_CAR, event.getElementId());
                    startActivity(intent);
                }
                break;
            case Constant.MODIFY_CAR:
                if (mTwoPane) {
                    getActivity().invalidateOptionsMenu();
                    Bundle arguments = new Bundle();
                    arguments.putInt(Constant.ID_CAR, event.getElementId());

                    CarModifyFragment fragment = new CarModifyFragment();
                    fragment.setArguments(arguments);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.car_detail_container, fragment)
                            .commit();

                } else {
                    Intent intent = new Intent(getActivity(), CarModifyActivity.class);
                    intent.putExtra(Constant.ID_CAR, event.getElementId());
                    startActivity(intent);
                }
                break;
            case Constant.DELETE_CAR:
                // mise en cache des dernières informations
                CarDao carDao = new CarDao();
                Car mItem = carDao.getFromId(event.getElementId());
                carDao.delete(event.getElementId());
                Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.action_delete_result, Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo_delete, new UndoDeleteCarListener(mItem)).show();
                setupRecyclerView((RecyclerView) recyclerView);
                break;
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.car_list, menu);
        inflater.inflate(R.menu.list, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CarListFragment.this.query = query;
                itemRecyclerViewAdapter.filter(query.toLowerCase(Locale.getDefault()));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                CarListFragment.this.query = query;
                itemRecyclerViewAdapter.filter(query.toLowerCase(Locale.getDefault()));
                return false;
            }
        });
        // Si on est en paysage et admin, on ajoute les boutons de modification et de suppression
        if (mTwoPane && Constant.user.admin) {
            inflater.inflate(R.menu.admin_item, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        super.onPrepareOptionsMenu(menu);
        if (mTwoPane && Constant.user.admin) {


            MenuItem menuSave = menu.findItem(R.id.action_menu_save);
            MenuItem menuCancel = menu.findItem(R.id.action_menu_cancel);
            MenuItem menuModify = menu.findItem(R.id.action_menu_modify);
            MenuItem menuDelete = menu.findItem(R.id.action_menu_delete);
            switch (currentFragment) {
                case Constant.ADD_CAR:
                    menuSave.setVisible(true);
                    menuCancel.setVisible(true);
                    menuModify.setVisible(false);
                    menuDelete.setVisible(false);
                    break;
                case Constant.MODIFY_CAR:
                    menuSave.setVisible(true);
                    menuCancel.setVisible(true);
                    menuModify.setVisible(false);
                    menuDelete.setVisible(false);
                    break;
                case Constant.DETAIL_CAR:
                    menuSave.setVisible(false);
                    menuCancel.setVisible(false);
                    menuModify.setVisible(true);
                    menuDelete.setVisible(true);
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_all:
                lastFilterSelected = id;
                setupRecyclerView((RecyclerView) recyclerView);
                break;
            case R.id.action_rented:
                lastFilterSelected = id;
                setupRecyclerView((RecyclerView) recyclerView);
                break;
            case R.id.action_no_rented:
                lastFilterSelected = id;
                setupRecyclerView((RecyclerView) recyclerView);
                break;
            case R.id.action_menu_delete:
                EventBus.getDefault().post(new QueryEvent(Constant.DELETE_CAR, lastItemSelected.id));
                break;
            case R.id.action_menu_modify:
                EventBus.getDefault().post(new QueryEvent(Constant.MODIFY_CAR, lastItemSelected.id));
                break;
            case R.id.action_menu_save:
                EventBus.getDefault().post(new QueryEvent(Constant.ON_SAVE_CAR));

                break;
            case R.id.action_menu_cancel:
                EventBus.getDefault().post(new QueryEvent(Constant.DETAIL_CAR));
                break;
            case R.id.action_search:

                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        switch (lastFilterSelected) {
            case R.id.action_all:
                itemRecyclerViewAdapter = new CarItemRecyclerViewAdapter(new CarDao().getAll());
                itemRecyclerViewAdapter.filter(query);
                break;
            case R.id.action_rented:
                itemRecyclerViewAdapter = new CarItemRecyclerViewAdapter(new CarDao().getRented(true));
                itemRecyclerViewAdapter.filter(query);
                break;
            case R.id.action_no_rented:
                itemRecyclerViewAdapter = new CarItemRecyclerViewAdapter(new CarDao().getRented(false));
                itemRecyclerViewAdapter.filter(query);
                break;
            default:
                itemRecyclerViewAdapter = new CarItemRecyclerViewAdapter(new CarDao().getAll());
                itemRecyclerViewAdapter.filter(query);
                break;
        }
        recyclerView.setAdapter(itemRecyclerViewAdapter);

    }


    private class UndoDeleteCarListener implements View.OnClickListener {
        Car mItem;

        public UndoDeleteCarListener(Car mItem) {
            this.mItem = mItem;
        }

        @Override
        public void onClick(View v) {
            mItem.id = -1;
            new CarDao().add(mItem);
            setupRecyclerView((RecyclerView) recyclerView);
        }
    }

    public class CarItemRecyclerViewAdapter
            extends RecyclerView.Adapter<CarItemRecyclerViewAdapter.ViewHolder> {

        private final List<Car> mValues;
        private final Map<String, Car> valueCache;

        public CarItemRecyclerViewAdapter(List<Car> items) {
            mValues = items;
            valueCache = new TreeMap<String, Car>();
            for (Car oneCar : mValues) {
                CarBrandDao carBrandDao = new CarBrandDao();
                CarModelDao carModelDao = new CarModelDao();
                CarModel carModel = new CarModelDao().getFromId(oneCar.idCarModel);
                CarBrand carBrand = new CarBrandDao().getFromId(carModel.idCarBrand);
                Category category = new CategoryDao().getFromId(oneCar.idCategory);
                String searchString = oneCar.fuel
                        + oneCar.registration
                        + oneCar.price
                        + carModel.name
                        + carBrand.name
                        + category.name;
                valueCache.put(searchString, oneCar);
            }
        }

        @Override
        public CarItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.car_list_content, parent, false);
//            view.setBackground(paint);
            return new CarItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final CarItemRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);

            CarModel oneCarModel = new CarModelDao().getFromId(holder.mItem.idCarModel);
            CarBrand oneCarBrand = new CarBrandDao().getFromId(oneCarModel.idCarBrand);
            Category oneCategory = new CategoryDao().getFromId(holder.mItem.idCategory);

            holder.mIdView.setText(oneCategory.name);

            holder.mContentView.setText(oneCarBrand.name + " " + oneCarModel.name);
            holder.mDetailView.setText(mValues.get(position).fuel);

            if (mValues.get(position).isLeasing) {
                holder.mStatusView.setText(R.string.string_list_car_status);
            }

            holder.mView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    if (lastViewSelected != null)
                        lastViewSelected.setSelected(false);
                    holder.mView.setSelected(true);
                    lastViewSelected = holder.mView;
                    lastItemSelected = holder.mItem;
                    EventBus.getDefault().post(new QueryEvent(Constant.DETAIL_CAR, lastItemSelected.id));

                }
            });
        }


        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public final TextView mDetailView;
            public final TextView mStatusView;
            public Car mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
                mDetailView = (TextView) view.findViewById(R.id.details);
                mStatusView = (TextView) view.findViewById(R.id.contentStatus);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }

        // Filter Class
        public void filter(String charText) {
            if (charText != null) {
                charText = charText.toLowerCase(Locale.getDefault());
                mValues.clear();
                if (charText.length() == 0) {
                    // si l'utilisateur a supprimé sa recherche alors on recharge la liste entière
                    mValues.addAll(valueCache.values());
                } else {

                    for (String searchString : valueCache.keySet()) {
                        if (searchString.toLowerCase(Locale.getDefault()).contains(charText)) {
                            mValues.add(valueCache.get(searchString));
                        }
                    }

                }
                notifyDataSetChanged();
            }

        }


    }

    private class InsertOrUpdate extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {

            if (articleId == -1) {


                articleDAL.addArticle(nouvelArticle);
            }
            else
            {
                articleDAL.setArticle(articleId, nouvelArticle);
            }
            return getString(R.string.return_dao);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            enableAll(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            disableAll();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//            ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
//            progress.setProgress(values[0]);
//            progress.setMax(100);
        }
    }


}
