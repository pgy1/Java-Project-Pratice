package test.instance;

/**
 * Created by pengguangyu on 2016/6/22.
 */
public class StringTest {

    public static String phoneNum(){

        int[] arr = new int[]{8,2,1,0,3};
        int[] index = new int[]{2,0,3,2,4,0,1,3,2,3,3};

        String tel = "";
        for(int i:index){
            tel = tel + arr[i];
        }

        return tel;
    }

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        String testStr = String.format("%s's phone is %s","×âÖ÷",phoneNum());
        System.out.println(testStr);
        System.out.println("Time is:"+(System.currentTimeMillis()-start) + "ms");
    }

}
