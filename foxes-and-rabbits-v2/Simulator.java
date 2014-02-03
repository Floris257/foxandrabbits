import java.util.Observable;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;


/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Simulator extends Observable
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.06;    
/////////////////////Jan-Bert chickens en hunters//////////////
    private static final double CHICKEN_CREATION_PROBABILITY = 0.08;
    
    private static final double HUNTER_CREATION_PROBABILITY = 0.01;
////////////////////////////////////////////////////////////

    // List of animals in the field.
    private List<Actor> animals;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    //class of food supply
    private VoedselVoorraad voedselVoorraad;
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
        
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
        animals = new ArrayList<Actor>();
        field = new Field(depth, width);
        

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        view.setColor(WildFire.class, Color.red);
        view.setColor(Hunter.class, Color.gray);
        view.setColor(Chicken.class, Color.yellow);
        view.setColor(Rabbit.class, Color.orange);
        view.setColor(Fox.class, Color.blue);
        /////////////////Jan-Bert van Slochteren menubar//////////////////////
        JMenuBar menu = new JMenuBar();
        JMenu menu1 = new JMenu("Menu1");
        JMenu menu2 = new JMenu("Menu2");
        JMenuItem option1 = new JMenuItem("add Hunter");
        JMenuItem option2 = new JMenuItem("Change Fox");
        JMenuItem addFire = new JMenuItem("add Wildfire");
        JMenuItem option3 = new JMenuItem("option2-1");
        JMenuItem option4 = new JMenuItem("option2-2");
        JMenuItem option5 = new JMenuItem("Change Rabbit");
        JMenuItem option6 = new JMenuItem("Change Chicken");
        JMenuItem infect1 = new JMenuItem("Infect Fox");
        JMenuItem infect2 = new JMenuItem("Infect Rabbit");
        JMenuItem infect3 = new JMenuItem("Infect Chicken");
        JMenuItem infect4 = new JMenuItem("Infect Hunter");
        JMenuItem help = new JMenu("Help");
        menu1.add(option1);
        menu1.add(addFire);
        menu1.add(option2);
        menu2.add(option3);
        menu2.add(option4);
        menu1.add(option5);
        menu1.add(option6);
        menu2.add(infect1);
        menu2.add(infect2);
        menu2.add(infect3);
        menu2.add(infect4);
        help.add(help);
        option1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				//WIP JB
				//
				Location location = field.randomLocation();
                Hunter hunter = new Hunter(field, location);
                animals.add(hunter);
                view.showStatus(step, field);
			}
		});
        option2.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				new ParameterInput("Fox");
				System.out.println("option1-2");
			}
		});
        addFire.addActionListener(new ActionListener(){
        	public void actionPerformed (ActionEvent e){
        		Location location = field.randomLocation();
                WildFire wildFire = new WildFire(field, location);
        		animals.add(wildFire);
        		view.showStatus(step, field);
        	}
        });
        option5.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				new ParameterInput("Rabbit");
				System.out.println("option1-3");
			}
		});
        option6.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				new ParameterInput("Chicken");
				System.out.println("option1-4");
			}
		});
        option3.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.out.println("option2-1");
				//TODO
				Iterator<Actor> it = animals.iterator();
		        while(it.hasNext()) {
		            System.out.println(it.next());		        }
			}
		});
        option4.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.out.println("option2-2");
			}
		});
        help.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.out.println("help");
			}
		});
        infect1.addActionListener(new ActionListener(){
        	//
        	public void actionPerformed(ActionEvent e){
        		Iterator<Actor> it = animals.iterator();
        		Fox infectee = null;
        		while(it.hasNext() && infectee == null){
        			Actor current = it.next();
        			if(current instanceof Fox){
        				infectee = (Fox)current;
        			}
        		}
        		infectee.setDisease(new Disease("Fox"));
        		infectee.setDiseaseDays(infectee.getDisease().getDEFAULT_DAYS());
        	}
        });
        infect2.addActionListener(new ActionListener(){
        	//
        	public void actionPerformed(ActionEvent e){
        		Iterator<Actor> it = animals.iterator();
        		Rabbit infectee = null;
        		while(it.hasNext() && infectee == null){
        			Actor current = it.next();
        			if(current instanceof Rabbit){
        				infectee = (Rabbit)current;
        			}
        		}
        		infectee.setDisease(new Disease("Rabbit"));
        		infectee.setDiseaseDays(infectee.getDisease().getDEFAULT_DAYS());
        	}
        });
        infect3.addActionListener(new ActionListener(){
        	//
        	public void actionPerformed(ActionEvent e){
        		Iterator<Actor> it = animals.iterator();
        		Chicken infectee = null;
        		while(it.hasNext() && infectee == null){
        			Actor current = it.next();
        			if(current instanceof Chicken){
        				infectee = (Chicken)current;
        			}
        		}
        		infectee.setDisease(new Disease("Chicken"));
        		infectee.setDiseaseDays(infectee.getDisease().getDEFAULT_DAYS());
        	}
        });
        infect4.addActionListener(new ActionListener(){
        	//
        	public void actionPerformed(ActionEvent e){
        		Iterator<Actor> it = animals.iterator();
        		Hunter infectee = null;
        		while(it.hasNext() && infectee == null){
        			Actor current = it.next();
        			if(current instanceof Hunter){
        				infectee = (Hunter)current;
        			}
        		}
        		infectee.setDisease(new Disease("Hunter"));
        		infectee.setDiseaseDays(infectee.getDisease().getDEFAULT_DAYS());
        	}
        });
        menu.add(menu1);
        menu.add(menu2);
        menu.add(help);
        view.setJMenuBar(menu);

///////////////////////////////////////////////////////////////////////////////////////// 
        ////////////////////JB buttons////////////////
        JPanel buttonPanel = new JPanel(new GridLayout(2,1));
        JButton button1 = new JButton("Step 1");
        JButton button100 = new JButton("Step 100");
        button1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				simulate(1);
			}
		});
        button100.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				simulate(100);
			}
		});
        buttonPanel.add(button1);
        buttonPanel.add(button100);
        view.add(buttonPanel, BorderLayout.WEST);
        /////////////////////////////////////
        
        voedselVoorraad = new VoedselVoorraad(field);
        
        // Setup a valid starting point.
        reset();
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation()
    {
        simulate(4000);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep()
    {
        step++;

        // Provide space for newborn animals.
        List<Actor> newAnimals = new ArrayList<Actor>();        
        // Let all rabbits act.
        for(Iterator<Actor> it = animals.iterator(); it.hasNext(); ) {
        	Actor animal = it.next();
            if(animal instanceof Animal){
            	
            Animal tempAnimal = (Animal) animal;
            	tempAnimal.act(newAnimals);
            	if(! tempAnimal.isAlive()) {
            		it.remove();
            	}
            }
        }
        //TODO
        List<Actor> newFires = new ArrayList<Actor>();
        Iterator<Actor> it = animals.iterator();
        while(it.hasNext()) {
        	
        	Actor wildFire = it.next();
        	if(wildFire instanceof WildFire){
        		WildFire tempWildFire = (WildFire) wildFire;
        		if(tempWildFire.getLocation() != null){
            		newFires.add(tempWildFire.spread());
            		tempWildFire.burnOut();
            	}
            	else{
            	it.remove();
            	}
        	}
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        animals.addAll(newFires);
        animals.addAll(newAnimals);
        
        view.showStatus(step, field);
        

        // Updates the amount of food supply
        voedselVoorraad.updateVoedselVoorraad(animals);
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        animals.clear();
        populate();
        
        // Show the starting state in the view.
        view.showStatus(step, field);
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    animals.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    animals.add(rabbit);
                }
                /////////////////////Jan-Bert chickens en hunters//////////////
                else if(rand.nextDouble() <= CHICKEN_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Chicken chicken = new Chicken(true, field, location);
                    animals.add(chicken);
                }
                else if(rand.nextDouble() <= HUNTER_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Hunter hunter = new Hunter(field, location);
                    animals.add(hunter);
                }
                //////////////////////////////////////////////////////////////
                // else leave the location empty.
            }
        }
    }
    /**
     * Returns the list animals that live in the simulator field
     * @return animals The list of animals that live
     */
    public List<Actor> getAnimals(){
    	return animals;
    }
    
    /**
     * Returns the values of the object Field (width and height)
     * @return field The values of the object Field (width and height)
     */
    public Field getField(){
    	return field;
    }
}