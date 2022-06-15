package paparatto.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class RightHorn extends CustomRelic {
    public static final String ID = "RightHorn";
    private static boolean usedThisCombat = false;



    public RightHorn() {
        super(ID,
                new Texture("pits/images/relics/RightHorn.png"),
                new Texture("pits/images/relics/outline/RightHorn.png"),
                RelicTier.STARTER,
                LandingSound.HEAVY);
    }


    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atPreBattle() {
        usedThisCombat = false;
        this.pulse = true;
        this.beginPulse();
    }

    public void wasHPLost(int damageAmount) {
        if (damageAmount > 0 && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !usedThisCombat) {
            this.flash();
            this.pulse = false;
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, 2), 2));
            this.addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 3));

            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            usedThisCombat = true;
            this.grayscale = true;
        }

    }

    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new RightHorn();
    }

}