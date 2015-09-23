package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Adventure;
import model.City;
import model.InTransitException;
import model.InsufficientFundsException;
import model.ItemNotForSaleException;
import model.Squad;
import model.Store;
import model.StoreImpl;
import model.TravelObserver;

public class StoreView extends JPanel implements ActionListener {

	private Adventure anAdventure;
	private Store aStore;
	private Squad squad;
	private KnapsackView knapsackInfo;
	private FeedView feedInfo;
	private JComboBox storeItemsList;
	private JLabel unitPrice;
	private JTextField amountToBuy;
	private JPanel balancePanel;
	private JLabel balanceLabel;
	private JPanel purchasePanel;
	
	public StoreView(Adventure a, KnapsackView k, FeedView f) {
		anAdventure = a;
		aStore = new StoreImpl();
		squad = a.getSquad();
		knapsackInfo = k;
		feedInfo = f;
		
		balancePanel = new JPanel();
		balanceLabel = new JLabel();
		balanceLabel.setText("Balance: $" + String.format("%.2f", squad.getBalance()));
		balancePanel.add(balanceLabel);
		
		purchasePanel = new JPanel();
		
		JLabel aLabel = new JLabel("Buy ");
		purchasePanel.add(aLabel);
		
		amountToBuy = new JTextField();
		amountToBuy.setColumns(3);
		purchasePanel.add(amountToBuy);
		
		String[] allItems = aStore.getItemNames();
		storeItemsList = new JComboBox(allItems);
		storeItemsList.setActionCommand("select");
		storeItemsList.addActionListener(this);
		purchasePanel.add(storeItemsList);
		
		unitPrice = new JLabel("Unit Price: ");
		unitPrice.setPreferredSize(new Dimension(120, 25));
		purchasePanel.add(unitPrice);
		
		JButton purchaseButton = new JButton("Purchase");
		purchaseButton.setActionCommand("buy");
		purchaseButton.addActionListener(this);
		purchasePanel.add(purchaseButton);

		setLayout(new GridLayout(2, 1));
		add(balancePanel);
		add(purchasePanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("select")) {
			String selectedItem = (String) storeItemsList.getSelectedItem();
			try {
				unitPrice.setText("Unit Price: $" + String.format("%.2f", aStore.getPrice(selectedItem)));
			} catch (ItemNotForSaleException e1) {
			}
		}
		else if (command.equals("buy")) {
			String input = amountToBuy.getText();
			int unitsToBuy = Integer.parseInt(input);
			
			try {
				squad.purchaseSupply((String) storeItemsList.getSelectedItem(), unitsToBuy, aStore);
				balanceLabel.setText("Balance: $" + String.format("%.2f", squad.getBalance()));
				
				knapsackInfo.updateView();
				feedInfo.updateView();
			} catch (ItemNotForSaleException e1) {
				
			} catch (InsufficientFundsException e1) {
				
			}
		}
	}

}
