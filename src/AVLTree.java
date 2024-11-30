import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    private class Node {
        int value;
        Node left, right;
        int height;

        Node(int value) {
            this.value = value;
            this.height = 1; // Altura de um nó recém-criado é 1.
        }
    }

    private Node root;

    /** Adicionar elementos na árvore */
    public void add(int value) {
        root = add(root, value);
    }

    private Node add(Node node, int value) {
        if (node == null) return new Node(value);

        if (value < node.value) {
            node.left = add(node.left, value);
        } else if (value > node.value) {
            node.right = add(node.right, value);
        } else {
            return node; // Valores duplicados não são permitidos.
        }

        // Atualiza a altura e reequilibra o nó.
        updateHeight(node);
        return rebalance(node);
    }

    /** Retornar o pai de um elemento */
    public Integer getParent(int value) {
        return getParent(root, null, value);
    }

    private Integer getParent(Node node, Node parent, int value) {
        if (node == null) return null;

        if (node.value == value) return parent == null ? null : parent.value;

        if (value < node.value) {
            return getParent(node.left, node, value);
        } else {
            return getParent(node.right, node, value);
        }
    }

    /** Limpar o conteúdo da árvore */
    public void clear() {
        root = null;
    }

    /** Verificar se um elemento está armazenado na árvore ou não */
    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(Node node, int value) {
        if (node == null) return false;

        if (value < node.value) {
            return contains(node.left, value);
        } else if (value > node.value) {
            return contains(node.right, value);
        } else {
            return true;
        }
    }

    /** Verificar qual é a altura da árvore */
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    /** Verificar quantos elementos tem na árvore */
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;

        return 1 + size(node.left) + size(node.right);
    }

    /** Verificar se a árvore está vazia ou não */
    public boolean isEmpty() {
        return root == null;
    }

    /** Retornar os elementos da árvore em uma lista usando caminhamento central */
    public List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node node, List<Integer> result) {
        if (node != null) {
            inOrder(node.left, result);
            result.add(node.value);
            inOrder(node.right, result);
        }
    }

    /** Atualizar altura do nó */
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    /** Obter o fator de balanceamento */
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    /** Rebalancear a árvore */
    private Node rebalance(Node node) {
        int balance = getBalance(node);

        if (balance > 1) { // Subárvore esquerda é mais alta.
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left); // Rotação dupla.
            }
            return rotateRight(node);
        } else if (balance < -1) { // Subárvore direita é mais alta.
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right); // Rotação dupla.
            }
            return rotateLeft(node);
        }
        return node;
    }

    /** Rotação à direita */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /** Rotação à esquerda */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    /** Método principal para testes */
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Inserir números na árvore.
        int[] values = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        for (int value : values) {
            tree.add(value);
        }

        // Apresentar a altura da árvore.
        System.out.println("Altura da árvore: " + tree.height());

        // Apresentar o conteúdo da árvore.
        System.out.println("Caminhamento em ordem: " + tree.inOrder());

        // Limpar a árvore.
        tree.clear();
        System.out.println("Árvore limpa.");

        // Inserir números na ordem inversa.
        int[] reversedValues = {90, 80, 70, 60, 50, 40, 30, 20, 10};
        for (int value : reversedValues) {
            tree.add(value);
        }

        // Apresentar o conteúdo da árvore.
        System.out.println("Caminhamento em ordem: " + tree.inOrder());
    }
}
