package upm.tfm.rvm.merkado_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import upm.tfm.rvm.merkado_api.data.UserEntity;
import upm.tfm.rvm.merkado_api.data.UserRepository;
import upm.tfm.rvm.merkado_api.rest.dtos.User;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserEntity save(UserEntity user){
        String encodedPsw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(encodedPsw);
        //TODO verify unique user
        return this.userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findAll().stream().filter(u-> u.getEmail().equals(email))
                .findFirst().map(User::new).orElse(null);
    }
}
