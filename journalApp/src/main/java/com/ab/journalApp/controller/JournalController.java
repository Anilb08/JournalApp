package com.ab.journalApp.controller;

import com.ab.journalApp.entity.JournalEntity;
import com.ab.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    JournalEntity journalEntity;

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntity>getall(){
        return journalEntryService.getAll();
    }


    @PostMapping
    public JournalEntity createEntry(@RequestBody JournalEntity myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("/id/{myId}")
    public JournalEntity getJournalEntityById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }


    // delete the joural method
    @DeleteMapping("/id/{myId}")
    public JournalEntity deleteJournalEntity(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return journalEntity;
    }

    @PutMapping("/id/{myId}")
    public JournalEntity updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntity newEntry) {
        JournalEntity old = journalEntryService.findById(myId).orElse(null);
        if (old != null) {
            // Update title only if the new title is non-null and non-empty
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            // Update content only if the new content is non-null and non-empty
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());

            // Save the updated entry only if `old` is found
            journalEntryService.saveEntry(old);
        }
        return old;
    }



}
