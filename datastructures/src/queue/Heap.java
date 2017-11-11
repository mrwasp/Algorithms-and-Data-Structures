package queue;

public class Heap<T extends Comparable<T>> extends AbstractHeap<T> implements Queue<T> {

  public Heap() {
    super();
  }

  public Heap(T[] values) {
    super(values);
  }

  void upHeap(int i) {
    T value = get(i);
    while (i > 1 && greater(value, get(i / 2))) {
      values[i] = values[i / 2];
      i /= 2;
    }
    values[i] = value;
  }

  void downHeap(int i) {
    while (2 * i <= size) {
      int k = 2 * i;
      if (k + 1 <= size && greater(get(k + 1), get(k))) {
        k++;
      }
      if (greater(get(i), get(k))) {
        break;
      }
      T temp = get(i);
      values[i] = values[k];
      values[k] = temp;
      i = k;
    }
  }

}
