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
        loadStringsIfNull();
        return strings;
    }

    private synchronized void loadStringsIfNull()
    {
        if (strings == null)
        {
            strings = loadStrings();
        }
    }
}
