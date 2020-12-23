package com.root.service;

import com.root.dao.RoleRepository;
import com.root.dao.UserLocationRepository;
import com.root.dao.UserRepository;
import com.root.error.UserAlreadyExistException;
import com.root.model.User;
import com.root.model.UserLocation;
import com.root.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.maxmind.geoip2.DatabaseReader;

@Service
@Transactional

public class UserService {

    private final DatabaseReader databaseReader;
    private final Environment env;
    private final UserLocationRepository userLocationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserLocationRepository userLocationRepository,
                       Environment env, @Qualifier("GeoIPCountry") DatabaseReader databaseReader) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userLocationRepository = userLocationRepository;
        this.env = env;
        this.databaseReader = databaseReader;
    }

    public User registerNewUserAccount(final UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
        }
        final User user = new User();

        user.setName(accountDto.getName());
        user.setEmail(accountDto.getEmail());

        return userRepository.save(user);
    }

    public void addUserLocation(User user, String ip) {

        if (!isGeoIpLibEnabled()) {
            return;
        }

        try {
            final InetAddress ipAddress = InetAddress.getByName(ip);
            final String country = databaseReader.country(ipAddress)
                    .getCountry()
                    .getName();
            UserLocation loc = new UserLocation(country, user);
            loc.setEnabled(true);
            userLocationRepository.save(loc);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

    private boolean isGeoIpLibEnabled() {
        return Boolean.parseBoolean(env.getProperty("geo.ip.lib.enabled"));
    }




}
