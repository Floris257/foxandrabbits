import java.util.List;
import java.util.Iterator;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 * 
 * @author Floris Meijer, Joz Reijneveld, Jan-bert van Slochteren
 * @version 2014.01.24
 */
public class Hunter extends Animal
{
    // Characteristics shared by all hunters (class variables).
    

    /**
     * Create a Hunter.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
	private Disease disease;
	//days till the disease kills the Hunter.
    private int diseaseDays;
	
    public Hunter(Field field, Location location)
    {
        super(field, location);
    }
    
    /**
     * This is what the fox does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newFoxes A list to return newly born foxes.
     */
    public void act(List<Actor> newHunters)
    {
        if(isAlive()) {
        	if(disease != null){
            	disease.spread(getField(), getLocation());
            }
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                // setDead();
            }
            if(disease != null){
            	//System.out.println(diseaseDays);
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
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
            if(animal instanceof Fox) {
            	Fox fox = (Fox) animal;
            	if(fox.isAlive()){
            		fox.setDead();
            		// Remove the dead fox from the field.
            		return where;
            	}
            }
            
        }
        return null;
    }

    public Disease getDisease(){
		return disease;
	}
	public void setDisease(Disease disease) {
		this.disease = disease;
	}
	public void setDiseaseDays(int days){
		this.diseaseDays = days;
	}
}
