public class Node<T> {
    protected T data;
    protected Node<T> next = null;

    Node(T data) {
        this.data = data;
    }

    // return data stored in this node
    public T getData() {
        return data;
    }

    //set data of a Node to take the value d
    public void setData(T d){
        this.data = d;
    }

    // get next node
    public Node<T> getNext() {
        return next;
    }

    //set the next Node
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
