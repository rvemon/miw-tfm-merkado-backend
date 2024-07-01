package upm.tfm.rvm.merkado_api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upm.tfm.rvm.merkado_api.data.UserEntity;
import upm.tfm.rvm.merkado_api.rest.dtos.JwtRequest;
import upm.tfm.rvm.merkado_api.rest.dtos.JwtResponse;
import upm.tfm.rvm.merkado_api.rest.dtos.User;
import upm.tfm.rvm.merkado_api.service.UserService;

import java.time.LocalDate;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    static final String USERS = "/users";
    static final String LOGIN = "/login";
    private UserService userService;

    @Autowired
    public UserResource(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public JwtResponse createUser(@RequestBody JwtRequest jwtRequest){
        UserEntity userEntity = new UserEntity(
                jwtRequest.getEmail(),
                jwtRequest.getPassword(),
                LocalDate.now()
        );
        User userSaved = new User(this.userService.save(userEntity));
        return new JwtResponse(userSaved.getId(), userSaved.getEmail());
    }

    @PostMapping(LOGIN)
    public ResponseEntity<JwtResponse> Login(@RequestBody JwtRequest request){
        User user = this.userService.getUserByEmail(request.getEmail());


        if(BCrypt.checkpw(request.getPassword(),user.getPassword())){
            return new ResponseEntity<>(new JwtResponse(user.getId(),user.getEmail()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new JwtResponse(), HttpStatus.UNAUTHORIZED);
        }

    }

}
