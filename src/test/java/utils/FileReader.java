package utils;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

    public static <T> T readFileFromPathAndDeserialize(String filePath, Class<T> clazz){
       return serializeStringToObject(readFileAsString(filePath),clazz);
    }
    private static <T> T serializeStringToObject(String body, Class<T> clazz){
       T result =  new Gson().fromJson(body,clazz);
        return result;
    }
    private static String readFileAsString(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }





}
