package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import paparatto.Avocado;

public class SkullBash extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("SkullBash");

    private static final CardRarity RARITY = CardRarity.BASIC; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public SkullBash() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 6;
        damageUp = 2;
        baseMagicNumber = 2;
        magicNumberUp = 1;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        act(new ApplyPowerAction(m,p, new VulnerablePower(m, this.magicNumber, false ), this.magicNumber));
    }
}