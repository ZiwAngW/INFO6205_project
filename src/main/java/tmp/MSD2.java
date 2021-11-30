package tmp;

import Util.Node;

public class MSD2 {
    public int byteAt(Node s, int d)
    {
//        byte[] source = s.getByteArray();
//        if (d < source.length) return source[d]&0xFF;
//        else return -1;
        if (d < s.getByteArray().length) return s.getByteArray()[d]&0xFF;
        else return -1;
    }
    public void sort(String[] a)
    {
        Node[] nodeArray = new Node[a.length];
        for(int i = 0;i<a.length;i++){
            nodeArray[i] = new Node(a[i]);
        }
        Node[] aux = new Node[a.length];
        sort(nodeArray, aux, 0, a.length - 1, 0);
        for(int i = 0;i<a.length;i++){
            a[i] = nodeArray[i].getChineseChar();
        }
    }

    private void sort(Node[] a, Node[] aux, int lo, int hi, int d)
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
