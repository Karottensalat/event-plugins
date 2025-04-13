package de.karotte128.unpredictor.challenge;

import de.karotte128.unpredictor.Unpredictor;
import de.karotte128.unpredictor.challenge.challenges.DamagingRainChallenge;
import de.karotte128.unpredictor.challenge.challenges.TestChallenge;

import java.util.Arrays;
import java.util.Random;

public class ChallengeManager {
    private static final String[] challenges = {"test1", "rain_damage"};

    public static String currentChallenge;

    private static DefaultChallenge challengeClass;

    public static String[] getAllChallenges() {
        return challenges;
    }

    public static String getRandomChallenge() {
        Random rand = new Random();
        return Arrays.asList(challenges).get(rand.nextInt(challenges.length));
    }

    public static String getCurrentChallenge() {
        return currentChallenge;
    }

    public static void startChallenge(String challenge) {
        currentChallenge = challenge;
        challengeSelector(challenge);
        if (challengeClass != null) {
            challengeClass.loadChallenge(Unpredictor.getInstance());
        }
    }

    public static void stopChallenge() {
        if (challengeClass != null) {
            challengeClass.unloadChallenge();
        }
        currentChallenge = "";
    }

    private static void challengeSelector(String challenge) {
        switch (challenge) {
            case "test1":
                challengeClass = new TestChallenge();
                break;
            case "rain_damage":
                challengeClass = new DamagingRainChallenge();
                break;
            default:
                challengeClass = null;
                break;
        }
    }

}