import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Método para inserir um novo nó
    void insert(int value) {
        root = insertRec(root, value);
    }

    // Método recursivo para inserir um novo nó
    Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    // Método AverageExternalNodes
    public int AverageExternalNodes(Node current) {
        if (current == null) {
            return 0;
        }
        int[] result = sumAndCountExternalNodes(current);
        return result[1] == 0 ? 0 : result[0] / result[1];
    }

    private int[] sumAndCountExternalNodes(Node current) {
        if (current == null) {
            return new int[]{0, 0};
        }
        if (current.left == null && current.right == null) {
            return new int[]{current.value, 1};
        }
        int[] left = sumAndCountExternalNodes(current.left);
        int[] right = sumAndCountExternalNodes(current.right);
        return new int[]{left[0] + right[0], left[1] + right[1]};
    }

    // Método maxNodeLevel
    public int maxNodeLevel(Node current) {
        if (current == null) {
            return -1;
        }
        return maxNodeLevelHelper(current, 0, new int[]{Integer.MIN_VALUE, -1});
    }

    private int maxNodeLevelHelper(Node current, int level, int[] maxInfo) {
        if (current == null) {
            return maxInfo[1];
        }
        if (current.value > maxInfo[0]) {
            maxInfo[0] = current.value;
            maxInfo[1] = level;
        }
        maxNodeLevelHelper(current.left, level + 1, maxInfo);
        maxNodeLevelHelper(current.right, level + 1, maxInfo);
        return maxInfo[1];
    }

    // Método diffMaxRoot
    public int diffMaxRoot() {
        if (root == null) {
            return 0;
        }
        int maxValue = findMaxValue(root);
        return maxValue - root.value;
    }

    private int findMaxValue(Node current) {
        if (current == null) {
            return Integer.MIN_VALUE;
        }
        int leftMax = findMaxValue(current.left);
        int rightMax = findMaxValue(current.right);
        return Math.max(current.value, Math.max(leftMax, rightMax));
    }

    // Método sumBetween
    public int sumBetween(int start, int end, Node current) {
        if (current == null) {
            return 0;
        }
        if (current.value < start) {
            return sumBetween(start, end, current.right);
        }
        if (current.value >= end) {
            return sumBetween(start, end, current.left);
        }
        return current.value + sumBetween(start, end, current.left) + sumBetween(start, end, current.right);
    }

    // Método breadthFirstOrder
    public void breadthFirstOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.value + " ");
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    // Método para imprimir a árvore de forma estruturada
    public void printTree() {
        printTreeRec(root, 0);
    }

    private void printTreeRec(Node node, int level) {
        if (node == null) {
            return;
        }
        printTreeRec(node.right, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t");
            }
            System.out.println("|-------" + node.value);
        } else {
            System.out.println(node.value);
        }
        printTreeRec(node.left, level + 1);
    }
}
