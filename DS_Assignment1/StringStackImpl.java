import java.io.PrintStream;
import java.util.NoSuchElementException;


public class StringStackImpl<T> implements StringStack<T> {

    private int size;
    private Node<T> head;

    public void push(T item){
        Node<T> temp = head;
        head = new Node<>(item); 
        head.setNext(temp);

        size++;
    }

    public T pop() throws NoSuchElementException{
        //throw the exception if the stack is empty
        if (isEmpty()) throw new NoSuchElementException(); 

        //basic operations 
        T returnedValue = head.getData();
        head = head.getNext();
        size--;

        //return the String value of the head 
        return returnedValue;
    }
    
    public T peek() throws NoSuchElementException{
        //throw the exception if the stack is empty
        if (isEmpty()) throw new NoSuchElementException(); //-----------------------NA TO RWTISW an prepei na efanizei mhnuma h na petaei exception

        //return the data of the head
        return head.getData();
    }

    public void printStack(PrintStream stream){
        if (isEmpty()) stream.println("The stack is Empty"); 
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

    public boolean isEmpty(){
        if (size == 0) return true;
        return false;
    }

    //------------------------------TESTING------------------------------//

    // public static void main(String[] args) {
    //     System.out.println("HI!");
    //     StringStackImpl<Integer> stoiva = new StringStackImpl<>();

    //     System.out.print("Stoiva is empty? ");
    //     System.out.println(stoiva.isEmpty());
    //     stoiva.push(5);
    //     System.out.print("Stoiva size after insertion of 5: ");
    //     System.out.println(stoiva.size());
    //     stoiva.push(10);
    //     stoiva.push(15);
    //     System.out.print("Stoiva size after insertion of 10,15: ");
    //     System.out.println(stoiva.size());
    //     System.out.println("STOIVA : ");
    //     stoiva.printStack(System.out);
    //     System.out.println("Remove the element on top: ");
    //     System.out.println(stoiva.pop());
    //     System.out.print("Stoiva size after extraction of the top element: ");
    //     System.out.println(stoiva.size());
    //     System.out.print("Top element now: ");
    //     System.out.println(stoiva.peek());
    //     System.out.print("Stoiva size after just peeking: ");
    //     System.out.println(stoiva.size());
    // }
}

