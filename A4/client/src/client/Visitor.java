package client;
import client.visitor.Operand;
import client.visitor.Operator;

public interface Visitor {
	public void visitOperator(Operator operation);
	public void visitOperand(Operand operation);
	
}
