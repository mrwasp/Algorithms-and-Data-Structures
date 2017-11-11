package queue;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Assert;
import org.junit.Test;

public class QueueTests {
  private static final int SIZE = 1000;

  @Test
  public void heapInsert() {
    Heap<Integer> heap = new Heap<>();
    List<Integer> numbers = testList();
    for (Integer i : numbers) {
      heap.insert(i);
    }
    heapTest(heap);
  }

  @Test
  public void beapInsert() {
    BinaryHeap<Integer> binaryHeap = new BinaryHeap<>();
    List<Integer> numbers = testList();
    for (Integer i : numbers) {
      binaryHeap.insert(i);
    }
    heapTest(binaryHeap);
  }

  @Test
  public void leftishHeapInsert() {
    LeftistHeap<Integer> binaryHeap = new LeftistHeap<>();
    List<Integer> numbers = testList();
    for (Integer i : numbers) {
      binaryHeap.insert(i);
    }
    heapTest(binaryHeap);
  }

  @Test
  public void heapCreateFromArray() {
    List<Integer> numbers = testList();
    Integer[] integers = new Integer[SIZE];
    Heap<Integer> heap = new Heap<>(numbers.toArray(integers));
    heapTest(heap);
  }

  private void heapTest(Queue<Integer> queue) {
    for (int i = SIZE; i > 0; i--) {
      Assert.assertEquals(i, (int) queue.peek());
      Assert.assertEquals(i, (int) queue.poll());
    }
  }

  private List<Integer> testList() {
    List<Integer> numbers = IntStream.range(1, SIZE + 1).boxed().collect(Collectors.toList());
    Collections.shuffle(numbers);
    return numbers;
  }

}