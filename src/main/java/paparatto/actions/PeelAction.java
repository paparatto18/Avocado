//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package paparatto.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PeelAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public static int peelsThisTurn;
    public static int peelsThisCombat;


    public PeelAction(AbstractPlayer p, int amount) {
        this.amount = amount;
        this.p = p;
    }

    public void update() {
        if (AbstractDungeon.player.hasRelic("TungstenPlate")){
            this.amount -= 1;
        }

        if (AbstractDungeon.player.hasPower("Plated Armor")) {
            if (AbstractDungeon.player.getPower("Plated Armor").amount >= this.amount) {
                this.addToBot(new ReducePowerAction(p, p, "Plated Armor", this.amount));
                peelsThisTurn++;
                peelsThisCombat++;

                if (AbstractDungeon.player.hasPower("PeelNoPain")) {
                    this.addToBot(new GainBlockAction(p, AbstractDungeon.player.getPower("PeelNoPain").amount));
                }

                if (AbstractDungeon.player.hasPower("Shrapnel")) {
                    this.addToBot(new DamageRandomEnemyAction(new DamageInfo(p, AbstractDungeon.player.getPower("Shrapnel").amount, DamageInfo.DamageType.THORNS), AttackEffect.BLUNT_LIGHT));
                }

            }
        }
        this.isDone = true;
    }
}