package tree;

class ColoredNode<T> extends BinaryNode<T> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private boolean color;

    ColoredNode(T object, BinaryNode<T> parent) {
        super(object, parent);
        this.color = RED;
    }

    // @Override Node class methods //----------------------------------------------------------------------------------

    @Override
    ColoredNode<T> getParent() {
        return (ColoredNode<T>) super.getParent();
    }

    String nodeToString() {
        return (color ? "R-" : "B-") + super.nodeToString();
    }

    // @Override BinaryNode class methods //----------------------------------------------------------------------------

    @Override
    public ColoredNode<T> getLeft() {
        return (ColoredNode<T>) super.getLeft();
    }

    @Override
    public ColoredNode<T> getRight() {
        return (ColoredNode<T>) super.getRight();
    }

    // Methods overriding ColoredNode interface //----------------------------------------------------------------------

    boolean getColor() {
        return color;
    }

    boolean isRed() {
        return color == RED;
    }

    boolean isBlack() {
        return color == BLACK;
    }

    void setColor(boolean color) {
        this.color = color;
    }

    void paintRed() {
        this.color = RED;
    }

    void paintBlack() {
        this.color = BLACK;
    }

}
