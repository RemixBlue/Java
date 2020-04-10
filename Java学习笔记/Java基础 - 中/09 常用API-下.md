# 常用API-下
## 1、 String类
### 1. 1 概述
* 概述 :\
`java.lang.String` 类代表字符串。Java程序中所有的字符串文字（例如 "abc" ）都可以被看作是实现此类的实例。\
类 `String` 中包括用于检查各个字符串的方法，比如用于比较字符串，搜索字符串，提取子字符串以及创建具有翻译为大写或小写的所有字符的字符串的副本。
* 特点 :
    * 字符串不变：字符串的值在创建后不能被更改。
    * 因为String对象是不可变的，所以它们可以被共享。
    * `"abc"` 等效于 `char[] data={ 'a' , 'b' , 'c' }  // String底层是靠字符数组实现的。` 。

### 1. 2 使用
* 查看类
    * `java.lang.String` ：此类不需要导入。
* 查看构造方法
    * `public String()` ：初始化新创建的 String对象，以使其表示空字符序列。 
    * `public String(char[] value)` ：通过当前参数中的字符数组来构造新的String。 
    * `public String(byte[] bytes)` ：通过使用平台的默认字符集解码当前参数中的字节数组来构造新的 String。
    * 构造举例，代码如下：
    ~~~java
    // 无参构造 
    String str = new String（）； 
    // 通过字符数组构造 
    char chars[] = {'a', 'b', 'c'}; 
    String str2 = new String(chars); 
    // 通过字节数组构造 
    byte bytes[] = { 97, 98, 99 }; 
    String str3 = new String(bytes);
    ~~~
### 1. 3 常用方法
* 三种构造方法：
    * `public String()`:创建一个空白字符串，不包含任何内容。
    * `public String(char[] array)`:根据字符数组的内容，来创建对应的字符串。
    * `public String(byte[] array)`:根据字节数组的内容，来创建对应的字符串。
* 一种直接创建：
    * `String strd = "字符串"; `
* 判断功能的方法
    * `public boolean equals (Object anObject)` ：将此字符串与指定对象进行比较。 
    * `public boolean equalsIgnoreCase (String anotherString)` ：将此字符串与指定对象进行比较，忽略大小写。
    > Object 是” 对象”的意思，也是一种引用类型。作为参数类型，表示任意对象都可以传递到方法中。
* 获取功能的方法
    * `public int length ()` ：返回此字符串的长度。 
    * `public String concat (String str) `：将指定的字符串连接到该字符串的末尾。 
    * `public char charAt (int index)` ：返回指定索引处的 char值。 
    * `public int indexOf (String str)` ：返回指定子字符串第一次出现在该字符串内的索引。 
    * `public String substring (int beginIndex)` ：返回一个子字符串，从beginIndex开始截取字符串到字符串结尾。 
    * `public String substring (int beginIndex, int endIndex)` ：返回一个子字符串，从beginIndex到 endIndex截取字符串。含beginIndex，不含endIndex。
* 转换功能的方法 
    * `public char[] toCharArray() `：将此字符串转换为新的字符数组。 
    * `public byte[] getByte()` ：获得当前字符串底层的字节数组。 
    * `public String replace(CharSequence target, CharSequence replacement)` ：将所有出现的老字符串替换成新的字符串，返回替换之后的结果新字符串。
* 分割字符串方法：
    * `public String[] split(String regex)`: 按照参数的规则，将字符串切成若干部分     
    >ps : split方法的参数其实就是一个"正则表达式"
    >注意 ：如果按照英文句点"`.`"进行切分必须写成"`\\.`"  (两个反斜杠)
> 代码说话：
~~~java
String[] stt = "Mute Remix".split(" ");
System.out.println(stt); // 地址值
System.out.println(stt[0]); // Mute 
System.out.println(stt[1]); // Remix

String[] stte = "Mute.Remix".split("\\.");
System.out.println(stte[0]); // Mute
~~~
* String 字符串的截取方法：
    * `public String substring(int index)`: 截取从参数位置一直到字符串末尾，返回新的字符串。
    * `public String substring(int begin, int end)`:截取从begin开始，一直到end结束中间的字符串。
    > ps : `[begin,end)`,包含左边，不包含右边。
    
    > 代码说话：
~~~java
System.out.println("REMIX=Remix".substring(6)); // Remix
System.out.println("Remix=ovo=REMIX".substring(6,9)); // ovo
~~~
--- 
## 2、 static关键字
### 2. 1 概述 
关于 `static` 关键字的使用，它可以用来修饰的成员变量和成员方法，被修饰的成员是属于类的，而不是单单是属于某个对象的。也就是说，既然属于类，就可以不靠创建对象来调用了。
### 2. 2 定义和使用格式 
* 类变量 \
当 `static` 修饰成员变量时，该变量称为类变量。该类的每个对象都共享同一个类变量的值。任何对象都可以更改该类变量的值，但也可以在不创建该类的对象的情况下对类变量进行操作。 
    * 类变量：使用 `static`关键字修饰的成员变量。
> 定义格式：

`static 数据类型 变量名; `
* 静态方法 
当 `static `修饰成员方法时，该方法称为类方法 。静态方法在声明中有 `static` ，建议使用类名来调用，而不需要 创建类的对象。调用方式非常简单。 
    * 类方法：使用 `static`关键字修饰的成员方法，习惯称为静态方法。
> 定义格式：
~~~java
修饰符 static 返回值类型 方法名 (参数列表){ 
    // 执行语句 
}
~~~
> 静态方法调用的注意事项：
* 静态方法可以直接访问类变量和静态方法。 
* 静态方法不能直接访问普通成员变量或成员方法。反之，成员方法可以直接访问类变量或静态方法。 
* 静态方法中，不能使用`this`关键字。
> ps ：静态方法只能访问静态成员。

* 调用格式 : 被 `static`修饰的成员可以并且建议通过类名直接访问。
> 格式：
~~~java
// 访问类变量 
类名.类变量名； 
// 调用静态方法 
类名.静态方法名(参数)；
~~~
### 2. 3 静态原理 
`static` 修饰的内容： 
* 是随着类的加载而加载的，且只加载一次。 
* 存储于一块固定的内存区域（静态区），所以，可以直接被类名调用。 
* 它优先于对象存在，所以，可以被所有对象共享。
### 2. 4 静态代码块 
* 静态代码块：定义在成员位置，使用`static`修饰的代码块`{ }`。 
    * 位置：类中方法外。 
    * 执行：随着类的加载而执行且执行一次，优先于`main`方法和构造方法的执行。 
> 格式：
~~~java
public class 类名{
    static {
        // 执行语句 
    } 
}
~~~
>作用：给类变量进行初始化赋值。

~~~java
public class Mute{ 
    public static int number; 
    public static ArrayList<String> list; 
    
    static { 
        // 给类变量赋值 
        number = 32; 
        list = new ArrayList<String>(); 
        // 添加元素到集合中 
        list.add("啊嘞"); 
        list.add("Remix"); 
    } 
}
~~~

> `static` 关键字，可以修饰变量、方法和代码块。在使用的过程中，其主要目的还是想在不创建对象的情况 下，去调用方法。

--- 
 ## 3、Arrays类 
 ### 3. 1 概述 
 `java.util.Arrays` 一个与数组相关的工具类，里面提供了大量的静态方法，用来实现数组的操作，比如排序和搜索等。其所有方法均为静态方法，调用起来非常简单。 
 ### 3. 2 操作数组的方法 
 * `public static String toString(数组)` ：将参数数组变成字符串（按照默认格式：【元素1，元素2，元素3.。。】）
 * `public staric void sort(数组)`：按照默认升序（从大到小）对数组的元素进行排序。

 >ps:
 * 1、如果是数值，sort默认按照升序从小到大
 * 2、如果是字符串。sort默认按照字母升序
 * 3、如果是自定义类型，那么这个自定义的类需要有`Comparable`或者`Comparator`接口的支持。

 --- 
 ## 4、Math类 
 ### 4. 1 概述 
 `java.lang.Math` 类包含用于执行基本数学运算的方法，如初等指数、对数、平方根和三角函数。类似这样的工具类，其所有方法均为静态方法，并且不会创建对象，调用起来非常简单。 
 ### 4. 2 基本运算的方法 
 * `public static double abs(double num)`: 获取绝对值。
 * `public static double ceil(double num)`: 向上取整。
 * `public static double floor(double num)`: 向下取整。
 * `public static long round(double num)`: 四舍五入。

 >Math.PI 代表近似的圆周率常量。

> ps : 仅供本人学习使用