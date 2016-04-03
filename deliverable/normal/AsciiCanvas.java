import java.awt.Font;
import java.io.*;
import javax.swing.*;

/**
 * Created by Kyle on 4/2/16.
 */
public class AsciiCanvas extends JTextArea {
	//create a new linked list
	private Node currentNode;
	private Node head;

	//AsciiCanvas constructor
	//creates an instance of AsciiCanvas with
	//a monospaced font and line wrapping
	public AsciiCanvas() {
		//creates a new linked list
		head = new Node();
		//set the current node to the head since we just created the
		//linked list
		currentNode = head;
		//set the properties of the JTextArea
		this.setLineWrap(true);
		this.setFont(new Font("monospaced", Font.PLAIN, 16));
	}

	public void prevFrame(){
		//when we click the previous button, make sure we save the frame
		this.currentNode.setText(this.getText());
		//if we are at the beginning of the linked list, don't do anything
		if (this.currentNode == this.head){
			return;
		}else{
			//otherwise, we are not at the beginning of the linked list,
			//we load the previous node
			this.currentNode = this.currentNode.getPrev();
		}
		//load the text of the previous frame
		this.setText(this.currentNode.getText());
	}

	public void nextFrame(){
		//when we click the next button, make sure we save the frame
		this.currentNode.setText(this.getText());
		//if we getNext returns null, we are at the end of the linked list
		if (this.currentNode.getNext() == null){
			//because we are at the end, create a new node
			this.currentNode.setNext(new Node());
			//we just created a new node, so set the previous of the new node to the last node
			this.currentNode.getNext().setPrev(this.currentNode);
			//access the next node of the linked list
			this.currentNode = this.currentNode.getNext();
		}else{
			//access the next node of the linked list
			this.currentNode = this.currentNode.getNext();
		}
		//update the text area with the current node's text
		this.setText(this.currentNode.getText());
	}

	public void save(){
		//when we click the save button, make sure we save the frame
		this.currentNode.setText(this.getText());
		//try to save the file
		try{
			//save the file as asciianim.ser in the current directory
			FileOutputStream fileOut = new FileOutputStream("asciianim.ser");
			//serialize the object and save it to the fileoutputstream fileOut
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			//serialize the head object, that loads the entire animation
			out.writeObject(head);
			//close the streams
			out.close();
			fileOut.close();
			//System.out.println("Serialized data is saved to asciianim.ser");
		}catch(IOException e){
			//if we got an error, print the error
			e.printStackTrace();
		}
	}

	public void load(){
		try{
			//create a file input stream, load file 'asciianim.ser' in the current directory
			FileInputStream fileIn = new FileInputStream("asciianim.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			//read the file as a Node object and assign it to the head of the linked list
			head = (Node) in.readObject();
			//close the file streams
			in.close();
			fileIn.close();
		}catch(IOException i){
			//catch not finding the file, output to the user to check for the file
			System.out.println("Error 404: Can't find file 'asciianim.ser', check to make sure the file exists and try again.");
			//print the error
			i.printStackTrace();
			return;
		}catch(ClassNotFoundException c){
			//print the error if the Node class doesn't exist for some reason?..
			c.printStackTrace();
			return;
		}
		//set the text from the head node we just loaded
		this.setText(head.getText());
		//set the current node pointer to the head
		this.currentNode = head;
	}

	public void anim(){
		//when we click the animate button, make sure to save the frame
		this.currentNode.setText(this.getText());
		//if we are at the end of the list, start back at the beginning
		if (this.currentNode.getNext() == null){
			//starting over, so set the currentNode pointer to be the head
			this.currentNode = head;
			//load the currentNodes text into the TextArea
			this.setText(this.currentNode.getText());
		}else{
			//if we are not at the end of the list, move the currentNode pointer to the next frame
			this.currentNode = this.currentNode.getNext();
			//update the text area with the new frame's text
			this.setText(this.currentNode.getText());
		}
	}
}
