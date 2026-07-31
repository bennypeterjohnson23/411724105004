import java.util.ArrayList;
import java.util.Scanner;
public class RearrangeElements {
    public static void rearrange(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        ArrayList <Integer> ns=new ArrayList<>();
        int pos = 0; 
        int neg = 1; 

        for (int num : arr) {
            if (num >= 0) {
                ans[pos] = num;
                pos += 2;
            }
            else {
                ans[neg] = num;
                neg += 2;
            }
        }

        for (int x : ans) {
            System.out.print(x + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter " + n + " elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Array elements are:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        rearrange(arr);

        sc.close();
    }
}