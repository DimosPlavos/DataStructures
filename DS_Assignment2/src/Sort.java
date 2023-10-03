//Ylopoihsh Heapsort

public class Sort {
	
	public void heapify (List<Integer> heap,int curr,int size) {
		int smallest = curr;
		int l = 2*curr + 1;
		int r = 2*curr +2;
		
		if (l<size && heap.get(l)<heap.get(smallest))
			smallest = l;
		if (r<size && heap.get(r)<heap.get(smallest))
			smallest = r;
		if (smallest!=curr) {
			int temp = heap.get(curr);
            heap.set(curr, heap.get(smallest));
			heap.set(smallest,temp);
			
			heapify(heap,smallest,size);
		} 
	}
	
	public List<Integer> heapSort(List<Integer> heap) {
		for (int i = heap.size()/2-1;i>=0;i--) {
			heapify(heap,i,heap.size());
		}
		for (int i=heap.size()-1;i>0;--i){
			int temp = heap.get(0);
			heap.set(0,heap.get(i));
			heap.set(i, temp);
			
			heapify(heap,0,i);
		}
		return heap;
	}
	

}