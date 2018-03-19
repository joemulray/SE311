package client;
import client.visitor.Operand;
import client.visitor.Operation;
import client.visitor.Operator;

public interface Visitor {
	public void visitOperator(Operation operation);
	public void visitOperand(Operation operation);
	
}
