import java.util.Arrays;
import java.util.Scanner;


public class MinimumCostinput {
    
    public static int minCostToDecorate(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;

        
        int[][] dp = new int[n][k];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        
        for (int i = 0; i < k; i++) {
            dp[0][i] = costs[0][i];
        }

       
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int minPrev = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++) {
                    if (l != j) {
                        minPrev = Math.min(minPrev, dp[i - 1][l]);
                    }
                }
                dp[i][j] = minPrev + costs[i][j];
            }
        }

       
        int minTotalCost = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            minTotalCost = Math.min(minTotalCost, dp[n - 1][i]);
        }
        return minTotalCost;
    }

    //----------RUN FROM HERE---------//
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //---ENTER THE NUMBER OF VENUE---//
        System.out.print("ENTER THE NUMBER OF VENUES: ");
        int n = scanner.nextInt();
        //--ENTER NUMBER OF THEMES --//
        System.out.print("ENTER THE NUMBER OF THEMES: ");
        int k = scanner.nextInt();

        int[][] costs = new int[n][k];

        // GET THE COST ITS IN A FORMAT (LETS SAY THERE ARE TWO VENUE AND TWO THEME [[1,2],[2,3]],, ENTER IN FORMAT 1 2 2 3)
        System.out.println("ENTER THE COST FOR EACH VENUE AND THEME : ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                costs[i][j] = scanner.nextInt();
            }
        }

        //------CALLING MINIMUM COST---//////
        System.out.println(minCostToDecorate(costs));

        scanner.close();
    }
}