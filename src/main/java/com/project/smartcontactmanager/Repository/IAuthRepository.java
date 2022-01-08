package com.project.smartcontactmanager.Repository;

import com.project.smartcontactmanager.Model.Contact;
import com.project.smartcontactmanager.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface IAuthRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);
    public Contact save(Contact contact);

}
