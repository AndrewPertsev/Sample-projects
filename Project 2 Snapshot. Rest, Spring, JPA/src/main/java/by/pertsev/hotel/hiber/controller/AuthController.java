package by.pertsev.hotel.hiber.controller;


import by.pertsev.hotel.hiber.dto.UserDto;
import by.pertsev.hotel.hiber.model.User;
import by.pertsev.hotel.hiber.security.AuthenticationUserDto;
import by.pertsev.hotel.hiber.security.JwtProviderUtil;
import by.pertsev.hotel.hiber.service.UserServiceable;
import by.pertsev.hotel.hiber.service.validation.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {
    private final UserServiceable userServiceable;
    private final ModelMapper modelMapper;
    private final UserValidator userValidator;
    private final JwtProviderUtil jwtProviderUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid UserDto userDto,
                                                   BindingResult bindingResult) {

        userValidator.validate(userDto, bindingResult);
        User user = convertToUser(userDto);

        if (bindingResult.hasErrors()) {
            throw new BadCredentialsException("Bad credentials");// set field with error D'Ifference 97jwt 59:39
        }

        userServiceable.save(userDto);
        String token = jwtProviderUtil.generateToken(user.getLogin());
        return Map.of("jwt-token", token);

        //1.08.00 su4:::
//        Map<String, Object> response = new HashMap<>();
//        response.put("username", user.getLogin());
//        response.put("jwt-token", token);
        // return ResponseEntity.ok(response);
        //    return "redirect:/auth/login";
    }

    public User convertToUser(UserDto userDTO) {
        return this.modelMapper.map(userDTO, User.class);
    }


    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationUserDto authUserDto) {
        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(authUserDto.getLogin(),
                authUserDto.getPassword());


        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }
        String token = jwtProviderUtil.generateToken(authUserDto.getLogin());
        return Map.of("jwt-token", token);
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }


    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("userDto") UserDto userDto) {
        return "auth/registration";
    }
}
