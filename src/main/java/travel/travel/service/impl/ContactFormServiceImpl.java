package travel.travel.service.impl;

import org.springframework.stereotype.Service;
import travel.travel.model.dto.request.ContactFormRequest;
import travel.travel.service.ContactFormService;
@Service
public class ContactFormServiceImpl implements ContactFormService {
    @Override
    public void sendContactFormMessage(Long tourId, ContactFormRequest contactFormRequest) {


    }
}
