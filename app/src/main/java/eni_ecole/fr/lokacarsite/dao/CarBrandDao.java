package eni_ecole.fr.lokacarsite.dao;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.CarBrand;

/**
 * Created by pbontempi2017 on 26/06/2017.
 */

public class CarBrandDao {
    private static ArrayList<CarBrand> carBrands = null;

    public List<CarBrand> getAll()
    {
        if (carBrands == null)
        {
            carBrands = new ArrayList<CarBrand>();
            carBrands.add(new CarBrand(0,"Peugeot"));
            carBrands.add(new CarBrand(0,"Renault"));
            carBrands.add(new CarBrand(0,"Citroën"));
            carBrands.add(new CarBrand(0,"Audi"));
        }
        return carBrands;
    }

    public CarBrand getFromId(Integer id)
    {
        return getAll().get(id);
    }

    public void set(Integer id, CarBrand carBrand)
    {
        CarBrand mCarBrand = getAll().get(id);
        mCarBrand.name = carBrand.name;
    }

    public CarBrand add(CarBrand carBrand){
        carBrand.id = getAll().size();
        getAll().add(carBrand);
        return carBrand;
    }

    public void delete(Integer id){
        getAll().remove(id);
        for (int i = 0; i < getAll().size(); i++)
        {
            getAll().get(i).id = i;
        }
    }
}
