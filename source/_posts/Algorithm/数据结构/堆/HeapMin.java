
import java.util.Arrays;

/**
 * 大顶堆
 */
public class HeapMin {
    // 当前堆大小
    int size = 0;
    int queue[];

    public HeapMin(int initCapacity) {
        if (initCapacity < 1) {
            throw new IllegalArgumentException();
        }
        this.queue = new int[initCapacity];
    }

    // 单纯的数组，接下来要 构建堆buildHeap
    public HeapMin(int[] arr) {
        size = arr.length;
        queue = new int[arr.length + 1];
        // 从第一个下标开始
        int i = 1;
        for (int val : arr) {
            queue[i++] = val;
        }
    }

    public void buildHeap() {
        for (int i = size >> 1; i > 0; i--) {
            shiftDowm(i);
        }
    }

    /**
     * 向下查找，保证满足 小顶堆
     * 
     * @param i
     */
    public void shiftDowm(int i) {
        // 保存下沉节点
        int tmp = queue[i];
        while (i << 1 <= size) {
            int child = i << 1;
            // 取子节点较小的
            if (child != size && queue[child + 1] < queue[child]) {
                child++;
            }
            if (tmp > queue[child]) {
                queue[i] = queue[child];
                i = child;
            } else {
                break;
            }
        }
        queue[i] = tmp;
    }

    // 最后元素上升
    public void shiftUp(int i) {
        int tmp = queue[i];

        while ((i >> 1) > 0) {
            // 如果 待上升节点比父节点 大，
            if (tmp < queue[i >> 1]) {
                queue[i] = queue[i >> 1];
                i >>= 1;
            } else {
                break;
            }
        }
        queue[i] = tmp;
    }

    public int peek() {
        int res = queue[1];
        return res;
    }

    public void push(int val) {
        // 扩容
        if (size == queue.length - 1) {
            queue = Arrays.copyOf(queue, size << 1 + 1);
        }
        queue[++size] = val;
        shiftUp(size);
    }

    public int pop() {
        int res = queue[1];
        // 将最后一个节点放置到根节点位置
        queue[1] = queue[size--];
        shiftDowm(1);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 7, 4, 1, 8, 1 };
        HeapMin heap = new HeapMin(arr);
        heap.buildHeap();
        // System.out.println(heap.peek());
        heap.push(5);
        while (heap.size > 0) {
            int num = heap.pop();
            System.out.println(num);
        }
    }

}