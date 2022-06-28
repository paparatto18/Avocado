package paparatto.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import paparatto.Avocado;
import paparatto.actions.RandomDiscardCopyAction;

public class LiquidReclamation extends AbstractPotion {
    public static final String POTION_ID = "LiquidReclamation";
    private static final PotionStrings potionStrings;

    public LiquidReclamation() {
        super(potionStrings.NAME, "LiquidReclamation", PotionRarity.UNCOMMON, PotionSize.EYE, PotionEffect.NONE, Avocado.AVOCADO_GREEN.cpy(), Color.OLIVE.cpy(), (Color)null);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        this.addToBot(new RandomDiscardCopyAction(this.potency));
    }

    public int getPotency(int ascensionLevel) {
        return 2;
    }

    public AbstractPotion makeCopy() {
        return new LiquidReclamation();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString("LiquidReclamation");
    }
}
