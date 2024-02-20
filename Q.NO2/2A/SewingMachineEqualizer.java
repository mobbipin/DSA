public class SewingMachineEqualizer {

    public static int[] minMovesToEqualize(int[] dresses) {
        int totalDresses = 0;
        int totalMachines = dresses.length;

      
        for (int dressCount : dresses) {
            totalDresses += dressCount;
        }

    
        int targetDressesPerMachine = totalDresses / totalMachines;
        if (totalDresses % totalMachines != 0) {
            return new int[]{-1, -1}; 
        }

        int currentMoveCount = 0;

      
        for (int i = 0; i < dresses.length; i++) {
            while (dresses[i] > targetDressesPerMachine) {
            
                for (int j = 0; j < dresses.length; j++) {
                    if (dresses[j] < targetDressesPerMachine) {
                        int dressesToMove = Math.min(targetDressesPerMachine - dresses[j], dresses[i] - targetDressesPerMachine);
                        dresses[i] -= dressesToMove;
                        dresses[j] += dressesToMove;
                        currentMoveCount += dressesToMove;
                    }
                }
            }
        }


        return new int[]{currentMoveCount, currentMoveCount};
    }

    public static void main(String[] args) {
        int[] dresses = {2, 1, 3, 0, 2};
        int[] result = minMovesToEqualize(dresses);

        if (result[0] == -1) {
            System.out.println("THE WORK CANNOT BE EQUALIZED");
        } else {
            System.out.println("THE NUMBER OF MOVES WILL BE " + result[0]);
        }
    }
}
