package _test06;

/*
 * 플레이어의 방향 상태 관리
 * enum : 상수의 범주화를 만들 때 사용
 * boolean 2개(left, right)로 사용할 수 있지만
 * 둘다 true가 되면 잘못된 상태가 생길 수 있다.
 * enum은 정해진 값 중 하나만 가질 수 있어 더 안전하다.
 *
 * 왜 사용할까?
 * 나의 프로젝트나 논리 안에서 데이터의 범위를 지정하고 싶을 때 안전하게 사용 가능하다.
 *
 * [사용 방법]
 * PlayetWay p = PlayerWay.LEFT;
 * PlayetWay p = PlayerWay.RIGHT;
 */
public enum PlayerWay {
    LEFT, RIGHT
}
