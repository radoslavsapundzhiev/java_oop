package wildFarm;

import java.text.DecimalFormat;

public class Cat extends Felime{
    private String breed;

    public Cat(String animalName, String animalType, Double animalWeight, String livingRegion, String breed) {
        super(animalName, animalType, animalWeight, livingRegion);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eat(Food food) {
        super.eat(food);
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formatWeight = decimalFormat.format(this.getAnimalWeight());
        String builder = String.format("%s[%s, %s, %s, %s, %d]",
                this.getAnimalType(), this.getAnimalName(), this.getBreed(), formatWeight, this.getLivingRegion(), this.getFoodEaten());
        return builder;
    }
}
