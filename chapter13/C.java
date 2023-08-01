package javaInAction.chapter13;

public class C implements A, B{

    /**
     * 1. 클래스가 항상 이긴다. 클래스나 슈퍼클래스에서 정의한 메서드가 디폴트 메서드보다 우선권을 가진다
     * 2. 1번 규칙 이외의 상황에서는 서브인테페이스가 이긴다. 상속 관계를 갖는 인터페이스에서
     *    같은 시그니처를 갖는 메서드를 정의할 때는 서브인터페이스가 이긴다.
     *    즉, B가 A를 상속 받는다면 B가 A를 이긴다
     * 3. 여전히 디폴트 메서드의 우선순위가 결정되지 않았다면 여러 인터페이스를 상속받는 클래스가
     *     명시적으로 디폴트 메서드를 오버라이드하고 호출해야 한다
     */
    public static void main(String[] args) {
        new C().hello(); // -> Hello from B
    }
}
