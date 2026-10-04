package problems;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Week 1 Foundation: Dynamic Array Implementation from Scratch.
 * 
 * Key Learning Concepts for TypeScript -> Java Transition:
 * 1. Generics Type Erasure: Why 'new T[]' fails in Java and how (T[]) new Object[] is used.
 * 2. Amortized O(1) Growth: Why geometric doubling (2x) achieves amortized O(1) per append,
 *    whereas arithmetic expansion (+k) yields quadratic O(N^2) total cost.
 * 3. Memory Leaks / Loitering in GC: Why removing an element must null-out the old slot.
 * 4. System.arraycopy: Hardware-accelerated memory-block copying in the JVM.
 */
public class MyArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    
    // Backing array holding references to elements
    @SuppressWarnings("unchecked")
    private T[] data;
    
    // Actual number of elements currently stored in the array
    private int size;

    /**
     * Initializes the dynamic array with default capacity (10).
     */
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initializes the dynamic array with a specified initial capacity.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initialCapacity);
        }
        // In Java, generic type parameters are erased at runtime.
        // Therefore, 'new T[initialCapacity]' causes a compile error.
        // The standard JVM solution (used in java.util.ArrayList) is to allocate Object[]
        // and safely cast to T[].
        this.data = (T[]) new Object[initialCapacity];
        this.size = 0;
    }

    /**
     * Appends an element to the end of the array.
     * Time Complexity: Amortized O(1), Worst-Case O(N) when resizing.
     */
    public void add(T element) {
        ensureCapacity(size + 1);
        data[size] = element;
        size++;
    }

    /**
     * Inserts an element at a specific index, shifting existing elements to the right.
     * Time Complexity: O(N) due to element shifting.
     */
    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);

        // Shift elements from [index .. size - 1] one slot right
        int numMoved = size - index;
        if (numMoved > 0) {
            System.arraycopy(data, index, data, index + 1, numMoved);
        }

        data[index] = element;
        size++;
    }

    /**
     * Returns the element at the specified index.
     * Time Complexity: O(1) direct memory offset calculation.
     */
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * Replaces the element at the specified index with a new value.
     * Returns the old value previously stored at that index.
     * Time Complexity: O(1).
     */
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = data[index];
        data[index] = element;
        return oldValue;
    }

    /**
     * Removes the element at the specified index, shifting trailing elements left.
     * Time Complexity: O(N).
     */
    public T remove(int index) {
        checkIndex(index);
        T removedValue = data[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }

        // CRITICAL FOR GC (Preventing Object Loitering):
        // If we don't set data[size - 1] to null, the JVM's Garbage Collector
        // will keep the referenced object alive in the heap indefinitely!
        size--;
        data[size] = null;

        return removedValue;
    }

    /**
     * Returns true if the array contains no elements.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements currently stored.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the current internal capacity of the backing buffer.
     */
    public int capacity() {
        return data.length;
    }

    /**
     * Ensures that the backing array can accommodate at least minCapacity elements.
     * If resizing is required, doubles the capacity (geometric expansion).
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            // Geometric doubling: if current capacity is 0, start at DEFAULT_CAPACITY
            int oldCapacity = data.length;
            int newCapacity = (oldCapacity == 0) ? DEFAULT_CAPACITY : oldCapacity * 2;

            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }

            // Arrays.copyOf internally invokes System.arraycopy
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                String.format("Index %d out of bounds for size %d", index, size)
            );
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                String.format("Index %d out of bounds for insertion into size %d", index, size)
            );
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[cursor++];
            }
        };
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // ==========================================
    // Interview practice verification harness
    // ==========================================
    public static void main(String[] args) {
        System.out.println("Running MyArrayList verification tests...");

        MyArrayList<Integer> list = new MyArrayList<>(2);
        assert list.size() == 0 : "Initial size must be 0";
        assert list.isEmpty() : "Should be empty initially";
        assert list.capacity() == 2 : "Initial capacity should be 2";

        // Test 1: Append with automatic doubling
        list.add(10);
        list.add(20);
        assert list.size() == 2;
        assert list.capacity() == 2;

        list.add(30); // Triggers resize to 4
        assert list.size() == 3;
        assert list.capacity() == 4 : "Capacity must double to 4";
        assert list.get(0) == 10;
        assert list.get(1) == 20;
        assert list.get(2) == 30;

        // Test 2: Insert at arbitrary index
        list.add(1, 15); // [10, 15, 20, 30]
        assert list.size() == 4;
        assert list.get(1) == 15;
        assert list.get(2) == 20;

        // Test 3: Set value
        int oldVal = list.set(1, 99);
        assert oldVal == 15;
        assert list.get(1) == 99;

        // Test 4: Removal and loitering prevention
        int removed = list.remove(1); // removes 99 -> [10, 20, 30]
        assert removed == 99;
        assert list.size() == 3;
        assert list.get(1) == 20;

        // Test 5: Boundary & Exception tests
        boolean exceptionThrown = false;
        try {
            list.get(10);
        } catch (IndexOutOfBoundsException e) {
            exceptionThrown = true;
        }
        assert exceptionThrown : "Must throw IndexOutOfBoundsException on illegal read";

        // Test 6: Iteration (foreach loop)
        StringBuilder sb = new StringBuilder();
        for (int num : list) {
            sb.append(num).append(" ");
        }
        assert sb.toString().equals("10 20 30 ") : "Iterator must produce correct order";

        System.out.println("✅ All MyArrayList assertions passed cleanly!");
    }
}
