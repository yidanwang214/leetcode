import java.util.ArrayList;
import java.util.List;

/*
    25525511135
    |
    2552551113|5 -> 255255111|35 -> 25525511|135
    |               |               |
    |               |               |
    |               | 2552551|1|135-> 255255|11|135 -> 25525|511|135 illegal
    |               |        |        |
    |               |        |        |
    |               |        |   25525|5|11|135-> 2552|55|11|135 -> 255|255|11|135
    |               |        |
    |               | 255255|1|1|135-> 25525|51|1|135 -> 2552|551|1|135
    |               |
    |               25525511|1|35 -> 2552551|11|35 -> 255255|111|35
    |               |                |                  |
    |               |                | 25525|5|111|35-2552|55|111|35->255|255|111|35
    |               |                |
    |               |            255255|1|11|35 -> 25525|51|11|35 -> 2552|551|11|35
    |               |
    |               2552551|1|1|35 -> 255255|11|1|35 -> 25525|511|1|35
    255255111|3|5 -> 25525511|13|5 -> 2552551|113|5
    |                       |           |
    |                       |           |
    |                       |           |
    |                       |           |
    |                       |       255255|1|113|5 -> 25525|51|113|5 -> 2552|551|113|5
    |                2552551|1|13|5 -> 255255|11|13|5 -> 25525|511|13|5
    25525511|1|3|5 -> 2552551|11|3|5 -> 255255|111|3|5
 
    101023
    |
    10102|3
 
 */

public class RestoreIPAddresses {
    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        dfs(s.length(), new ArrayList<>(), s);
        return res;
    }

    private void dfs(int offset, List<String> subnet, String s) {
        // base case
        if (subnet.size() == 4) {
            String str = "";
            for (int i = 3; i >= 0; i--)
                str += subnet.get(i);
            // System.out.println(str);
            res.add(str);
            return;
        }

        // explore possibilities
        for (int i = offset - 1; (i >= 0) && (offset - i) <= 3 && (subnet.size() <= 3); i--) {
            String substring = s.substring(i, offset);
            System.out.println("i: " + i + ", offset: " + offset + ", substring: " + substring + ", subnet.size: "
                    + subnet.size());
            if (Integer.valueOf(substring) <= 255) {
                if (substring.length() != 1 && substring.indexOf('0') == 0)
                    continue;
                // save current state
                if (subnet.size() != 3)
                    subnet.add("." + substring);
                else {
                    if (i != 0)
                        continue;
                    subnet.add(substring);
                }

                // dfs
                dfs(i, subnet, s);

                // revert to current state
                subnet.remove(subnet.size() - 1);
            }
        }
    }
}
