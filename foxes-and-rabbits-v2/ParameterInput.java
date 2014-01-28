import java.awt.Container;
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
	private JLabel MALabel = new JLabel("Vul de leeftijd in waarop de dieren sterven [e.g. 18");
	private JLabel BPLabel = new JLabel("Vul de kans van voortplanting in [e.g. 0.36]");
	private JLabel LSLabel = new JLabel("Vul de het maximum aantal geboortes per dier per stap in [e.g. 3]");
	
	public ParameterInput(){
		//omitted
	}
	
	public ParameterInput(String animal){
		//TODO fix all this BS;
		//
		if(animal.equals("Fox")){
			//Fox BS
			nameLabel = new JLabel("Vossen");
		}
		if(animal.equals("Rabbit")){
			//Rabbit BS
			nameLabel = new JLabel("Konijnen");
		}
		if(animal.equals("Chicken")){
			//Chicken BS
			nameLabel = new JLabel("Kippen");
		}
		//TODO textfields etc.;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextField BAText = new JTextField();
		JTextField MAText = new JTextField();
		JTextField BPText = new JTextField();
		JTextField LSText = new JTextField();
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(nameLabel);
		contentPane.add(BALabel);
		contentPane.add(BAText);
		contentPane.add(MALabel);
		contentPane.add(MAText);
		contentPane.add(BPLabel);
		contentPane.add(BPText);
		contentPane.add(LSLabel);
		contentPane.add(LSText);
		frame.pack();
		frame.setVisible(true);
		
		
	}
}
