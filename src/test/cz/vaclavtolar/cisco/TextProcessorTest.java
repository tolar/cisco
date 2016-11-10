package cz.vaclavtolar.cisco;

import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TextProcessorTest {

    private static TextProcessor textProcessor;

    @BeforeClass
    public static void testSetup() {
        textProcessor = new TextProcessorImpl();
    }

    @Test
    public void testCountWords() {
        Map<String, Long> result = textProcessor.countWords("wordA wordB wordA wordC wordC wordD wordC");
        Assert.assertEquals("Unexpected word count!", 2, result.get("wordA").longValue());
        Assert.assertEquals("Unexpected word count!", 1, result.get("wordB").longValue());
        Assert.assertEquals("Unexpected word count!", 3, result.get("wordC").longValue());
        Assert.assertEquals("Unexpected word count!", 1, result.get("wordD").longValue());
    }
}
