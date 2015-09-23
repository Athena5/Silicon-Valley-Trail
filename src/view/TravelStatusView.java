package view;

import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Adventure;
import model.City;
import model.InTransitException;
import model.TravelObserver;

public class TravelStatusView extends Panel implements TravelObserver {
	
	private JPanel travelStatusPanel;
	private JLabel currentTravelStatus;
	
	public TravelStatusView(Adventure a) {
		
		travelStatusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		currentTravelStatus = new JLabel("Start at Chapel Hill");
		travelStatusPanel.add(currentTravelStatus);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(travelStatusPanel);
		a.addTravelObserver(this);
	}
	
	@Override
	public void travelUpdate(Adventure adventure, int distance_to_destination, City destination) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				String message = "";
				
				if (adventure.isTravelling()) {
					message = "On day " + adventure.getDay() + " you are " + distance_to_destination + " miles from " + destination.getName();
				}
				else {
					message = "Arrived at " + destination.getName();
				}
				
				currentTravelStatus.setText(message);
			}
		});
	}

}
