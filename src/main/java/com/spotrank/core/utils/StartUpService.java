package com.spotrank.core.utils;

import com.spotrank.core.models.*;
import com.spotrank.core.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
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
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private SpotRepository spotRepository;
    @Autowired
    private PostRepository postRepository;
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
    public void initUsers() {
        for (int i = 1; i <= 15; i++) {
            String userEmail = "user" + i + "@example.com";
            Optional<User> existingUser = this.userRepository.findByEmail(userEmail);

            if (!existingUser.isPresent()) {
                User regularUser = new User();
                Wallet w = new Wallet(1L, 1000, regularUser);

                regularUser.setEmail(userEmail);
                regularUser.setName("User" + i);
                regularUser.setAge(25 + i);
                regularUser.setSurname("Surname" + i);
                regularUser.setPassword(encoder.encode("password")); // Puoi utilizzare una password predefinita o generarne una casuale
                regularUser.getRoles().add(UserRole.USER);

                userRepository.save(regularUser);
                walletRepository.save(w);
            }
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
    @PostConstruct
    public void initSpots() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/spots.csv")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Dividi la riga in un array di stringhe usando la virgola come delimitatore
                String[] elements = line.split(",");

                // Ottieni i valori dalla riga
                Long cityId = Long.parseLong(elements[0].trim());
                double latitude = Double.parseDouble(elements[1].trim());
                double longitude = Double.parseDouble(elements[2].trim());
                String spotName = elements[3].trim();
                String thumbnailName = elements[4].trim();
                String thumbnailType = elements[5].trim();
                String thumbnailBase64 = elements[6].trim();

                // Trova la città associata
                City city = cityRepository.findById(cityId).orElse(null);

                // Verifica se uno Spot simile esiste già
                Spot existingSpot = spotRepository.findByCityAndName(city, spotName);

                // Se la città esiste e uno Spot simile non esiste ancora, crea e salva l'oggetto Spot
                if (city != null && existingSpot == null) {
                    Spot spot = new Spot();
                    spot.setName(spotName);
                    spot.setLatitude(latitude);
                    spot.setLongitude(longitude);
                    spot.setCity(city);

                    // Crea e salva l'oggetto Thumbnail associato al Spot senza parentId
                    Image thumbnail = new Image();
                    thumbnail.setName(thumbnailName);
                    thumbnail.setParentType(ImageParentType.valueOf("SPOT"));
                    thumbnail.setType(thumbnailType);
                    thumbnail.setFile(Base64.getDecoder().decode(thumbnailBase64));

                    imageRepository.save(thumbnail);

                    // Ora che abbiamo l'ID dello Spot, possiamo aggiornare il parentId di Thumbnail
                    spot.setThumbnail(thumbnail);
                    spotRepository.save(spot);

                    thumbnail.setParentId(spot.getSpotId());
                    imageRepository.save(thumbnail);
                }
            }
        } catch (IOException e) {
            System.out.println("Impossibile inizializzare gli spots");
        }
    }

    @PostConstruct
    public void initPosts() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/posts.csv")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Dividi la riga in un array di stringhe usando la virgola come delimitatore
                String[] elements = line.split(",");

                // Ottieni i valori dalla riga
                Long userId = Long.parseLong(elements[0].trim());
                Long spotId = Long.parseLong(elements[1].trim());
                String imageName = elements[2].trim();
                String imageType = elements[3].trim();
                String imageBase64 = elements[4].trim();

                // Trova l'utente associato
                User user = userRepository.findById(userId).orElse(null);

                // Trova lo Spot associato
                Spot spot = spotRepository.findById(spotId).orElse(null);

                // Verifica se un Post simile esiste già
                Post existingPost = postRepository.findByUserAndSpot(user, spot);

                // Se l'utente e lo Spot esistono e un Post simile non esiste ancora, crea e salva l'oggetto Post
                if (user != null && spot != null && existingPost == null) {
                    Post post = new Post();
                    post.setCreationDate(new Date());
                    post.setUser(user);
                    post.setSpot(spot);

                    // Crea e salva l'oggetto Image associato al Post
                    Image image = new Image();
                    image.setName(imageName);
                    image.setParentType(ImageParentType.valueOf("POST"));
                    image.setType(imageType);
                    image.setFile(Base64.getDecoder().decode(imageBase64));

                    // Verifica se un'immagine simile esiste già
                    Image existingImage = imageRepository.findByNameAndParentType(imageName, ImageParentType.valueOf("POST"));

                    // Se l'immagine simile non esiste ancora, crea e salva l'oggetto Image
                    if (existingImage == null) {
                        imageRepository.save(image);
                    } else {
                        // Usa l'immagine esistente
                        image = existingImage;
                    }

                    post.setImage(image);

                    postRepository.save(post);
                }
            }
        } catch (IOException e) {
            System.out.println("Impossibile inizializzare i posts");
        }
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
