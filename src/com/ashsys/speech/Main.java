package com.ashsys.speech;

import java.io.*;
import java.util.logging.Logger;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class Main {
    public static void main(String[] args) {
        System.out.println("Speech Logic Started");
        Configuration config = new Configuration();

        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        config.setDictionaryPath("src\\dic.dic");
        config.setLanguageModelPath("src\\lm.lm");

        Logger cmRootLogger = Logger.getLogger("default.config");
        cmRootLogger.setLevel(java.util.logging.Level.OFF);
        String conFile = System.getProperty("java.util.logging.config.file");
        if(conFile == null)
            System.setProperty("java.util.logging.config.file", "ignoreAllSphinx4LoggingOutput");

        try {
            LiveSpeechRecognizer speech = new LiveSpeechRecognizer(config);
            speech.startRecognition(true);
            SpeechResult speechResult = null;

            while ((speechResult = speech.getResult()) != null) {
                String voiceCommand = speechResult.getHypothesis();
                System.out.print(voiceCommand + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}