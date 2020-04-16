package wu.xiu.myblog.service;

import wu.xiu.myblog.domian.Comment;

public interface CommentService {
    /**
     * 根据id获取 Comment
     * @param id
     * @return
     */
    Comment getCommentById(Long id);
    /**
     * 删除评论
     * @param id
     * @return
     */
    void removeComment(Long id);
}
