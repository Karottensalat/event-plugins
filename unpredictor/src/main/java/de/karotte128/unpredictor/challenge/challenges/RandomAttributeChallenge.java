package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomAttributeChallenge extends DefaultChallenge {

    private final Attribute attribute = randomAttribute();

    @Override
    public void load() {
        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(plugin, "attribute"), randomInt(), AttributeModifier.Operation.ADD_NUMBER);

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
        Registry<Attribute> registry = RegistryAccess.registryAccess().getRegistry(RegistryKey.ATTRIBUTE);

        List<Attribute> attributeList = registry.stream().collect(Collectors.toList());

        attributeList.remove(Attribute.FLYING_SPEED);
        attributeList.remove(Attribute.FOLLOW_RANGE);
        attributeList.remove(Attribute.SPAWN_REINFORCEMENTS);
        attributeList.remove(Attribute.TEMPT_RANGE);

        Random rand = new Random();
        return attributeList.get(rand.nextInt(attributeList.size()));
    }

    private Integer randomInt() {
        Random rand = new Random();
        return rand.nextInt(21) - 10; // Generates number between -10 and 10
    }
}
