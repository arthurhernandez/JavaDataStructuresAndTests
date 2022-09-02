//-----------------------------------------------------------------------
// Empty AVL exception

import java.util.NoSuchElementException;

class EmptyAVLE extends Exception {
}

//-----------------------------------------------------------------------
// Abstract AVL class

abstract class AVL implements TreePrinter.PrintableNode {

    //--------------------------
    // Static fields and methods
    //--------------------------

    static EmptyAVLE EAVLX = new EmptyAVLE();

    static AVL EAVL = new EmptyAVL();
    static AVL AVLLeaf(int elem) {
<<<<<<< HEAD
        return new AVLNode(elem, EAVL,EAVL ); // TODO
=======
        return new AVLNode(elem, EAVL, EAVL);
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    // Recursively copy the tree changing AVL nodes to BST nodes
    static BST toBST(AVL avl) {
        try {
            return new BSTNode(avl.AVLData(), toBST(avl.AVLLeft()), toBST(avl.AVLRight()));
        } catch (EmptyAVLE emptyAVLE) {
            return new EmptyBST();
        }
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract int AVLData() throws EmptyAVLE;

    abstract AVL AVLLeft() throws EmptyAVLE;

    abstract AVL AVLRight() throws EmptyAVLE;

    abstract int AVLHeight();

    abstract boolean isEmpty();

    //--------------------------
    // Main methods
    //--------------------------

    abstract boolean AVLfind(int key);

    abstract AVL AVLinsert(int key);

    abstract AVL AVLeasyRight();

    abstract AVL AVLrotateRight();

    abstract AVL AVLeasyLeft();

    abstract AVL AVLrotateLeft();

    abstract AVL AVLdelete(int key) throws EmptyAVLE;

    abstract Pair<Integer, AVL> AVLshrink() throws EmptyAVLE;

}

//-----------------------------------------------------------------------

class EmptyAVL extends AVL {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int AVLData() throws EmptyAVLE {
        throw EAVLX;
    }

    AVL AVLLeft() throws EmptyAVLE {
        throw EAVLX;
    }

    AVL AVLRight() throws EmptyAVLE {
        throw EAVLX;
    }

    int AVLHeight() {
        return 0;
    }

    boolean isEmpty() {
        return true;
    }

    ;

    //--------------------------
    // Main methods
    //--------------------------

    boolean AVLfind(int key) {
        return false;
    }

    AVL AVLinsert(int key) {
        return AVLLeaf(key);
    }

    AVL AVLeasyRight() {
        throw new Error("Internal bug: should never call easyRight on empty tree");
    }

    AVL AVLrotateRight() {
        throw new Error("Internal bug: should never call rotateRight on empty tree");
    }

    AVL AVLeasyLeft() {
        throw new Error("Internal bug: should never call easyLeft on empty tree");
    }

    AVL AVLrotateLeft() {
        throw new Error("Internal bug: should never call rotateLeft on empty tree");
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        throw EAVLX;
    }

    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
        throw EAVLX;
    }

    //--------------------------
    // Override
    //--------------------------

    public boolean equals(Object o) {
        return (o instanceof EmptyAVL);
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
}

//-----------------------------------------------------------------------

class AVLNode extends AVL {
    private int data;
    private AVL left, right;
    private int height;

    public AVLNode(int data, AVL left, AVL right) {
<<<<<<< HEAD
        this.left = left;
        this.right = right;
        this.data = data;
    }
     // constructor TODO
=======
        this.data = data;
        this.left = left;
        this.right = right;
        height = 1 + Math.max(left.AVLHeight(), right.AVLHeight());
    }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int AVLData() {
        return data;
    }

    AVL AVLLeft() {
        return left;
    }

    AVL AVLRight() {
        return right;
    }

    int AVLHeight() {
        return height;
    }

    boolean isEmpty() {
        return false;
    }

    ;

    //--------------------------
    // Main methods
    //--------------------------

    /**
     * @noinspection Duplicates
     */
    boolean AVLfind(int key) {
<<<<<<< HEAD
            if (this.AVLData() == key) {
                return true;
            }
            if (key < this.AVLData()) {
                return this.left.AVLfind(key);
            } else if (key > this.AVLData()) {
                return this.right.AVLfind(key);
            } else {
                return false;
            }
        }; // TODO

    AVL AVLinsert(int key) {
        if (key < this.AVLData()){
            AVL in = new AVLNode(this.AVLData(),this.AVLLeft().AVLinsert(key),this.AVLRight());
            try{
                AVL left2 = in.AVLLeft();
                if (Math.abs(left2.AVLHeight() - this.AVLRight().AVLHeight()) > 1){
                    in = in.AVLrotateRight();
                }
            }catch (EmptyAVLE e){
                return in;
            }
        }else{
            AVL in = new AVLNode(this.AVLData(),this.AVLLeft(),this.AVLRight().AVLinsert(key));
            try {
            AVL right2 = in.AVLRight();
                if (Math.abs(right2.AVLHeight() - this.AVLLeft().AVLHeight()) > 1){
                    in = in.AVLrotateLeft();
                }
            }catch (EmptyAVLE e){
                return in;
            }
        }
    }

    AVL AVLeasyRight() {
        try{
            int newRoot = left.AVLData();
            AVL right2 = new AVLNode(data, left.AVLRight(),right);
            AVL left2 = left.AVLLeft();
            return new AVLNode(newRoot,left2,right2);
        }catch(EmptyAVLE e){
            return new AVLNode(this.data,this.left,this.right);
        } // TODO
=======
        boolean found = false;

        if (key == this.data) {
            found = true;
        } else if (key < this.data) {
            found = left.AVLfind(key);
        } else {
            found = right.AVLfind(key);
        }
        return found;
    }

    AVL AVLinsert(int key) {
        AVL b;
        if (key < this.data) {
            AVL newLeft = left.AVLinsert(key);
            b = new AVLNode(data, newLeft, right);

            if (right.AVLHeight()+1 < newLeft.AVLHeight()) {
                b = b.AVLrotateRight();
            }
        } else {
            AVL newRight = right.AVLinsert(key);
            b = new AVLNode(data, left, newRight);
            if (left.AVLHeight()+1 < newRight.AVLHeight() ) {
                b = b.AVLrotateLeft();
            }
        }




        return b;
    }

    AVL AVLeasyRight() {
        try {
            AVL newRoot = left;
            AVL danglingMan = newRoot.AVLRight();

            return new AVLNode(newRoot.AVLData(), newRoot.AVLLeft(),  new AVLNode(data, danglingMan,right));


        } catch (EmptyAVLE e) {
            throw new Error("Why are you rotating something that's empty?");
        }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }


    AVL AVLrotateRight() {
<<<<<<< HEAD
    try{
        if(left.AVLLeft().AVLHeight() >= left.AVLRight().AVLHeight()){
            AVL newRotatedTree = new AVLNode(this.data,this.left,this.right).AVLeasyRight();
            return newRotatedTree;// TODO
        }else{
            AVL newLeftside = AVLLeft().AVLeasyLeft();
            AVL newLeftRightRotated = new AVLNode(this.data,newLeftside,this.right).AVLeasyRight();
            return newLeftRightRotated;
        }
    }catch(EmptyAVLE e){
        return new AVLNode(this.data,this.left,this.right);
        }
    }

    AVL AVLeasyLeft() {
        try{
            int newRoot = right.AVLData();
            AVL left2 = new AVLNode(data, left,right.AVLLeft());
            AVL right2 = right.AVLRight();
            return new AVLNode(newRoot,left2,right2);
        }catch(EmptyAVLE e){
            return new AVLNode(this.data,this.left,this.right);
        } // TODO
    }

    AVL AVLrotateLeft() {
        try{
            if(left.AVLLeft().AVLHeight() <= left.AVLRight().AVLHeight()){
                AVL newRotatedTree = new AVLNode(this.data,this.left,this.right).AVLeasyLeft();
                return newRotatedTree;
            }else{
                AVL newRightside = AVLRight().AVLeasyRight();
                AVL newRightLefttRotated = new AVLNode(this.data,this.left,newRightside).AVLeasyLeft();
                return newRightLefttRotated;
            }
        }catch(EmptyAVLE e){
            return new AVLNode(this.data,this.left,this.right);
        }// TODO
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        if(key == this.AVLData() && (!this.AVLRight().isEmpty() || this.AVLLeft().isEmpty())){
            Pair<Integer, AVL> pair = this.AVLshrink();
            return new AVLNode(pair.getFirst(),pair.getSecond(),this.AVLRight());
        }else if(key < this.AVLData()) {
            AVL del = new AVLNode(this.AVLData(), this.AVLLeft().AVLdelete(key), this.AVLRight());
            if (Math.abs(del.AVLLeft().AVLHeight() - this.AVLRight().AVLHeight()) > 1) {
                del = del.AVLrotateLeft();
            }
            return del;
        }else if (key == this.AVLData()&&this.AVLLeft().isEmpty() && this.AVLRight().isEmpty()){
            return AVL.EAVL;
        }else{
            AVL del = new AVLNode(this.AVLData(),this.AVLLeft(),this.AVLRight().AVLdelete(key));
            if (Math.abs(del.AVLRight().AVLHeight() - this.AVLLeft().AVLHeight()) > 1) {
                del = del.AVLrotateLeft();
            }
            return del;
        }
    }

    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
        if(this.AVLRight().isEmpty()){
            return new Pair<Integer, AVL>(this.AVLData(), new EmptyAVL());
        }else{
            Pair<Integer, AVL> key = this.AVLRight().AVLshrink();
            AVL avl2 = new AVLNode(this.AVLData(),this.AVLLeft(),key.getSecond());
            if (avl2.AVLLeft().AVLHeight() - this.AVLRight().AVLHeight() > 1) {
                avl2 = avl2.AVLeasyRight();
            }else if (avl2.AVLRight().AVLHeight() - this.AVLLeft().AVLHeight() > 1){
                avl2 = avl2.AVLeasyLeft();
            }
            return new Pair<Integer, AVL>(key.getFirst(),avl2);
        }

         // TODO
=======
        try {
            AVL finalTree;
            if (left.AVLLeft().AVLHeight() >= left.AVLRight().AVLHeight()) {
                finalTree = this.AVLeasyRight();
            } else {
                AVL newLeft = left.AVLeasyLeft();
                AVL midwayTree = new AVLNode(data, newLeft, right);
                finalTree = midwayTree.AVLeasyRight();

            }


            return finalTree;
        } catch (EmptyAVLE E) {
            throw new Error ("Why are you rotating something that's empty?");
        }

    }

    AVL AVLeasyLeft() {
        try {
            AVL newRoot = right;
            AVL danglingMan = newRoot.AVLLeft();

            return new AVLNode(newRoot.AVLData(), new AVLNode(data, left, danglingMan), newRoot.AVLRight());


        } catch (EmptyAVLE e) {
            throw new Error("Why are you rotating something that's empty?");
        }
    }

    AVL AVLrotateLeft() {
       try{
           AVL finalTree;
           if(right.AVLRight().AVLHeight() >= right.AVLLeft().AVLHeight()){
               finalTree = this.AVLeasyLeft();
           }
           else{
               AVL newRight = right.AVLeasyRight();
               AVL midwayTree = new AVLNode(data, left, newRight);
               finalTree = midwayTree.AVLeasyLeft();

           }

           return finalTree;
       }catch(EmptyAVLE e){
           throw new Error("What are you doing rotating something empty?");
       }
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        AVL finalTree;

            if (key < data) {
                AVL newLeft = left.AVLdelete(key);
                finalTree = new AVLNode(data, newLeft, right);
                if (newLeft.AVLHeight() + 1 < right.AVLHeight()) {
                    finalTree = finalTree.AVLrotateLeft();
                }

            } else if (key > data) {
                AVL newRight = right.AVLdelete(key);
                finalTree = new AVLNode(data, left, newRight);
                if (newRight.AVLHeight() + 1 < left.AVLHeight()) {
                    finalTree = finalTree.AVLrotateRight();
                }
            } else {
                try {
                    Pair<Integer, AVL> largestElementOnLeftAndBalancedLeft = left.AVLshrink();
                    finalTree = new AVLNode(largestElementOnLeftAndBalancedLeft.getFirst(), largestElementOnLeftAndBalancedLeft.getSecond(), right);
                    if (largestElementOnLeftAndBalancedLeft.getSecond().AVLHeight() + 1 < right.AVLHeight()) {
                        finalTree = finalTree.AVLrotateLeft();
                    }

                } catch (EmptyAVLE e) {
                    //return right if "this" doesnt have a left
                    finalTree = right;
                }
            }


        return finalTree;

    }

    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
       try{
           Pair<Integer, AVL> rightMostChildAndTreeWithoutIt = right.AVLshrink();

           AVL newLeft = new AVLNode(data, left, rightMostChildAndTreeWithoutIt.getSecond());

           if(left.AVLHeight() > rightMostChildAndTreeWithoutIt.getSecond().AVLHeight()+1){
               newLeft = newLeft.AVLrotateRight();
           }
           return new Pair<>(rightMostChildAndTreeWithoutIt.getFirst(), newLeft);

       }catch(EmptyAVLE e){
           return new Pair<Integer,AVL>(data, left);

       }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }


    //--------------------------
    // Override
    //--------------------------

    public boolean equals(Object o) {
        if (o instanceof AVLNode) {
            AVLNode other = (AVLNode) o;
            return data == other.data && left.equals(other.left) && right.equals(other.right);
        }
        return false;
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
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
