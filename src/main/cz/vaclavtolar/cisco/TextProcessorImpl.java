package cz.vaclavtolar.cisco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TextProcessorImpl implements TextProcessor {

    public Map<String, Long> countWords(String text) {
        Map<String, Long> result = new HashMap<String, Long>();

        String[] words = text.split("\\s+");

        for (String word : words) {
            result.put(word,
                    result.get(word) == null ? 1 :  result.get(word).longValue()+1 );
        }

        return result;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java cz/vaclavtolar/cisco/TextProcessorImpl <filename>");
            System.exit(0);
        }

        try {
            String contents = new String(Files.readAllBytes(Paths.get(args[0])));
            Map<String, Long> result = (new TextProcessorImpl()).countWords(contents);
            for (String word : result.keySet()) {
                System.out.println(result.get(word) + ":" + word);
            }
        } catch (IOException e) {
            System.out.println("Failed to read file.");
            System.exit(-1);
        }
    }

}
