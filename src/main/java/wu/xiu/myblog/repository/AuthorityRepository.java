package wu.xiu.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wu.xiu.myblog.domian.Authority;

/**
 * 权限仓库
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
