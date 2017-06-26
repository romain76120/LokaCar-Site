package eni_ecole.fr.lokacarsite.dao;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Agency;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.CarModel;

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
//            add(new CarModel(0,"106")); 0
//            add(new CarModel(0,"206")); 1
//            add(new CarModel(0,"207")); 2
//            add(new CarModel(0,"208")); 3
//            add(new CarModel(0,"307")); 4
//            add(new CarModel(0,"308")); 5
//            add(new CarModel(0,"407")); 6
//            add(new CarModel(0,"408")); 7
//            add(new CarModel(0,"5008")); 8
//            add(new CarModel(1,"Clio")); 9
//            add(new CarModel(1,"Mégane")); 10
//            add(new CarModel(1,"Scénic")); 11
//            add(new CarModel(2,"C1")); 12
//            add(new CarModel(2,"C2")); 13
//            add(new CarModel(2,"C3")); 14
//            add(new CarModel(2,"C4")); 15
//            add(new CarModel(2,"C5")); 16
//            add(new CarModel(3,"A1")); 17
//            add(new CarModel(3,"A2")); 18
//            add(new CarModel(3,"A3")); 19
//            add(new CarModel(3,"A4")); 20
//            add(new CarModel(3,"A5")); 21
//            add(new CarModel(3,"A6")); 22
            add(new Car(1,"4546AT44", "Diesel", "Citadine", "Hors ville", new ArrayList<String>(), new Float(58.89 )));
            add(new Car(5,"5546ET44", "Essence", "Citadine", "Ville", new ArrayList<String>(), new Float(78.89 )));
            add(new Car(12,"845VFAT", "Essence", "Citadine", "Ville", new ArrayList<String>(), new Float(49.89 )));
            add(new Car(15,"45687FG", "Diesel", "Berline", "Hors ville", new ArrayList<String>(), new Float(105.89 )));
            add(new Car(21,"HTGRATUI", "Diesel", "Berline", "Hors ville", new ArrayList<String>(), new Float(158.89 )));
        }
        return cars;
    }

    public Car getFromId(Integer id)
    {
        return getAll().get(id);
    }

    public List<Car> getFromCriteria(String criteria)
    {
        ArrayList<Car> carSelection = new ArrayList<Car>();
        for (Car car: getAll())
        {
            if (car.criteria.equals(criteria))
            {
                carSelection.add(car);
            }
        }
        return carSelection;
    }

    public void set(Integer id, Car car)
    {
        Car mCar = getAll().get(id);
        mCar.idCarModel = car.idCarModel;
        mCar.registration = car.registration;
        mCar.fuel = car.fuel;
        mCar.type = car.type;
        mCar.criteria = car.criteria;
        mCar.price = car.price;
        mCar.isLeasing = car.isLeasing;
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
