/**
 * Lenell Davis
 * CMIS 242
 * Project 3
 * 4/24/16
 * Sequence.java
 */

public final class Sequence {
    public static long counter;
    
    private Sequence(){
        counter = 0;
    }
    
    /**
     *Computes the sequence values iteratively
     */
    public static int computeIterative(int n){
        int previous = 0;
        int current = 1;
        
        if(n==0){
            return previous;
        }
        
        for(int i = 1; i < n; i++){
            int result = (2 * current) + previous;
            previous = current;
            current = result;
            getEfficiency();
        }

        return current;
    }
    
    /**
     *Computes the sequence values recursively
     */
    public static int computeRecursive(int n){
        if(n == 0){
            int result = 0;
            getEfficiency();
            return result;
        }
        
        if(n == 1){
            int result = 1;
            getEfficiency();
            return result;
        }

        int num1 = computeRecursive(n-1);
        int num2 = computeRecursive(n-2);
        int newTerm = ((2 * num1) + num2);
        getEfficiency();        
        return newTerm;
    }
    
    /**
     *Calculates the efficiency for each method
     */
    public static void getEfficiency(){
        counter ++;
    }
    
    /*
    * Resets the counter variable
    */
    public static void resetCounter(int a){
        counter = a;
    }
    
}
