package upm.tfm.rvm.merkado_api.rest.dtos;

import lombok.Getter;

@Getter
public class JwtResponse {
    private String id;
    private String jwtToken;

    public JwtResponse(){

    }

    public JwtResponse(String id, String jwtToken) {
        this.id = id;
        this.jwtToken = jwtToken;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
