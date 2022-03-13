package wildFarm;

public class Tiger extends Felime{
    public Tiger(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void eat(Food food) {
        if(food instanceof Meat) {
            super.eat(food);
        } else {
            String message = String.format("%ss are not eating that type of food!", this.getAnimalType());
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }
}
