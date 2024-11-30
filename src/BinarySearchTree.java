import java.util.LinkedList;
import java.util.Queue;

class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    // Adicionar um valor à árvore
    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }
        return current;
    }

    // AverageExternalNodes
    public int AverageExternalNodes(Node current) {
        int[] result = externalNodesSumAndCount(current);
        return result[1] == 0 ? 0 : result[0] / result[1];
    }

    private int[] externalNodesSumAndCount(Node current) {
        if (current == null) return new int[]{0, 0};
        if (current.left == null && current.right == null) return new int[]{current.value, 1};

        int[] left = externalNodesSumAndCount(current.left);
        int[] right = externalNodesSumAndCount(current.right);

        return new int[]{left[0] + right[0], left[1] + right[1]};
    }

    // maxNodeLevel
    public int maxNodeLevel(Node current) {
        if (current == null) return -1;

        Queue<Node> queue = new LinkedList<>();
        queue.add(current);
        int level = 0, maxValue = Integer.MIN_VALUE, maxLevel = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.value > maxValue) {
                    maxValue = node.value;
                    maxLevel = level;
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            level++;
        }
        return maxLevel;
    }

    // diffMaxRoot
    public int diffMaxRoot() {
        if (root == null) return 0;
        return findMax(root) - root.value;
    }

    private int findMax(Node current) {
        return current.right == null ? current.value : findMax(current.right);
    }

    // sumBetween
    public int sumBetween(int start, int end, Node current) {
        if (current == null) return 0;
        int sum = 0;
        if (current.value >= start && current.value < end) sum += current.value;
        sum += sumBetween(start, end, current.left);
        sum += sumBetween(start, end, current.right);
        return sum;
    }

    // breadthFirstOrder
    public void breadthFirstOrder() {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.value + " ");
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        System.out.println();
    }
}
