package client.states;
import java.awt.event.ActionEvent;

import client.CalcContext;
import client.State;
import javax.swing.*;

public class Start extends State{

	public Start(CalcContext context) {
		super(context);
	}

	@Override
	public void nextState(ActionEvent e) {
		
		String button = e.getActionCommand();
		
		
		System.out.println("STARTOPERNAD");
		System.out.println(button);
		
		switch(button) {
		
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "0":
			this.context.setFirst(button);
			this.context.getView().updateResult(button);
			this.context.setState(new FirstOperand(this.context));
			break;
		default:
			this.context.setState(new Start(this.context));
			this.context.getView().updateResult("");
			break;
		
		}
		
	}

}
