// https://leetcode.com/problems/compare-version-numbers/

// reference: https://leetcode.com/problems/compare-version-numbers/solutions/50774/accepted-small-java-solution/
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i = 0; i < length; i++) {
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }

    /*
     * my solution
     * Time: O(n)
     * Space: O(1)
     * compare two strings from left to right, as long as the same revisions are not
     * equal, we can early quit and return the answer; if the length of two strings
     * are not the same, we assign 0 to the shorter one until we finish compare the
     * shorter string with the longer string.
     */
    public int compareVersion(String version1, String version2) {
        //
        while (version1.length() > 0 || version2.length() > 0) {
            String revision1 = "";
            String revision2 = "";
            if (version1.indexOf(".") != -1) {
                revision1 = version1.substring(0, version1.indexOf("."));
                version1 = version1.substring(version1.indexOf(".") + 1);
            } else {
                if (version1.length() != 0)
                    revision1 = version1;
                else
                    revision1 = "0";
                version1 = "";
            }
            if (version2.indexOf(".") != -1) {
                revision2 = version2.substring(0, version2.indexOf("."));
                version2 = version2.substring(version2.indexOf(".") + 1);
            } else {
                if (version2.length() != 0)
                    revision2 = version2;
                else
                    revision2 = "0";
                version2 = "";
            }

            if (Integer.parseInt(revision1) == Integer.parseInt(revision2))
                continue;
            else if (Integer.parseInt(revision1) > Integer.parseInt(revision2))
                return 1;
            else
                return -1;
        }
        return 0;
    }
}
