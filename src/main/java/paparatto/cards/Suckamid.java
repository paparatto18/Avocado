package paparatto.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;
import paparatto.Avocado;

public class Suckamid extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Suckamid");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 2;

    public Suckamid() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseMagicNumber = 8;
        magicNumberUp = 4;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new HealAction(p, p, this.magicNumber));
        act(new ApplyPowerAction(p, p, new EquilibriumPower(p, 2), 2));

    }
}