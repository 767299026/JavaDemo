package Java.assemble.collection.set;

import java.util.HashSet;
import java.util.Iterator;

//HashSet基础API
//HashSet基于HashMap实现,不允许有重复元素。允许有null值但只允许一个，无序，线程不安全。
//通过equals方法与hashcode比较是否相等，如果要把一个对象放入HashSet中，重写该对象对应类的equals方法，也应该重写其hashCode()方法。
public class HashSetDemo {
    public static void main(String[] args) {

        //HashSet<E> objectName = new HashSet<>();
        HashSet<Integer> sites = new HashSet<>();

        //向集合中添加一些元素
        sites.add(17);
        sites.add(8);
        sites.add(24);
        sites.add(31);
        sites.add(31);//重复元素不会被添加

        //打印集合元素
        System.out.println("当前HashSet集合元素为：" + sites);

        //删除指定元素
        sites.remove(17);
        System.out.println("当前HashSet集合元素为：" + sites);

        //查找集合中是否有目标元素值
        System.out.println("当前HashSet集合是否有目标元素：" + sites.contains(24));

        //计算集合的元素数量
        System.out.println("当前HashSet集合长度为：" + sites.size());

        //迭代器遍历
        Iterator<Integer> iterator = sites.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //清空集合
        sites.clear();
        System.out.println("当前HashSet集合元素为：" + sites);

        //判断集合中是否有元素
        System.out.println("当前HashSet集合是否为空：" + sites.isEmpty());

    }
}
