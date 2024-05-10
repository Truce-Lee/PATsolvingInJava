import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    // Build tree from postorder and inorder traversal
    private static Node buildTree(Map<Integer, Integer> inMap, int[] postorder, int postStart, int postEnd, int inStart, int inEnd) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }

        Node root = new Node(postorder[postEnd]);
        int inRoot = inMap.get(root.value);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(inMap, postorder, postStart, postStart + numsLeft - 1, inStart, inRoot - 1);
        root.right = buildTree(inMap, postorder, postStart + numsLeft, postEnd - 1, inRoot + 1, inEnd);
        
        return root;
    }

    // Perform level order traversal
    private static List<Integer> levelOrderTraversal(Node root) {
        List<Integer> order = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            order.add(current.value);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return order;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine().trim());
        int[] postorder = Arrays.stream(bf.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] inorder = Arrays.stream(bf.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        Map<Integer, Integer> inMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            inMap.put(inorder[i], i);
        }

        Node root = buildTree(inMap, postorder, 0, N - 1, 0, N - 1);
        List<Integer> result = levelOrderTraversal(root);

        System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
