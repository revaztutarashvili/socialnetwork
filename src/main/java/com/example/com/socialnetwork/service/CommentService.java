package com.example.com.socialnetwork.service;


import com.example.com.socialnetwork.dto.CommentDto;
import com.example.com.socialnetwork.dto.requests.CommentCreateRequest;
import com.example.com.socialnetwork.entity.Comment;
import com.example.com.socialnetwork.entity.Post;
import com.example.com.socialnetwork.entity.User;
import com.example.com.socialnetwork.mapper.DtoMapper;
import com.example.com.socialnetwork.repository.CommentRepository;
import com.example.com.socialnetwork.repository.PostRepository;
import com.example.com.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final DtoMapper dtoMapper;

    @Transactional
    public CommentDto createComment(CommentCreateRequest request) {
        User author = userRepository.findById(request.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + request.authorId()));
        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + request.postId()));

        Comment comment = new Comment();
        comment.setCommentText(request.commentText());
        comment.setAuthor(author);
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);
        return dtoMapper.toCommentDto(savedComment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long requestingUserId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

        Long commentAuthorId = comment.getAuthor().getId();
        Long postAuthorId = comment.getPost().getAuthor().getId();

        // კომენტარს შლის ან მისი ავტორი, ან პოსტის ავტორი
        if (!requestingUserId.equals(commentAuthorId) && !requestingUserId.equals(postAuthorId)) {
            throw new RuntimeException("User with id " + requestingUserId + " cannot delete this comment.");
        }

        commentRepository.delete(comment);
    }

    // დანარჩენი მეთოდების იმპლემენტაცია (მაგ: updateComment) ანალოგიურად ხდება.
    // UserX-ს არ შეუძლია UserY-ის კომენტარის რედაქტირება, ამიტომ update-ში მკაცრი შემოწმება იქნება:
    @Transactional
    public CommentDto updateComment(Long commentId, Long requestingUserId, String newText) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

        // მხოლოდ კომენტარის ავტორს შეუძლია რედაქტირება
        if (!comment.getAuthor().getId().equals(requestingUserId)) {
            throw new RuntimeException("Only the author can edit this comment.");
        }

        comment.setCommentText(newText);
        comment.setUpdatedAt(LocalDateTime.now());

        Comment updatedComment = commentRepository.save(comment);
        return dtoMapper.toCommentDto(updatedComment);
    }
}
