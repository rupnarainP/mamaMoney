package com.mm.ussd.translator;

import com.mm.ussd.entity.UssdEntity;
import com.mm.ussd.enumeration.CountryEnum;
import com.mm.ussd.response.UssdResponse;
import com.mm.ussd.service.UssdService;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageTranslator {

    public final UssdResponse results;

    public MessageTranslator(final MessageTranslatorBuilder builder){
        this.results = builder.results;
    }

    public static final class MessageTranslatorBuilder {

        public UssdResponse results;

        public MessageTranslatorBuilder(final UssdResponse results) {
            this.results = results;
        }

        public MessageTranslator.MessageTranslatorBuilder displayMessage(UssdEntity ussdEntity) {
            if (ussdEntity != null) {
                if (ussdEntity.getProcessId() == 0) {
                    this.results.setSessionId(ussdEntity.getSessionId().trim());
                    this.results.setMessage("Welcome to Mama Money! Where would you like to send money to? " +
                            "1) Kenya 2) Malawi");
                }

                else if(ussdEntity.getProcessId() == 1){
                    if(ussdEntity.getUserEntry().equalsIgnoreCase(CountryEnum.KENYA.getEntryNumber())){
                        this.results.setMessage(String.format("How much money (in Rands) would you like to send to %s?"
                                , CountryEnum.KENYA.getName()));
                    }

                    else if(ussdEntity.getUserEntry().equalsIgnoreCase(CountryEnum.MALAWI.getEntryNumber())){
                        this.results.setMessage(String.format("How much money (in Rands) would you like to send to %s?"
                                , CountryEnum.MALAWI.getName()));
                    }

                    this.results.setSessionId(ussdEntity.getSessionId().trim());

                }

                else if(ussdEntity.getProcessId() == 2){
                    this.results.setSessionId(ussdEntity.getSessionId().trim());
                    this.results.setMessage(String.format("Your person you are sending to will receive: %s %s 1) OK"
                            , currencyConversion(ussdEntity.getUserEntry(), ussdEntity.getForeignCurrencyCode())
                            , ussdEntity.getForeignCurrencyCode()));
                }

                else if(ussdEntity.getProcessId() == 3){
                    this.results.setSessionId(ussdEntity.getSessionId().trim());
                    this.results.setMessage("Thank you for using Mama Money!");
                }
            }
            return this;
        }

        public MessageTranslator.MessageTranslatorBuilder displayErrorMessage(UssdEntity ussdEntity) {
            if (ussdEntity != null) {
//                if (ussdEntity.getProcessId() == 0) {
//                    this.results.setSessionId(ussdEntity.getSessionId().trim());
//                    this.results.setMessage("Welcome to Mama Money! Where would you like to send money to?" +
//                            "\n1) Kenya\n2) Malawi");
//                }

                if(ussdEntity.getProcessId() == 1){
                    this.results.setMessage("Please select a country to send money to");
                    this.results.setSessionId(ussdEntity.getSessionId().trim());
                }

                else if(ussdEntity.getProcessId() == 2){
                    this.results.setMessage("Please insert a valid amount");
                    this.results.setSessionId(ussdEntity.getSessionId().trim());
                }

                else if(ussdEntity.getProcessId() == 3){
                    this.results.setMessage("Please confirm your transaction by inserting option 1");
                    this.results.setSessionId(ussdEntity.getSessionId().trim());
                }
            }
            return this;
        }

        public MessageTranslator build(){
            return new MessageTranslator(this);
        }

        public String currencyConversion(String amount, String foreignCurrencyCode){
            double result = 0.0;
            if(foreignCurrencyCode.equalsIgnoreCase(CountryEnum.KENYA.getForeignCurrencyCode())){
                result = Double.parseDouble(amount);
                result = result * CountryEnum.KENYA.getExchangeRate();
            }

            else if(foreignCurrencyCode.equalsIgnoreCase(CountryEnum.MALAWI.getForeignCurrencyCode())){
                result = Double.parseDouble(amount);
                result = result * CountryEnum.MALAWI.getExchangeRate();
            }

            return result + "";
        }
    }
}
