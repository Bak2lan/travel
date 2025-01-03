package travel.travel.service;

import travel.travel.model.dto.request.ContactFormRequest;

public interface ContactFormService {

     void sendContactFormMessage(Long tourId, ContactFormRequest contactFormRequest);

}
