package Java.assemble.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//HashMap集合基础API
//由数组和链表以及红黑树组成的数据结构，由hash决定元素位置，线程不安全
public class HashMapDemo {
    public static void main(String[] args) {

        //HashMap<E, E> sites = new HashMap<>();
        HashMap<Integer, String> sites = new HashMap<>();

        //向集合添加元素
        sites.put(1, "Google");
        sites.put(1, "Jingdong");//计算出相同的hashcode,在当前桶中覆盖上一个Entry
        sites.put(2, "Runoob");
        sites.put(3, "Taobao");
        sites.put(4, "Zhihu");
        System.out.println("当前HashMap集合元素为：" + sites);

        //通过Key获取对应Value值
        System.out.println(sites.get(1));

        //删除指定Key对应的键值对
        sites.remove(3);

        //替换指定Key对应的Value
        sites.replace(4, "Taobao");

        //查找集合中是否有目标Key
        System.out.println("当前HashMap集合是否有目标元素：" + sites.containsKey(3));

        //查找集合中是否有目标Value
        System.out.println("当前HashMap集合是否有目标元素：" + sites.containsValue("Taobao"));

        //计算集合的元素数量
        System.out.println("当前HashMap集合长度为：" + sites.size());

        //通过KeySet()遍历
        for (Integer i : sites.keySet()) {
            System.out.println("key: " + i + " value: " + sites.get(i));
        }

        //迭代器遍历
        Iterator<Map.Entry<Integer, String>> iterator = sites.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next();
            Integer key = iterator.next().getKey();
            String value = iterator.next().getValue();
            System.out.println("key: " + key + " value: " + value);
        }

        //清空集合
        sites.clear();
        System.out.println("当前HashMap集合元素为：" + sites);

        //判断集合中是否有元素
        System.out.println("当前HashMap集合是否为空：" + sites.isEmpty());
    }
}