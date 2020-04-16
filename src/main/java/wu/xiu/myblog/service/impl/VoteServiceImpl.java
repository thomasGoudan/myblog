package wu.xiu.myblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wu.xiu.myblog.domian.Vote;
import wu.xiu.myblog.repository.VoteRepository;
import wu.xiu.myblog.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService{

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.getOne(id);
    }

    @Override
    public void removeVote(Long id) {
        voteRepository.existsById(id);
    }
}
