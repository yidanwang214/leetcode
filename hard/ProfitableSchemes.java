// https://leetcode.com/problems/profitable-schemes/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ProfitableSchemes {

    public static ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> indexRes = new ArrayList<>();
    public static int resNum = 0;
    public static ArrayList<Integer> innerCombo = new ArrayList<Integer>();
    public static ArrayList<Integer> indexCombo = new ArrayList<Integer>();
    public static int profitSum = 0;
    public static int memberSum = 0;

    public static void backtrack(int startIndex, int n, int minProfit, int[] group, int[] profit) {
        for (int i = startIndex; i < profit.length; i++) {
            innerCombo.add(profit[i]);
            indexCombo.add(i);
            profitSum += profit[i];
            memberSum += group[i];
            System.out.print("i: " + i + ", innerCombo: ");
            print(innerCombo);
            print(indexCombo);
            System.out.print(" profitSum: " + profitSum + " memberSum: " + memberSum + "\n");
            if (profitSum < minProfit) {
                if (memberSum < n) {
                    backtrack(i + 1, n, minProfit, group, profit);
                    profitSum -= profit[i];
                    memberSum -= group[i];
                    innerCombo.remove(innerCombo.size() - 1);
                    indexCombo.remove(indexCombo.size() - 1);
                }
                if (memberSum >= n) {
                    profitSum -= profit[i];
                    memberSum -= group[i];
                    innerCombo.remove(innerCombo.size() - 1);
                    indexCombo.remove(indexCombo.size() - 1);
                    backtrack(i + 1, n, minProfit, group, profit);
                    break;
                }
            } else { // profitSum >= minProfit
                if (memberSum <= n) {
                    res.add(copyAL(innerCombo));
                    indexRes.add(copyAL(indexCombo));
                    resNum++;
                    backtrack(i + 1, n, minProfit, group, profit);
                    profitSum -= profit[i];
                    memberSum -= group[i];
                    innerCombo.remove(innerCombo.size() - 1);
                    indexCombo.remove(indexCombo.size() - 1);
                }
                // if(memberSum == n){
                // res.add(innerCombo);
                // profitSum -= profit[i];
                // memberSum -= group[i];
                // innerCombo.remove(innerCombo.size()-1);
                // backtrack(i+1, n, minProfit, group, profit);
                // }
                if (memberSum > n) {
                    profitSum -= profit[i];
                    memberSum -= group[i];
                    innerCombo.remove(innerCombo.size() - 1);
                    indexCombo.remove(indexCombo.size() - 1);
                    backtrack(i + 1, n, minProfit, group, profit);
                    break;
                }

            }
        }

    }

    public static void print(ArrayList<Integer> arr) {
        System.out.print("[");
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + ", ");
        }
        System.out.print("]" + ", Sum: " + sum(arr) + " ");
    }

    public static int sum(ArrayList<Integer> arr) {
        int sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        return sum;
    }

    public static ArrayList<Integer> copyAL(ArrayList<Integer> arr) {
        ArrayList<Integer> newAL = new ArrayList<>();
        for (Integer integer : arr) {
            newAL.add(integer);
        }
        return newAL;
    }

    public static void main(String[] args) {
        int n = 64;
        int minProfit = 0;
        int[] group = { 80, 40 };
        int[] profit = { 88, 88 };
        backtrack(0, n, minProfit, group, profit);

        // System.out.println("start printing resAL");
        // for (ArrayList<Integer> i : res) {
        // print(i);
        // System.out.println();
        // }
        // System.out.println("stop printing resAL");

        // System.out.println("start printing indexRes");
        // for (ArrayList<Integer> i : indexRes) {
        // print(i);
        // System.out.println();
        // }
        // System.out.println("stop printing indexRes");

        System.out.println("resNum: " + resNum);
    }
}
