

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /**
     * 将双端队列中数的下一个数的位置映射到实际的数组中。
     * 例如，这一个数索引为items.length-1（即数组最后一位），则下一个数
     * 应该位于0位置.若索引还不到最后一位，则下一个数的位置
     * 就是下一个数的索引，正常+1即可。
     * <p>
     * 这个trick应该很常用于循环。每次将索引+1，一到某个阈值
     * 就从头开始。
     *
     * @param a
     * @return
     */
    private int addOne(int a) {
        return (a + 1) % items.length;
    }

    private int subOne(int a) {
        return (a - 1 + items.length) % items.length;
    }

    /**
     * resize the array.
     * 主要思想是，将原数组从first到end复制新数组的0到size中。
     * 则新数组的nextFirst为新数组的最后一个，新数组的nextLast为新数组的size索引。
     *
     * @param length
     */
    private void resize(int length) {
        T[] newItems = (T[]) new Object[length];
        int oldindex = addOne(nextFirst);
        for (int i = 0; i < size; i++) {
            newItems[i] = items[oldindex];
            oldindex = addOne(oldindex);
        }
        this.items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void addFirst(T item){
        if (size == items.length){  //数组满了要扩容
            resize(2 * size);
        }
        items[nextFirst] = item;
        nextFirst = subOne(nextFirst);
        size++;
    }

    public void addLast(T item){
        if (size == items.length){  //数组满了要扩容
            resize(2 * size);
        }
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for (int i = 0; i < items.length; i++){
            if (items[i] == null){
                continue;
            }
            System.out.print(items[i] + " ");
        }
    }

    public T removeFirst(){
        if (size == 0){
            return null;
        }
        T rm = items[addOne(nextFirst)];
        items[addOne(nextFirst)] = null;
        nextFirst = addOne(nextFirst);
        size--;
        if (items.length >= 16 && size < (items.length) / 4){   //数组密度不到1/4要减少容量
            resize(items.length / 2);
        }
        return rm;
    }

    public T removeLast(){
        if (size == 0){
            return null;
        }
        T rm = items[subOne(nextLast)];
        items[subOne(nextLast)] = null;
        nextLast = subOne(nextLast);
        size--;
        if (items.length >= 16 && size < (items.length) / 4){   //数组密度不到1/4要减少容量
            resize(items.length / 2);
        }
        return rm;
    }

    public T get(int index){
        if (index >= items.length){ //目标索引超出数组长度
            return null;
        }
        return items[index];
    }
}
