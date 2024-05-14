import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int red = Integer.parseInt(line[0]);
        int green = Integer.parseInt(line[1]);
        int blue = Integer.parseInt(line[2]);
        String redM= getRadix(red);
        String greenM = getRadix(green);
        String blueM = getRadix(blue);
        System.out.println("#"+redM+greenM+blueM);
    }

    private static String getRadix(int red) {
        int digit = 0;
        String s = new String();
        while(red!=0) {
            digit = red%13;
            if(digit<10) {
                s=digit+s;
            }else {
                s=(char)((int)'A'+(digit-10))+s;
            }
            red = red/13;
        }
        while(s.length()<2) {
            s="0"+s;
        }
        return s;
    }

}
