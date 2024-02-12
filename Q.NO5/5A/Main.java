import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //----FROM HERE YOU CAN CHANGE THE NUMBERS----//
        int numberOfCities = 10;
        int numberOfAnts = 10;
        int numberOfIterations = 100;

        //--A LIST OF CITY AS DEMO--//
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < numberOfCities; i++) {
            //--WE CAN CHANGE THIS IF WE HAVE ANY ACTUAL VALUE--//
            double x = Math.random() * 100;
            double y = Math.random() * 100;
            cities.add(new City(i, x, y));
        }

        SalesmanProblem tspSolver = new SalesmanProblem(cities, numberOfAnts, numberOfIterations, 0.1, 0.1);

        tspSolver.solve(new double[numberOfCities][numberOfCities], 1.0);

        System.out.println("THE SHORTEST PATH IS - " + tspSolver.getShortestPathDistance());//--THE OUTPUT WILL BE THE SHORTEST PATH--//
    }
}
