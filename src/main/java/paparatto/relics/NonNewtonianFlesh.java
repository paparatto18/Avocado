package paparatto.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ImpulseAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.Iterator;

public class NonNewtonianFlesh extends CustomRelic {
    public static final String ID = "NonNewtonianFlesh";
    private int blockAmount;

    public NonNewtonianFlesh() {
        super(ID, new Texture("pits/images/relics/Flesh.png"),new Texture("pits/images/relics/outline/Flesh.png"), RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atTurnStart() {
        if (this.pulse) {
            this.pulse = false;
            this.flash();
            if (AbstractDungeon.player.hasPower("Plated Armor")) {
                blockAmount = MathUtils.floor((float)AbstractDungeon.player.getPower("Plated Armor").amount * 0.5F);
            }
            this.addToTop(new GainBlockAction(AbstractDungeon.player, blockAmount));
        }
    }

    public void wasHPLost(int damageAmount) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && damageAmount > 0) {
            this.flash();
            if (!this.pulse) {
                this.beginPulse();
                this.pulse = true;
            }
        }

    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new NonNewtonianFlesh();
    }

}