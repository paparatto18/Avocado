package paparatto.NobCards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;

public class NobDefend extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("NobDefend");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF; 
    private static final CardType TYPE = CardType.SKILL;      

    private static final int COST = 1; 

    public NobDefend() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseBlock = 5;
        blockUp = 3;
        this.tags.add(BaseModCardTags.BASIC_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new GainBlockAction(p, p, this.block));
    }
}