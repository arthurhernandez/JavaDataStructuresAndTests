import java.util.ArrayList;
import java.util.List;

class RootE extends Exception {
}

class NoLeftChildE extends Exception {
}

class NoRightChildE extends Exception {
}

public class WeakHeap {
    private int size;
    private ArrayList<Item> elems;
    private ArrayList<Integer> flips;
    /*
     * Position 0 in elems is for the root.
     * The root has no left child.
     * Position 1 in elems is for the right child of the root
     * After that the left child of an item at position i is at position 2i+flips(i)
     * and the right child is at position 2i+1-flips(i)
     * The parent of a child at position i is at position i/2
     * flips(0) should never be set to 1 because that would give the root
     * a left child instead of a right one
     */

    WeakHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
        this.flips = new ArrayList<>();
    }

    /*
     * The following constructor does the following sequences of steps:
     *   - it copies the incoming items to a the array items
     *   - for each item, setPosition and setHeap are called with the
     *     appropriate parameters
     *   - for each item, a corresponding flip bit is initialized to 0
     *   - call weakHeapify
     */
    WeakHeap (ArrayList<Item> items) {
	// TODO
        int count = 0;
        elems = new ArrayList<>();
        flips = new ArrayList<>();
        size = items.size();
        for(Item i: items){
            i.setPosition(count);
            i.setHeap(this);
            elems.add(i);
            flips.add(0);
            count++;
        }
        weakHeapify();
    }

    /*
     * This method executes a loop that starts with the last element
     * in the array and ends with the element at position 1. In each
     * iteration, the item is joined with its distinguished ancestor. 
     * Note that when calling join, the distinguished ancestor is 
     * always in the first argument position. 
     */
    void weakHeapify () {
	// TODO
        try{
            for(int i = size - 1; i > 0;i--){
                join(getDAncestorIndex(i),i);
            }
        }catch (RootE e){
            //what goes here?
        }
    }

    // Trivial methods

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    Item findMin() {
        return elems.get(0);
    }

    List<Item> getElems() {
        return elems;
    }

    Item getElem (int i) {
        return elems.get(i);
    }

    int getValue(int i) {
        return elems.get(i).getValue();
    }

    int getFlip (int i) {
        return flips.get(i);
    }

    public String toString() {
        return getElems().toString();
    }

    // Computations with indices

    int getParentIndex(int i) throws RootE {
	// TODO
        if (i >= size) {
            throw new RootE();
        }else{
            if(i == 0){
                throw new RootE();
            } else {
                return i / 2;
            }
        }
    }

    int getLeftChildIndex(int i) throws NoLeftChildE {
	    if(i >= size || i ==0){
	        throw new NoLeftChildE();
        }
	    int holder = (2 * i) + getFlip(i);
	    if(holder<size){
	        return holder;
        }else {
            throw new NoLeftChildE();
        }
    }

    int getRightChildIndex(int i) throws NoRightChildE {
	// TODO
        if(i ==0){
            return 1;
        }
        if(i >= size){
            throw new NoRightChildE();
        }
        int holder = (2 * i) + 1 - getFlip(i);
        if(holder < size){
            return holder;
        }else{
            throw new NoRightChildE();
        }
    }

    boolean isLeftChild (int i) throws RootE {
        // TODO
        if(i == 0){
            throw new RootE();
        }
        if(getFlip(getParentIndex(i))== 1 && i % 2 == 1){
            return true;
        }else if(getFlip(getParentIndex(i))==0 && i %2 ==0){
            return true;
        }else{
            return false;
        }
    }

    boolean isRightChild (int i) throws RootE {
        // TODO
        if(i == 0){
            throw new RootE();
        }
        if(getFlip(getParentIndex(i))== 1 && i % 2 == 0){
            return true;
        }else if(getFlip(getParentIndex(i))==0 && i %2 ==1){
            return true;
        }else{
            return false;
        }
    }

    int getDAncestorIndex(int i) throws RootE {
        //TODO
	    if(i ==0){
	        throw new RootE();
        }
	    if(isLeftChild(i)){
	        return getDAncestorIndex(getParentIndex(i));
        }else{
	        return getParentIndex(i);
        }
    }

    int getLeftMostChildIndex () throws NoLeftChildE {
	// TODO
        if(size <= 1){
            throw new NoLeftChildE();
        }
        int result = 1;
        try {
            while (true) {
                result = getLeftChildIndex(result);
            }
        }catch (NoLeftChildE e){
            return result;
        }
    }

    // Helpers for main methods

    void swap(int i, int j) {
	// TODO -- should be identical to swap in A9
        Item temp = getElem(i);
        elems.set(i,getElem(j));
        elems.set(j,temp);
        getElem(j).setPosition(i);
        getElem(i).setPosition(j);
    }

    /*
     * If the value at position j is smaller than the value 
     * at position i, they are swapped and the flip bit at 
     * position j is negated. In that case the method returns 
     * false. If no action was taken, the method returns true.
     */
    boolean join (int i, int j) {
	// TODO
        if(getValue(i) > getValue(j)){
            swap(i,j);
            if(flips.get(j) == 0){
                flips.set(j,1);
            }else{
                flips.set(j,0);
            }
            return false;
        }else{
            return true;
        }
    }

    /*
     * The method starts by joining j with its distinguished ancestor. 
     * If a swap was performed, the method recursively continues by moving
     * the distinguished ancestor up. If not, the method returns immediately.
     */
    void moveUp (int j) {
	// TODO
        try {
            if(!(join(getDAncestorIndex(j),j))) {
                moveUp(getDAncestorIndex(j));
            }

        }catch (RootE e){

        }
    }

    /*
     * The method starts by locating the leftmost child along the leftmost
     * spine. It then repeatedely joins j with that leftmost child and its 
     * parents. 
     */
    void moveDown (int j) {
	// TODO
        try {
            int i = getLeftMostChildIndex();
            while (i != j){
                join(j,i);
                i = getParentIndex(i);
            }
        }catch (NoLeftChildE e){
        }catch(RootE e){
        }
    }

    // Main methods: insert and extractMin

    /*
     * The method adds the new item at the end of the array making sure 
     * it calls setPosition and setHeap with the appropriate parameters 
     * and initializes the associated flip bit correctly. As a little 
     * optimization, if the inserted item is in a left child position, it 
     * will reset the flip bit of the parent to 0. 
     * The method then calls moveUp.
     */
    void insert (Item item) {
	// TODO
        try{
            item.setHeap(this);
            elems.add(item);
            size++;
            getElem(size-1).setPosition(size -1);
            flips.add(0);
            if(isLeftChild(size-1)){
                flips.set(getParentIndex(size-1),0);
            }
            moveUp(size-1);
        }catch (RootE e){
        }
    }

    /*
     * Like we did in the previous and as is outlined in the lecture notes, 
     * the last item in the array is moved to location 0. And then moveDown 
     * is called. Just make sure you don't call moveDown if the array has exactly 
     * one element!
     */
    Item extractMin () {
        // TODO
        Item min = findMin();
        swap(0, size - 1);
        elems.remove(size - 1);
        size--;
        if (size > 1){
            moveDown(0);
        }
        return min;
    }

    /*
     * This method is useful for testing and debugging. It loops over the elements
     * of the array starting from the end until reaching index 1. For each item,
     * the method checks that it is larger than its distinguished ancestor.
     */
    boolean checkOrder () {
	// TODO
        try {
            for (int i = size - 1; i > 0; i--) {
                if (getValue(i) > getDAncestorIndex(i)){
                    return true;
                }else{
                    return false;
                }
            }
        }catch (RootE e){
        }
        return false;
    }

}
