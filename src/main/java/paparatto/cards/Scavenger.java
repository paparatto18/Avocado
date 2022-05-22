package paparatto.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import paparatto.Avocado;
import paparatto.powers.NextTurnReclaimPower;

public class Scavenger extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Scavenger");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Scavenger() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 15;
        damageUp = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        act(new ApplyPowerAction(p, p, new NextTurnReclaimPower(p, 2), 2));
    }
}