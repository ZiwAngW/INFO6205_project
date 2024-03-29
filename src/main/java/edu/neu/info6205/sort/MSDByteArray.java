package edu.neu.info6205.sort;

import edu.neu.info6205.Util.ByteNode;

/**
 * main idea is copied from class slides
 * https://northeastern.instructure.com/courses/91206/files/12221171?module_item_id=6199667
 * MSD algorithm with transfer the input to Collator.getCollationKey(s).toByteArray() to count
 */
public class MSDByteArray {
    public int byteAt(ByteNode s, int d)
    {
        if (d < s.getByteArray().length) return s.getByteArray()[d]&0xFF;
        else return -1;
    }
    public void sort(String[] a)
    {
        ByteNode[] byteNodeArray = new ByteNode[a.length];
        for(int i = 0;i<a.length;i++){
            byteNodeArray[i] = new ByteNode(a[i]);
        }
        ByteNode[] aux = new ByteNode[a.length];
        sort(byteNodeArray, aux, 0, a.length - 1, 0);
        for(int i = 0;i<a.length;i++){
            a[i] = byteNodeArray[i].getChineseChar();
        }
    }

    private void sort(ByteNode[] a, ByteNode[] aux, int lo, int hi, int d)
    {
        int R = 256;
        if (hi <= lo) return;
        int[] count = new int[R+2];
        for (int i = lo; i <= hi; i++)
            count[byteAt(a[i], d) + 2]++;
        for (int r = 0; r < R+1; r++)
            count[r+1] += count[r];
        for (int i = lo; i <= hi; i++)
            aux[count[byteAt(a[i], d) + 1]++] = a[i];
        if (hi + 1 - lo >= 0) System.arraycopy(aux, 0, a, lo, hi + 1 - lo);
        for (int r = 0; r < R; r++)
            sort(a, aux, lo + count[r], lo + count[r+1] - 1, d+1);
    }
}
