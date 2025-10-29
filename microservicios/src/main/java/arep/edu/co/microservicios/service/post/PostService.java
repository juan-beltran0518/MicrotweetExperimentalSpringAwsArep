package arep.edu.co.microservicios.service.post;

import arep.edu.co.microservicios.model.Post;
import arep.edu.co.microservicios.model.Stream;
import arep.edu.co.microservicios.model.User;
import arep.edu.co.microservicios.repository.PostRepository;
import arep.edu.co.microservicios.repository.StreamRepository;
import arep.edu.co.microservicios.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {
    private final PostRepository posts;
    private final UserRepository users;
    private final StreamRepository streams;

    public PostService(PostRepository posts, UserRepository users, StreamRepository streams) {
        this.posts = posts;
        this.users = users;
        this.streams = streams;
    }

    public Post create(Long userId, Long streamId, String content) {
        User u = users.findById(userId).orElseThrow();
        Long sid = (streamId == null) ? 1L : streamId;
        Stream s = streams.findById(sid).orElseThrow();

        var p = new Post();
        p.setUser(u);
        p.setStream(s);
        p.setContent(content);
        return posts.save(p);
    }

    public Page<Post> stream(Pageable pageable) {
        return posts.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Page<Post> byUser(Long userId, Pageable pageable) {
        return posts.findAllByUser_IdOrderByCreatedAtDesc(userId, pageable);
    }
}
