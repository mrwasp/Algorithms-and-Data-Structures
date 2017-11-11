package queue;

public class BinaryHeap<T extends Comparable<T>> implements Queue<T> {
  private static final int DEFAULT_SIZE = 16;
  private Object[] values;
  private int size;

  public BinaryHeap() {
    this.values = new Object[DEFAULT_SIZE];
    this.size = 0;
  }

  private void upHeap(int k) {
    T value = get(k);
    int i = (int) (Math.ceil(0.5 * (-1 + Math.sqrt(1. + 8*k))));
    int j = k - (i * (i - 1) / 2);
    int next_k = k;

    while (i > 1) {
      k -= i; //go to parent (level up)

      if (j == 1) {
        k++; //in this case j of k-i index is 0, so add 1 to be the left son
      } else {
        if (j != i && greater(get(k), get(k + 1))) { //if you are not last one in level, check if next element is less than you
          k++;
          j++;
        }
        j--; //level up has one less element, so decrement j to be in [1; i-1] interval
      }

      if (greater(value, get(k))) { // if new value is still greater than current one, go level up and continue
        values[next_k] = values[k];
        next_k = k;
        i--;
      } else {
        break;
      }
    }
    values[next_k] = value;
  }

  private void downHeap(int k) {
    T value = get(k);
    int i = (int) (Math.ceil(0.5 * (-1 + Math.sqrt(1. + 8*k))));
    int next_k = k;
    k = k + i;
    i++;

    while (k <= size) {
      if (k < size && greater(get(k + 1), get(k))) {
        k++;
      }

      if (greater(get(k), value)) {
        values[next_k] = values[k];
        next_k = k;
        k = k + i;
        i++;
      } else {
        break;
      }
    }
    values[next_k] = value;
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

  private boolean greater(T val1, T val2) {
    return val1.compareTo(val2) > 0;
  }

  private boolean lessThan(int id1, int id2) {
    return get(id1).compareTo(get(id2)) < 0;
  }

  private boolean lessThan(int id1, T val2) {
    return get(id1).compareTo(val2) < 0;
  }

  private boolean greaterThan(int id1, int id2) {
    return get(id1).compareTo(get(id2)) > 0;
  }

}
