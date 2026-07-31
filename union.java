import java.util.*;
public class union {
    public static void main(String[] args) {
        int[] arr1 = {1,2,2,3,4};
        int[] arr2 = {2,3,5};
        ArrayList<Integer> union = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < arr1.length && j < arr2.length) {
            if(arr1[i] < arr2[j]) {
                if(union.size() == 0 || union.get(union.size() - 1) != arr1[i])
                    union.add(arr1[i]);
                i++;
            } else if(arr1[i] > arr2[j]) {
                if(union.size() == 0 || union.get(union.size() - 1) != arr2[j])
                    union.add(arr2[j]);
                j++;
            } else {
                if(union.size() == 0 || union.get(union.size() - 1) != arr1[i])
                    union.add(arr1[i]);
                i++;
                j++;
            }
        }
        while(i < arr1.length) {
            if(union.size() == 0 || union.get(union.size() - 1) != arr1[i])
                union.add(arr1[i]);
            i++;
        }
        while(j < arr2.length) {
            if(union.size() == 0 || union.get(union.size() - 1) != arr2[j])
                union.add(arr2[j]);
            j++;
        }
        System.out.println(union);
    }
}