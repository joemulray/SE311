package client;
import java.io.*;

public class MainCalc {

	public static void main(String[] args) throws IOException {
		
		CalcContext context = new CalcContext();
		CalculatorView view = new CalculatorView();
		context.setCalculator(view);
		view.attachListener(context);	
	}

	
}
