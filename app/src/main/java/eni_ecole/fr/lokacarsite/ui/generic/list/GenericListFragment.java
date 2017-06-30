package eni_ecole.fr.lokacarsite.ui.generic.list;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import de.greenrobot.event.EventBus;
import eni_ecole.fr.lokacarsite.R;
import eni_ecole.fr.lokacarsite.constant.Constant;
import eni_ecole.fr.lokacarsite.dao.ObjectDao;
import eni_ecole.fr.lokacarsite.tools.FragmentManager;
import eni_ecole.fr.lokacarsite.tools.QueryEvent;
import eni_ecole.fr.lokacarsite.ui.generic.details.GenericAddActivity;
import eni_ecole.fr.lokacarsite.ui.generic.details.GenericDetailActivity;
import eni_ecole.fr.lokacarsite.ui.generic.details.GenericModifyActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class GenericListFragment<T> extends Fragment {

    private static final int REQUEST_CODE = 4542;
    private View recyclerView;
    private FrameLayout frameLayoutContainer;
    private boolean mTwoPane;
    private View lastViewSelected = null;
    private ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private int currentFragment;

    // Pour gérer le réaffichage de la liste lorsqu'on supprime l'élément
    public T lastItemSelected = null;
    private int lastFilterSelected;
    private String query;
    private SwipeRefreshLayout swipeContainer;

    public GenericListFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        lastFilterSelected = R.id.action_all;

    }

    public abstract int getIdObject(T object);
    public abstract ObjectDao<T> getDao(Context context);
    public abstract void constructListItem(View view, T object) ;
    protected abstract void onOptionalOptionItemSelected(MenuItem item);
    protected abstract int[] getOptionalMenuItem();
    protected abstract Class<?> getAddFragment();
    protected abstract Class<?> getDetailFragment();
    protected abstract Class<?> getModifyFragment();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.generic_list, container, false);
        EventBus.getDefault().register(this);
        setHasOptionsMenu(true);
        lastFilterSelected = R.id.action_all;
        recyclerView = view.findViewById(R.id.car_list);
        frameLayoutContainer = (FrameLayout) view.findViewById(R.id.car_detail_container);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadList();

            }
        });
        swipeContainer.setColorSchemeResources(R.color.colorPrimaryDark);
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
        reloadList();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(QueryEvent event) {
        currentFragment = event.getAction();

        switch (event.getAction()) {

            case Constant.ADD:
                // Averti le fragmentManager qui conserve le nom du fragment affiché pour le basculement en paysage / portrait
                FragmentManager.setFragment(getAddFragment());
                if (mTwoPane) {
                    getActivity().invalidateOptionsMenu();
                    if(lastViewSelected != null)
                        lastViewSelected.setSelected(false) ;
                    Fragment fragment = null;
                    try {
                        fragment = FragmentManager.getFragment();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.car_detail_container, fragment)
                                .commit();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (java.lang.InstantiationException e) {
                        e.printStackTrace();
                    }

                } else {
                    Intent intent = new Intent(getActivity(), GenericAddActivity.class);
                    startActivity(intent);
                }
                break;

            case Constant.DETAIL:
                // Averti le fragmentManager qui conserve le nom du fragment affiché pour le basculement en paysage / portrait
                FragmentManager.setFragment(getDetailFragment());
                if (mTwoPane) {
                    getActivity().invalidateOptionsMenu();
                    int elementId = event.getElementId();
                    // si l'élément n'a pas été donné, alors on sélectionne le dernier connu
                    if (elementId == -1 && lastItemSelected != null) {
                        elementId = getIdObject(lastItemSelected);
                        lastViewSelected.setSelected(true);
                    }
                    Bundle arguments = new Bundle();
                    arguments.putInt(Constant.ID, event.getElementId());

                    Fragment fragment = null;
                    try {
                        fragment = FragmentManager.getFragment();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.car_detail_container, fragment)
                                .commit();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (java.lang.InstantiationException e) {
                        e.printStackTrace();
                    }

                } else {
                    Intent intent = new Intent(getActivity(), GenericDetailActivity.class);
                    intent.putExtra(Constant.ID, event.getElementId());
                    startActivity(intent);
                }
                break;

            case Constant.MODIFY:
                // Averti le fragmentManager qui conserve le nom du fragment affiché pour le basculement en paysage / portrait
                FragmentManager.setFragment(getModifyFragment());
                if (mTwoPane) {
                    getActivity().invalidateOptionsMenu();
                    Bundle arguments = new Bundle();
                    arguments.putInt(Constant.ID, event.getElementId());

                    Fragment fragment = null;
                    try {
                        fragment = FragmentManager.getFragment();
                        fragment.setArguments(arguments);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.car_detail_container, fragment)
                                .commit();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (java.lang.InstantiationException e) {
                        e.printStackTrace();
                    }

                } else {

                    Intent intent = new Intent(getActivity(), GenericModifyActivity.class);
                    intent.putExtra(Constant.ID, event.getElementId());
                    startActivity(intent);
                }
                break;

            case Constant.DELETE:
                // mise en cache des dernières informations
                ObjectDao<T> dao = getDao(GenericListFragment.this.getContext());
                T mItem = dao.get(event.getElementId());
                dao.delete(event.getElementId());
                Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.action_delete_result, Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo_delete, new UndoDeleteListener(mItem)).show();
                reloadList();
                break;
        }


    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        for (int menuRes :getOptionalMenuItem()) {
            inflater.inflate(menuRes, menu);
        }
        
        inflater.inflate(R.menu.list, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                GenericListFragment.this.query = query;
                itemRecyclerViewAdapter.filter(query.toLowerCase(Locale.getDefault()));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                GenericListFragment.this.query = query;
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
                case Constant.ADD:
                    menuSave.setVisible(true);
                    menuCancel.setVisible(true);
                    menuModify.setVisible(false);
                    menuDelete.setVisible(false);
                    break;
                case Constant.MODIFY:
                    menuSave.setVisible(true);
                    menuCancel.setVisible(true);
                    menuModify.setVisible(false);
                    menuDelete.setVisible(false);
                    break;
                case Constant.DETAIL:
                    menuSave.setVisible(false);
                    menuCancel.setVisible(false);
                    menuModify.setVisible(true);
                    menuDelete.setVisible(true);
                    break;
                default:
                    menuSave.setVisible(false);
                    menuCancel.setVisible(false);
                    menuModify.setVisible(false);
                    menuDelete.setVisible(false);
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
//            case R.id.action_all:
//                lastFilterSelected = id;
//                setupRecyclerView((RecyclerView) recyclerView);
//                break;
//            case R.id.action_rented:
//                lastFilterSelected = id;
//                setupRecyclerView((RecyclerView) recyclerView);
//                break;
//            case R.id.action_no_rented:
//                lastFilterSelected = id;
//                setupRecyclerView((RecyclerView) recyclerView);
//                break;
            case R.id.action_menu_delete:
                EventBus.getDefault().post(new QueryEvent(Constant.DELETE, getIdObject(lastItemSelected), lastItemSelected));
                break;
            case R.id.action_menu_modify:
                EventBus.getDefault().post(new QueryEvent(Constant.MODIFY, getIdObject(lastItemSelected), lastItemSelected));
                break;
            case R.id.action_menu_save:
                EventBus.getDefault().post(new QueryEvent(Constant.ON_SAVE));

                break;
            case R.id.action_menu_cancel:
                EventBus.getDefault().post(new QueryEvent(Constant.DETAIL));
                break;
            case R.id.action_search:

                break;
            default:
                onOptionalOptionItemSelected(item);
                break;


        }


        return super.onOptionsItemSelected(item);
    }

    protected ArrayList<T> constructList()
    {
        return getDao(getContext()).get();
    }

    protected void reloadList()
    {
        swipeContainer.setRefreshing(true);
        reloadList((RecyclerView) recyclerView);
        swipeContainer.setRefreshing(false);
    }

    protected void reloadList(@NonNull RecyclerView recyclerView) {

//        switch (lastFilterSelected) {
//            case R.id.action_all:
//                itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(getDao(getContext()).get());
//                itemRecyclerViewAdapter.filter(query);
//                break;
//            case R.id.action_rented:
//                itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(new CarDao().getRented(true));
//                itemRecyclerViewAdapter.filter(query);
//                break;
//            case R.id.action_no_rented:
//                itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(new CarDao().getRented(false));
//                itemRecyclerViewAdapter.filter(query);
//                break;
//            default:
//                itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(new CarDao().getAll());
//                itemRecyclerViewAdapter.filter(query);
//                break;
//        }
        itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(constructList());
        itemRecyclerViewAdapter.filter(query);


        recyclerView.setAdapter(itemRecyclerViewAdapter);

    }


    private class UndoDeleteListener implements View.OnClickListener {
        T mItem;

        public UndoDeleteListener(T mItem) {
            this.mItem = mItem;
        }

        @Override
        public void onClick(View v) {
            getDao(GenericListFragment.this.getContext()).add(mItem);
            reloadList();
        }
    }

    public class ItemRecyclerViewAdapter
            extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

        private final List<T> mValues;
        private final Map<String, T> valueCache;

        public ItemRecyclerViewAdapter(List<T> items) {
            mValues = items;
            valueCache = new TreeMap<String, T>();
            for (T oneItem : mValues) {
                valueCache.put(oneItem.toString(), oneItem);
            }
        }

        @Override
        public ItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.car_list_content, parent, false);
//            view.setBackground(paint);
            return new ItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ItemRecyclerViewAdapter.ViewHolder holder, int position) {

            holder.mItem = mValues.get(position);
            constructListItem(holder.mView, holder.mItem);
            holder.mView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    if (lastViewSelected != null)
                        lastViewSelected.setSelected(false);
                    holder.mView.setSelected(true);
                    lastViewSelected = holder.mView;
                    lastItemSelected = holder.mItem;
                    EventBus.getDefault().post(new QueryEvent(Constant.DETAIL, getIdObject(holder.mItem), holder.mItem));

                }
            });
        }

        // Filter
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

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public T mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
            }

            @Override
            public String toString() {
                return super.toString() + " '" + getIdObject(mItem) + "'";
            }
        }




    }


    private class Insert extends AsyncTask<T, Integer, String> {

        @Override
        protected String doInBackground(T... params) {

            getDao(getContext()).add(params[0]);

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            swipeContainer.setRefreshing(false);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeContainer.setRefreshing(true);
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
