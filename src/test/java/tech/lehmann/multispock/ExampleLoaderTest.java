package tech.lehmann.multispock;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExampleLoaderTest
{
    @Test
    public void getStringsIsThreadSafe() throws Exception
    {
        // given
        ExampleLoader loader = Mockito.spy(ExampleLoader.class);

        List<Thread> threads = IntStream.range(0, 10)
                .mapToObj(index -> new Thread(loader::getStrings, "Thread #" + index))
                .collect(Collectors.toList());

        // when
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // then
        Mockito.verify(loader, Mockito.times(1))
                .loadStrings();
    }
}
