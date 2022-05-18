//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package paparatto.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import java.util.Iterator;

public class TriggerConstrictedAction extends AbstractGameAction {
    AbstractCard card;

    public TriggerConstrictedAction(AbstractCard callingCard) {
        this.card = callingCard;
    }

    public void update() {

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (mo.hasPower("Constricted")) {
                    AbstractPlayer p = null;
                    this.addToBot(new DamageAction(mo, new DamageInfo(p, mo.getPower("Constricted").amount, DamageInfo.DamageType.THORNS)));
            }
        }

        this.isDone = true;
    }
}
