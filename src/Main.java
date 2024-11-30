public class Main {
    public static void main(String[] args) {
        // Teste da Árvore Binária de Pesquisa (BinarySearchTree)
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);

        System.out.println("Ordem em largura (Breadth-first order):");
        bst.breadthFirstOrder();

        System.out.println("Média dos Nós Externos: " + bst.AverageExternalNodes(bst.root));
        System.out.println("Nível do Nó com Maior Valor: " + bst.maxNodeLevel(bst.root));
        System.out.println("Diferença entre o Maior Valor e a Raiz: " + bst.diffMaxRoot());
        System.out.println("Soma entre 30 e 70: " + bst.sumBetween(30, 70, bst.root));

        // Teste da Árvore AVL
        AVLTree avl = new AVLTree();
        avl.add(10);
        avl.add(20);
        avl.add(30);
        avl.add(40);
        avl.add(50);
        avl.add(60);

        System.out.println("\nCaminhamento Em Ordem (InOrder) da AVL:");
        avl.inOrder();

        // Testes adicionais para AVL
        System.out.println("\nContém 40? " + avl.contains(40));
        System.out.println("Altura da árvore AVL: " + avl.getHeight(avl.root));
        System.out.println("Tamanho da árvore AVL: " + avl.size());
        System.out.println("Árvore AVL está vazia? " + avl.isEmpty());

        // Limpar AVL
        avl.clear();
        System.out.println("Árvore AVL limpa.");
        System.out.println("Árvore AVL está vazia? " + avl.isEmpty());

        // Inserir números na ordem inversa
        avl.add(90);
        avl.add(80);
        avl.add(70);
        avl.add(60);
        avl.add(50);
        avl.add(40);
        avl.add(30);
        avl.add(20);
        avl.add(10);

        System.out.println("Caminhamento Em Ordem (InOrder) da AVL após inserções inversas:");
        avl.inOrder();
    }
}
