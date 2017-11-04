package tree;

interface Tree<T> {

  int size();

  boolean insertObject(T object);

  boolean removeObject(T object);

}
