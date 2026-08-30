import java.util.*;

class Heap
{
    public int [] HeapArray;
    int size;
		
    public Heap() 
    { 
        HeapArray = null;
        size = 0;

    }
		
    public void MakeHeap(int[] a, int depth)
    {
        if (a == null || depth < 0) {
            HeapArray = new int[0];
            size = 0;
            return;
        }

        int capacity = (1 << (depth + 1)) - 1;

        HeapArray = new int[capacity];
        size = Math.min(a.length, capacity);

        for (int i = 0; i < size; i++) {
            HeapArray[i] = a[i];
        }

        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            if (left < size && HeapArray[left] > HeapArray[largest]) {
                largest = left;
            }
            if (right < size && HeapArray[right] > HeapArray[largest]) {
                largest = right;
            }

            if (largest == i) {
                break; 
            }

            swap(i, largest);
            i = largest;
        }
    }

    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (HeapArray[parent] >= HeapArray[i]) {
                break; 
            }
            swap(parent, i);
            i = parent;
        }
    }

    private void swap(int i, int j) {
        int tmp = HeapArray[i];
        HeapArray[i] = HeapArray[j];
        HeapArray[j] = tmp;
    }
		
    public int GetMax()
    {
      
    if (size != 0 && HeapArray != null) {
            return gMax();
        }
	return -1; 
    }

    private int gMax(){
        int max = HeapArray[0];

        HeapArray[0] = HeapArray[size - 1];
        size--;

        if (size > 0) {
            siftDown(0);
        }
        return max;
    }

    public boolean Add(int key)
    {
        if(key >= 0 && (HeapArray != null && size < HeapArray.length)){
            HeapArray[size] = key;
            int index = size;
            
            siftUp(index);
            size++;
            return true;
        }
        return false; 
    }

    public boolean IsValid() {
        if (HeapArray == null || size <= 1) {
            return true; 
        }

        for (int i = 0; i < size; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && HeapArray[i] < HeapArray[left]) {
                return false; 
            }

            if (right < size && HeapArray[i] < HeapArray[right]) {
                return false; 
            }
        }

        return true;
    }
	
}
