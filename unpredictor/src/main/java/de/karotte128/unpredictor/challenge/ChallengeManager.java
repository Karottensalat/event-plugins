package de.karotte128.unpredictor.challenge;

import de.karotte128.unpredictor.challenge.challenges.*;

import java.util.Arrays;
import java.util.Random;

public class ChallengeManager {
    private static final String[] challenges = {"test", "rain_damage", "dangerous_blocks", "entity_interaction", "water_damage", "swapped_spawning", "attacking_blocks", "lightning_spawning", "random_attribute", "zombie_apocalypse", "sneaking_damage", "falling_anvils", "position_swap", "no_gravity", "frost_walker"};
  
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
            case "test":
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
            case "water_damage":
                challengeClass = new DamagingWaterChallenge();
                break;
            case "swapped_spawning":
                challengeClass = new EntitySpawningChallenge();
                break;
            case "attacking_blocks":
                challengeClass = new AttackingBlocksChallenge();
                break;
            case "lightning_spawning":
                challengeClass = new LightningSpawnsEntitiesChallenge();
                break;
            case "random_attribute":
                challengeClass = new RandomAttributeChallenge();
                break;
            case "zombie_apocalypse":
                challengeClass = new ZombieApocalypseChallenge();
                break;
            case "sneaking_damage":
                challengeClass = new SneakDamagesChallenge();
                break;
            case "falling_anvils":
                challengeClass = new FallingAnvilChallenge();
                break;
            case "position_swap":
                challengeClass = new PositionSwapChallenge();
                break;
            case "no_gravity":
                challengeClass = new NoGravityChallenge();
                break;
            case "frost_walker":
                challengeClass = new FrostWalkerChallenge();
                break;
            default:
                challengeClass = null;
                break;
        }
    }

}