

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read the first line and parse numbers manually
        StreamTokenizer tk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		tk.nextToken();
        int n1 = (int)tk.nval;
        long[] seq1 = new long[n1];
        for (int i = 0; i < n1; i++) {
			tk.nextToken();
            seq1[i] = (long)tk.nval;
        }

        // Read the second line and parse numbers manually
		tk.nextToken();
        int n2 = (int)tk.nval;
        long[] seq2 = new long[n2];
        for (int i = 0; i < n2; i++) {
            tk.nextToken();
            seq2[i] = (long)tk.nval;
        }

        int totalLength = n1 + n2;
        int medianIndex = (totalLength - 1) / 2;
        int i = 0, j = 0, count = 0;
        long median = 0;
        boolean found = false;

        while (i < n1 && j < n2 && !found) {
            long current;
            if (seq1[i] < seq2[j]) {
                current = seq1[i];
                i++;
            } else {
                current = seq2[j];
                j++;
            }

            if (count == medianIndex) {
                median = current;
                found = true;
            }
            count++;
        }

        while (!found && i < n1) {
            if (count == medianIndex) {
                median = seq1[i];
                found = true;
            }
            i++;
            count++;
        }

        while (!found && j < n2) {
            if (count == medianIndex) {
                median = seq2[j];
                found = true;
            }
            j++;
            count++;
        }

        System.out.println(median);
    }
}
