package client;

import java.awt.event.ActionEvent;

public abstract class State {
	protected CalcContext context;
	
	public State(CalcContext context){
		this.context = context;
	}
	
	public void nextState(ActionEvent e) {}
	
}
