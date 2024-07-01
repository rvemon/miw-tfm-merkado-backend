package upm.tfm.rvm.merkado_api.rest.dtos;

import lombok.Getter;

@Getter
public class JwtRequest {
    private String email;
    private String password;

    public JwtRequest(){

    }

    public JwtRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
