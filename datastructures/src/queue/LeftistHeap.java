package queue;

public class LeftistHeap<T extends Comparable<T>> implements ConcatenableQueue<T> {

  private Node root;

  private class Node {
    T element;
    Node left;
    Node right;
    int rank;
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
    Node node = new Node();
    node.element = value;
    root = union(root, node);
  }

  @Override
  public void replace(int id, T value) {

  }

  private Node union(Node node1, Node node2) {
    Node currentNode;
    if (node1 == null) {
      return node2;
    }
    if (node2 == null) {
      return node1;
    }
    if (greater(node1.element, node2.element)) {
      currentNode = node1;
      currentNode.right = union(node1.right, node2);
    } else {
      currentNode = node2;
      currentNode.right = union(node2.right, node1);
    }
    if (currentNode.left == null || currentNode.left.rank < currentNode.right.rank) {
      Node temp = currentNode.left;
      currentNode.left = currentNode.right;
      currentNode.right = temp;
    }
    if (currentNode.right == null) {
      currentNode.rank = 0;
    } else {
      currentNode.rank = currentNode.right.rank + 1;
    }
    return currentNode;
  }

  private boolean greater(T val1, T val2) {
    return val1.compareTo(val2) > 0;
  }
}
