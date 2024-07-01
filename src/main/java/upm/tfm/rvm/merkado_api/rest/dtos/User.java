package upm.tfm.rvm.merkado_api.rest.dtos;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import upm.tfm.rvm.merkado_api.data.UserEntity;

@Getter
public class User {
    private String id;
    private String email;
    private String password;

    public User(){

    }

    public User(UserEntity userEntity){
        BeanUtils.copyProperties(userEntity, this);
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

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
