package queue;

public class Heap<T extends Comparable<T>> implements Queue<T> {
  private static final int DEFAULT_SIZE = 16;
  private Object[] values;
  private int size;

  public Heap() {
    this.values = new Object[DEFAULT_SIZE];
    this.size = 0;
  }

  public Heap(T[] values) {
    int array_size = DEFAULT_SIZE;
    while (array_size < values.length) {
      array_size *= 2;
    }
    this.values = new Object[array_size];
    this.size = values.length;
    System.arraycopy(values, 0, this.values, 1, values.length);
    for (int i = size / 2; i >= 1; i--) {
      downHeap(i);
    }
  }

  @Override
  public T peek() {
    return get(1);
  }

  @Override
  public T poll() {
    T temp = get(1);
    values[1] = values[size--];
    downHeap(1);
    return temp;
  }

  @Override
  public void insert(T value) {
    if (values.length == size + 1) {
      Object[] temp = new Object[2 * values.length];
      System.arraycopy(values, 0, temp, 0, values.length);
      values = temp;
    }
    values[++size] = value;
    upHeap(size);
  }

  @SuppressWarnings("unchecked")
  private T get(int i) {
    return (T) values[i];
  }

  private void upHeap(int i) {
    T value = get(i);
    while (i > 1 && value.compareTo(get(i / 2)) > 0) {
      values[i] = values[i / 2];
      i /= 2;
    }
    values[i] = value;
  }

  private void downHeap(int i) {
    while (2 * i <= size) {
      int k = 2 * i;
      if (k + 1 <= size && (get(k + 1)).compareTo(get(k)) > 0) {
        k++;
      }
      if (get(i).compareTo(get(k)) > 0) {
        break;
      }
      T temp = get(i);
      values[i] = values[k];
      values[k] = temp;
      i = k;
    }
  }

  private void replace(int i, T value) {
    if (i > size) {
      throw new IndexOutOfBoundsException(String.format(
          "Invalid array index: %d, when last element is: %d", i, size));
    } else {
      T oldValue = get(i);
      values[i] = value;
      if (get(i).compareTo(oldValue) > 0) {
        upHeap(i);
      } else {
        downHeap(i);
      }
    }
  }
}
