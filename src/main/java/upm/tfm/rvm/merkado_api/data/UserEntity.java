package upm.tfm.rvm.merkado_api.data;

import lombok.Getter;
import org.apache.catalina.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Entity
@Table(name="users")
public class UserEntity {

    @Id
    private String id;
    private String email;
    private String password;
    private LocalDate creationDate;

    public UserEntity(){

    }

    public UserEntity(String email, String password, LocalDate creationDate) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
