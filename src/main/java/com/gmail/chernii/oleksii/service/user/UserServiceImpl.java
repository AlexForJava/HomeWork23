package com.gmail.chernii.oleksii.service.user;

import com.gmail.chernii.oleksii.dto.UserDto;
import com.gmail.chernii.oleksii.enities.Role;
import com.gmail.chernii.oleksii.enities.UserEntity;
import com.gmail.chernii.oleksii.exceptions.EmailExistsException;
import com.gmail.chernii.oleksii.exceptions.NotFoundUserException;
import com.gmail.chernii.oleksii.mappers.UserMapperImpl;
import com.gmail.chernii.oleksii.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService, UserServiceRegistration {
    private final UserRepository repository;

    @Override
    public void create(UserDto userDto) {
        repository.save(UserMapperImpl.entityCreate(userDto));
    }

    @Override
    public void update(UserDto userDto) throws NotFoundUserException {
        UserEntity entity = repository.findById(userDto.getId()).orElseThrow(NotFoundUserException::new);
        repository.save(UserMapperImpl.updateEntity(entity, userDto));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDto findUserById(Long id) throws NotFoundUserException {
        return UserMapperImpl.entityToDto(repository.findById(id).orElseThrow(NotFoundUserException::new));
    }

    @Override
    public UserDto findUserByEmail(String email) {
        UserEntity byEmail = repository.findByEmail(email);
        return new UserDto()
                .setEmail(byEmail.getEmail())
                .setFirstName(byEmail.getFirstName())
                .setLastName(byEmail.getLastName())
                .setId(byEmail.getId());
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> UserMapperImpl.entityToDto(entity))
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

}
