package Java.assemble.collection.set;

import java.util.Iterator;
import java.util.TreeSet;

//TreeSet基础API
//TreeSet基于TreeMap实现,不允许有重复元素。Key不允许为空，但Value允许有null值，有序，线程不安全。
//判断两个对象不相等的方式是两个对象通过equals方法返回false，或者通过CompareTo方法比较没有返回0
public class TreeSetDemo {

    public static void main(String[] args) {

        //TreeSet<E> objectName = new TreeSet<>();
        TreeSet<Integer> sites = new TreeSet<>();

        //向集合添加一些元素
        sites.add(17);
        sites.add(8);
        sites.add(24);
        sites.add(31);
        sites.add(31);//重复元素不会被添加

        //打印集合元素(基本数据自然排序)
        System.out.println("当前TreeSet集合元素为：" + sites);

        //查找集合中是否有目标元素值
        System.out.println("当前TreeSet集合是否有目标元素：" + sites.contains(24));

        //删除指定元素
        sites.remove(17);
        System.out.println("当前TreeSet集合元素为：" + sites);

        //计算集合的元素数量
        System.out.println("当前TreeSet集合长度为：" + sites.size());

        //返回集合中小于或等于给定元素的最大值，如果没有,则null
        System.out.println(sites.floor(24));

        //返回集合中小于给定元素的最大值，如果没有,则null
        System.out.println(sites.lower(24));

        //返回集合中大于或等于给定元素的最大值，如果没有,则null
        System.out.println(sites.ceiling(24));

        //返回集合中大于定元素的最大值，如果没有,则null
        System.out.println(sites.higher(24));


        //新建一个集合，添加自定义类
        TreeSet<Person> set = new TreeSet<>();
        set.add(new Person("成龙", 13));
        set.add(new Person("成龙", 13));//判定为重复元素，添加失败
        set.add(new Person("胡歌", 22));
        set.add(new Person("刘亦菲", 11));
        set.add(new Person("周星驰", 16));
        set.add(new Person("李连杰", 56));

        //打印集合元素(自定义比较器 年龄升序排序)
        System.out.println("当前TreeSet集合元素为：" + set);

        //迭代器遍历
        Iterator<Person> iterator1 = set.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }

        //降序迭代器遍历
        Iterator<Person> iterator2 = set.descendingIterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }
}

//定义类测试TreeSet的自定义比较
class Person implements Comparable {
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        return this.age - p.getAge();
    }
}