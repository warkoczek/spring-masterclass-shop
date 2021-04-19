package pl.training.shop;

public class TwoSum {
    public static void main(String[] args) {

        int[] target = getTarget();
        for(int i=0; i < target.length; i++){
            System.out.println(target[i]);
        }

    }

    public static int[] getTarget(){
        int target = 9;
        int[] nums = getNums();
        for(int i=0; i < nums.length; i++){
            for(int j=1; j < nums.length; j++){
                int sum = nums[i] + nums[j];
                if(sum == target){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] getNums(){
        return new int[]{2, 9, 11, 7};
    }

}
