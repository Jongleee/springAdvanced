package com.example.intermediate.task;

import com.example.intermediate.domain.Post;
import com.example.intermediate.repository.CommentRepository;
import com.example.intermediate.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class ScheduleTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduleTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    // corn 으로 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(1-7)
    @Scheduled(cron="0 0 1 * * *") // 매일 오전 1시

//    @Scheduled(cron="*/10 * * * * *") // 테스트용 10초마다
    public void scheduledDeleteNoCommentedPost() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        for (Post post : postList) {
            int commentsNum = commentRepository.findAllByPost(post).size();
            String title=post.getTitle();
            if (commentsNum==0) {
                postRepository.delete(post);
                log.info("게시물 <"+title+">이 삭제되었습니다.");
            }
        }
    }

}
