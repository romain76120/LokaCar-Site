package eni_ecole.fr.lokacarsite.dao;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Category;

/**
 * Created by pbontempi2017 on 28/06/2017.
 */

public class CategoryDao {
    private static ArrayList<Category> categories = null;

    public List<Category> getAll()
    {
        if (categories == null)
        {
            categories = new ArrayList<Category>();
            add(new Category("SUV"));
            add(new Category("Citadine"));
            add(new Category("Berline"));
            add(new Category("Tout terrain"));
        }
        return categories;
    }

    public Category getFromId(Integer id)
    {
        return getAll().get(id);
    }

    public void set(Integer id, Category category)
    {
        Category mCategory = getAll().get(id);
        mCategory.name = category.name;
    }

    public Category add(Category category){
        category.id = getAll().size();
        getAll().add(category);
        return category;
    }

    public void delete(Integer id){
        getAll().remove(id);
        for (int i = 0; i < getAll().size(); i++)
        {
            getAll().get(i).id = i;
        }
    }
}
