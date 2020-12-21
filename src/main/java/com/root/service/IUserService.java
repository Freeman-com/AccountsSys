package com.root.service;

import com.root.model.Users;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    Users registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

    Users getUser(String verificationToken);

    void saveRegisteredUser(Users user);

    void deleteUser(Users user);

    void createVerificationTokenForUser(Users user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(Users user, String token);

    Users findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    Optional<Users> getUserByPasswordResetToken(String token);

    Optional<Users> getUserByID(long id);

    void changeUserPassword(Users user, String password);

    boolean checkIfValidOldPassword(Users user, String password);

    String generateQRUrl(Users user) throws UnsupportedEncodingException;

    List<String> getUsersFromSessionRegistry();

}
