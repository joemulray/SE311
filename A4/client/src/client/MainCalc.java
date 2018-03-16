package client;
import java.awt.event.ActionListener;
import javax.swing.*;
import client.states.*;

public class MainCalc {

	public static void main(String[] args) {
		
		CalcState start = new Start();
		CalcContext context = new CalcContext();
		CalculatorView calculator = new CalculatorView();
		context.setCalculator(calculator);
		
		calculator.attachListener(context);
		
		
	}
	
	
	
}
