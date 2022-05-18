package paparatto.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class SuckDummy extends CustomRelic {
    public static final String ID = "SuckDummy";


    public SuckDummy() {
        super(ID, new Texture("pits/images/relics/SuckDummy.png"),new Texture("pits/images/relics/outline/SuckDummy.png"), RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public int onPlayerHeal(int healAmount) {
        if (AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.flash();
            return healAmount + 2;
        } else {
            return healAmount;
        }
    }
    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new SuckDummy();
    }

}