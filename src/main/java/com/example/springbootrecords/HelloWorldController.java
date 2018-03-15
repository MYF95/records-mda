package com.example.springbootrecords;

import com.example.springbootrecords.history.model.HistoryEntry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping()
    public ModelAndView getHistory(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
