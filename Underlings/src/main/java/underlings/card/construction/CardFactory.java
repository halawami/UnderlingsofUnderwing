package underlings.card.construction;

import com.google.common.reflect.ClassPath;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import underlings.card.Card;
import underlings.card.effect.Effect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class CardFactory {
    private String cardsFilePath;
    private Gson gson;
    private List<Class<? extends Effect>> effectClasses;


    public CardFactory(String cardsFilePath) {
        this.cardsFilePath = cardsFilePath;
        this.effectClasses = getEffectClasses();
        this.gson = getGson();
    }

    private List<Class<? extends Effect>> getEffectClasses() {
        List<Class<? extends Effect>> effectClasses = new ArrayList<>();

        Set<ClassPath.ClassInfo> effectClassInfos = loadEffectClassInfos();
        effectClassInfos.forEach(effectClassInfo -> effectClasses.add(getClassFromClassInfo(effectClassInfo)));

        return effectClasses;
    }

    private Class<? extends Effect> getClassFromClassInfo(ClassPath.ClassInfo effectClassInfo) {
        return (Class<? extends Effect>) effectClassInfo.load();
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
        RuntimeTypeAdapterFactory<Effect> effectsTypeAdapter = getEffectTypeAdapter();
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
        Card[] cards = constructCards();
        return Arrays.asList(cards);
    }

    private Card[] constructCards() {
        try {
            FileReader fileReader = new FileReader(getCardsFile());
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