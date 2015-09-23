package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.*;

public class TravelControlView extends JPanel implements ActionListener {

	private Adventure anAdventure;
	private JComboBox citiesList;
	
	public TravelControlView (Adventure a) {
		anAdventure = a;
		
		JLabel aLabel = new JLabel("Travel to: ");
		add(aLabel);
		
		City[] allCities = anAdventure.getCities();
		citiesList = new JComboBox(allCities);
		citiesList.addActionListener(this);
		add(citiesList);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		anAdventure.travel((City)citiesList.getSelectedItem());
	}

}
