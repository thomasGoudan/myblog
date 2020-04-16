package wu.xiu.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wu.xiu.myblog.domian.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
