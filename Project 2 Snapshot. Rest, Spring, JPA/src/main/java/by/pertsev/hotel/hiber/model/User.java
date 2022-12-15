package by.pertsev.hotel.hiber.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import static by.pertsev.hotel.hiber.model.DBColumnLabel.*;

@Entity
@Table(name = USERS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"userId"})
@Builder
public class User extends HotelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = USERS_ID)
    private Integer userId;

    @Column(name = USERS_ROLE_ID)
    private int role;

    @Column(name = USERS_NAME)
    private String name;

    @Column(name = USERS_SURNAME)
    private String surname;

    @Column(name = USERS_LOGIN)
    private String login;

    @Column(name = USERS_PASSWORD)
    private String password;

    @Column(name = USERS_EMAIL)
    private String email;

    @Column(name = USERS_COMMENT)
    private String comment;

    @Column(name = USERS_VIP)
    private boolean isVip;
    @Column(name = USERS_NON_GRATA)
    private boolean isNonGrata;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", roles=" + role +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                ", isVip=" + isVip +
                ", isNonGrata=" + isNonGrata +
                '}';
    }
}


