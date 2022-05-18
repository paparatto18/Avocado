//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package paparatto.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.UpgradeHammerImprintEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import java.util.Iterator;
import java.util.UUID;

public class ForgeAction extends AbstractGameAction {
    private int increaseAmount;
    private DamageInfo info;
    private UUID uuid;
    private AbstractCard theCard;

    public ForgeAction(AbstractCreature target, DamageInfo info, int incAmount, UUID targetUUID) {
        this.info = info;
        this.setValues(target, info);
        this.increaseAmount = incAmount;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
        this.uuid = targetUUID;
    }

    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HORIZONTAL));
            this.target.damage(this.info);
            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
                Iterator var1 = AbstractDungeon.player.masterDeck.group.iterator();

                AbstractCard c;
                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (c.uuid.equals(this.uuid)) {
                        c.misc += this.increaseAmount;
                        c.applyPowers();
                        c.baseMagicNumber = c.misc;
                        c.isDamageModified = false;
                    }
                }

                for(var1 = GetAllInBattleInstances.get(this.uuid).iterator(); var1.hasNext(); c.baseDamage = c.misc) {
                    c = (AbstractCard)var1.next();
                    c.misc += this.increaseAmount;
                    c.applyPowers();
                }
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }



        }

        this.tickDuration();
    }
}
