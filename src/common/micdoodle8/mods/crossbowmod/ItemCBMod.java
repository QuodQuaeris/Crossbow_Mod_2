package micdoodle8.mods.crossbowmod;

import net.minecraft.src.*;

public class ItemCBMod extends Item
{
    public ItemCBMod(int i) 
    {
		super(i);
        this.setCreativeTab(CreativeTabs.tabCombat);
	}

    @Override
	public String getTextureFile()
    {
    	return "/Mic'sMods/CrossbowMod/gui/other.png";
    }
}