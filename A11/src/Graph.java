import java.util.*;

public class Graph {

    private ArrayList<Item> nodes;
    private Hashtable<Item, ArrayList<Item>> neighbors;
    private Hashtable<Edge, Integer> weights;

    Graph(
            ArrayList<Item> nodes,
            Hashtable<Item, ArrayList<Item>> neighbors,
            Hashtable<Edge, Integer> weights) {
        this.nodes = nodes;
        this.neighbors = neighbors;
        this.weights = weights;
    }

    // -----

    ArrayList<Item> getNodes() {
        return nodes;
    }

    Hashtable<Item, ArrayList<Item>> getNeighbors() {
        return neighbors;
    }

    Hashtable<Edge, Integer> getWeights() {
        return weights;
    }

    // -----
    // Computes all shortest paths from a given node
    // Nodes are marked with the shortest path to the source

    void allShortestPaths(Item source) {
        // TODO
        for(Item i: nodes){
            i.reset();
        }
        for(Item i: nodes){
            i.setValue(Integer.MAX_VALUE);
        }
        source.setValue(0);
        WeakHeap heap = new WeakHeap(nodes);
        while(!heap.isEmpty()) {
            Item extracted = heap.extractMin();
            if (!extracted.isVisited()) {
                extracted.setVisited(true);
                for (Item i : neighbors.get(extracted)) {
                    int setNewDistance = extracted.getValue() + weights.get(new Edge(extracted,i));
                    if(setNewDistance < i.getValue() && !i.isVisited()){
                        heap.updateKey(i.getPosition(),setNewDistance);
                        i.setPrevious(extracted);
                    }
                }
            }
        }
    }

    // -----
    // Point-to-point shortest path; stops as soon as it reaches destination
    //set all nodevals to infinity
    //set all nodes in weakheap
    //filter through and extract min until getting to destination
    // dont circle back
    // new distance will be the current distance value and the weight
    // dont forget to add the weights as we move along and get val to be > the distance update key
    ArrayList<Edge> shortestPath(Item source, Item dest) {
        // TODO
        for(Item i: nodes){
            i.reset();
        }
        for(Item i: nodes){
            i.setValue(Integer.MAX_VALUE);
        }
        source.setValue(0);
        WeakHeap heap = new WeakHeap(nodes);
        while (!heap.isEmpty()){
            Item extracted = heap.extractMin();
            if(extracted.equals(dest)){
                break;
            }
            if(!extracted.isVisited()){
                extracted.setVisited(true);
                for(Item i : neighbors.get(extracted)){
                    int setNewDistance = extracted.getValue() + weights.get(new Edge(extracted,i));
                    if(setNewDistance < i.getValue()){
                        heap.updateKey(i.getPosition(),setNewDistance);
                        i.setPrevious(extracted);
                    }
                }
            }
        }
        return Item.pathToSource(dest);
    }

    // -----

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Nodes:%n%s%n", nodes));
        res.append(String.format("Neighbors:%n%s%n", neighbors));
        res.append(String.format("Weights:%n%s%n", weights));
        return new String(res);
    }
}
