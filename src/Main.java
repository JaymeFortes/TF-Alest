import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Inicializando a árvore binária de busca
        BinarySearchTree bst = new BinarySearchTree();

        // Inserindo valores na árvore
        int[] values = {10, 5, 15, 3, 7, 18};
        for (int value : values) {
            bst.insert(value);
        }

        // Exibindo a estrutura da árvore
        System.out.println("Estrutura da árvore:");
        bst.printTree(); // Certifique-se de que o método printTree exiba a árvore corretamente.

        // Calculando e exibindo a média dos nós externos
        double averageExternal = bst.AverageExternalNodes(bst.root);
        System.out.println("\nMédia dos nodos externos: " + averageExternal);

        // Encontrando e exibindo o nível do maior valor
        int maxLevel = bst.maxNodeLevel(bst.root);
        System.out.println("Nível do maior valor: " + maxLevel);

        // Calculando a diferença entre o maior valor e a raiz
        int diffMaxRoot = bst.diffMaxRoot();
        System.out.println("Diferença entre maior valor e raiz: " + diffMaxRoot);

        // Calculando a soma dos nós entre 5 e 15
        int sumBetween = bst.sumBetween(5, 15, bst.root);
        System.out.println("Soma dos nodos entre 5 e 15: " + sumBetween);

        // Caminhamento em largura (Breadth-First Order)
        System.out.print("Caminhamento em largura: ");
        bst.breadthFirstOrder(); // Certifique-se de que este método imprima corretamente os nós.
        System.out.println();

        AVLTree avlTree = new AVLTree();

        // Inserindo os números na árvore (ordem crescente)
        int[] valuesToAdd = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        for (int value : valuesToAdd) {
            avlTree.add(value);
        }

        // Exibindo a estrutura da árvore
        System.out.println("Estrutura da árvore AVL após inserção (ordem crescente):");
        avlTree.printTree();

        // Apresentando a altura da árvore
        System.out.println("\nAltura da árvore AVL: " + avlTree.getHeight());

        // Limpando a árvore (vamos apenas criar uma nova árvore aqui)
        avlTree = new AVLTree(); // Recria a árvore para limpar o conteúdo

        // Inserindo os números na árvore (ordem decrescente)
        int[] valuesToReAdd = {90, 80, 70, 60, 50, 40, 30, 20, 10};
        for (int value : valuesToReAdd) {
            avlTree.add(value);
        }

        // Exibindo a estrutura da árvore após nova inserção (ordem decrescente)
        System.out.println("\nEstrutura da árvore AVL após inserção (ordem decrescente):");
        avlTree.printTree();

        // Exibindo o conteúdo da árvore com caminhamento central (inOrder)
        System.out.println("\nConteúdo da árvore em ordem crescente (Caminhamento Central):");
        List<Integer> inOrderValues = avlTree.inOrder();
        System.out.println(inOrderValues);
    }
}


