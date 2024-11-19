public class Agsoy {

    private int[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    // Inserts a new element into the heap
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full.");
            return;
        }

        heap[size] = value;
        heapifyUp(size);
        size++;
    }

    // Extracts the minimum element from the heap
    public int extractMin() {
        if (size == 0) {
            System.out.println("Heap is empty.");
            return -1;
        }

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    // Deletes a specific element from the heap
    public void delete(int value) {
        if (size == 0) {
            System.out.println("Heap is empty.");
            return;
        }

        // Find the index of the element to delete
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (heap[i] == value) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Element not found in the heap.");
            return;
        }

        // Replace the element with the last element
        heap[index] = heap[size - 1];
        size--;

        // Heapify the heap
        if (index < size) {
            heapifyDown(index);
        }
    }

    // Heapifies the heap after insertion
    private void heapifyUp(int index) {
        while (index > 0 && heap[index] < heap[(index - 1) / 2]) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // Heapifies the heap after extraction or deletion
    private void heapifyDown(int index) {
        while (2 * index + 1 < size) {
            int smallerChildIndex = 2 * index + 1;
            if (2 * index + 2 < size && heap[2 * index + 2] < heap[2 * index + 1]) {
                smallerChildIndex = 2 * index + 2;
            }

            if (heap[index] > heap[smallerChildIndex]) {
                swap(index, smallerChildIndex);
                index = smallerChildIndex;
            } else {
                break;
            }
        }
    }

    // Swaps two elements in the heap
    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    // Prints the heap
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // Prints a tree representation of the heap
    public void printHeapTree(int index, int level) {
        if (index >= size) {
            return;
        }

        printHeapTree(2 * index + 2, level + 1); // Print right subtree first
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(heap[index]);
        printHeapTree(2 * index + 1, level + 1); // Print left subtree
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(7); // Capacity of 7

        // Insert the elements from the example
        minHeap.insert(2);
        minHeap.insert(4);
        minHeap.insert(6);
        minHeap.insert(8);
        minHeap.insert(10);
        minHeap.insert(12);
        minHeap.insert(14);

        // Heapify the heap after insertion
        minHeap.heapifyDown(0);

        System.out.println("Initial Min Heap:");
        minHeap.printHeap();
        minHeap.printHeapTree(0, 0);

        System.out.println("\nExtract Min: " + minHeap.extractMin());
        System.out.println("Min Heap after extraction:");
        minHeap.printHeap();
        minHeap.printHeapTree(0, 0);

        System.out.println("\nDelete element 8:");
        minHeap.delete(8);
        System.out.println("Min Heap after deletion:");
        minHeap.printHeap();
        minHeap.printHeapTree(0, 0);
    }
}