package com.example.learnJPAmongoIntegretion.service;

import com.example.learnJPAmongoIntegretion.entity.JournalEntry;
import com.example.learnJPAmongoIntegretion.entity.User;
import com.example.learnJPAmongoIntegretion.repository.JournalEntryRepositoty;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    JournalEntryRepositoty journalEntryRepositoty;

    @Autowired
    UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        User user = userService.findByUsername(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepositoty.save(journalEntry);
        user.getJournalEntryList().add(saved);
        userService.saveEntry(user);
    }

    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepositoty.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepositoty.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepositoty.findById(id);
    }

    public void deleteById(String username, ObjectId id){
        User user = userService.findByUsername(username);
        user.getJournalEntryList().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepositoty.deleteById(id);
    }
}

