package wu.xiu.myblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wu.xiu.myblog.domian.Authority;
import wu.xiu.myblog.repository.AuthorityRepository;
import wu.xiu.myblog.service.AuthorityService;

/**
 * 接口实现
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityRepository.getOne(id);
    }
}
