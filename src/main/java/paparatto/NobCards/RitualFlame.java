package paparatto.NobCards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.UpgradeHammerImprintEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import paparatto.Avocado;
import paparatto.actions.ForgeAction;
import paparatto.cards.Forge;

public class RitualFlame extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("RitualFlame");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 1;

    public RitualFlame() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 10;
        damageUp = 4;
        this.misc = 1;
        this.baseMagicNumber = this.misc;
        this.magicNumber = baseMagicNumber;
        this.exhaust = true;
        this.rawDescription = "Deal !D! Damage. NL Gain " + this.misc + " *Strength. NL If Fatal, increase this gain by 1. NL Exhaust.";


    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ForgeAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 1, this.uuid));
        this.addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
        act(new ApplyPowerAction(p, p, new StrengthPower(p, this.misc), this.misc));
    }

    public void applyPowers() {
        this.baseBlock = this.misc;
        this.baseMagicNumber = this.misc;
        super.applyPowers();
        this.rawDescription = "Deal !D! Damage. NL Gain " + "!M!" + " *Strength. NL If Fatal, increase this gain by 1. NL Exhaust.";
        this.initializeDescription();
    }
    public AbstractCard makeCopy() {
        return new Forge();
    }

}