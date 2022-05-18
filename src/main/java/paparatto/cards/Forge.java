package paparatto.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.RitualDaggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.vfx.UpgradeHammerImprintEffect;
import paparatto.Avocado;
import paparatto.actions.ForgeAction;

public class Forge extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Forge");


    private static final CardRarity RARITY = CardRarity.RARE; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Forge() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 10;
        damageUp = 4;
        this.misc = 1;
        this.baseMagicNumber = this.misc;
        this.magicNumber = baseMagicNumber;
        this.exhaust = true;
        this.rawDescription = "Deal !D! Damage. NL Gain " + this.misc + " *Plated *Armor. NL If Fatal, increase this gain by 1. NL Exhaust.";
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ForgeAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 1, this.uuid));
        act(new VFXAction(new UpgradeHammerImprintEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale)));
        act(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.misc), this.misc));
    }

    public void applyPowers() {
        this.baseBlock = this.misc;
        super.applyPowers();
        this.rawDescription = "Deal !D! Damage. NL Gain " + this.misc + " *Plated *Armor. NL If Fatal, increase this gain by 1. NL Exhaust.";
        this.initializeDescription();
    }
    public AbstractCard makeCopy() {
        return new Forge();
    }

}
