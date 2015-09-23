package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import person.Person;
import supplies.Food;
import model.Adventure;
import model.Knapsack;
import model.Squad;

public class FeedView extends JPanel implements ActionListener {

	private Adventure anAdventure;
	private Squad aSquad;
	private Knapsack aKnapsack;
	private KnapsackView knapsackInfo;
	private JButton feedButton;
	private JComboBox foodList;
	private JComboBox playerList;
	private int[] playerNumbers;
	private Person[] players;
	private String[] playerNames;
	private Food[] knapsackFoods;
	private String[] foodNames;
	
	public FeedView(Adventure a, KnapsackView k) {
		anAdventure= a;
		aSquad = anAdventure.getSquad();
		aKnapsack = aSquad.getKnapsack();
		knapsackInfo = k;
		
		feedButton = new JButton("Feed");
//		purchaseButton.setActionCommand("buy");
		feedButton.addActionListener(this);
		add(feedButton);
		
		knapsackFoods = aKnapsack.getEdibleSupplies();
		foodNames = new String[knapsackFoods.length];
		for (int i = 0; i < knapsackFoods.length; i++) {
			foodNames[i] = knapsackFoods[i].getName();
		}
		
		foodList = new JComboBox(foodNames);
		foodList.setPreferredSize(new Dimension(120, 25));
		add(foodList);
		
		JLabel aLabel = new JLabel(" to ");
		add(aLabel);
		
		int numberOfPlayers = aSquad.getNumPlayers();
		playerNumbers = new int[numberOfPlayers];
		players = new Person[numberOfPlayers];
		playerNames = new String[numberOfPlayers];
		
		for (int i = 0; i < numberOfPlayers; i++) {
			playerNumbers[i] = i;
			players[i] = aSquad.getPlayer(i);
			playerNames[i] = players[i].getName();
		}
		
		playerList = new JComboBox(playerNames);
		add(playerList);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedFoodName = (String)foodList.getSelectedItem();
		String selectedPlayerName = (String)playerList.getSelectedItem();
		Food selectedFood = knapsackFoods[Arrays.asList(foodNames).indexOf(selectedFoodName)];
		Person selectedPlayer = players[Arrays.asList(playerNames).indexOf(selectedPlayerName)];
		
		aSquad.feed(selectedFood, selectedPlayer);
		updateView();
	}

	public void updateView() {
		
		knapsackFoods = aKnapsack.getEdibleSupplies();
		foodNames = new String[knapsackFoods.length];
		for (int i = 0; i < knapsackFoods.length; i++) {
			foodNames[i] = knapsackFoods[i].getName();
		}
		
		foodList.removeAllItems();
		for (int i = 0; i < foodNames.length; i++) {
			foodList.addItem(foodNames[i]);
		}
//		foodList = new JComboBox(foodNames);
		knapsackInfo.updateView();
	}
}
