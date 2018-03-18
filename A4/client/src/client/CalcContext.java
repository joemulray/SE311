package client;

import javax.swing.*;

import com.sun.glass.ui.SystemClipboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import client.states.*;
import java.util.*;
import javax.script.*;


public class CalcContext extends JPanel implements ActionListener{

	private CalcState current = new Start();
	private CalculatorView view;
	private String firstop;
	private String secop;
	private String operand;
	private ArrayList<String> operation = new ArrayList<String>();
	private ArrayList<String> currentOP = new ArrayList<String>();
	
	CalcContext(){}
	
	public void setCalculator(CalculatorView calc) { view = calc;}

	
	public void actionPerformed(ActionEvent e)
	 {
		  String value = ((JButton)e.getSource()).getText();
		  
//		  System.out.println("current: " + value);
//		  System.out.println(firstop);
//		  System.out.println(secop);
//		  System.out.println(operand);
//		  
		  System.out.println("BEFORE");
		  
		  //given multiple operands
		  if(("*+-/".contains(value)) && isNotNull(firstop, secop, operand)){
			  
			  System.out.println("Given multiple commands");
			  
			  if(operation.isEmpty()){
				  
				  System.out.println("isemptyy");
			  operation.add(firstop);
			  operation.add(operand);
			  operation.add(secop);
			  
			  currentOP.add(firstop);
			  currentOP.add(operand);
			  currentOP.add(secop);
			  }
			  else {
				  System.out.println("not is isemptyy");
				  operation.add(operand);
				  operation.add(secop);
				  
				  currentOP.add(1, operand);
				  currentOP.add(2,secop);
			  }
			  
			  
			  calculate();
			  firstop = secop;
			  secop = null;

		}
		  
		  if(current instanceof FirstOperand){
			  if(firstop == null)
				  firstop = value;
			  else if("*+=-/".contains(value)){}
				 
			  else
				  firstop +=value; 
			  
		  }
		  else if(current instanceof SecOperand) {
			  
			  if("*+=-/".contains(value)){}
			  else if(secop == null)
				  secop = value;
			  else
				  secop +=value; 
		  }

		  //get the next state
		  current = current.nextState(e);
		  
		  if(current instanceof Start) {
			  System.out.println("start");
			  view.updateResult("0");
			  firstop = null;
			  secop = null;
		  }
			  
		  else if(current instanceof FirstOperand){  
			  System.out.println("first");
			  if(firstop == null) {
				  firstop = value;
				  view.updateResult(value);
			  }
			  else
				  view.updateResult(firstop);
			  
		  }
		  else if(current instanceof NextOperand) {
			  System.out.println("next");
			  	operand = value;
			  	System.out.println(value);
			  	System.out.println(firstop);
			  	System.out.println(secop);
			  	
//			  	if(secop == null)
//			  	view.updateResult(firstop);
//			  	else
			  		//view.updateResult(secop);
			  	
			  	
		  }
		  else if(current instanceof SecOperand) {
			  System.out.println("secoind operand");
			  if(secop == null) {
				  view.updateResult(value);
				  secop=value;
			  }
			  
			  else
				  view.updateResult(secop);
		  }
		  else if(current instanceof Calculate){
			  System.out.println("calc");

			  if(!operation.isEmpty()){
				  operation.add(operand);
				  operation.add(secop);
				  
				  currentOP.add(operand);
				  currentOP.add(secop);
				  
			  }
			  else {
				  operation.add(firstop);
				  operation.add(operand);
				  operation.add(secop);
				  
				  currentOP.add(firstop);
				  currentOP.add(operand);
				  currentOP.add(secop);
			  }
			 
			  String foo="";
			  
			  for(String val : operation) {
				 foo+=val;
				 System.out.print(val + " ");
			  }
			  
			 
			  calculate();
			  System.out.println("");
			  
			  for(String val : operation) {
				 foo+=val;
				 System.out.print(val + " ");
			  }
			
			    operation.removeAll(operation);
			    currentOP.removeAll(currentOP);
			    firstop=null;
			    secop=null;
			    operand=null;
			  
		  }
		  
		  else {
			  view.updateResult("ERR");
			  firstop = null;
			  secop = null;
		  }
		  
	 }
	
	private boolean isNotNull(String firstop, String secop, String operand){
		if((firstop != null && secop != null ) && operand != null)
			return true;
		else
			return false;
	}
	
	public void calculate(){
		
		System.out.println("Calculate");
		double total=0;
		
//		try {
			int index =0;
			double firstDigit = Double.parseDouble(currentOP.get(index));
			String operand = currentOP.get(index+1);
			double secDigit = Double.parseDouble(currentOP.get(index+2));
				
			switch(operand){
				case "+":
					total = firstDigit + secDigit;
					break;
				case "-":
					total = firstDigit - secDigit;
					break;
					
				case "*":
					total = firstDigit * secDigit;
					break;
				case "/":
					total = firstDigit / secDigit;
					
				default:
					break;
	
			}
			
			for(String val: currentOP)
				System.out.println("val: " + val);
			
			currentOP.set(index, Double.toString(total));
						currentOP.remove(index+1);
						currentOP.remove(index+1);
//						currentOP.set(1, null);
//						currentOP.set(2, null);
//						currentOP.set(index, Double.toString(total));
			
		
//		}catch(Exception e){System.out.println("Exception" + e);}

		System.out.println("total : " + total);
		view.updateResult(Double.toString(total));
	}
	
	
	
}
