import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class HelloWorldTest {

    @Test
    public void testHelloWorld() {
        assertEquals("Hello World", HelloWorldController.getHelloWorld());
    }
}
