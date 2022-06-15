package paparatto.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ExpungeVFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import paparatto.Avocado;

public class HeavySlam extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("HeavySlam");

    private static final CardRarity RARITY = CardRarity.COMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public HeavySlam() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 6;
        baseMagicNumber = 1;
        magicNumberUp = 1;
        magicNumber = baseMagicNumber;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }



    public void calculateCardDamage(AbstractMonster mo) {
        int plate = 0; if (AbstractDungeon.player.hasPower("Plated Armor")) {
            plate = (AbstractDungeon.player.getPower("Plated Armor").amount);
        }
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * plate;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int plate = 0; if (AbstractDungeon.player.hasPower("Plated Armor")) {
            plate = (AbstractDungeon.player.getPower("Plated Armor").amount);
        }
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * plate;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

}