import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class AVLTest {
    BST bst;
    AVL avl;
    BST bst2;
    AVL avl2;
    @Before
    public void setUp() {
        List<Integer> nums = IntStream.range(0, 8).boxed().collect(Collectors.toList());
        Collections.shuffle(nums);
        bst = BST.EBST;
        avl = AVL.EAVL;
        for (int i : nums) {
            bst = bst.BSTinsert(i);
            avl = avl.AVLinsert(i);
        }
    List<Integer> nums2 = IntStream.range(0, 25).boxed().collect(Collectors.toList());
        Collections.shuffle(nums2);
    bst2 = BST.EBST;
    avl2 = AVL.EAVL;
        for (int i : nums2) {
        bst2 = bst2.BSTinsert(i);
        avl2 = avl2.AVLinsert(i);
    }
}
    @Test
    public void toAVL() {
        BST bstone = AVL.toBST(BST.toAVL(bst));
        Iterator<Integer> bstIter = bst.iterator();
        Iterator<Integer> bst2Iter = bst2.iterator();
        while (bstIter.hasNext() && bst2Iter.hasNext()) {
            assertEquals((int)bstIter.next(),(int)bst2Iter.next());
        }

        BST bsttwo = AVL.toBST(BST.toAVL(bst2));
        Iterator<Integer> bstIter2 = bst2.iterator();
        Iterator<Integer> bst2Iter2 = bst2.iterator();
        while (bstIter.hasNext() && bst2Iter.hasNext()) {
            assertEquals((int)bstIter.next(),(int)bst2Iter.next());
        }
    }

    @Test
    public void AVLeasyRight() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(40);
        avl = avl.AVLinsert(50);
        avl = avl.AVLinsert(20);
        avl = avl.AVLinsert(10);
        avl = avl.AVLinsert(30);
        avl = avl.AVLinsert(15);

        avl2 = AVL.EAVL;
        avl2 = avl2.AVLinsert(5);
        avl2 = avl2.AVLinsert(10);
        avl2 = avl2.AVLinsert(20);
        avl2 = avl2.AVLinsert(30);
        avl2 = avl2.AVLinsert(-10);
        avl2 = avl2.AVLinsert(10);
        avl2 = avl2.AVLinsert(15);
        avl2 = avl2.AVLinsert(3);
        avl2 = avl2.AVLinsert(-100);
        avl2 = avl2.AVLinsert(55);
        avl2 = avl2.AVLinsert(23);
        avl2 = avl2.AVLinsert(30);

        AVL left2 = avl2.AVLLeft();
        AVL right2 = avl2.AVLRight();
        assertEquals(5, avl.AVLData());
        assertEquals(10,right2.AVLData());
        assertEquals(15, right2.AVLRight().AVLData());
        assertEquals(20,right2.AVLData());
        assertEquals(30, right2.AVLRight().AVLData());
        assertEquals(-10, left2.AVLLeft().AVLData());
        assertEquals(55,right2.AVLData());
        assertEquals(-100, left2.AVLRight().AVLData());
        assertEquals(23,right2.AVLData());
        assertEquals(30, right2.AVLLeft().AVLData());
        assertEquals(-100, left2.AVLLeft().AVLData());
        assertEquals(3, left2.AVLLeft().AVLData());



        AVL left = avl.AVLLeft();
        AVL right = avl.AVLRight();
        assertEquals(20, avl.AVLData());
        assertEquals(10,left.AVLData());
        assertEquals(15, left.AVLRight().AVLData());
        assertEquals(40,right.AVLData());
        assertEquals(30, right.AVLLeft().AVLData());
        assertEquals(50, right.AVLRight().AVLData());
    }

    @Test
    public void AVLrotateRight() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(40);
        avl = avl.AVLinsert(50);
        avl = avl.AVLinsert(20);
        avl = avl.AVLinsert(10);
        avl = avl.AVLinsert(30);
        avl = avl.AVLinsert(25);

        AVL left = avl.AVLLeft();
        AVL right = avl.AVLRight();
        assertEquals(30, avl.AVLData());
        assertEquals(20,left.AVLData());
        assertEquals(10, left.AVLLeft().AVLData());
        assertEquals(25, left.AVLRight().AVLData());
        assertEquals(40,right.AVLData());
        assertEquals(50, right.AVLRight().AVLData());
    }

    @Test
    public void AVLdelete() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(35);
        avl = avl.AVLinsert(20);
        avl = avl.AVLinsert(40);
        avl = avl.AVLinsert(7);
        avl = avl.AVLinsert(30);
        avl = avl.AVLinsert(50);
        avl = avl.AVLinsert(5);
        avl = avl.AVLinsert(10);

        AVL avl21 = avl.AVLdelete(35);
        AVL left = avl21.AVLLeft();
        AVL right = avl21.AVLRight();
        assertEquals(30, avl21.AVLData());
        assertEquals(7,left.AVLData());
        assertEquals(5, left.AVLLeft().AVLData());
        assertEquals(20, left.AVLRight().AVLData());
        assertEquals(10, left.AVLRight().AVLLeft().AVLData());
        assertEquals(40,right.AVLData());
        assertEquals(50, right.AVLRight().AVLData());

<<<<<<< HEAD
        avl2 = AVL.EAVL;
        avl2 = avl2.AVLinsert(5);
        avl2 = avl2.AVLinsert(10);
        avl2 = avl2.AVLinsert(20);
        avl2 = avl2.AVLinsert(30);
        avl2 = avl2.AVLinsert(-10);
        avl2 = avl2.AVLinsert(10);
        avl2 = avl2.AVLinsert(15);
        avl2 = avl2.AVLinsert(3);
        avl2 = avl2.AVLinsert(-100);
        avl2 = avl2.AVLinsert(55);
        avl2 = avl2.AVLinsert(23);
        avl2 = avl2.AVLinsert(35);
        avl2 = avl2.AVLinsert(0);

        AVL avl23 = avl2.AVLdelete(30);
        AVL left23 = avl23.AVLLeft();
        AVL right23 = avl23.AVLRight();


        AVL avl24 = avl2.AVLdelete(10);
        AVL left24 = avl23.AVLLeft();
        AVL right24 = avl23.AVLRight();

        AVL avl25 = avl2.AVLdelete(55);
        AVL left25 = avl23.AVLLeft();
        AVL right25 = avl23.AVLRight();

        assertEquals(5, avl24.AVLData());
        assertEquals(3,left24.AVLData());
        assertEquals(30, left24.AVLLeft().AVLData());
        assertEquals(20, right24.AVLRight().AVLData());
        assertEquals(-10, left24.AVLRight().AVLLeft().AVLData());
        assertEquals(50, right24.AVLRight().AVLData());

        assertEquals(5, avl25.AVLData());
        assertEquals(20, right25.AVLLeft().AVLData());
        assertEquals(35, right25.AVLRight().AVLData());
        assertEquals(0, left25.AVLLeft().AVLLeft().AVLData());
        assertEquals(50, right25.AVLRight().AVLData());


=======



        int n = 50;
        int l = -20;
        int g = 20;


        avl = new EmptyAVL();
        List<Integer> nums = new ArrayList<>();
        List<Integer> numc = new LinkedList<>();

        HashSet<Integer> seen = new HashSet<Integer>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int r = l + rand.nextInt(g - l);
            if (!seen.contains(r)){
                nums.add(r);
                numc.add(r);
                seen.add(r);
            }
        }
        n = seen.size();
        for (int i = 0; i < n; i++) {

            avl = avl.AVLinsert(nums.get(i));
        }

        try {
            avl.AVLdelete(10000000);
            fail("AVL doesn't throw an exception when a number that is not in the AVL is deleted.");
        } catch (EmptyAVLE e) {
        }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }
}

