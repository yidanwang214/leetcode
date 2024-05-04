/*
people[i]: [3,4,5,2,1], limit: 5 -> 3
1. sort: [1,2,3,4,5]
2. keep a right pointer and left pointer, scan from left to right, while r < l:
    if people[r] == limit, res++, right--
    else: // people[i] < limit
       if(people[r]+people[l] <= limit){
            increment res and l;
            decrement r;
       } else{
        increment res;
        decrement r;
       }
3. edge case, when the num of boats is odd: if r == l, res +++ -> eg: [1,2,3,3,4],limit=5, [1,4][2,3]
                                                                           || l = r
return res;

Time: O(nlogn) -> sort(nlogn) + scan through the array use two pointer
Space: O(1)
*/

import java.util.Arrays;

public class BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int res = 0, l = 0, r = people.length - 1;
        while (l < r) {
            if (people[r] == limit) {
                res++;
                r--;
            } else {
                if (people[l] + people[r] <= limit) {
                    r--;
                    l++;
                    res++;
                } else {
                    r--;
                    res++;
                }
            }
        }
        if (l == r)
            res++;
        return res;
    }
}
