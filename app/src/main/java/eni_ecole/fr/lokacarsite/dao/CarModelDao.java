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
//            carBrands.add(new CarBrand(0,"Peugeot"));
//            carBrands.add(new CarBrand(0,"Renault"));
//            carBrands.add(new CarBrand(0,"Citroën"));
//            carBrands.add(new CarBrand(0,"Audi"));
            add(new CarModel(0,"106"));
            add(new CarModel(0,"206"));
            add(new CarModel(0,"207"));
            add(new CarModel(0,"208"));
            add(new CarModel(0,"307"));
            add(new CarModel(0,"308"));
            add(new CarModel(0,"407"));
            add(new CarModel(0,"408"));
            add(new CarModel(0,"5008"));
            add(new CarModel(1,"Clio"));
            add(new CarModel(1,"Mégane"));
            add(new CarModel(1,"Scénic"));
            add(new CarModel(2,"C1"));
            add(new CarModel(2,"C2"));
            add(new CarModel(2,"C3"));
            add(new CarModel(2,"C4"));
            add(new CarModel(2,"C5"));
            add(new CarModel(3,"A1"));
            add(new CarModel(3,"A2"));
            add(new CarModel(3,"A3"));
            add(new CarModel(3,"A4"));
            add(new CarModel(3,"A5"));
            add(new CarModel(3,"A6"));
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
