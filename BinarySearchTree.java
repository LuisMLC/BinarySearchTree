/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa1718_bst.tad;
import java.util.ArrayList;

/**
 *
 * @author brunomnsilva
 * @param <T>
 */
public interface BinarySearchTree<T extends Comparable> {
    
    /** Checks whether the tree is empty. */
    public boolean isEmpty();
    /** Returns the size (number of elements) of the tree. */
    public int size();
    /** Checks whether an element is present on the tree. */
    public boolean exists(T elem);
    
    /** Inserts the given element into the tree. */
    public void insert(T elem);
    /** Removes the given element from the tree. */
    public void remove(T elem) throws EmptyContainerException;
    
    /** Returns the minimum element of the tree by the Comparable criterion. */
    public T minimum() throws EmptyContainerException;
    /** Returns the maximum element of the tree by the Comparable criterion. */
    public T maximum() throws EmptyContainerException;
       
    /** Returns the existing elements of the tree in their order. */
    public Iterable<T> inOrder();
    /** Returns the existing elements of the tree in pre-order. */
    public Iterable<T> preOrder();
    /** Returns the existing elements of the tree in post-order. */
    public Iterable<T> posOrder();
    
  
}