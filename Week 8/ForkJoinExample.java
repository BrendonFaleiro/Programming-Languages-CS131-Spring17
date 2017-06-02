public class ParallelTaskSummer extends RecursiveTask<Double>{

private double[] nums;
private int lower_index, upper_index;
private static final int THRESHOLD = 100;

ParallelTaskSummer(double[] nums, int lower_index, int upper_index){
    this.nums = nums;
    this.lower_index = lower_index;
    this.upper_index = upper_index;
}

public Double compute(){
    int range = upper_index - lower_index;
    if(range < THRESHOLD){
        return(MathUtils.arraySum(nums, lower_index, upper_index));
    }
    else{
        int mid = lower_index + range/2;
        ParallelTaskSummer leftsummer = new ParallelTaskSummer(nums, lower_index, mid);
        ParallelTaskSummer rightsummer = new ParallelTaskSummer(num, mid, upper_index);
    
        leftsummer.fork();
        Double rightSum = rightsummer.compute();
        Double leftSum = leftsum.join();
    
        return (rightSum+leftSum);
    }
}

public class MathUtils{
    private static final FORK_JOIN_POOL = new ForkJoinPool();
    :
    :
    public static Double arraySumParallel(double[] nums){
        return (FORK_JOIN_POOL.invoke(new ParallelTaskSummer(nums, 0, nums.length-1)));
    }
}
}