package client.visitor;
import client.Visitor;

public interface Element {
	public void accept(Visitor v);
}
