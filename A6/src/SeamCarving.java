import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class SeamCarving {
    private int[] pixels;
    private int type, height, width;
    Map<Position,Pair<List<Position>, Integer>> hash = new WeakHashMap<>();
    // Field getters

    int[] getPixels() {
        return pixels;
    }

    int getType() {
        return type;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    // Read and write images

    void readImage(String filename) throws IOException {
        BufferedImage image = ImageIO.read(new File(filename));
        type = image.getType();
        height = image.getHeight();
        width = image.getWidth();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }

<<<<<<< HEAD
    void writeImage (String filename) throws IOException {
        BufferedImage image = new BufferedImage(width,height,type);
        image.setRGB(0,0, width , height , pixels ,0 , width);
=======
    void writeImage(String filename) throws IOException {
        BufferedImage image = new BufferedImage(width, height, type);
        image.setRGB(0, 0, width, height, pixels, 0, width);
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        ImageIO.write(image, "jpg", new File(filename));
    }

    // Accessing pixels and their neighbors

    Color getColor(int h, int w) {
        int pixel = pixels[w + h * width];
        return new Color(pixel, true);
    }

    ArrayList<Position> getHVneighbors(int h, int w) {
<<<<<<< HEAD
        ArrayList<Position> neigbours = new ArrayList<>();
        for(int i = 1; i >= -1; i= i -2){
            if((h + i >= 0) && (h + i) < height){
                neigbours.add(new Position(h + i , w));
             }
            if((w + i >= 0) && (w + i) < width){
                neigbours.add(new Position(h, w + i));
            }
        }
        return neigbours;
=======
        ArrayList<Position> neighbors = new ArrayList<>();

        if (w == 0) neighbors.add(new Position(h, w + 1));
        else if (w + 1 == width) neighbors.add(new Position(h, w - 1));
        else {
            neighbors.add(new Position(h, w - 1));
            neighbors.add(new Position(h, w + 1));
        }

        if (h == 0) neighbors.add(new Position(h + 1, w));
        else if (h + 1 == height) neighbors.add(new Position(h - 1, w));
        else {
            neighbors.add(new Position(h + 1, w));
            neighbors.add(new Position(h - 1, w));
        }
        return neighbors;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    ArrayList<Position> getBelowNeighbors(int h, int w) {
<<<<<<< HEAD
        ArrayList<Position> belowNeighbors = new ArrayList<>();
        if(h!=height-1){
            belowNeighbors.add(new Position(h+1, w));
        }
        if(h!=height-1&& w!= width-1){
            belowNeighbors.add(new Position(h+1,w+1));
        }
        if(h!=height-1 && w != 0){
            belowNeighbors.add(new Position(h+1, w -1));
        }
        return belowNeighbors;
=======
        ArrayList<Position> neighbors = new ArrayList<>();
        if (h + 1 == height) return neighbors;
        neighbors.add(new Position(h + 1, w));
        if (w == 0) {
            neighbors.add(new Position(h + 1, w + 1));
            return neighbors;
        } else if (w + 1 == width) {
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        } else {
            neighbors.add(new Position(h + 1, w + 1));
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    // Computing energy at given pixel

    int computeEnergy(int h, int w) {
<<<<<<< HEAD
        ArrayList<Position> others = getHVneighbors(h, w);
        int newH;
        int newW;
        int count = 0;
        for (int i = 0; i < others.size(); i++) {
            newH = others.get(i).getFirst();
            newW = others.get(i).getSecond();
            count+= Math.pow(getColor(h,w).getRed()-getColor(newH,newW).getRed(),2);
            count+= Math.pow(getColor(h,w).getGreen()-getColor(newH,newW).getGreen(),2);
            count+= Math.pow(getColor(h,w).getBlue() -getColor(newH,newW).getBlue(),2);
        }
    return count;
    }


    /**
     * This next method is the core of our dynamic programming algorithm. We will
     * use the top-down approach with the given hash table (which you should initialize).
     * The key to the hash table is a pixel position. The value stored at each key
     * is the "seam" that starts with this pixel all the way to the bottom
     * of the image and its cost.
     *
     * The method takes the position of a pixel and returns the seam from this pixel
     * and its cost using the following steps:
     *   - compute the energy of the given pixel
     *   - get the list of neighbors below the current pixel
     *
     *   - Base case: if the list of neighbors is empty, return the following pair:
     *       < [<h,w>], energy >
     *     the first component of the pair is a list containing just one position
     *     (the current one); the second component of the pair is the current energy.
     *   - Recursive case: we will consider each of the neighbors below the current
     *     pixel and choose the one with the cheapest seam.
     *
     */


    Pair<List<Position>, Integer> findSeam(int h, int w){
        return hash.computeIfAbsent(new Position(h,w),p ->{
            ArrayList<Position> bottomNeighbors = new ArrayList<>(getBelowNeighbors(h,w));
            try{
                Position belowNeighborsPos = bottomNeighbors.get(0);
                Pair<List<Position>, Integer> bestPos = findSeam(belowNeighborsPos.getFirst(),belowNeighborsPos.getSecond());
                for(int i = 1; i < bottomNeighbors.size();i++){
                    belowNeighborsPos = bottomNeighbors.get(i);
                    Pair<List<Position>, Integer> tempPos = findSeam(belowNeighborsPos.getFirst(), belowNeighborsPos.getSecond());
                    if(tempPos.getSecond() < bestPos.getSecond()){
                        bestPos = tempPos;
                    }
                }
                return new Pair<List<Position>, Integer>(new Node(new Position(h,w), bestPos.getFirst()), bestPos.getSecond() + computeEnergy(h,w));
            }catch(Exception e){
                return new Pair<List<Position>, Integer>(new Node(new Position(h,w),new Empty()), computeEnergy(h,w) );
            }
        });
=======
        Color c = getColor(h, w);
        Function<Integer, Integer> sq = n -> n * n;
        int energy = 0;
        for (Position p : getHVneighbors(h, w)) {
            Color nc = getColor(p.getFirst(), p.getSecond());
            energy += sq.apply(nc.getRed() - c.getRed());
            energy += sq.apply(nc.getGreen() - c.getGreen());
            energy += sq.apply(nc.getBlue() - c.getBlue());
        }
        return energy;
    }

    // Find seam starting from (h,w) going down and return list of positions and cost
    // and then pick best seam starting from some position on the first row

    Map<Position, Pair<List<Position>, Integer>> hash = new WeakHashMap<>();

    Pair<List<Position>, Integer> findSeam(int h, int w) {
        return hash.computeIfAbsent(new Position(h, w), p -> {

                    int energy = computeEnergy(h, w);
                    ArrayList<Position> below = getBelowNeighbors(h, w);
                    if (below.isEmpty()) {
                        return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), new Empty<>()),
                                energy);
                    } else {
                        if (below.size() == 1) {
                            Pair<List<Position>, Integer> s = findSeam(below.get(0).getFirst(), below.get(0).getFirst());
                            return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s.getFirst()),
                                    energy + s.getSecond());
                        } else if (below.size() == 2) {
                            Pair<List<Position>, Integer> s1 = findSeam(below.get(0).getFirst(), below.get(0).getSecond());
                            Pair<List<Position>, Integer> s2 = findSeam(below.get(1).getFirst(), below.get(1).getSecond());
                            if (s1.getSecond() <= s2.getSecond()) {
                                return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s1.getFirst()),
                                        energy + s1.getSecond());
                            } else {
                                return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s2.getFirst()),
                                        energy + s2.getSecond());
                            }
                        } else if (below.size() == 3) {
                            Pair<List<Position>, Integer> s1 = findSeam(below.get(0).getFirst(), below.get(0).getSecond());
                            Pair<List<Position>, Integer> s2 = findSeam(below.get(1).getFirst(), below.get(1).getSecond());
                            Pair<List<Position>, Integer> s3 = findSeam(below.get(2).getFirst(), below.get(2).getSecond());

                            if (s1.getSecond() <= s2.getSecond()) {
                                if (s1.getSecond() <= s3.getSecond()) {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s1.getFirst()),
                                            energy + s1.getSecond());
                                } else {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s3.getFirst()),
                                            energy + s3.getSecond());
                                }
                            } else {
                                if (s2.getSecond() <= s3.getSecond()) {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s2.getFirst()),
                                            energy + s2.getSecond());
                                } else {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s3.getFirst()),
                                            energy + s3.getSecond());
                                }
                            }
                        }
                    }
                    return null;
                });
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }


<<<<<<< HEAD
    Pair<List<Position>,Integer> bestSeam () {
        hash.clear();
        Pair<List<Position>, Integer> bestSeam = findSeam(0, 0);
        for (int i = 0; i < width - 1; i++) {
            if (findSeam(0, i).getSecond() < bestSeam.getSecond()) {
                bestSeam = findSeam(0, i);
            }

        }
        return bestSeam;// to write
    }        /**
         * The last method puts its all together:
         *   - it finds the best seam
         *   - then it creates a new array of pixels representing an image of dimensions
         *     (height,width-1)
         *   - it then copies the old array pixels to the new arrays skipping the pixels
         *     in the seam
         *   - the method does not return anything: instead it updates the width and
         *     pixels instance variables to the new values.
         */
        void cutSeam () {
            // to write
            List<Position> bestPos = bestSeam().getFirst();
            int newPix[] = new int[(width - 1) * height];
            try {
                for (int h = 0; h < height; h++) {
                    int count = 0;
                    for (int j = 0; j < width; j++) {
                        if (bestPos.isEmpty()) {
                            newPix[(width - 1) * h + count] = pixels[h * width + j];
                            count++;
                        } else if (bestPos.getFirst().getFirst() == h && bestPos.getFirst().getSecond() == j) {
                            bestPos = bestPos.getRest();
                        } else {
                            newPix[(width - 1) * h + count] = pixels[width * h + j];
                            count++;
                        }
                    }
                }
            } catch (EmptyListE e) {

            }
            pixels = newPix;
            width = width - 1;
        }
=======

    Pair<List<Position>, Integer> bestSeam() {
        hash.clear();
        int cost = Integer.MAX_VALUE;
        List<Position> seam = new Empty<>();
        for (int w = 0; w < width; w++) {
            Pair<List<Position>, Integer> r = findSeam(0, w);
            if (r.getSecond() < cost) {
                seam = r.getFirst();
                cost = r.getSecond();
            }
        }
        return new Pair<>(seam, cost);
    }

    // Putting it all together; find best seam and copy pixels without that seam

    void cutSeam() {
        try {
            List<Position> seam = bestSeam().getFirst();
            int[] newPixels = new int[height * (width - 1)];
            for (int h = 0; h < height; h++) {
                int nw = 0;
                for (int w = 0; w < width; w++) {
                    if (seam.isEmpty()) {
                        newPixels[nw + h * (width - 1)] = pixels[w + h * width];
                    }
                    else {
                        Position p = seam.getFirst();
                        if (p.getFirst() == h && p.getSecond() == w) {
                            seam = seam.getRest();
                            nw--;
                        } else {
                            newPixels[nw + h * (width - 1)] = pixels[w + h * width];
                        }
                    }
                    nw++;
                }
            }
            width = width - 1;
            pixels = newPixels;
        } catch (EmptyListE e) {
            throw new Error("Bug");
        }
    }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
}
