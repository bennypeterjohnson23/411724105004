public class test1 {
    public static void main(String[] args) {
        int arr1[] ={2,2,2,1,2,2,1};
        int candidate =2;
        int count=0;
        for (int num : arr1)
        {
            if(count == 0)
                 {
                     candidate= num;
                 }
            else if(num == candidate)
                {
                    count--;
                }
        }
       System.out.println(candidate);
    }
    
}
