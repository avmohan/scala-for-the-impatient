Scala for the Impatient
=======================

Lesson 1 
---------
var: mutable, val: immutable  
Use val whenever you can

Semicolons are optional. Convention is to avoid them.

Type is inferred, but it's still strongly and statically typed.

Scala has no primitives, everything is an object

RichInt  
StringOps  

Infix notation  
`1.to(10)`  vs `1 to 10`

No `++` or `--` operators in Scala, instead use `+= 1` and `-= 1` (similar to python here)

Functions vs methods -> Methods operate on objects, functions don't.   
`1 + 2 = 3`     // + is a method on Int  
`sqrt(2)`       // sqrt is a function (in scala.math._)


Convention: if a method is an accessor, don't put parenthesis while calling it. Else use parenthesis.  
`"Hello".length` instead of `"Hello".length()`, as length is not a mutator.


If a type has an `apply()` method, it can be called directly (similar to `__call__` in python)  
`"Hello"(4)` // 'o'  
`"Hello".apply(4)` // 'o'  


Imports can be anywhere in the file. Need not be at the top.


Lesson 2
--------

If condition in  scala is an expression, not a statement as in case of java. So it has a value.  
`if (x > 0) 1 else -1` is similar to `x > 0 ? 1 : -1` in Java (it even works for multiple statements)  

What's the type of an if condition? It will be the common supertype of the branches.


Unit in scala is analogous to void, but unlike void which has no values, there is exactly 1 unit value `()`  [Hence the reason it's called Unit ]  


Blocks are also expressions. The value of a block is the value of the last expression in the block  
In the below block, dx and dy have a very limited scope => Blocks are useful for having these kinds of temporary variables to improve readability
```scala
val distance = {
    val dx = x - x0
    val dy = y - y0
    sqrt(dx*dx + dy*dy)
}
```


Scala does not have a simple for loop. It has a foreach loop
`for (i <- 1 to n)`
Here `1 to n` is a collection (Range). It can work with other collections too, like strings.
`for (ch <- "Hello")`

Check out multiple generators & Guards
(Kind of comparable to list comprehensions in python)

Collect the results into a collection using yield

`for (i <- 1 to 3; j <- 1 to 3 if i!=j) yield (i, j)` will yield a collection of all tuples `(i, j)` 
where 1 <= i < 3 and i != j
i.e. ((1,2), (1,3), (2,1), (2,3), (3,1), (3,2))
Similar to python's `[(i, j) for i in range(1, 4) for j in range(1, 4) if i!=j]`

For comprehensions are just syntactic sugar. Scala compiler internally desugars them 
using foreach, map, flatmap, filter, withFilter calls. For comprehensions can be more readable in 
certain cases.


In function definition, type should be given for the parameters, but the return type 
can be inferred, except in the case of recursive functions in which case the return 
type needs to be supplied Still, in many cases, it can be more readable to provide an
explicit return type.  
Without an equals, it's a Unit return type. This is not considered good style, better to 
be explicit by explicitly specifying Unit return type and
using the equals syntax itself.


Named arguments => Useful for greater clarity, for changing the order, and to avoid
supplying other default arguments.  
Default arguments => Let's us omit argument values.

Varargs - Indicated with * after type, in the argument list. Inside the function body, it is 
received as a Seq  
To call with Seq, we need a special syntax `: _*`. This is a difference from java's vararg behaviour.   
eg: To call a function `sum(x: Int*) => Int`, if we call with `sum(1 to 10)`, 
it will be an error. We need to call as `sum(1 to 10 : _*)`


In java, we can check some conditions and reassign to method parameters to handle a particular flag. Method parameters are immutable in Scala, so one way to handle this in Scala would be to recurse.
eg:
Below java code:
```java
int distinctChars(String str, boolean ignoreCase) {
 if (ignoreCase) {
   str = str.toLower() 
 }
 // ..
 // ...
 
}
```
is kind of equivalent to the below scala code:
```scala
def distinctCharsIgnoreCase(str : String, ignoreCase : Boolean) : Int {
    if(ignoreCase) {
      distinctCharsIgnoreCase(str.lower, false)
    }
    //..
    // ..
}
```


Lesson 3 - Arrays, Maps & Tuples
--------------------------------
### Array
`val nums = new Array[Int](10)` is equivalent to java's `final int[] nums = new int[10];`  
Ten integers, all initialized to zero.  
Not the same as `val nums = Array[int](0)` which is just `final int[] nums = new int[]{0};`


Arrays can also be created with initial values and without giving the size explicitly
`val a = Array("Hello", "World")` - same as - `final String[] a = new String[]{"Hello", "World"};`  
The type is inferred.

Use parentheses to access the elements, instead of square brackets
`a(0) = "Goodbye"`

Arrays are mutable in that the elements within can be changed but changing the size of the array is not possible without a full reassignment. 
Arrays can be appended to by +:, but this is not a true append as it creates a new array and does a full copy of the current array. The current array remains the same.

`for (element <- a)` traverses the array elements  
`for (i <- 0 until array.length)` traverses array indices  
`for (i <- array.indices)` is a cleaner way for the same



### ArrayBuffer
Equivalent to ArrayList in java
```
import scala.collection.mutable.ArrayBuffer
val b = new ArrayBuffer[Int]
```

Use += to append an element (add at the end), ++= to concatenate a sequence at the end.
Appending an element takes amortized constant time, and worst case linear time.
Prepending an element takes linear time

x.remove(i) to remove an element at index i

Also supports methods like sorted, reverse, sum etc


### Map
Map is immutable
scala.collection.mutable.Map is the mutable version  
`val scores = Map("Alice" -> 1, "Bob" -> 2)`

for comprehension on a map  
`for ((k, v) <- scores) println(k + " has score " + v)`

for/yield to get a new Map  
`for ((k, v) <- scores) yield(v, k)` reverses the map's key & values


### Tuple
Tuple aggregates values of possibly different types  
`val twins = ("Fred", "George", 1)`

