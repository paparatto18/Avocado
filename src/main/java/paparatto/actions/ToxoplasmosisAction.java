//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package paparatto.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.TheBombPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import paparatto.powers.ToxoplasmosisPower;

import java.lang.reflect.Field;

import static com.badlogic.gdx.utils.reflect.ClassReflection.getDeclaredField;

public class ToxoplasmosisAction extends AbstractGameAction {

    private final AbstractMonster targetMonster;
    private AbstractPlayer p;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;


    public ToxoplasmosisAction(AbstractPlayer p, AbstractMonster m) {

        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.targetMonster = m;
        this.p = p;
    }

    public void update() {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {
            int intentDmg = targetMonster.getIntentDmg();
            if (p.hasPower(VulnerablePower.POWER_ID)) {
                intentDmg = Math.round(intentDmg * 0.66667f);
            }
            this.addToBot(new ApplyPowerAction(this.targetMonster ,p , new ToxoplasmosisPower(this.targetMonster, intentDmg), intentDmg));

        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[0], true));
        }

        this.isDone = true;
    }



    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("OpeningAction");
        TEXT = uiStrings.TEXT;
    }
}