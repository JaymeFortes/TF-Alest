public class Node {
    int value;
    Node left, right;
    int height;
    String element; // Campo adicionado para compatibilidade com TreeFormatter

    public Node(int item) {
        value = item;
        left = right = null;
        height = 1;
        element = String.valueOf(item); // Inicializando o campo element
    }
}
