package client;

import client.visitor.Operand;
import client.visitor.Operation;
import client.visitor.Operator;

public class CalcVisitor implements Visitor{

	@Override
	public void visitOperator(Operation operation) {
//		Operand firstOP = operation.firstOP;
//		Operand secOP = operation.secOP;
		System.out.println("VISITOPEATOR");
		
		
	}

	@Override
	public void visitOperand(Operation operation) {
		// TODO Auto-generated method stub
		System.out.println("VISITOPERAND");
	}

}
