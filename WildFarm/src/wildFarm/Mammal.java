package wildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal{
    private String livingRegion;

    public Mammal(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight);
        this.livingRegion = livingRegion;
    }

    public String getLivingRegion() {
        return livingRegion;
    }

    @Override
    public void eat(Food food) {
        super.setFoodEaten(super.getFoodEaten() + food.getQuantity());
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formatWeight = decimalFormat.format(this.getAnimalWeight());
        String builder = String.format("%s[%s, %s, %s, %d]",
                this.getAnimalType(), this.getAnimalName(), formatWeight, this.getLivingRegion(), this.getFoodEaten());
        return builder;
    }
}
