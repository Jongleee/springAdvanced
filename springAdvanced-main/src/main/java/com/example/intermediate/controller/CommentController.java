package com.example.intermediate.controller;

import com.example.intermediate.controller.response.ResponseDto;
import com.example.intermediate.controller.request.CommentRequestDto;
import com.example.intermediate.domain.Comment;
import com.example.intermediate.domain.UserDetailsImpl;
import com.example.intermediate.service.CommentService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
public class CommentController {

  private final CommentService commentService;

  @RequestMapping(value = "/api/auth/comment", method = RequestMethod.POST)
  public ResponseDto<?> createComment(@RequestBody CommentRequestDto requestDto,
      HttpServletRequest request) {
    return commentService.createComment(requestDto, request);
  }

  @RequestMapping(value = "/api/comment/{id}", method = RequestMethod.GET)
  public ResponseDto<?> getAllComments(@PathVariable Long id) {
    return commentService.getAllCommentsByPost(id);
  }

  @RequestMapping(value = "/api/auth/comment/{id}", method = RequestMethod.PUT)
  public ResponseDto<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto,
      HttpServletRequest request) {
    return commentService.updateComment(id, requestDto, request);
  }

  @RequestMapping(value = "/api/auth/comment/{id}", method = RequestMethod.DELETE)
  public ResponseDto<?> deleteComment(@PathVariable Long id,
      HttpServletRequest request) {
    return commentService.deleteComment(id, request);
  }
  @RequestMapping(value = "/api/myPage/{id}", method = RequestMethod.GET)
  public List<Comment> getAllLikes(@AuthenticationPrincipal UserDetailsImpl member_Id, @PathVariable Long id) {
    return commentService.getAllLikes(member_Id, id);
  }
  }

