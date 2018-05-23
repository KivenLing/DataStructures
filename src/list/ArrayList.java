package list;

/**
 * @author Kiven Ling
 * 2018/5/21 21:20
 * 线性表之顺序表
 */
public class ArrayList<Item> {
    //动态顺序表数据元素
    private Item[] data;
    //初始化时，顺序表的容量
    private int capacity;
    //实际顺序表的大小
    private int length;

    public ArrayList(int capacity){
        this.data = (Item[]) new Object[capacity];
        this.capacity = capacity;
        this.length = 0;
    }

    /**
     * @return 顺序表实际大小
     */
    public int size(){
        return this.length;
    }

    /**
     * @return 顺序表为空返回true，否则返回false
     */
    public boolean isEmpty(){
        return this.length == 0;
    }

    /**
     * 向顺序表指定位置i插入一项数据e
     * @param i 插入数据位置，这里的i指的是线性表的概念，从1开始(考研书这里的设计真是反人类)
     * @param e 插入的数据
     * @return 插入有异常返回false
     */
    public boolean insert(int i, Item e){
        if (i < 1 || i > length + 1)
            return false;
        if (length >= this.capacity)//存储已经满了
            return false;
        for (int j = this.length; j >= i ; j--) {
            data[j] = data[j - 1];
        }
        data[i - 1] = e;
        this.length++;
        return true;
    }

    /**
     * 只有数据时默认插入队尾
     * @return 插入有异常返回false
     */
    public boolean insert(Item e){
        return insert(this.length + 1, e);
    }

    /**
     * 删除指定索引位置的元素并返回其值，当i不在顺序表范围内抛出异常
     * @param i 插入数据位置，这里的i指的是线性表的概念，从1开始
     * @return 被删除元素
     */
    public Item remove(int i) {
        if (i < 1 || i > length)
            throw new IndexOutOfBoundsException();
        Item oldValue = data[i - 1];
        for (int j = i - 1; j < length - 1; j++) {//将元素前移
            data[j] = data[j + 1];
        }
        this.length--;
        return oldValue;
    }

    /**
     * 删除顺序表中第一个出现e的元素
     * @param e 需要被删除的元素
     * @return 当顺序表存在元素e，删除并返回true，否则返回false
     */
    public boolean remove(Item e){
        if (e == null)
            return false;
        for (int i = 0; i < this.length; i++) {
            if (e.equals(data[i])){
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * 查找相关元素e在顺序表的第一个位置
     * @param e 被查找的元素
     * @return 元素在顺序表的索引值
     *
     * 如果需要返回线性表的位序，那么这里返回值需要 +1，具体看题目需求
     */
    public int indexOf(Item e){
        if (e == null){
            for (int i = 0; i < length; i++) {
                if (data[i] == null)
                    return i;
            }
        }else {
            for (int i = 0; i < length; i++) {
                if (e.equals(data[i]))
                    return i;
            }
        }
        return -1;
    }

    /**
     * @return 打印显示的字符串，类似1 -> 2 -> 3
     */
    @Override
    public String toString() {
        if (length == 0)
            return "null";
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < length - 1; i++)
            s.append(data[i]).append(" -> ");
        s.append(data[length - 1]);
        return s.toString();
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(20);
        for (int i = 0; i < 10; i++) {
            list.insert(i);
        }
        System.out.println(list.isEmpty());
        System.out.println(list.size());
        System.out.println(list.remove(3));
        System.out.println(list.insert(3, 3));
        System.out.println(list.indexOf(8));
        System.out.println(list.toString());
    }
}
