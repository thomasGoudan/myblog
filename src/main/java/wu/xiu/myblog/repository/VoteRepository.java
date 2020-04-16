package wu.xiu.myblog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wu.xiu.myblog.domian.Vote;

/**
 * 点赞仓库
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
