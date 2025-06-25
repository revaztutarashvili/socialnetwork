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
 * @Component: ამ ანოტაციას ვადებ და აღვნიშნავ, რომ ეს კლასი არის "კომპონენტი" ანუ "Bean".
 * შესაბამისად, Spring ავტომატურად შექმნის ამ კლასის ერთ ეგზემპლარს (Singleton) აპლიკაციის გაშვებისას
 * და ჩვენ შეგვეძლება მისი გამოყენება სხვა კომპონენტებში (მაგ. სერვისებში) Dependency Injection-ის მეშვეობით.
 *
 * DtoMapper კლასის დანიშნულება: ეს კლასი პასუხისმგებელია Entity-დან DTO-ში და პირიქით კონვერტაციაზე.
 * თუმცა ამ შემთხვეევაში მთავარი მიზანია Entity ობიექტების DTO (Data Transfer Object) ობიექტებად გადაყვანა.
 */

@Component
public class DtoMapper {

    /**
     * User Entity-ს UserDto-დ გადაყვანა.
     * პარამეტრად გადავცემ User Entity-ის (user ბაზიდან წამოღებული User ობიექტი.)
     * @return-ით კი ვაბრუნებ  UserDto ობიექტს, რომელიც მზად არის კლიენტისთვის გასაგზავნად(სვაგერით ან პოოსტმენით რასაც ვაგზავნი).
     */
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
    /**
     * Comment Entity-ს CommentDto-დ გადაყვანა.
     * შენიშვნა: აქ ჩვენ არ ვაბრუნებთ მთლიან User ობიექტს, არამედ მხოლოდ ავტორის იუზერნეიმს,
     * რაც საუკეთესო პრაქტიკაა.
     * @param  comment პარამეტრებად ბაზიდან წამოღებულ Comment ობიექტს ვაწვდი.
     * return-ით კი ვაბრუნებ CommentDto ობიექტს .
     */
    public CommentDto toCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getCommentText(),
                comment.getAuthor().getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
    /**
     * აქაც იგივე ლოგიკით Post Entity-ს PostDto-დ გადამყავს.
     * ის აგროვებს ინფორმაციას რამდენიმე ადგილიდან:
     * - პოსტის ძირითადი ველები.
     * - ავტორის იუზერნეიმი (`post.getAuthor().getUsername()`).
     * - პოსტთან დაკავშირებული კომენტარების სია, რომლებიც ასევე DTO-ებად არიან გადაყვანილი.
     * - პოსტის ლაიქების რაოდენობა (`post.getLikes().size()`).
     * @param post პარამეტრად გადავცემ ბაზიდან წამოღებულ Post ობიექტს.
     * @return ვაბრუნებ სრულად დაკომპლექტებული PostDto.
     */
    public PostDto toPostDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getPostText(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                // ქვემოთ ვიყენებ Java Stream API-ს. post.getComments() სიას ვიღებთ,
                // თითოეულ Comment ობიექტზე ვიძახებთ toCommentDto მეთოდს და შედეგებს ვაგროვებთ ახალ სიაში.
                post.getComments().stream().map(this::toCommentDto).collect(Collectors.toList()),
                post.getLikes().size() // ვიღებთ ლაიქების სიის ზომას, ანუ ლაიქების რაოდენობას.
        );
    }
}