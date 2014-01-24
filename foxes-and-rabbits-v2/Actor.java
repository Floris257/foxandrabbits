import java.util.List;

public interface Actor {
	
	abstract public void act(List<Animal> newAnimals);
    public boolean isAlive();
    public void setDead();
    public Location getLocation();
    public void setLocation(Location newLocation);
    public Field getField();

}