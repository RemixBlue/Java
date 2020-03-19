# 常用API 
## 1、 API
### 概述：
API(Application Programming Interface)，应用程序编程接口。Java API是一本程序员的 字典 ，是JDK中提供给我们使用的类的说明文档。
### API 使用步骤
1. 打开帮助文档。 
2. 点击显示，找到索引，看到输入框。 
3. 你要找谁？在输入框里输入，然后回车。 
4. 看包。java.lang下的类不需要导包，其他需要。 
5. 看类的解释和说明。 
6. 学习构造方法。
7. 使用成员方法。
---
## 2、 Scanner类
`java.util.Scanner ` 是 Java5 的新特征，我们可以通过 Scanner 类来获取用户的输入。 \
Scanner类 : 一个可以解析基本类型和字符串的简单文本扫描器。

### 2. 1 使用 Scaanner 类
* 查看类
* 查看构造方法
* 查看成员方法

* 导包
    * 使用import关键字进行导包，引用要使用的类型，java.lang包下的所有类不需要导包。
    > 格式：`import 包名.类名; `

* 创建对象
>创建 Scanner 对象的基本语法：
~~~java
// 数据类型 变量名1 = new 数据类型(参数列表);
// 数据类型 变量名2 = 变量名1.方法名();

// 创建Scanner对象
Scanner sc = new Scanner(System.in);
// 调用方法并接受数据
int i = sc.nextInt(); // 接收一个键盘录入的整数
// 输出数据
System.out.println("i = " + i);
~~~
> `System.in`系统输入指的是通过键盘录入数据。

### 2. 2 匿名对象【了解】
#### 概念
创建对象时，只有创建对象的语句，却没有把对象地址值赋值给某个变量。虽然是创建对象的简化写法，但是应用 场景非常有限。 
* 匿名对象 ：没有变量名的对象。
* 使用：如果确定有一个对象只需要使用一次，就可以用匿名对象。
>创建对象的标准格式 :

`类名称 对象名 = new 类名称();`

* 匿名对象就是只有右边的对象，没有左边的名字和赋值运算符。
> 格式 : 

`new 类名(参数列表); `
* 匿名对象可以作为方法的参数和返回值。
>ps:匿名对象只能创建后只能使用一次，下次创建使用是就是一个新的匿名对象。

---
## 3、Random 类
Random类的实例用于生成伪随机数。
* 使用步骤：
1. 导包
`import java.util.Random;`
2. 创建
`Random r = new Random(); // 小括号留空即可`
3. 使用
~~~java
// 获取一个随机的int数字：
int num =  nextInt() 生成范围是int所有范围，包含正负数
int num =  nextInt(int a) 生成范围是0~a（不包含a） a只能为正整数
~~~

--- 
## 4、 ArrayList 类
存储对象数据，选择的容器，只有对象数组。而数组的长度是固定的，无法适应数据变化的需求。为了解决这个问题，Java提供了另一个容器 `java.util.ArrayList `集合类,让我们可以更便捷的存储和操作对象数据。
### 4. 1 定义 
`java.util.ArrayList` 是大小可变的数组的实现，存储在内的数据称为元素。此类提供一些方法来操作内部存储的元素。 ArrayList 中可不断添加元素，其大小也自动增长。
* ### 使用
#### 查看类
* `java.util.ArrayList <E> `：该类需要 import导入使后使用。 \
`<E>` ，表示一种指定的数据类型，叫做泛型。 `E` ，取自Element（元素）的首字母。在出现 E 的地方，使用一种引用数据类型将其替换即可，表示将存储哪种引用类型的元素。
>代码如下： 

`ArrayList<String>，ArrayList<Student>`
#### 查看构造方法 
* `public ArrayList()` ：构造一个内容为空的集合。 
> 基本格式: 

`ArrayList<String> list = new ArrayList<String>(); `

在JDK 7后,右侧泛型的尖括号之内可以留空，但是<>仍然要写。
> 简化格式：

`ArrayList<String> list = new ArrayList<>(); `

#### 查看成员方法 
* `public boolean add(E e) ` ： 将指定的元素添加到此集合的尾部。 \
参数 `E e` ，在构造ArrayList对象时， `<E>` 指定了什么数据类型，那么 `add(E e) `方法中，只能添加什么数据类型的对象。

#### 4. 2 常用方法和遍历
对于元素的操作,基本体现在——增、删、查。常用的方法有： 
* `public boolean add(E e) `：将指定的元素添加到此集合的尾部。 
* `public E remove(int index) `：移除此集合中指定位置上的元素。返回被删除的元素。 
* `public E get(int index)` ：返回此集合中指定位置上的元素。返回获取的元素。 
* `public int size()` ：返回此集合中的元素数。遍历集合时，可以控制索引范围，防止越界。
* `public void add(int index, E element) `:将指定的元素插入此列表中的指定位置。
#### 4. 3 存储基本数据类型
ArrayList对象不能存储基本类型，只能存储引用类型的数据。类似 <int> 不能写，但是存储基本数据类型对应的包装类型是可以的。所以，想要存储基本类型数据， `<>` 中的数据类型，必须转换后才能编写。
> 转换写法如下：

|基本类型|基本类型包装类|
|:---|:---:|
|byte|Byte|
|short|Short|
|int|Integer(特殊)|
|long|Long|
|float|Float|
|double|Double|
|char|Character(特殊)|
|boolean|Boolean|


> ps : 仅供本人学习使用
