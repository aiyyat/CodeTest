Advanced MultiThreading
        
The Need for Volatility:
Some JVMs try to perform Optimization by virtue of Variable Caching it when it comes to threads, that tend to behave as if no other thread would be expected to be modifying the variable. These Thread Cached Variables are local copies to threads; Hence a change from outside the thread is not visible to the thread itself. The following program without volatile could potentially run into an infinite loop with some JVMs.
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example01.java
Problems that can arise out of variables shared between multiple threads:
Messed up Amount: Imagine two threads trying to deposit money into the same accounts. The following example depicts the problem of vanishing money when done simulataneously by multiple threads.
Incrementing the value of an Integer using the ++ operator for example has been among the major leaps in C++ from C, which was adopted in Java like most other C++ constructs. As we know the operator does its job in not 1 but 3 separate steps. Hence let’s say two threads simultaneously reads the value of integer variable amount(initially 0).Let us now assume within both the threads the value of the variable is read simultaneously. Now thread A increments the value and sets it back to the variable making it sound 1. Thread B springs into action incrementing the value 0 to 1 and setting it back to the amount. In effect, two increment operations only manages to set the value of the amount to 1 instead of 2.
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example02.java
Synchronized Blocks are here to rescue: 
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example03.java
Using Atomic Variables: 
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example04.java
Note: Synchronized methods are as good as Synchronized blocks around the ‘this’ object as far as the whole method is concerned. The block of code protected from this simultaneous multi thread access is called a Critical Section.
ArrayList, we have ever known that unlike Vectors are not Synchronized.
Now for some fiddling with ArrayList with multiple threads – exactly what they are not designed to do:
The Case of Missing Elements and ArrayIndexOutOfBound.
The aforementioned problem do occur with ArrayList, for, they are not designed to be thread safe. Not Quite astonishing. But running the program multiple times seem to reveal an occational ArrayIndexOutOfBound.

Exception in thread “Thread-0” java.lang.ArrayIndexOutOfBoundsException: 9369
at java.util.ArrayList.add(ArrayList.java:441) at
com.technicalyorker.threads.Example5.addElement(Example5.java:10) at
com.technicalyorker.threads.Example5.access$0(Example5.java:9) at
com.technicalyorker.threads.Example5$1.run(Example5.java:20) 13587

Beautiful!
This is because of how ArrayList Datastructure works. ArrayList internally uses Arrays for its implmentation. Fortunately, Arrays needs a well defined length which gave birth to the class of Collection implementations in Java. For an Array of size N it gets more than doubled if the insertions exceeds N. This is done by creating a new Array and copying its contents across. Lets just call this process Array Resizing. Similarly its resized when the size reaches N/4. Operations done on Array during such activities lead to the Exception.
Solution
Use CopyOnWriteArrayList:
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example05.java
Vector:
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example06.java
Synchronized Blocks:
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example07.java
Using Collections Utility Class:
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example08.java

Now, Take a look at this
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example10.java
Now even though the Integer variable is used to lock it behaves wierd. Cause is all that mentioned in the above on the ‘++’ operator.
Lock Variable:Its better to use a lock variable separate from the Integer itself. This is a better practice in all cases since you never know if java has done something to optimize your original variable to something else while incrementing for example in case of an integer etc. Java only ensures that the end result of the variable is correct. Hence its always safer to use a variable explicitely for locking.
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example11.java
Wait & Notify:
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example12.java
Producer Consumer Model:
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example13.java
Blocking Queue:
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example14.java

Dead Lock:
As we all know creating a dead lock is not very difficult.😉 Just be careful to not do exact copy paste of code block that does synchronization.
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example15.java
To ensure a dead lock never occurs just remember to keep the attainment of the order of the locks the same.
synchronized (a) {
    synchronized (b) {
        System.out.println("hi there..");
    }
}

Reentrant Lock:
Reentrant locks are quite similar to having Synchronized blocks, but not without differences. Reentrant locks come with a flag of fairness. Setting it to true lets the longest waiting thread get the lock. 
A locked Reentrant lock must have its unlock called to release the lock. The condition interface allow a reentrant lock release and acquire the locks.
Reentrant locks also allows to check if the lock is on with the isLocked() method.
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example17.java

Semaphore:
The example is that of a connection pool of a fixed size. Once all the connections from the pool are acquired it will not try to release any further connections, or the threads will have to wait until any of the acquired connections are gracefully released.  
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example18.java

Count Down Latch:
https://github.com/technicalyorker/misc/blob/master/Threads/src/com/technicalyorker/threads/Example16.java

