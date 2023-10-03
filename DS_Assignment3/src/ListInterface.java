public interface ListInterface<T> {
   
    //Inserts the data at the front of the list
    void add(T data);

    //returns true if the list is empty
    boolean isEmpty();

    //returns the size of the list
    int size();

    //returns the value of the index i of the list
    T get(int index);

    //set the index i to take the value 
    void set(int i, T value);
}