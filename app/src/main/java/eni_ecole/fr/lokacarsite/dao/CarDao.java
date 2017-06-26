package eni_ecole.fr.lokacarsite.dao;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Agency;
import eni_ecole.fr.lokacarsite.beans.Car;

/**
 * Created by rroger2016 on 26/06/2017.
 */

public class CarDao {

    private static ArrayList<Car> cars = null;

    public List<Car> getAll()
    {
        if (cars == null)
        {
            cars = new ArrayList<Car>();
            // TODO
            //cars.add(new Car());
        }
        return cars;
    }

    public Car getFromId(Integer id)
    {
        return getAll().get(id);
    }

    public void set(Integer id, Car car)
    {
        Car mCar = getAll().get(id);
        mCar.name = car.name;
        // TODO
    }

    public Car add(Car car){
        car.id = getAll().size();
        getAll().add(car);
        return car;
    }

    public void delete(Integer id){
        getAll().remove(id);
        for (int i = 0; i < getAll().size(); i++)
        {
            getAll().get(i).id = i;
        }
    }
}
