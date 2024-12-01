public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(3);
        tree.insert(7);
        tree.insert(15);
        tree.insert(25);

        System.out.println("Média da árvore: " + tree.AverageExternalNodes(tree.root));

        System.out.println("Nível em que está localizado o maior valor existente na árvore: " + tree.maxNodeLevel(tree.root));

        System.out.println("Diferença entre o maior valor presente na árvore e o valor do nodo raiz: " + tree.diffMaxRoot());

        System.out.println("Soma dos valores de todos os nodos que estão entre dois valores especificados: " + tree.sumBetween(5, 20, tree.root));

        System.out.print("Caminhamento em largura: ");
        tree.breadthFirstOrder();

        System.out.println("\n\nÁrvore estruturada:");
        tree.printTree();

        AVLTree treeAvl = new AVLTree();
        int[] values1 = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        for (int value : values1) {
            treeAvl.add(value);
        }
        System.out.println("Árvore AVL:");
        treeAvl.printTree();
        System.out.println("Altura da árvore: " + treeAvl.height());
        treeAvl.clear();
        int[] values2 = {90, 80, 70, 60, 50, 40, 30, 20, 10};
        for (int value : values2) {
            treeAvl.add(value);
        }
        System.out.println("Árvore AVL 2:");
        treeAvl.printTree();
        System.out.println("Conteúdo da árvore (caminhamento central): " + treeAvl.inOrder());
    }
}

