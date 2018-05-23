package list;

/**
 * @author Kiven Ling
 * 2018/5/23 9:41
 * 顺序表算法题 2.2.3 P18
 */
public class ArrayUtil {
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

}
