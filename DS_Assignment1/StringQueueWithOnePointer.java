import java.util.NoSuchElementException;
import java.io.PrintStream;

/**
 * StringQueueWithOnePointer
 */
public class StringQueueWithOnePointer<T>{
    private int size;
    private Node<T> tail;
    
    public boolean isEmpty(){
        if (tail==null)return true;
        return false;
    }

    public void put(T value){
        Node<T> temp = new Node<>(value);
        if (tail==null) {
            temp.next = temp;
            tail=temp;
        }else{
            temp.next = tail.next;
            tail.next=temp;
            tail=temp;
            
        }
        size++;
    }
    

    public T pop() throws NoSuchElementException{
        if (isEmpty()) throw new NoSuchElementException();
        else{
            if (size==1) tail = null; //head points to null, because it will be empty after the extraction
            //basic operations
            T returnedValue = tail.next.getData();
            tail.next = tail.next.next;
            size--;

            //return the value of the last item
            return returnedValue;
        }
    }

    public void remove(Node<T> x) {
        x.setNext(x.getNext().getNext());
    } 

    public T peek() throws NoSuchElementException{
        if (isEmpty()) throw new NoSuchElementException();
        else{
            return tail.next.getData();
        }
    }

    public void printQueue(PrintStream stream){
        if (isEmpty()) stream.println("The queue is Empty");
        else{
            Node<T> temp = tail;
            do{
                stream.println(temp.next.getData() + " ");
                temp = temp.getNext();
            }while (temp != tail);                                               
        }
    }

    public int size(){
        return size;
    }

        //-----------------------------------TESTING-----------------------------------//

        // public static void main(String[] args) {
        //     StringQueueWithOnePointer<Integer> oura = new StringQueueWithOnePointer<>();
        //     Node <Integer> x = null;
        //     oura.put(5);
        //     System.out.println("EKTYPWSE OYRA: ");
        //     oura.printQueue(System.out);
        //     oura.put(10);
        //     System.out.println("EKTYPWSE OYRA: ");
        //     oura.printQueue(System.out);
        //     oura.put(15);
        //     System.out.println("EKTYPWSE OYRA: ");
        //     oura.printQueue(System.out);
        //     oura.put(20);
        //     System.out.println("EKTYPWSE OYRA: ");
        //     oura.printQueue(System.out);
        //     oura.put(25);
        //     System.out.println("EKTYPWSE OYRA: ");
        //     oura.printQueue(System.out);
        //     oura.pop();
        //     oura.pop();
        //     oura.put(30);
        //     oura.put(35);
        //     System.out.println("EKTYPWSE OYRA: ");
        //     oura.printQueue(System.out);
        // } 
        
}
