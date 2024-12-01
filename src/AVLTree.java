
import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    Node root;

    public AVLTree() {
        root = null;
    }

    // Método para adicionar elementos na árvore
    public void add(int value) {
        root = addRec(root, value);
    }

    private Node addRec(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = addRec(node.left, value);
        } else if (value > node.value) {
            node.right = addRec(node.right, value);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balanceFactor = getBalance(node);
        if (balanceFactor > 1 && value < node.left.value) {
            return rightRotate(node);
        }
        if (balanceFactor < -1 && value > node.right.value) {
            return leftRotate(node);
        }
        if (balanceFactor > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balanceFactor < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Rotação à direita
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Rotação à esquerda
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Obter o fator de balanceamento de um nó
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Obter a altura de um nó
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // Método para retornar o pai de um elemento
    public Node getParent(int value) {
        return getParentRec(root, value, null);
    }

    private Node getParentRec(Node current, int value, Node parent) {
        if (current == null) {
            return null;
        }
        if (current.value == value) {
            return parent;
        }
        if (value < current.value) {
            return getParentRec(current.left, value, current);
        } else {
            return getParentRec(current.right, value, current);
        }
    }

    // Método para limpar o conteúdo da árvore
    public void clear() {
        root = null;
    }

    // Método para verificar se um elemento está armazenado na árvore ou não
    public boolean contains(int value) {
        return containsRec(root, value);
    }

    private boolean containsRec(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value ? containsRec(current.left, value) : containsRec(current.right, value);
    }

    // Método para verificar a altura da árvore
    public int height() {
        return height(root);
    }

    // Método para verificar quantos elementos tem na árvore
    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRec(node.left) + sizeRec(node.right);
    }

    // Método para verificar se a árvore está vazia ou não
    public boolean isEmpty() {
        return root == null;
    }

    // Método para retornar os elementos da árvore em uma lista usando caminhamento central
    public List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrderRec(root, result);
        return result;
    }

    private void inOrderRec(Node node, List<Integer> result) {
        if (node != null) {
            inOrderRec(node.left, result);
            result.add(node.value);
            inOrderRec(node.right, result);
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
