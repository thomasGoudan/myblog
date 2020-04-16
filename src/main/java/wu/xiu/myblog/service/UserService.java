package wu.xiu.myblog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wu.xiu.myblog.domian.User;

import java.util.Collection;
import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 新增，编辑、保存用户
     * @param user
     * @return
     */
    User saveOrUpdateUser(User user);

    /**
     * 注册用户
     * @param user
     * @return
     */
    User registerUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void removeUser(Long id);

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 获取用户列表
     * @return
     */
    List<User> listUsers();

    /**
     * 根据用户名进行分页模糊查询
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);

    /**
     * 根据名称列表查询
     * @param usernamelist
     * @return
     */
    List<User> listUsersByUserNames(Collection<String> usernamelist);
}
