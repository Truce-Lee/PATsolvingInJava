import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Main {
	static class Station{
		double price;
		double distance;
		public Station(double price,double distance) {
			this.price = price;
			this.distance = distance;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
		st.nextToken();
		int capacity = (int)st.nval;
		st.nextToken();
		int distance = (int)st.nval;
		st.nextToken();
		int gasrun = (int)st.nval;
		st.nextToken();
		int n = (int)st.nval;
		List<Station> list = new ArrayList<>();
		for(int i = 0;i<n;i++) {
			st.nextToken();
			double price = st.nval;
			st.nextToken();
			double distances = st.nval;
			Station station = new Station(price,distances);
			list.add(station);
		}
		list.add(new Station(0,distance));
		list.sort(new Comparator<Station>() {
			@Override
			public int compare(Station o1, Station o2) {
				// TODO Auto-generated method stub
				return (int)(o1.distance-o2.distance);
			}
		});
		if(list.get(0).distance!=0) {
			System.out.printf("The maximum travel distance = 0.00\n");
            return;
		}
		double cost = 0;
		boolean unreached = false;
		int now = 0;
		double gasleft = 0;
		
		while(now<n) {
			double minPrice = Double.MAX_VALUE;
			int next = -1;
			boolean found = false;
			for(int i = now+1;i<list.size()&&list.get(i).distance<=(list.get(now).distance+capacity*gasrun);i++) {
				if(minPrice > list.get(i).price) {
					minPrice = list.get(i).price;
					next = i;
				}
				if(list.get(now).price>list.get(i).price) {
					minPrice = list.get(i).price;
					next = i;
					found = true;
					break;
				}
			}
			if (next == -1) {
				unreached = true;
				break;
			}
			if(found) {
				cost+=((list.get(next).distance-list.get(now).distance)/gasrun-gasleft)*list.get(now).price;
				gasleft = 0;
			}else {
				cost+=(capacity-gasleft)*list.get(now).price;
				gasleft = capacity - (list.get(next).distance-list.get(now).distance)/gasrun;
			}
			now = next;
		}

		if (!unreached)
            System.out.printf("%.2f\n", cost);
        else
            System.out.printf("The maximum travel distance = %.2f\n", list.get(now).distance + capacity * gasrun);
	}
}
