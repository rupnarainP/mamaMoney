package com.mm.ussd.service;

import com.mm.ussd.entity.UssdEntity;
import com.mm.ussd.enumeration.CountryEnum;
import com.mm.ussd.repository.UssdRepository;
import com.mm.ussd.request.UssdRequest;
import com.mm.ussd.response.UssdResponse;
import com.mm.ussd.translator.MessageTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UssdService {
    @Autowired
    private UssdRepository ussdRepository;

    public UssdResponse getUssd(UssdRequest ussdRequest) {
        UssdResponse ussdResponse = new UssdResponse();

        UssdEntity ussdEntity = ussdRepository.findBySessionId(ussdRequest.getSessionId().trim());

        // for initializing the USSD process/ Menu #1
        if(ussdRequest.getUserEntry() == null || ussdEntity.getProcessId() == 0){
            //send initial message
            if(ussdEntity.getProcessId() != 0){
                new MessageTranslator.MessageTranslatorBuilder(ussdResponse).displayErrorMessage(ussdEntity).build();
            }

            else{
                new MessageTranslator.MessageTranslatorBuilder(ussdResponse).displayMessage(ussdEntity).build();

                ussdEntity.setProcessId(1);

                ussdRepository.save(ussdEntity);
            }

            return ussdResponse;
        }

        else{
            //Asking for amount/ Menu #2
            if(ussdEntity.getProcessId() == 1){
                if(ussdRequest.getUserEntry() != null){
                    if(ussdRequest.getUserEntry().equalsIgnoreCase(CountryEnum.KENYA.getEntryNumber())) {
                        ussdEntity.setForeignCurrencyCode(CountryEnum.KENYA.getForeignCurrencyCode());
                        ussdEntity.setUserEntry(ussdRequest.getUserEntry().trim());
                    }

                    else if(ussdRequest.getUserEntry().equalsIgnoreCase(CountryEnum.MALAWI.getEntryNumber())){
                        ussdEntity.setForeignCurrencyCode(CountryEnum.MALAWI.getForeignCurrencyCode());
                        ussdEntity.setUserEntry(ussdRequest.getUserEntry().trim());
                    }

                    else{
                        new MessageTranslator.MessageTranslatorBuilder(ussdResponse).displayErrorMessage(ussdEntity).build();
                        return  ussdResponse;
                    }

                    new MessageTranslator.MessageTranslatorBuilder(ussdResponse).displayMessage(ussdEntity).build();

                    ussdEntity.setProcessId(2);

                    ussdRepository.save(ussdEntity);

                    return ussdResponse;
                }

                else{
                    new MessageTranslator.MessageTranslatorBuilder(ussdResponse).displayErrorMessage(ussdEntity).build();
                    return  ussdResponse;
                }
            }

            //Amount and foreign currency code/ Menu #3
            if(ussdEntity.getProcessId() == 2){
                if(ussdRequest.getUserEntry() != null){
                    try{
                        Double.parseDouble(ussdRequest.getUserEntry());
                    }catch (NumberFormatException e){
                        new MessageTranslator.MessageTranslatorBuilder(ussdResponse).displayErrorMessage(ussdEntity).build();
                        return  ussdResponse;
                    }
                    ussdEntity.setUserEntry(ussdRequest.getUserEntry().trim());
                    new MessageTranslator.MessageTranslatorBuilder(ussdResponse).displayMessage(ussdEntity).build();

                    ussdEntity.setProcessId(3);
                    ussdRepository.save(ussdEntity);

                    return ussdResponse;
                }

                else{
                    new MessageTranslator.MessageTranslatorBuilder(ussdResponse).displayErrorMessage(ussdEntity).build();
                    return  ussdResponse;
                }
            }

            //Thank you message/ Menu #4
            if(ussdEntity.getProcessId() == 3){
                if(ussdRequest.getUserEntry() != null && ussdRequest.getUserEntry().equalsIgnoreCase("1")){

                    new MessageTranslator.MessageTranslatorBuilder(ussdResponse).displayMessage(ussdEntity).build();

                    ussdEntity.setProcessId(0);
                    ussdEntity.setUserEntry(null);
                    ussdEntity.setForeignCurrencyCode(null);

                    ussdRepository.save(ussdEntity);

                    return ussdResponse;
                }

                else{
                    new MessageTranslator.MessageTranslatorBuilder(ussdResponse).displayErrorMessage(ussdEntity).build();
                    return  ussdResponse;
                }
            }
        }


        return ussdResponse;
    }
}
