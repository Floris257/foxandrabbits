import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * @author Floris Meijer, Joz Reijneveld, Jan-bert van Slochteren
 * @version 2014.01.24 
 */
public class Rabbit extends Animal
{
    // Characteristics shared by all rabbits (class variables).

    // The age at which a rabbit can start to breed.
	private static int BREEDING_AGE = 5;
    // The age to which a rabbit can live.
	private static int MAX_AGE = 20;
    // The likelihood of a rabbit breeding.
	private static double BREEDING_PROBABILITY = 0.12;
    // The maximum number of births.
	private static int MAX_LITTER_SIZE = 4;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    
    	
    // Individual characteristics (instance fields).
    
    // The rabbit's age.
    private int age;
    // Has a contagious disease.
    private Disease disease;
  //days till the disease kills the animal.
    private int diseaseDays;
    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Rabbit(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        age = 0;
        if(randomAge) {
            age = rand.nextInt(getMAX_AGE());
        }
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to return newly born rabbits.
     */
    public void act(List<Animal> newRabbits)
    {
        incrementAge();
        if(isAlive()) {
        	if(disease != null){
            	disease.spread(getField(), getLocation());
            }
            giveBirth(newRabbits);            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
            if(disease != null){
            	if(diseaseDays == 0){
            		setDead();
            	}
            	else{
            		diseaseDays = diseaseDays - 1;
            	}
            }
        }
    }

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
     * @param newRabbits A list to return newly born rabbits.
     */
    private void giveBirth(List<Animal> newRabbits)
    {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Rabbit young = new Rabbit(false, field, loc);
            newRabbits.add(young);
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
	public Disease getDisease(){
		return disease;
	}
	public void setDisease(Disease disease) {
		// TODO Auto-generated method stub
		this.disease = disease;
		
	}
	public void setDiseaseDays(int days){
		this.diseaseDays = days;
	}
}
