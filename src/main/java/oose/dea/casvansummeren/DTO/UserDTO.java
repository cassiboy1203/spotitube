package oose.dea.casvansummeren.DTO;

public class UserDTO {
    private int id;
    private String name;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        var userDTO = (UserDTO) obj;
        return userDTO.id == id &&
                userDTO.name.equals(name) &&
                userDTO.password.equals(password);
    }
}
