package com.ooad.pixeledit.service.impl;

import com.ooad.pixeledit.dto.UserDto;
import com.ooad.pixeledit.entity.Role;
import com.ooad.pixeledit.entity.User;
import com.ooad.pixeledit.repository.RoleRepository;
import com.ooad.pixeledit.repository.UserRepository;
import com.ooad.pixeledit.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getUserName() + " " + userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress()); 
        user.setUserType(userDto.getUserType());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("Admin");
        if(role == null){
            role = checkRoleExist();
        }
        Role role1 = roleRepository.findByName("Photographer");
        if(role1 == null){
            checkRoleExist1();
        }
        Role role2 = roleRepository.findByName("Customer");
        if(role2 == null){
            checkRoleExist2();
        }
        Role role3 = roleRepository.findByName("Reviewer");
        if(role3 == null){
            checkRoleExist3();
        }

        
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

        
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setUserName(name[0]);
        userDto.setFullName(name[1]);
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress()); 
        user.setUserType(userDto.getUserType());


        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("Admin");
        return roleRepository.save(role);

           
        }
        private Role checkRoleExist1() {
            Role role = new Role();
            role.setName("Photographer");
            return roleRepository.save(role);
        }
        private Role checkRoleExist2() {
            Role role = new Role();
            role.setName("Customer");
            return roleRepository.save(role);
        }
        private Role checkRoleExist3() {
            Role role = new Role();
            role.setName("Reviewer");
            return roleRepository.save(role);
        }
        
       
        
    }
