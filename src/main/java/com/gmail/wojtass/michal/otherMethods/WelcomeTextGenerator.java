package com.gmail.wojtass.michal.otherMethods;

import java.time.LocalDateTime;

public class WelcomeTextGenerator {

    public String generate(){
        int actualHour = LocalDateTime.now().getHour();
        String welcome = "Hello!";
        if (actualHour <= 4){welcome = "Good Night!";
        }else if (actualHour <= 12){welcome = "Good Morning!";
        }else if (actualHour <= 18){welcome = "Good Afternoon!";
        }else{welcome = "Good Night!";}
        return welcome;
    }
}
