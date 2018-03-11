package com.example.springbootrecords.history;

import com.example.springbootrecords.history.model.HistoryEntry;
import com.example.springbootrecords.history.model.HistoryEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryEntryRepository historyEntryRepository;

    @GetMapping()
    public ModelAndView getHistory(){
        ModelAndView modelAndView = new ModelAndView("history");
        List<HistoryEntry> historyEntries =
                Optional.ofNullable(historyEntryRepository.findAll())
                        .orElse(new ArrayList<>());
        modelAndView.addObject("entries", historyEntries);
        return modelAndView;
    }

    @PostMapping("/addEntry/{dni}/{observations}")
    public ResponseEntity<?> addEntry(@PathVariable String dni, @PathVariable String observations){
        HistoryEntry historyEntry = new HistoryEntry();
        historyEntry.setDate(new Date());
        historyEntry.setObservations(observations);
        historyEntry.setPatientDni(dni);

        historyEntryRepository.save(historyEntry);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteEntry/{id}")
    public String deleteEntry(@PathVariable Integer id) {
        historyEntryRepository.delete(id);
        return "ok";
    }

}
