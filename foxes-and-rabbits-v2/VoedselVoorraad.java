import java.util.List;


public class VoedselVoorraad {
	private static double voedselVoorraad;
	private Field field;
	private double rabbitCount;
	private double chickenCount;
	private Simulator model;
	
	// this is the food supply per field
	private static final double voedselwaarde = 10.0;
	// this is the amount that a rabbit eats per step
	private static final double rabbitEats = 1.0;
	// this is the amount that a chicken eats per step
	private static final double chickenEats = 0.5;
	
	/**
	 * Generates a food supply for the prey animals based on the side of the simulation field
	 * @param voedselwaarde Food supply per field
	 */
	public VoedselVoorraad(){
		voedselVoorraad = ((field.getDepth() * field.getWidth()) * voedselwaarde);
	}
	
	/**
	 * updates the status of food supply
	 */
	public void updateVoedselVoorraad(){
		checkCountAnimal();
		voedselVoorraad = 	voedselVoorraad - ((rabbitCount*rabbitEats)+(chickenCount*chickenEats));
		
	}
	
	/**
	 * returns the amount of food supply
	 * @return voedselVoorraad Total amount of food supply
	 */
	public static double getVoedselVoorraad(){
		return voedselVoorraad;
	}
	
	/**
	 * Counts the amount of prey animals
	 */
	public void checkCountAnimal(){
		rabbitCount = 0;
        chickenCount = 0;
        List<Animal> aniList = model.getAnimals();
        for(int loop = 0; loop < model.getAnimals().size(); loop++){
        	Animal animal = aniList.get(loop);
        	if(animal != null) {
                if(animal instanceof Rabbit) {
                    rabbitCount++;
                }
                if(animal instanceof Chicken) {
                    chickenCount++;
                }
        	}
        }
	}
}
