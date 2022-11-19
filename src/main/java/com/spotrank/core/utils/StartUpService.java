package com.spotrank.core.utils;

import com.spotrank.core.models.User;
import com.spotrank.core.models.UserRole;
import com.spotrank.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
@ConfigurationProperties(prefix = "admin")
public class StartUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    private String email;
    private String password;

    @PostConstruct
    public void initAdmin() {
        Optional<User> admin = this.userRepository.findByEmail(this.email);
        if (!admin.isPresent()) {
            User adminUser = new User();
            adminUser.setEmail(this.email);
            adminUser.setName("Admin");
            adminUser.setAge(28);
            adminUser.setSurname("Admin");
            adminUser.setPassword(encoder.encode(this.password));
            adminUser.getRoles().add(UserRole.ADMIN);
            this.userRepository.save(adminUser);
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
