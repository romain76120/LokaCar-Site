package eni_ecole.fr.lokacarsite.dao;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.CarBrand;
import eni_ecole.fr.lokacarsite.beans.CarModel;

/**
 * Created by pbontempi2017 on 26/06/2017.
 */

public class CarModelDao {
    private static ArrayList<CarModel> carModels = null;

    public List<CarModel> getAll()
    {
        if (carModels == null)
        {
            carModels = new ArrayList<CarModel>();
            // TODO
            //cars.add(new Car());
        }
        return carModels;
    }

    public CarModel getFromId(Integer id)
    {
        return getAll().get(id);
    }

    public List<CarModel> getFromIdCarBrand(Integer idCarBrand)
    {
        List<CarModel> mCarModel = new ArrayList<CarModel>();
        for (CarModel car: getAll()) {
            if (car.idCarBrand == idCarBrand)
                mCarModel.add(car);
        }
        return mCarModel;
    }

    public void set(Integer id, CarModel carModel)
    {
        CarModel mCarModel = getAll().get(id);
        mCarModel.name = carModel.name;
        mCarModel.idCarBrand = carModel.idCarBrand;
    }

    public CarModel add(CarModel carModel){
        carModel.id = getAll().size();
        getAll().add(carModel);
        return carModel;
    }

    public void delete(Integer id){
        getAll().remove(id);
        for (int i = 0; i < getAll().size(); i++)
        {
            getAll().get(i).id = i;
        }
    }
}
