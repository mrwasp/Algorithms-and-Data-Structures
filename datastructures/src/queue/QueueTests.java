package queue;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Assert;
import org.junit.Test;

public class QueueTests {

  private static final int SIZE = 1000;

  // Heap tests
  @Test
  public void heapInsert() {
    Heap<Integer> heap = new Heap<>();
    List<Integer> numbers = createTestList();
    for (Integer i : numbers) {
      heap.insert(i);
    }
    heapTest(heap);
  }

  @Test
  public void heapCreateFromArray() {
    List<Integer> numbers = createTestList();
    Integer[] integers = new Integer[SIZE];
    Heap<Integer> heap = new Heap<>(numbers.toArray(integers));
    heapTest(heap);
  }

  @Test
  public void heapReplace() {
    Heap<Integer> heap = createTestQueue(new Heap<>());
    heapReplaceTest(heap);
  }

  // BinaryHeap tests
  @Test
  public void binaryHeapInsert() {
    BinaryHeap<Integer> binaryHeap = new BinaryHeap<>();
    List<Integer> numbers = createTestList();
    for (Integer i : numbers) {
      binaryHeap.insert(i);
    }
    heapTest(binaryHeap);
  }

  @Test
  public void binaryHeapCreateFromArray() {
    List<Integer> numbers = createTestList();
    Integer[] integers = new Integer[SIZE];
    BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(numbers.toArray(integers));
    heapTest(binaryHeap);
  }

  @Test
  public void binaryHeapReplace() {
    BinaryHeap<Integer> heap = createTestQueue(new BinaryHeap<>());
    heapReplaceTest(heap);
  }

  // LeftishHeap tests
  @Test
  public void leftishHeapInsert() {
    LeftistHeap<Integer> binaryHeap = new LeftistHeap<>();
    List<Integer> numbers = createTestList();
    for (Integer i : numbers) {
      binaryHeap.insert(i);
    }
    heapTest(binaryHeap);
  }

  // Handy functions
  private void heapTest(Queue<Integer> queue) {
    for (int i = SIZE; i > 0; i--) {
      Assert.assertEquals(i, (int) queue.peek());
      Assert.assertEquals(i, (int) queue.poll());
    }
  }

  private void heapReplaceTest(Queue<Integer> queue) {
    List<Integer> replacing = createTestList();
    for (int i = 0; i < replacing.size(); i++) {
      queue.replace(i, replacing.get(i));
    }

    int current = queue.poll();
    for (int i = SIZE - 1; i > 0; i--) {
      int next_current = queue.poll();
      Assert.assertTrue(current >= next_current);
      current = next_current;
    }
  }

  private List<Integer> createTestList() {
    List<Integer> numbers = IntStream.range(1, SIZE + 1).boxed().collect(Collectors.toList());
    Collections.shuffle(numbers);
    return numbers;
  }

  private <Q extends Queue<Integer>> Q createTestQueue(Q queue) {
    for (Integer i : createTestList()) {
      queue.insert(i);
    }
    return queue;
  }

}