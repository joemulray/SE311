package client;

import java.awt.event.ActionEvent;

public interface CalcState {
	CalcState nextState(ActionEvent e);
	
}
