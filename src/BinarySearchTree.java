import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    Node root;

    /**
     * Método AverageExternalNodes()
     * Calcula a média dos valores de todos os nodos externos (folhas) na árvore.
     * @param current Nodo atual da árvore (geralmente inicializado com a raiz).
     * @return valor médio dos nodos externos. Retorna 0 se a árvore não tiver nodos externos.
     */
    public int AverageExternalNodes(Node current) {
        if (current == null) return 0;

        int[] result = calculateExternalNodes(current);
        return result[0] == 0 ? 0 : result[1] / result[0]; // Média = soma / quantidade
    }

    private int[] calculateExternalNodes(Node node) {
        if (node == null) return new int[]{0, 0}; // {quantidade, soma}
        if (node.left == null && node.right == null) return new int[]{1, node.value};

        int[] left = calculateExternalNodes(node.left);
        int[] right = calculateExternalNodes(node.right);

        return new int[]{left[0] + right[0], left[1] + right[1]};
    }

    /**
     * Método maxNodeLevel()
     * Determina o nível em que está localizado o maior valor existente na árvore.
     * @param current nodo atual da árvore (geralmente inicializado com a raiz).
     * @return nível do nodo com o maior valor na árvore. Retorna -1 se a árvore estiver vazia.
     */
    public int maxNodeLevel(Node current) {
        if (current == null) return -1;

        Queue<Node> queue = new LinkedList<>();
        queue.add(current);

        int level = 0, maxValue = Integer.MIN_VALUE, maxLevel = -1;

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

    /**
     * Método diffMaxRoot()
     * Calcula a diferença entre o maior valor presente na árvore e o valor do nodo raiz.
     * @return diferença (valor do maior nodo - valor do nodo raiz).
     */
    public int diffMaxRoot() {
        if (root == null) return 0;

        int max = findMax(root);
        return max - root.value;
    }

    private int findMax(Node node) {
        if (node == null) return Integer.MIN_VALUE;

        int leftMax = findMax(node.left);
        int rightMax = findMax(node.right);

        return Math.max(node.value, Math.max(leftMax, rightMax));
    }

    /**
     * Método sumBetween()
     * Calcula a soma dos valores de todos os nodos que estão entre dois valores especificados
     * (inclusive o nodo inicial, mas excluindo o nodo final).
     * @param start valor inicial do intervalo, incluído na soma.
     * @param end valor final do intervalo, excluído da soma.
     * @param current nodo atual da árvore (geralmente inicializado com a raiz).
     * @return soma dos valores dos nodos dentro do intervalo especificado.
     *         retorna 0 se não houver nodos no intervalo ou se a árvore estiver vazia.
     */
    public int sumBetween(int start, int end, Node current) {
        if (current == null) return 0;

        int sum = 0;
        if (current.value >= start && current.value < end) {
            sum += current.value;
        }
        if (current.value > start) {
            sum += sumBetween(start, end, current.left);
        }
        if (current.value < end) {
            sum += sumBetween(start, end, current.right);
        }
        return sum;
    }

    /**
     * Método breadthFirstOrder()
     * Realiza o caminhamento em largura (level-order traversal) e imprime os valores da árvore.
     */
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

    /**
     * Método printTree()
     * Exibe a árvore binária em forma de diagrama.
     */
    public void printTree() {
        printTreeRec(root, 0);
    }

    private void printTreeRec(Node node, int level) {
        if (node == null) {
            return;
        }
        // Primeiro, exibe o lado direito (mais ao fundo)
        printTreeRec(node.right, level + 1);
        // Exibe o valor do nodo com espaçamento proporcional ao nível
        System.out.println(generateSpaces(level * 4) + node.value);
        // Depois, exibe o lado esquerdo (mais à frente)
        printTreeRec(node.left, level + 1);
    }

    private String generateSpaces(int count) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < count; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    // Método para inserir um nodo na árvore
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value < root.value) root.left = insertRec(root.left, value);
        else if (value > root.value) root.right = insertRec(root.right, value);

        return root;
    }
}
