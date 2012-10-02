package micdoodle8.mods.crossbowmod;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.Render;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderManager;
import net.minecraft.src.RenderPlayer;
import net.minecraft.src.Tessellator;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class ItemRendererCrossbowMod implements IItemRenderer
{
	private Minecraft mcinstance = ModLoader.getMinecraftInstance();
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		switch (type) 
		{
		case ENTITY:
			return false;
		case EQUIPPED:
			return true;
		case INVENTORY:
			return false;
		case FIRST_PERSON_MAP:
			return false;
		}
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		return true;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		if (type == type.EQUIPPED)
		{
//			GL11.glPushMatrix();
//	        Render var24 = RenderManager.instance.getEntityRenderObject(this.mcinstance.thePlayer);
//	        RenderPlayer var26 = (RenderPlayer)var24;
//	        var26.drawFirstPersonHand();
//	        GL11.glPopMatrix();
	        
			if (!(item.getItem() instanceof ItemCrossbow))
			{
				return;
			}
			
			ItemCrossbow crossbow = (ItemCrossbow) item.getItem();
			
			EntityPlayer par1EntityLiving = (EntityPlayer) data[1];
			GL11.glPushMatrix();
			float i = (float) crossbow.reloadingTime + (crossbow.reloadingTime - crossbow.prevReloadingTime);
			float i2 = 0f;
			
			if (par1EntityLiving.inventory.getCurrentItem() == null || !(par1EntityLiving.inventory.getCurrentItem().getItem() instanceof ItemCrossbow))
			{
				return;
			}
			
			if (par1EntityLiving.inventory.getCurrentItem().getItem() instanceof ItemWoodCrossbow)
			{
				i2 = (float) Math.sin((double)i / 20);
			}
			else if (par1EntityLiving.inventory.getCurrentItem().getItem() instanceof ItemStoneCrossbow)
			{
				i2 = (float) Math.sin((double)i / 30);
			}
			else if (par1EntityLiving.inventory.getCurrentItem().getItem() instanceof ItemIronCrossbow)
			{
				i2 = (float) Math.sin((double)i / 40);
			}
			else if (par1EntityLiving.inventory.getCurrentItem().getItem() instanceof ItemGoldCrossbow)
			{
				i2 = (float) Math.sin((double)i / 50);
			}
			else if (par1EntityLiving.inventory.getCurrentItem().getItem() instanceof ItemDiamondCrossbow)
			{
				i2 = (float) Math.sin((double)i / 60);
			}
			
			if (i2 < 0)
			{
				i2 = 0f;
			}
			
			if (!(mcinstance.currentScreen != null && mcinstance.currentScreen.allowUserInput) && (Mouse.getEventDWheel() != 0 || (Keyboard.isKeyDown(Keyboard.KEY_0) || Keyboard.isKeyDown(Keyboard.KEY_1) || Keyboard.isKeyDown(Keyboard.KEY_2) || Keyboard.isKeyDown(Keyboard.KEY_3) || Keyboard.isKeyDown(Keyboard.KEY_4) || Keyboard.isKeyDown(Keyboard.KEY_5) || Keyboard.isKeyDown(Keyboard.KEY_6) || Keyboard.isKeyDown(Keyboard.KEY_7) || Keyboard.isKeyDown(Keyboard.KEY_8) || Keyboard.isKeyDown(Keyboard.KEY_9))))
			{
				i2 = 0;
			}
				
			if (mcinstance.gameSettings.thirdPersonView == 0)
			{
		        GL11.glTranslatef(0.0F, -(Math.abs(i2) / 2), 0.0F);
			}
			
			if (par1EntityLiving.getItemInUseCount() > 0)
			{
		        GL11.glRotatef(-335F, 0.0F, 0.0F, 1.0F);
		        GL11.glRotatef(-50F, 0.0F, 1.0F, 0.0F);
		        GL11.glTranslatef(0.0F, 0.5F, 0.0F);
		        GL11.glTranslatef(0.0F, -0.5F, 0.0F);
		        GL11.glRotatef(50F, 0.0F, 1.0F, 0.0F);
		        GL11.glRotatef(335F, 0.0F, 0.0F, 1.0F);
			}
	        
	        if (Util.isDiamond(item) || Util.isGold(item) || Util.isIron(item) || Util.isStone(item) || Util.isWooden(item))
	        {
	            GL11.glRotatef(80F, 1.0F, 0.0F, 0.0F);
	            GL11.glRotatef(-62F, 0.0F, 0.0F, 1.0F);
	            GL11.glRotatef(-39F, 0.0F, 1.0F, 0.0F);
	            GL11.glTranslatef(-1.5F, -1.4F, -0.7F);
	        }
	        
			renderItemCustom(type, item, data);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			
			float f28 = (float)item.getMaxItemUseDuration() - (((float)par1EntityLiving.getItemInUseCount() - 1) + 1.0F);
            float f32 = f28 / 50F;
            f32 = (f32 * f32 + f32 * 2.0F) / 3F;

            if (f32 > 1.0F)
            {
                f32 = 1.0F;
            }

            GL11.glRotatef(-330F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-50F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(1F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, 0.5F, 0.15F);
	        float f34 = 1.0F + f32 * 0.2F;
            GL11.glTranslatef(0.0F, 0.05F, -0.25F);//
            GL11.glRotatef(50F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(331F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(2F, 1.0F, 0.0F, 1.0F);
            GL11.glScalef(0.7F, 0.7F, 0.7F);
            
            if (Util.isDiamond(item) || Util.isGold(item) || Util.isIron(item) || Util.isStone(item) || Util.isWooden(item))
            {
                GL11.glRotatef(83F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-62F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-45F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(-1.5F, -1.22F, -0.7F);
            }
            
            if (crossbow.requiredItem(par1EntityLiving) != null && crossbow.reloadingTime <= 0 && (par1EntityLiving.inventory.hasItem(crossbow.requiredItem(par1EntityLiving).shiftedIndex) || par1EntityLiving.capabilities.isCreativeMode))
        	{
        		if (crossbow.requiredItem(par1EntityLiving) == Items.diamondBolt)
        		{
                	this.renderItemCustom(type, new ItemStack(Items.diamondBolt), data);
        		}
        		else if (crossbow.requiredItem(par1EntityLiving) == Items.goldBolt)
        		{
                	this.renderItemCustom(type, new ItemStack(Items.goldBolt), data);
        		}
        		else if (crossbow.requiredItem(par1EntityLiving) == Items.ironBolt)
        		{
                	this.renderItemCustom(type, new ItemStack(Items.ironBolt), data);
        		}
        		else if (crossbow.requiredItem(par1EntityLiving) == Items.stoneBolt)
        		{
                	this.renderItemCustom(type, new ItemStack(Items.stoneBolt), data);
        		}
        		else if (crossbow.requiredItem(par1EntityLiving) == Items.woodBolt)
        		{
                	this.renderItemCustom(type, new ItemStack(Items.woodBolt), data);
        		}
        	}
		
            GL11.glPopMatrix();
		}
	}
	
	private void renderItemCustom(ItemRenderType type, ItemStack item, Object... data)
	{
		int par3 = 0;
		EntityLiving par1EntityLiving = (EntityLiving) data[1];
		RenderBlocks renderBlocks = (RenderBlocks) data[0];
		GL11.glPushMatrix();
        IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(item, type.EQUIPPED);
        
        if (item.itemID < 256)
        {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, mcinstance.renderEngine.getTexture(item.getItem().getTextureFile()));
        }
        else
        {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, mcinstance.renderEngine.getTexture(item.getItem().getTextureFile()));
        }

        Tessellator var4 = Tessellator.instance;
        int var5 = par1EntityLiving.getItemIcon(item, par3);
        float var6 = ((float)(var5 % 16 * 16) + 0.0F) / 256.0F;
        float var7 = ((float)(var5 % 16 * 16) + 15.99F) / 256.0F;
        float var8 = ((float)(var5 / 16 * 16) + 0.0F) / 256.0F;
        float var9 = ((float)(var5 / 16 * 16) + 15.99F) / 256.0F;
        float var10 = 0.0F;
        float var11 = 0.3F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glTranslatef(-var10, -var11, 0.0F);
        float var12 = 1.5F;
        GL11.glScalef(var12, var12, var12);
        GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
        this.renderItemIn2D(var4, var7, var8, var6, var9);

        if (item != null && item.hasEffect() && par3 == 0)
        {
            GL11.glDepthFunc(GL11.GL_EQUAL);
            GL11.glDisable(GL11.GL_LIGHTING);
            mcinstance.renderEngine.bindTexture(mcinstance.renderEngine.getTexture("%blur%/misc/glint.png"));
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
            float var13 = 0.76F;
            GL11.glColor4f(0.5F * var13, 0.25F * var13, 0.8F * var13, 1.0F);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPushMatrix();
            float var14 = 0.125F;
            GL11.glScalef(var14, var14, var14);
            float var15 = (float)(System.currentTimeMillis() % 3000L) / 3000.0F * 8.0F;
            GL11.glTranslatef(var15, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
            this.renderItemIn2D(var4, 0.0F, 0.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(var14, var14, var14);
            var15 = (float)(System.currentTimeMillis() % 4873L) / 4873.0F * 8.0F;
            GL11.glTranslatef(-var15, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            this.renderItemIn2D(var4, 0.0F, 0.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);

        GL11.glPopMatrix();
	}
	
	private void renderItemIn2D(Tessellator par1Tessellator, float par2, float par3, float par4, float par5)
    {
        float var6 = 1.0F;
        float var7 = 0.0625F;
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 0.0F, 1.0F);
        par1Tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)par2, (double)par5);
        par1Tessellator.addVertexWithUV((double)var6, 0.0D, 0.0D, (double)par4, (double)par5);
        par1Tessellator.addVertexWithUV((double)var6, 1.0D, 0.0D, (double)par4, (double)par3);
        par1Tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, (double)par2, (double)par3);
        par1Tessellator.draw();
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 0.0F, -1.0F);
        par1Tessellator.addVertexWithUV(0.0D, 1.0D, (double)(0.0F - var7), (double)par2, (double)par3);
        par1Tessellator.addVertexWithUV((double)var6, 1.0D, (double)(0.0F - var7), (double)par4, (double)par3);
        par1Tessellator.addVertexWithUV((double)var6, 0.0D, (double)(0.0F - var7), (double)par4, (double)par5);
        par1Tessellator.addVertexWithUV(0.0D, 0.0D, (double)(0.0F - var7), (double)par2, (double)par5);
        par1Tessellator.draw();
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        int var8;
        float var9;
        float var10;
        float var11;

        for (var8 = 0; var8 < 16; ++var8)
        {
            var9 = (float)var8 / 16.0F;
            var10 = par2 + (par4 - par2) * var9 - 0.001953125F;
            var11 = var6 * var9;
            par1Tessellator.addVertexWithUV((double)var11, 0.0D, (double)(0.0F - var7), (double)var10, (double)par5);
            par1Tessellator.addVertexWithUV((double)var11, 0.0D, 0.0D, (double)var10, (double)par5);
            par1Tessellator.addVertexWithUV((double)var11, 1.0D, 0.0D, (double)var10, (double)par3);
            par1Tessellator.addVertexWithUV((double)var11, 1.0D, (double)(0.0F - var7), (double)var10, (double)par3);
        }

        par1Tessellator.draw();
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(1.0F, 0.0F, 0.0F);

        for (var8 = 0; var8 < 16; ++var8)
        {
            var9 = (float)var8 / 16.0F;
            var10 = par2 + (par4 - par2) * var9 - 0.001953125F;
            var11 = var6 * var9 + 0.0625F;
            par1Tessellator.addVertexWithUV((double)var11, 1.0D, (double)(0.0F - var7), (double)var10, (double)par3);
            par1Tessellator.addVertexWithUV((double)var11, 1.0D, 0.0D, (double)var10, (double)par3);
            par1Tessellator.addVertexWithUV((double)var11, 0.0D, 0.0D, (double)var10, (double)par5);
            par1Tessellator.addVertexWithUV((double)var11, 0.0D, (double)(0.0F - var7), (double)var10, (double)par5);
        }

        par1Tessellator.draw();
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);

        for (var8 = 0; var8 < 16; ++var8)
        {
            var9 = (float)var8 / 16.0F;
            var10 = par5 + (par3 - par5) * var9 - 0.001953125F;
            var11 = var6 * var9 + 0.0625F;
            par1Tessellator.addVertexWithUV(0.0D, (double)var11, 0.0D, (double)par2, (double)var10);
            par1Tessellator.addVertexWithUV((double)var6, (double)var11, 0.0D, (double)par4, (double)var10);
            par1Tessellator.addVertexWithUV((double)var6, (double)var11, (double)(0.0F - var7), (double)par4, (double)var10);
            par1Tessellator.addVertexWithUV(0.0D, (double)var11, (double)(0.0F - var7), (double)par2, (double)var10);
        }

        par1Tessellator.draw();
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, -1.0F, 0.0F);

        for (var8 = 0; var8 < 16; ++var8)
        {
            var9 = (float)var8 / 16.0F;
            var10 = par5 + (par3 - par5) * var9 - 0.001953125F;
            var11 = var6 * var9;
            par1Tessellator.addVertexWithUV((double)var6, (double)var11, 0.0D, (double)par4, (double)var10);
            par1Tessellator.addVertexWithUV(0.0D, (double)var11, 0.0D, (double)par2, (double)var10);
            par1Tessellator.addVertexWithUV(0.0D, (double)var11, (double)(0.0F - var7), (double)par2, (double)var10);
            par1Tessellator.addVertexWithUV((double)var6, (double)var11, (double)(0.0F - var7), (double)par4, (double)var10);
        }

        par1Tessellator.draw();
    }
}