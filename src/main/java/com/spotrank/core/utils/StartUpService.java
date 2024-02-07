package com.spotrank.core.utils;

import com.spotrank.core.models.Country;
import com.spotrank.core.models.User;
import com.spotrank.core.models.UserRole;
import com.spotrank.core.models.Wallet;
import com.spotrank.core.repositories.CountryRepository;
import com.spotrank.core.repositories.UserRepository;
import com.spotrank.core.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
@ConfigurationProperties(prefix = "admin")
public class StartUpService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private CountryRepository countryRepository;
    private String email;
    private String password;

    @PostConstruct
    public void initAdmin() {
        Optional<User> admin = this.userRepository.findByEmail(this.email);
        if (!admin.isPresent()) {
            User adminUser = new User();
            Wallet w = new Wallet(1L, 1000, adminUser);

            adminUser.setEmail(this.email);
            adminUser.setName("Admin");
            adminUser.setAge(30);
            adminUser.setSurname("Admin");
            adminUser.setPassword(encoder.encode(this.password));
            adminUser.getRoles().add(UserRole.ADMIN);

            userRepository.save(adminUser);
            walletRepository.save(w);
        }
    }
    @PostConstruct
    public void initCountries() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/countries.csv")))) {            String content;
            while ((content = reader.readLine()) != null) {
                countryRepository.save(new Country(null,content));
            }
        } catch (IOException e) {
            System.out.println("Impossibile inizializzare countries");
        }

    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
