package eni_ecole.fr.lokacarsite.dao;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Agency;

/**
 * Created by rroger2016 on 26/06/2017.
 */

public class AgencyDao {

    private static ArrayList<Agency> agencies = null;

    public List<Agency> getAll()
    {
        if (agencies == null)
        {
            agencies = new ArrayList<Agency>();
            agencies.add(new Agency(1, "Nantes", "2 rue d'Orléans", "http://monagencedenantes.com","0241554788"));
        }
        return agencies;
    }

    public Agency getFromId(Integer id)
    {
        return getAll().get(id);
    }

    public void set(Integer id, Agency agency)
    {
        Agency mAgence = getAll().get(id);
        mAgence.name = agency.name;
        mAgence.address = agency.address;
        mAgence.phone = agency.phone;
        mAgence.url = agency.url;
    }

    public Agency add(Agency agency){
        agency.id = getAll().size();
        getAll().add(agency);
        return agency;
    }

    public void delete(Integer id){
        agencies.remove(id);
        for (int i = 0; i < agencies.size(); i++)
        {
            agencies.get(i).id = i;
        }
        agencies.remove(id);
    }


}
