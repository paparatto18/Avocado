package paparatto.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import paparatto.Avocado;
import paparatto.actions.ModifyMagicAction;
import paparatto.vfx.TextEffect;

import java.util.Iterator;


public class PerfectedSuck extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("PerfectedSuck");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    private int damageRatio = 3;

    public PerfectedSuck() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 9;
        baseMagicNumber = 3;
        magicNumber = baseMagicNumber;
        int damageRatio = 3;
        this.tags.add(CardTags.HEALING);

    }

    public static int countCards() {
        int count = 0;
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isSuck(c)) {
                ++count;
            }
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isSuck(c)) {
                ++count;
            }
        }

        var1 = AbstractDungeon.player.discardPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isSuck(c)) {
                ++count;
            }
        }

        return count;
    }

    public static boolean isSuck(AbstractCard c) {
        return c.hasTag(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Avocado.AVOCADO_GREEN.cpy()), 0.1F));
        this.addToBot(new VFXAction(new TextEffect(m.hb.cX, m.hb.cY, "Sucked", 0.33f)));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        act(new HealAction(p, p, this.magicNumber));
        act(new ModifyMagicAction(this.uuid, -1));
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += damageRatio * countCards();
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += damageRatio * countCards();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.damageRatio = 5;
            this.upgradeName();
            this.rawDescription = this.upgradeDescription;
            this.initializeDescription();
        }
    }

}

// For the game to run, you need 9 cards - one of each rarity (Common, Uncommon, Rare), and each type!