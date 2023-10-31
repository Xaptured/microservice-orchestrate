package com.esportarena.microservices.orchestrateapi.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class IntegerGenerator {

    private static final Integer MIN = 10000;
    private static final Integer MAX = 99999;

    public Integer generateFiveDigitRandomInteger() {
        Random random = new Random();
        return random.nextInt(MAX - MIN + 1) + MIN;
    }
}
