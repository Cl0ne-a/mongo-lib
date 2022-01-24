package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Comment;
import com.nosql.mongolib.repository.CommentRepository;
import com.nosql.mongolib.service.CommentService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataMongoTest
class CommentServiceImplTest extends MongoLibApplicationTest{

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @Autowired
    CommentServiceImplTest() {
        this.commentRepository = mock(CommentRepository.class);
        this.commentService = new CommentServiceImpl(commentRepository);
    }

    @Test
    void addComment() {
        Book book = Book.builder().id("1").title("T").build();
        Comment comment = commentService.addComment(book, "some comment line");

        when(commentRepository.save(comment)).thenReturn(comment);

        val actualComment = commentService.addComment(book, comment.getCommentLine());

        assertThat(actualComment).isEqualTo(comment);
    }

    @Test
    void findCommentsByBook() {
        Book book = Book.builder().id("1").title("T").build();
        Comment comment = commentService.addComment(book, "some comment line");

        List<Comment> expectedCommentList = List.of(comment);
        when(commentRepository.findAllByBook(book)).thenReturn(expectedCommentList);

        val  actual = commentService.findCommentsByBook(book);
        assertEquals(actual, expectedCommentList);
    }
}