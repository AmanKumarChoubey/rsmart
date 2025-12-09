package com.ecommerce.ecom.service;

import com.ecommerce.ecom.model.User;
import com.ecommerce.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalDate.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;


    // -------------------- CUSTOMER ------------------------
    public User registerCustomer(User user) {

        long count = repo.countByRole("CUSTOMER") + 1;

        user.setUserId("CC" + count);
        user.setRole("CUSTOMER");
        user.setCreatedBy("SELF");
        user.setCreatedOn(now().atStartOfDay());

        user.setPassword(encoder.encode(user.getPassword()));

        return repo.save(user);
    }


    // -------------------- MANAGER -------------------------
    public User registerManager(User user, String adminUserId) {

        long count = repo.countByRole("MANAGER") + 1;

        user.setUserId("MM" + count);      // **FIXED**
        user.setRole("MANAGER");
        user.setCreatedBy(adminUserId);
        user.setCreatedOn(now().atStartOfDay());

        user.setPassword(encoder.encode(user.getPassword()));

        return repo.save(user);
    }


    // -------------------- SHOPKEEPER ----------------------
    public User registerShopkeeper(User user, String managerUserId) {

        long count = repo.countByRole("SHOPKEEPER") + 1;

        user.setUserId("SS" + count);
        user.setRole("SHOPKEEPER");
        user.setCreatedBy(managerUserId);
        user.setCreatedOn(now().atStartOfDay());

        user.setPassword(encoder.encode(user.getPassword()));

        return repo.save(user);
    }

    public User findByEmail(String email){
        return repo.findByEmail(email).orElse(null);
    }
}
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository repo;
//
//    @Autowired
//    private PasswordEncoder encoder;
//
//    public User registerCustomer(User user){
//        user.setRole("CUSTOMER");
//
//        long count = repo.countByRole("CUSTOMER")+1;
//        user.setUserId("CC"+count);
//        user.setPassword(encoder.encode(user.getPassword()));
//        return repo.save(user);
//    }
//
//    public User registerManager(User user,String adminUserId){
//        user.setRole("MANAGER");
//        long count = repo.countByRole("MANAGER")+1;
//        user.setCreatedBy(adminUserId);
//        user.setPassword(encoder.encode(user.getPassword()));
//        return repo.save(user);
//    }
//
//    public User registerShopkeeper(User user, String managerUserId){
//        user.setRole("SHOPKEEPER");
//
//        long count = repo.countByRole("SHOPKEEPER")+1;
//        user.setUserId("SS"+count);
//
//        user.setCreatedBy(managerUserId);
//        user.setPassword(encoder.encode(user.getPassword()));
//
//        return repo.save(user);
//    }
//}
