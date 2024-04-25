import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
        if(num==0) {
			System.out.println(0);
			System.exit(0);
		}
		int[] seq = new int[num];
		for(int i=0;i<num;i++) {
			seq[i]=s.nextInt();
		}
		s.close();
		int max = 0;
		int maxL = 0;
		int maxR = 0;
		int sum =0;
		int left=0;
		int right = 0;
		for(int i=0;i<num;i++) {
			if(seq[i]>=sum+seq[i]) {
				sum = seq[i];
				left = i;
				right = i;
			}else {
				sum = sum+seq[i];
				right = i;
			}
			if(sum>max) {
				max = sum;
				maxL =left;
				maxR =right;
			}
		}
        if(max<=0) {
			max=0;
			maxL=0;
			maxR=num-1;
		}
		System.out.println(max+" "+seq[maxL]+" "+seq[maxR]);
	}
}
