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
        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setMobile(request.mobile());
        user.setBirthDate(request.birthDate());
        User savedUser = userRepository.save(user);
        return dtoMapper.toUserDto(savedUser);
    }

    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return dtoMapper.toUserDto(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(dtoMapper::toUserDto)
                .collect(Collectors.toList());
    }
}