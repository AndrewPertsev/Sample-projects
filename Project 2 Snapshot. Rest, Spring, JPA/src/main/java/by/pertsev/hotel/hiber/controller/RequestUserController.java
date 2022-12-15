package by.pertsev.hotel.hiber.controller;

import by.pertsev.hotel.hiber.controller.exception.NotFoundException;
import by.pertsev.hotel.hiber.dto.RequestUserDto;
import by.pertsev.hotel.hiber.model.RequestUser;
import by.pertsev.hotel.hiber.service.RequestServiceable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static by.pertsev.hotel.hiber.controller.RequestUserController.REST_URL_REQUESTS;

@RestController
@RequestMapping(REST_URL_REQUESTS)
@AllArgsConstructor
public class RequestUserController {
    public static final String REST_URL_REQUESTS = "/requestusers";
    private final RequestServiceable requestUserServiceable;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequestUser save(@RequestBody RequestUserDto requestUser) {
        return requestUserServiceable.save(requestUser);

    }

    @GetMapping("/{id}")
    public RequestUser findById(@PathVariable int id) throws NotFoundException {
        return requestUserServiceable.findById(id);
    }

    @GetMapping
    public Page<RequestUser> findAll(Pageable pageable) {
        Page<RequestUser> requestUsers = requestUserServiceable.findAll(pageable);
        return requestUsers;
    }


    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody RequestUserDto dto, @PathVariable Integer id) {
        requestUserServiceable.update(dto, id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        requestUserServiceable.deleteById(id);

    }


    @GetMapping("/unresponded")
    public List<RequestUser> findUnresponded() {
        return requestUserServiceable.findUnresponded();
    }

    @GetMapping("/new")                                                     //todo
    public String showRequestUserForm(Model model) {
        RequestUser requestUser = new RequestUser();
        model.addAttribute("requestUser", requestUser);
        return "request_form";
    }
}



