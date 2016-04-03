import java.io.Serializable;

/**
 * Created by Kyle on 4/2/16.
 */
public class Node implements Serializable {

	private Node prev;
	private Node next;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}
