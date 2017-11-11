package queue;

public class BinaryHeap<T extends Comparable<T>> extends AbstractHeap<T> implements Queue<T> {

  public BinaryHeap() {
    super();
  }

  public BinaryHeap(T[] values) {
    super(values);
  }

  void upHeap(int k) {
    T value = get(k);
    int i = (int) (Math.ceil(0.5 * (-1 + Math.sqrt(1. + 8 * k))));
    int j = k - (i * (i - 1) / 2);
    int next_k = k;

    while (i > 1) {
      k -= i; //go to parent (level up)

      if (j == 1) {
        k++; //in this case j of k-i index is 0, so add 1 to be the left son
      } else {
        if (j != i && greater(get(k), get(k
            + 1))) { //if you are not last one in level, check if next element is less than you
          k++;
          j++;
        }
        j--; //level up has one less element, so decrement j to be in [1; i-1] interval
      }

      if (greater(value,
          get(k))) { // if new value is still greater than current one, go level up and continue
        values[next_k] = values[k];
        next_k = k;
        i--;
      } else {
        break;
      }
    }
    values[next_k] = value;
  }

  void downHeap(int k) {
    T value = get(k);
    int i = (int) (Math.ceil(0.5 * (-1 + Math.sqrt(1. + 8 * k))));
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

}
