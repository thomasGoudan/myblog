package wu.xiu.myblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wu.xiu.myblog.domian.Authority;
import wu.xiu.myblog.domian.User;
import wu.xiu.myblog.service.AuthorityService;
import wu.xiu.myblog.service.UserService;
import wu.xiu.myblog.vo.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理控制器
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 用户列表展示
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(@RequestParam(value = "async",required = false) boolean async,
                             @RequestParam(value = "pageIndex",required = false,defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize",required = false,defaultValue = "5") int pageSize,
                             @RequestParam(value = "name",required = false,defaultValue = "") String name,
                             Model model){
        //前端传入的当前页及页面大小
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        //获取分页数据
        Page<User> page = userService.listUsersByNameLike(name,pageable);
        List<User> userList = page.getContent();
        model.addAttribute("page",page);
        model.addAttribute("userList",userList);
        return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list","userModel",model);
    }

    /**
     * 用户表单页面获取
     * @param model
     * @return
     */
    @GetMapping("/add")
    public ModelAndView createForm(Model model){
        model.addAttribute("user",new User(null,null,null,null));
        return new ModelAndView("users/add","userModel",model);
    }

    /**
     * 创建用户
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> create(User user,Long authorityId){
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);
        //密码加密
        if(user.getId() == null) {
            user.setEncodePassword(user.getPassword()); // 加密密码
        }else {
            // 判断密码是否做了变更
            User originalUser = userService.getUserById(user.getId());
            String rawPassword = originalUser.getPassword();
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodePasswd = encoder.encode(user.getPassword());
            boolean isMatch = encoder.matches(rawPassword, encodePasswd);
            if (!isMatch) {
                user.setEncodePassword(user.getPassword());
            }else {
                user.setPassword(user.getPassword());
            }
        }
        try {
            userService.saveOrUpdateUser(user);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false,e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true,"处理成功",user));
    }

    /**
     * 编辑用户
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "edit/{id}")
    public ModelAndView modifyForm(@PathVariable("id") long id,Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return new ModelAndView("users/edit","userModel",model);
    }

    /**
     * 删除用户
     * @param id
     * @param model
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") long id,Model model){
        try {
            userService.removeUser(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false,e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true,"处理成功"));
    }
}
