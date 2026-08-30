import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
    
public class HeapTest {

    @Test
    public void makeHeap_nullArray_shouldCreateEmptyHeap() {
        Heap heap = new Heap();
        heap.MakeHeap(null, 3);
        assertEquals(0, heap.size);
        assertTrue(heap.IsValid());
    }

    @Test
    public void makeHeap_negativeDepth_shouldCreateEmptyHeap() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{1, 2, 3}, -1);
        assertEquals(0, heap.size);
        assertTrue(heap.IsValid());
    }

    @Test
    public void makeHeap_singleElement_shouldBeValid() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{5}, 2);
        assertEquals(1, heap.size);
        assertEquals(5, heap.HeapArray[0]);
        assertTrue(heap.IsValid());
    }

    @Test
    public void makeHeap_unsortedInput_shouldBuildValidMaxHeap() {
        Heap heap = new Heap();
        int[] input = {3, 1, 6, 5, 2, 4};
        heap.MakeHeap(input, 3);
        assertEquals(input.length, heap.size);
        assertTrue(heap.IsValid(), "Построенная куча должна быть валидной");
        assertEquals(6, heap.HeapArray[0], "Корень должен быть максимальным элементом");
    }

    @Test
    public void makeHeap_capacityLimit_shouldTruncateInput() {
        Heap heap = new Heap();
        int[] input = {10, 20, 30, 40, 50};
        heap.MakeHeap(input, 1);
        assertEquals(3, heap.size); 
        assertTrue(heap.IsValid());
        int maxInFirstThree = Math.max(input[0], Math.max(input[1], input[2]));
        assertEquals(maxInFirstThree, heap.HeapArray[0]);
    }

    @Test
    public void add_toEmptyHeap_shouldWork() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[0], 3);
        assertTrue(heap.Add(10));
        assertEquals(1, heap.size);
        assertEquals(10, heap.HeapArray[0]);
        assertTrue(heap.IsValid());
    }

    @Test
    public void add_multipleElements_shouldMaintainHeapProperty() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[0], 4);
        assertTrue(heap.Add(5));
        assertTrue(heap.Add(10));
        assertTrue(heap.Add(3));
        assertTrue(heap.Add(20));
        assertTrue(heap.Add(7));

        assertEquals(5, heap.size);
        assertTrue(heap.IsValid());
        assertEquals(20, heap.HeapArray[0]);
    }

    @Test
    public void add_negativeKey_shouldFail() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[0], 3);
        assertFalse(heap.Add(-1));
        assertEquals(0, heap.size);
    }

    @Test
    public void add_whenFull_shouldFail() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[0], 1);
        assertTrue(heap.Add(1));
        assertTrue(heap.Add(2));
        assertTrue(heap.Add(3));
        assertFalse(heap.Add(4));
        assertEquals(3, heap.size);
        assertTrue(heap.IsValid());
    }

    @Test
    public void getMax_emptyHeap_shouldReturnMinusOne() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[0], 2);
        assertEquals(-1, heap.GetMax());
    }

    @Test
    public void getMax_singleElement_shouldReturnThatElement() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{42}, 2);
        assertEquals(42, heap.GetMax());
        assertEquals(0, heap.size);
        assertTrue(heap.IsValid());
    }

    @Test
    public void getMax_multipleElements_shouldRemoveMaxAndRebuild() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{3, 1, 6, 5, 2}, 3);
        int max = heap.GetMax();
        assertEquals(6, max);
        assertEquals(4, heap.size);
        assertTrue(heap.IsValid());
        assertEquals(5, heap.HeapArray[0]);
    }

    @Test
    public void getMax_sequence_shouldExtractElementsInDescendingOrder() {
        Heap heap = new Heap();
        int[] input = {7, 3, 9, 1, 5};
        heap.MakeHeap(input, 3);

        int prev = Integer.MAX_VALUE;
        while (heap.size > 0) {
            int current = heap.GetMax();
            assertTrue(current <= prev);
            prev = current;
        }
        assertEquals(0, heap.size);
    }

    @Test
    public void isValid_onValidHeap_shouldReturnTrue() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{10, 20, 15, 30, 25}, 3);
        assertTrue(heap.IsValid());
    }

    @Test
    public void isValid_manuallyBrokenHeap_shouldReturnFalse() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{100, 50, 60}, 2); 
        assertTrue(heap.IsValid());

        heap.HeapArray[0] = 1;
        assertFalse(heap.IsValid());
    }

    @Test
    public void mixedOperations_addGetMaxAdd_shouldMaintainValidity() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[0], 4);

        assertTrue(heap.Add(10));
        assertTrue(heap.Add(20));
        assertTrue(heap.Add(5));
        assertTrue(heap.IsValid());

        assertEquals(20, heap.GetMax());
        assertTrue(heap.IsValid());

        assertTrue(heap.Add(30));
        assertTrue(heap.IsValid());
        assertEquals(30, heap.HeapArray[0]);
    }
}
    
