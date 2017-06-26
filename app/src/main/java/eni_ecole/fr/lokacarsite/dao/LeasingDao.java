package eni_ecole.fr.lokacarsite.dao;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Client;
import eni_ecole.fr.lokacarsite.beans.Leasing;

/**
 * Created by rroger2016 on 26/06/2017.
 */

public class LeasingDao {
    private static ArrayList<Leasing> leasings = null;

    public List<Leasing> getAll()
    {
        if (leasings == null)
        {
            leasings = new ArrayList<Leasing>();
            // TODO
            //cars.add(new Car());
        }
        return leasings;
    }

    public Leasing getFromId(Integer id)
    {
        return getAll().get(id);
    }

    public void set(Integer id, Leasing leasing)
    {
        Leasing mLeasing = getAll().get(id);
        mLeasing.idCar = leasing.idCar;
        // TODO
    }

    public Leasing add(Leasing leasing){
        leasing.id = getAll().size();
        getAll().add(leasing);
        return leasing;
    }

    public void delete(Integer id){
        getAll().remove(id);
        for (int i = 0; i < getAll().size(); i++)
        {
            getAll().get(i).id = i;
        }
    }
}
