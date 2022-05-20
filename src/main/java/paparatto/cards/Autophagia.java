package paparatto.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import paparatto.Avocado;
import paparatto.actions.AutophagiaAction;


public class Autophagia extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Autophagia");


    private static final CardRarity RARITY = CardRarity.RARE; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Autophagia() {
        super(ID, COST, TYPE, RARITY, TARGET);

        this.baseDamage = 10;
        this.damageUp = 2;
        this.exhaust = true;
        this.baseMagicNumber = 9;
        this.magicNumberUp = 3;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.HEALING);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p != null) {
            this.addToBot(new VFXAction(new BiteEffect(p.hb.cX, p.hb.cY - 40.0F * Settings.scale, Color.OLIVE.cpy()), 0.3F));
        }
        this.addToBot(new AutophagiaAction(p, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return new Autophagia();
    }

}
