package jsebfranck.samples.arquillian;

import java.io.PrintStream;

/**
 * A component for creating personal greetings.
 * Source : arquillian official examples
 */
public class Greeter {
    public void greet(PrintStream to, String name) {
        to.println(createGreeting(name));
    }

    public String createGreeting(String name) {
        return "Hello, " + name + "!";
    }
}
