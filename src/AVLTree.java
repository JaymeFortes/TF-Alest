
import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    Node root;

    public AVLTree() {
        root = null;
    }

    // Método para adicionar elementos na árvore
    public void add(int value) {
        root = addRec(root, value);
    }

    private Node addRec(Node node, int value) {
        // Se o nó atual for null, cria um novo nó com o valor fornecido
        if (node == null) {
            return new Node(value); // Retorna o novo nó como a folha
        }

        // Se o valor for menor que o valor do nó atual, insere à esquerda
        if (value < node.value) {
            node.left = addRec(node.left, value); // Chamada recursiva para o filho esquerdo
        }
        // Se o valor for maior que o valor do nó atual, insere à direita
        else if (value > node.value) {
            node.right = addRec(node.right, value); // Chamada recursiva para o filho direito
        }
        // Se o valor já existir, não faz nada (sem duplicados na árvore)
        else {
            return node; // Retorna o nó atual sem alterações
        }

        // Atualiza a altura do nó atual com base nos filhos esquerdo e direito
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Calcula o fator de balanceamento para determinar a necessidade de rotação
        int balanceFactor = getBalance(node);

        // Caso 1: Rotação à direita (desbalanceado à esquerda, valor menor que filho esquerdo)
        if (balanceFactor > 1 && value < node.left.value) {
            return rightRotate(node); // Realiza uma rotação simples para a direita
        }

        // Caso 2: Rotação à esquerda (desbalanceado à direita, valor maior que filho direito)
        if (balanceFactor < -1 && value > node.right.value) {
            return leftRotate(node); // Realiza uma rotação simples para a esquerda
        }

        // Caso 3: Rotação dupla esquerda-direita (desbalanceado à esquerda, valor maior que filho esquerdo)
        if (balanceFactor > 1 && value > node.left.value) {
            node.left = leftRotate(node.left); // Primeiro, rotaciona o filho esquerdo para a esquerda
            return rightRotate(node); // Depois, rotaciona o nó atual para a direita
        }

        // Caso 4: Rotação dupla direita-esquerda (desbalanceado à direita, valor menor que filho direito)
        if (balanceFactor < -1 && value < node.right.value) {
            node.right = rightRotate(node.right); // Primeiro, rotaciona o filho direito para a direita
            return leftRotate(node); // Depois, rotaciona o nó atual para a esquerda
        }

        return node; // Retorna o nó atual após ajustes (se necessários)
    }

    // Rotação simples para a direita
    private Node rightRotate(Node y) {
        Node x = y.left; // Define o filho esquerdo como nova raiz temporária
        Node T2 = x.right; // Armazena o filho direito do novo nó raiz temporário

        x.right = y; // Realiza a rotação: o nó original se torna o filho direito da nova raiz
        y.left = T2; // O filho direito do novo nó raiz é realocado

        // Atualiza as alturas dos nós afetados pela rotação
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; // Retorna a nova raiz
    }

    // Rotação simples para a esquerda
    private Node leftRotate(Node x) {
        Node y = x.right; // Define o filho direito como nova raiz temporária
        Node T2 = y.left; // Armazena o filho esquerdo do novo nó raiz temporário

        y.left = x; // Realiza a rotação: o nó original se torna o filho esquerdo da nova raiz
        x.right = T2; // O filho esquerdo do novo nó raiz é realocado

        // Atualiza as alturas dos nós afetados pela rotação
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; // Retorna a nova raiz
    }

    // Calcula o fator de balanceamento de um nó
    private int getBalance(Node node) {
        if (node == null) {
            return 0; // Nó nulo tem fator de balanceamento 0
        }
        // Retorna a diferença entre as alturas dos filhos esquerdo e direito
        return height(node.left) - height(node.right);
    }

    // Retorna a altura de um nó
    private int height(Node node) {
        return (node == null) ? 0 : node.height; // Altura de nó nulo é 0; caso contrário, retorna a altura armazenada
    }

    // Retorna o nó pai de um valor específico
    public Node getParent(int value) {
        return getParentRec(root, value, null); // Inicia a busca a partir da raiz, sem pai inicial
    }

    // Busca recursivamente o pai de um nó
    private Node getParentRec(Node current, int value, Node parent) {
        if (current == null) {
            return null; // Se o nó atual for nulo, o valor não existe na árvore
        }
        if (current.value == value) {
            return parent; // Se encontrar o valor, retorna o pai
        }
        // Continua a busca recursivamente à esquerda ou à direita
        if (value < current.value) {
            return getParentRec(current.left, value, current); // Busca no filho esquerdo
        } else {
            return getParentRec(current.right, value, current); // Busca no filho direito
        }
    }

    // Método para limpar o conteúdo da árvore
    public void clear() {
        root = null;
    }

    // Método para verificar se um elemento está armazenado na árvore ou não
    public boolean contains(int value) {
        return containsRec(root, value);
    }

    private boolean containsRec(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value ? containsRec(current.left, value) : containsRec(current.right, value);
    }

    // Método para verificar a altura da árvore
    public int height() {
        return height(root);
    }

    // Método para verificar quantos elementos tem na árvore
    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRec(node.left) + sizeRec(node.right);
    }

    // Método para verificar se a árvore está vazia ou não
    public boolean isEmpty() {
        return root == null;
    }

    // Método para retornar os elementos da árvore em uma lista usando caminhamento central
    public List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrderRec(root, result);
        return result;
    }

    private void inOrderRec(Node node, List<Integer> result) {
        if (node != null) {
            inOrderRec(node.left, result);
            result.add(node.value);
            inOrderRec(node.right, result);
        }
    }

    // Método para imprimir a árvore de forma estruturada
    public void printTree() {
        printTreeRec(root, 0);
    }

    private void printTreeRec(Node node, int level) {
        if (node == null) {
            return;
        }
        printTreeRec(node.right, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t");
            }
            System.out.println("|-------" + node.value);
        } else {
            System.out.println(node.value);
        }
        printTreeRec(node.left, level + 1);
    }
}
