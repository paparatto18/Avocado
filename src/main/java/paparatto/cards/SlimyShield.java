package paparatto.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import paparatto.Avocado;

import java.util.Iterator;


public class SlimyShield extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("SlimyShield");

    private static final CardRarity RARITY = CardRarity.RARE; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.STATUS;       // ATTACK, SKILL, POWER

    private static final int COST = 2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public SlimyShield() {
        super(ID, COST, TYPE, RARITY, TARGET);

     
        baseMagicNumber = 3;
        magicNumberUp = 1;
        magicNumber = baseMagicNumber;
        
    }

    public static int countCards() {
        int count = 0;
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isStatus(c)) {
                ++count;
            }
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isStatus(c)) {
                ++count;
            }
        }

        var1 = AbstractDungeon.player.discardPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isStatus(c)) {
                ++count;
            }
        }

        var1 = AbstractDungeon.player.exhaustPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isStatus(c)) {
                ++count;
            }
        }

        return count;
    }

    public static boolean isStatus(AbstractCard c) {
        return c.type.equals(CardType.STATUS);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseBlock = this.magicNumber * countCards();
        this.applyPowers();
        act(new GainBlockAction(p,p, this.block));
    }
    

    public void applyPowers() {
        this.baseBlock = this.magicNumber * countCards();
        super.applyPowers();
        this.rawDescription = "Gain !M! Block for ALL Status cards, including Exhaust pile. NL (Gain !B! Block.)";
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
       this.applyPowers();
    }



}

// For the game to run, you need 9 cards - one of each rarity (Common, Uncommon, Rare), and each type!