public class missingelement {
    public static void main(String[] args) {

        int arr[] = {1, 2, 3, 5, 6, 7};

        int n = 7;
        int sum = 0;

        for(int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }

        int total = n * (n + 1) / 2;

        System.out.println("Missing Number = " + (total - sum));
    }
}