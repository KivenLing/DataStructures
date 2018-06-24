package list;

/**
 * 单链表 实现书本基本操作
 * @author Kiven Ling
 * 2018/6/22 21:15
 */
public class LinkedList<E extends Comparable<E>> {
//    public LNode<E> dummyRoot = new LNode<>();//空头节点
//    public int length = 0;
//
    //采用头插法建立单链表
    //root伪头节点
    //arr数据数组
    public static <E> LNode<E> createLinkedListHead(E[] arr, LNode<E> root){
        for (E data : arr) {
            LNode<E> node = new LNode<>();
            node.data = data;
            node.next = root.next;
            root.next = node;
        }
        return root;
    }

    //采用尾插法建立单链表
    //root伪头节点
    //arr数据数组
    public static <E> LNode<E> createLinkedListTail(E[] arr, LNode<E> root){
        LNode tail = root;
        for (E data : arr) {
            LNode<E> node = new LNode<>();
            node.data = data;
            tail.next = node;
            tail = node;
        }
        return root;
    }

    /**
     * 按序号查找节点值
     * @param i 寻找第i个节点
     * @param root 伪头节点
     * @return 若 i < 0则返回null，若i == 0 返回root 否则返回第i个节点
     */
    public static <E> LNode<E> getElem(int i, LNode<E> root){
        if (i < 0)
            return null;
        if (i == 0)
            return root;
        int count = 1;
        LNode<E> node = root.next;
        while (count < i && node != null){
            node = node.next;
            count++;
        }
        return node;
    }

    /**
     * 按值查找表节点
     * @param data 查找的值
     * @param root 伪头节点
     *
     */
    public static <E> LNode<E> getElem(E data, LNode<E> root){
        LNode<E> node = root.next;
        while (node != null && !node.data.equals(data)){
            node = node.next;
        }
        return node;
    }

    /**
     * 在链表root第i位置插入一个节点
     * @param i 位置索引
     * @param node 要插入的节点
     * @param root 伪头节点
     */
    public static <E> LNode<E> insert(int i, LNode<E> node, LNode<E> root){
        i = (i <= 0) ? 1 : i;
        LNode<E> pre = getElem(i - 1, root);
        if (pre != null){
            node.next = pre.next;
            pre.next = node;
        }
        //pre == null 插入尾部，略
        return root;
    }

    /**
     * 删除第i个节点
     * @param i 需要删除的节点索引
     * @param root 伪头节点
     *
     */
    public static <E> LNode<E> delete(int i, LNode<E> root){
        i = (i <= 0) ? 1 : i;
        LNode<E> pre = getElem(i - 1, root);
        if (pre != null){
            LNode<E> del = pre.next;
            pre.next = del.next;
        }
        return root;
    }

    /**
     * 求链表长度
     * @param root 伪头节点
     */
    public static <E> int getLength(LNode<E> root){
        int count = 0;
        LNode beg = root.next;
        while (beg != null){
            beg = beg.next;
            count++;
        }
        return count;
    }
}

class LNode<E>{
    E data;
    LNode<E> next;
}
