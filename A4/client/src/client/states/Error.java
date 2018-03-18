package client.states;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import client.CalcState;

public class Error implements CalcState{

	@Override
	public CalcState nextState(ActionEvent e) {

		String button = ((JButton)e.getSource()).getText();
		switch(button) {
		
		case "C":
			return new Start();
			
		default:
			return new Error();
		
		}
		
	}

}
