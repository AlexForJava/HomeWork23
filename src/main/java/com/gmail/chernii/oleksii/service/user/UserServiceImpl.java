package com.gmail.chernii.oleksii.service.user;

import com.gmail.chernii.oleksii.dto.UserDto;
import com.gmail.chernii.oleksii.enities.Role;
import com.gmail.chernii.oleksii.enities.UserEntity;
import com.gmail.chernii.oleksii.exceptions.EmailExistsException;
import com.gmail.chernii.oleksii.exceptions.NotFoundUserException;
import com.gmail.chernii.oleksii.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService, UserServiceRegistration {
    private final UserRepository repository;

    @Override
    public void create(UserDto userDto) {
        UserEntity entity = new UserEntity()
                .setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()))
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setEmail(userDto.getEmail())
                .setRoles(userDto.getRoles());
        entity.setUuid(UUID.randomUUID());
        repository.save(entity);
    }

    @Override
    public void update(UserDto userDto) throws NotFoundUserException {
        UserEntity entity = repository.findById(userDto.getId()).orElseThrow(NotFoundUserException::new);
        entity.setEmail(userDto.getEmail())
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setRoles(userDto.getRoles());
        repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDto findUserById(Long id) throws NotFoundUserException {
        return entityToDto(repository.findById(id).orElseThrow(NotFoundUserException::new));
    }

    @Override
    public UserDto findUserByEmail(String email) {
        UserEntity byEmail = repository.findByEmail(email);
        UserDto dto = new UserDto()
                .setEmail(byEmail.getEmail())
                .setFirstName(byEmail.getFirstName())
                .setLastName(byEmail.getLastName())
                .setId(byEmail.getId());
        return dto;
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> entityToDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserEntity registeredNewAccount(UserDto accountDto) throws EmailExistsException {
        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException("There's an account with that email address: " + accountDto.getEmail());
        }
        UserEntity entity = new UserEntity()
                .setEmail(accountDto.getEmail())
                .setFirstName(accountDto.getFirstName())
                .setLastName(accountDto.getLastName())
                .setPassword(new BCryptPasswordEncoder().encode(accountDto.getPassword()))
                .setRoles(new HashSet<Role>(Collections.singleton(Role.ROLE_USER)));
        return repository.save(entity);
    }

    private boolean emailExist(String email) {
        return (repository.findByEmail(email) != null);
    }

    private UserDto entityToDto(UserEntity entity) {
        UserDto dto = new UserDto()
                .setRoles(entity.getRoles())
                .setEmail(entity.getEmail())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setId(entity.getId());
        return dto;
    }
}
