package sort;

import Util.Node;


public class LSDByteArray {
    public int findLongestLength(Node[] a) {
        int longest = 0;
        for (Node x : a) {
            if (x.getByteArray().length > longest) {
                longest = x.getByteArray().length;
            }
        }
        return longest;
    }

    public int findCharAtInString(int d, Node a) {
        if (d < 0 || d >= a.getByteArray().length) {
            return 0;
        }
        return a.getByteArray()[d] & 0xFF;
    }

    public void sort(String[] a) {
        Node[] nodeArray = new Node[a.length];
        for (int i = 0; i < a.length; i++) {
            nodeArray[i] = new Node(a[i]);
        }
        sort(nodeArray);
        for (int i = 0; i < a.length; i++) {
            a[i] = nodeArray[i].getChineseChar();
        }
    }

    public void sort(Node[] a) {
        int R = 256;
        int N = a.length;
        int W = findLongestLength(a);
        Node[] aux = new Node[N];
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
