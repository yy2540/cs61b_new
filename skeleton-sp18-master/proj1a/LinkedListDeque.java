public class LinkedListDeque<T> {
    private class ItemNode{
        private T item;
        private ItemNode pre;
        private ItemNode next;

        public ItemNode(T item, ItemNode pre, ItemNode next){
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    private int size;
    private ItemNode sentinel;

    public LinkedListDeque(){
        /*
        循环链表与哨兵节点：在双向链表中设置一个sentinel，它的next永远指向第一个节点，pre永远指向最后一个节点
        （即使此时链表为空，只有它自己，那么next和pre就都指向自己）。
         */
        sentinel = new ItemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

    public void addFirst(T item){
        ItemNode a = new ItemNode(item, sentinel, sentinel.next);
        sentinel.next.pre = a;
        sentinel.next = a;
        size++;
    }

    public void addLast(T item){
        ItemNode a = new ItemNode(item, sentinel.pre, sentinel);
        sentinel.pre.next = a;
        sentinel.pre = a;
        size++;
    }

    public boolean isEmpty(){
        /*
        Internet:
        return sentinel.next == sentinel;
         */
        if (size == 0){
            return true;
        }else{
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        ItemNode cur = sentinel;
        while(cur.next != sentinel){
            System.out.println(cur.next.item + " ");
            cur = cur.next;
        }
    }

    public T removeFirst(){
        if (size == 0){
            return null;    //empty deque
        }else{
            T r = sentinel.next.item;
            sentinel.next.next.pre = sentinel;
            sentinel.next = sentinel.next.next;
            size--;
            return r;
        }
    }

    public T removeLast(){
        if (size == 0){
            return null;
        }else{
            T r = sentinel.pre.item;
            sentinel.pre.pre.next = sentinel;
            sentinel.pre = sentinel.pre.pre;
            size--;
            return r;
        }
    }

    public T get(int index){
        if (index >= size){ // index out of bound
            return null;
        }
        ItemNode cur = sentinel;
        for (int i = 0; i < index - 1; i++){
            cur = cur.next;
        }
        return cur.item;
    }

    public T getRecursive(int index){
        if (index >= size){ //index out of bound
            return null;
        }
        return getRecursiveHelper(sentinel, index);
    }

    public T getRecursiveHelper(ItemNode node, int n){
        // basis case
        if (n == 0){
            return node.item;
        }
        // recursive case
        return getRecursiveHelper(node, n - 1);
    }
}
