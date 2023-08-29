package Java.assemble.collection.list;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

//LinkedList基础API
//LinkedList是一种线性表，每一个节点存储到下一个节点的地址，与ArrayList相比适合增加和删除，查找和修改的操作效率较低。线程不安全
public class LinkedListDemo {
    public static void main(String[] args) {

        //LinkedList<E> objectName = new LinkedList<>();
        LinkedList<Integer> sites = new LinkedList<>();

        //向链表里添加一些元素
        sites.add(17);
        sites.add(8);
        sites.addFirst(24);//使用addFirst在头部添加元素
        sites.addLast(31);//使用addLast在尾部添加元素

        //打印集合元素
        System.out.println("当前LinkedList集合元素为：" + sites);

        //向指定索引下标处添加元素
        sites.add(2, 19);

        //寻找索引下标为2的元素值
        System.out.println("当前LinkedList集合索引下标 2 的元素值为：" + sites.get(2));

        //获取第一个元素值
        System.out.println("当前LinkedList集合头部元素值为：" + sites.getFirst());

        //获取尾部元素值
        System.out.println("当前LinkedList集合尾部元素值为：" + sites.getLast());

        //修改指定索引下标的元素值
        sites.set(2, 9);
        System.out.println("修改后LinkedList集合元素为：" + sites);

        //删除指定索引下标的元素
        sites.remove(2);
        System.out.println("删除后LinkedList集合元素为：" + sites);

        //删除头部尾部元素
        sites.removeFirst();
        sites.removeLast();
        System.out.println("删除后LinkedList集合元素为：" + sites);

        //计算集合的元素数量
        System.out.println("当前LinkedList集合长度为：" + sites.size());

        //查找集合中是否有目标元素值
        System.out.println("当前LinkedList集合是否有目标元素：" + sites.contains(24));

        //获取目标元素在集合中的索引下标
        System.out.println("目标元素在当前LinkedList集合中索引值为：" + sites.indexOf(24));

        //集合排序与遍历
        Collections.sort(sites);
        Iterator<Integer> iterator = sites.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //清空集合
        sites.clear();
        System.out.println("当前LinkedList集合元素为：" + sites);

        //判断集合中是否有元素
        System.out.println("当前LinkedList集合是否为空：" + sites.isEmpty());
    }
}
