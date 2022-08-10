package com.example.intermediate.controller.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponseDto {

    private List myPagePostResponseDtoArrayList;
    private List myPageCommentResponseDtoArrayList;
    private List myPageSubCommentResponseDtoArrayList;



    public void updateResponse(
            List<MyPagePostResponseDto> myPagePostResponseDtoArrayList,
            List<MyPageCommentResponseDto> myPageCommentResponseDtoArrayList,
            List<MyPageSubCommentResponseDto> myPageSubCommentResponseDtoArrayList)
    {
        this.myPagePostResponseDtoArrayList = myPagePostResponseDtoArrayList;
        this.myPageCommentResponseDtoArrayList = myPageCommentResponseDtoArrayList;
        this.myPageSubCommentResponseDtoArrayList = myPageSubCommentResponseDtoArrayList;
    }
}
