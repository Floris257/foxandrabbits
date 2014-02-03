import java.util.List;

/**
 * An interface that acts as actor.
 * All actors should implemend this class. 
 * 
 * @author Floris Meijer, Joz Reijneveld, Jan-bert van Slochteren
 * @version 2014.01.24 
 */

public interface Actor {
    public Location getLocation();
    public void setLocation(Location newLocation);
    public Field getField();

}