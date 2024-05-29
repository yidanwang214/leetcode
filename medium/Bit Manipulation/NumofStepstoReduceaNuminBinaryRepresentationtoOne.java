public class NumofStepstoReduceaNuminBinaryRepresentationtoOne {
    public int numSteps(String s) {
        int op = 0, carry = 0;
        boolean first1 = true, first0 = true;
        for (int i = s.length() - 1; i > 0; i--) {
            op++;
            if (s.charAt(i) == '1') {
                if (carry == 0) { // when we met the first 1, carry should be 0
                    carry = 1;
                    op++;
                }
            } else {
                if (carry == 1)
                    op++;
            }
        }
        return op + carry;
    }
}
