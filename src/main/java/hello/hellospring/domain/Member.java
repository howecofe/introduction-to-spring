package hello.hellospring.domain;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

// [JPA] Annotation을 통해 객체와 관계형 DB를 Mapping
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
