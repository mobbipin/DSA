import java.util.ArrayList;
import java.util.List;

public class SalesmanProblem {
    private List<City> cities;
    private int numberOfAnts;
    private int numberOfIterations;
    private Colony colony;

    public SalesmanProblem(List<City> cities, int numberOfAnts, int numberOfIterations, double pheromoneUpdateRule, double evaporationRate) {
        this.cities = cities;
        this.numberOfAnts = numberOfAnts;
        this.numberOfIterations = numberOfIterations;
        this.colony = new Colony(createAnts(), cities.size());
    }

    private List<Ant> createAnts() {
        List<Ant> ants = new ArrayList<>();
        for (int i = 0; i < numberOfAnts; i++) {
            ants.add(new Ant(cities.size()));
        }
        return ants;
    }

    public void solve(double[][] graph, double q) {
        for (int iteration = 0; iteration < numberOfIterations; iteration++) {
            for (Ant ant : colony.getAnts()) {
                ant.move(graph); // Assuming you have a move method in the Ant class
            }
            colony.updatePheromones(graph, q);
            colony.evaporatePheromones(0.1);
        }
    }

    public double getShortestPathDistance() {
        double shortestDistance = Double.MAX_VALUE;

        for (Ant ant : colony.getAnts()) {
            double distance = calculatePathDistance(ant.getPath());
            if (distance < shortestDistance) {
                shortestDistance = distance;
            }
        }

        return shortestDistance;
    }

    private double calculatePathDistance(List<Integer> path) {
        double distance = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {
            int fromCity = path.get(i);
            int toCity = path.get(i + 1);
            distance += graph[fromCity][toCity];
        }

        // Add distance from the last city back to the starting city
        distance += graph[path.get(path.size() - 1)][path.get(0)];

        return distance;
    }
}
