package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import client.states.*;

public class CalcContext extends JPanel implements ActionListener{

	private CalcState current = new Start();
	private CalculatorView view;
	private String firstop;
	private String secop;
	private String operand;
	
	CalcContext(){}
	
	public void setCalculator(CalculatorView calc) { view = calc;}

	
	public void actionPerformed(ActionEvent e)
	 {
		
		  //view.updateResult( ((JButton)e.getSource()).getText());
		  String value = ((JButton)e.getSource()).getText();
		  System.out.println( ((JButton)e.getSource()).getText());
		  
		  if(current instanceof FirstOperand){
			  System.out.println("CURRENT INSTANCE OF FIRSTOP");
			  if(firstop == null)
				  firstop = value;
			  else
				  firstop +=value; 
			  
		  }
		  else if(current instanceof SecOperand) {
			  if(secop == null)
				  secop = value;
			  else
				  secop +=value; 
		  }
		  
		  //get the next state
		  current = current.nextState(e);
		  
		  if(current instanceof Start) {
			  view.updateResult("0");
			  firstop = null;
			  secop = null;
		  }
			  
		  else if(current instanceof FirstOperand){
			  
			  if(firstop == null) {
				  firstop = value;
				  view.updateResult(value);
			  }
			  else
				  view.updateResult(firstop);
			  
		  }
		  else if(current instanceof NextOperand) {
			  	view.updateResult(firstop);
		  }
		  else if(current instanceof SecOperand) {
			  
			  if(value == null)
				  view.updateResult(value);
			  else
				  view.updateResult(secop);
		  }	
		  else {
			  view.updateResult("ERR");
			  firstop = null;
			  secop = null;
		  }
		  
	 }
}
