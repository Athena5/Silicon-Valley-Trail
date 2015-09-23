package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import supplies.Food;
import supplies.Supplies;
import model.Adventure;
import model.City;
import model.Knapsack;
import model.TravelObserver;

public class KnapsackView extends JPanel implements TravelObserver {

	private Knapsack aKnapsack;
	private JPanel knapsackPanel;
	private JLabel knapsackContents;
	
	public KnapsackView(Adventure a) {
		aKnapsack = (a.getSquad().getKnapsack());
		
		String contentsDescription = createLabel();
		knapsackContents = new JLabel(contentsDescription);
		knapsackContents.setVerticalAlignment(JLabel.TOP);
		knapsackContents.setPreferredSize(new Dimension(300, 200));
		
		knapsackPanel = new JPanel(new BorderLayout());
		knapsackPanel.add(knapsackContents, BorderLayout.WEST);
		add(knapsackPanel);
		a.addTravelObserver(this);
	}
	
	@Override
	public void travelUpdate(Adventure adventure, int distance_to_destination, City destination) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				updateView();
			}
		});
	}

	private String createLabel( ) {
		String contentsDescription = "<html>Knapsack:";
		Supplies[] allSupplies = aKnapsack.getSupplies();
		
		for(int i = 0; i < allSupplies.length; i++) {
			Supplies currentSupply = allSupplies[i];
			String currentSupplyName = currentSupply.getName();
			int currentSupplyAmount = currentSupply.getAmount();
			
			contentsDescription = contentsDescription + "<br>" + currentSupplyAmount + " " + currentSupplyName;
			if(currentSupply instanceof Food) {
				contentsDescription = contentsDescription + " (expires in " + ((Food)currentSupply).getDaysTillExpiration() +" days)";
			}
		}
		contentsDescription = contentsDescription + "</html>";
		
		return contentsDescription;
	}
	
	public void updateView() {
		String message = createLabel();				
		knapsackContents.setText(message);
	}
}
