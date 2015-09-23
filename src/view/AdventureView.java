package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import person.Person;
import model.Adventure;
import model.Squad;
import model.Store;

public class AdventureView extends JPanel {

	private Adventure adventure;
	private Store currentStore;
	
	/**
	 * @param adventure
	 */
	public AdventureView(Adventure adventure) {
		this.adventure = adventure;
		
		setLayout(new BorderLayout());
		
		Squad s = adventure.getSquad();
		
		JPanel squadPanel = new JPanel();
		squadPanel.setLayout(new GridLayout(5, 1));
		squadPanel.setVisible(true);
		
		Person p;
		for(int i = 0; i < s.getNumPlayers(); i++) {
			p = s.getPlayer(i);
			PersonView pv = new PersonView(p);
			squadPanel.add(pv, BorderLayout.WEST);
		}
		
		add(squadPanel, BorderLayout.WEST);
		
		KnapsackView knapsackView = new KnapsackView(adventure);
		knapsackView.setPreferredSize(new Dimension(300, 150));
		add(knapsackView, BorderLayout.EAST);
		
		TravelControlView travelView = new TravelControlView(adventure);
		add(travelView, BorderLayout.SOUTH);
		
		TravelStatusView travelStatusView = new TravelStatusView(adventure);
		add(travelStatusView, BorderLayout.SOUTH);
		
		JPanel travelPanel = new JPanel(new GridLayout(1, 2));
		travelPanel.add(travelStatusView, BorderLayout.WEST);
		travelPanel.add(travelView, BorderLayout.EAST);
		
		FeedView feedPanel = new FeedView(adventure, knapsackView);
		StoreView storePanel = new StoreView(adventure, knapsackView, feedPanel);
		
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		bottomPanel.add(travelPanel, BorderLayout.NORTH);
		bottomPanel.add(storePanel, BorderLayout.CENTER);
		bottomPanel.add(feedPanel, BorderLayout.SOUTH);
		add(bottomPanel, BorderLayout.SOUTH);
		
	}
}
