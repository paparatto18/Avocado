package paparatto.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;


public class TungstenPlate extends CustomRelic {
    public static final String ID = "TungstenPlate";


    public TungstenPlate() {
        super(ID, new Texture("pits/images/relics/TungstenPlate.png"), new Texture("pits/images/relics/outline/TungstenPlate.png"), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new TungstenPlate();
    }
}