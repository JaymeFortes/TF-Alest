public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        // Adicionar nós à árvore
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(3);
        tree.insert(7);
        tree.insert(15);
        tree.insert(25);

        // Testar AverageExternalNodes
        System.out.println("Average of external nodes: " + tree.AverageExternalNodes(tree.root));

        // Testar maxNodeLevel
        System.out.println("Level of the max node: " + tree.maxNodeLevel(tree.root));

        // Testar diffMaxRoot
        System.out.println("Difference between max node and root: " + tree.diffMaxRoot());

        // Testar sumBetween
        System.out.println("Sum of nodes between 5 and 20: " + tree.sumBetween(5, 20, tree.root));

        // Testar breadthFirstOrder
        System.out.print("Breadth-First Order: ");
        tree.breadthFirstOrder();

        // Imprimir a árvore de forma estruturada
        System.out.println("\n\nStructured Tree:");
        tree.printTree();
    }
}
