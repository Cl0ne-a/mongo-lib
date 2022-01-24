package com.nosql.mongolib.listeners;

import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.repository.CommentRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoCommentBookCascadeEventListenner extends AbstractMongoEventListener<Book> {
    private final CommentRepository commentRepository;

    @Autowired
    public MongoCommentBookCascadeEventListenner(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        val id = source.get("_id").toString();
        commentRepository.deleteAllByBook_Id(id);
    }
}
