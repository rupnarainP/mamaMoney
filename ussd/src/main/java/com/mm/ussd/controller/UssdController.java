package com.mm.ussd.controller;

import com.mm.ussd.request.UssdRequest;
import com.mm.ussd.response.UssdResponse;
import com.mm.ussd.service.UssdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/mamamoney") // This means URL's start with /demo (after Application path)
public class UssdController {
    @Autowired
    UssdService ussdService;

    @PostMapping(path="/ussd")
    public UssdResponse getUser(@RequestBody UssdRequest ussdRequest) {
        // This endpoint accepts a USSD request and returns a USSD response
        return ussdService.getUssd(ussdRequest);
    }
}
