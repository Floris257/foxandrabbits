import java.util.List;


public class VoedselVoorraad {
	private static double voedselVoorraad;
	private Field field;
	private double rabbitCount;
	private double chickenCount;
	//private Simulator model;
	private int depth;
	private int width;
	
	// this is the food supply per field
	private static final double voedselwaarde = 10.0;
	// this is the amount that a rabbit eats per step
	//private static final double rabbitEats = 0.2;
	// this is the amount that a chicken eats per step
	//private static final double chickenEats = 0.2;
	
	/**
	 * Generates a food supply for the prey animals based on the side of the simulation field
	 * @param voedselwaarde Food supply per field
	 */
	public VoedselVoorraad(Field field){
		this.field = field;
		this.depth = field.getDepth();
		this.width = field.getWidth();
		voedselVoorraad = ((depth * width) * voedselwaarde);
	}
	
	/**
	 * updates the status of food supply
	 */
	public void updateVoedselVoorraad(List<Animal> animals){
		checkCountAnimal(animals);
		voedselVoorraad = ((depth * width) - (rabbitCount + chickenCount)); 
		//voedselVoorraad = 	voedselVoorraad - ((rabbitCount*rabbitEats)+(chickenCount*chickenEats));
		
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
	public void checkCountAnimal(List<Animal> animals){
		rabbitCount = 0;
        chickenCount = 0;
        for(int loop = 0; loop < animals.size(); loop++){
        	Animal animal = animals.get(loop);
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
