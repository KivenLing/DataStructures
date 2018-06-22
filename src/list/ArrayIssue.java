package list;

/**
 * @author Kiven Ling
 * 2018/5/23 9:41
 * 顺序表算法题 2.2.3 P18
 */
public class ArrayIssue {
    /**
     * ID:1
     * 从顺序表中删除最小元素(假设唯一)，并返回删除的值
     * 若顺序表位空，返回错误信息，并停止执行
     * @param list 顺序表
     */
    public static <E extends Comparable<E>> E removeMin(ArrayList<E> list) throws Exception {
        if (list.isEmpty()){
            throw new Exception("顺序表为空");
        }
        E min = list.data[0];
        int pos = 0;
        for (int i = 1; i < list.length; i++) {
            E e = list.data[i];
            if (e.compareTo(min) < 0){
                min = e;
                pos = i;
            }
        }
        list.data[pos] = list.data[list.length - 1];
        list.length --;
        return min;
    }

    /**
     * ID: 2
     * 逆置顺序表，空间复杂度O(1)
     * @param list 顺序表
     */
    public static <E extends Comparable<E>> void reverse(ArrayList<E> list){
        int l = 0;
        int r = list.length - 1;
        while (l < r){
            E e = list.data[l];
            list.data[l] = list.data[r];
            list.data[r] = e;
        }
    }

    /**
     * ID: 3
     * 删除所有顺序表中值为x的元素，时间复杂度O(n), 空间复杂度O(1)
     * @param list 顺序表
     */
    public static <E extends Comparable<E>> void remove(ArrayList<E> list, E x){
        int n = list.length;
        int count = 0;//记录list中 存在x的个数
        for (int i = 0; i < n; i++) {
            if (x.equals(list.data[i])){
                count++;
            }else {
                list.data[i - count] = list.data[i];
            }
        }
        list.length -= count;
    }

    /**
     * ID: 4
     * 从有序顺序表删除[s, t]间所有元素，s < t
     * 若s > t 或者 顺序表为空， 返回错误信息并退出
     * @param list 有序顺序表
     */
    public static <E extends Comparable<E>> boolean removeRangOf(ArrayList<E> list,
                                                              E s, E t) {
        if (list.length <= 0 || s.compareTo(t) > 0) {
            return false;
        }
        int l = 0;
        int r = list.length;//在data[l - r)内元素大于等于s，小于等于t
        for (; l < list.length; l++) {
            if (list.data[l].compareTo(s) >= 0){//data[i] >= s
                break;
            }
        }
        if (l >= list.length)
            return false;
        for (int i = l; i < list.length; i++) {
            if (list.data[i].compareTo(t) > 0){//data[i] > t
                r = i;
                break;
            }
        }
        //在data[l - r)内元素大于等于s，小于等于t
        while (r < list.length){
            list.data[l] = list.data[r];
            l++;
            r++;
        }
        list.length -= (r - l);
        return true;
    }

    /**
     * ID: 5
     * 从顺序表删除[s, t]间所有元素，s < t
     * 若s > t 或者 顺序表为空， 返回错误信息并退出
     * @param list 顺序表
     * 注意这里顺序表不是有序的
     */
    public static <E extends Comparable<E>> boolean removeRang(ArrayList<E> list,
                                                              E s, E t) {
        if (list.length <= 0 || s.compareTo(t) > 0) {
            return false;
        }
        int count = 0;//记录list中 [s - t]中个数
        for (int i = 0; i < list.length; i++) {
            if (list.data[i].compareTo(s) >= 0 && list.data[i].compareTo(t) <= 0){
                //s <= data[l] <= t
                count++;
            }else {
                list.data[i - count] = list.data[i];
            }
        }
        list.length -= count;
        return true;
    }

    /**
     * 删除有序顺序表中所有不同元素，使顺序表元素各不相同
     * @param list 有序顺序表
     */
    public static <E extends Comparable<E>> void removeSame(ArrayList<E> list) {
        if (list.length <= 1)
            return;
        int tail = 0;
        int next;
        for (next = 1; next < list.length; next++) {
            if (!list.data[next].equals(list.data[tail])){
                tail++;
                list.data[tail] = list.data[next];
            }
        }
        list.length = tail + 1;
    }

    /**
     * ID：7
     * 两个顺序表合并成新的顺序表并返回
     * @param l1 有序顺序表1
     * @param l2 有序顺序表2
     * @return 新的顺序表
     */
    public static <E extends Comparable<E>> ArrayList<E> mergeList(ArrayList<E> l1,
                                                                   ArrayList<E> l2){
        ArrayList<E> newList = new ArrayList<>((l1.length + l2.length) * 2);
        int i1 = 0;
        int i2 = 0;
        int index = 0;
        while (i1 < l1.length && i2 < l2.length){
            if (l1.data[i1].compareTo(l2.data[i2]) < 0){
                newList.data[index++] = l1.data[i1++];
            }else{
                newList.data[index++] = l2.data[i2++];
            }
        }
        while (i1 < l1.length){
            newList.data[index++] = l1.data[i1++];
        }
        while (i2 < l2.length){
            newList.data[index++] = l2.data[i2++];
        }
        newList.length = index;
        return newList;
    }

    /**
     * ID: 8
     * 将A[m + n] m n数据块位置互换
     * @param A 数组
     */
    public static <E> void swapTwoArrays(E[] A, int m, int n){
        if (m + n != A.length || A.length <= 0)
            return ;
        reverse(A, 0, m - 1);
        reverse(A, m, A.length - 1);
        reverse(A, 0, A.length - 1);
    }

    //将数组A从[l-r]逆置
    private static <E> void reverse(E[] A, int l, int r){
        if (l >= r || A.length <= r)
            return;
        while (l < r){
            E temp = A[l];
            A[l] = A[r];
            A[r] = temp;
            l++;
            r--;
        }
    }

    /**
     * ID: 9
     * 要求在有序有序递增顺序表寻找x， 若存在，则令x与其后继元素交换位置,返回true
     * 若不存在，将x插入顺寻表使其任然有序，返回false
     */
    public static <E extends Comparable<E>> boolean search(ArrayList<E> list, E x){
        //小于最小值
        if (x.compareTo(list.data[0]) < 0){
            list.length++;
            for (int i = list.length; i > 0; i--) {
                list.data[i] = list.data[i - 1];
            }
            list.data[0] = x;
            return false;
        }
        //大于最大值
        if (x.compareTo(list.data[list.length - 1]) > 0){
            list.data[list.length++] = x;
            return false;
        }
        //二分查找
        int l = 0, r = list.length - 1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if (x.compareTo(list.data[mid]) > 0){
                l = mid + 1;
            }else if (x.compareTo(list.data[mid]) == 0){
                if (mid < r){
                    E temp = list.data[mid];
                    list.data[mid] = list.data[mid + 1];
                    list.data[mid + 1] = temp;
                }
            }else {
                r = mid - 1;
            }
        }
        return true;
    }

    /**
     * ID: 10
     * 将n(n > 0)整数存放在一维数组R， 将R循环左移p位
     * 想法：将整个数组逆置，将后p个元素逆置，将前面n - p个元素逆置即可
     * 算法时间复杂度 O(n)， 空间复杂度O(1)
     */
    public static void leftShiftP(int[] R, int p) {
        int n = R.length;
        //n - 1, n - 2, ... 3, 2, 1, 0
        reverse(R, 0, n - 1);
        //p, p + 1 , p + 2, ... n - 1
        reverse(R, 0, n - p - 1);
        //0, 1, 2, ... p - 1
        reverse(R,n - p , n - 1);
    }

    //将数组R从R[l, r]区间内逆置， l <= r < R.length
    public static void reverse(int[] R, int l, int r){
        if (l < 0 || r >= R.length)
            return;
        while (l < r){
            int temp = R[l];
            R[l++] = R[r];
            R[r--] = temp;
        }
    }

    /**
     * ID: 11
     * 长度为L的增序列，中位数在第 向上取整(L / 2) 的位置,这里的位置指的是数学位置
     * 现有两个长度相等的升序数组A, B，求他们的俩序列的中位数
     * 思路：利用merge的思想，中位数的数学位置是数组的长度n，从两个升序数组找到第n大数
     * Time O(n / 2) => O(n), Space O(1)
     *
     * 答案思路：寻找每个数列中位数  a, b
     * 若 a == b， 那么a即为所求
     * 若 a < b, 那么A较小部分，B较大部分，舍弃元素个数相同
     * 若a > b,同理
     * 重复上述步骤，直至只有一个元素，此时求元素小的
     * time O(logn)
     */

    public static int getMidNum(int[] A, int[] B){
        int len = A.length;
        //若其中一升序列最大值小于或等于另外一升序列最小值，那么中位数即小升序列的最大值
        if (A[len - 1] <= B[0])
            return A[len - 1];
        if (B[len - 1] <= A[0])
            return B[len - 1];
        //两个数组的左右索引
        int aL = 0, bL = 0;
        //已经比较出count个数字大小
        int count = 0;
        int mid = 0;
        while (count < len){
            if (A[aL] <= B[bL]){
                mid = A[aL++];
            }else {
                mid = B[bL++];
            }
            count++;
        }
        return mid;
    }

    /**
     * ID: 12
     * 整数序列A， 包含n个元素，每个元素0 <= A[i] < n
     * 找出元素个数大于数列A长度一半的元素，即主元素并返回
     * 若不存在，返回-1
     * 思路：开辟一数组，大小为n，记录A数字出现频率
     * 找到频率最高的数字，与长度一般比较，若大于，符合要求，否则返回-1
     * time: O(n), space O(n)
     */

    public static int getMainElement(int[] A){
        int n = A.length;
        int m = n / 2;
        int[] count = new int[n];
        int maxCount = -1;
        int maxCountNum = -1;
        for (int i : A) {
            count[i] ++;
            if (count[i] > maxCount){
                maxCount = count[i];
                maxCountNum = i;
            }
        }
        if (maxCount > m)
            return maxCountNum;
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("test ID: 8");
        Integer[] a = new Integer[10];
        for (int i = 0; i < 10; i++) {
            a[i] = i;
        }
        swapTwoArrays(a, 3, 7);
        for (int i = 0; i < 10; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        System.out.println("ID: 8 -------- end");
    }
}
