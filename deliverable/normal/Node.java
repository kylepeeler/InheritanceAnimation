import java.io.Serializable;

/**
 * Created by Kyle Peeler on 3/31/16.
 */
public class Node implements Serializable {

	//previous element
	private Node prev;
	//next element
	private Node next;
	//ascii frame text
	private String text;

	//ascii frame text getter
	public String getText() {
		return text;
	}

	//ascii frame text setter
	public void setText(String text) {
		this.text = text;
	}

	//previous linked list node getter
	public Node getPrev() {
		return prev;
	}

	//previous linked list node setter
	public void setPrev(Node prev) {
		this.prev = prev;
	}

	//next linked list node getter
	public Node getNext() {
		return next;
	}

	//next linked list node setter
	public void setNext(Node next) {
		this.next = next;
	}
}
