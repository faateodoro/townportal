package com.fteodoro.townportal.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserForm userForm) {
        User user = new User(userForm);
        userRepository.save(user);
        return user;
    }

    public Page<UserDto> pageAllUsers(Pageable pageable) {
        Page<UserDto> users = userRepository.findAllByActiveTrue(pageable).map(UserDto::new);
        return users;
    }
    
    public User getUser(Long id) {
        User user = userRepository.getReferenceById(id);
        return user;
    }

    public void inactivateUser(Long id) {
        User user = userRepository.getReferenceById(id);
        user.inactivate();
    }

    public User updateUser(Long id, UserForm userForm) {
        User user = userRepository.getReferenceById(id);
        user.updateData(userForm);
        userRepository.save(user);
        return user;
    }
}