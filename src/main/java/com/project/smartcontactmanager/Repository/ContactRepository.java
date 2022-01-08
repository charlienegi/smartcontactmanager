package com.project.smartcontactmanager.Repository;

import com.project.smartcontactmanager.Model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
    /*public Page<Contact> findContactsByUser(Pageable pageable);*/
}
