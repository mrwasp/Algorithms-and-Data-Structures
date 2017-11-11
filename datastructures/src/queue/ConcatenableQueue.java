package queue;

public interface ConcatenableQueue<T extends Comparable<T>> extends Queue<T> {

  <Q extends ConcatenableQueue<T>> Q concat(Q queue);
}
