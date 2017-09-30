package tree;

abstract class AbstractTree<T> implements Tree<T> {

    private Node<T> root;
    int size;

    // @Override Object class methods //--------------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("---%s--- size: %d", getName(), size);
    }

    // @Override Tree interface methods //------------------------------------------------------------------------------

    public int size() {
        return size;
    }

    public abstract boolean insertObject(T object);

    public abstract boolean removeObject(T object);

    // AbstractTree class  methods //-----------------------------------------------------------------------------------

    abstract String getName();

    abstract Node<T> createNode(T object, Node<T> parent);

    Node<T> getRoot() {
        return root;
    }

    void setRoot(Node<T> root) {
        this.root = root;
    }

    boolean isRoot(Node<T> node) {
        return node == root;
    }

}
