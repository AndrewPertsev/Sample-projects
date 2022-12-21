package by.pertsev.hotel.hiber.controller;

import by.pertsev.hotel.hiber.controller.exception.NotFoundException;
import by.pertsev.hotel.hiber.model.User;
import by.pertsev.hotel.hiber.model.dto.UserDto;
import by.pertsev.hotel.hiber.security.UserDetailsImpl;
import by.pertsev.hotel.hiber.service.UserServiceable;
import by.pertsev.hotel.hiber.service.validation.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static by.pertsev.hotel.hiber.controller.UserController.REST_URL_USERS;

@Slf4j
@RestController
@RequestMapping(REST_URL_USERS)
@AllArgsConstructor
public class UserController {

    public static final String REST_URL_USERS = "/users";
    private final UserServiceable userServiceable;
    private final UserValidator userValidator;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@Valid @RequestBody UserDto dto, BindingResult bindingResult) {
        userValidator.validate(dto, bindingResult);
        return userServiceable.save(dto);
    }


    @GetMapping
    public Page<User> findAll(Pageable pageable) {
        Page<User> users = userServiceable.findAll(pageable);
        return users;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) throws NotFoundException {
        return userServiceable.findById(id);
    }

    @GetMapping("/login/{login}")
    public Optional<User> findByLogin(@PathVariable String login) {
        return userServiceable.findByLogin(login);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserDto dto, @PathVariable Integer id, BindingResult bindingResult) {
        userValidator.validate(dto, bindingResult);
        userServiceable.update(dto, id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userServiceable.deleteById(id);
    }


    @GetMapping("/info")
    public String showCurrentUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getUsername();
    }


}


//    @Override
//    public Page getAllUsers(int page) {
//        return userRepository.findAll(PageRequest.of(page, 2, Sort.by("id")));
//    }


