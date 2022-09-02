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

    WeakHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
        this.flips = new ArrayList<>();
    }

    WeakHeap (ArrayList<Item> items) {
        int count = 0;
        elems = new ArrayList<>();
        flips = new ArrayList<>();
        size = items.size();
        for(Item i: items){
            i.setPosition(count);
            count++;
            i.setHeap(this);
            elems.add(i);
            flips.add(0);

        }
        weakHeapify();
    }

    void weakHeapify () {
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
        Item temp = getElem(i);
        Item temp2 = getElem(j);
        elems.set(i,temp2);
        elems.set(j,temp);
        temp.setPosition(j);
        temp2.setPosition(i);
    }

    boolean join (int i, int j) {
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

    void moveUp (int j) {
        try {
            int index = getDAncestorIndex(j);
            if(!join(index,j)) {
                moveUp(index);
            }
            return;
        }catch (RootE e){
            return;
        }
    }

    void moveDown (int j) {
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

    void updateKey (int i, int value) {
	assert value < elems.get(i).getValue();
	getElem(i).setValue(value);
	moveUp(i);
    }

    // Main methods: insert and extractMin

    void insert (Item item) {
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

    Item extractMin () {
        Item min = findMin();
        swap(0, size - 1);
        elems.remove(size - 1);
        size--;
        if (elems.size() > 1){
            moveDown(0);
        }
        return min;
    }

    // For debugging

    boolean checkOrder () {
        try {
            for (int i = size - 1; i > 0; i--) {
                if (getValue(i) < getValue(getDAncestorIndex(i))){
                    return false;
                }
            }
            return true;
        }catch (RootE e){
            throw new Error();
        }
    }
}


