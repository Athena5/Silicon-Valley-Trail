package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import person.Person;

public class PersonView extends JPanel implements Observer {

	private Person person;
	private JPanel b;
	private JLabel personLabel;
	
	public PersonView(Person p) {
		person = p;
		
		personLabel = new JLabel(p.toString());
		
		b = new JPanel(new FlowLayout(FlowLayout.LEFT));
		b.add(personLabel);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(b);
		person.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				personLabel.setText(person.toString());
			}
		});
	}
}
