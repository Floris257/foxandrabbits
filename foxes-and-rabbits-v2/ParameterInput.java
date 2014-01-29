import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

public class ParameterInput {
	//vars
	private JFrame frame;
	private int breedingAge;
	private int maxAge;
	private	double breedingProbability;
	private int litterSize;
	private JLabel nameLabel;
	private JLabel BALabel = new JLabel("Vul de leeftijd in waarop de dieren beginnen met voortplanten [e.g. 5]");
	private JLabel MALabel = new JLabel("Vul de leeftijd in waarop de dieren sterven [e.g. 18]");
	private JLabel BPLabel = new JLabel("Vul de kans van voortplanting in [e.g. 0.36]");
	private JLabel LSLabel = new JLabel("Vul de het maximum aantal geboortes per dier per stap in [e.g. 3]");
	private JTextField BAText;
	private JTextField MAText;
	private JTextField BPText;
	private JTextField LSText;
	
	public ParameterInput(){
		//omitted
	}
	
	public ParameterInput(final String animal){
		//TODO fix all this BS;
		//
		if(animal.equals("Fox")){
			//Fox BS
			nameLabel = new JLabel("Vossen");
			BAText = new JTextField(new String().valueOf(Fox.BREEDING_AGE));
			MAText = new JTextField(new String().valueOf(Fox.MAX_AGE));
			BPText = new JTextField(new String().valueOf(Fox.BREEDING_PROBABILITY));
			LSText = new JTextField(new String().valueOf(Fox.MAX_LITTER_SIZE));
			breedingAge = Fox.BREEDING_AGE;
			maxAge = Fox.MAX_AGE;
			breedingProbability = Fox.BREEDING_PROBABILITY;
			litterSize = Fox.MAX_LITTER_SIZE;
		}
		if(animal.equals("Rabbit")){
			//Rabbit BS
			nameLabel = new JLabel("Konijnen");
			BAText = new JTextField(new String().valueOf(Rabbit.BREEDING_AGE));
			MAText = new JTextField(new String().valueOf(Rabbit.MAX_AGE));
			BPText = new JTextField(new String().valueOf(Rabbit.BREEDING_PROBABILITY));
			LSText = new JTextField(new String().valueOf(Rabbit.MAX_LITTER_SIZE));
			breedingAge = Rabbit.BREEDING_AGE;
			maxAge = Rabbit.MAX_AGE;
			breedingProbability = Rabbit.BREEDING_PROBABILITY;
			litterSize = Rabbit.MAX_LITTER_SIZE;
		}
		if(animal.equals("Chicken")){
			//Chicken BS
			nameLabel = new JLabel("Kippen");
			BAText = new JTextField(new String().valueOf(Chicken.BREEDING_AGE));
			MAText = new JTextField(new String().valueOf(Chicken.MAX_AGE));
			BPText = new JTextField(new String().valueOf(Chicken.BREEDING_PROBABILITY));
			LSText = new JTextField(new String().valueOf(Chicken.MAX_LITTER_SIZE));
			breedingAge = Chicken.BREEDING_AGE;
			maxAge = Chicken.MAX_AGE;
			breedingProbability = Chicken.BREEDING_PROBABILITY;
			litterSize = Chicken.MAX_LITTER_SIZE;
		}
		//TODO textfields etc.;
		frame = new JFrame();
		/*BAText = new JTextField(5);
		MAText = new JTextField(5);
		BPText = new JTextField(5);
		LSText = new JTextField(5);*/
		JButton OK = new JButton("OK");
		JButton reset = new JButton("Reset");
		OK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//TODO get text, filter text, update vars;
				String BAString = BAText.getText();
				String MAString = MAText.getText();
				String BPString = BPText.getText();
				String LSString = LSText.getText();
				//Set Strings to usable types;
				try{
				Integer BAInteger = new Integer(BAString);
				Integer MAInteger = new Integer(MAString);
				Double BPInteger = new Double(BPString);
				Integer LSInteger = new Integer(LSString);
				
				System.out.println(BAInteger.intValue());
				System.out.println(MAInteger.intValue());
				System.out.println(BPInteger.doubleValue());
				System.out.println(LSInteger.intValue());
				
				if(animal.equals("Fox")){
					//Fox BS
					Fox.BREEDING_AGE = BAInteger.intValue();
					Fox.MAX_AGE = MAInteger.intValue();
					Fox.BREEDING_PROBABILITY = BPInteger.doubleValue();
					Fox.MAX_LITTER_SIZE = LSInteger.intValue();
					System.out.println("FOX aangepast");
				}
				if(animal.equals("Rabbit")){
					//Rabbit BS
					Rabbit.BREEDING_AGE = BAInteger.intValue();
					Rabbit.MAX_AGE = MAInteger.intValue();
					Rabbit.BREEDING_PROBABILITY = BPInteger.doubleValue();
					Rabbit.MAX_LITTER_SIZE = LSInteger.intValue();
					System.out.println("rabbit aangepast");
				}
				if(animal.equals("Chicken")){
					//Chicken BS
					Chicken.BREEDING_AGE = BAInteger.intValue();
					Chicken.MAX_AGE = MAInteger.intValue();
					Chicken.BREEDING_PROBABILITY = BPInteger.doubleValue();
					Chicken.MAX_LITTER_SIZE = LSInteger.intValue();
					System.out.println("chicken aangepast");
				}
				
				frame.dispose();
				}
				catch(NumberFormatException except){
					System.out.println("USE VALID INPUT, FUCKTARD!");
				}
				
			}
		});
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//TODO get text, filter text, update vars;
				BAText.setText(new String().valueOf(breedingAge));
				MAText.setText(new String().valueOf(maxAge));
				BPText.setText(new String().valueOf(breedingProbability));
				LSText.setText(new String().valueOf(litterSize));
				
				///////////
			}
		});
		Container contentPane = frame.getContentPane();
		//contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
		labels.add(nameLabel);
		labels.add(BALabel);
		labels.add(BAText);
		labels.add(MALabel);
		labels.add(MAText);
		labels.add(BPLabel);
		labels.add(BPText);
		labels.add(LSLabel);
		labels.add(LSText);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS) );
		buttons.add(OK);
		buttons.add(Box.createHorizontalStrut(10));
		buttons.add(reset);
		contentPane.add(labels, BorderLayout.WEST);
		contentPane.add(buttons, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
}
