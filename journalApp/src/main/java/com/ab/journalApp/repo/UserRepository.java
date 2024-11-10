package com.ab.journalApp.repo;

import com.ab.journalApp.entity.JournalEntity;
import com.ab.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
}
