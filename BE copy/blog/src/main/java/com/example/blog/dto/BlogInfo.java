package com.example.blog.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogInfo {
    private Integer id;
    private String title;
    private String description;
    private String thumbnail;
    private LocalDateTime publishedAt;
    private Integer countComment;
    private AuthorInfo author;
    private String content;


    public BlogInfo(Integer id, String title, String description, String thumbnail, LocalDateTime publishedAt, Integer countComment, String author, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.publishedAt = publishedAt;
        this.countComment = countComment;
        if (author != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.author = mapper.readValue(author, AuthorInfo.class);
            } catch (IOException e) {
                this.author = null;
            }
        }
        this.content = content;
    }


    // Top 3 Blog co nhieu comment nhat
    public BlogInfo (Integer id, String title, LocalDateTime publishedAt) {
        this.id = id;
        this.title = title;
        this.publishedAt = publishedAt;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    private static class AuthorInfo implements Serializable {
        private Integer id;
        private String name;
    }
}
