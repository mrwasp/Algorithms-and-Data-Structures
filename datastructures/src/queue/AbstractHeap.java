package queue;

abstract class AbstractHeap<T extends Comparable<T>> implements Queue<T> {

  private static final int DEFAULT_SIZE = 16;
  Object[] values;
  int size;

  AbstractHeap() {
    this.values = new Object[DEFAULT_SIZE];
    this.size = 0;
  }

  AbstractHeap(T[] values) {
    int array_size = DEFAULT_SIZE;
    while (array_size < values.length) {
      array_size *= 2;
    }
    this.values = new Object[array_size];
    this.size = values.length;
    System.arraycopy(values, 0, this.values, 1, values.length);
    for (int i = size; i >= 1; i--) {
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

  @Override
  public void replace(int id, T value) {
    if (0 <= id && id < size) {
      id++; // fixed to index in heap array;
      T oldValue = get(id);
      values[id] = value;
      if (get(id).compareTo(oldValue) > 0) {
        upHeap(id);
      } else {
        downHeap(id);
      }
    } else {
      throw new IndexOutOfBoundsException(String.format(
          "Invalid array index: %d, when size is: %d", id, size));
    }
  }

  @SuppressWarnings("unchecked")
  T get(int i) {
    return (T) values[i];
  }

  boolean greater(T val1, T val2) {
    return val1.compareTo(val2) > 0;
  }

  void debug() {
    for (int i = 1; i <= size; i++) {
      System.out.print(get(i) + " ");
    }
    System.out.println();
  }

  abstract void upHeap(int id);

  abstract void downHeap(int id);
}
