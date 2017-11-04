package queue;

public interface Queue<T extends Comparable<T>> {

  T peek();

  T poll();

  void insert(T value);

}
