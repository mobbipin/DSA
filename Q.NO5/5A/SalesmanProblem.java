import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SalesmanProblem {
    private int numOfCities; 
    private int[][] distanceMatrix; 
    private double[][] pheromoneMatrix; 
    private double alpha; 
    private double beta; 
    private double evaporationRate;
    private int numAnts; 
    private int maxIterations; 
    private Random random;

    public SalesmanProblem(int numOfCities, int[][] distanceMatrix, double alpha, double beta, double evaporationRate, int numAnts, int maxIterations) {
        this.numOfCities = numOfCities;
        this.distanceMatrix = distanceMatrix;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.numAnts = numAnts;
        this.maxIterations = maxIterations;
        this.random = new Random();


        this.pheromoneMatrix = new double[numOfCities][numOfCities];
        for (int i =  0; i < numOfCities; i++) {
            Arrays.fill(pheromoneMatrix[i],  1.0);
        }
    }

    public List<Integer> solveTravellingSalesmanProb() {
        List<Integer> bestTour = null;
        double bestTourLength = Double.POSITIVE_INFINITY;

        for (int iter =  0; iter < maxIterations; iter++) {

            List<List<Integer>> antTours = new ArrayList<>();
            double[] tourLengths = new double[numAnts];


            for (int ant =  0; ant < numAnts; ant++) {
                List<Integer> tour = constructTour();
                antTours.add(tour);
                tourLengths[ant] = calculateTourLength(tour);

                if (tourLengths[ant] < bestTourLength) {
                    bestTourLength = tourLengths[ant];
                    bestTour = new ArrayList<>(tour);
                }
            }

            updatePheromoneTrails(antTours, tourLengths);
        }

        return bestTour;
    }

    private List<Integer> constructTour() {
        List<Integer> tour = new ArrayList<>();
        boolean[] visited = new boolean[numOfCities];
        int initialCity = random.nextInt(numOfCities); 
        int currentCity = initialCity;
        tour.add(initialCity);
        visited[initialCity] = true;

        for (int i =  1; i < numOfCities; i++) {
            int nextCity = selectNextCity(currentCity, visited);
            tour.add(nextCity);
            visited[nextCity] = true;
            currentCity = nextCity;
        }

        return tour;
    }

    private int selectNextCity(int currentCity, boolean[] visited) {
        double[] probabilities = new double[numOfCities];
        double totalProbability =  0;

        for (int i =  0; i < numOfCities; i++) {
            if (!visited[i]) {
                double pheromone = Math.pow(pheromoneMatrix[currentCity][i], alpha);
                double visibility = Math.pow(1.0 / distanceMatrix[currentCity][i], beta);
                probabilities[i] = pheromone * visibility;
                totalProbability += probabilities[i];
            }
        }

  
        double rand = random.nextDouble() * totalProbability;
        double sum =  0;
        for (int i =  0; i < numOfCities; i++) {
            sum += probabilities[i];
            if (sum >= rand) {
                return i;
            }
        }

        return -1;
    }

    private void updatePheromoneTrails(List<List<Integer>> antTours, double[] tourLengths) {
      
        for (int i =  0; i < numOfCities; i++) {
            for (int j =  0; j < numOfCities; j++) {
                pheromoneMatrix[i][j] *= (1 - evaporationRate);
            }
        }

   
        for (int ant =  0; ant < numAnts; ant++) {
            List<Integer> tour = antTours.get(ant);
            double tourLength = tourLengths[ant];
            for (int i =  0; i < numOfCities -  1; i++) {
                int city1 = tour.get(i);
                int city2 = tour.get(i +  1);
                pheromoneMatrix[city1][city2] +=  1.0 / tourLength;
                pheromoneMatrix[city2][city1] +=  1.0 / tourLength;
            }
        }
    }

    private double calculateTourLength(List<Integer> tour) {
        double length =  0;
        for (int i =  0; i < tour.size() -  1; i++) {
            int city1 = tour.get(i);
            int city2 = tour.get(i +  1);
            length += distanceMatrix[city1][city2];
        }
       
        int initialCity = tour.get(0);
        int lastCity = tour.get(tour.size() -  1);
        length += distanceMatrix[lastCity][initialCity];
        return length;
    }

    public static void main(String[] args) {
        int numOfCities =  5;
        int[][] distanceMatrix = {
                {0,  10,  15,  20,  25},
                {10,  0,  35,  25,  30},
                {15,  35,  0,  30,  50},
                {20,  25,  30,  0,  40},
                {25,  30,  50,  40,  0}
        };
        double alpha =  1.0; 
        double beta =  2.0; 
        double evaporationRate =  0.5; 
        int numAnts =  10; 
        int maxIterations =  100; 

        SalesmanProblem SalesmanProblem = new SalesmanProblem(numOfCities, distanceMatrix, alpha, beta, evaporationRate, numAnts, maxIterations);
        List<Integer> bestTour = SalesmanProblem.solveTravellingSalesmanProb();

        System.out.println("THE POSSIBLE BEST TOUR IS :: " + bestTour);
        System.out.println("AND THE TOUR LENGTH IS: " + SalesmanProblem.calculateTourLength(bestTour));
    }
}
