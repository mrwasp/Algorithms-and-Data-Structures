package tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreeTests {

    private static final int SIZE = 1000;

    @Test
    public void binarySearchTreeTest() {
        treeTest(new BinarySearchTree<>());
    }

    @Test
    public void redBlackTreeTest() {
        treeTest(new RedBlackTree<>());
    }

    private void treeTest(BinaryTree<Integer> binaryTree) {

        List<Integer> numbers = IntStream.range(1, SIZE + 1).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        for (Integer i : numbers) {
            binaryTree.insertObject(i);
        }
        binaryTree.printLevelOrder();
        Collections.sort(numbers);
        Assert.assertArrayEquals(numbers.toArray(), binaryTree.getInOrder().toArray());

        List<Integer> evenNumbers = numbers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        List<Integer> oddNumbers = numbers.stream().filter(x -> x % 2 == 1).collect(Collectors.toList());
        Collections.shuffle(evenNumbers);
        for (Integer i : evenNumbers) {
            binaryTree.removeObject(i);
        }
        binaryTree.printLevelOrder();
        Collections.sort(evenNumbers);
        Assert.assertArrayEquals(oddNumbers.toArray(), binaryTree.getInOrder().toArray());
    }



}