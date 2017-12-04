package bullhorn.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {


    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping("/")
    public String home(){
        return "base";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping ("/list")
    public String listmessages (Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "list";
    }

    @GetMapping ("/add")
    public String messageForm (Model model){
        model.addAttribute("message", new Messages());
        return "messageForm";
    }

    @PostMapping ("/process")
    public String processMessage (@Valid Messages message, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("message", message);
            return "messageForm";
        }
        messageRepository.save(message);
        return "redirect:/list";
    }

    @RequestMapping("/detail/{id}")
    public String showMessage (@PathVariable ("id") long id, Model model){
        model.addAttribute("message", messageRepository.findOne(id));
        return "show";
    }

    @RequestMapping ("/update/{id}")
    public String updateMessage (@PathVariable ("id") long id, Model model){
        model.addAttribute("message", messageRepository.findOne(id));
        return "messageForm";
    }

    @RequestMapping ("/delete/{id}")
    public String deleteMessage (@PathVariable ("id") long id){
        messageRepository.delete(id);
        return "redirect:/list";

    }
}
