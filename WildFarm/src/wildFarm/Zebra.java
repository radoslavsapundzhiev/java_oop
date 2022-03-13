package wildFarm;

public class Zebra extends Mammal{
    public Zebra(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void eat(Food food) {
        if(food instanceof Vegetable) {
            super.eat(food);
        } else {
            String message = String.format("%ss are not eating that type of food!", this.getAnimalType());
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }
}
