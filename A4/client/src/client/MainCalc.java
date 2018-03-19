package client;
import java.awt.event.ActionListener;
import javax.swing.*;
import client.states.*;
import java.io.*;
import java.net.*;

public class MainCalc {

	public static void main(String[] args) throws IOException {
		
		CalcContext context = new CalcContext();
		CalculatorView view = new CalculatorView();
		context.setCalculator(view);
		view.attachListener(context);	
	}

	
}
