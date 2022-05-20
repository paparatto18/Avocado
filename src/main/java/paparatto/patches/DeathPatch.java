package paparatto.patches;


import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.relics.MarkOfTheBloom;
import paparatto.actions.AutophagiaAction;

@SpirePatch(clz = AbstractPlayer.class, method = "damage")
public class DeathPatch {
    @SpireInsertPatch(rloc = 149)
    public static SpireReturn<Void> triggerAutophagia(AbstractPlayer instance, DamageInfo damageInfo) {
        if (!instance.hasRelic(MarkOfTheBloom.ID) && AutophagiaAction.autophagiaPlayed == true) {
            instance.currentHealth = 0;
            instance.isDead = false;


            return SpireReturn.Return(null);
        }

        return SpireReturn.Continue();
    }
}