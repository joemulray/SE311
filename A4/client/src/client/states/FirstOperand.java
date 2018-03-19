package client.states;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import client.State;
import client.CalcContext;
import client.CalculatorView;

public class FirstOperand extends State{

	public FirstOperand(CalcContext context) {
		super(context);
	}

	@Override
	public void nextState(ActionEvent e) {
		
		String button = e.getActionCommand();
		System.out.println("FIRSTOPERNAD");
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
			this.context.getView().updateResult(context.getFirst() + button);
			this.context.setFirst(context.getFirst() + button);
			break;
		case "+":
		case "-":
		case "/":
		case "*":
			this.context.setState(new NextOperand(this.context));
			break;
		case "C":
			this.context.setFirst("");
			this.context.setSecond("");
			this.context.getView().updateResult("");
			this.context.setState(new Start(this.context));
			break;
			
		default:
			this.context.setState(new Error(this.context));
			this.context.getView().updateResult("ERR");
			 break;
		
		}
		
		
	}


}
