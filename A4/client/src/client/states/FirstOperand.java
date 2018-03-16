package client.states;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import client.CalcState;

public class FirstOperand implements CalcState{

	@Override
	public CalcState nextState(ActionEvent e) {
		
		String button = ((JButton)e.getSource()).getText();
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
			return new FirstOperand();
		case "+":
		case "-":
		case "/":
		case "*":
			return new NextOperand();
		case "C":
			return new Start();
			
		default:
			return new Error();
		
		}
		
		
	}


}
