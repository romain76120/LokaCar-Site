package eni_ecole.fr.lokacarsite.beans;

import java.util.List;

/**
 * Created by pbontempi2017 on 26/06/2017.
 */

public class Leasing {
    public Integer id;
    public Car car;
    public Client client;
    public String startDate;
    public String endDate;
    public List<Photo> photoBefore;
    public List<Photo> photoAfter;
    public String realStartDate;
    public String realEndDate;
    public Float priceTotal;

    public Leasing() {
        car = new Car();
        client = new Client();

    }

    public Leasing(Integer id, Car car, Client client, String startDate, String endDate, List<Photo> photoBefore, List<Photo> photoAfter, String realStartDate, String realEndDate, Float priceTotal) {
        this.id = id;
        this.car = car;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.photoBefore = photoBefore;
        this.photoAfter = photoAfter;
        this.realStartDate = realStartDate;
        this.realEndDate = realEndDate;
        this.priceTotal = priceTotal;
    }

    public Leasing(Car car, Client client, String startDate, String endDate, List<Photo> photoBefore, List<Photo> photoAfter, String realStartDate, String realEndDate, Float priceTotal) {
        this.id = -1;
        this.car = car;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.photoBefore = photoBefore;
        this.photoAfter = photoAfter;
        this.realStartDate = realStartDate;
        this.realEndDate = realEndDate;
        this.priceTotal = priceTotal;
    }

    @Override
    public String toString() {
        return car.toString() + client.toString() + startDate + endDate + realStartDate + realEndDate + priceTotal ;
    }
}
