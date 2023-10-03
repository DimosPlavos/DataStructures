import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueImpl<T> implements StringQueue<T> {
    private int size;
    private Node<T> head;
    private Node <T> tail;

    public boolean isEmpty(){
        if (size==0)return true;
        return false;
    }

    public void put(T item){
        Node<T> temp = tail;
		tail = new Node<>(item);
		if (isEmpty()){
			head=tail;
		}else{
			temp.setNext(tail);
		}
		size++;
    }

    public T get() throws NoSuchElementException{
        if (isEmpty()) throw new NoSuchElementException();
        else{
            if (size==1) tail = null; //tail points to null, because it will be empty after the extraction
            //basic operations
            T returnedValue = head.getData();
            head = head.getNext();
            size--;

            //return the value of the last item
            return returnedValue;
        }
    }

    public T peek() throws NoSuchElementException{
        if (isEmpty()) throw new NoSuchElementException();
        else{
            return head.getData();
        }
    }

    public void printQueue(PrintStream stream){
        if (isEmpty()) stream.println("The queue is Empty");
        else{
            Node<T> temp = head;
            while (temp != null){
                stream.println(temp.getData() + " ");
                temp = temp.next; 
           } 
        }
    }


    public int size(){
        return size;
    }

    //--------------------------------TESTING--------------------------------//

    // public static void main(String[] args) {
    //     System.out.println("hi");
    //     StringQueueImpl<Integer> oura = new StringQueueImpl<>();

    //     System.out.println("SIZE:");
    //     oura.put(5);
    //     System.out.println(oura.size());
    //     oura.put(10);
    //     System.out.println(oura.size());
    //     oura.put(15);
    //     System.out.println(oura.size());
    //     oura.put(20);
    //     System.out.println(oura.size());
    //     oura.put(25);
    //     System.out.println(oura.size());
    //     System.out.println("----------------------");
    //     oura.printQueue(System.out);
    //     System.out.println("----------------------");
    //     System.out.println(oura.get());
    //     System.out.println(oura.size());
    //     System.out.println(oura.get());
    //     System.out.println("----------------------");
    //     System.out.println("PEEK: ");
    //     System.out.println(oura.peek());
    //     System.out.println(oura.peek());
    //     System.out.println("----------------------");
    //     oura.printQueue(System.out);

    // }
}


