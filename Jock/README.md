Jock
====
###### Because visibility is important...

Jock provides simple and powerful object protection.
```java
    public class Example{
        final public Jock awesomeVariable;
        pubic Example(){
            awesomeVariable = new Jock("value"); //Create jock, you will be granted full access
            awesomeVariable.get(); //Returns value
            awesomeVariable.set("new content"); //Sets content

            awesomeVariable.allow(new Entry("(.*)", false, { "read" })); // Allow everyone to read, false means that we opt out of checking the super class
            awesomeVariable.allow(new Entry(this, true)); // By default Jock acts as private, this will make it more like a protected value


        }
    }
```