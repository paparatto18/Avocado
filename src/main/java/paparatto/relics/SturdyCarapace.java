package paparatto.relics;

import basemod.abstracts.CustomRelic;
import basemod.interfaces.PostPowerApplySubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import paparatto.actions.LoseRelicAction;
import paparatto.actions.PeelAction;

import java.util.Objects;

public class SturdyCarapace extends CustomRelic {
    public static final String ID = "SturdyCarapace";


    public SturdyCarapace() {
        super(ID, new Texture("pits/images/relics/SturdyCarapace.png"),new Texture("pits/images/relics/outline/Shell.png"), RelicTier.BOSS, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atTurnStart() {
        if (PeelAction.peelsThisTurn <= 0 ) {
            this.beginLongPulse();
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        super.onPlayCard(c, m);
        if (PeelAction.peelsThisTurn > 0 ) {
            this.pulse = false;
        }
    }

    @Override
    public void obtain() {
        instantObtain(AbstractDungeon.player, 0, true);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlatedArmorPower(AbstractDungeon.player, 2), 2));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(Shell.ID);
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new SturdyCarapace();
    }




}