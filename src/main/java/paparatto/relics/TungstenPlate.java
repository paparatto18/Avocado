package paparatto.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;


public class TungstenPlate extends CustomRelic {
    public static final String ID = "TungstenPlate";
    private int initialPlate = AbstractDungeon.player.getPower("Plated Armor").amount;


    public TungstenPlate() {
        super(ID, new Texture("pits/images/relics/TwistedTendril.png"),new Texture("pits/images/relics/outline/TwistedTendril.png"), RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals("Plated Armor")) {
            if (AbstractDungeon.player.getPower("Plated Armor").amount < initialPlate) {


            }

        }
    }



    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new TungstenPlate();
    }

}