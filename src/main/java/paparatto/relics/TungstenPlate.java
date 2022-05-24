package paparatto.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnAnyPowerAppliedRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;


public class TungstenPlate extends CustomRelic implements OnReceivePowerRelic {
    public static final String ID = "TungstenPlate";
    private int initialPlate = AbstractDungeon.player.getPower("Plated Armor").amount;


    public TungstenPlate() {
        super(ID, new Texture("pits/images/relics/TwistedTendril.png"),new Texture("pits/images/relics/outline/TwistedTendril.png"), RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }





    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new TungstenPlate();
    }



    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature) {
        return false;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature source, int stackAmount) {

        return OnReceivePowerRelic.super.onReceivePowerStacks(power, source, stackAmount);
    }
}