package paparatto.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import paparatto.patches.*;

public abstract class AbstractAvocadoCharacterCard extends CustomCard {

    public int damageUp = 0;
    public int blockUp = 0;
    public int magicNumberUp = 0;
    public int newCost = -2;
    public String upgradeDescription = "";

    public AbstractAvocadoCharacterCard(final String id, final int cost,
                               final CardType type, final CardRarity rarity, final CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, "pits/images/cards/" + id.split(":")[1] + ".png", cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type, AbstractCardEnum.AVOCADO_GREEN, rarity, target);

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