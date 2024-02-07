package com.spotrank.core.utils;

import com.spotrank.core.models.*;
import com.spotrank.core.repositories.CityRepository;
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
import java.util.ArrayList;
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
    @Autowired
    private CityRepository cityRepository;
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
    public void initCountriesAndCities() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/cities.csv")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Dividi la riga in un array di stringhe usando la virgola come delimitatore
                String[] elements = line.split(",");

                // Il primo elemento è il nome del paese
                String countryName = elements[0].trim();

                // Crea e salva l'oggetto Country
                Country country = new Country(null, countryName, new ArrayList<>());
                countryRepository.save(country);

                // Salva le città associate al paese
                for (int i = 1; i < elements.length; i++) {
                    String cityName = elements[i].trim();

                    // Crea e salva l'oggetto City associato al Country
                    City city = new City(null, cityName, country);
                    cityRepository.save(city);
                }
            }
        } catch (IOException e) {
            System.out.println("Impossibile inizializzare countries e cities");
        }
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
