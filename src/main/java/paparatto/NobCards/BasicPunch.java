package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;
import paparatto.cards.AbstractAvocadoCharacterCard;

public class BasicPunch extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("BasicPunch");

    private static final CardRarity RARITY = CardRarity.COMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public BasicPunch() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 8;         
        damageUp = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
}