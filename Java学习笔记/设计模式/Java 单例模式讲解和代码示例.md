# Java单例模式介绍和代码示例
别称：单件模式、Singleton

## 意图
**单例模式** 是一种创建型设计模式，让你能保证一个类只有一个实例，并提供在一个访问该实例的全局访问点。

## 问题
单例模式违反了单一职责原则：

1. **单例模式保证一个类只有一个实例** 。 为什么会有人想要控制一个类所有的实例数量呢？ 最常见的原因是控制某些共享资源（例如数据库或文件）的访问权限。    

    单例模式的运作方式是这样的：如果你创建了一个对象，同时过一会儿后你决定再创建一个新对象，此时你会获得之前已创建的对象，而不是一个新对象。

    注意，普通构造函数无法实现上述行为，因为构造函数的设计决定了它必须总是返回一个新对象。

2. **单例模式为该实例提供一个全局访问节点** 。 还记得你（好吧，其实是我自己）用过的那些存储重要对象的全局变量吗？它们在使用上十分方便，但同时也非常不安全，因为任何代码都有可能覆盖掉那些变量的内容，从而引发程序崩溃。

    和全局变量一样，单例模式也允许在程序的任何地方访问特定对象。但是它可以保护该实例不被其他代码覆盖。

    还有一点：你不会希望解决同一个问题的代码分散在程序各处的。因此更好的方式是将其放在同一个类中，特别是当其他代码已经依赖这个类时更应该如此。

## 解决方案
所有单例的实现都包含以下两个相同的步骤：

- 将默认构造函数设为私有，防止其他对象使用单例类的 `new` 运算符。
- 新建一个静态构建方法作为构造函数。该函数会 “偷偷” 调用私有构造函数来创建对象，并将其保存在一个静态成员变量中。此后所有对于该函数的调用都将返回这一缓存对象。

如果你的代码能够访问单例类，那它就能调用单例类的静态方法。无论何时调用该方法，它总是会返回相同的对象。

##  真实世界类比
政府是单例模式的一个很好的示例。一个国家只有一个官方政府。不管组成政府的每个人的身份是什么，“某政府” 这一称谓总是鉴别那些掌权者的全局访问节点。

## 单例模式适合应用场景
问：如果程序中的某个类对于所有客户端只有一个可用的实例，可以使用单例模式。

答：单例模式禁止通过除特殊构建方法以外的任何方式来创建自身类的对象。该方法可以创建一个新对象，但如果该对象已经被创建，则返回已有的对象。

问：如果你需要更加严格地控制全局变量，可以使用单例模式。

答：单例模式与全局变量不同，它保证类只存在一个实例。除了单例类自己以外，无法通过任何方式替换缓存的实例。

> 请注意，你可以随时调整限制并设定生成单例实例的数量，只需修改 获取实例方法，即 getInstance 中的代码即可实现。

## 实现方式
1. 在类中添加一个私有静态成员变量用于保存单例实例。

2. 声明一个公有静态构建方法用于获取单例实例。

3. 在静态方法中实现"延迟初始化"。该方法会在首次被调用时创建一个新对象，并将其存储在静态成员变量中。此后该方法每次被调用时都返回该实例。

4. 将类的构造函数设为私有。类的静态方法仍能调用构造函数，但是其他对象不能调用。

5. 检查客户端代码，将对单例的构造函数的调用替换为对其静态构建方法的调用。

## 单例模式优缺点
优点：
- 你可以保证一个类只有一个实例。
- 你获得了一个指向该实例的全局访问节点。
- 仅在首次请求单例对象时对其进行初始化。

缺点：
- 违反了_单一职责原则_。该模式同时解决了两个问题。
- 单例模式可能掩盖不良设计，比如程序各组件之间相互了解过多等。
- 该模式在多线程环境下需要进行特殊处理，避免多个线程多次创建单例对象。
- 单例的客户端代码单元测试可能会比较困难，因为许多测试框架以基于继承的方式创建模拟对象。由于单例类的构造函数是私有的，而且绝大部分语言无法重写静态方法，所以你需要想出仔细考虑模拟单例的方法。要么干脆不编写测试代码，或者不使用单例模式。

## 与其他模式的关系
- 外观模式类通常可以转换为单例模式类，因为在大部分情况下一个外观对象就足够了。
- 如果你能将对象的所有共享状态简化为一个享元对象，那么享元模式就和单例类似了。但这两个模式有两个根本性的不同。
- 只会有一个单例实体，但是享元类可以有多个实体，各实体的内在状态也可以不同。
单例对象可以是可变的。享元对象是不可变的。
抽象工厂模式、生成器模式和原型模式都可以用单例来实现。

## 代码实例
### 1. 饿汉式单例

立即初始化，在加载类时会创建Singleton类的实例，这是创建Singleton类的最简单方法，但是它存在一个缺点，即使客户端应用程序可能不使用它也会创建该实例。

这是静态初始化单例类的实现。
```java
package top.bluer.singleton;

public class EagerInitializedSingleton {
    
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();
    
    // 私有构造函数，以避免客户端应用程序使用构造函数
    private EagerInitializedSingleton(){}

    public static EagerInitializedSingleton getInstance(){
        return instance;
    }
}
```
如果您的单例类没有使用很多资源，则可以使用这种方法。但是在大多数情况下，都是为文件系统，数据库连接等资源创建Singleton类的 `getInstance()`。除非客户端调用该方法，否则应避免实例化。另外，此方法不提供任何用于异常处理的选项。

### 2. 静态代码块初始化

静态代码块初始化实现与饿汉式单例的立即初始化类似，不同的是，类的实例是在提供了异常处理选项的静态块中创建的。
~~~java
package top.bluer.singleton;

public class StaticBlockSingleton {

    private static StaticBlockSingleton instance;
    
    private StaticBlockSingleton(){}
    
    // 静态块初始化，用于异常处理
    static{
        try{
            instance = new StaticBlockSingleton();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }
    
    public static StaticBlockSingleton getInstance(){
        return instance;
    }
}
~~~
立即初始化和静态块初始化都在创建实例之前就创建了实例，这不是最佳实践。

### 3. 懒汉式单例

实现单例模式的懒汉初始化方法在全局访问方法中创建实例。这是使用这种方法创建Singleton类的示例代码。
```java
package top.bluer.singleton;

public class LazyInitializedSingleton {

    private static LazyInitializedSingleton instance;
    
    private LazyInitializedSingleton(){}
    
    public static LazyInitializedSingleton getInstance(){
        if(instance == null){
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
}
```
上面的实现在单线程环境下可以很好地工作，但是对于多线程系统，如果多个线程同时位于if条件中，则可能导致问题。它将破坏单例模式，并且两个线程都将获得单例类的不同实例。

### 4. 线程安全单例

创建线程安全的单例类的更简单方法是使全局访问方法同步，以便一次只能有一个线程执行此方法。这种方法的一般实现类似于下面的类。
```java
package top.bluer.singleton;

public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton(){}
    
    public static synchronized ThreadSafeSingleton getInstance(){
        if(instance == null){
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
```

上面的实现可以很好地工作并提供线程安全性，但是由于与同步方法相关的成本，它降低了性能，尽管我们仅对可能创建单独实例的前几个线程需要它。为了避免每次额外的开销，使用了双重检查的锁定原理。在这种方法中，在if条件中使用了同步块，并进行了附加检查，以确保仅创建一个singleton类的实例。

以下代码片段提供了双重检查的锁定实现。
```java
public static ThreadSafeSingleton getInstanceUsingDoubleLocking(){
    if(instance == null){
        synchronized (ThreadSafeSingleton.class) {
            if(instance == null){
                instance = new ThreadSafeSingleton();
            }
        }
    }
    return instance;
}
```

### 5. Bill Pugh Singleton执行（静态内部类）

在Java 5之前，Java内存模型存在很多问题，并且上述方法在某些情况下会失败，在某些情况下，太多的线程试图同时获取Singleton类的实例。因此，比尔·普格（Bill Pugh）想出了另一种方法，可以使用内部静态帮助程序类来创建Singleton类。Bill Pugh Singleton的实现是这样的；
```java
package top.bluer.singleton;

public class BillPughSingleton {

    private BillPughSingleton(){}
    
    private static class SingletonHelper{
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    
    public static BillPughSingleton getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
```
请注意，内部私有静态类包含单例类的实例。加载singleton类时，`SingletonHelper` 该类不会加载到内存中，只有当有人调用getInstance方法时，该类才会加载并创建Singleton类实例。

这是Singleton类使用最广泛的方法，因为它不需要同步。我在许多项目中都使用这种方法，而且也很容易理解和实现。

### 6. 使用反射破坏单例模式

反射可用于破坏所有上述单例实现方法。让我们用一个示例类来看看
```java
package top.bluer.singleton;

import java.lang.reflect.Constructor;

public class ReflectionSingletonTest {

    public static void main(String[] args) {
        EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton instanceTwo = null;
        try {
            Constructor[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                // 下面的代码将破坏单例模式
                constructor.setAccessible(true);
                instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());
    }

}
```
当您运行上述测试类时，您会注意到两个实例的hashCode不同，这会破坏单例模式。

### 7. 枚举式单例

为了通过反射来克服这种情况，约书亚·布洛赫（Joshua Bloch）建议使用Enum来实现Singleton设计模式，因为Java确保在Java程序中仅将一次枚举值实例化一次。由于Java枚举值可全局访问，因此单例也是如此。缺点是枚举类型有些不灵活；例如，它不允许延迟初始化。
```java
package top.bluer.singleton;

public enum EnumSingleton {

    INSTANCE;

    public static void doSomething(){
        return INSTANCE;
    }
}
```

### 8. 序列化和单例

有时在分布式系统中，我们需要在Singleton类中实现Serializable接口，以便我们可以将其状态存储在文件系统中，并在以后的某个时间点检索它。这是一个小的单例类，它也实现了Serializable接口。
```java
package top.bluer.singleton;

import java.io.Serializable;

public class SerializedSingleton implements Serializable{

    private static final long serialVersionUID = -7604766932017737115L;

    private SerializedSingleton(){}
    
    private static class SingletonHelper{
        private static final SerializedSingleton instance = new SerializedSingleton();
    }
    
    public static SerializedSingleton getInstance(){
        return SingletonHelper.instance;
    }
    
}
```
序列化单例类的问题在于，每当我们反序列化它时，它将创建该类的新实例。让我们用一个简单的程序看看。
```java
package top.bluer.singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SingletonSerializedTest {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        SerializedSingleton instanceOne = SerializedSingleton.getInstance();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                "filename.ser"));
        out.writeObject(instanceOne);
        out.close();
        
        // 从文件反序列化到对象
        ObjectInput in = new ObjectInputStream(new FileInputStream(
                "filename.ser"));
        SerializedSingleton instanceTwo = (SerializedSingleton) in.readObject();
        in.close();
        
        System.out.println("instanceOne hashCode="+instanceOne.hashCode());
        System.out.println("instanceTwo hashCode="+instanceTwo.hashCode());
        
    }

}
```
上面程序的输出是：
```java 
instanceOne hashCode=2011117821
instanceTwo hashCode=109647522
```
因此，它破坏了单例模式，以克服这种情况，我们需要做的所有工作都提供了readResolve()方法的实现。
```java
protected Object readResolve() {
    return getInstance();
}
```
此后，您将注意到两个实例的hashCode在测试程序中相同。

### 9. ThreadLocal 线程单例类
ThreadLocal 不能保证其创建的对象是全局唯一，但是能保证在单个线程中是唯一的，天生的线程安全。
```java
package top.bluer.singleton;

public class ThreadLocalSingleton {
    private ThreadLocalSingleton(){}

    private static final ThreadLocal<ThreadLocalSingleton> threadLocalSingleton = new ThreadLocal<ThreadLocalSingleton> () {
        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    public static ThreadLocalSingleton getInstance(){
        return threadLocalSingleton.get();
    }
}
```
上面的单例模式为了达到线程安全的目的，给方法上锁，以时间换空间。ThreadLocal 将所有的对象全部放在 ThreadLocalMap 中，为每个线程都提供一个对象，实际上是以 空间换时间来实现线程间隔离的。

## 单例模式小结
单例模式可以保证内存里只有一个实例，减少了内存开销；可以避免对资源的多重占用。单例模式看起来非常简单，实现起来其实也非常简单。但是在面试中却是一个高频面试题。