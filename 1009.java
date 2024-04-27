import java.util.Scanner;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		TreeMap<Integer,Double> poly1 = new TreeMap<>((a,b)->b-a);
		TreeMap<Integer,Double> poly2 = new TreeMap<>((a,b)->b-a);
		Scanner s = new Scanner(System.in);
		int num1 = s.nextInt();
		for(int i=0;i<num1;i++) {
			poly1.put(s.nextInt(), s.nextDouble());
		}
		int num2 = s.nextInt();
		for(int i=0;i<num2;i++) {
			poly2.put(s.nextInt(), s.nextDouble());
		}
		s.close();
		poly1=product(poly1,poly2);
		
		StringBuilder sb = new StringBuilder();
		sb.append(poly1.size());
		for(Integer exp:poly1.keySet()) {
			sb.append(" "+exp+" "+String.format("%,.1f", poly1.get(exp)));
		}
		System.out.println(sb.toString());
	}

	private static TreeMap<Integer, Double> product(TreeMap<Integer, Double> poly1, TreeMap<Integer, Double> poly2) {
		TreeMap<Integer,Double> multiple = new TreeMap<>((a,b)->b-a);
		for(Integer exp1 : poly2.keySet()) {
			for(Integer exp2 : poly1.keySet()) {
				multiple.merge(exp1+exp2, poly1.get(exp2)*poly2.get(exp1),Double::sum);
			    multiple.values().removeIf(v->Math.abs(v)<1e-10);
            }
		}
		return multiple;
	}

}
