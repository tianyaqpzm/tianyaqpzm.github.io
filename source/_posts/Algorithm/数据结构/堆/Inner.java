public class Inner {

    // PriorityQueue（优先队列），一个基于优先级堆的无界优先级队列。
    // 实际上是一个堆（不指定Comparator时默认为最小堆），通过传入自定义的Comparator函数可以实现大顶堆。

    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); // 小顶堆，默认容量为11
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>() { // 大顶堆，容量11
        @Override
        public int compare(Integer i1, Integer i2) {
            return i2 - i1;
        }
    });

    public static void main(String[] args) {

        // add()和remove()方法在失败的时候会抛出异常(不推荐)
        Queue<String> queue = new LinkedList<String>();

        maxHeap.add(32);
        System.out.println();
    }

    // PriorityQueue的常用方法有：poll(),offer(Object),size(),peek()等。

}
