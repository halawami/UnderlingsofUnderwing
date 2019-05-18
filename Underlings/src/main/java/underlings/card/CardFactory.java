package underlings.card;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import underlings.card.effect.Effect;

public class CardFactory {

    private String cardsFileName;
    private Gson gson;
    private List<Class<? extends Effect>> effectClasses;


    public CardFactory(String cardsFileName) {
        this.cardsFileName = cardsFileName;
        this.effectClasses = this.getEffectClasses();
        this.gson = this.getGson();
    }

    private List<Class<? extends Effect>> getEffectClasses() {
        Set<ClassPath.ClassInfo> effectClassInfos = this.loadEffectClassInfos();
        return this.getEffectClassesFromInfos(effectClassInfos);

    }

    @SuppressWarnings("unchecked")
    private List<Class<? extends Effect>> getEffectClassesFromInfos(Set<ClassInfo> effectClassInfos) {
        List<Class<? extends Effect>> effectClasses = new ArrayList<>();
        for (ClassInfo classInfo : effectClassInfos) {
            Class<?> loadedClass = classInfo.load();
            if (this.isEffectClass(loadedClass)) {
                effectClasses.add((Class<? extends Effect>) loadedClass);
            }
        }
        return effectClasses;
    }

    private boolean isEffectClass(Class<?> loadedClass) {
        return Effect.class.isAssignableFrom(loadedClass);
    }

    private Set<ClassPath.ClassInfo> loadEffectClassInfos() {
        try {

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            return ClassPath.from(loader).getTopLevelClassesRecursive("underlings.card.effect");

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }

    private Gson getGson() {
        RuntimeTypeAdapterFactory<Effect> effectsTypeAdapter = this.getEffectTypeAdapter();
        return new GsonBuilder().registerTypeAdapterFactory(effectsTypeAdapter).create();
    }

    private RuntimeTypeAdapterFactory<Effect> getEffectTypeAdapter() {
        RuntimeTypeAdapterFactory<Effect> effectsTypeAdapter = RuntimeTypeAdapterFactory.of(Effect.class, "effect");

        for (Class<? extends Effect> effectClass : this.effectClasses) {
            effectsTypeAdapter = effectsTypeAdapter.registerSubtype(effectClass);
        }

        return effectsTypeAdapter;
    }


    public List<Card> getCards() {
        Card[] cards = this.constructCards();

        List<Card> cardList = Arrays.asList(cards);
        Collections.shuffle(cardList);

        return cardList;
    }

    private Card[] constructCards() {
        return this.gson.fromJson(this.getCardsAsString(), Card[].class);
    }

    private String getCardsAsString() {
        try {
            return Resources.toString(Resources.getResource(this.cardsFileName), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
