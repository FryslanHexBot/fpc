package fpc;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.hexbot.api.listeners.Paintable;
import org.hexbot.api.methods.Walking;
import org.hexbot.api.methods.input.Mouse;
import org.hexbot.api.methods.interactable.GameObjects;
import org.hexbot.api.methods.interactable.Npcs;
import org.hexbot.api.methods.interactable.Players;
import org.hexbot.api.methods.node.GroundItems;
import org.hexbot.api.methods.node.Inventory;
import org.hexbot.api.util.Random;
import org.hexbot.api.util.Time;
import org.hexbot.api.wrapper.interactable.GameObject;
import org.hexbot.api.wrapper.interactable.Npc;
import org.hexbot.api.wrapper.node.GroundItem;
import org.hexbot.api.wrapper.node.InventoryItem;
import org.hexbot.core.concurrent.script.Condition;
import org.hexbot.core.concurrent.script.Info;
import org.hexbot.core.concurrent.script.LoopScript;
import org.hexbot.core.concurrent.script.Type;

import java.awt.*;

@Info(author = "Fryslan", name = "Fryslan PowerChopper v1.0", description = "Powerchops Trees", type = Type.WOODCUTTING)
public class FryslanPowerChopper extends LoopScript implements Paintable {

    public static String treeName = "Willow";
    public static boolean loaded = true;

    @Override
    public void onStart() {
        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        new UI().start(new Stage());
                        System.out.println("1");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (loaded == false) {
            Time.sleep(1000);
        }
    }

    @Override
    public void onEnd() {

    }

    @Override
    public int loop() {
        GameObject boom = GameObjects.getNearest(treeName);
        GroundItem nest = GroundItems.getNearest("Bird nest");
        Npc ent = Npcs.getNearest(treeName);

        if (ent != null) {
            AvoidEnt(ent, boom);
        }

        if (!Inventory.isFull()) {

            if (nest != null) {
                pickNest(nest);

            } else if (boom != null) {
                Chop(boom);
            }

        } else {
            dropAllWithMouseKeys(new String[]{"Willow logs", "Beer", "Kebab", "Strength potion(2)", "Vial"}, -2, 2);
        }

        return 300;
    }

    private void AvoidEnt(Npc ent, GameObject boom) {
        if (ent.getInteracting().equals(Players.getLocal())) {
            if (boom.isVisible()) {
                boom.interact("Chop");
                Time.sleep(1000);
            } else {
                Walking.walk(boom);
            }
        }
    }

    private void pickNest(GroundItem nest) {

        if (nest.isVisible()) {
            if (Players.getLocal().isIdle()) {
                nest.interact("Take");
                Time.sleep(1000);
            } else {
                Time.dynamicSleep(new Condition() {
                    @Override
                    public boolean validate() {
                        return Players.getLocal().isIdle();
                    }
                }, 2000, 2500);
            }
        } else {
            Walking.walk(nest);
        }
    }

    private void Chop(GameObject boom) {

        if (boom.isVisible()) {
            if (Players.getLocal().isIdle()) {
                boom.interact("Chop");
                Time.sleep(1000);
            } else {
                Time.dynamicSleep(new Condition() {
                    @Override
                    public boolean validate() {
                        return Players.getLocal().isIdle();
                    }
                }, 2000, 2500);
            }

        } else {
            Walking.walk(boom);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Mouse.isPressed() ? Color.RED : Color.GREEN);
        final Point m = Mouse.getLocation();
        g.drawLine(m.x - 5, m.y, m.x + 5, m.y);
        g.drawLine(m.x, m.y - 5, m.x, m.y + 5);


    }

    private void dropAllWithMouseKeys(String[] items, int offsetX, int offsetY) {
        Rectangle bottom = new Rectangle(550, 430, 733, 464);
        for (InventoryItem i : Inventory.getAll()) {
            for (String s : items) {
                if (i != null && i.getName().equals(s)) {
                    if (!bottom.contains(i.getScreenLocation())) {
                        Mouse.hop(i.getScreenLocation().x + offsetX, i.getScreenLocation().y + offsetY);
                        Time.sleep(Random.nextInt(25, 100));
                        Mouse.click(false);
                        if (i.getActions().length == 5) {
                            Mouse.hop(i.getScreenLocation().x, i.getScreenLocation().y + 40);
                        } else {
                            Mouse.hop(i.getScreenLocation().x, i.getScreenLocation().y + 60);
                        }
                        Time.sleep(Random.nextInt(25, 100));
                        Mouse.click(true);
                    } else {
                        Mouse.hop(i.getScreenLocation().x + offsetX, i.getScreenLocation().y + offsetY);
                        Time.sleep(Random.nextInt(25, 100));
                        Mouse.click(false);
                        if (i.getActions().length == 5) {
                            Mouse.hop(i.getScreenLocation().x, i.getScreenLocation().y + 20);
                        } else {
                            Mouse.hop(i.getScreenLocation().x, i.getScreenLocation().y + 40);
                        }
                        Time.sleep(Random.nextInt(25, 100));
                        Mouse.click(true);
                    }
                }
            }
        }
    }


}
