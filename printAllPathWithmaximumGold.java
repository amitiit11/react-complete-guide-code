import java.io.*;
import java.util.*;

public class Main {

   private static class Pair {
      String psf;
      int i;
      int j;

      public Pair(String psf, int i, int j) {
         this.psf = psf;
         this.i = i;
         this.j = j;
      }
   }
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      int m = Integer.parseInt(br.readLine());
      int[][] arr = new int[n][m];

      for (int i = 0; i < n; i++) {
         String str = br.readLine();
         for (int j = 0; j < m; j++) {
            arr[i][j] = Integer.parseInt(str.split(" ")[j]);
         }
      }

      //write your code here
      int[][] dp = new int[arr.length][arr[0].length];
      for(int j = dp[0].length-1;j>=0;j--){
          for(int i=0;i<dp.length;i++){
              if(j == dp[0].length-1){
                  dp[i][j] = arr[i][j]; 
              }else if(i == dp.length-1){
                  dp[i][j] = arr[i][j]+Math.max(dp[i][j+1],dp[i-1][j+1]);
              }else if(i == 0){
                  dp[i][j] = arr[i][j]+Math.max(dp[i][j+1],dp[i+1][j+1]);
              }else{
                  dp[i][j] = arr[i][j]+Math.max(dp[i][j+1],Math.max(dp[i-1][j+1],dp[i+1][j+1]));
              }
          }
      }
      int max1 = dp[0][0];
      System.out.println(dp[0][0]);
      for(int i=1;i<dp.length;i++){
           System.out.println(dp[i][0]);
          if(max1<dp[i][0]){
              max1 = dp[i][0];
          }
      }
      System.out.println(max1);
      ArrayDeque<Pair> que = new ArrayDeque<>();
      for(int i=0;i<dp.length;i++){
           
          if(max1 == dp[i][0]){
              que.add(new Pair(i+" ",i,0));
          }
      }
      while(que.size()>0){
          Pair rem = que.removeFirst();
          if(rem.j == dp[0].length-1){
              System.out.println(rem.psf);
          }else if(rem.i == 0){
              int max = Math.max(dp[rem.i][rem.j+1],dp[rem.i+1][rem.j+1]);
              if(max == dp[rem.i][rem.j+1]){
                  que.add(new Pair(rem.psf+"d2 ",rem.i,rem.j+1));
              }
              if(max == dp[rem.i+1][rem.j+1]){
                  que.add(new Pair(rem.psf+"d3 ",rem.i+1,rem.j+1));
              }
          }else if(rem.i == dp.length-1){
              int max = Math.max(dp[rem.i][rem.j+1],dp[rem.i-1][rem.j+1]);
               if(max == dp[rem.i-1][rem.j+1]){
                  que.add(new Pair(rem.psf+"d1 ",rem.i-1,rem.j+1));
              }
              if(max == dp[rem.i][rem.j+1]){
                  que.add(new Pair(rem.psf+"d2 ",rem.i,rem.j+1));
              }
             
          }else{
              int max = Math.max(dp[rem.i+1][rem.j+1],Math.max(dp[rem.i][rem.j+1],dp[rem.i-1][rem.j+1]));
               if(max == dp[rem.i-1][rem.j+1]){
                  que.add(new Pair(rem.psf+"d1 ",rem.i-1,rem.j+1));
              }
              if(max == dp[rem.i][rem.j+1]){
                  que.add(new Pair(rem.psf+"d2 ",rem.i,rem.j+1));
              }
              if(max == dp[rem.i+1][rem.j+1]){
                  que.add(new Pair(rem.psf+"d3 ",rem.i+1,rem.j+1));
              }
          }
      }
   }


}