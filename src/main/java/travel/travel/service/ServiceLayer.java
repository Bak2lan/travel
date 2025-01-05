package travel.travel.service;

import org.springframework.stereotype.Service;
import travel.travel.model.dto.response.SimpleResponse;

import java.util.List;

@Service
public interface ServiceLayer<RQST,RSNPS> {
    RSNPS save( RQST rqst);

    RSNPS findById(Long id);

    List<RSNPS> findAll();

    RSNPS update(Long id, RQST rqst);

    SimpleResponse delete(Long id);
}
