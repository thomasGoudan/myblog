package wu.xiu.myblog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wu.xiu.myblog.domian.User;

import java.util.Collection;
import java.util.List;

/**
 * 用户存储库  接口
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 用户名查询用户列表
     * @param name
     * @param pageable
     * @return
     */
    Page<User> findByNameLike(String name, Pageable pageable);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 根据名称列表查询
     * @param userNames
     * @return
     */
    List<User> findByUserNameIn(Collection<String> userNames);
}
