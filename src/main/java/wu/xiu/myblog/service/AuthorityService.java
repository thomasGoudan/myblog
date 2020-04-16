package wu.xiu.myblog.service;

import wu.xiu.myblog.domian.Authority;

/**
 * 权限服务接口
 */
public interface AuthorityService {

    /**
     * 根据权限查询Authority
     * @param id
     * @return
     */
    Authority getAuthorityById(Long id);
}
