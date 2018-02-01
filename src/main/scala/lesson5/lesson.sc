object lesson {

  // Packages
  // ========
  // Purpose of packages - for namespacing - same as java
  // Scala packages nest
  // Scala packages have nothing to do with the directory structure
  // Scala packages can have contribution from multiple source files
  // Top of file package statement

  // Extremely flexible imports:
  //    - wildcard imports:  import java.util._
  //    - import a subset :  import java.awt.{Color, Font}
  //    - aliasing        :  import java.util.{HashMap => JavaHashMap}
  //    - hide imports    :  import java.util.{HashMap =>_, _ }

  // Imports can be anywhere - inside classes, inside methods, inside any block.
  // Useful for restricting the scope of the import

  // java.lang, scala, Predef are always imported

  // TODO: Packages vs directory structure ??


  // Inheritance
  // ===========
  // class Manager extends Employee - same syntax as java
  // override keyword mandatory for overriding

  // Run time type inquiry -
  // e.isInstanceOf[Manager] // like e instanceOf Manager
  // e.asInstanceOf[Manager] // like (Manager) e
  // classOf[Manager]        // like Manager.class

  class Person(val name: String, val age: Int) {
    def this(age: Int) = this("John Doe", age)

    override def toString = s"Person(name=$name, age=$age)"
  }

  class Employee(name: String, age: Int, val salary: Double) extends Person(name, age) {
    final override def toString = s"Employee(name=$name, age=$age, salary=$salary)"
  }

  // Superclass construction is done in the extends sytax itself
  class Manager(name: String, age: Int) extends Employee(name, age, 100000) {
    // Below line is a compile error because toString was made final
    // in Employee
    // override def toString = ""
  }

  val manager = new Manager("Ram", 35)
  assert(manager.getClass == classOf[Manager]) // true

  // manager is an instance of Employee, but it's class is not Employee
  assert(manager.isInstanceOf[Employee])
  assert(!(manager.getClass == classOf[Employee]))

  // manager is also a Person
  assert(manager.isInstanceOf[Employee]) // true

  val employee = new Employee("John", 30, 45000)
  assert(employee.getClass == classOf[Employee])


  // Traits
  // ======
  // Superficially similar to Java interface, but has a ton of extras
  // A class can extend multiple traits (there is no implements keyword, use extends and with)
  // Traits can have concrete methods
  // Traits can have abstract fields
  // Traits can have concrete fields

  // Traits cannot have construction parameters - this is the only difference
  // between classes and traits

  // Mixins
  // ======
  // Use traits to "mix in" small amounts of functionality into a class
  // Can also mix directly into objects without mixing it into the class itself


  trait Logged {
    def log(msg: String): Unit = {}
  }

  trait ConsoleLogger extends Logged {
    override def log(msg: String): Unit = {
      println(msg)
    }
  }

  class SavingsAccount extends Logged {
    private var balance: Double = 0

    def withdraw(amount: Double): Unit = {
      if (amount > balance) log("Insufficient funds")
      else balance -= amount
    }
  }

  val defaultAcct = new SavingsAccount
  val consoleLoggedAcct = new SavingsAccount with ConsoleLogger

  defaultAcct.withdraw(100) // logs nothing as default implementation in Logged is noop
  consoleLoggedAcct.withdraw(100) // logs to console

  trait TimestampLogger extends Logged {
    override def log(msg: String): Unit = {
      super.log(new java.util.Date() + " " + msg)
    }
  }

  trait AlertLogger extends Logged {
    override def log(msg: String): Unit = {
      super.log("ALERT: " + msg)
    }
  }


  // Ordering of the mix-in determines the actual method to which the call gets dispatched
  // Dispatch happens in the reverse order in which it's specified
  // TODO: Understand this better

  val acct1 = new SavingsAccount with ConsoleLogger with TimestampLogger with AlertLogger
  acct1.withdraw(100) // AlertLogger     -> TimestampLogger -> ConsoleLogger
  // AlertLogger will prepend an ALERT to the Insufficient funds message
  // TimestampLogger will prepend timestamp to the msg from AlertLogger
  // ConsoleLogger will print out the msg from TimestampLogger to the console

  val acct2 = new SavingsAccount with ConsoleLogger with AlertLogger with TimestampLogger
  acct2.withdraw(100) // TimestampLogger -> AlertLogger     -> ConsoleLogger
  // TimestampLogger will prepend timestamp to the Insufficient funds message
  // AlertLogger will prepend an ALERT to the msg from TimestampLogger
  // ConsoleLogger will print out the msg from AlertLogger to the console

}






