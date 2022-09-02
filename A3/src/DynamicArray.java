import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Optional;

public class DynamicArray<E> implements StackI<E>, QueueI<E>, DequeI<E> {
    private Optional<E>[] elements;
    private int capacity, front, back, size;
    //
    // data stored in locations:
    // front+1, front+2, ... back-2, back-1 (all mod capacity)
    //
    // common cases:
    // front points to an empty location
    // back points to an empty location
    // adding to front decreases 'front' by 1
    // adding to back increases 'back' by 1
    // removing does the opposite
    //
    // |-------------------------|
    // | 4 5 6 _ _ _ _ _ _ 1 2 3 |
    // |-------------------------|
    //         /\        /\      /\
    //        back      front  capacity
    //
    @SuppressWarnings("unchecked")
    DynamicArray (int initialCapacity) {
        elements = (Optional<E>[]) Array.newInstance(Optional.class, initialCapacity);
        Arrays.fill(elements, Optional.empty());
        capacity = initialCapacity;
        front = capacity - 1;
        back = 0;
        size = 0;
    }
    Hashtable
    // Complete the definitions of the following methods from the interfaces

    @Override
    public void clear() {
        Arrays.fill(elements,Optional.empty());
    }

    public int size () {
	return size;
    }
    //stack methods delegate to dequeue
    public void push(E item) {
        addFirst(item);
    }

    public E peek() throws NoSuchElementE {
        return getFirst();
    }

    public E pop() throws NoSuchElementE {
        return removeFirst();
    }
    //queue methods delegate to dequeue methods
    public void offer(E elem) {
        addLast(elem);
    }

    public E poll() throws NoSuchElementE {
        return getFirst();
    }

    public E remove() throws NoSuchElementE {
        return removeLast();
    }
    //dequeue methods this is the hard part
    public void addFirst(E elem) {
        //check if array is full
        if(size == capacity){
            doubleCapacity();
        }

        elements[front] = Optional.of(elem);
        front = Math.floorMod(front - 1, capacity);
        size++;
    }

    public void addLast(E elem) {
        if(size == capacity){
            doubleCapacity();
        }

        elements[back] = Optional.of(elem);
        back = Math.floorMod(back + 1, capacity);
        size++;

    }

    public E getFirst() throws NoSuchElementE {

        return  elements[Math.floorMod(front + 1, capacity)].get();
    }

    public E getLast() throws NoSuchElementE {
        return elements[Math.floorMod(back - 1,capacity)].get();
    }

    public E removeFirst() throws NoSuchElementE {
        try {
            E tempFirst = getFirst();
            elements[front] = Optional.empty();
            front = Math.floorMod(front + 1, capacity);
            size--;
            return tempFirst;
        } catch (NoSuchElementE e){
            throw e;
        }
    }

    public E removeLast() throws NoSuchElementE {

        E tempFirst= getFirst();
        elements[back] = Optional.empty();
        back = Math.floorMod(front - 1, capacity);
        size--;
        return tempFirst;
    }

    void doubleCapacity() {
        /*
        newElements..
        fill
        copy old elements to newElements
        loop from i=0 to capacity -1
        newWlelemnts[i]
         */
        Optional<E>[] newElements;
        int newCapacity = capacity * 2;
        newElements = (Optional<E>[]) Array.newInstance(Optional.class, newCapacity);
        Arrays.fill(newElements, Optional.empty());
        for(int i = 0; i < capacity;i++){
            newElements[i] = elements[Math.floorMod(front+1+i,capacity)];
        }
        capacity = newCapacity;
        back = 0;
        front = capacity - 1;
        elements = newElements;
    }

    // the following methods are used for debugging and testing;
    // please do not edit them

    Optional<E>[] getElements () { return elements; }

    int getCapacity () { return capacity; }

    int getFront () { return front; }

    int getBack () { return back; }

}
