package halcyon.robouser.com.gui.elements;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import halcyon.robouser.com.RoboUserMain;
import halcyon.robouser.com.Utility;
import halcyon.robouser.com.actionEngine.Action;
import halcyon.robouser.com.actionEngine.Action.AType;
import halcyon.robouser.com.gui.elements.customizers.ActionCustomizer;

public class AddActionDialogue extends JPanel {

	ActionCustomizer customizer;
	Action.AType aType;
	
	JLabel actionTypeLabel;
	JComboBox actionTypes;
	
	public AddActionDialogue() {
		initialize();
	}
	
	public AddActionDialogue(Action a) {
		initialize();
		changeActionType(a);
	}
	
	private void initialize() {
		setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(350, 500));
		
		aType = Action.AType.click;
		
		//Instantiate components
		actionTypes = new JComboBox<Action.AType>(Action.AType.values());
		customizer = ActionCustomizer.getCustomizer(aType, RoboUserMain.o);

		actionTypes.setPreferredSize(new Dimension(100, 30));
		
		//Map buttons
		actionTypes.addActionListener(e -> {changeActionType();});
		
		//Add components
		Utility.addToPanelAtWeighted(this, actionTypes, 0, 0, 1, 0);
		Utility.addToPanelAt(this, customizer, 0, 1);
	}
	
	
	private void changeActionType() {
		changeActionType((AType) actionTypes.getSelectedItem());
	}
	private void changeActionType(Action.AType type) {
		remove(customizer);
		aType = type;
		customizer = ActionCustomizer.getCustomizer(aType, RoboUserMain.o);
		Utility.addToPanelAt(this, customizer, 0, 1);

		validate();
		repaint();
	}
	private void changeActionType(Action a) {
		remove(customizer);
		aType = a.type;
		customizer = ActionCustomizer.getCustomizer(RoboUserMain.o, a);
		Utility.addToPanelAt(this, customizer, 0, 1);

		validate();
		repaint();
	}
	
	public Action getAction() {
		return customizer.getAction();
	}
	
	
	
}
