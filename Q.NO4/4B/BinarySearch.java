import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class BinarySearch {
    public static List<Integer> closestValues(TreeNode root, double target, int x) {
        List<Integer> result = new ArrayList<>();
        if (root == null || x == 0) return result;

        List<Integer> closestList = new ArrayList<>();
        closestValuesHelper(root, target, x, closestList);

        return closestList;
    }

    private static void closestValuesHelper(TreeNode node, double target, int x, List<Integer> closestList) {
        if (node == null) return;

        closestValuesHelper(node.left, target, x, closestList);

        double diff = Math.abs(target - node.val);

        if (closestList.size() < x) {
            closestList.add(node.val);
        } else {
            if (diff < Math.abs(target - closestList.get(0))) {
                closestList.remove(0);
                closestList.add(node.val);
            } else {
                return; 
            }
        }

        closestValuesHelper(node.right, target, x, closestList);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        double k = 3.8;
        int x = 2;
        List<Integer> closest = closestValues(root, k, x);
        System.out.println("THE VALUE CLOSET TO  " + k + " ON DISTANCE " + x + ": " + closest);
    }
}
