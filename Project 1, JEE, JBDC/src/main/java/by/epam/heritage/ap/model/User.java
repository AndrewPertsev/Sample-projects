package by.epam.heritage.ap.model;

import java.io.Serializable;
import java.util.Objects;

public class User extends HotelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private int userId;
    private int roleId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String tel;
    private String country;
    private String comment;
    private int passport;
    private boolean isVip;
    private boolean isNonGrata;

    public User() {
    }

    public User(int userId, String name, String surname, String login, String password, int roleId, int passport, String email, String tel, String country, boolean isVip, boolean isNonGrata, String comment) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
        this.passport = passport;
        this.email = email;
        this.tel = tel;
        this.country = country;
        this.isVip = isVip;
        this.isNonGrata = isNonGrata;
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public int getPassport() {
        return passport;
    }
    public void setPassport(int passport) {
        this.passport = passport;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {this.isVip = vip;}
    public boolean isNonGrata() {return isNonGrata;}
    public void setNonGrata(boolean nonGrata) {this.isNonGrata = nonGrata;}
    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o.getClass() != getClass()){return false;}
        User guest = (User) o;
        return getUserId() == guest.getUserId() &&
                getRoleId() == guest.getRoleId() &&
                getPassport() == guest.getPassport() &&
                getName().equals(guest.getName()) &&
                getSurname().equals(guest.getSurname()) &&
                getLogin().equals(guest.getLogin()) &&
                getEmail().equals(guest.getEmail()) &&
                getTel().equals(guest.getTel()) &&
                getCountry().equals(guest.getCountry()) &&
                getComment().equals(guest.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getName(), getSurname(), getLogin(), getRoleId(), getPassport(), getEmail(), getTel(), getCountry(), getComment());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", role_id=" + roleId +
                ", passport=" + passport +
                ", mail='" + email + '\'' +
                ", phone='" + tel + '\'' +
                ", country='" + country + '\'' +
                ", vip=" + isVip +
                ", nongrata=" + isNonGrata +
                ", comment='" + comment + '\'' +
                '}';
    }
}
