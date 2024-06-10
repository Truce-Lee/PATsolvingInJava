import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Node {
    int address, key, next;
    boolean flag;

    public Node(int address, int key, int next, boolean flag) {
        this.address = address;
        this.key = key;
        this.next = next;
        this.flag = flag;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        
        // 读取节点数和起始节点地址
        st.nextToken();
        int n = (int) st.nval;
        st.nextToken();
        int start = (int) st.nval;

        HashMap<Integer, Node> nodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st.nextToken();
            int address = (int) st.nval;
            st.nextToken();
            int key = (int) st.nval;
            st.nextToken();
            int next = (int) st.nval;
            nodes.put(address, new Node(address, key, next, false));
        }

        List<Node> validNodes = new ArrayList<>();
        for (int i = start; i != -1; i = nodes.get(i).next) {
            Node node = nodes.get(i);
            if (node != null) {
                node.flag = true;
                validNodes.add(node);
            }
        }

        if (validNodes.isEmpty()) {
            System.out.println("0 -1");
        } else {
            Collections.sort(validNodes, (o1, o2) -> Integer.compare(o1.key, o2.key));

            StringBuilder sb = new StringBuilder();
            sb.append(validNodes.size()).append(" ").append(String.format("%05d", validNodes.get(0).address)).append("\n");
            for (int i = 0; i < validNodes.size(); i++) {
                sb.append(String.format("%05d", validNodes.get(i).address)).append(" ")
                  .append(validNodes.get(i).key).append(" ");
                if (i != validNodes.size() - 1) {
                    sb.append(String.format("%05d", validNodes.get(i + 1).address)).append("\n");
                } else {
                    sb.append("-1\n");
                }
            }
            System.out.print(sb.toString());
        }
    }
}
