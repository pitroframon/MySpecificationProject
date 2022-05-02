package com.example.myspecificationproject.specifications;

import com.example.myspecificationproject.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<User> likeName(String name){
        if (name == null){
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<User> likeSurname(String surname){
        if (surname == null){
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("surname"), "%" + surname + "%");
    }
}
