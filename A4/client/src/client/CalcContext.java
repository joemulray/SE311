package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import client.states.*;
import client.visitor.*;
import java.util.*;
import java.net.*;
import java.io.*;


public class CalcContext implements ActionListener{

	private State currentState = new Start(this);
	private CalculatorView view;

	private ArrayList<Operation> current = new ArrayList<Operation>();
	private ArrayList<Operation> operation = new ArrayList<Operation>();
	
	private Operation firstoperand = new Operand();
	private Operation secoperand = new Operand();
	private Operation operator = new Operator();
	
	
	public void setCalculator(CalculatorView calc) { view = calc;}

	
	public void setState(State state){
		this.currentState = state;
	}
	
	public State getState(){
		return this.currentState;
	}
	
	public String getFirst(){ return this.firstoperand.getValue(); }
	public String getSecond() {return this.secoperand.getValue(); }
	public void setFirst(String first){ this.firstoperand.setValue(first); }
	public void setSecond(String second) { this.secoperand.setValue(second);}
	
	
	public CalculatorView getView(){
		return this.view;
	}
	
	
	public void actionPerformed(ActionEvent e)
	 {
		
		  String value = e.getActionCommand();
		  
		  if(("*+-/".contains(value)) && isNotNull(firstoperand, secoperand, operator)){
			  
			if(operation.isEmpty()){
				  
			operation.add(new Operand(firstoperand.getValue()));
			operation.add(new Operator(operator.getValue()));
			operation.add(new Operand(secoperand.getValue()));
			current.addAll(Arrays.asList(firstoperand, operator, secoperand));
			  
			 }
			  else {
				  
				  operation.add(operator);
				  operation.add(secoperand);				  
				  
				  current.add(1,operator);
				  current.add(2, secoperand);
				  
				  
			  }
			  
			  update();
			  firstoperand.setValue(secoperand.getValue());
			  secoperand.setValue("");
		
		  }
		  

		  this.currentState.nextState(e);
		  
		  if(this.currentState instanceof NextOperand){
			  this.operator.setValue(value);
			  
		  }		  
		  else if(currentState instanceof Calculate){

			  if(!operation.isEmpty()){
				  
				  operation.addAll(Arrays.asList(operator, secoperand));
				  current.addAll(Arrays.asList(operator, secoperand));

			  }
			  else {
				  operation.addAll(Arrays.asList(firstoperand,operator,secoperand));
				  current.addAll(Arrays.asList(firstoperand, operator, secoperand));
			  }
			  
			 
			  double calculated = update();
			  
			  Operation equals = new Operator("=");
			  Operation total = new Operand(Double.toString(calculated));
			   
			  operation.addAll(Arrays.asList(equals, total));
			  
			  try {
			  sendtoServer();
			  }
			  catch(Exception error){ System.out.println("ERROR: " + error);}
			  

			  operation.removeAll(operation);
			  current.removeAll(current);
			  firstoperand.setValue("");
			  secoperand.setValue("");
			  operator.setValue(null);
		  }
		  
		  
	 }
	
	private boolean isNotNull(Operation firstoperand, Operation second, Operation operand){
		if((firstoperand.getValue() != "" && second.getValue() != "" ) && operand.getValue() != null)
			return true;
		else
			return false;
	}
	
	public double update(){
		
		double total=0;
		
			double firstdigit = Double.parseDouble(current.get(0).getValue());
			double seconddigit = Double.parseDouble(current.get(2).getValue());
			String operator = current.get(1).getValue();
			
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
			current.set(0, updated);
			current.remove(1);
			current.remove(1);
						
						
		view.updateResult(Double.toString(total));
		return total;
	}
	
	
	public void sendtoServer() throws UnknownHostException, IOException{
		
		ArrayList<String> stringOperations = new ArrayList<String>();
		Socket socket = new Socket("localhost", 8080);
		OutputStream os = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		
		for(Operation op: operation) {
			stringOperations.add(op.getValue());
			System.out.println(op.getValue());
		}
		
		oos.writeObject(stringOperations);
		os.close();
		oos.close();
		socket.close();
		
	}
	
}