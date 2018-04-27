package com.example.springbootrecords.controller;

import com.example.springbootrecords.history.model.HistoryEntry;
import com.example.springbootrecords.user.model.UserEntry;
import com.example.springbootrecords.user.model.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.springbootrecords.history.model.HistoryEntryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/")
public class DefaultController  {

    @Autowired
    private HistoryEntryRepository historyEntryRepository;
    @Autowired
    private UserEntryRepository userEntryRepository;

    @GetMapping()
    public ModelAndView home1() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView getUserHistory(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        ModelAndView modelAndView = new ModelAndView("user");
        List<HistoryEntry> historyEntries =
                Optional.ofNullable(historyEntryRepository.findAllByPatientDni(name))
                        .orElse(new ArrayList<>());
        List<UserEntry> userEntries =
                Optional.ofNullable(userEntryRepository.findAllByPatientDni(name))
                        .orElse(new ArrayList<>());
        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("userentries", historyEntries);
        myModel.put("userinfo", userEntries);
        modelAndView.addAllObjects(myModel);
        return modelAndView;
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("about");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/403")
    public ModelAndView error403() {
        ModelAndView modelAndView = new ModelAndView("error/403");
        return modelAndView;
    }

    @RequestMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {

        String role =  authResult.getAuthorities().toString();

        if(role.contains("ROLE_ADMIN")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/history"));
        }
        else if(role.contains("ROLE_USER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user"));
        }
    }

}