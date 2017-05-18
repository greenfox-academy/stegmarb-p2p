package com.greenfox.controller;

import com.greenfox.model.Message;
import com.greenfox.model.User;
import com.greenfox.repository.MessageRepository;
import com.greenfox.repository.UserRepository;
import com.greenfox.service.P2PService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MessageController {

  @Autowired
  MessageRepository messageRepo;

  @Autowired
  UserRepository userRepo;

  @Autowired
  P2PService p2PService;

  @RequestMapping("/")
  public String indexSite(Model model) {
    return p2PService.mainPageHandler(model);
  }

  @PostMapping("/")
  public String addMessage(@RequestParam("message") String message) {
    messageRepo.save(new Message(p2PService.getCurrentUser(), message));
    return "redirect:/";
  }

  @PutMapping("/")
  public String updateUsername(@RequestParam("username") String user) {
    return p2PService.updateUsername(user);
  }

  @RequestMapping("/enter")
  public String enterPage(Model model) {
    List<User> users = new ArrayList<>((Collection) userRepo.findAll());
    model.addAttribute("currentUser", users);
    return "enter";
  }

  @PostMapping("/enter")
  public String enterMessage(Model model, @RequestParam("username") String user) {
    return p2PService.setUsername(model, user);
  }

}
