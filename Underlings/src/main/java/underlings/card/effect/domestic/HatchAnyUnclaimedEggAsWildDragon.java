package underlings.card.effect.domestic;

import java.util.ArrayList;
import java.util.List;
import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerMovementLogic;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class HatchAnyUnclaimedEggAsWildDragon extends HatchingGroundEffect {

    public enum Choice {
        YES, NO;

        @Override
        public String toString() {
            return LocaleWrap.get(this.name());
        }

        public static List<Choice> getChoices() {
            List<Choice> choices = new ArrayList<>();
            choices.add(YES);
            choices.add(NO);
            return choices;
        }

    }

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic) {
        Choice choice = gui.promptChoice(LocaleWrap.get("prompt_choice_hatch_wildly"), Choice.getChoices(),
                currentPlayer.getId());
        if (choice == Choice.YES) {
            List<Card> unclaimedEggs = hatchingGround.getUnclaimedEggs();
            if (unclaimedEggs.size() == 0) {
                gui.notifyAction(currentPlayer.getId(), LocaleWrap.get("notify_no_unclaimed_eggs"));
            } else {
                Card toHatch =
                        gui.promptChoice(LocaleWrap.get("prompt_card_hatch_wildly"), unclaimedEggs, currentPlayer.id);
                eggHatchingLogic.hatchEgg(toHatch, true, FakePlayer.getInstance());
            }
        }
    }

}
