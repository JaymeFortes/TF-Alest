public class Node {

    int value;
    Node left, right;
    int height;

    public Node(int item) {
        value = item;
        left = right = null;
        height = 1;
    }
}