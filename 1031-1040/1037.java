import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tk = new StreamTokenizer(br);
		tk.nextToken();
		int n1 = (int)tk.nval;
		List<Integer> couponsP = new ArrayList<>();
		List<Integer> couponsN = new ArrayList<>();
		for(int i = 0;i<n1;i++) {
			tk.nextToken();
			if((int)tk.nval>0) {
				couponsP.add((int)tk.nval);
			}else {
				couponsN.add((int)tk.nval);
			}
				
		}
		tk.nextToken();
		int n2 = (int)tk.nval;
		List<Integer> productsP = new ArrayList<>();
		List<Integer> productsN = new ArrayList<>();
		for(int i = 0;i<n2;i++) {
			tk.nextToken();
			if((int)tk.nval>0) {
				productsP.add((int)tk.nval);
			}else {
				productsN.add((int)tk.nval);
			}
		}
		Collections.sort(couponsP);
		Collections.reverse(couponsP);
		Collections.sort(productsP);
		Collections.reverse(productsP);
		Collections.sort(couponsN);
		Collections.sort(productsN);
		int index =0;
		int sum = 0;
		while(index<couponsP.size() && index<productsP.size()) {
			sum = sum + couponsP.get(index)*productsP.get(index);
			index++;
		}
		index = 0;
		while(index<couponsN.size() && index<productsN.size()) {
			sum = sum + couponsN.get(index)*productsN.get(index);
			index++;
		}
		System.out.print(sum);
	}

}
