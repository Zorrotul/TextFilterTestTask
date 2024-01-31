import spock.lang.Specification

class HandlerTest extends Specification {
    def "my first test"(){
        setup:
        def a = 3
        def b = 8
        expect:
        a==b
    }
}
