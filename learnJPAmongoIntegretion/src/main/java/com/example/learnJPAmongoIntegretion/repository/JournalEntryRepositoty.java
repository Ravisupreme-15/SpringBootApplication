package com.example.learnJPAmongoIntegretion.repository;

import com.example.learnJPAmongoIntegretion.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepositoty extends MongoRepository<JournalEntry, ObjectId> {
}
