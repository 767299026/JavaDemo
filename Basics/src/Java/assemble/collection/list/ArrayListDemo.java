package Java.assemble.collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

//ArrayList集合基础API
//ArrayList以数组为基础,没有固定大小的限制，与LinkedList相比适合查找和修改，增加和删除的操作效率较低。线程不安全
public class ArrayListDemo {

    public static void main(String[] args) {

        //ArrayList<E> objectName = new ArrayList<>();
        ArrayList<Integer> sites = new ArrayList<>();

        //向数组末尾里添加一些元素
        sites.add(17);
        sites.add(8);
        sites.add(24);
        sites.add(31);

        //打印集合元素
        System.out.println("当前ArrayList集合元素为：" + sites);

        //向指定索引下标处添加元素
        sites.add(2, 19);

        //寻找索引下标为2的元素值
        System.out.println("当前ArrayList集合索引下标 2 的元素值为：" + sites.get(2));

        //修改指定索引下标的元素值
        sites.set(2, 9);
        System.out.println("修改后ArrayList集合元素为：" + sites);

        //删除指定索引下标的元素
        sites.remove(2);
        System.out.println("删除后ArrayList集合元素为：" + sites);

        //计算集合的元素数量
        System.out.println("当前ArrayList集合长度为：" + sites.size());

        //查找集合中是否有目标元素值
        System.out.println("当前ArrayList集合是否有目标元素：" + sites.contains(24));

        //获取目标元素在集合中的索引下标
        System.out.println("目标元素在当前ArrayList集合中索引值为：" + sites.indexOf(24));

        //集合排序与迭代器遍历
        Collections.sort(sites);
        Iterator<Integer> iterator = sites.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //清空集合
        sites.clear();
        System.out.println("当前ArrayList集合元素为：" + sites);

        //判断集合中是否有元素
        System.out.println("当前ArrayList集合是否为空：" + sites.isEmpty());
    }
}
