package com.project.smartcontactmanager.Controller;

import com.project.smartcontactmanager.Model.Contact;
import com.project.smartcontactmanager.Model.Message;
import com.project.smartcontactmanager.Model.User;
import com.project.smartcontactmanager.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private AuthService authService;
    @RequestMapping("/home")
    public String RegistrationView(){
        return "register";
    }
    @PostMapping("/do_register")
    public String Signup(@ModelAttribute User user,@RequestParam("profileImage")MultipartFile file, HttpSession session){
        try {
            ResponseEntity<?> register = authService.Register(user,file,session);
            if(register.getBody().equals("User has already exist")){
                session.setAttribute("message",new Message("Email id is already exist. Please re-enter " +
                        "your E-mail id","alert-danger"));
            }
            else if(register.getStatusCodeValue()==200) {
                return "Index";
            }
        }
        catch (Exception ex){
            session.setAttribute("message",new Message(""+ex.getMessage(),"alert-danger"));
            ex.printStackTrace();
        }
        return "register";
    }
    @GetMapping("/login")
    public String Loginview(){
        return "Index";
    }
    @PostMapping("/do_login")
    public String Loginform(@ModelAttribute User user,HttpSession session){
        ResponseEntity<?> login = authService.Login(user, session);
        if(login.getStatusCodeValue()==200){
            session.setAttribute("message",new Message("Login Successfully","alert-success"));

        }
        else {
            session.setAttribute("message",new Message("Credentials is Invalid","alert-danger"));

        }
        return "dashboard";
    }
    @GetMapping("/dashboard")
    public String Dashboard(){
        return "Dashboard";
    }
    @GetMapping("/task")
    public String TaskView(){
        return "task";
    }
    @GetMapping("/contact")
    public String Contactview(){
        return "add-contact";
    }
    @PostMapping("/viewContact")
    public String Contactsubmit(@ModelAttribute Contact contact,Model model,@RequestParam("profileImage")MultipartFile file) throws IOException {
        authService.Contactdetail(contact,file);
        model.addAttribute("contacts",authService.GetAll());
        return "view-contact";
    }
    @GetMapping("/viewContact")
    public String ListAll(Model model){
        List<Contact> contacts = authService.GetAll();
        model.addAttribute("contacts",contacts);
        return "view-contact";
    }
}
