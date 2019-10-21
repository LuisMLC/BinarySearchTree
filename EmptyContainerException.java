/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa1718_bst.tad;

/**
 *
 * @author brunomnsilva
 */
class EmptyContainerException extends RuntimeException {

    public EmptyContainerException() {
        super("The tree is empty!");
    }

    public EmptyContainerException(String string) {
        super(string);
    }
    
}