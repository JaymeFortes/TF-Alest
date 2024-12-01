import java.util.ArrayList;
import java.util.List;

import static javax.swing.Spring.height;

public class AVLTree {
    private class Node {
        int value;
        Node left, right;
        int height;

        Node(int value) {
            this.value = value;
            this.height = 1; // Altura inicial para um novo nó
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public int getHeight() {
        return height(root);
    }



    /**
     * Adicionar elementos na árvore
     */
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
            return node;
        }

        updateHeight(node);
        return rebalance(node);
    }

    /**
     * Exibir a árvore em formato gráfico
     */
    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node node, String prefix, boolean isRight) {
        if (node != null) {
            System.out.println(prefix + (isRight ? "└── " : "├── ") + node.value);
            printTree(node.left, prefix + (isRight ? "    " : "│   "), false);
            printTree(node.right, prefix + (isRight ? "    " : "│   "), true);
        }
    }

    /**
     * Retornar os elementos da árvore em uma lista usando caminhamento central
     */
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

    /**
     * Atualizar altura do nó
     */
    private void updateHeight(Node node) {
        if (node != null) { // Verifica se node é válido antes de acessar left e right
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    /**
     * Obter o fator de balanceamento
     */
    private int getBalance(Node node) {
        if (node == null) {
            return 0; // Balanceamento de um nó nulo é 0
        }
        int leftHeight = (node.left == null) ? 0 : height(node.left); // Verifica se left é nulo
        int rightHeight = (node.right == null) ? 0 : height(node.right); // Verifica se right é nulo
        return leftHeight - rightHeight;
    }

    private int height(Node node) {
        return (node == null) ? 0 : node.height; // Retorna 0 para nós nulos
    }


    /**
     * Rebalancear a árvore
     */
    private Node rebalance(Node node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        } else if (balance < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    /**
     * Rotação à direita
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /**
     * Rotação à esquerda
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }
}
