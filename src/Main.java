public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Inserir valores na árvore
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(18);

        // Testando AverageExternalNodes
        int averageExternal = bst.AverageExternalNodes(bst.root);
        System.out.println("Média dos nodos externos: " + averageExternal);

        // Testando maxNodeLevel
        int maxLevel = bst.maxNodeLevel(bst.root);
        System.out.println("Nível do maior valor: " + maxLevel);

        // Testando diffMaxRoot
        int diffMaxRoot = bst.diffMaxRoot();
        System.out.println("Diferença entre maior valor e raiz: " + diffMaxRoot);

        // Testando sumBetween
        int sumBetween = bst.sumBetween(5, 15, bst.root);
        System.out.println("Soma dos nodos entre 5 e 15: " + sumBetween);

        // Testando breadthFirstOrder
        System.out.print("Caminhamento em largura: ");
        bst.breadthFirstOrder();
    }
}