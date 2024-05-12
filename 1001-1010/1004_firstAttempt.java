import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int nodeNum = s.nextInt();
		int nonLeafNode = s.nextInt();
		s.nextLine();
		String[] lines = new String[nonLeafNode];
		for(int i = 0; i<nonLeafNode;i++) {
			lines[i] = s.nextLine();
		} 
		List<Node> tree = new ArrayList<>();
		readTree(tree,lines);
		int[] count = new int[nonLeafNode];
		int sum = 0;
		for(int i=0;i<nonLeafNode;i++) {
			for (Node node : tree.get(i).children) {
				if(!tree.contains(node)) {
					count[i]++;
					sum++;
				}
			}
			System.out.print(count[i]+" ");
		}
		System.out.print(nodeNum-nonLeafNode-sum);
		s.close();
	}
	private static void readTree(List<Node> tree, String[] lines) {
		for(int i=0 ; i< lines.length;i++) {
			String[] parts = lines[i].split(" ");
			String id = parts[0];
			Node node = new Node(id);
			int num =Integer.parseInt(parts[1]);
			for(int j=0;j<num;j++) {
				Node child = new Node(parts[j+2]);
				node.appendChild(child);
			}
			tree.add(node);
		}
	}
	static class Node{
		String id;
		List<Node> children;
		public Node(String id) {
			this.id = id;
			this.children=new ArrayList<>();
		}
		public void appendChild(Node node) {
			this.children.add(node);
		}
		public int getChildNum() {
			int num = children.size();
			return num;
		}
	}
}
