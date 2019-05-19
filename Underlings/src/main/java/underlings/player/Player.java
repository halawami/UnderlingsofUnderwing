package underlings.player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import underlings.card.Card;
import underlings.card.effect.ObserverEffect;
import underlings.element.Element;
import underlings.element.ElementGiver;
import underlings.element.ElementSpaceUtilities;
import underlings.element.NullElement;
import underlings.handler.Handler;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerState;
import underlings.utilities.LocaleUtilities;

public class Player {

    public List<Handler> handlers;
    public int maxHandlers;
    private int points;
    private boolean reached12Points;
    private boolean reached25Points;
    private HandlerFactory handlerFactory;
    public List<Element> elements;
    public int id;
    public List<Card> hatchedCards;
    public Map<Card, Integer> unhatchedCards;
    public ElementSpaceUtilities elementSpaceLogic;
    public int maxHandlersOnSpace;
    private List<ObserverEffect> observerEffects;
    public List<ElementGiver> effectElementGivers;
    private boolean useEffectElementGivers;
    public int hatchingTime;

    public int score;
    public int netTemperature;

    public Player(int maxHandlers, HandlerFactory handlerFactory, int id) {
        this.handlers = new ArrayList<>();
        this.elements = new ArrayList<>();
        this.hatchedCards = new ArrayList<>();
        this.unhatchedCards = new HashMap<>();
        this.observerEffects = new ArrayList<>();
        this.handlerFactory = handlerFactory;
        this.maxHandlers = maxHandlers;
        this.gainHandler();
        this.gainHandler();
        this.points = 0;
        this.reached12Points = false;
        this.reached25Points = false;
        this.id = id;
        this.maxHandlersOnSpace = 1;
        this.hatchingTime = 1;
    }

    public int getHandlerCount() {
        return this.handlers.size();
    }

    public void gainHandler() {
        if (this.handlers.size() != this.maxHandlers) {
            this.handlers.add(this.handlerFactory.createHandler());
        }
    }

    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;
        if (!this.reached12Points) {
            if (this.points >= 12 && this.handlers.size() < 3) {
                this.handlers.add(this.handlerFactory.createHandler());
                this.reached12Points = true;
            }
        }
        if (!this.reached25Points) {
            if (this.points >= 25 && this.handlers.size() < 4) {
                this.handlers.add(this.handlerFactory.createHandler());
                this.reached25Points = true;
            }
        }
    }

    public void loseHandler() {
        if (this.handlers.size() > 2) {
            this.handlers.remove(0);
        }
    }

    public void addElement(Element elementToAdd) {
        if (elementToAdd != NullElement.getInstance()) {
            this.elements.add(elementToAdd);
        }
    }

    public List<Element> getElements() {
        return this.elements;
    }

    public void losePoints(int pointsToLose) {
        this.points -= pointsToLose;
    }

    public void removeElement(Element elementToRemove) {
        this.elements.remove(elementToRemove);
    }

    public List<ElementGiver> getElementGivers() {
        if (this.useEffectElementGivers) {
            return this.effectElementGivers;
        }

        return this.getNormalElementGivers();
    }

    public List<ElementGiver> getNormalElementGivers() {
        List<ElementGiver> elementGivers = new ArrayList<>();

        elementGivers.addAll(this.handlers);

        for (Card card : this.hatchedCards) {
            elementGivers.addAll(card.getElementGivers());
        }

        return elementGivers;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return MessageFormat.format(LocaleUtilities.get("player_number"), this.getId());
    }

    public void destroyAllElements() {
        this.elements.clear();
    }

    public void stealAllElementsFromPlayer(Player player) {
        this.elements.addAll(player.elements);
        player.destroyAllElements();
    }

    public List<Card> getMostValuableDragons() {
        Map<Integer, List<Card>> highestValues = new HashMap<Integer, List<Card>>();
        int max = -1;
        for (Card card : this.hatchedCards) {
            if (!highestValues.containsKey(card.points)) {
                highestValues.put(card.points, new LinkedList<>());
            }
            if (card.points >= max) {
                highestValues.get(card.points).add(card);
                max = card.points;
            }
        }
        return (highestValues.size() > 0) ? highestValues.get(max) : new LinkedList<>();
    }

    public void onPhaseOne() {
        this.observerEffects.forEach(observerEffect -> observerEffect.onPhaseOne(this));
    }

    public void addObserverEffect(ObserverEffect observerEffect) {
        this.observerEffects.add(observerEffect);
    }

    public void useEffectElementGivers(boolean useEffectElementGivers) {
        this.useEffectElementGivers = useEffectElementGivers;
    }

    public void endPhaseOne() {
        this.useEffectElementGivers(false);
    }

    public void moveToIncubation(Card card, int roundsLeft) {
        this.unhatchedCards.put(card, roundsLeft);
        card.handler.moveToState(HandlerState.INCUBATION);
    }
}
