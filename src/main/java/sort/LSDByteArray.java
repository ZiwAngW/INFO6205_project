package sort;

import Util.ByteNode;


public class LSDByteArray {
    public int findLongestLength(ByteNode[] a) {
        int longest = 0;
        for (ByteNode x : a) {
            if (x.getByteArray().length > longest) {
                longest = x.getByteArray().length;
            }
        }
        return longest;
    }

    public int findCharAtInString(int d, ByteNode a) {
        if (d < 0 || d >= a.getByteArray().length) {
            return 0;
        }
        return a.getByteArray()[d] & 0xFF;
    }

    public void sort(String[] a) {
        ByteNode[] byteNodeArray = new ByteNode[a.length];
        for (int i = 0; i < a.length; i++) {
            byteNodeArray[i] = new ByteNode(a[i]);
        }
        sort(byteNodeArray);
        for (int i = 0; i < a.length; i++) {
            a[i] = byteNodeArray[i].getChineseChar();
        }
    }

    public void sort(ByteNode[] a) {
        int R = 256;
        int N = a.length;
        int W = findLongestLength(a);
        ByteNode[] aux = new ByteNode[N];
        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                int c = findCharAtInString(d, a[i]);
                count[c + 1]++;
            }
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];
            for (int i = 0; i < N; i++) {
                int c = findCharAtInString(d, a[i]);
                aux[count[c]++] = a[i];
            }
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }
}
