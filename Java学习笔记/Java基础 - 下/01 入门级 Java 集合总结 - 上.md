# 入门级 Java 集合总结 - 上

## 1. 集合概述
集合是Java中提供的一种容器，可以用来存储多个数据

## 2. 集合与数组的区别
- 数组的长度是固定的，集合的长度是可变的。
- 数组中存储的是同一类型的元素，可以存储基本数据类型值。集合存储的都是对象，而且对象的类型可以不一致。在可发中一般当对象多的时候，使用集合惊醒存储。

## 3. 集合分类
集合按照其存储结构可以分为两大类：
- 单列集合：`java.util.Collertion` 单列集合类的根接口，用于存储一系列符合某种规则的元素
- 双列集合：`java.util.Map`

## 4. Collection常用功能
- `boolean add(E e)`: 把给定的对象添加到当前的集合中
- `void clear()`: 清空集合中的所有元素
- `boolean remove(E e)`: 把给定的对象从当前集合中删除
- `boolean contains(E e)`: 判断当前集合中是否包含给定的对象
- `boolean isEmpty()`：判断当前集合是否为空
- `int size()`: 返回集合中的元素个数
- `Object[] toArray()`: 把集合中的元素存储到数组中

## 5. 使用迭代器对集合进行取元素
~~~java
@Test
    public void Iterator() {

        ArrayList<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);

        // Iterator iterator(): 获取集合对应的迭代器，用来遍历集合中的元素的。
        Iterator<Integer> iterator = integers.iterator();
        // boolean hasNext():如果迭代具有更多元素，则返回 true 。
        while (iterator.hasNext()) {
            // E next():返回迭代中的下一个元素。
            System.out.println(iterator.next());
            // hasNext方法返回false，表示到达了集合的末尾，终止对元素的遍历。
            System.out.println(iterator.hasNext());
        }
    }
    
    输出结果：
    1
    true
    2
    true
    3
    true
    4
    true
    5
    true
    6
    false    
~~~

## 6. 迭代器的实现原理
当遍历集合时，首先通过调用集合的iterator()方法获得迭代器对象，然后使用hashNext()方法判断集合中是否存在下一个元素，如果存在，则调用next()方法将元素取出，否则说明已到达了集合末尾，停止遍历元素。
Iterator迭代器对象在遍历集合时，内部采用指针的方式来跟踪集合中的元素

在调用Iterator的next方法之前，迭代器的索引位于第一个元素之前，不指向任何元素，当第一次调用迭代器的next方法后，迭代器的索引会向后移动一位，指向第一个元素并将该元素返回，当再次调用next方法时，迭代器的索引会指向第二个元素并将该元素返回，依此类推，直到hasNext方法返回false，表示到达了集合的末尾，终止对元素的遍历。

## 7. 说出集合的使用细节

## 8. 泛型：可以在类或方法中预支地使用未知的类型。
泛型，用来灵活地将数据类型应用到不同的类、方法、接口当中。将数据类型作为参数进行传递。

## 9. 理解泛型上下限
泛型的通配符:不知道使用什么类型来接收的时候,此时可以使用`?`
- `?` 表示未知通配符。

通配符高级使用----受限泛型 \
设置泛型的时候，实际上是可以任意设置的，只要是类就可以设置。但是在JAVA的泛型中可以指定一个泛型的上限和下限。

泛型的上限：
- 格式： 类型名称 <? extends 类 > 对象名称
- 意义： 只能接收该类型及其子类 

泛型的下限：
- 格式： 类型名称 <? super 类 > 对象名称
- 意义： 只能接收该类型及其父类型

~~~java
// 比如：现已知Object类，String 类，Number类，Integer类，其中Number是Integer的父类
public static void main(String[] args) {
    Collection<Integer> list1 = new ArrayList<Integer>();
    Collection<String> list2 = new ArrayList<String>();
    Collection<Number> list3 = new ArrayList<Number>();
    Collection<Object> list4 = new ArrayList<Object>();
    
    getElement(list1);
    getElement(list2);//报错
    getElement(list3);
    getElement(list4);//报错
  
    getElement2(list1);//报错
    getElement2(list2);//报错
    getElement2(list3);
    getElement2(list4);
  
}
// 泛型的上限：此时的泛型?，必须是Number类型或者Number类型的子类
public static void getElement1(Collection<? extends Number> coll){}
// 泛型的下限：此时的泛型?，必须是Number类型或者Number类型的父类
public static void getElement2(Collection<? super Number> coll){}
~~~

## 10. 阐述泛型通配符的作用
当使用泛型类或者接口时，传递的数据中，泛型类型不确定，可以通过通配符<?>表示。但是一旦使用泛型的通配符后，只能使用Object类中的共性方法，集合中元素自身方法无法使用。

--- 
>ps : 仅供本人学习使用