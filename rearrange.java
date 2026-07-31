
import java.util.ArrayList;
import java.util.List;

public class rearrange {
    
    public static void main(String[] args) {
        int[] arr = new int[];
        int[] result = new int[arr.length];

        int pos = 0;
        int neg = 1;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] >= 0) {
                result[pos] = arr[i];
                pos += 2;
            } else {
                result[neg] = arr[i];
                neg += 2;
            }
        }

        System.out.println("Rearranged Array:");

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
    

