package com.example.com.socialnetwork.mapper;
import com.example.com.socialnetwork.dto.CommentDto;
import com.example.com.socialnetwork.dto.PostDto;
import com.example.com.socialnetwork.dto.UserDto;
import com.example.com.socialnetwork.entity.Comment;
import com.example.com.socialnetwork.entity.Post;
import com.example.com.socialnetwork.entity.User;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
/**
 *
 * */
@Component
public class DtoMapper {

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getMobile(),
                user.getBirthDate()
        );
    }

    public CommentDto toCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getCommentText(),
                comment.getAuthor().getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    public PostDto toPostDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getPostText(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getComments().stream().map(this::toCommentDto).collect(Collectors.toList()),
                post.getLikes().size()
        );
    }
}