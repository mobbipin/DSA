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
    
            }
            colony.updatePheromones(graph, q);
            colony.evaporatePheromones(0.1);
              //--//
    }

    public double getShortestPathDistance() {
      ///--THIS GIVE SHORTEST DISTANCE BY ANT--//
        return 0.0; 
    }
}
