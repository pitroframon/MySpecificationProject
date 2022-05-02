package com.example.myspecificationproject;

import com.example.myspecificationproject.repository.UserRepositorySpec;
import com.example.myspecificationproject.specifications.UserSpecifications;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.myspecificationproject.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MySpecificationProjectApplicationTests {

    @Autowired
    private UserRepositorySpec userRepositorySpec;

    @BeforeEach
    void init() {
        userRepositorySpec.save(new User("Roman", "Petrov"));
        userRepositorySpec.save(new User("Artyom", "Petrov"));
    }

    @Test
    void search_with_one_spec__shouldWork() {
        List<User> users = userRepositorySpec.findAll(UserSpecifications.likeName("om"));
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    void search_with_all_specs__shouldWork() {
        Specification<User> specs = Specification.where(UserSpecifications.likeName("oman"))
                .and(UserSpecifications.likeSurname("etro"));

        List<User> users = userRepositorySpec.findAll(specs);
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    void search_using_or__shouldWork() {
        Specification<User> specs = Specification.where(UserSpecifications.likeName("oman"))
                .or(UserSpecifications.likeName("Art"));

        List<User> users = userRepositorySpec.findAll(specs);
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    void search_with_null_spec__shouldWork() {
        Specification<User> specs = Specification.where(UserSpecifications.likeName(null))
                .and(UserSpecifications.likeSurname("etr"));

        List<User> users = userRepositorySpec.findAll(specs);
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    void search_with_one_spec_no_data__shouldWork() {
        Specification<User> specs = Specification.where(UserSpecifications.likeName("bla"));

        List<User> users = userRepositorySpec.findAll(specs);
        assertNotNull(users);
        assertEquals(0, users.size());
    }


    @AfterEach
    void tearDown() {
        userRepositorySpec.deleteAll(userRepositorySpec.findAll());
    }

}
