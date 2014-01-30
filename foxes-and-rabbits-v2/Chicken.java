import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of chicken.
 * 
 * @author Floris Meijer, Joz Reijneveld, Jan-bert van Slochteren
 * @version 2014.01.24 
 */

public class Chicken extends Animal {
	// Characteristics shared by all chicken (class variables).

    // The age at which a chicken can start to breed.
	private static int BREEDING_AGE = 5;
    // The age to which a chicken can live.
	private static int MAX_AGE = 10;
    // The likelihood of a chicken breeding.
	private static double BREEDING_PROBABILITY = 0.18;
    // The maximum number of births.
	private static int MAX_LITTER_SIZE = 3;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    
    // The chicken's age.
    private int age;

    /**
     * Create a new chicken. A chicken may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the chicken will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
	public Chicken(boolean randomAge, Field field, Location location) {
		super(field, location);
		age = 0;
        if(randomAge) {
            age = rand.nextInt(getMAX_AGE());
        }
	}
	//random comment line
	/**
     * Increase the age.
     * This could result in the rabbit's death.
     */
	private void incrementAge()
    {
        age++;
        if(age > getMAX_AGE()) {
            setDead();
        }
    }
	
	/**
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newChickens A list to return newly born chickens.
     */
    private void giveBirth(List<Animal> newChickens)
    {
        // New chickens are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Chicken young = new Chicken(false, field, loc); //<- changed Rabbit to Chicken
            newChickens.add(young);
        }
    }
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= getBREEDING_PROBABILITY()) {
            births = rand.nextInt(getMAX_LITTER_SIZE()) + 1;
        }
        return births;
    }
    
    /**
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= getBREEDING_AGE();
    }

	@Override
	public void act(List<Animal> newChickens) {
		incrementAge();
        if(isAlive()) {
            giveBirth(newChickens);            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
		
	}
	public static int getBREEDING_AGE() {
		return BREEDING_AGE;
	}
	public static void setBREEDING_AGE(int bREEDING_AGE) {
		BREEDING_AGE = bREEDING_AGE;
	}
	public static int getMAX_AGE() {
		return MAX_AGE;
	}
	public static void setMAX_AGE(int mAX_AGE) {
		MAX_AGE = mAX_AGE;
	}
	public static double getBREEDING_PROBABILITY() {
		return BREEDING_PROBABILITY;
	}
	public static void setBREEDING_PROBABILITY(double bREEDING_PROBABILITY) {
		BREEDING_PROBABILITY = bREEDING_PROBABILITY;
	}
	public static int getMAX_LITTER_SIZE() {
		return MAX_LITTER_SIZE;
	}
	public static void setMAX_LITTER_SIZE(int mAX_LITTER_SIZE) {
		MAX_LITTER_SIZE = mAX_LITTER_SIZE;
	}

}
