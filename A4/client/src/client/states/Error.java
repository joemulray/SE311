package client.states;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import client.CalcContext;
import client.State;

public class Error extends State{

	public Error(CalcContext context) {
		super(context);
	}

	@Override
	public void nextState(ActionEvent e) {

		String button = ((JButton)e.getSource()).getText();
		switch(button) {
		
		case "C":
			this.context.setFirst("");
			this.context.setSecond("");
			this.context.getView().updateResult("");
			this.context.setState(new Start(this.context));
			break;
			
		default:
			this.context.setState(new Error(this.context));
			break;
		
		}
		
	}

}
