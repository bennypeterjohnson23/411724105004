public class Main {
    public static void main(String[] args) {

       
        int[] arr = {12, 5, 8, 20, 3, 15};

      
        int min = arr[0];
        int max = arr[0];
        int temp;

        for (int i = 1; i < arr.length; i++) {

            temp = arr[i];

            if (temp < min) {
                min = temp;
            }

            if (temp > max) {
                max = temp;
            }
        }

        int ans = max - min;

        System.out.println("Minimum = " + min);
        System.out.println("Maximum = " + max);
        System.out.println("Answer (max - min) = " + ans);
    }
}