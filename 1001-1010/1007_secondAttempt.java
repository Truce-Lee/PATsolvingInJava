import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int num = Integer.parseInt(br.readLine().trim()); // 读取并转换第一行输入为整数
        if (num == 0) {
            System.out.println("0 0 0");
            System.exit(0);
        }
        
        int[] seq = new int[num];
        String[] inputs = br.readLine().trim().split(" "); // 读取一行输入并分割成字符串数组
        for (int i = 0; i < num; i++) {
            seq[i] = Integer.parseInt(inputs[i]); // 将输入字符串转换为整数
        }
        
        br.close();
        
        int max = Integer.MIN_VALUE;
        int maxL = 0;
        int maxR = 0;
        int sum = 0;
        int left = 0;
        int right = 0;
        
        for (int i = 0; i < num; i++) {
            if (seq[i] >= sum + seq[i]) {
                sum = seq[i];
                left = i;
                right = i;
            } else {
                sum += seq[i];
                right = i;
            }
            if (sum > max) {
                max = sum;
                maxL = left;
                maxR = right;
            }
        }
        
        if (max < 0) {
            max = 0;
            maxL = 0;
            maxR = num - 1;
        }
        
        System.out.println(max + " " + seq[maxL] + " " + seq[maxR]);
    }
}
