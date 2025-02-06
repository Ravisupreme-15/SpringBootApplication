package com.example.learnJPAmongoIntegretion.controller;


import com.example.learnJPAmongoIntegretion.entity.JournalEntry;
import com.example.learnJPAmongoIntegretion.entity.User;
import com.example.learnJPAmongoIntegretion.service.JournalEntryService;
import com.example.learnJPAmongoIntegretion.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;

    @GetMapping("/get-all-jounals/{username}")
    public ResponseEntity getAllJournalEntriesOfUsers(@PathVariable String username){
        User user = userService.findByUsername(username);
        List<JournalEntry> all  = user.getJournalEntryList();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-entry/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username) {

         try {
             User user =  userService.findByUsername(username);
             journalEntryService.saveEntry(myEntry,username);
             return new ResponseEntity<>(myEntry,HttpStatus.CREATED);

         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId id){
         Optional<JournalEntry> journalEntry  = journalEntryService.getById(id);
         if(journalEntry.isPresent()){

             return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity<JournalEntry> deleteJournalEntry(@PathVariable String username, @PathVariable ObjectId id){
        journalEntryService.deleteById(username,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{username}/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable String username,@PathVariable ObjectId id, @RequestBody JournalEntry myEntry)
    {
        JournalEntry oldEntry = journalEntryService.getById(id).orElse(null);
        if(oldEntry!=null){
            oldEntry.setTitle(myEntry.getTitle() !=null && !myEntry.getTitle().equals("")? myEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("")? myEntry.getContent():oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
