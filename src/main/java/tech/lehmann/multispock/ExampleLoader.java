package tech.lehmann.multispock;

import java.util.Arrays;
import java.util.List;

public class ExampleLoader
{
    private List<String> strings;

    protected List<String> loadStrings()
    {
        return Arrays.asList("Hello", "World", "Sup");
    }

    public List<String> getStrings()
    {
        if (strings == null)
        {
            strings = loadStrings();
        }

        return strings;
    }
}
