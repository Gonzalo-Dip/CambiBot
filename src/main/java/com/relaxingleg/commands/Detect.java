package com.relaxingleg.commands;
import com.detectlanguage.DetectLanguage;
import com.detectlanguage.errors.APIError;

public class Detect {
    public static void Language1() {
        try {
            DetectLanguage.apiKey = "e2afbe8f9cabf6a3261e13b1cb62d79a";
            String language = DetectLanguage.simpleDetect("свистеть");
            System.out.println(language);
        } catch (APIError e) {
            System.out.println(e.getMessage());
        }
    }
}

