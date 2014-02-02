import java.util.Iterator;
import java.util.List;


public class Disease {
	
	private final static int DEFAULT_DAYS = 5;
	private String carrier;
	
	public Disease(String carrier){
		this.carrier = carrier;
	}
	
	/**
	 * Gives the actors on the location a disease
	 * @param field size of the simulation field
	 * @param location Location of an actor
	 */
	public void spread(Field field, Location location){
		List<Location> adjacentLocations = field.adjacentLocations(location);
		Iterator<Location> it = adjacentLocations.iterator();
		while(it.hasNext()){
			Location where = it.next();
			Object animal = field.getObjectAt(where);
			if(carrier.equals("Rabbit") && animal instanceof Rabbit){
				Rabbit rabbit = (Rabbit)animal;
				rabbit.setDisease(new Disease(carrier));
				rabbit.setDiseaseDays(DEFAULT_DAYS);
			}
			if(carrier.equals("Fox") && animal instanceof Fox){
				Fox fox = (Fox)animal;
				fox.setDisease(new Disease(carrier));
				fox.setDiseaseDays(DEFAULT_DAYS);
			}
			if(carrier.equals("Chicken") && animal instanceof Chicken){
				Chicken chicken = (Chicken)animal;
				chicken.setDisease(new Disease(carrier));
				chicken.setDiseaseDays(DEFAULT_DAYS);
			}
			if(carrier.equals("Hunter") && animal instanceof Hunter){
				Hunter hunter = (Hunter)animal;
				hunter.setDisease(new Disease(carrier));
				hunter.setDiseaseDays(DEFAULT_DAYS);
			}
		}
	}
	public int getDEFAULT_DAYS(){
		return DEFAULT_DAYS;
	}
	
}
