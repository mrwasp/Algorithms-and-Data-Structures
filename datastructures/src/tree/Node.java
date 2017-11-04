package tree;

class Node<T> {

  private T object;
  private Node<T> parent;

  Node(T object, Node<T> parent) {
    this.object = object;
    this.parent = parent;
  }

  // @Override Object class methods //--------------------------------------------------------------------------------

  /**
   * Is marked as final because we want the same format for each Node type Method nodeToString is
   * recommended to be overridden in subclasses
   *
   * @return proper string for node
   */
  @Override
  public final String toString() {
    return nodeToString() + parentToString() + " ";
  }

  // Node class methods //--------------------------------------------------------------------------------------------
  // getters and setters

  T getObject() {
    return object;
  }

  void setObject(T object) {
    this.object = object;
  }

  Node<T> getParent() {
    return parent;
  }

  void setParent(Node<T> parent) {
    this.parent = parent;
  }

  // others

  private String parentToString() {
    return parent == null ? "" : "(" + parent.object + ")";
  }

  String nodeToString() {
    return object.toString();
  }
}
