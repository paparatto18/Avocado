package paparatto.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.vfx.UpgradeHammerImprintEffect;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import com.megacrit.cardcrawl.vfx.combat.ReaperEffect;
import paparatto.Avocado;
import paparatto.actions.ExtractorDamageAction;
import paparatto.actions.ForgeAction;

public class Extractor extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Extractor");

    private static final CardRarity RARITY = CardRarity.RARE; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Extractor() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 3;
        damageUp = 1;
        this.exhaust = true;

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new HemokinesisEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY), 0.15F));
        this.addToBot(new ExtractorDamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    public void applyPowers() {
        super.applyPowers();
        this.initializeDescription();
    }
    public AbstractCard makeCopy() {
        return new Extractor();
    }

}
