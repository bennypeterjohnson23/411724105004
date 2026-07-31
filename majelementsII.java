public class majelementsII {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 1, 2, 1, 2, 1, 2};

        int candidate1 = 0;
        int candidate2 = 0;
        int count1 = 0;
        int count2 = 0;

       
        for(int i = 0; i < arr.length; i++) {

            if(arr[i] == candidate1) {
                count1++;
            }
            else if(arr[i] == candidate2) {
                count2++;
            }
            else if(count1 == 0) {
                candidate1 = arr[i];
                count1 = 1;
            }
            else if(count2 == 0) {
                candidate2 = arr[i];
                count2 = 1;
            }
            else {
                count1--;
                count2--;
            }
        }

       
        count1 = 0;
        count2 = 0;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == candidate1) {
                count1++;
            }
            else if(arr[i] == candidate2) {
                count2++;
            }
        }

        if(count1 > arr.length / 3) {
            System.out.println(candidate1);
        }

        if(count2 > arr.length / 3) {
            System.out.println(candidate2);
        }
    }
}