import java.util.ArrayList;
import java.util.List;

public class Ant {
    private List<City> path;
    private List<Boolean> visited;

    public Ant(int numberOfCities) {
        this.path = new ArrayList<>();
        this.visited = new ArrayList<>(numberOfCities);
        for (int i = 0; i < numberOfCities; i++) {
            this.visited.add(false);
        }
    }

    public void visitCity(City city) {
        this.path.add(city);
        this.visited.set(city.getId(), true);
    }

    public boolean hasVisited(City city) {
        return this.visited.get(city.getId());
    }

    public double pathDistance(double[][] distances) {
        if (path.isEmpty()) {
            return 0.0; 
        }

        double totalDistance = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            totalDistance += distances[path.get(i).getId()][path.get(i + 1).getId()];
        }

       
        totalDistance += distances[path.get(path.size() - 1).getId()][path.get(0).getId()];
        return totalDistance;
    }

    public List<City> getPath() {
        return path;
    }
}
