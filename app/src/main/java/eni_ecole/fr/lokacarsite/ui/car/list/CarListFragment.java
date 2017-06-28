package eni_ecole.fr.lokacarsite.ui.car.list;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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
import eni_ecole.fr.lokacarsite.ui.car.details.CarDetailActivity;
import eni_ecole.fr.lokacarsite.ui.car.details.CarDetailFragment;
import eni_ecole.fr.lokacarsite.ui.car.modify.CarModifyActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarListFragment extends Fragment {

    private static final int REQUEST_CODE = 4542;
    private View recyclerView;
    private FrameLayout frameLayoutContainer;
    private boolean mTwoPane;
    public View lastViewSelected = null;

    // Pour gérer le réaffichage de la liste lorsqu'on supprime l'élément
    public Car lastItemSelected = null;
    private int lastFilterSelected;

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.car_list, menu);
        if (mTwoPane)
            inflater.inflate(R.menu.item, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if( resultCode == CarDetailActivity.ON_DELETE)
            {
                executeDeleteOrder(data.getIntExtra(Constant.ID_CAR, -1));
            }
        }
    }

    private void executeDeleteOrder(int id)
    {
        // mise en cache des dernières informations
        CarDao carDao = new CarDao();
        Car mItem = carDao.getFromId(id);
        carDao.delete(id);
        Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.action_delete_result, Snackbar.LENGTH_LONG)
                .setAction(R.string.undo_delete, new UndoDeleteCarListener(mItem)).show();
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
                break;
            case R.id.action_rented:
                lastFilterSelected = id;
                break;
            case R.id.action_no_rented:
                lastFilterSelected = id;
                break;
            case R.id.action_delete:
                executeDeleteOrder(lastItemSelected.id);
                break;
            case R.id.action_modify:
                Intent intent = new Intent(getActivity(), CarModifyActivity.class);
                intent.putExtra(Constant.ID_CAR, lastItemSelected.id);
                startActivity(intent);
                break;
        }
        setupRecyclerView((RecyclerView) recyclerView);

        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        switch (lastFilterSelected) {
            case R.id.action_all:
                recyclerView.setAdapter(new CarItemRecyclerViewAdapter(new CarDao().getAll()));
                break;
            case R.id.action_rented:
                recyclerView.setAdapter(new CarItemRecyclerViewAdapter(new CarDao().getRented(true)));
                break;
            case R.id.action_no_rented:
                recyclerView.setAdapter(new CarItemRecyclerViewAdapter(new CarDao().getRented(false)));
                break;
            default:
                recyclerView.setAdapter(new CarItemRecyclerViewAdapter(new CarDao().getAll()));
                break;
        }

    }

    public class CarItemRecyclerViewAdapter
            extends RecyclerView.Adapter<CarItemRecyclerViewAdapter.ViewHolder> {

        private final List<Car> mValues;

        public CarItemRecyclerViewAdapter(List<Car> items) {
            mValues = items;
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
                    if (mTwoPane) {
                        // Déselection de l'ancien bouton
                        if (lastViewSelected != null)
                            lastViewSelected.setSelected(false);
                        holder.mView.setSelected(true);
                        lastViewSelected = holder.mView;
                        lastItemSelected = holder.mItem;
                        Bundle arguments = new Bundle();
                        arguments.putInt(CarDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        CarDetailFragment fragment = new CarDetailFragment();
                        fragment.setArguments(arguments);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.car_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(getActivity(), CarDetailActivity.class);
                        intent.putExtra(CarDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        startActivityForResult(intent, REQUEST_CODE);
                    }
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
    }

    public class UndoDeleteCarListener implements View.OnClickListener {
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
}
