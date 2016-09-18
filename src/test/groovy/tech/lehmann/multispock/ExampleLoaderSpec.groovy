package tech.lehmann.multispock

import spock.lang.Specification

class ExampleLoaderSpec extends Specification {
    void getStringsReturnsTheCorrectStrings() {
        given:
        def loader = new ExampleLoader()

        when:
        def strings = loader.getStrings()

        then:
        strings == ["Hello", "World", "Sup"]
    }

    def "getStrings caches the strings"() {
        given:
        def loader = Spy(ExampleLoader)

        when:
        50.times { loader.getStrings() }

        then:
        1 * loader.loadStrings()
    }

    def "getStrings is thread safe"() {
        given:
        def loader = Spy(ExampleLoader)

        def threads = (0..<10).collect { new Thread({ loader.getStrings() }) }

        when:
        threads.each { it.start() }
        threads.each { it.join() }

        then:
        1 * loader.loadStrings()
    }
}
