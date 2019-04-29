package underlings.card.construction;

import com.google.common.reflect.ClassPath;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import underlings.card.Card;
import underlings.card.effect.Effect;

public class CardFactory {
    private String cardsFilePath;
    private Gson gson;
    private List<Class<? extends Effect>> effectClasses;


    public CardFactory(String cardsFilePath) {
        this.cardsFilePath = cardsFilePath;
        this.effectClasses = this.getEffectClasses();
        this.gson = this.getGson();
    }

    private List<Class<? extends Effect>> getEffectClasses() {
        List<Class<? extends Effect>> effectClasses = new ArrayList<>();

        Set<ClassPath.ClassInfo> effectClassInfos = this.loadEffectClassInfos();
        effectClassInfos.forEach(effectClassInfo -> effectClasses
                .add(this.getClassFromClassInfo(effectClassInfo)));

        return effectClasses;
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Effect> getClassFromClassInfo(
            ClassPath.ClassInfo effectClassInfo) {
        return (Class<? extends Effect>) effectClassInfo.load();
    }

    private Set<ClassPath.ClassInfo> loadEffectClassInfos() {
        try {

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            return ClassPath.from(loader)
                    .getTopLevelClassesRecursive("underlings.card.effect");

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }

    private Gson getGson() {
        RuntimeTypeAdapterFactory<Effect> effectsTypeAdapter =
                this.getEffectTypeAdapter();
        return new GsonBuilder().registerTypeAdapterFactory(effectsTypeAdapter)
                .create();
    }

    private RuntimeTypeAdapterFactory<Effect> getEffectTypeAdapter() {
        RuntimeTypeAdapterFactory<Effect> effectsTypeAdapter =
                RuntimeTypeAdapterFactory.of(Effect.class, "effect");

        for (Class<? extends Effect> effectClass : this.effectClasses) {
            effectsTypeAdapter =
                    effectsTypeAdapter.registerSubtype(effectClass);
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
        try {
            FileReader fileReader = new FileReader(this.getCardsFile());
            return this.gson.fromJson(fileReader, Card[].class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new Card[0];
        }
    }

    private File getCardsFile() {
        String workingDirectory = Paths.get("").toAbsolutePath().toString();
        return new File(workingDirectory + this.cardsFilePath);
    }
}
