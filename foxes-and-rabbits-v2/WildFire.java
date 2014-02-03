public class WildFire{
 
 private static final int DEFAULT_BURN_OUT_TIME = 5;
	 //class vars
	 private Field field;
	 private int burnOutTime;
	 private Location location;
	 
	 //constructor
	 public WildFire(Field field){
	  //body
	  setField(field);
	  Location startLocation = getField().randomLocation();
	  setLocation(startLocation);
	  setBurnOutTime(DEFAULT_BURN_OUT_TIME);
	 }
	 
	 public WildFire(Field field, Location location){
	  //body
	  setField(field);
	  setLocation(location);
	  setBurnOutTime(DEFAULT_BURN_OUT_TIME);
	 }
	 
	 public WildFire spread(){
	  System.out.println(location);
	  Location adjacent = getField().randomAdjacentLocation(getLocation());
	  Object obj = getField().getObjectAt(adjacent);
	  if(obj instanceof WildFire){
	   //TODO make body
	   return null;
	  }
	  else{
	   return(new WildFire(getField(), adjacent));
	  }
	 }
	 
	 public void burnOut(){
	  if(burnOutTime == 0){
	   field.clear(getLocation());
	   location = null;
	   field = null;
	  }
	  else{
	   burnOutTime = burnOutTime - 1;
	  }
	  
	 }
	 
	 public Location getLocation(){
	  return location;
	 }
	 
	 public void setLocation(Location newLocation){
	  location = newLocation;
	 }
	 
	 public Field getField(){
	  return field;
	 }
	 
	 public void setField(Field field){
	  this.field = field;
	 }
	 public void setBurnOutTime(int time){
	  burnOutTime = time;
	 }
}