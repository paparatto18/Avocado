package paparatto.NobCards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import paparatto.patches.AbstractCardEnum;

public abstract class AbstractNobCharacterCard extends CustomCard {

    public int damageUp = 0;
    public int blockUp = 0;
    public int magicNumberUp = 0;
    public int newCost = -2;
    public String upgradeDescription = "";

    public AbstractNobCharacterCard(final String id, final int cost,
                                    final CardType type, final CardRarity rarity, final CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, "pits/images/nobcards/" + id.split(":")[1] + ".png", cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type, AbstractCardEnum.NOBBER_RED, rarity, target);



        this.upgradeDescription = CardCrawlGame.languagePack.getCardStrings(id).UPGRADE_DESCRIPTION;
    }

    public void act(AbstractGameAction action) { 
        AbstractDungeon.actionManager.addToBottom(action);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(damageUp);
            upgradeBlock(blockUp);
            upgradeMagicNumber(magicNumberUp);
            if (newCost != -2) {
                upgradeBaseCost(newCost);
            }
            if (this.upgradeDescription != "" && this.upgradeDescription != null) {
                this.rawDescription = this.upgradeDescription;
            }
            initializeDescription();
        }
    }
}