package sort;

import Util.WordNode;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class DualPivotQuickPINYIN {
    void sort(WordNode[] A, int left, int right) {
        if (right > left) {
            if (A[left].getPinyin().compareTo(A[right].getPinyin()) > 0) {
                swap(A, left, right);
            }
            WordNode p = A[left];
            WordNode q = A[right];
            int l = left + 1, g = right - 1, k = l;
            while (k <= g) {
                if (A[k].getPinyin().compareTo(p.getPinyin()) < 0) {
                    swap(A, k, l);
                    ++l;
                } else if (A[k].getPinyin().compareTo(q.getPinyin()) > 0) {
                    while (A[g].getPinyin().compareTo(q.getPinyin()) > 0 && k < g) --g;
                    swap(A, k, g);
                    --g;
                    if (A[k].getPinyin().compareTo(p.getPinyin()) < 0) {
                        swap(A, k, l);
                        ++l;
                    }
                }
                ++k;
            }
            --l;
            ++g;
            swap(A, left, l);
            swap(A, right, g);
            sort(A, left, l - 1);
            sort(A, l + 1, g - 1);
            sort(A, g + 1, right);
        }
    }

    public void sort(String[] arr) {
        WordNode[] nodeArr = new WordNode[arr.length];
        try {
            for (int i = 0; i < arr.length; i++) {
                nodeArr[i] = new WordNode(arr[i]);
            }

        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        sort(nodeArr, 0, nodeArr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = nodeArr[i].getChineseChar();
        }
    }

    private void swap(WordNode[] arr, int left, int right) {
        WordNode tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }
}
