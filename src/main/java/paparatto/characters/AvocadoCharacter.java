package paparatto.characters;

import basemod.BaseMod;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import paparatto.Avocado;
import paparatto.cards.*;
import paparatto.patches.AbstractCardEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import paparatto.relics.Shell;

import java.util.ArrayList;

import static paparatto.Avocado.*;

public class AvocadoCharacter extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(Avocado.class.getName());

    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 69;
    public static final int MAX_HP = 69;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;

    // =============== /BASE STATS/ =================


    // =============== STRINGS =================

    private static final String ID = makeID("DefaultCharacter");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    // =============== /STRINGS/ =================


    // =============== TEXTURES OF BIG ENERGY ORB ===============

    public static final String[] orbTextures = {
            "pits/images/char/defaultCharacter/orb/layer1.png",
            "pits/images/char/defaultCharacter/orb/layer2.png",
            "pits/images/char/defaultCharacter/orb/layer3.png",
            "pits/images/char/defaultCharacter/orb/layer4.png",
            "pits/images/char/defaultCharacter/orb/layer5.png",
            "pits/images/char/defaultCharacter/orb/layer6.png",
            "pits/images/char/defaultCharacter/orb/layer1d.png",
            "pits/images/char/defaultCharacter/orb/layer2d.png",
            "pits/images/char/defaultCharacter/orb/layer3d.png",
            "pits/images/char/defaultCharacter/orb/layer4d.png",
            "pits/images/char/defaultCharacter/orb/layer5d.png",};

    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============

    private Texture tex = new Texture("pits/images/char/defaultCharacter/main2.png");
    private double counter = 0.0f;


    // =============== CHARACTER CLASS START =================

    public AvocadoCharacter(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "pits/images/char/defaultCharacter/orb/vfx.png", null,
                (String)null, (String)null);

        tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.atlas = new TextureAtlas();


        // =============== TEXTURES, ENERGY, LOADOUT =================  

        initializeClass(null, // required call to load textures and setup energy/loadout.
                // I left these in Avocado.java (Ctrl+click them to see where they are, Ctrl+hover to see what they read.)
                AVOCADOCHARACTER_SHOULDER_1, // campfire pose
                AVOCADOCHARACTER_SHOULDER_2, // another campfire pose
                AVOCADOCHARACTER_CORPSE, // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================


        // =============== ANIMATIONS =================  


        loadAnimation("images/monsters/theCity/shellMonster/skeleton.atlas", "images/monsters/theCity/shellMonster/skeleton.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.stateData.setMix("Hit", "Idle", 0.2F);
        e.setTimeScale(0.8F);

        this.skeleton.setFlipX(false);

        // =============== /ANIMATIONS/ =================


        // =============== TEXT BUBBLE LOCATION =================

        dialogX = (drawX + 0.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 220.0F * Settings.scale); // you can just copy these values

        // =============== /TEXT BUBBLE LOCATION/ =================

    }

    // =============== /CHARACTER CLASS END/ =================

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");

        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(DoubleStrike.ID);
        retVal.add(ParasiticShell.ID);


        UnlockTracker.markCardAsSeen( Strike.ID);
        UnlockTracker.markCardAsSeen( Defend.ID);

        UnlockTracker.markCardAsSeen( DoubleStrike.ID);
        UnlockTracker.markCardAsSeen( Fell.ID);
        UnlockTracker.markCardAsSeen( Wither.ID);
        UnlockTracker.markCardAsSeen( Suck.ID);
        UnlockTracker.markCardAsSeen( RainOfPits.ID);
        UnlockTracker.markCardAsSeen( FecklessWhirl.ID);
        UnlockTracker.markCardAsSeen( PlateSnap.ID);
        UnlockTracker.markCardAsSeen( Suction.ID);
        UnlockTracker.markCardAsSeen( SquishyStrike.ID);
        UnlockTracker.markCardAsSeen( ExpirationDate.ID);
        UnlockTracker.markCardAsSeen( TendrilSwipe.ID);
        UnlockTracker.markCardAsSeen( BGone.ID);
        UnlockTracker.markCardAsSeen( Forge.ID);
        UnlockTracker.markCardAsSeen( Toast.ID);
        UnlockTracker.markCardAsSeen( PeelHook.ID);
        UnlockTracker.markCardAsSeen( Extractor.ID);
        UnlockTracker.markCardAsSeen( PitAndRun.ID);
        UnlockTracker.markCardAsSeen( PerfectedSuck.ID);
        UnlockTracker.markCardAsSeen( OnePitWonder.ID);
        UnlockTracker.markCardAsSeen( HeavySlam.ID);
        UnlockTracker.markCardAsSeen( Autophagia.ID);
        UnlockTracker.markCardAsSeen( Scavenger.ID);
        UnlockTracker.markCardAsSeen( ArmedPit.ID);
        UnlockTracker.markCardAsSeen( Splinter.ID);
        UnlockTracker.markCardAsSeen( PitStorm.ID);

        UnlockTracker.markCardAsSeen( ParasiticShell.ID);
        UnlockTracker.markCardAsSeen( Plateheaded.ID);
        UnlockTracker.markCardAsSeen( Suckamid.ID);
        UnlockTracker.markCardAsSeen( EthyleneGas.ID);
        UnlockTracker.markCardAsSeen( PeelItOff.ID);
        UnlockTracker.markCardAsSeen( Overguac.ID);
        UnlockTracker.markCardAsSeen( Constrict.ID);
        UnlockTracker.markCardAsSeen( EatShell.ID);
        UnlockTracker.markCardAsSeen( Harden.ID);
        UnlockTracker.markCardAsSeen( Overblock.ID);
        UnlockTracker.markCardAsSeen( Wrap.ID);
        UnlockTracker.markCardAsSeen( PittedArmor.ID);
        UnlockTracker.markCardAsSeen( SowDoubt.ID);
        UnlockTracker.markCardAsSeen( FlashFreeze.ID);
        UnlockTracker.markCardAsSeen( AcridPuke.ID);
        UnlockTracker.markCardAsSeen( Apoptosis.ID);
        UnlockTracker.markCardAsSeen( Compost.ID);
        UnlockTracker.markCardAsSeen( Necrosis.ID);
        UnlockTracker.markCardAsSeen( EatScrap.ID);
        UnlockTracker.markCardAsSeen( Encapsulate.ID);
        UnlockTracker.markCardAsSeen( BagOfPits.ID);
        UnlockTracker.markCardAsSeen( Phosphorylation.ID);
        UnlockTracker.markCardAsSeen( Phototropism.ID);
        UnlockTracker.markCardAsSeen( Suffocate.ID);
        UnlockTracker.markCardAsSeen( FleshBarrier.ID);
        UnlockTracker.markCardAsSeen( SlimyShield.ID);
        UnlockTracker.markCardAsSeen( Reclaim.ID);
        UnlockTracker.markCardAsSeen( ShellSmash.ID);
        UnlockTracker.markCardAsSeen( Rummage.ID);
        UnlockTracker.markCardAsSeen( Spoils.ID);
        UnlockTracker.markCardAsSeen( Lunacy.ID);
        UnlockTracker.markCardAsSeen( Toxoplasmosis.ID);
        UnlockTracker.markCardAsSeen( FungalElixir.ID);

        UnlockTracker.markCardAsSeen( BalancedDiet.ID);
        UnlockTracker.markCardAsSeen( Overripe.ID);
        UnlockTracker.markCardAsSeen( Growth.ID);
        UnlockTracker.markCardAsSeen( MucousEmbrace.ID);
        UnlockTracker.markCardAsSeen( Baseplate.ID);
        UnlockTracker.markCardAsSeen( Reabsorb.ID);
        UnlockTracker.markCardAsSeen( Goorruption.ID);
        UnlockTracker.markCardAsSeen( Ratrops.ID);
        UnlockTracker.markCardAsSeen( Electroplating.ID);
        UnlockTracker.markCardAsSeen( Strangulation.ID);
        UnlockTracker.markCardAsSeen( ParasiticForm.ID);
        UnlockTracker.markCardAsSeen( Savagery.ID);
        UnlockTracker.markCardAsSeen( Ensnare.ID);
        UnlockTracker.markCardAsSeen( Recombination.ID);
        UnlockTracker.markCardAsSeen( PeelNoPain.ID);
        UnlockTracker.markCardAsSeen( Shrapnel.ID);
        UnlockTracker.markCardAsSeen( RAT.ID);

        UnlockTracker.markCardAsSeen( GrowChoice.ID);
        UnlockTracker.markCardAsSeen( BiteChoice.ID);

        return retVal;
    }

    // Starting Relics	
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(Shell.ID);
        UnlockTracker.markRelicAsSeen(Shell.ID);

        return retVal;
    }

    // Character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false); // Screen Effect
    }

    // Character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 4;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.AVOCADO_GREEN;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return paparatto.Avocado.AVOCADO_GREEN.cpy();
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }
    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    // Should return a new instance of your character, sending name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new AvocadoCharacter(name, chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return paparatto.Avocado.AVOCADO_GREEN.cpy();
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() { return paparatto.Avocado.AVOCADO_GREEN.cpy(); }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    @Override
    public String getVampireText() {
        return TEXT[2];
    }
    public void renderPlayerImage(SpriteBatch sb) {
        this.state.update(Gdx.graphics.getDeltaTime());
        this.state.apply(this.skeleton);
        this.skeleton.updateWorldTransform();
        this.skeleton.setPosition(this.drawX + this.animX, this.drawY + this.animY);
        this.skeleton.setColor(this.tint.color);
        this.skeleton.setFlip(!this.flipHorizontal, this.flipVertical);
        sb.end();
        CardCrawlGame.psb.begin();
        sr.draw(CardCrawlGame.psb, this.skeleton);
        CardCrawlGame.psb.end();
        sb.begin();
    }
}
