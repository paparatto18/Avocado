package paparatto.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@SpirePatch(clz = AbstractCard.class, method = "canUse")
public class GoorruptionPatch {
    @SpirePrefixPatch
    public static SpireReturn<Boolean> allowStatusToBePlayedWithGoorruption(AbstractCard instance,
                                                                            AbstractPlayer _player,
                                                                            AbstractMonster _targetMonster) {
        if (instance.type == AbstractCard.CardType.STATUS && instance.costForTurn < -1 &&
                AbstractDungeon.player.hasPower("Goorruption")) {
            return SpireReturn.Return(true);
        } else {
            return SpireReturn.Continue();
        }
    }
}