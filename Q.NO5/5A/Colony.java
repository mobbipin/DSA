import java.util.List;

public class Colony {
    private List<Ant> ants;
    private double[][] pheromoneMatrix;

    public Colony(List<Ant> ants, int numberOfCities) {
        this.ants = ants;
        this.pheromoneMatrix = new double[numberOfCities][numberOfCities];
        initializePheromoneMatrix();
    }

    private void initializePheromoneMatrix() {
        for (int i = 0; i < pheromoneMatrix.length; i++) {
            for (int j = 0; j < pheromoneMatrix[i].length; j++) {
                pheromoneMatrix[i][j] = 1.0; 
            }
        }
    }

    public void updatePheromones(double[][] distances, double pheromoneUpdateRule) {
        for (Ant ant : ants) {
            List<City> path = ant.getPath();
            if (!path.isEmpty()) { 
                double tourLength = ant.pathDistance(distances);
                for (int i = 0; i < path.size() - 1; i++) {
                    int from = path.get(i).getId();
                    int to = path.get(i + 1).getId();
                    pheromoneMatrix[from][to] += pheromoneUpdateRule / tourLength;
                }
               
                int lastCity = path.get(path.size() - 1).getId();
                int firstCity = path.get(0).getId();
                pheromoneMatrix[lastCity][firstCity] += pheromoneUpdateRule / tourLength;
            }
        }
    }

    public void evaporatePheromones(double evaporationRate) {
        for (int i =  0; i < pheromoneMatrix.length; i++) {
            for (int j =  0; j < pheromoneMatrix[i].length; j++) {
                pheromoneMatrix[i][j] *= (1 - evaporationRate);
            }
        }
    }

    public double[][] getPheromoneMatrix() {
        return pheromoneMatrix;
    }

    public List<Ant> getAnts() {
        return ants;
    }
}
