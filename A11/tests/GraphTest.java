import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void testEdge() {
        Item p1, p2, p3, p4;
        p1 = new Item("A",0);
        p2 = new Item("B", 0);
        p3 = new Item("C", 1);
        p4 = new Item("D", 1);

        assertNotEquals(p1,p2);
        assertNotEquals(p1,p3);
        assertNotEquals(p3,p4);

        Edge e1, e2, e3, e4, f1;
        e1 = new Edge(p1, p2);
        f1 = new Edge(p1,p2);
        e2 = new Edge(p3, p3);
        e3 = new Edge(p2, p4);
        e4 = new Edge(p2, p3);

        assertEquals(e1,f1);
        assertEquals(e1.getFrom(), p1);
        assertEquals(e1.getTo(), p2);
        assertEquals(e2.getFrom(), p3);
        assertNotEquals(e2.getFrom(), p4);
        assertEquals(e2.getFrom(), e2.getTo());
        assertNotEquals(e3, e4);
    }

    @Test
    public void testItem() {
        Item pp1, pp2, pp3, pp4, pp5;

        pp1 = new Item("A", 1);
        pp2 = new Item("B", 2);
        pp3 = new Item("C", 3);
        pp4 = new Item("D", 4);
        pp5 = new Item("E", 5);

        pp1.setPrevious(pp2);
        pp2.setPrevious(pp3);
        pp3.setPrevious(pp4);
        pp4.setPrevious(pp5);

        ArrayList<Edge> path = Item.pathToSource(pp1);
        assertEquals(4, path.size());

        Edge e1, e2, e3, e4;
        e1 = path.get(0);
        e2 = path.get(1);
        e3 = path.get(2);
        e4 = path.get(3);

        assertEquals(e1.getFrom(), pp1);
        assertEquals(e1.getTo(), pp2);

        assertEquals(e2.getFrom(), pp2);
        assertEquals(e2.getTo(), pp3);

        assertEquals(e3.getFrom(), pp3);
        assertEquals(e3.getTo(), pp4);

        assertEquals(e4.getFrom(), pp4);
        assertEquals(e4.getTo(), pp5);
    }

    @Test
    public void testWHeap() {
        Item pp1, pp2, pp3, pp4, pp5;

        pp1 = new Item("A", 50);
        pp2 = new Item("B", 20);
        pp3 = new Item("C", 30);
        pp4 = new Item("D", 10);
        pp5 = new Item("E", 40);

        WeakHeap h = new WeakHeap();
        h.insert(pp1);
        h.insert(pp2);
        h.insert(pp3);
        h.insert(pp4);
        h.insert(pp5);

        ArrayList<Item> sorted;
        sorted = new ArrayList<>(Arrays.asList(pp4, pp2, pp3, pp5, pp1));
        for (int i=0; i<5; i++) {
            assertEquals(sorted.get(i),h.extractMin());
        }

        assertTrue(h.isEmpty());

        h.insert(pp1);
        h.insert(pp2);
        h.insert(pp3);
        h.insert(pp4);
        h.insert(pp5);

        TreePrinter.print(h.findMin());
        h.updateKey(4, 9);
        TreePrinter.print(h.findMin());
        sorted = new ArrayList<>(Arrays.asList(pp1, pp4, pp2, pp3, pp5));
        for (int i=0; i<5; i++) {
            assertEquals(sorted.get(i),h.extractMin());
        }
    }


    @Test
    public void testGraph() {
        Item pp1, pp2, pp3, pp4, pp5, pp6, pp7, pp8, pp9, pp10, pp11, pp12;
        pp1 = new Item("A", 0);
        pp2 = new Item("B", 0);
        pp3 = new Item("C", 0);
        pp4 = new Item("D", 0);
        pp5 = new Item("E", 0);
        pp6 = new Item("F", 0);
        pp7 = new Item("G", 0);
        pp8 = new Item("H", 0);
        pp9 = new Item("I", 0);
        pp10 = new Item("J", 0);
        pp11 = new Item("K", 0);
        pp12 = new Item("L", 0);

        ArrayList<Item> nodes =
                new ArrayList<>(Arrays.asList(pp1, pp2, pp3, pp4, pp5, pp6, pp7, pp8, pp9, pp10, pp11, pp12));

        Hashtable<Item, ArrayList<Item>> neighbors = new Hashtable<>();
        neighbors.put(pp1, new ArrayList<>());
        neighbors.put(pp2, new ArrayList<>(Arrays.asList(pp1)));
        neighbors.put(pp3, new ArrayList<>(Arrays.asList(pp2)));
        neighbors.put(pp4, new ArrayList<>());
        neighbors.put(pp5, new ArrayList<>(Arrays.asList(pp4)));
        neighbors.put(pp6, new ArrayList<>(Arrays.asList(pp3, pp5)));
        neighbors.put(pp7, new ArrayList<>(Arrays.asList(pp6, pp8, pp9, pp10)));
        neighbors.put(pp8, new ArrayList<>(Arrays.asList(pp12)));
        neighbors.put(pp9, new ArrayList<>(Arrays.asList(pp11)));
        neighbors.put(pp10, new ArrayList<>(Arrays.asList(pp12)));
        neighbors.put(pp11, new ArrayList<>(Arrays.asList(pp12)));
        neighbors.put(pp12, new ArrayList<>());

        Edge e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13;
        e1 = new Edge(pp2, pp1);
        e2 = new Edge(pp3, pp2);
        e3 = new Edge(pp5, pp4);
        e4 = new Edge(pp6, pp3);
        e5 = new Edge(pp6, pp5);
        e6 = new Edge(pp7, pp6);
        e7 = new Edge(pp7, pp8);
        e8 = new Edge(pp7, pp9);
        e9 = new Edge(pp7, pp10);
        e10 = new Edge(pp8, pp12);
        e11 = new Edge(pp9, pp11);
        e12 = new Edge(pp10, pp12);
        e13 = new Edge(pp11, pp12);

        Hashtable<Edge, Integer> weights = new Hashtable<>();
        weights.put(e1, 10);
        weights.put(e2, 10);
        weights.put(e3, 10);
        weights.put(e4, 10);
        weights.put(e5, 10);
        weights.put(e6, 10);
        weights.put(e7, 10);
        weights.put(e8, 10);
        weights.put(e9, 10);
        weights.put(e10, 2);
        weights.put(e11, 1);
        weights.put(e12, 1);
        weights.put(e13, 1);

        Graph g = new Graph(nodes, neighbors, weights);

        // all shortest paths from p7
        g.allShortestPaths(pp7);

        assertTrue(pp1.isVisited());
        assertTrue(pp2.isVisited());
        assertTrue(pp3.isVisited());
        assertTrue(pp4.isVisited());
        assertTrue(pp5.isVisited());
        assertTrue(pp6.isVisited());
        assertTrue(pp7.isVisited());
        assertTrue(pp8.isVisited());
        assertTrue(pp9.isVisited());
        assertTrue(pp10.isVisited());
        assertTrue(pp11.isVisited());
        assertTrue(pp12.isVisited());

        assertEquals(pp2, pp1.getPrevious());
        assertEquals(pp3, pp2.getPrevious());
        assertEquals(pp6, pp3.getPrevious());
        assertEquals(pp5, pp4.getPrevious());
        assertEquals(pp6, pp5.getPrevious());
        assertEquals(pp7, pp6.getPrevious());
        assertEquals(pp7, pp8.getPrevious());
        assertEquals(pp7, pp9.getPrevious());
        assertEquals(pp7, pp10.getPrevious());
        assertEquals(pp9, pp11.getPrevious());
        assertEquals(pp10, pp12.getPrevious());

        // shortest path from p7 to p4
        g.shortestPath(pp7, pp4);

        assertFalse(pp1.isVisited());
        assertTrue(pp2.isVisited());
        assertTrue(pp3.isVisited());
        assertTrue(pp5.isVisited());
        assertTrue(pp6.isVisited());
        assertTrue(pp7.isVisited());
        assertTrue(pp8.isVisited());
        assertTrue(pp9.isVisited());
        assertTrue(pp10.isVisited());
        assertTrue(pp11.isVisited());
        assertTrue(pp12.isVisited());

        assertEquals(pp2, pp1.getPrevious());
        assertEquals(pp3, pp2.getPrevious());
        assertEquals(pp6, pp3.getPrevious());
        assertEquals(pp5, pp4.getPrevious());
        assertEquals(pp6, pp5.getPrevious());
        assertEquals(pp7, pp6.getPrevious());
        assertEquals(pp7, pp8.getPrevious());
        assertEquals(pp7, pp9.getPrevious());
        assertEquals(pp7, pp10.getPrevious());
        assertEquals(pp9, pp11.getPrevious());
        assertEquals(pp10, pp12.getPrevious());
    }

    @Test
    public void testGraph2() {
        // Wikipedia example
        Item pp1, pp2, pp3, pp4, pp5, pp6;
        pp1 = new Item("A1", 0);
        pp2 = new Item("A2", 0);
        pp3 = new Item("A3", 0);
        pp4 = new Item("A4", 0);
        pp5 = new Item("A5", 0);
        pp6 = new Item("A6", 0);

        ArrayList<Item> nodes =
                new ArrayList<>(Arrays.asList(pp1, pp2, pp3, pp4, pp5, pp6));

        Hashtable<Item, ArrayList<Item>> neighbors = new Hashtable<>();
        neighbors.put(pp1, new ArrayList<>(Arrays.asList(pp2,pp3,pp6)));
        neighbors.put(pp2, new ArrayList<>(Arrays.asList(pp1,pp3,pp4)));
        neighbors.put(pp3, new ArrayList<>(Arrays.asList(pp1,pp2,pp4,pp6)));
        neighbors.put(pp4, new ArrayList<>(Arrays.asList(pp2,pp3,pp5)));
        neighbors.put(pp5, new ArrayList<>(Arrays.asList(pp4,pp6)));
        neighbors.put(pp6, new ArrayList<>(Arrays.asList(pp1, pp3, pp5)));

        Edge e1, e2, e3, e4, e5, e6, e7, e8, e9;
        Edge e10, e11, e12, e13, e14, e15, e16, e17, e18;
        e1 = new Edge(pp1, pp2);
        e2 = new Edge(pp1, pp3);
        e3 = new Edge(pp1, pp6);
        e4 = new Edge(pp2, pp1);
        e5 = new Edge(pp2, pp3);
        e6 = new Edge(pp2, pp4);
        e7 = new Edge(pp3, pp1);
        e8 = new Edge(pp3, pp2);
        e9 = new Edge(pp3, pp4);
        e10 = new Edge(pp3, pp6);
        e11 = new Edge(pp4, pp2);
        e12 = new Edge(pp4, pp3);
        e13 = new Edge(pp4, pp5);
        e14 = new Edge(pp5, pp4);
        e15 = new Edge(pp5, pp6);
        e16 = new Edge(pp6, pp1);
        e17 = new Edge(pp6, pp3);
        e18 = new Edge(pp6, pp5);

        Hashtable<Edge, Integer> weights = new Hashtable<>();
        weights.put(e1, 7);
        weights.put(e2, 9);
        weights.put(e3, 14);
        weights.put(e4, 7);
        weights.put(e5, 10);
        weights.put(e6, 15);
        weights.put(e7, 9);
        weights.put(e8, 10);
        weights.put(e9, 11);
        weights.put(e10, 2);
        weights.put(e11, 15);
        weights.put(e12, 11);
        weights.put(e13, 6);
        weights.put(e14, 6);
        weights.put(e15, 9);
        weights.put(e16, 14);
        weights.put(e17, 2);
        weights.put(e18, 9);

        Graph g = new Graph(nodes, neighbors, weights);

        // shortest path from p1 to p5
        g.shortestPath(pp1, pp5);
        ArrayList<Edge> path = Item.pathToSource(pp5);
        assertEquals(path.get(0).getFrom(), pp5);
        assertEquals(path.get(1).getFrom(), pp6);
        assertEquals(path.get(2).getFrom(), pp3);
        assertEquals(path.get(2).getTo(), pp1);

        int distance = path.stream().mapToInt(g.getWeights()::get).sum();
        assertEquals(20, distance);
    }

    @Test
    public void exampleTest() {
        /*same as the one sent under announcements
               6
       A ------------- C
       | \  5     8  / |
       |   \       /   |
       |     \   /     | 10
       |       B       |
      7|  4  /   \  6  |
       |   /       \   |
       | /           \ |
       E---------------D
               4
         */
        Item pp1, pp2, pp3, pp4, pp5;
        pp1 = new Item("A", 0);
        pp2 = new Item("B", 0);
        pp3 = new Item("C", 0);
        pp4 = new Item("D", 0);
        pp5 = new Item("E", 0);


        ArrayList<Item> nodes =
                new ArrayList<>(Arrays.asList(pp1, pp2, pp3, pp4,pp5));

        Hashtable<Item, ArrayList<Item>> neighbors = new Hashtable<>();
        neighbors.put(pp1, new ArrayList<>(Arrays.asList(pp2,pp3,pp5)));
        neighbors.put(pp2, new ArrayList<>(Arrays.asList(pp1,pp3,pp4,pp5)));
        neighbors.put(pp3, new ArrayList<>(Arrays.asList(pp1,pp2,pp4)));
        neighbors.put(pp4, new ArrayList<>(Arrays.asList(pp2,pp3,pp5)));
        neighbors.put(pp5, new ArrayList<>(Arrays.asList(pp1,pp2,pp4)));

        Edge e1, e2, e3, e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16;
        e1 = new Edge(pp1, pp2);
        e2 = new Edge(pp1, pp3);
        e3 = new Edge(pp1, pp5);

        e4 = new Edge(pp2, pp1);
        e5 = new Edge(pp2, pp3);
        e6 = new Edge(pp2, pp4);
        e7 = new Edge(pp2, pp5);

        e8 = new Edge(pp3, pp1);
        e9 = new Edge(pp3, pp2);
        e10 = new Edge(pp3, pp4);

        e11 = new Edge(pp4, pp2);
        e12 = new Edge(pp4, pp3);
        e13 = new Edge(pp4, pp5);

        e14 = new Edge(pp5, pp1);
        e15 = new Edge(pp5, pp2);
        e16 = new Edge(pp5, pp4);

        Hashtable<Edge, Integer> weights = new Hashtable<>();
        weights.put(e1, 5);
        weights.put(e2, 6);
        weights.put(e3, 7);

        weights.put(e4, 5);
        weights.put(e5, 8);
        weights.put(e6, 6);
        weights.put(e7, 4);

        weights.put(e8, 6);
        weights.put(e9, 8);
        weights.put(e10, 10);

        weights.put(e11, 10);
        weights.put(e12, 6);
        weights.put(e13, 4);

        weights.put(e14, 4);
        weights.put(e15, 4);
        weights.put(e16, 7);

        Graph g = new Graph(nodes, neighbors, weights);
        //A's
        g.shortestPath(pp1, pp2);
        ArrayList<Edge> path = Item.pathToSource(pp2);
        assertEquals(path.get(0).getFrom(), pp2);
        g.shortestPath(pp1, pp3);
        ArrayList<Edge> path2 = Item.pathToSource(pp3);
        assertEquals(path2.get(0).getFrom(), pp3);
        g.shortestPath(pp1, pp4);
        ArrayList<Edge> path3 = Item.pathToSource(pp4);
        assertEquals(path3.get(0).getFrom(), pp4);
        assertEquals(path3.get(1).getFrom(), pp2);
        g.shortestPath(pp1, pp5);
        ArrayList<Edge> path4 = Item.pathToSource(pp5);
        assertEquals(path4.get(0).getFrom(), pp5);

        //B's
        g.shortestPath(pp2, pp1);
        ArrayList<Edge> path1 = Item.pathToSource(pp1);
        assertEquals(path1.get(0).getFrom(), pp1);
        g.shortestPath(pp2, pp3);
        ArrayList<Edge> path21 = Item.pathToSource(pp3);
        assertEquals(path21.get(0).getFrom(), pp3);
        g.shortestPath(pp2, pp4);
        ArrayList<Edge> path31 = Item.pathToSource(pp4);
        assertEquals(path31.get(0).getFrom(), pp4);
        g.shortestPath(pp2, pp5);
        ArrayList<Edge> path41 = Item.pathToSource(pp5);
        assertEquals(path41.get(0).getFrom(), pp5);

        //C's
        g.shortestPath(pp3, pp1);
        ArrayList<Edge> path35 = Item.pathToSource(pp1);
        assertEquals(path35.get(0).getFrom(), pp1);
        g.shortestPath(pp3, pp2);
        ArrayList<Edge> path23 = Item.pathToSource(pp2);
        assertEquals(path23.get(0).getFrom(), pp2);
        g.shortestPath(pp3, pp4);
        ArrayList<Edge> path33 = Item.pathToSource(pp4);
        assertEquals(path33.get(0).getFrom(), pp4);
        g.shortestPath(pp3, pp5);
        ArrayList<Edge> path43 = Item.pathToSource(pp5);
        assertEquals(path43.get(1).getFrom(), pp2);
        assertEquals(path33.get(0).getFrom(), pp4);

        //D's
        g.shortestPath(pp4, pp1);
        ArrayList<Edge> path45 = Item.pathToSource(pp1);
        assertEquals(path45.get(0).getFrom(), pp1);
        assertEquals(path45.get(1).getFrom(), pp5);
        g.shortestPath(pp4, pp2);
        ArrayList<Edge> path24 = Item.pathToSource(pp2);
        assertEquals(path24.get(0).getFrom(), pp2);
        g.shortestPath(pp4, pp3);
        ArrayList<Edge> path34 = Item.pathToSource(pp3);
        assertEquals(path34.get(0).getFrom(), pp3);
        g.shortestPath(pp4, pp5);
        ArrayList<Edge> path44= Item.pathToSource(pp5);
        assertEquals(path44.get(0).getFrom(), pp5);

        //E's
        g.shortestPath(pp5, pp2);
        ArrayList<Edge> path55 = Item.pathToSource(pp2);
        assertEquals(path55.get(0).getFrom(), pp2);
        g.shortestPath(pp5, pp3);
        ArrayList<Edge> path25 = Item.pathToSource(pp3);
        assertEquals(path25.get(0).getFrom(), pp3);
        assertEquals(path25.get(1).getFrom(), pp1);
        g.shortestPath(pp5, pp4);
        ArrayList<Edge> path36 = Item.pathToSource(pp4);
        assertEquals(path36.get(0).getFrom(), pp4);
        g.shortestPath(pp5, pp1);
        ArrayList<Edge> path46= Item.pathToSource(pp1);
        assertEquals(path46.get(0).getFrom(), pp1);


        g.shortestPath(pp1, pp2);


    }
    @Test
    public void exampleTest2() {
        /*same as the one sent under announcements
           10      300
       A ----- B ----- C
        \             /
         \           /
       20 \         / 20
           \       /
            \     /
             \   /
               D
         */
        Item pp1, pp2, pp3, pp4, pp5;
        pp1 = new Item("A", 0);
        pp2 = new Item("B", 0);
        pp3 = new Item("C", 0);
        pp4 = new Item("D", 0);


        ArrayList<Item> nodes =
                new ArrayList<>(Arrays.asList(pp1, pp2, pp3, pp4));

        Hashtable<Item, ArrayList<Item>> neighbors = new Hashtable<>();
        neighbors.put(pp2, new ArrayList<>(Arrays.asList(pp1)));
        neighbors.put(pp3, new ArrayList<>(Arrays.asList(pp2)));
        neighbors.put(pp4, new ArrayList<>(Arrays.asList(pp3)));
        neighbors.put(pp1, new ArrayList<>(Arrays.asList(pp4)));

        Edge e1, e2, e3, e4,e5,e6,e7,e8;
        e1 = new Edge(pp1, pp2);
        e2 = new Edge(pp2, pp1);
        e3 = new Edge(pp2, pp3);
        e4 = new Edge(pp3, pp2);
        e5 = new Edge(pp3, pp4);
        e6 = new Edge(pp4, pp3);
        e7 = new Edge(pp4, pp1);
        e8 = new Edge(pp1, pp4);


        Hashtable<Edge, Integer> weights = new Hashtable<>();
        weights.put(e1, 10);
        weights.put(e2, 10);
        weights.put(e3, 300);
        weights.put(e4, 300);
        weights.put(e5, 20);
        weights.put(e6, 20);
        weights.put(e7, 20);
        weights.put(e8, 20);


        Graph g = new Graph(nodes, neighbors, weights);

    }
}