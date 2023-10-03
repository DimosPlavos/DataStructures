
/**
 * Single-link List. Uses {@link Node} for list nodes.
 */
public class List<T> implements ListInterface<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size;

    //Default constructor
    public List() {
    }

    
    public boolean isEmpty() {
        return head == null;
    }

    public void add(T data) {
        Node<T> n = new Node<>(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            tail.setNext(n);
            tail = n;
        }
        size++;
    }

    public String toString() {
        if (isEmpty()) {
            return "List is empty :(";
        }

        Node<T> current = head;

        StringBuilder ret = new StringBuilder();

        while (current != null) {
            ret.append(current.getData().toString() + " ");
            current = current.getNext();
        }
        return ret.toString();
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        T returnedvalue=null;
        if (isEmpty()) {
            return null;
        }else{
            Node<T> current = head;
            int k=0;
            while (current != null) {
                if (index==k){
                    returnedvalue = current.getData();
                }
                k++;
                current = current.getNext();
            }
            return returnedvalue;
        }
    }

    public void set(int i, T value) {
        Node<T> current = head;
            int k=0;
            while (current != null) {
                if (i==k){
                    current.setData(value);
                }
                k++;
                current = current.getNext();
            }     
    }
    
}
