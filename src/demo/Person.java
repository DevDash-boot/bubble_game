package demo;

import lombok.*;

@Getter
@Setter
@ToString
// 위 Getter, Setter, ToString 단축버전 Data
@Data
////////////
@NoArgsConstructor
@AllArgsConstructor

public class Person {
    private String name;
    private Integer age;

}
