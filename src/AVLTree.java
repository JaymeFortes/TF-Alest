class AVLTree {
    private class AVLNode {
        int value, height;
        AVLNode left, right;

        public AVLNode(int value) {
            this.value = value;
            this.height = 1;
        }
    }

    protected AVLNode root;

    // Adicionar elemento com balanceamento
    public void add(int value) {
        root = addRecursive(root, value);
    }

    private AVLNode addRecursive(AVLNode node, int value) {
        if (node == null) return new AVLNode(value);

        if (value < node.value) {
            node.left = addRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = addRecursive(node.right, value);
        }

        updateHeight(node);
        return balance(node);
    }

    // Atualizar altura
    private void updateHeight(AVLNode node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    int getHeight(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    // Balancear nodo
    private AVLNode balance(AVLNode node) {
        int balanceFactor = getBalanceFactor(node);

        // Rotação esquerda
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rotateRight(node);
        }
        // Rotação direita
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return rotateLeft(node);
        }
        // Rotação esquerda-direita
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // Rotação direita-esquerda
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private int getBalanceFactor(AVLNode node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T = x.right;

        x.right = y;
        y.left = T;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private AVLNode rotateLeft(AVLNode y) {
        AVLNode x = y.right;
        AVLNode T = x.left;

        x.left = y;
        y.right = T;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Caminhamento central
    public void inOrder() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(AVLNode node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.print(node.value + " ");
            inOrderRecursive(node.right);
        }
    }

    // Limpar o conteúdo da árvore
    public void clear() {
        root = null;
    }

    // Verificar se um elemento está armazenado na árvore
    public boolean contains(int value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(AVLNode node, int value) {
        if (node == null) {
            return false;
        }
        if (value == node.value) {
            return true;
        }
        return value < node.value
                ? containsRecursive(node.left, value)
                : containsRecursive(node.right, value);
    }

    // Verificar quantos elementos tem na árvore
    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
    }

    // Verificar se a árvore está vazia
    public boolean isEmpty() {
        return root == null;
    }
}
