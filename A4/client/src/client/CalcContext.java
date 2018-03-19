package client;

import javax.swing.*;

import com.sun.glass.ui.SystemClipboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import client.states.*;
import java.util.*;
import javax.script.*;


public class CalcContext implements ActionListener{

	private State current = new Start(this);
	private CalculatorView view;
	private String firstop = "";
	private String secop = "";
	private String operand;
	private ArrayList<String> operation = new ArrayList<String>();
	private ArrayList<String> currentOP = new ArrayList<String>();
	
	CalcContext(){}
	
	public void setCalculator(CalculatorView calc) { view = calc;}

	
	public void setState(State state){
		this.current = state;
	}
	
	public State getState(){
		return this.current;
	}
	
	public String getFirst(){ return this.firstop; }
	public String getSecond() {return this.secop; }
	public void setFirst(String first){ this.firstop = first;}
	public void setSecond(String second) { this.secop = second;}
	
	
	
	public CalculatorView getView(){
		return this.view;
	}
	
	
	public void actionPerformed(ActionEvent e)
	 {
		  String value = ((JButton)e.getSource()).getText();
		  
		  
		  if(("*+-/".contains(value)) && isNotNull(firstop, secop, operand)){
			  
			  System.out.println("Given multiple commands");
			  
			  if(operation.isEmpty()){
				  
			  operation.addAll(Arrays.asList(firstop,operand,secop));
			  currentOP.addAll(Arrays.asList(firstop, operand,secop));
			  
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
			  secop = "";

		}
		  
		  this.current.nextState(e);
		  
		  if(this.current instanceof NextOperand){
			  this.operand = value;
		  }		  
		  else if(current instanceof Calculate){
			  System.out.println("calc");

			  if(!operation.isEmpty()){
				  operation.addAll(Arrays.asList(operand,secop));
				  currentOP.addAll(Arrays.asList(operand,secop));
				  
			  }
			  else {
				  operation.addAll(Arrays.asList(firstop,operand,secop));
				  currentOP.addAll(Arrays.asList(firstop, operand,secop));
			  }
			 
			
			  calculate();
	
			  operation.removeAll(operation);
			  currentOP.removeAll(currentOP);
			  firstop="";
			  secop="";
			  operand=null;
		  }
		  
	 }
	
	private boolean isNotNull(String firstop, String secop, String operand){
		if((firstop != "" && secop != "" ) && operand != null)
			return true;
		else
			return false;
	}
	
	public void calculate(){
		
		System.out.println("Calculate");
		double total=0;
		
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
						
			currentOP.set(index, Double.toString(total));
						currentOP.remove(index+1);
						currentOP.remove(index+1);

						
		System.out.println("total : " + total);
		view.updateResult(Double.toString(total));
	}

}
