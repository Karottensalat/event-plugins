package de.karotte128.unpredictor.challenges;

import java.util.Arrays;
import java.util.Random;

public class ChallengeManager {
    private static final String[] challenges = {"test1", "test2"};

    public static String[] getAllChallenges() {
        return challenges;
    }

    public static String getRandomChallenge() {
        Random rand = new Random();
        return Arrays.asList(challenges).get(rand.nextInt(challenges.length));
    }

    public static void startChallenge(String challenge) {

    }

    public static void stopChallenge() {

    }

}
