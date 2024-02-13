// https://leetcode.com/problems/delete-characters-to-make-fancy-string/
public class DeleteCharToMakeFancyString {
    /*
     * optimal approach
     * reference:
     * https://www.bilibili.com/video/BV1ri4y1h7Xt/?vd_source=
     * c74db3b5742c8630d40add5529146de2
     */
    public String makeFancyString(String s) {
        if (s.length() < 3)
            return s;
        StringBuilder res = new StringBuilder();
        res.append(s.charAt(0));
        res.append(s.charAt(1));
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i - 2) != s.charAt(i) || s.charAt(i - 1) != s.charAt(i))
                res.append(s.charAt(i));
        }
        return res.toString();
    }

    /*
     * optimal approach 2:
     */
    public String makeFancyString(String s) {
        char prev = s.charAt(0);
        int freq = 1;
        StringBuilder res = new StringBuilder();
        res.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == prev)
                freq++;
            else {
                prev = s.charAt(i);
                freq = 1;
            }
            if (freq < 3)
                res.append(s.charAt(i));
        }
        return res.toString();
    }

    /**
     * my approach:
     * input: "zzzyyyyyxx"
     * i=10
     * output: "zzyyxx"
     * res: zzyyxx
     * counter: 0
     * 
     * if (s[i] == s[i+1]){ // current letter is same as the next letter
     * if(i+1 < length-1){ // when we are able to check the letter 2 after the
     * current letter
     * if(s[i+1] == s[i+2]){ if the letter following the next one is the same as the
     * next one
     * if(count < 2){ // no duplicate or 1 duplicate letter for current letter so
     * far
     * res.append(s[i]); // we can add the current one and the next one to res
     * i++; // increment i so that in next iteration i will point to the next
     * count++; // 1 duplicate letter we have met so far
     * } else { // 2 or more duplicate letters for current letter so far
     * // don't add the letter to res
     * i++;
     * count++;
     * }
     * } else{ // s[i+1] != s[i+2], the next letter is not same as the letter
     * following the next
     * if(count == 0){ // no duplicate letter for current letter so far
     * res.append(s[i]+s[i+1]); // add the current letter and the next to res
     * i += 2; // jump to the letter following the next in next iteration
     * } else if(count == 1){ // 1 duplicate letter for current letter so far
     * res.append(s[i]); // only add the current letter to res to make sure the
     * occurence is no more than 2
     * i += 2; // jump to the letter fowlloing the next in next ietration
     * count = 0; // reset count to be 0 cuz in next iteration the letter we are
     * handling is a new letter
     * } else { // 2 or more duplicate letter for current letter so far
     * // no need to add current letter to res because it has been added to res
     * twice
     * i += 2; // jump to the letter following the next in the next iteration
     * count = 0; // reset count to be 0
     * }
     * }
     * } else if (i+1 == length-1){ // i is the second last, i+1 is the last
     * if(count == 0){ // the letter hasn't appeared before
     * res.append(s[i]+s[i+1]);
     * i += 2;
     * count = 0;
     * } else if (count == 1){ // the letter appeared once before
     * res.append(s[i]);
     * i += 2;
     * count = 0;
     * } else { // the letter has appeared twice or more than twice before
     * // not adding to res
     * i += 2;
     * count = 0;
     * }
     * } else (i+1 > length-1) out of bound, no handling
     * } else { // s[i] != s[i+1]
     * res.append(s[i]);
     * }
     * Time: O(n)
     * Space: O(1)
     */
    public String makeFancyString(String s) {
        if (s.length() <= 2)
            return s;

        StringBuilder res = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                if (i + 1 < s.length() - 1 && s.charAt(i + 1) == s.charAt(i + 2)) {
                    if (counter < 2)
                        res.append(s.charAt(i));
                    counter++;
                } else if (i + 1 < s.length() - 1 && s.charAt(i + 1) != s.charAt(i + 2)) {
                    if (counter == 0) {
                        res.append(s.charAt(i));
                        res.append(s.charAt(i + 1));
                        i++;
                    } else if (counter == 1) {
                        res.append(s.charAt(i));
                        i++;
                        counter = 0;
                    } else {
                        i++;
                        counter = 0;
                    }
                } else if (i + 1 == s.length() - 1) {
                    if (counter == 0) {
                        res.append(s.charAt(i));
                        res.append(s.charAt(i + 1));
                        i++;
                    } else if (counter == 1) {
                        res.append(s.charAt(i));
                        i++;
                        counter = 0;
                    } else {
                        i++;
                        counter = 0;
                    }
                }
            } else {
                System.out.println("i: " + i);
                res.append(s.charAt(i));
            }
        }
        if (res.charAt(res.length() - 1) != s.charAt(s.length() - 1))
            res.append(s.charAt(s.length() - 1));
        else {
            if (res.charAt(res.length() - 2) != s.charAt(s.length() - 1))
                res.append(s.charAt(s.length() - 1));
        }
        return res.toString();
    }
}
