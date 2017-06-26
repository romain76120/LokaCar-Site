package eni_ecole.fr.lokacarsite.dao;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.Client;

/**
 * Created by rroger2016 on 26/06/2017.
 */

public class ClientDao {
    private static ArrayList<Client> clients = null;

    public List<Client> getAll() {
        if (clients == null) {
            clients = new ArrayList<Client>();
            // TODO
            //cars.add(new Car());
        }
        return clients;
    }

    public Client getFromId(Integer id) {
        return getAll().get(id);
    }

    public void set(Integer id, Client client) {
        Client mClient = getAll().get(id);
        mClient.lastname = client.lastname;
        mClient.firstname = client.firstname;
        mClient.lastname = client.lastname;
        mClient.mail = client.mail;
        mClient.phone = client.phone;
    }

    public Client add(Client client) {
        client.id = getAll().size();
        getAll().add(client);
        return client;
    }

    public void delete(Integer id) {
        getAll().remove(id);
        for (int i = 0; i < getAll().size(); i++) {
            getAll().get(i).id = i;
        }
    }
}
