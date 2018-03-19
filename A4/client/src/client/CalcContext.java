package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import client.states.*;
import client.visitor.*;
import java.util.*;
import java.net.*;
import java.io.*;


public class CalcContext implements ActionListener{

	private State current = new Start(this);
	private CalculatorView view;

	private ArrayList<Operation> currentoperation = new ArrayList<Operation>();
	private ArrayList<Operation> operation = new ArrayList<Operation>();
	
	private Operation firstoperation = new Operand();
	private Operation secondoperation = new Operand();
	private Operation operator = new Operator();
	
	
	
	CalcContext(){}
	
	public void setCalculator(CalculatorView calc) { view = calc;}

	
	public void setState(State state){
		this.current = state;
	}
	
	public State getState(){
		return this.current;
	}
	
	public String getFirst(){ return this.firstoperation.getValue(); }
	public String getSecond() {return this.secondoperation.getValue(); }
	public void setFirst(String first){ this.firstoperation.setValue(first); }
	public void setSecond(String second) { this.secondoperation.setValue(second);}
	
	
	public CalculatorView getView(){
		return this.view;
	}
	
	
	public void actionPerformed(ActionEvent e)
	 {
		  String value = e.getActionCommand();
		  
		  if(("*+-/".contains(value)) && isNotNull(firstoperation.getValue(), secondoperation.getValue(), operator.getValue())){
			  
			  System.out.println("Given multiple commands");
			  
			  if(operation.isEmpty()){
			
			  operation.addAll(Arrays.asList(firstoperation ,operator,secondoperation));
			  currentoperation.addAll(Arrays.asList(firstoperation, operator, secondoperation));
			  
			  }
			  else {
				  System.out.println("not is isemptyy");
				  operation.add(operator);
				  operation.add(secondoperation);
				  
				  currentoperation.add(1,operator);
				  currentoperation.add(2, secondoperation);
			  }
			  
			  
			  calculate();
			  firstoperation.setValue(secondoperation.getValue());
			  secondoperation.setValue("");
		
		  }
		  
		  this.current.nextState(e);
		  
		  if(this.current instanceof NextOperand){
			  this.operator.setValue(value);
			  
		  }		  
		  else if(current instanceof Calculate){
			  System.out.println("calc");

			  if(!operation.isEmpty()){
				  operation.addAll(Arrays.asList(operator,secondoperation));
				  currentoperation.addAll(Arrays.asList(operator, secondoperation));

			  }
			  else {
				  operation.addAll(Arrays.asList(firstoperation,operator,secondoperation));
				  currentoperation.addAll(Arrays.asList(firstoperation, operator, secondoperation));
			  }
			 
			
			  double calculated = calculate();
			  System.out.println("CALCAFTER");
			  System.out.println(calculated);
			  
			  for(Operation op: operation)
				  System.out.print(" " + op.getValue());
			  
			  Operation equals = new Operator("=");
			  Operation total = new Operand(Double.toString(calculated));
			  
			  operation.addAll(Arrays.asList(equals, total));
			  
			  operation.removeAll(operation);
			  currentoperation.removeAll(currentoperation);
			  firstoperation.setValue("");
			  secondoperation.setValue("");
			  operator.setValue(null);
		  }
		  
	 }
	
	private boolean isNotNull( String firstop, String secop, String operand){
		if((firstop != "" && secop != "" ) && operand != null)
			return true;
		else
			return false;
	}
	
	public double calculate(){
		
		double total=0;
		
			double firstdigit = Double.parseDouble(currentoperation.get(0).getValue());
			double seconddigit = Double.parseDouble(currentoperation.get(2).getValue());
			String operator = currentoperation.get(1).getValue();
			
			switch(operator){
				case "+":
					total = firstdigit + seconddigit;
					break;
				case "-":
					total = firstdigit - seconddigit;
					break;
				case "*":
					total = firstdigit * seconddigit;
					break;
				case "/":
					total = firstdigit / seconddigit;
				default:
					break;
			}
				
			Operation updated = new Operand(Double.toString(total));
			currentoperation.set(0, updated);
			currentoperation.remove(1);
			currentoperation.remove(1);
						
						
		System.out.println("total : " + total);
		view.updateResult(Double.toString(total));
		return total;
	}
	
	
}
