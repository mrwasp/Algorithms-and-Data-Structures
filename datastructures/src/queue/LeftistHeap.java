package queue;

public class LeftistHeap<T extends Comparable<T>> implements ConcatenableQueue<T> {
  private Node<T> root;

  private class Node<T extends Comparable<T>> {
    T element;
    Node<T> left;
    Node<T> right;
    int npl;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <Q extends ConcatenableQueue<T>> Q concat(Q queue) {
    LeftistHeap<T> leftistHeap = (LeftistHeap<T>) queue;
    root = union(root, leftistHeap.root);
    return null;
  }

  @Override
  public T peek() {
    return root.element;
  }

  @Override
  public T poll() {
    T max = root.element;
    root = union(root.left, root.right);
    return max;
  }

  @Override
  public void insert(T value) {
    Node<T> node = new Node<>();
    node.element =  value;
    root = union(root, node);
  }

  private Node<T> union(Node<T> node1, Node<T> node2) {
    Node<T> currentNode;
    if (node1 == null) return node2;
    if (node2 == null) return node1;
    if (greater(node1.element, node2.element)) {
      currentNode = node1;
      currentNode.right = union(node1.right, node2);
    } else {
      currentNode = node2;
      currentNode.right = union(node2.right, node1);
    }
    if (currentNode.left == null || currentNode.left.npl < currentNode.right.npl) {
      Node<T> temp = currentNode.left;
      currentNode.left = currentNode.right;
      currentNode.right = temp;
    }
    if (currentNode.right == null) {
      currentNode.npl = 0;
    } else {
      currentNode.npl = currentNode.right.npl + 1;
    }
    return currentNode;
  }

  private boolean greater(T val1, T val2) {
    return val1.compareTo(val2) > 0;
  }
}
