/**
 * A very silly program to demonstrate the basics of defining your own classes.
 */

import java.util.Arrays;

/**
 * A monster.
 */
public class Monster {

    /* Variables defined at the class level

       Remember that in Java, unlike Python, we must declare variables before
       using them. There are two kinds of variables that we can declare
       outside of any method:

       (a) Instance variables.
       Every instance of the class will contain its own instance of each of
       these variables. They come into existence when the instance is
       constructed (using "new").

       (b) Class variables.
       All instances of the class share a single instance of each class
       variable. This is useful, for example, if we want the instances to
       accumulate a value together. Below, we define a class variable called
       "population", that will be incremented by one every time a constructor
       is called to create a new Monster. If this were not a class variable,
       every instance would have its own "population", each with the the
       value 1 -- not very useful!

       We indicate that a variable is class variable by using the keyword
       "static" in its declaration. Although in English the word "static"
       means unmoving or unchanging, in Java, the value of a class variable
       can certainly change.
    */

    // === Class Variables ===

    // The number of Monsters created so far.
    private static int population = 0;

    // === Instance Variables ===

    // The size of this Monster.
    private int size;
    // The name of this Monster.
    private String name;
    // The contents of this Monster's belly.
    private Monster[] belly;
    // The number of items in this Monster's belly.
    private int fullness;

    // === Representation Invariants ===

    /* Representation Invariants

       It is important to record any constraints on the values of the instance
       variables, including relationships between them. These constraints
       impact each method in two ways:

       (1) At the beginning of the method body, we can assume that these
       constraints hold. This can help us accomplish what the method is to do.

       (2) At the end of the method body, we must ensure that the constraints
       are still true. This guides the development of the method body -- it
       tells us some of what we have to accomplish.

       For example, consider the "eat" method below. Suppose our
       representation invariants said that the belly array must be sorted
       according to Monster size. On the one hand, the eat method would
       have to make sure that the belly array is sorted by the end of the
       method. But on the other hand, it would be able to assume the array
       was sorted at the beginning. This means that the method wouldn't
       have to perform any sorting -- it could simply insert the new item
       into the right spot in the already sorted array.
    */

    // fullness < belly.len
    // All monsters in this monster's belly are at the front of the array:
    //    for 0 <= i < fullness, belly[i] is not null
    //    for fullness <= i < belly.len, belly[i] is null
    // A monster can only have in its belly other monsters that themselves
    // have empty bellies:
    //    for 0 <= i < fullness, belly[i].fullness == 0

    // === Constructors ===

    /* Constructors
    A constructor has the same name as the class and no return type
    (not even void). It is called automatically when an instance of the
    class is created, that is, when "new" is used.

    A class can have multiple constructors, as long as their signatures are
    different. This is useful when we want client code to be able to
    construct instances in different ways, providing different sorts or
    amounts of initial information. For example, here we offer two ways
    to construct a Monster: client code can either provide the Monster's
    name, size, and bellyCapacity, or not provide any of them, in which
    case, defaults will be used. Java knows which constructor we are calling
    by the number and type of the arguments we provide.
    */

    /**
     * Creates a new Monster.
     *
     * @param name          the name of this Monster.
     * @param size          the size of this Monster.
     * @param bellyCapacity the number of Monsters this Monster can hold in its
     *                      belly.
     */
    public Monster(String name, int size, int bellyCapacity) {
        /* Using "this" to access an instance variable

        "this" is like "self" in Python. The value of "this" is the address
        of the object whose method has been called.

        Below, to refer to the "size" instance variable, we *had to* prefix
        its name with "this"; otherwise Java would think we were referring
        to the more locally defined "size" -- the parameter to this method.
        But we could have said simply "fullness" and Java would have found
        the instance variable "fullness", as desired. It's good practise,
        however, to use the "this" prefix anyway. That way, if we later
        add a parameter called fullness, the code will still work.
        */

        this.size = size;
        this.name = name;
        this.belly = new Monster[bellyCapacity];
        this.fullness = 0;
        this.population += 1;
    }

    /**
     * Creates a new Monster with a default name, size, and belly capacity.
     */
    public Monster() {
        /* Using this to call another constructor

        We need to set the instance variables and update the static
        population just as in the other constructor, but with default values.
        We could do that like so:
        this.size = 10;
        this.name = "Monster" + String.valueOf(population);
        this.belly = new Monster[3];
        this.fullness = 0;
        this.population += 1;
        But the other constructor already knows how to do all of this,
        if we provide the necessary arguments. It's better style to call
        that constructor to do the work. That way, if there ever
        is any change made to the code, it only has to happen in one
        place. Any time we duplicate code, we are at risk of having some
        future update occur in only one place, leaving the code
        inconsistent.

        To call another constructor, we use "this" as shown below. Such a
        call is termed an "explicit constructor invocation". If we have one,
        it must occur on the very first line of the method.
        */

        this("Monster" + population, 10, 3);
    }

    /* No-arg constructors

    If you don't define any constructors, the compiler supplies one with
    no parameters and no body. (We call this a "no-arg" constructor.)
    When an instance of your class is constructed using "new" with
    no arguments, this no-arg constructor is called. Of course it does
    nothing, since it has no body, but it means that the "new" statement
    compiles and runs.

    If you define any constructor for a class, the compiler will no longer
    supply the default no-arg constructor. If you don't define one either,
    then a use of "new" with no arguments will fail to compile. In our code,
    since we have a constructor with arguments, if we didn't also provide
    a no-arg constructor, then this line would not compile:
    Monster m4 = new Monster();
    */

    // === Regular methods ===

    /* Defining methods

       A method must have a return type declared. (The "size" method below
       returns an int.)  If nothing is returned, we specify "void" instead of
       giving a type.

       "return" statements look the same as in Python, except for the
       semi-colon. Of course, a void method cannot have one, since it has
       declared that it doesn't return a value.

       Unlike Python, if the end of the method is reached without executing
       a return statement, nothing is returned.
    */

    /**
     * Reports the size of this Monster.
     *
     * @return the size of this Monster.
     */
    public int size() {
        return this.size;
    }

    /*  Overloading

       The method name "grow" is said to be "overloaded": it can refer to
       either of the two methods defined below. (Our constructor above was also
       overloaded.)  Java can tell which method we mean by looking at the
       number and typ of arguments provided.

       There are multiple possible uses for overloading. Here we have used it
       to provide the option of using default values for arguments. In some
       languages, this can be accomplished directly in the signature of a
       method. For example, in Python we could say
              def grow(factor = 2):
                 self.size = self.size * factor
       We could then call grow with no arguments and, by default, factor would
       have the value 2. Java does not provide this feature.

       Note that although the word "overloaded" has a negative connotation in
       regular English, in Java, overloading is considered good style.
    */

    /**
     * Grows this Monster.
     *
     * @param factor The factor by which this Monster is to grow.
     */
    public void grow(int factor) {
        this.size = this.size * factor;
    }

    /**
     * Grows this Monster by a default factor.
     */
    public void grow() {
        /*
           Again, we call the other "grow" method to do the work. This method
           would be one line long either way, but it is still smart to not
           repeat the code here.
        */

        grow(2);
    }

    /**
     * Puts Monster m in this Monster's belly, as long as this Monster's belly
     * has room and m's belly is empty.
     *
     * @param m the Monster to be eaten.
     * @return true iff this Monster successfully ate m.
     */
    public boolean eat(Monster m) {
        if (this.fullness < this.belly.length - 1 && m.fullness == 0) {
            this.belly[fullness] = m;
            this.fullness += 1;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Digests the contents of this Monster's belly. Its belly becomes
     * empty, and the Monster burps.
     *
     * @return a "burp" String, with the number of u letters equal to the
     * number of Monsters in this Monster's belly at the time of
     * digesting, or "cough" if the Monster's belly is already empty.
     */
    public String digest() {
        if (this.fullness == 0) {
            return "cough";
        } else {
            String answer = burp(this.fullness);
            this.fullness = 0;
            return answer;
        }
    }

    /* Accessibility modifiers

       All the methods so far have had the keyword "public". This means that
       they are "visible" (can be accessed from) all classes, everywhere.
       These methods are part of the API for this class.

       Sometimes we have methods that we don't want to make part of the API.
       For instance, method "burp" is just a helper for method "digest".
       We can prevent client code from calling it directly be declaring it
       to be "private". A private method can only be called by other methods
       in the same class. Even subclasses of this class would not be able
       to access it.

       There are, in fact, 4 levels of accessibility that one can use.
       These allow for quite a lot of subtlety in how you control access.
       You'll learn more about these in class. This page is a great
       reference:
       https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html

       You can control the accessibility of instance and class variables also.
       We made ours private. The philosophy in Java is to make everything
       private (both variables and methods) unless you have a good reason not
       to. Methods may legitimately be part of the API of a class, in which
       case you have a good reason not to make them public. But variables
       rarely belong in the API. Doing so ties you down to your current
       implementation. If you want client code to be able to know a value,
       rather than making the variable public, define a public method that
       will return the value. And if you want client code to also be able
       to *change* the value, provide a public method that will do that.
       These are called "getter" and "setter" methods, respectively.

       It may seem pointless to have to define getters and setters, but
       by doing so, we are free to change the implementation of the class in
       any way we want, as long as we keep the API the same. Suppose we decided
       to keep a Queue of all the Monsters and to get rid of the "population"
       instance variable -- we can call the Queue's size method to find out
       the population. No client code would have to change at all,
       because the population method can still return the number of Monsters --
       it would just do it in a new way (by calling the Queue's size method).
       Contrast this to the scenario if we had simply made the static population
       count public. Every piece of client code that touched that variable
       would have to be rewritten.
     */

    /**
     * Returns the string "burp", but with the "u" repeated n times.
     *
     * @param n number of times to repeat the "u".
     * @return a "burp" string.
     */
    private String burp(int n) {
        StringBuilder answer = new StringBuilder("b");
        for (int i = 0; i < n; i++) {
            answer.append("u");
            this.belly[i] = null;
        }
        answer.append("rp");
        return new String(answer);
    }

    /* toString

       Java's toString is analogous to Python's __str__.

       The toString method is called implicitly if we print an object.

       All classes are descendants of class Object, and if they don't
       define their own toString or inherit one from elsewhere, they
       will inherit a toString method from class Object. It simply
       returns a unique identifier for the object whose toString was called.
     */

    /* @Override annotation

       Our Monster class inherits a toString method from class Object.
       When we define our own toString, we are overriding the inherited
       one. The @Override annotation tells the reader, and Java, that
       this is what we intend to be doing.

       It is very worthwhile to use this annotation. If we accidentally
       mistype the method name, we will fail to actually override the
       inherited method. Without the annotation, the code would compile
       and run, but its behaviour would be confusing! With the annotation,
       the code won't even compile.
     */

    /**
     * Returns a string representation of the object, including this Monster's
     * name, size, and the contents of its belly.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder(this.name);
        answer.append(" of size ").append(this.size).append(": [");
        for (int i = 0; i < this.fullness; i++) {
            // Below, this.belly[i] is an instance of Monster. Appending
            // it to our StringBuilder causes its toString method to be
            // called. So this is a second way to call toString implicitly.
            // Notice that this second call is not recursive since the representation
            // invariant specifies that the fullness of each monster in the belly must be 0, so
            // when the second toString call is made, the for loop never iterates and so
            // it immediately terminates.
            answer.append(" ").append(this.belly[i]);
        }
        answer.append("]");
        return answer.toString();
    }

    /* equals

       Java's equals is analogous to Python's __eq__.

       The equals method is NOT called implicitly when comparing objects
       using "==". (Recall that, in Java, "==" means identity equality.)
       If we want to use the equals method, we must call it by name.

       All classes are descendants of class Object, and if they don't
       define their own equals or inherit one from elsewhere, they
       will inherit an equals method from class Object. It simply
       checks identity equality. If we prefer to define equals differently,
       we can override it.

       The designer of a class gets to decide what has to be true in order
       for two instances to be considered "equals". We have chosen that
       they must have the same name and size, and equivalent belly contents.
       This sounds trivial to implement, but there are a number of details
       to be handled (see below).

       There is more to say about the equals method. Any implementation of
       it must obey these properties:
       (1) Symmetry: For non-null references a and b,
                     a.equals(b) if and only if b.equals(a)
       (2) Reflexivity:  a.equals(a)
       (3) Transitivity: If a.equals(b) and b.equals(c), then a.equals(c)

       In addition, whenever we override the equals method, we should also
       override another inherited method called "hashCode". The hashCode
       of an object is an integer value that obeys this property:
           If two objects are equal (according to the equals method),
           they have the same hashCode.
       If is not an if-and-only-if: it's fine for two objects with the same
       hashCode *not* to be equal.

       Why do objects have a hashCode and why must it satisfy this property?
       Because there are some important classes such as HashMap that use the
       hashCode of an object to decide where to store it. This allows them to
       efficiently retrieve the object later by just comparing hashCodes and
       thus avoiding costly calls to the equals method. The HashMap class does
       all this using a data structure called a "hash table". Hash tables have
       remarkable properties and are thus very important in computer science.

       Here, we can safely use the hash code of the Monster's name as the name
       variable is not changed once initially set in the constructor. In
       general, it is important to carefully choose a good hashCode to ensure
       that hash-based structures like HashMap work correctly and  efficiently.
       You will learn about hash tables and their efficiency in csc263.
     */

    /**
     * Returns true iff this Monster is equivalent to obj, meaning that
     * they have the same name and size, and equivalent belly contents.
     *
     * @param obj the Object to be compared to.
     * @return true iff this Monster is equivalent to obj.
     */
    @Override
    public boolean equals(Object obj) {
        // This check allows a ver efficient answer in the case where the
        // we more than equivalence, we have identity equality.
        // That is, we are comparing the very same object to itself.
        if (this == obj)
            return true;
        // This check avoids null references later on.
        if (obj == null)
            return false;
        // If the two objects aren't even instances of the same class, they
        // certainly aren't equivalent.
        if (getClass() != obj.getClass())
            return false;
        // We are comparing two Monster objects. We must cast obj from its
        // declared type (Object) to Monster so that Java will know it has
        // Monster-specific attributes, like belly.
        Monster other = (Monster) obj;
        // Now we can check the attributes for equivalence.
        // We can't check name equality until we are sure we are comparing
        // two non-null Strings.
        if (this.name == null) {
            return other.name == null;
        } else if (!this.name.equals(other.name)) {
            return false;
        } else if (this.size != other.size) {
            return false;
        } else if (!Arrays.equals(this.belly, other.belly)) {
            // Arrays.equals is true iff the two arrays have the same length,
            // and each pair of their elements at corresponding positions
            // are equal.
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /* Class methods

       Like class variables, we can have class methods. Again, we use the
       keyword "static" to declare that a method is a class method, as in
       method "population" below.

       Since a class method is associated with the class not the instances,
       when we call it, we prefix it with the class name. For example,
       we can say:
              System.out.println("Monster population: " + Monster.population());

       We could have defined method "population" to be an instance method
       instead by omitting the keyword "static". The code would still work;
       there is nothing wrong with an instance method accessing a class
       variable. In fact our no-arg constructor does so. But if "population"
       were an instance method, we would have to use an instance to access
       the method. For example, we could do this:
              m1 = new Monster("Grok", 21, 3);
              System.out.println("Monster population: " + m1.population());
       This would certainly work, but it feels a bit odd to talk about
       "m1.population()".

       Although an instance method can reference a class variable (or call
       a class method), the opposite is not true. A class method cannot
       access an instance variable or call an instance method directly.
       So in class method "population", the following won't compile:
               return size;
       And a class method can't use this to get at instance variables or
       methods either. There is no "this" when you are in a class method!
       So the following won't work:
               return this.size;
       The only way for a class method to access an instance variable or call
       an instance method is if a reference to an object is passed to the
       method. Through that reference, the instance variables and instance
       methods of the object are accessible.
    */

    /**
     * Reports the population of Monsters created so far.
     *
     * @return the number of Monsters that have been constructed.
     */
    public static int population() {
        /* Using a class variable

        Because population was declared "static", there is only one,
        shared by all instances of Monster. It is not stored in every
        instance of Monster. So it would not make sense to say:
              return this.population;
        and in fact, that line would not compile. We can access the
        variable through the class name, as below. If we omit the
        class name and say simply:
              return population;
        Java would still find the class variable and the code would
        work the same. However, it's good practise to be explicit
        and say that you mean to refer to a class variable.
         */
        return Monster.population;
    }

    public static void main(String[] args) {

        // Create some monsters.
        Monster m0 = new Monster("Grok", 21, 3);
        Monster m1 = new Monster();
        Monster m2 = new Monster("Gruffalo", 18, 5);
        Monster m3 = new Monster("Tiny", 5, 1);
        Monster m4 = new Monster();
        Monster[] scarey = {m0, m1, m2, m3, m4};

        // Do some eating and see what we have.
        System.out.println(m0.eat(m1));
        System.out.println(m0.eat(m4));
        System.out.println(m2.eat(m3));
        System.out.println(m0.eat(m2));  // Disallowed, since m2 isn't empty.
        System.out.println("Monster population: " + Monster.population());
        for (int i = 0; i < scarey.length; i++) {
            System.out.println(scarey[i]);
        }
        // tip: the above loop can be replaced with an enhanced for-loop
        // similar to what you are familiar with from Python:
        // for (Monster monster : scarey) {
        //     System.out.println(monster);
        // }

        // Do some digesting and see what we have.
        System.out.println(m0.digest());
        System.out.println(m0.digest());  // Only a cough, since m0 is empty.
        for (int i = 0; i < scarey.length; i++) {
            System.out.println(scarey[i]);
        }

        // Make some clones and check equivalance.
        Monster m2Clone = new Monster("Gruffalo", 18, 5);
        System.out.println(m2.equals(m2Clone));  // Not yet equivalent.
        Monster m3Clone = new Monster("Tiny", 5, 1);
        System.out.println(m2Clone.eat(m3Clone));
        System.out.println(m2.equals(m2Clone));  // Now equivalent.

        // No monsters were harmed in the making of this demo.
    }
}