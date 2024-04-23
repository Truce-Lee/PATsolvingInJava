import java.util.Scanner;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String line1 = s.nextLine();
		String line2 = s.nextLine();
		s.close();
		TreeMap<Integer,Double> poly1 = stringToPoly(line1);
		TreeMap<Integer,Double> poly2 = stringToPoly(line2);
		TreeMap<Integer,Double> result= polySum(poly1,poly2);
		printPoly(result);
		
	}	
		

	private static TreeMap<Integer, Double> stringToPoly(String line) {
		TreeMap<Integer,Double> poly= new TreeMap<>((a,b)-> b-a);
		Scanner s = new Scanner(line);
		int term = s.nextInt();
		for(int i=0;i<term;i++) {
			int eoff = s.nextInt();
			double alpha = s.nextDouble();
			poly.put(eoff, alpha);
		}
		s.close();
		return poly;
	}	
	
	private static TreeMap<Integer, Double>  polySum(TreeMap<Integer, Double> poly1, TreeMap<Integer, Double> poly2) {
		// TODO Auto-generated method stub
		for(Integer eoff:poly2.keySet()) {
			poly1.merge(eoff, poly2.get(eoff), Double::sum);
		}
		return poly1;
	}
	
	
	private static void printPoly(TreeMap<Integer, Double> result) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(result.size());
		for(int eoff:result.keySet()) {
			sb.append(" "+eoff+" "+String.format("%,.1f", result.get(eoff)));
		}
		System.out.println(sb.toString());
	}	
	
}
