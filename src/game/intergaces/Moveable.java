package game.intergaces;

public interface Moveable {
    void left();
    void right();
    void up();

    // Adapter 클래스가 너무 많이 생겨서 default 문법을 인터페이스에서 사용할 수 있도록 만들어 줬다.
    // default를 사용하면 인터페이스 안에서 일반 메서드도 구현할 수 있다.
    // 재정의가 필요없는 곳에서는 재정의하지 않아도 된다.
    default void down(){};
}
