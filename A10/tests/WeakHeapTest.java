import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.WeakHashMap;

import static org.junit.Assert.*;

public class WeakHeapTest {
    private WeakHeap wh1;
    private WeakHeap wh2;
    private WeakHeap wh3;
    private WeakHeap wh4;

    @Before
    public void setUp() {
        wh1 = new WeakHeap();
        wh1.insert(new Item(30));
        wh1.insert(new Item(50));
        wh1.insert(new Item(40));
        wh1.insert(new Item(70));
        wh1.insert(new Item(80));
        wh1.insert(new Item(60));
        wh1.insert(new Item(170));
        wh1.insert(new Item(110));
        assertTrue(wh1.checkOrder());
        /*
                                30_0
                                  └───────────────────┐
                                                    50_1
                                            ┌─────────┴─────────┐
                                          40_2                70_3
                                       ┌────┴────┐         ┌────┴────┐
                                     80_4      60_5      170_6     110_7
         */

        wh2 = new WeakHeap();
        wh2.insert(new Item(5));
        wh2.insert(new Item(10));
        wh2.insert(new Item(30));
        wh2.insert(new Item(7));
        wh2.insert(new Item(8));
        wh2.insert(new Item(29));
        wh2.insert(new Item(12));
        wh2.insert(new Item(11));
        wh2.insert(new Item(13));
        assertTrue(wh2.checkOrder());

        wh3 = new WeakHeap();
        wh3.insert(new Item(0));
        wh3.insert(new Item(10));
        wh3.insert(new Item(30));
        wh3.insert(new Item(39));
        wh3.insert(new Item(80));
        wh3.insert(new Item(86));
        wh3.insert(new Item(112));
        wh3.insert(new Item(143));
        assertTrue(wh3.checkOrder());

        wh4 = new WeakHeap();
        wh3.insert(new Item(0));
    }

    @Test
    public void getParentIndex() throws RootE {
        assertEquals(70, wh1.getValue(wh1.getParentIndex(7)));
        assertEquals(70, wh1.getValue(wh1.getParentIndex(6)));
        assertEquals(40, wh1.getValue(wh1.getParentIndex(5)));
        assertEquals(40, wh1.getValue(wh1.getParentIndex(4)));
        assertEquals(50, wh1.getValue(wh1.getParentIndex(3)));
        assertEquals(50, wh1.getValue(wh1.getParentIndex(2)));
        assertEquals(30, wh1.getValue(wh1.getParentIndex(1)));

        assertEquals(8, wh2.getValue(wh2.getParentIndex(8)));
        assertEquals(10, wh2.getValue(wh2.getParentIndex(7)));
        assertEquals(10, wh2.getValue(wh2.getParentIndex(6)));
        assertEquals(29, wh2.getValue(wh2.getParentIndex(5)));
        assertEquals(29, wh2.getValue(wh2.getParentIndex(4)));
        assertEquals(7, wh2.getValue(wh2.getParentIndex(3)));
        assertEquals(7, wh2.getValue(wh2.getParentIndex(2)));
        assertEquals(5, wh2.getValue(wh2.getParentIndex(1)));

        assertEquals(80, wh3.getValue(wh3.getParentIndex(8)));
        assertEquals(39, wh3.getValue(wh3.getParentIndex(7)));
        assertEquals(39, wh3.getValue(wh3.getParentIndex(6)));
        assertEquals(30, wh3.getValue(wh3.getParentIndex(5)));
        assertEquals(30, wh3.getValue(wh3.getParentIndex(4)));
        assertEquals(10, wh3.getValue(wh3.getParentIndex(3)));
        assertEquals(10, wh3.getValue(wh3.getParentIndex(2)));
        assertEquals(0, wh3.getValue(wh3.getParentIndex(1)));

        // RootE no parent assertEquals(0, wh4.getValue(wh4.getParentIndex(0)));

    }

    @Test
    public void getLeftChildIndex() throws NoLeftChildE {
        assertEquals(40, wh1.getValue(wh1.getLeftChildIndex(1)));
        assertEquals(80, wh1.getValue(wh1.getLeftChildIndex(2)));
        assertEquals(170, wh1.getValue(wh1.getLeftChildIndex(3)));

        assertEquals(13, wh2.getValue(wh2.getLeftChildIndex(4)));
        assertEquals(12, wh2.getValue(wh2.getLeftChildIndex(3)));
        assertEquals(8, wh2.getValue(wh2.getLeftChildIndex(2)));

        assertEquals(30, wh3.getValue(wh3.getLeftChildIndex(1)));
        assertEquals(80, wh3.getValue(wh3.getLeftChildIndex(2)));
        assertEquals(112, wh3.getValue(wh3.getLeftChildIndex(3)));
        assertEquals(0, wh3.getValue(wh3.getLeftChildIndex(4)));

        //0 has no children
    }

    @Test
    public void getRightChildIndex() throws NoRightChildE {
        assertEquals(50, wh1.getValue(wh1.getRightChildIndex(0)));
        assertEquals(70, wh1.getValue(wh1.getRightChildIndex(1)));
        assertEquals(60, wh1.getValue(wh1.getRightChildIndex(2)));
        assertEquals(110, wh1.getValue(wh1.getRightChildIndex(3)));

        assertEquals(7, wh2.getValue(wh2.getRightChildIndex(0)));
        assertEquals(10, wh2.getValue(wh2.getRightChildIndex(1)));
        assertEquals(30, wh2.getValue(wh2.getRightChildIndex(2)));
        assertEquals(11, wh2.getValue(wh2.getRightChildIndex(3)));

        assertEquals(10, wh3.getValue(wh3.getRightChildIndex(0)));
        assertEquals(39, wh3.getValue(wh3.getRightChildIndex(1)));
        assertEquals(86, wh3.getValue(wh3.getRightChildIndex(2)));
        assertEquals(143, wh3.getValue(wh3.getRightChildIndex(3)));
        //0 has no right children
    }

    @Test
    public void isLeftChild() throws RootE {
        assertTrue(wh1.isLeftChild(2));
        assertTrue(wh1.isLeftChild(4));
        assertTrue(wh1.isLeftChild(6));
        assertFalse(wh1.isLeftChild(1));
        assertFalse(wh1.isLeftChild(3));
        assertFalse(wh1.isLeftChild(5));
        assertFalse(wh1.isLeftChild(7));

        assertFalse(wh2.isLeftChild(1));
        assertTrue(wh2.isLeftChild(2));
        assertFalse(wh2.isLeftChild(3));
        assertTrue(wh2.isLeftChild(4));
        assertFalse(wh2.isLeftChild(5));
        assertTrue(wh2.isLeftChild(6));
        assertFalse(wh2.isLeftChild(7));
        assertTrue(wh2.isLeftChild(8));
    }

    @Test
    public void isRightChild() throws RootE {
        assertTrue(wh1.isRightChild(1));
        assertTrue(wh1.isRightChild(3));
        assertTrue(wh1.isRightChild(5));
        assertTrue(wh1.isRightChild(7));
        assertFalse(wh1.isRightChild(2));
        assertFalse(wh1.isRightChild(4));
        assertFalse(wh1.isRightChild(6));

        assertTrue(wh3.isRightChild(1));
        assertTrue(wh3.isRightChild(3));
        assertTrue(wh3.isRightChild(5));
        assertTrue(wh3.isRightChild(7));
        assertFalse(wh3.isRightChild(2));
        assertFalse(wh3.isRightChild(4));
        assertFalse(wh3.isRightChild(6));
    }

    @Test
    public void getDAncestorIndex() throws RootE {
        assertEquals(0, wh1.getDAncestorIndex(1));
        assertEquals(0, wh1.getDAncestorIndex(2));
        assertEquals(1, wh1.getDAncestorIndex(3));
        assertEquals(0, wh1.getDAncestorIndex(4));
        assertEquals(2, wh1.getDAncestorIndex(5));
        assertEquals(1, wh1.getDAncestorIndex(6));
        assertEquals(3, wh1.getDAncestorIndex(7));

        assertEquals(0, wh2.getDAncestorIndex(1));
        assertEquals(0, wh2.getDAncestorIndex(2));
        assertEquals(1, wh2.getDAncestorIndex(3));
        assertEquals(0, wh2.getDAncestorIndex(4));
        assertEquals(2, wh2.getDAncestorIndex(5));
        assertEquals(1, wh2.getDAncestorIndex(6));
        assertEquals(3, wh2.getDAncestorIndex(7));
    }

    @Test
    public void getLeftMostChildIndex() throws NoLeftChildE {
        assertEquals(4, wh1.getLeftMostChildIndex());

        assertEquals(8, wh2.getLeftMostChildIndex());

        assertEquals(8, wh3.getLeftMostChildIndex());
    }

    @Test
    public void swap() {
        assertEquals(50, wh1.getValue(1));
        assertEquals(40, wh1.getValue(2));
        wh1.swap(1, 2);
        assertEquals(50, wh1.getValue(2));
        assertEquals(40, wh1.getValue(1));

        assertEquals(29, wh2.getValue(2));
        assertEquals(10, wh2.getValue(3));
        wh1.swap(2, 3);
        assertEquals(10, wh2.getValue(3));
        assertEquals(29, wh2.getValue(2));

        assertEquals(0, wh3.getValue(0));
        assertEquals(143, wh3.getValue(7));
        wh1.swap(0, 7);
        assertEquals(143, wh3.getValue(7));
        assertEquals(0, wh3.getValue(0));
        wh1.swap(0, 7);
        assertEquals(0, wh3.getValue(0));
        assertEquals(143, wh3.getValue(7));

    }

    @Test
    public void join() {
        assertTrue(wh1.join(0, 4));
        assertTrue(wh2.join(0, 4));
        assertTrue(wh3.join(0, 4));

        wh1.getElem(4).setValue(10);
        assertFalse(wh1.join(0, 4));
        assertEquals(10, wh1.getValue(0));
        assertEquals(50, wh1.getValue(1));
        assertEquals(40, wh1.getValue(2));
        assertEquals(70, wh1.getValue(3));
        assertEquals(30, wh1.getValue(4));
        assertEquals(60, wh1.getValue(5));
        assertEquals(170, wh1.getValue(6));
        assertEquals(110, wh1.getValue(7));

        wh1.getElem(2).setValue(5);
        assertFalse(wh1.join(0, 2));
        assertEquals(5, wh1.getValue(0));
        assertEquals(50, wh1.getValue(1));
        assertEquals(10, wh1.getValue(2));
        assertEquals(70, wh1.getValue(3));
        assertEquals(30, wh1.getValue(4));
        assertEquals(60, wh1.getValue(5));
        assertEquals(170, wh1.getValue(6));
        assertEquals(110, wh1.getValue(7));
        assertEquals(1, wh1.getFlip(2));
    }

    @Test
    public void weakHeapify() {

        Random r = new Random(1);
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            items.add(new Item(r.nextInt(100)));
        }
        WeakHeap wh = new WeakHeap(items);
        assertEquals(4, wh.getValue(0));
        assertEquals(6, wh.getValue(1));
        assertEquals(48, wh.getValue(2));
        assertEquals(17, wh.getValue(3));
        assertEquals(78, wh.getValue(4));
        assertEquals(47, wh.getValue(5));
        assertEquals(34, wh.getValue(6));
        assertEquals(13, wh.getValue(7));
        assertEquals(85, wh.getValue(8));
        assertEquals(54, wh.getValue(9));
        assertEquals(69, wh.getValue(10));
        assertEquals(73, wh.getValue(11));
        assertEquals(88, wh.getValue(12));
        assertEquals(63, wh.getValue(13));
        assertEquals(1, wh.getFlip(2));
        assertEquals(1, wh.getFlip(3));
        assertEquals(1, wh.getFlip(4));
        assertEquals(1, wh.getFlip(5));
        assertTrue(wh.checkOrder());
    }

    @Test
    public void moveUp() {
        wh1.getElem(7).setValue(0);
        wh1.moveUp(7);
        assertEquals(0, wh1.getValue(0));
        assertEquals(30, wh1.getValue(1));
        assertEquals(40, wh1.getValue(2));
        assertEquals(50, wh1.getValue(3));
        assertEquals(80, wh1.getValue(4));
        assertEquals(60, wh1.getValue(5));
        assertEquals(170, wh1.getValue(6));
        assertEquals(70, wh1.getValue(7));
        assertEquals(1, wh1.getFlip(1));
        assertEquals(1, wh1.getFlip(3));
        assertTrue(wh1.checkOrder());

        wh1.getElem(4).setValue(2);
        wh1.moveUp(4);
        assertEquals(0, wh1.getValue(0));
        assertEquals(2, wh1.getValue(1));
        assertEquals(40, wh1.getValue(2));
        assertEquals(50, wh1.getValue(3));
        assertEquals(30, wh1.getValue(4));
        assertEquals(60, wh1.getValue(5));
        assertEquals(170, wh1.getValue(6));
        assertEquals(70, wh1.getValue(7));
        assertEquals(1, wh1.getFlip(1));
        assertEquals(1, wh1.getFlip(3));
        assertTrue(wh1.checkOrder());
    }

    @Test
    public void insert() {
        wh1.insert(new Item(10));
        assertTrue(wh1.checkOrder());
        wh1.insert(new Item(11));
        assertTrue(wh1.checkOrder());
        wh1.insert(new Item(12));
        assertTrue(wh1.checkOrder());
        wh1.insert(new Item(13));
        assertTrue(wh1.checkOrder());
        wh1.insert(new Item(14));
        assertTrue(wh1.checkOrder());

        wh2.insert(new Item(10));
        assertTrue(wh2.checkOrder());
        wh2.insert(new Item(20));
        assertTrue(wh2.checkOrder());
        wh2.insert(new Item(15));
        assertTrue(wh2.checkOrder());
        wh2.insert(new Item(13));
        assertTrue(wh2.checkOrder());
        wh2.insert(new Item(35));
        assertTrue(wh2.checkOrder());

        wh3.insert(new Item(1));
        assertTrue(wh3.checkOrder());
        wh3.insert(new Item(40));
        assertTrue(wh3.checkOrder());
        wh3.insert(new Item(55));
        assertTrue(wh3.checkOrder());
        wh3.insert(new Item(60));
        assertTrue(wh3.checkOrder());
        wh3.insert(new Item(67));
        assertTrue(wh3.checkOrder());

        wh4.insert(new Item(1));
        assertFalse(wh4.checkOrder());
    }

    @Test
    public void extractMin() {
        WeakHeap wh = new WeakHeap();
        wh.insert(new Item(1));
        wh.insert(new Item(10));
        wh.insert(new Item(50));
        wh.insert(new Item(11));
        wh.insert(new Item(80));
        wh.insert(new Item(60));
        wh.insert(new Item(12));
        wh.insert(new Item(15));
        wh.insert(new Item(75));
        wh.insert(new Item(90));
        wh.insert(new Item(55));
        wh.insert(new Item(70));
        wh.insert(new Item(13));
        wh.insert(new Item(14));
        wh.insert(new Item(16));
        wh.insert(new Item(100));
        wh.checkOrder();

        wh.extractMin();
        assertEquals(10, wh.getValue(0));
        assertEquals(50, wh.getValue(1));
        assertEquals(75, wh.getValue(2));
        assertEquals(11, wh.getValue(3));
        assertEquals(80, wh.getValue(4));
        assertEquals(60, wh.getValue(5));
        assertEquals(12, wh.getValue(6));
        assertEquals(15, wh.getValue(7));
        assertEquals(100, wh.getValue(8));
        assertEquals(90, wh.getValue(9));
        assertEquals(55, wh.getValue(10));
        assertEquals(70, wh.getValue(11));
        assertEquals(13, wh.getValue(12));
        assertEquals(14, wh.getValue(13));
        assertEquals(16, wh.getValue(14));
        assertEquals(1, wh.getFlip(1));
        assertEquals(1, wh.getFlip(2));
        assertEquals(1, wh.getFlip(8));

        wh2.extractMin();
        wh2.extractMin();
        assertEquals(8, wh2.getValue(0));
        assertEquals(10, wh2.getValue(1));
        assertEquals(29, wh2.getValue(2));
        assertEquals(11, wh2.getValue(3));
        assertEquals(13, wh2.getValue(4));
        assertEquals(30, wh2.getValue(5));
        assertEquals(12, wh2.getValue(6));

        assertEquals(0, wh2.getFlip(1));
        assertEquals(0, wh2.getFlip(2));
        assertEquals(0, wh2.getFlip(8));

        wh3.extractMin();
        wh3.extractMin();
        assertEquals(10, wh3.getValue(0));
        assertEquals(30, wh3.getValue(1));
        assertEquals(80, wh3.getValue(2));
        assertEquals(39, wh3.getValue(3));
        assertEquals(143, wh3.getValue(4));
        assertEquals(86, wh3.getValue(5));

        assertEquals(1, wh3.getFlip(1));
        assertEquals(1, wh3.getFlip(2));
        assertEquals(0, wh3.getFlip(3));
    }
    @Test (expected = RootE.class)
    public void getParentIndex2() throws RootE{
        wh2.getParentIndex(10);
    }
    @Test (expected = RootE.class)
    public void getParentInndex3() throws RootE{
        wh2.getParentIndex(30);
    }
    @Test (expected = NoLeftChildE.class)
    public void getLeftChildIndex2() throws NoLeftChildE{
        wh2.getLeftChildIndex(10);
    }
    @Test (expected = NoLeftChildE.class)
    public void getLeftChildIndex3() throws NoLeftChildE{
        wh2.getLeftChildIndex(30);
    }
    @Test (expected = NoRightChildE.class)
    public void getRightChildIndex2() throws NoRightChildE{
        wh2.getRightChildIndex(10);
    }
    @Test (expected = NoRightChildE.class)
    public void getRightChildIndex3() throws NoRightChildE{
        wh2.getRightChildIndex(30);
    }
    @Test (expected = RootE.class)
    public void getIsRightChild2() throws RootE{
        wh2.isRightChild(0);
    }
    @Test (expected = RootE.class)
    public void getIsRightChild3() throws RootE{
        wh2.isRightChild(30);
    }
}
