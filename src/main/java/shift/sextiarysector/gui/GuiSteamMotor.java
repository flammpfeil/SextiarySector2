package shift.sextiarysector.gui;

import java.text.NumberFormat;
import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.container.ContainerSteamMotor;
import shift.sextiarysector.tileentity.TileEntitySteamMotor;

public class GuiSteamMotor  extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("sextiarysector:textures/guis/machine/steam_motor.png");
    private TileEntitySteamMotor tileFurnace;

    public GuiSteamMotor(InventoryPlayer p_i1091_1_, TileEntitySteamMotor p_i1091_2_)
    {
        super(new ContainerSteamMotor(p_i1091_1_, p_i1091_2_));
        this.tileFurnace = p_i1091_2_;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        String s = this.tileFurnace.hasCustomInventoryName() ? this.tileFurnace.getInventoryName() : I18n.format(this.tileFurnace.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);


        //Fluid
        if (this.tileFurnace.isFluid())
        {
        	//FluidStack f = this.tileFurnace.getTank().getFluid();
        	this.drawFluidTank(k + 74,l + 17,this.tileFurnace.getTank(),34,52);

        }
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        this.drawTexturedModalRect(k + 73, l + 16, 176, 0, 36, 54);

    }

    @Override
	public void drawScreen(int par1, int par2, float par3)
    {
		super.drawScreen(par1, par2, par3);

		ArrayList arraylist = new ArrayList();

		String f = "None";

		if (this.tileFurnace.isFluid())
        {
			f = this.tileFurnace.getTank().getFluid().getFluid().getLocalizedName(this.tileFurnace.getTank().getFluid());
        }

		NumberFormat nfNum = NumberFormat.getNumberInstance();

		arraylist.add(0,"" + EnumChatFormatting.RESET+ "Fluid Tank" + EnumChatFormatting.RESET );
		arraylist.add(1,"" + EnumChatFormatting.GRAY + "Fluid  : "+f);
		arraylist.add(2, ""+ EnumChatFormatting.GRAY + "Amount : " + nfNum.format(this.tileFurnace.getTank().getFluidAmount()) + " / " +  nfNum.format(this.tileFurnace.getTank().getCapacity()) + " mB");

		GL11.glPushMatrix();
		if (this.func_146978_c(73, 16, 36, 54, par1, par2))
        {
			drawHoveringText(arraylist, par1, par2,fontRendererObj);
        }
        GL11.glPopMatrix();



    }

	public void drawFluidTank(int x, int y, FluidTank fluidTank, int width, int height) {

		GL11.glColor3f(1,1,1);

		FluidStack fluidStack = fluidTank.getFluid();

		ResourceLocation r;
		if(fluidStack.getFluid().getSpriteNumber()==0){
			r = TextureMap.locationBlocksTexture;
		}else{
			r = TextureMap.locationItemsTexture;
		}

		this.mc.getTextureManager().bindTexture(r);
		this.setColor3ubFromInt(fluidStack.getFluid().getColor(fluidStack));

		int widthR = width;//*(fluidTank.getFluidAmount()/fluidTank.getCapacity());
		int heightR = (int) (height*((float)fluidTank.getFluidAmount()/(float)fluidTank.getCapacity()));

		int yR = y+(height-heightR);

		int widthL = 0;
		int heightL = 0;
		IIcon icon = (fluidStack.getFluid().getIcon(fluidStack));

		for (int i = 0; i < widthR; i += 16) {

			for (int j = 0; j < heightR; j += 16) {

				widthL = Math.min(widthR - i, 16);
				heightL = Math.min(heightR - j, 16);
				this.drawTexturedModelRectFromIcon(x + i, yR + j, icon, widthL, heightL);

			}
		}

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);

	}

	public static void setColor3ubFromInt(int color) {

		GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));

	}

}
