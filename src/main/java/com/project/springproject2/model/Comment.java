package com.project.springproject2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.springproject2.domain.Timestamped;
import com.project.springproject2.dto.CommentDto;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "commentResponseDtoList")
@NoArgsConstructor
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String author;
//    @JsonIgnore
//    private Long postId;
    @Column(nullable = false)
    private String content;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "posts_Id")
    private Post post;

    public Comment(Long postId, String content) {
//        this.postId = postId;
        this.content = content;
    }

    public Comment(CommentDto requestDto) {
//        this.postId = requestDto.getPostId();
        this.content = requestDto.getContent();
    }

    public Comment(CommentDto requestDto, String author) {
//        this.postId = requestDto.getPostId();
        this.content = requestDto.getContent();
        this.author = author;
    }

    public void update(CommentDto requestDto) {

        this.content = requestDto.getContent();
    }
}
