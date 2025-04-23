package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class RandomAttributeChallenge extends DefaultChallenge {

    private final Attribute attribute = randomAttribute();

    @Override
    public void load() {
        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(plugin, "attribute"), 100, AttributeModifier.Operation.ADD_SCALAR); //TODO: Change debug value 100 to random generated value

        server.getOnlinePlayers().forEach(player -> {
            Objects.requireNonNull(player.getAttribute(attribute)).addModifier(modifier);
        });

        Debug.debugMessage("Attribute " + attribute.toString() + " got modifier of " + modifier.getAmount());
        Debug.debugMessage("load attribute challenge");
    }

    @Override
    public void unload() {
        server.getOnlinePlayers().forEach(player -> {
            Objects.requireNonNull(player.getAttribute(attribute)).removeModifier(new NamespacedKey(plugin, "attribute"));
        });
        Debug.debugMessage("unload attribute challenge");
    }

    @Override
    public void runTask() {
        //nothing here
    }

    private Attribute randomAttribute() {
        Random rand = new Random();
        return Arrays.asList(Attribute.values()).get(rand.nextInt(Attribute.values().length));
    }
}
