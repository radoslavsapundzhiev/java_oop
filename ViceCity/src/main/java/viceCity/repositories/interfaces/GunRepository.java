package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;

public class GunRepository implements Repository{
    private Collection<Gun> models;
    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection getModels() {
        return models;
    }

    @Override
    public void add(Object model) {
        
    }

    @Override
    public boolean remove(Object model) {
        return false;
    }

    @Override
    public Object find(String name) {
        return null;
    }
}
