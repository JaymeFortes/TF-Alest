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

        System.out.println("Average of external nodes: " + tree.AverageExternalNodes(tree.root));
        System.out.println("Level of the max node: " + tree.maxNodeLevel(tree.root));
        System.out.println("Difference between max node and root: " + tree.diffMaxRoot());
        System.out.println("Sum of nodes between 5 and 20: " + tree.sumBetween(5, 20, tree.root));
        System.out.print("Breadth-First Order: ");
        tree.breadthFirstOrder();
        System.out.println("\n\nStructured Tree:");
        System.out.println(tree.formatTree()); // Utilizando TreeFormatter para formatar a árvore

        AVLTree treeAvl = new AVLTree();
        int[] values1 = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        for (int value : values1) {
            treeAvl.add(value);
        }
        System.out.println("Árvore AVL:");
        System.out.println(treeAvl.formatTree()); // Utilizando TreeFormatter para formatar a árvore
        System.out.println("Altura da árvore: " + treeAvl.height());
        treeAvl.clear();
        int[] values2 = {90, 80, 70, 60, 50, 40, 30, 20, 10};
        for (int value : values2) {
            treeAvl.add(value);
        }
        System.out.println("Árvore AVL 2:");
        System.out.println(treeAvl.formatTree()); // Utilizando TreeFormatter para formatar a árvore
        System.out.println("Conteúdo da árvore (caminhamento central): " + treeAvl.inOrder());
    }
}
