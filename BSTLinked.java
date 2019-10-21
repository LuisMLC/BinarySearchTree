/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa1718_bst.tad;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe que implementa uma Binary Search Tree (BST). A maioria dos métodos da
 * interface são implementados à custa de métodos privados recursivos que
 * exploram implicitamente (não explicitamente, ao contrário de outra impl.
 * possível) o conceito de sub-árvore.
 *
 * Nenhum método público expõe TreeNode como argumento, dado que é uma
 * representação utilizada internamente, i.e., dependente da implementação.
 *
 * @author brunomnsilva
 * @param <E>
 */
public class BSTLinked<E extends Comparable> implements BinarySearchTree<E> {

    //Embora pudessemos seguir a lógica de um atributo 'size' para ir mantendo
    //o número de elementos atuais, nesta impl., por uma questão de prática,
    //vamos implementar o size() de forma recursiva!
    private TreeNode root;

    /**
     * Construtor que inicializa uma BST com um elemento na raiz.
     *
     * @param rootElement elemento inicial da raiz
     */
    public BSTLinked(E rootElement) {
        this.root = new TreeNode(rootElement);
    }

    /**
     * Construtor que inicializa uma BST vazia.
     */
    public BSTLinked() {
        this.root = null; //empty
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public int size() {
        return size(root);
    }

    /**
     * Método que calcula o tamanho de uma sub-árvore, dada a sua raiz.
     *
     * @param treeRoot raiz da sub-árvore
     * @return tamanho da sub-árvore
     */
    private int size(TreeNode treeRoot) {
        int size=0;
        if (treeRoot == null) {
            return size;
        }
        size= 1+ size(treeRoot.left) + size(treeRoot.right);
        //retorna a raiz (1) mais a soma do tamanho das sub-àrvores esq. e dir.
       
        return    size ; 
    }

    @Override
    public boolean exists(E elem) {
        return exists(elem, root);
    }

    /**
     * Método que verifica se um elemento existe numa sub-árvore, dada a sua
     * raiz.
     *
     * @param elem elemento a pesquisar segundo o critério Comparable
     * @param treeRoot raiz da sub-árvore
     * @return valor lógico da presença do elemento
     */
    private boolean exists(E elem, TreeNode treeRoot) {
        if (treeRoot == null) {
            return false;
        }

        int comparison = elem.compareTo(treeRoot.element);
        if (comparison == 0) {
            return true;
        } else if (comparison < 0) //pesquisa na sub-arvore esquerda
        {
            return exists(elem, treeRoot.left);
        } else //pesquisa na sub-arvore direita
        {
            return exists(elem, treeRoot.right);
        }

    }

    @Override
    public void insert(E elem) {
        if (isEmpty()) {
            this.root = new TreeNode(elem, null, null);
        } else {
            //podemos só executar a seguinte instrução sse elem não existir,
            //i.e., !exists(elem), mas como os nós terão de ser percorridos
            //à mesma para essa verificação, é mais eficaz incluir essa
            //lógica na inserção (se existir não insere).
            insert(elem, root);
        }
    }

    /**
     * Método que insere um elemento numa sub-àrvore não vazia, i.e., treeRoot
     * != null.
     *
     * @param elem elemento a inserir
     * @param treeRoot raiz da sub-àrvore
     */
    private void insert(E elem, TreeNode treeRoot) {
        if (treeRoot == null) {
            throw new IllegalArgumentException("This method cannot take a null node!");
        }

        int comparison = elem.compareTo(treeRoot.element);
        if (comparison == 0) {
            return; //já existe
        } else if (comparison < 0) {
            if(treeRoot.left == null) {
                treeRoot.left = new TreeNode(elem,null,null);
            } else {
                insert(elem,treeRoot.left);
            }
        } else { 
            if(treeRoot.right == null) {
                treeRoot.right = new TreeNode(elem,null,null);
            } else {
                insert(elem,treeRoot.right);
            }
        }
        
    }

    @Override
    public void remove(E elem) throws EmptyContainerException {
          if (isEmpty()){
              throw new EmptyContainerException();
          } else {
              remove(elem,root,null);
          }
          
    }
    
    private void remove(E elem, TreeNode treeRoot, TreeNode parentNode) throws EmptyContainerException {
        if (treeRoot == null) {
            throw new IllegalArgumentException("This method cannot take a null node!");
        }

        int comparison = elem.compareTo(treeRoot.element);
        if (comparison == 0) {
            if (treeRoot.left == null && treeRoot.right == null) {
                if (parentNode.left != null) {
                    if (parentNode.left.element == elem) {
                        parentNode.left = null;
                    }
                }
                if (parentNode.right != null) {
                    if (parentNode.right.element == elem) {
                        parentNode.right = null;
                    }
                }
                return;
            }
            if (treeRoot.left != null) {
                E auxE = maximum(treeRoot.left);
                remove(auxE, treeRoot.left, treeRoot);
                treeRoot.element = auxE;
            } else if (treeRoot.right != null) {
                E auxE = minimum(treeRoot.right);
                remove(auxE, treeRoot.right, treeRoot);
                treeRoot.element = auxE;
            }
        } else if (comparison < 0) {
            if (treeRoot.left != null) {
                remove(elem, treeRoot.left,treeRoot);
            } else {
                throw new EmptyContainerException();
            }
        } else {
            if (treeRoot.right != null) {
                remove(elem, treeRoot.right, treeRoot);
            } else {
                throw new EmptyContainerException();
            }
        }
        
    }

  
    @Override
    public E minimum() throws EmptyContainerException {
        if(isEmpty()) throw new EmptyContainerException();
        return minimum(root);
    }

    /**
     * Método que devolve o elemento menor de uma sub-árvore, segundo o critério
     * Comparable. O menor elemento é o elemento mais à esquerda da sub-árvore.
     * Assume que treeRoot != null!
     *
     * @param treeRoot raiz da sub-árvore a pesquisar
     * @return o menor elemento
     */
    private E minimum(TreeNode treeRoot)  {
        if(treeRoot.left==null)
            return treeRoot.element;
        return minimum(treeRoot.left);
    }

    @Override
    public E maximum() throws EmptyContainerException {
        if(isEmpty()) throw new EmptyContainerException();
        return maximum(root);
  
    }

    /**
     * Método que devolve o elemento maior de uma sub-árvore, segundo o critério
     * Comparable. O maior elemento é o elemento mais à direta da sub-árvore.
     * Assume que treeRoot != null!
     *
     * @param treeRoot raiz da sub-árvore a pesquisar
     * @return o menor elemento
     */
    private E maximum(TreeNode treeRoot) throws EmptyContainerException {
        if(treeRoot.right==null)
            return treeRoot.element;
        return maximum(treeRoot.right);
  
    }

    @Override
    public Iterable<E> inOrder() {
        ArrayList<E> list = new ArrayList<>();
        inOrder(this.root, list);
        return list;
    }

    private void inOrder(TreeNode treeRoot, ArrayList<E> elements) {
        if (treeRoot == null) {
            return;
        }
        inOrder(treeRoot.left, elements);
        elements.add(treeRoot.element);
        inOrder(treeRoot.right, elements);
    }

   
    @Override
    public Iterable<E> posOrder() {
        ArrayList<E> list = new ArrayList<>();
        posOrder(this.root, list);
        return list;
    }
    
    private void posOrder(TreeNode treeRoot, ArrayList<E> elements) {
        if (treeRoot == null) {
            return;
        }
        
        elements.add(treeRoot.element);
        posOrder(treeRoot.left, elements);
        posOrder(treeRoot.right, elements);
    }

    @Override
    public Iterable<E> preOrder() {
        ArrayList<E> list = new ArrayList<>();
        preOrder(this.root, list);
        return list;
    }
    
    private void preOrder(TreeNode treeRoot, ArrayList<E> elements) {
        if (treeRoot == null) {
            return;
        }
        preOrder(treeRoot.left, elements);
        preOrder(treeRoot.right, elements);
        elements.add(treeRoot.element);
    }
    
    public boolean hasChildren() {
        return (root.left == null && root.right == null);
    }
    
    @Override
    public String toString() {
        if(isEmpty()) return "Empty tree.";
                
        StringBuilder sb = new StringBuilder();        
        inOrderPrettyString(root, new StringBuilder(), true, sb);
        return sb.toString();
    }
    
    
    private void inOrderPrettyString(TreeNode treeRoot, 
            StringBuilder prefix, boolean isTail, StringBuilder sb) {
        
        if(treeRoot.right != null) {
            inOrderPrettyString(treeRoot.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(treeRoot.element).append("\n");

        if(treeRoot.left != null) {
            inOrderPrettyString(treeRoot.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        
    }

   
    private class TreeNode {

        private E element;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(E element, TreeNode left, TreeNode right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        public TreeNode(E element) {
            this(element, null, null);
        }
    
    }
}