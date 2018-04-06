package com.example.springbootrecords.history;

import com.example.springbootrecords.history.model.HistoryEntry;
import com.example.springbootrecords.history.model.HistoryEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
    public String deleteEntry(@PathVariable int id) {
        historyEntryRepository.deleteById(id);
        return "ok";
    }

    @PostMapping("/modifyEntry/{id}/{dni}/{observations}")
    public ResponseEntity<?> modifyEntry(@PathVariable int id, @PathVariable String dni, @PathVariable String observations){
        HistoryEntry historyEntry = historyEntryRepository.getOne(id);
        historyEntry.setDate(new Date());
        historyEntry.setObservations(observations);
        historyEntry.setPatientDni(dni);
        historyEntryRepository.save(historyEntry);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{dni}")
    public ModelAndView getSearch(@PathVariable String dni){
        ModelAndView modelAndView = new ModelAndView("perfil");
        List<HistoryEntry> historyEntries =
                Optional.ofNullable(historyEntryRepository.findAllByPatientDniOrderByDateDesc(dni))
                        .orElse(new ArrayList<>());
        modelAndView.addObject("entries", historyEntries);
        return modelAndView;
    }


    @GetMapping("/date/{date}")
    public ModelAndView getSearchDate(@DateTimeFormat(pattern = "yyyy-MM-dd")@PathVariable Date date){
        ModelAndView modelAndView = new ModelAndView("perfil");
        List<HistoryEntry> historyEntries =
                Optional.ofNullable(historyEntryRepository.findAllByDateIsGreaterThanEqual(date))
                        .orElse(new ArrayList<>());
        modelAndView.addObject("entries", historyEntries);
        return modelAndView;
    }

}
