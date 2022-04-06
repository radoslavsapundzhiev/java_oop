package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.Repository;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private Repository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        switch (type){
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                house = null;
                break;
        }
        if(house == null) {
            throw new NullPointerException(INVALID_HOUSE_TYPE);
        }
        this.houses.add(house);
        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type){
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                toy = null;
                break;
        }
        if(toy == null) {
            throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }
        this.toys.buyToy(toy);
        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toys.findFirst(toyType);
        if(toy == null) {
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }
        House house = this.houses
                .stream()
                .filter(h -> h.getName().equals(houseName))
                .findFirst()
                .orElse(null);
        if(house != null) {
            house.buyToy(toy);
            toys.removeToy(toy);
        }
        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                cat = null;
                break;
        }
        if(cat == null) {
            throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }
        House house = this.houses
                .stream()
                .filter(h -> h.getName().equals(houseName))
                .findFirst()
                .orElse(null);
        boolean canLive = false;
        if(house != null) {
            if((house.getClass().getSimpleName().startsWith("Long") && catType.startsWith("Long")) ||
                    (house.getClass().getSimpleName().startsWith("Short") && catType.startsWith("Short"))) {
                canLive = true;
            }
        }
        if(!canLive) {
            return UNSUITABLE_HOUSE;
        }
        house.addCat(cat);
        return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        House house = this.houses
                .stream()
                .filter(h -> h.getName().equals(houseName))
                .findFirst()
                .orElse(null);
        Collection<Cat> fedCats = new ArrayList<>();
        if(house != null) {
            house.feeding();
            fedCats = house
                    .getCats()
                    .stream()
                    .filter(c -> (c.getClass().getSimpleName().startsWith("Long") && c.getKilograms() != 9) ||
                            (c.getClass().getSimpleName().startsWith("Short") && c.getKilograms() != 7))
                    .collect(Collectors.toList());
        }
        return String.format(FEEDING_CAT, fedCats.size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = this.houses
                .stream()
                .filter(h -> h.getName().equals(houseName))
                .findFirst()
                .orElse(null);
        double totalPrice = 0.0;
        if(house != null) {
            double catsPrice = house
                    .getCats()
                    .stream()
                    .mapToDouble(Cat::getPrice)
                    .sum();
            double toysPrice = house
                    .getToys()
                    .stream()
                    .mapToDouble(Toy::getPrice)
                    .sum();
            totalPrice = catsPrice + toysPrice;
        }
        return String.format(VALUE_HOUSE, houseName, totalPrice);
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        for (House house: houses) {
            builder.append(house.getStatistics());
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
