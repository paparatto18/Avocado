package paparatto.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class GreenSkull extends CustomRelic {
    public static final String ID = "GreenSkull";
    private static final int PLATE_AMT = 3;
    private boolean isActive = false;


    public GreenSkull() {
        super(ID, new Texture("pits/images/relics/GreenSkull.png"),new Texture("pits/images/relics/outline/GreenSkull.png"), RelicTier.COMMON, LandingSound.CLINK);
    }

    public void atBattleStart() {
        this.isActive = false;
        this.addToBot(new AbstractGameAction() {
            public void update() {
                if (!GreenSkull.this.isActive && AbstractDungeon.player.isBloodied) {
                    GreenSkull.this.flash();
                    GreenSkull.this.pulse = true;
                    AbstractDungeon.player.addPower(new PlatedArmorPower(AbstractDungeon.player, 3));
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, GreenSkull.this));
                    GreenSkull.this.isActive = true;
                    AbstractDungeon.onModifyPower();
                }

                this.isDone = true;
            }
        });
//        while (this.isActive) {
//            if (AbstractDungeon.player.hasPower("Plated Armor")) {
//                if (AbstractDungeon.player.getPower("Plated Armor").amount < 3) {
//                    AbstractPlayer p = AbstractDungeon.player;
//                    this.addToTop(new ReducePowerAction(p,p, "Plated Armor", AbstractDungeon.player.getPower("Plated Armor").amount));
//                    this.addToTop(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 3), 3));
//
//                }
//            }
//        }
    }

    public void onBloodied() {
        this.flash();
        this.pulse = true;
        if (!this.isActive && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractPlayer p = AbstractDungeon.player;
            this.addToTop(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 3), 3));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.isActive = true;
            AbstractDungeon.player.hand.applyPowers();
        }

    }

    public void onNotBloodied() {
        if (this.isActive && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractPlayer p = AbstractDungeon.player;
            this.addToTop(new ReducePowerAction(p,p, "Plated Armor", 3));
        }

        this.stopPulse();
        this.isActive = false;
        AbstractDungeon.player.hand.applyPowers();
    }

    public void onVictory() {
        this.pulse = false;
        this.isActive = false;
    }
    
    
    
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new GreenSkull();
    }

}