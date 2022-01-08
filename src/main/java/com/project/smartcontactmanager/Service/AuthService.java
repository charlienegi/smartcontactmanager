package com.project.smartcontactmanager.Service;

import com.project.smartcontactmanager.Model.Contact;
import com.project.smartcontactmanager.Model.Message;
import com.project.smartcontactmanager.Model.User;
import com.project.smartcontactmanager.Repository.ContactRepository;
import com.project.smartcontactmanager.Repository.IAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    private IAuthRepository iAuthRepository;
    @Autowired
    private ContactRepository contactRepository;

    public ResponseEntity<?> Register(User user,MultipartFile file,HttpSession session) throws IOException {
        try {

        if(!file.isEmpty()){
            user.setImageUrl(file.getOriginalFilename());
            Path path = Paths.get(new ClassPathResource("static/img/image").getFile().getAbsolutePath() + File.separator + file.getOriginalFilename());
            Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);

        }
            User byEmail = iAuthRepository.findByEmail(user.getEmail());
          if(byEmail==null) {
                iAuthRepository.save(user);
              return ResponseEntity.ok().body("Saved Successfully");
            }
            if(byEmail.getEmail().equals(user.getEmail())){
                //session.setAttribute("user",new Message("Member has already exist. Please create a new E-Mail id"));
                return ResponseEntity.badRequest().body("User has already exist");
            }


        }
        catch (Exception ex){
            session.setAttribute("message",new Message(""+ex.getMessage(),"alert-danger"));
            ex.printStackTrace();
        }
        return ResponseEntity.ok().body(user);
    }

    public ResponseEntity<?> Login(User user,HttpSession session){
        User byEmail = iAuthRepository.findByEmail(user.getEmail());
        if(byEmail.getEmail().equals(user.getEmail())&& byEmail.getPassword().equals(user.getPassword())){
            return ResponseEntity.ok().body("Login Credentials is correct");
        }
        else {
            return ResponseEntity.badRequest().body("Invalid Credentials ");
        }
       // return ResponseEntity.ok(user);

    }

    public ResponseEntity<?> Contactdetail(Contact contact,MultipartFile file) throws IOException {
        try {
            if(!file.isEmpty()){
                contact.setImage(file.getOriginalFilename());
                Path path = Paths.get(new ClassPathResource("static/img").getFile().getAbsolutePath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);

            }
            Contact save = contactRepository.save(contact);
            return ResponseEntity.ok().body(save);
        }
        catch (Exception ex){
            throw ex;
        }
    }
    public List<Contact> GetAll(){
        return contactRepository.findAll();
    }

}
