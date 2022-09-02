import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import static org.junit.Assert.*;

public class HeapTest {

    @Test
    public void sortBH () {
        ArrayList<Item> items = new ArrayList<>();

        Item it;

        it = new Item("a1",  6);
        items.add(it);

        it = new Item("a2",  1);
        it.reverse();
        items.add(it);

        it = new Item("a3",  8);
        items.add(it);

        it = new Item("a4",  2);
        it.reverse();
        items.add(it);

        it = new Item("a5",  4);
        items.add(it);

        it = new Item("a6",  7);
        items.add(it);

        it = new Item("a7",  9);
        items.add(it);

        it = new Item("a8",  3);
        items.add(it);

        it = new Item("a9",  5);
        items.add(it);

        BinaryHeap bhp = new BinaryHeap(items);
        TreePrinter.print(bhp.findMin());

        for (int i = 1; i < 10; i++) assertEquals(i, bhp.extractMin().getValue());
<<<<<<< HEAD
=======

    }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

    ArrayList<Item> items2 = new ArrayList<>();

    Item it2;

    it2 = new Item("a1",  2);
        items.add(it2);
    it2 = new Item("a1",  5);
        items.add(it2);

    it2 = new Item("a1",  6);
        items.add(it2);

    it2 = new Item("a1",  9);
        items.add(it2);

    it2 = new Item("a1",  4);
        items.add(it2);

    it2 = new Item("a1",  5);
        items.add(it2);
    it2 = new Item("a1",  9);
        items.add(it2);

    BinaryHeap bhp2 = new BinaryHeap(items);
        TreePrinter.print(bhp2.findMin());

        for (int i = 1; i < 7; i++) assertEquals(i, bhp2.extractMin().getValue());


        ArrayList<Item> items3 = new ArrayList<>();

        Item it3;

        it3 = new Item("a1",  18);
        items.add(it3);
        it3 = new Item("a1",  12);
        items.add(it3);

        it3 = new Item("a1",  8);
        items.add(it3);

        it3 = new Item("a1",  9);
        items.add(it3);

        it3 = new Item("a1",  4);
        items.add(it3);

        it3 = new Item("a1",  12);
        items.add(it3);
        it3 = new Item("a1",  0);
        items.add(it3);
        it3 = new Item("a1",  6);
        items.add(it3);
        it3 = new Item("a1",  -2);
        items.add(it3);

        BinaryHeap bhp3 = new BinaryHeap(items);
        TreePrinter.print(bhp3.findMin());

        for (int i = 1; i < 9; i++) assertEquals(i, bhp3.extractMin().getValue());
    }
}

