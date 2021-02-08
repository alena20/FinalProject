package edu.epam.web.entity;

import edu.epam.web.util.IdGenerator;

public class User extends Entity {
    // private static final IdGenerator indexGenerator = new IdGenerator();

    private long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private UserRole role;

/*    {
        id = indexGenerator.getId();
    }*/


    public User(String login, String password, UserRole role) {
      //  this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String login, String password, String name, String surname, String email, UserRole role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }


    public long getId() {
        return id;
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

    public void setPassword(String pass) {
        this.password = pass;
    }

    public UserRole getRole() {
        return role;
    }

    public User setRole(UserRole role) {
        this.role = role;
        return this;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = login.hashCode()*31 + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login);
        sb.append(", pass='").append(password);
        sb.append(", role='").append(role.toString());
        sb.append("}");
        return sb.toString();
    }
}
