package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.username))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
            new ApplicationUser(
                    "annasmith",
                                    passwordEncoder.encode("password"),
                                    STUDENT.grantedAuthorities(),
                                    true,
                    true,
                    true,
                            true
            ) , new ApplicationUser(
                        "linda",
                        passwordEncoder.encode("password"),
                        ADMIN.grantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ), new ApplicationUser(
                        "tom",
                        passwordEncoder.encode("password"),
                        ADMINTRAINEE.grantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );


        return applicationUsers;
    }
}
