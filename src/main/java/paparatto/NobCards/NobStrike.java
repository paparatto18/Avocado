package paparatto.NobCards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;

public class NobStrike extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("NobStrike");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY; 
    private static final CardType TYPE = CardType.ATTACK;      

    private static final int COST = 1; 

    public NobStrike() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 6;
        damageUp = 3;

        this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
}