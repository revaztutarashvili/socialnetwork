package com.example.com.socialnetwork.service;
import com.example.com.socialnetwork.dto.UserDto;
import com.example.com.socialnetwork.dto.requests.UserCreateRequest;
import com.example.com.socialnetwork.entity.User;
import com.example.com.socialnetwork.mapper.DtoMapper;
import com.example.com.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 *
 * */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;

    public UserDto registerUser(UserCreateRequest request) {
        // 1. ვქმნით ახალ User ენტერპრაიზის ობიექტს.
        User user = new User();
        // 2. ვუსეტავთ ველებს DTO-დან მოსული ინფორმაციით.
        // DTO-ში record-ის გამოყენების გამო, getter-ების ნაცვლად პირდაპირ ველის სახელით ვიძახებთ: request.firstName()
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setMobile(request.mobile());
        user.setBirthDate(request.birthDate());
        // 3. ვინახავთ User ობიექტს ბაზაში. save მეთოდი აბრუნებს შენახულ ობიექტს (რომელსაც უკვე აქვს ID).
        User savedUser = userRepository.save(user);

        // 4. შენახულ ობიექტს ვაქცევთ DTO-დ და ვაბრუნებთ.
        return dtoMapper.toUserDto(savedUser);
    }



    public UserDto getUserById(Long userId) {
        // userRepository.findById(userId) აბრუნებს Optional<User>-ს.
        // .orElseThrow(...) არის ელეგანტური გზა: თუ ობიექტი Optional-ში არის, მას აბრუნებს.
        // თუ Optional ცარიელია (მომხმარებელი ვერ მოიძებნა), ისვრის მითითებულ Exception-ს.
        // ამ ეტაპზე ვიყენებთ RuntimeException-ს, როგორც დავალებაში იყო მითითებული.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return dtoMapper.toUserDto(user);
    }

    public List<UserDto> getAllUsers() {
        // 1. `findAll()`-ით ბაზიდან ვიღებთ ყველა User ობიექტს.
        // 2. `stream()`-ით ვიწყებთ Stream API-ს გამოყენებას.
        // 3. `map(dtoMapper::toUserDto)` თითოეულ User ობიექტს გადააქცევს UserDto-დ.
        // 4. `collect(Collectors.toList())` აგროვებს შედეგს და აბრუნებს ახალ სიას.

        return userRepository.findAll().stream()
                .map(dtoMapper::toUserDto)
                .collect(Collectors.toList());

        /**
         * მოკლედ ამ ბოლო მეთოდში სტრიმ API-ის გამოყენება უფრო იმით არის გამართლეებული რომ ბოილერ ფლეითს
         * ვამცირებ, რადგან მის გარეშე კოდი იქნებოდა ასეთი for ციკლი რომ გამომეყენებინა:

         * Stream API-ის გარეშე:
         * List<User> users = userRepository.findAll();
         * List<UserDto> userDtos = new ArrayList<>();
         * for (User user : users) {
         *     userDtos.add(dtoMapper.toUserDto(user));
         * }
         * return userDtos;


         * ზოგადად ეს მეთოდი მომავალში თუ გავააფგრეიდებ ამ აპლიკაციას, დამეხმარება გარდა კოდის ლაკონუროობისა, ასევეე
         * პარალელიზმის დანერგვაში რაც სტრიმ ეიპიაის მთავარი კოზირია.
         * ეს ავტომატურად გაანაწილებს დამუშავებას ხელმისაწვდომ ბირთვებზე,
         * რაც გააუმჯობესებს შესრულებას დიდი მონაცემთა ნაკრებებისთვის.

         * ამისთვის საკმარისი იქნებოდა კოდის ამგვარი გადაკეთება:
         * return userRepository.findAll().parallelStream()<---აქ ეს იცვლება მხოლოდ: .parallelStream()
         *                 .map(dtoMapper::toUserDto)
         *                 .collect(Collectors.toList());
         * */
    }
}