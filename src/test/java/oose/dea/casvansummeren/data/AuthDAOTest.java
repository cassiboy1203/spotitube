package oose.dea.casvansummeren.data;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class AuthDAOTest {
    AuthDAO sut;

    @BeforeEach
    void setup(){
        sut = new AuthDAO();
    }
}