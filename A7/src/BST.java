<<<<<<< HEAD
import org.w3c.dom.Node;

import java.util.ArrayList;
=======
import java.util.EmptyStackException;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

//-----------------------------------------------------------------------
// Empty BST exception

class EmptyBSTE extends Exception {}

//-----------------------------------------------------------------------
// Abstract BST class

abstract class BST implements TreePrinter.PrintableNode, Iterable<Integer> {

    //--------------------------
    // Static fields and methods
    //--------------------------

    static EmptyBSTE EBSTX = new EmptyBSTE();

    static BST EBST = new EmptyBST();

    // A leaf is a tree with empty left and right children
    static BST BSTLeaf(int elem) {
<<<<<<< HEAD
       return new BSTNode(elem, EBST, EBST, 1) ; // TODO
=======
        return new BSTNode(elem, EBST, EBST); // TODO
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    // Use the iterator (that you need to define below) to get the BST nodes
    // one-by-one and insert them into the resulting AVL tree.
    static AVL toAVL (BST bst) {
<<<<<<< HEAD
        AVL avl = new EmptyAVL();
        while (bst.iterator().hasNext()){
            avl.AVLinsert(bst.iterator().next());
        }
        return avl;
=======

        try {
            Iterator<Integer> bstIterator = bst.iterator();
            AVLNode avl = new AVLNode(bstIterator.next(), new EmptyAVL(), new EmptyAVL());
            while (bstIterator.hasNext()) {
                avl.AVLinsert(bstIterator.next());
            }
            return avl;
        }catch(EmptyStackException e){
            return AVL.EAVL;
        }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract int BSTData() throws EmptyBSTE;

    abstract BST BSTLeft() throws EmptyBSTE;

    abstract BST BSTRight() throws EmptyBSTE;

    abstract int BSTHeight();

    abstract boolean isEmpty();

    //--------------------------
    // Main methods
    //--------------------------

    abstract boolean BSTfind (int key);

    abstract BST BSTinsert(int key);

    abstract BST BSTdelete(int key) throws EmptyBSTE;

    abstract Pair<Integer, BST> BSTdeleteLeftMostLeaf() throws EmptyBSTE;

    abstract BST flip();
}

//-----------------------------------------------------------------------

class EmptyBST extends BST {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int BSTData() throws EmptyBSTE {
        throw EBSTX;
    }

    BST BSTLeft() throws EmptyBSTE {
        throw EBSTX;
    }

    BST BSTRight() throws EmptyBSTE {
        throw EBSTX;
    }

    int BSTHeight() {
        return 0;
    }

    boolean isEmpty () { return true; }

    //--------------------------
    // Main methods
    //--------------------------

    boolean BSTfind(int key) {
        return false;
    }

    BST BSTinsert(int key) {
        return BSTLeaf(key);
    }

    BST BSTdelete(int key) throws EmptyBSTE { throw EBSTX; }

    Pair<Integer, BST> BSTdeleteLeftMostLeaf() throws EmptyBSTE { throw EBSTX; }

    @Override
    BST flip() {
        return this;
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public String getText() {
        return "";
    }

    //--------------------------
    // Iterable interface
    //--------------------------

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            public boolean hasNext() {
                return false;
            }

            public Integer next() {
                throw new NoSuchElementException();
            }
        };
    }
}

//-----------------------------------------------------------------------
// Non-empty tree case

class BSTNode extends BST {
    private int data;
    private BST left, right;
    private int height;

    BSTNode(int data, BST left, BST right, int height){
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = height;
    }

    public BSTNode(int data, BST left, BST right){
        this.data = data;
        this.left = left;
        this.right = right;
        height = Math.max(left.BSTHeight(),right.BSTHeight())+1;
    }

    int BSTData() throws EmptyBSTE {
<<<<<<< HEAD
        return this.data; // TODO
    }

    BST BSTLeft() throws EmptyBSTE {
        return this.left; // TODO
    }

    BST BSTRight() throws EmptyBSTE {
        return this.right; // TODO
    }

    int BSTHeight() {
        return 1 + Integer.max(right.BSTHeight(), left.BSTHeight()); // TODO
=======
        return data;
    }

    BST BSTLeft() throws EmptyBSTE {
        return left;
    }

    BST BSTRight() throws EmptyBSTE {
        return right;
    }

    int BSTHeight() {
        return height; // TODO
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }
    boolean isEmpty() {
        return false;
    }

    //--------------------------
    // Main methods
    //--------------------------

    boolean BSTfind(int key) {
<<<<<<< HEAD
        try{
            if(this.BSTData() == key){
                return true;
            } if (key < this.BSTData()){
                return this.left.BSTfind(key);
            } else{
              return this.right.BSTfind(key);
            }
        }catch (EmptyBSTE e){
            return false;
        }
    }

    BST BSTinsert(int key)  {
        try {

        if (key < this.BSTData() ){
                    this.left = BSTLeft().BSTinsert(key);
                    this.BSTHeight();
                    return this;

                }else{
                    this.right = BSTRight().BSTinsert(key);
                    this.BSTHeight();
                    return this;
            }
        } catch (EmptyBSTE e) {
            return new BSTNode(key, new EmptyBST(), new EmptyBST(), height);
        }
=======

        boolean found = false;
        if (key == this.data){
            found = true;
        }
        else if(key<this.data){
            found = left.BSTfind(key);
        }
        else{
            found = right.BSTfind(key);
        }

        return found;
    }

    /** @noinspection Duplicates*/
    BST BSTinsert(int key) {

        BSTNode b;
        if(key<this.data){
           b = new BSTNode(data,left.BSTinsert(key),right);
        }
        else{
           b = new BSTNode(data, left, right.BSTinsert(key));
        }

return b;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }



    /** @noinspection Duplicates*/
    BST BSTdelete(int key) throws EmptyBSTE {
<<<<<<< HEAD
        try {
            if (key < this.BSTData()) {
                this.left = this.BSTLeft().BSTdelete(key);
                return this;
            }else if(key > this.BSTData()){
                this.right = this.BSTRight().BSTdelete(key);
                return this;
            }else {
                Pair<Integer, BST> doub = this.right.BSTdeleteLeftMostLeaf();
                this.right = doub.getSecond().BSTRight();
                this.data = doub.getSecond().BSTData();
                return this;
            }
        } catch (EmptyBSTE e) {
            return new EmptyBST();
        }
    }

    Pair<Integer, BST> BSTdeleteLeftMostLeaf() throws EmptyBSTE{
        try {
            Pair<Integer, BST> key;
            if (this.BSTLeft().isEmpty()) {
                if (this.BSTRight().isEmpty()) {
                    return new Pair<Integer, BST>(this.BSTData(), this.BSTRight());
                } else {
                    key = BSTRight().BSTdeleteLeftMostLeaf();
                    this.right = key.getSecond();
                }
            } else {
                key = this.BSTLeft().BSTdeleteLeftMostLeaf();
                this.left = key.getSecond();
            }
            return new Pair<>(key.getFirst(), this);
        } catch (EmptyBSTE e) {
            return new Pair<>(null, new EmptyBST());
        }
=======
        if (key == this.data){
            try {
                Pair<Integer, BST> leftMostChildOnRightAndTreeWithoutIt = right.BSTdeleteLeftMostLeaf();
                return new BSTNode(leftMostChildOnRightAndTreeWithoutIt.getFirst(), left, leftMostChildOnRightAndTreeWithoutIt.getSecond());
            }catch(EmptyBSTE e){
                return left;
            }
        }
        else if(key<this.data){
            return new BSTNode(data, left.BSTdelete(key),right);
        }
        else{
            return new BSTNode(data, left,right.BSTdelete(key));
        }
    }

    Pair<Integer, BST> BSTdeleteLeftMostLeaf() {
        try{
            Pair<Integer, BST> alpha = left.BSTdeleteLeftMostLeaf();
            return new Pair<Integer,BST>(alpha.getFirst(),new BSTNode(data, alpha.getSecond() ,right));
        }catch(EmptyBSTE e){
            return new Pair<>(data, right);
        }
    }

    @Override
    BST flip() {
        return new BSTNode(data, right.flip(), left.flip())
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }


    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return left.isEmpty() ? null : left;
    }

    public TreePrinter.PrintableNode getRight() {
        return right.isEmpty() ? null : right;
    }

    public String getText() {
        return String.valueOf(data);
    }

    //--------------------------
    // Iterable interface
    //--------------------------

    public Iterator<Integer> iterator() {
<<<<<<< HEAD
        return new Iterator<Integer>() {
            boolean root = false;
            Iterator<Integer> curr = left.iterator();

            public boolean hasNext() {
                if (!root) {
                    return true;
                } else {
                    return curr.hasNext();
                }
            }

            public Integer next() {
                if (!root && curr.hasNext()) {
                    return curr.next();
                } else if (!root) {
                    root = true;
                    curr = right.iterator();
                    return data;
                } else {
                    return curr.next();
                }
            }
        };
=======
        return new TreeIterator(this);
    }


}

class TreeIterator implements Iterator<Integer>{


    private Stack<BST> stack = new Stack<>();

    public TreeIterator(BST b){
        loadStack(b);
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }




    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        try {
            BST s = stack.pop();
            int t = 0;
            try {
                t = s.BSTData();
                this.loadStack(s.BSTRight());
            } catch (EmptyBSTE emptyBSTE) {
                emptyBSTE.printStackTrace();
            }
            return t;
        } catch (EmptyStackException e){
            throw new NoSuchElementException();
        }

    }

    private void loadStack(BST root){
        if (!root.isEmpty()){
            stack.push(root);
            try {
                loadStack(root.BSTLeft());
            } catch (EmptyBSTE emptyBSTE) {
                emptyBSTE.printStackTrace();
            }
        }
    }

}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
