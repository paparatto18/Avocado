package paparatto.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;

public class PreservedParasite extends CustomRelic {
    public static final String ID = "PreservedParasite";
    private float MODIFIER_AMT = 0.20F;


    public PreservedParasite() {
        super(ID, new Texture("pits/images/relics/Parasite.png"),new Texture("pits/images/relics/outline/Parasite.png"), RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    public void atBattleStart() {
        if (!AbstractDungeon.getCurrRoom().eliteTrigger) {
            this.flash();

            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (m.currentHealth > (int) ((float) m.maxHealth * (1.0F - this.MODIFIER_AMT))) {
                    m.currentHealth = (int) ((float) m.maxHealth * (1.0F - this.MODIFIER_AMT));
                    m.healthBarUpdatedEvent();
                }
            }

            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }

    }

    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 48;
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new PreservedParasite();
    }

}