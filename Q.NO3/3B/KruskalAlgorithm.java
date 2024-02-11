import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class Subset {
    int parent, rank;
}

public class KruskalAlgorithm {
    int verticesCount;
    List<Edge> edgesList = new ArrayList<>();

    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }


    void Union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    void kruskalMST() {
        Collections.sort(edgesList);

        Subset[] subsets = new Subset[verticesCount];
        for (int i =  0; i < verticesCount; ++i) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank =  0;
        }

        List<Edge> mst = new ArrayList<>();
        int e =  0; 
        int numOfEdgesInMST =  0;

        while (e < edgesList.size()) {
            Edge nextEdge = edgesList.get(e++);
            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            if (x != y) {
                mst.add(nextEdge);
                Union(subsets, x, y);
                numOfEdgesInMST++;
            }

          
            if (numOfEdgesInMST == verticesCount -  1)
                break;
        }

        System.out.println("Minimum spanning tree constructed:");
        for (int i =  0; i < mst.size(); i++)
            System.out.println(mst.get(i).src + " -- " + mst.get(i).dest + " == " + mst.get(i).weight);
    }

   
    public static void main(String[] args) {
        int verticesCount =  4;
        KruskalAlgorithm kA = new KruskalAlgorithm(verticesCount);

        kA.addEdge(0,  1,  10);
        kA.addEdge(0,  2,  6);
        kA.addEdge(0,  3,  5);
        kA.addEdge(1,  3,  15);
        kA.addEdge(2,  3,  4);

        kA.kruskalMST();
    }

    void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge();
        edge.src = src;
        edge.dest = dest;
        edge.weight = weight;
        edgesList.add(edge);
    }
}
