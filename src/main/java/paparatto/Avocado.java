package paparatto;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.ModPanel;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import paparatto.NobCards.*;
import paparatto.NobCards.BasicBlock;
import paparatto.NobCards.BasicDraw;
import paparatto.NobCards.BasicEnergy;
import paparatto.NobCards.BasicMetallicize;
import paparatto.NobCards.BasicPower;
import paparatto.NobCards.BasicPowerPunch;
import paparatto.NobCards.BasicPunch;
import paparatto.NobCards.BasicPunchAll;
import paparatto.NobCards.BasicPunchX;
import paparatto.actions.PeelAction;
import paparatto.cards.*;
import paparatto.characters.AvocadoCharacter;
import paparatto.characters.NobCharacter;
import paparatto.patches.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import paparatto.potions.LiquidReclamation;
import paparatto.relics.*;

@SpireInitializer
public class Avocado implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        OnPowersModifiedSubscriber,
        OnPlayerTurnStartSubscriber,
        OnStartBattleSubscriber{

    public static final Logger logger = LogManager.getLogger(Avocado.class.getName());

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "Avocado";
    private static final String AUTHOR = "Paparatto";
    private static final String DESCRIPTION = "Paparatto's Avocado character template";


    public static Gson gson;
    private static Map<String, Keyword> keywords;

    // =============== INPUT TEXTURE LOCATION =================

    // Colors (RGB)
    // Character Color
    public static final Color AVOCADO_GREEN = CardHelper.getColor(110.0f, 112.0f, 66.0f);
    public static final Color NOBBER_RED = CardHelper.getColor(171.0f, 57.0f, 73.0f);

    // Potion Colors in RGB
    // public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f); // Orange-ish Red
    // public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f); // Near White
    // public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f); // Super Dark Red/Brown

    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_AVOCADO_GREEN = "pits/images/512/bg_attack_AVOCADO_GREEN.png";
    private static final String SKILL_AVOCADO_GREEN = "pits/images/512/bg_skill_AVOCADO_GREEN.png";
    private static final String POWER_AVOCADO_GREEN = "pits/images/512/bg_power_AVOCADO_GREEN.png";
    private static final String ENERGY_ORB_AVOCADO_GREEN = "pits/images/512/card_AVOCADO_GREEN_orb.png";
    private static final String CARD_ENERGY_ORB = "pits/images/512/card_small_orb.png";

    private static final String ATTACK_AVOCADO_GREEN_PORTRAIT = "pits/images/1024/bg_attack_AVOCADO_GREEN.png";
    private static final String SKILL_AVOCADO_GREEN_PORTRAIT = "pits/images/1024/bg_skill_AVOCADO_GREEN.png";
    private static final String POWER_AVOCADO_GREEN_PORTRAIT = "pits/images/1024/bg_power_AVOCADO_GREEN.png";
    private static final String ENERGY_ORB_AVOCADO_GREEN_PORTRAIT = "pits/images/1024/card_AVOCADO_GREEN_orb.png";

    private static final String AVOCADOCHARACTER_BUTTON = "pits/images/charSelect/DefaultCharacterButton.png";
    private static final String AVOCADOCHARACTER_PORTRAIT = "pits/images/charSelect/DefaultCharacterPortraitBG.png";

    public static final String AVOCADOCHARACTER_SHOULDER_1 = "pits/images/char/defaultCharacter/shoulder.png";
    public static final String AVOCADOCHARACTER_SHOULDER_2 = "pits/images/char/defaultCharacter/shoulder2.png";
    public static final String AVOCADOCHARACTER_CORPSE = "pits/images/char/defaultCharacter/corpse.png";



    private static final String ATTACK_NOBBER_RED = "pits/images/512/bg_attack_NOBBER_RED.png";
    private static final String SKILL_NOBBER_RED = "pits/images/512/bg_skill_NOBBER_RED.png";
    private static final String POWER_NOBBER_RED = "pits/images/512/bg_power_NOBBER_RED.png";
    private static final String ENERGY_ORB_NOBBER_RED = "pits/images/512/card_NOBBER_RED_orb.png";
    private static final String NOB_CARD_ENERGY_ORB = "pits/images/512/nob_card_small_orb.png";

    private static final String ATTACK_NOBBER_RED_PORTRAIT = "pits/images/1024/bg_attack_NOBBER_RED.png";
    private static final String SKILL_NOBBER_RED_PORTRAIT = "pits/images/1024/bg_skill_NOBBER_RED.png";
    private static final String POWER_NOBBER_RED_PORTRAIT = "pits/images/1024/bg_power_NOBBER_RED.png";
    private static final String ENERGY_ORB_NOBBER_RED_PORTRAIT = "pits/images/1024/card_NOBBER_RED_orb.png";


    private static final String NOBCHARACTER_BUTTON = "pits/images/charSelect/NobCharacterButton.png";
    private static final String NOBCHARACTER_PORTRAIT = "pits/images/charSelect/NobCharacterPortraitBG.png";

    public static final String NOBCHARACTER_SHOULDER_1 = "pits/images/char/defaultCharacter/nobshoulder.png";
    public static final String NOBCHARACTER_SHOULDER_2 = "pits/images/char/defaultCharacter/nobshoulder2.png";
    public static final String NOBCHARACTER_CORPSE = "pits/images/char/defaultCharacter/nobcorpse.png";

    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "pits/images/Badge.png";

    // Atlas and JSON files for the Animations
    public static final String AVOCADOCHARACTER_SKELETON_ATLAS = "pits/images/char/defaultCharacter/skeleton.atlas";
    public static final String AVOCADOCHARACTER_SKELETON_JSON = "pits/images/char/defaultCharacter/skeleton.json";

    // =============== /INPUT TEXTURE LOCATION/ =================


    // =============== SUBSCRIBE, CREATE THE COLOR, INITIALIZE =================

    public Avocado() {
        BaseMod.subscribe(this);


        BaseMod.addColor(AbstractCardEnum.AVOCADO_GREEN, AVOCADO_GREEN.cpy(), AVOCADO_GREEN.cpy(), AVOCADO_GREEN.cpy(),
                AVOCADO_GREEN.cpy(), AVOCADO_GREEN.cpy(), AVOCADO_GREEN.cpy(), AVOCADO_GREEN.cpy(),
                ATTACK_AVOCADO_GREEN, SKILL_AVOCADO_GREEN, POWER_AVOCADO_GREEN, ENERGY_ORB_AVOCADO_GREEN,
                ATTACK_AVOCADO_GREEN_PORTRAIT, SKILL_AVOCADO_GREEN_PORTRAIT, POWER_AVOCADO_GREEN_PORTRAIT,
                ENERGY_ORB_AVOCADO_GREEN_PORTRAIT, CARD_ENERGY_ORB);

        BaseMod.addColor(AbstractCardEnum.NOBBER_RED, NOBBER_RED, NOBBER_RED, NOBBER_RED,
                NOBBER_RED, NOBBER_RED, NOBBER_RED, NOBBER_RED,
                ATTACK_NOBBER_RED, SKILL_NOBBER_RED, POWER_NOBBER_RED, ENERGY_ORB_NOBBER_RED,
                ATTACK_NOBBER_RED_PORTRAIT, SKILL_NOBBER_RED_PORTRAIT, POWER_NOBBER_RED_PORTRAIT,
                ENERGY_ORB_NOBBER_RED_PORTRAIT, NOB_CARD_ENERGY_ORB);
    }


    public static void initialize() {
        logger.info("========================= Initializing Monstrosity. Hi. =========================");
        Avocado paparatto = new Avocado();
        logger.info("========================= Monstrosity Initialized. Hello World./ =========================");
    }

    // ============== /SUBSCRIBE, CREATE THE COLOR, INITIALIZE/ =================


    // =============== LOAD THE CHARACTER =================

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new AvocadoCharacter("the Default", AvocadoCharacterEnum.AVOCADOCHARACTER),
                AVOCADOCHARACTER_BUTTON, AVOCADOCHARACTER_PORTRAIT, AvocadoCharacterEnum.AVOCADOCHARACTER);

//        BaseMod.addCharacter(new NobCharacter("the Default", AvocadoCharacterEnum.NOBCHARACTER),
//                NOBCHARACTER_BUTTON, NOBCHARACTER_PORTRAIT, AvocadoCharacterEnum.NOBCHARACTER);
    }

    // =============== /LOAD THE CHARACTER/ =================


    // =============== POST-INITIALIZE =================


    @Override
    public void receivePostInitialize() {
        // Load the Mod Badge
        Texture badgeTexture = new Texture(BADGE_IMAGE);

        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
    }

    // =============== / POST-INITIALIZE/ =================


    // ================ ADD RELICS ===================

    @Override
    public void receiveEditRelics() {
//      BaseMod.addRelicToCustomPool(new SomeRelic(), AbstractCardEnum.AVOCADO_GREEN);

        BaseMod.addRelicToCustomPool(new Shell(), AbstractCardEnum.AVOCADO_GREEN);
        BaseMod.addRelicToCustomPool(new SturdyCarapace(), AbstractCardEnum.AVOCADO_GREEN);
        BaseMod.addRelicToCustomPool(new MarkOfNausea(), AbstractCardEnum.AVOCADO_GREEN);

        BaseMod.addRelicToCustomPool(new SuckDummy(), AbstractCardEnum.AVOCADO_GREEN);
        BaseMod.addRelicToCustomPool(new NonNewtonianFlesh(), AbstractCardEnum.AVOCADO_GREEN);

        BaseMod.addRelicToCustomPool(new TwistedTendril(), AbstractCardEnum.AVOCADO_GREEN);

        BaseMod.addRelicToCustomPool(new GreenSkull(), AbstractCardEnum.AVOCADO_GREEN);

        BaseMod.addRelicToCustomPool(new PitOfFury(), AbstractCardEnum.AVOCADO_GREEN);
        BaseMod.addRelicToCustomPool(new PreservedParasite(), AbstractCardEnum.AVOCADO_GREEN);
        BaseMod.addRelicToCustomPool(new StartCodon(), AbstractCardEnum.AVOCADO_GREEN);
        BaseMod.addRelicToCustomPool(new TungstenPlate(), AbstractCardEnum.AVOCADO_GREEN);

//        BaseMod.addRelicToCustomPool(new RightHorn(), AbstractCardEnum.NOBBER_RED);



    }

    // ================ /ADD RELICS/ ===================


    // ================ ADD CARDS ===================

    @Override
    public void receiveEditCards() {
        // Add the cards
        
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());


        BaseMod.addCard(new DoubleStrike());
        BaseMod.addCard(new Fell());
//        BaseMod.addCard(new Wither());
        BaseMod.addCard(new Suck());
        BaseMod.addCard(new RainOfPits());
        BaseMod.addCard(new FecklessWhirl());
        BaseMod.addCard(new PlateSnap());
        BaseMod.addCard(new Suction());
        BaseMod.addCard(new SquishyStrike());
        BaseMod.addCard(new ExpirationDate());
        BaseMod.addCard(new TendrilSwipe());
        BaseMod.addCard(new BGone());
        BaseMod.addCard(new Forge());
        BaseMod.addCard(new Toast());
        BaseMod.addCard(new PeelHook());
        BaseMod.addCard(new Extractor());
        BaseMod.addCard(new PitAndRun());
        BaseMod.addCard(new PerfectedSuck());
        BaseMod.addCard(new OnePitWonder());
        BaseMod.addCard(new HeavySlam());
        BaseMod.addCard(new Autophagia());
        BaseMod.addCard(new Scavenger());
        BaseMod.addCard(new ArmedPit());
        BaseMod.addCard(new Splinter());
        BaseMod.addCard(new PitStorm());


        BaseMod.addCard(new ParasiticShell());
        BaseMod.addCard(new Plateheaded());
        BaseMod.addCard(new Suckamid());
        BaseMod.addCard(new EthyleneGas());
        BaseMod.addCard(new PeelItOff());
        BaseMod.addCard(new Overguac());
        BaseMod.addCard(new Constrict());
        BaseMod.addCard(new EatShell());
        BaseMod.addCard(new Harden());
        BaseMod.addCard(new Overblock());
        BaseMod.addCard(new Wrap());
        BaseMod.addCard(new PittedArmor());
        BaseMod.addCard(new SowDoubt());
        BaseMod.addCard(new FlashFreeze());
        BaseMod.addCard(new AcridPuke());
        BaseMod.addCard(new Apoptosis());
        BaseMod.addCard(new Compost());
        BaseMod.addCard(new Necrosis());
        BaseMod.addCard(new EatScrap());
        BaseMod.addCard(new Encapsulate());
//        BaseMod.addCard(new BagOfPits());
        BaseMod.addCard(new Phosphorylation());
        BaseMod.addCard(new Phototropism());
        BaseMod.addCard(new Suffocate());
        BaseMod.addCard(new FleshBarrier());
        BaseMod.addCard(new SlimyShield());
        BaseMod.addCard(new Reclaim());
        BaseMod.addCard(new ShellSmash());
        BaseMod.addCard(new Rummage());
        BaseMod.addCard(new Spoils());
        BaseMod.addCard(new Lunacy());
        BaseMod.addCard(new Toxoplasmosis());
        BaseMod.addCard(new FungalElixir());
        BaseMod.addCard(new Eau());



        BaseMod.addCard(new BalancedDiet());
        BaseMod.addCard(new Overripe());
        BaseMod.addCard(new Growth());
        BaseMod.addCard(new MucousEmbrace());
        BaseMod.addCard(new Baseplate());
        BaseMod.addCard(new Reabsorb());
        BaseMod.addCard(new Goorruption());
        BaseMod.addCard(new Ratrops());
        BaseMod.addCard(new Electroplating());
        BaseMod.addCard(new Strangulation());
        BaseMod.addCard(new ParasiticForm());
        BaseMod.addCard(new Savagery());
        BaseMod.addCard(new Ensnare());
//        BaseMod.addCard(new Recombination());
        BaseMod.addCard(new PeelNoPain());
        BaseMod.addCard(new Shrapnel());
        BaseMod.addCard(new RAT());

        BaseMod.addCard(new GrowChoice());
        BaseMod.addCard(new BiteChoice());

        // ================ /NOB CARDS/ ===================

//        BaseMod.addCard(new NobStrike());
//        BaseMod.addCard(new NobDefend());
//        BaseMod.addCard(new SkullBash());
//        BaseMod.addCard(new Rush());
//
//
//        BaseMod.addCard(new BasicPunchX());
//        BaseMod.addCard(new Smash());
//        BaseMod.addCard(new PerfectedSmash());
//        BaseMod.addCard(new WindCondition());
//        BaseMod.addCard(new Stumble());
//
//
//        BaseMod.addCard(new BasicEnergy());
//        BaseMod.addCard(new Moxie());
//        BaseMod.addCard(new SelfSoothing());
//        BaseMod.addCard(new Irritation());
//        BaseMod.addCard(new SignatureNob());
//        BaseMod.addCard(new Regenerate());
//
//
//        BaseMod.addCard(new Bellow());
//        BaseMod.addCard(new Topheavy());
//        BaseMod.addCard(new Education());



    }

    // ================ /ADD CARDS/ ===================


    // ================ LOAD THE TEXT ===================

    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings");

        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                "pits/localization/eng/Avocado-Card-Strings.json");

        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                "pits/localization/eng/Avocado-Power-Strings.json");

        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                "pits/localization/eng/Avocado-Relic-Strings.json");

        // PotionStrings
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                "pits/localization/eng/Avocado-Potion-Strings.json");

        // CharacterStrings
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                "pits/localization/eng/Avocado-Character-Strings.json");

        logger.info("Done editing strings");
    }

    // ================ /LOAD THE TEXT/ ===================

    // ================ LOAD THE KEYWORDS ===================

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();

        String keywordStrings = Gdx.files.internal("pits/localization/eng/Avocado-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Type typeToken = new TypeToken<Map<String, Keyword>>() {}.getType();

        keywords = (Map)gson.fromJson(keywordStrings, typeToken);

        keywords.forEach((k,v)->{
                BaseMod.addKeyword(v.NAMES, v.DESCRIPTION);
            });
    }
        
    // ================ /LOAD THE KEYWORDS/ ===================    

    public static String makeID(String idText) {
        return "AvocadoCharacter:" + idText;
    }

    @Override
    public void receivePowersModified() {

    }

    @Override
    public void receiveOnPlayerTurnStart() {
        PeelAction.peelsThisTurn = 0;
    }


    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        PeelAction.peelsThisCombat = 0;
    }



}
