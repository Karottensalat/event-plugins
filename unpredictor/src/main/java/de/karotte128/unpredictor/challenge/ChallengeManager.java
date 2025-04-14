package de.karotte128.unpredictor.challenge;

import de.karotte128.unpredictor.challenge.challenges.DamagingRainChallenge;
import de.karotte128.unpredictor.challenge.challenges.DangerousBlocksChallenge;
import de.karotte128.unpredictor.challenge.challenges.EntityInteractionChallenge;
import de.karotte128.unpredictor.challenge.challenges.TestChallenge;

import java.util.Arrays;
import java.util.Random;

public class ChallengeManager {
    private static final String[] challenges = {"test1", "rain_damage", "dangerous_blocks", "entity_interaction"};

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
            challengeClass.loadChallenge();
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
            case "dangerous_blocks":
                challengeClass = new DangerousBlocksChallenge();
                break;
            case "entity_interaction":
                challengeClass = new EntityInteractionChallenge();
                break;
            default:
                challengeClass = null;
                break;
        }
    }

}