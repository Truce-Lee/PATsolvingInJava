import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int nodeNum = s.nextInt();  // 总节点数
        if (nodeNum == 0){
            System.out.println(nodeNum);
            System.exit(0);
        }
        int nonLeafNode = s.nextInt();  // 非叶子节点数
        if (nonLeafNode == 0){
            System.out.println(nodeNum);
            System.exit(0);
        }
        s.nextLine();  // 读取并忽略换行符
        String[] lines = new String[nonLeafNode];
        for (int i = 0; i < nonLeafNode; i++) {
            lines[i] = s.nextLine();  // 读取每一行非叶子节点的数据
        }

        Map<String,Node> treeFamily= new HashMap<>();
        createTree(treeFamily,lines);

        List<Integer> counts = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(treeFamily.get("01"));
        while(!queue.isEmpty()) {
            int size = queue.size();
            int count=0;
            for(int i =0; i<size;i++) {
                Node current = queue.poll();
                if(current.children.isEmpty()) {
                    count++;
                }else {
                    queue.addAll(current.children);
                }
            }
            counts.add(count);

        }
        printList(counts);
        s.close();
    }

    private static void createTree(Map<String, Node> tree, String[] lines) {
        for(String line : lines) {
            String parts[] = line.split("\\s");
            String id = parts[0];
            Node node= tree.getOrDefault(id, new Node(id));
            int num = Integer.parseInt(parts[1]);
            for(int i=0;i<num;i++) {
                Node child = tree.getOrDefault(parts[i+2], new Node(parts[i+2]));
                node.appendChild(child);
                tree.put(parts[i+2], child);
            }
            tree.put(id, node);
        }
    }

    private static void printList(List<Integer> counts) {
        for (int i =0; i<counts.size();i++) {
            if(i==counts.size()-1) System.out.println(counts.get(i));
            else System.out.print(counts.get(i)+" ");
        }
    }

    static class Node {
        String id;
        List<Node> children = new ArrayList<>();

        public Node(String id) {
            this.id = id;
        }

        public void appendChild(Node node) {
            this.children.add(node);
        }

    }
}
