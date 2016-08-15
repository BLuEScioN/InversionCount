import java.util.*;
import java.io.*;

public class InversionCount {
    
    private static long invCnt = 0;
    
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi)
    {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        
        int i = lo, j = mid+1;
        
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                invCnt += (mid+1-i);
            }
            else a[k] = aux[i++];
        }
    } 
    
    private static void sort(int[] a, int[] aux, int lo, int hi)
    {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }
    
    public static void sort(int[] a)
    {
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1);
    }
    
    private static boolean less(int a, int b) {
        int answer = a - b;
        if (answer < 0) return true;
        else return false;
    }
    
    public static void main(String[] args) throws FileNotFoundException {  
        
        int[] arr = new int[100000];
        
        Scanner in = new Scanner(new BufferedReader(new FileReader("IntegerArray.txt")));
        for (int i = 0; i < 100000; i++) {
            arr[i] = in.nextInt();
        }
        
        //System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
        //System.out.println(arr[99999]);
        
        sort(arr);
        /*
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
        */
        System.out.println(invCnt);
        in.close();
    }
}