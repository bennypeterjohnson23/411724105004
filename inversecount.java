import java.util.ArrayList;
import java.util.List;

public class inversecount {
    public List<Integer> Invcount(int[] arr)
    {
        List<Integer> result = new ArrayList<>();
        int count = 0;
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0;j<arr.length;j++)
            {
                if(arr[i]>arr[j])
                {
                    count++;
                }
            }
        }
        result.add(count);
        return result;
    }
    
}
