import java.util.List;
import java.util.Random;


public class Chicken extends Animal {
	// Characteristics shared by all chicken (class variables).

    // The age at which a chicken can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a chicken can live.
    private static final int MAX_AGE = 10;
    // The likelihood of a chicken breeding.
    private static final double BREEDING_PROBABILITY = 0.18;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 3;
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
            age = rand.nextInt(MAX_AGE);
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
        if(age > MAX_AGE) {
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
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }
    
    /**
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
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

}
