import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Histogram extends JPanel implements Observer {
    private int rabbitCount, foxCount, chickenCount, hunterCount, total;
    private JLabel histogram, rabbit, fox, chicken, hunter;
    private Simulator model;
    
    /**
     * Makes a Histogram, the information comes form the class Simulator();
     * It displays the amount of each animal
     * 
     * @param model Is een object Simulator
     */
    public Histogram(Simulator model) {
        this.model = model;
//        rabbitCount = getRabbitAmount();
//        foxCount = getFoxAmount();
//        chickenCount = getchickenAmount();
//		  hunterCount = gethunterAmount();	
        generateCounts();
        total = rabbitCount+foxCount+chickenCount+hunterCount;

        this.setLayout(null);

        histogram = new JLabel("Representation of the percentual participance per species");
        histogram.setBounds(20, 20, 350, 30);
        this.add(histogram);
        //rabbits
        rabbit = new JLabel("Rabbit");
        rabbit.setBounds(120, 250, 100, 20);
        this.add(rabbit);
        //Foxs
        fox = new JLabel("Fox");
        fox.setBounds(220, 250, 100, 20);
        this.add(fox);
        //Chicken
        chicken = new JLabel("chicken");
        chicken.setBounds(320, 250, 100, 20);
        this.add(chicken);
        //Hunters
        //TODO hier moet ik nog wat waarden aanpassen
        hunter = new JLabel("hunter");
        hunter.setBounds(320, 250, 100, 20);
        this.add(hunter);

    }
    
    /**
     * Gives an update to the graph
     */
    public void update(Observable obs, Object arg) {
        model = (Simulator) obs;
//      rabbitCount = getRabbitAmount();
//      foxCount = getFoxAmount();
//      chickenCount = getchickenAmount();
//		  hunterCount = gethunterAmount();
        generateCounts();
        this.total = rabbitCount+foxCount+chickenCount+hunterCount;

        repaint();
    }
    
    /**
     * Sets the propeties of the Histogram for each of the animals
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw the box in which the histogram is displayed
        g.setColor(Color.white);
        g.fillRoundRect(20, 50, 400, 200, 10, 10);

        //draw the rabbit line
        //x, y, width, height
        g.setColor(Color.red);
        g.fillRoundRect(120, 250-((((rabbitCount*100)/total)*200)/100), 25, ((((rabbitCount*100)/total)*200)/100), 10, 10);

        //draw the fox line
        //x, y, width height
        g.setColor(Color.white);
        g.fillRoundRect(220, 250-((((foxCount*100)/total)*200)/100), 25, ((((foxCount*100)/total)*200)/100), 10, 10);

        //draw the chicken line
        g.setColor(Color.blue);
        g.fillRoundRect(320, 250-((((chickenCount*100)/total)*200)/100), 25, ((((chickenCount*100)/total)*200)/100), 10, 10);
        
        //draw the hunter line
        //TODO ik moet hier nog waarden aanpassen
        g.setColor(Color.orange);
        g.fillRoundRect(320, 250-((((hunterCount*100)/total)*200)/100), 25, ((((hunterCount*100)/total)*200)/100), 10, 10);
    }

    /**
     * Counts the amount of animals per animal.
     */
    public void generateCounts()
    {
        rabbitCount = 0;
        foxCount = 0;
        chickenCount = 0;
        hunterCount = 0;
        List<Animal> aniList = model.getAnimals();
        for(int loop = 0; loop < model.getAnimals().size(); loop++){
        	Animal animal = aniList.get(loop);
        	if(animal != null) {
                if(animal instanceof Rabbit) {
                    rabbitCount++;
                }
                if(animal instanceof Fox) {
                    foxCount++;
                }
                if(animal instanceof Chicken) {
                    chickenCount++;
                }
                if(animal instanceof Hunter) {
                	hunterCount++;
                }
            }
        }
        //TODO moeten nog even zien of dit voorbeeld relevant is, indien de code gewoon werkt moet dit weg
        /*for(int row = 0; row < model.getField().getDepth(); row++) {
            for(int col = 0; col < model.getField().getWidth(); col++) {
                Animal animal = model.animals[].getAnimalAt(row, col);
                if(animal != null) {
                    if(animal instanceof Rabbit) {
                        rabbitCount++;
                    }
                    if(animal instanceof Fox) {
                        foxCount++;
                    }
                    if(animal instanceof Chicken) {
                        chickenCount++;
                    }
                    if(animal instanceof Hunter) {
                    	hunterCount++;
                    }
                }
            }
        }*/
    }
}