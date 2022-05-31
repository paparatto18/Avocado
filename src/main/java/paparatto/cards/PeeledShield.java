package paparatto.cards;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;

;import java.util.Iterator;

import static paparatto.actions.PeelAction.peelsThisCombat;

public class PeeledShield extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("PeeledShield");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 4;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    ;

    public PeeledShield() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseBlock = 12;
        blockUp = 4;
        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null) {
            this.configureCostsOnNewCard();
        }

    }

    public void triggerWhenDrawn() {
        this.setCostForTurn(this.cost - peelsThisCombat);
    }

    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        this.setCostForTurn(this.cost - peelsThisCombat);
    }


    public void triggerOnOtherCardPlayed(AbstractCard c) {
        this.setCostForTurn(this.cost - peelsThisCombat);
    }

    public void configureCostsOnNewCard() {
        this.setCostForTurn(this.cost - peelsThisCombat);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new GainBlockAction(p, p, this.block));
    }
}