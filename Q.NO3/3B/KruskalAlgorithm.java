import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Edge implements Comparable<Edge> {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

class DisjointSet {
    int[] parent, rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    public void union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        if (pu != pv) {
            if (rank[pu] > rank[pv]) {
                parent[pv] = pu;
            } else if (rank[pu] < rank[pv]) {
                parent[pu] = pv;
            } else {
                parent[pu] = pv;
                rank[pv]++;
            }
        }
    }
}

public class KruskalAlgorithm {
    public static List<Edge> findMinimumSpanningTree(List<Edge> edges, int numVertices) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(edges);
        DisjointSet ds = new DisjointSet(numVertices);
        List<Edge> mst = new ArrayList<>();

        while (!pq.isEmpty() && mst.size() < numVertices - 1) {
            Edge edge = pq.poll();
            int sourceParent = ds.find(edge.source);
            int destParent = ds.find(edge.destination);

            if (sourceParent != destParent) {
                mst.add(edge);
                ds.union(sourceParent, destParent);
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 5));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(1, 4, 4));
        edges.add(new Edge(2, 4, 6));
        edges.add(new Edge(3, 4, 7));

        List<Edge> mst = findMinimumSpanningTree(edges, 5);

        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + ": " + edge.weight);
        }
    }
}
